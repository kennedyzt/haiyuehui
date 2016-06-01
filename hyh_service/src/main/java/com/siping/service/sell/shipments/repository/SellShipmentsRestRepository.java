package com.siping.service.sell.shipments.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.service.inventory.service.InventoryRestService;
import com.siping.service.material.service.MaterialRestService;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.inventory.request.GetMaterialBatchNumberListRequest;
import com.siping.smartone.inventory.response.GetMaterialBatchNumberResponse;
import com.siping.smartone.invoicing.inventory.inventory.request.AddInventoryRequest;
import com.siping.smartone.invoicing.sell.shipments.request.AddSellShipmentsItemRequest;
import com.siping.smartone.invoicing.sell.shipments.request.AddSellShipmentsRequest;
import com.siping.smartone.invoicing.sell.shipments.request.BatchAddSellShipmentsRequest;
import com.siping.smartone.invoicing.sell.shipments.request.DeleteSellShipmentsRequest;
import com.siping.smartone.invoicing.sell.shipments.response.GetSellShipmentsItemResponse;
import com.siping.smartone.invoicing.sell.shipments.response.GetSellShipmentsListResponse;
import com.siping.smartone.invoicing.sell.shipments.response.GetSellShipmentsResponse;
import com.siping.smartone.material.response.GetMaterialResponse;
import com.siping.smartone.wms.response.ReadyShipmentsItemResponse;
import com.siping.smartone.wms.response.ReadyShipmentsResponse;
import com.siping.wms.service.ReadyShipmentsRestService;

@Repository
public class SellShipmentsRestRepository extends BillsRepository {
    @Autowired
    private InventoryRestService inventoryRestService;
    @Autowired
    private MaterialRestService materialRestService;
    @Autowired
    private ReadyShipmentsRestService readyShipmentsRestService;

    public Boolean add(AddSellShipmentsRequest request) {
        if (!request.getIsDraft()) {
            // checkPostPeriodDateTime(); // 检查是否在过账期间,存为草稿时不检查
        }
        String shipmentsNumber = "";
        if (!StringUtils.hasText(request.getShipmentsNumber())) {
            shipmentsNumber = generateBillsNumber("sell_shipments", "shipments_number");
            request.setShipmentsNumber(shipmentsNumber);
        } else {
            shipmentsNumber = request.getShipmentsNumber();
            int delete = jdbcAccessContext.execute("SELL.SQL_DELETE_SELLSHIPMENTS_AND_SELLSHIPMENTSITEM_BYSHIPMENTSNUMBER", new Object[] { request.getShipmentsNumber() });
            if (-1 == delete) {
                return Boolean.FALSE;
            }
        }
        Object[] params = new Object[] { shipmentsNumber, request.getPartnerId(), request.getOutboundStorage(), request.getSummary(), request.getIsDraft(), request.getTotalPrice(),
                request.getGiftPrice(), request.getFavorablePrice(), request.getGatheringPrice(), request.getBillsDate(), request.getCreateBy(), request.getAuditor(), null,
                request.getOwner(), request.getFromBillsNo() };
        int addSellOrder = jdbcAccessContext.execute("SELL.SQL_ADD_SELL_SHIPMENTS", params);
        if (-1 != addSellOrder) {
            int[] batchAddItems = addShipmentsItems(request, shipmentsNumber);
            if (Arrays.asList(batchAddItems).contains(-1)) {
                throw new BusinessProcessException("新增发货单项失败!");
            }
            reduceInventoryCount(request); // 确认销售发货，减少库存量
            addOperationLog("销售发货单", "新增销售发货单，单据编号：[{0}],请求的ip为：[" + request.getIp() + "]，mac为：[" + request.getMac() + "]", shipmentsNumber, request.getCreateBy() + "");
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    // 销售发货，则减少库存量
    private void reduceInventoryCount(AddSellShipmentsRequest request) {
        for (AddSellShipmentsItemRequest item : request.getShipmentsItemList()) {
            AddInventoryRequest i = new AddInventoryRequest();
            i.setMaterialId(StringUtils.hasText(item.getMaterialId()) ? Integer.valueOf(item.getMaterialId()) : null);
            i.setStorageId(StringUtils.hasText(request.getOutboundStorage()) ? Integer.valueOf(request.getOutboundStorage()) : null);
            i.setStorageLocationId(item.getStorageLocationId());
            i.setIsInbound(Boolean.FALSE);
            i.setBillsNo(request.getShipmentsNumber());
            i.setBillsDate(request.getBillsDate());
            i.setCounts(item.getCounts());
            i.setTotal(item.getTotal());
            i.setPurchasePrice(item.getPurchasePrice() * item.getDiscount() / 100);
            i.setSellPrice(item.getSellPrice());
            i.setCreateBy(StringUtils.hasText(request.getCreateBy()) ? Integer.valueOf(request.getCreateBy()) : null);
            i.setBatchNumber(item.getBatchNumber());
            i.setProductionDate(item.getProductionDate());
            GetMaterialResponse materialResponse = materialRestService.get(item.getMaterialId().toString());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try { // 设置过期时间
                i.setExpirationDate(df.format(new Date(df.parse(item.getProductionDate()).getTime() + materialResponse.getExpirationDate() * 24 * 60 * 60 * 1000)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // i.setExpirationDate(null);
            inventoryRestService.add(i);
        }

    }

    private List<AddInventoryRequest> assembleInventoryLogList(AddSellShipmentsRequest request) {
        List<AddInventoryRequest> inventoryRequestList = new ArrayList<AddInventoryRequest>();// 初始化库存日志对象
        for (AddSellShipmentsItemRequest itemRequest : request.getShipmentsItemList()) {
            checkInvnetoryNumber(itemRequest.getMaterialId(), request.getOutboundStorage(), itemRequest.getCounts().toString());// 检测库存量是否满足条件
            if (!materialRestService.get(itemRequest.getMaterialId()).getIsBatch()) {// 未启用批次管理的操作
                try {
                    assembleInventoryLogWithNoBatch(inventoryRequestList, itemRequest, request);// 组装未启用批次的库存日志
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new BusinessProcessException("生成未启用批次商品出库日志时出现错误");
                }
            } else {
                try {
                    assembleInventoryLogWithBatch(inventoryRequestList, itemRequest, request);// 组装启用批次的库存日志
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new BusinessProcessException("生成启用批次商品出库日志时出现错误");
                }
            }
        }
        return inventoryRequestList;
    }

    private void assembleInventoryLogWithNoBatch(List<AddInventoryRequest> inventoryRequestList, AddSellShipmentsItemRequest itemRequest, AddSellShipmentsRequest request) throws Exception {
        AddInventoryRequest i = new AddInventoryRequest();
        i.setMaterialId(Integer.valueOf(itemRequest.getMaterialId()));
        i.setStorageId(Integer.valueOf(request.getOutboundStorage()));
        i.setBatchNumber(null);// 未启用批次的不存在着两项
        i.setProductionDate(null);// 未启用批次的不存在着两项
        i.setCounts(itemRequest.getCounts());
        i.setSellPrice(itemRequest.getAfterDiscount());
        i.setTotal(itemRequest.getTotal());
        // i.setPurchasePrice(null);//出库不会对这个价格作维护
        i.setSellPrice(itemRequest.getSellPrice());
        i.setBillsNo(request.getShipmentsNumber());
        i.setBillsDate(request.getBillsDate());
        i.setIsInbound(Boolean.FALSE);
        i.setCreateBy(Integer.valueOf(request.getCreateBy()));
        // i.setFromBillsId(request.getFromBillsNo());
        inventoryRequestList.add(i);
    }

    private void assembleInventoryLogWithBatch(List<AddInventoryRequest> inventoryRequestList, AddSellShipmentsItemRequest itemRequest, AddSellShipmentsRequest request) throws Exception {
        GetMaterialBatchNumberListRequest getInventoryCheckMaterialBatchNumberListRequest = new GetMaterialBatchNumberListRequest();
        getInventoryCheckMaterialBatchNumberListRequest.setMaterialId(itemRequest.getMaterialId());
        getInventoryCheckMaterialBatchNumberListRequest.setStorageId(request.getOutboundStorage());
        List<GetMaterialBatchNumberResponse> batchNumberResponses = getMaterialBatchNumberList(getInventoryCheckMaterialBatchNumberListRequest);
        List<Double> smNumbers = new ArrayList<Double>();
        Double sum = new Double(0);
        Double sumOut = new Double(0);
        for (int i = 0; i < batchNumberResponses.size(); i++) {
            smNumbers.add(Double.valueOf(batchNumberResponses.get(i).getSmNumber()));
            sum = sum + smNumbers.get(i);
            if (sum >= itemRequest.getCounts()) {
                break;
            }
        }
        if (smNumbers.size() == 1) {
            AddInventoryRequest addInventoryRequest = new AddInventoryRequest();// 第一条数据够了
            addInventoryRequest.setBatchNumber(batchNumberResponses.get(0).getBatchNumber());
            // addInventoryRequest.setFromBillsId(request.getFromBillsNo());
            addInventoryRequest.setProductionDate(batchNumberResponses.get(0).getProductionDate());
            addInventoryRequest.setIsInbound(false);
            addInventoryRequest.setCounts(itemRequest.getCounts());
            addInventoryRequest.setSellPrice(itemRequest.getAfterDiscount());
            // addInventoryRequest.setPurchasePrice(batchNumberResponses.get(0).getPrice());
            addInventoryRequest.setMaterialId(Integer.valueOf(itemRequest.getMaterialId()));
            addInventoryRequest.setBillsDate(request.getBillsDate());
            addInventoryRequest.setBillsNo(request.getShipmentsNumber());
            addInventoryRequest.setStorageId(Integer.valueOf(request.getOutboundStorage()));
            addInventoryRequest.setTotal(addInventoryRequest.getSellPrice() * addInventoryRequest.getCounts());
            addInventoryRequest.setCreateBy(Integer.valueOf(request.getCreateBy()));
            // addInventoryRequest.setStorageLocationId();
            inventoryRequestList.add(addInventoryRequest);
        } else {
            for (int j = 0; j < smNumbers.size(); j++) {
                AddInventoryRequest addInventoryRequest = new AddInventoryRequest();
                addInventoryRequest.setBatchNumber(batchNumberResponses.get(j).getBatchNumber());
                // addInventoryRequest.setFromBillsId(request.getFromBillsNo());
                addInventoryRequest.setProductionDate(batchNumberResponses.get(j).getProductionDate());
                addInventoryRequest.setIsInbound(false);
                addInventoryRequest.setSellPrice(itemRequest.getAfterDiscount());
                // addInventoryRequest.setPurchasePrice(batchNumberResponses.get(j).getPrice());
                addInventoryRequest.setMaterialId(Integer.valueOf(itemRequest.getMaterialId()));
                addInventoryRequest.setBillsDate(request.getBillsDate());
                addInventoryRequest.setBillsNo(request.getShipmentsNumber());
                addInventoryRequest.setStorageId(Integer.valueOf(request.getOutboundStorage()));
                addInventoryRequest.setCreateBy(Integer.valueOf(request.getCreateBy()));
                if (j == smNumbers.size() - 1) {
                    addInventoryRequest.setCounts(itemRequest.getCounts() - sumOut);
                    addInventoryRequest.setTotal(addInventoryRequest.getSellPrice() * addInventoryRequest.getCounts());
                } else {
                    sumOut = sumOut + batchNumberResponses.get(j).getCounts();
                    addInventoryRequest.setCounts(batchNumberResponses.get(j).getCounts());
                    addInventoryRequest.setTotal(addInventoryRequest.getSellPrice() * addInventoryRequest.getCounts());
                }
                inventoryRequestList.add(addInventoryRequest);
            }
        }

    }

    private int[] addShipmentsItems(AddSellShipmentsRequest request, String shipmentsNumber) {
        List<Object[]> paramList = new ArrayList<Object[]>();
        for (AddSellShipmentsItemRequest item : request.getShipmentsItemList()) {
            List<Object> arr = new ArrayList<Object>();
            arr.add(shipmentsNumber);
            arr.add(item.getMaterialId());
            arr.add(item.getBatchNumber());
            arr.add(item.getProductionDate() == "" ? null : item.getProductionDate());
            arr.add(item.getCounts());
            arr.add(item.getPurchasePrice());
            arr.add(item.getSellPrice());
            arr.add(item.getDiscount());
            arr.add(item.getAfterDiscount());
            arr.add(item.getTaxRate());
            arr.add(item.getTax());
            arr.add(item.getTotal());
            arr.add(item.getIsGift());
            arr.add(item.getRemark());
            arr.add(new Date());
            arr.add(request.getCreateBy());
            paramList.add(arr.toArray());
        }
        return jdbcAccessContext.batchExecute("SELL.SQL_ADD_SELL_SHIPMENTS_ITEMS", paramList);
    }

    public Boolean edit(AddSellShipmentsRequest request) {
        GetSellShipmentsResponse shipments = getSellShipments(request.getShipmentsNumber());
        if (null == shipments)
            throw new BusinessProcessException("销售发货单不存在!");
        if (!shipments.getIsDraft())
            throw new BusinessProcessException("不能编辑正式的销售发货单!");
        Object[] params = new Object[] { request.getPartnerId(), request.getOutboundStorage(), request.getSummary(), request.getIsDraft(), request.getTotalPrice(), request.getGiftPrice(),
                request.getFavorablePrice(), request.getGatheringPrice(), request.getBillsDate(), request.getAuditor(), null, request.getOwner(), request.getShipmentsNumber() };
        int editOrder = jdbcAccessContext.execute("SELL.SQL_UPDATE_SELL_SHIPMENTS", params); // 编辑订单项
        List<AddInventoryRequest> inventoryRequestList = new ArrayList<AddInventoryRequest>();
        if (-1 != editOrder) {// 删除以前的销售发货单项
            int deleteOrderItem = jdbcAccessContext.execute("SELL.SQL_DELETE_SELL_SHIPMENTS_ITEMS", new Object[] { request.getShipmentsNumber() });
            if (-1 != deleteOrderItem) {// 新增销售发货单项
                int[] batchAddItems = addShipmentsItems(request, request.getShipmentsNumber());
                while (Arrays.asList(batchAddItems).contains(-1)) {
                    throw new BusinessProcessException("编辑销售发货单项失败!");
                }
                addOperationLog("销售发货单", "更新销售发货单，单据编号：[{0}]", request.getShipmentsNumber(), request.getCreateBy() + "");
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public GetSellShipmentsResponse get(String shipmentsNumber) {
        GetSellShipmentsResponse returns = getSellShipments(shipmentsNumber);
        if (null == returns)
            throw new BusinessProcessException("销售发货单不存在!");
        List<GetSellShipmentsItemResponse> shipmentsItems = jdbcAccessContext.find("SELL.SQL_GET_SELL_SHIPMENTS_ITEM", new RowMapper<GetSellShipmentsItemResponse>() {
            @Override
            public GetSellShipmentsItemResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildGetSellShipmentsItemResponse(rs);
            }

        }, new Object[] { shipmentsNumber });
        if (CollectionUtils.isEmpty(shipmentsItems)) {
        }
        // throw new BusinessProcessException("销售发货单没有发货单项!");
        returns.setShipmentsItemList(shipmentsItems);
        return returns;
    }

    private GetSellShipmentsResponse getSellShipments(String shipmentsNumber) {
        return jdbcAccessContext.findUniqueResult("SELL.SQL_GET_SELL_SHIPMENTS", new RowMapper<GetSellShipmentsResponse>() {
            @Override
            public GetSellShipmentsResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildGetSellShipments(paramResultSet);
            }
        }, new Object[] { shipmentsNumber });
    }

    private GetSellShipmentsResponse buildGetSellShipments(ResultSet rs) throws SQLException {
        GetSellShipmentsResponse response = new GetSellShipmentsResponse();
        response.setShipmentsNumber(rs.getString("shipments_number"));
        response.setPartnerId(rs.getInt("partner_id") + "");
        response.setPartnerCode(rs.getString("partner_code"));
        response.setPartnerName(rs.getString("partner_name"));
        response.setPartnerContactsFirstName(rs.getString("contacts_first_name"));
        response.setPartnerContactsLastName(rs.getString("contacts_last_name"));
        response.setPartnerContactsMobilephone(rs.getString("contacts_mobilephone"));
        response.setOutboundStorage(rs.getString("outbound_storage"));
        response.setStorageName(rs.getString("storageName"));
        response.setSummary(rs.getString("summary"));
        response.setIsDraft(rs.getBoolean("is_draft"));
        response.setTotalPrice(rs.getDouble("total_price"));
        response.setGiftPrice(rs.getDouble("gift_price"));
        response.setFavorablePrice(rs.getDouble("favorable_price"));
        response.setGatheringPrice(rs.getDouble("gathering_price"));
        response.setBillsDate(DateUtils.formatDate(rs.getDate("bills_date"), "yyyy-MM-dd"));
        response.setCreateDate(rs.getString("create_date"));
        response.setCreateBy(rs.getString("create_by"));
        response.setAuditor(rs.getInt("auditor") + "");
        response.setAuditDate(DateUtils.formatDate(rs.getDate("audit_date"), "yyyy-MM-dd"));
        response.setOwner(rs.getString("owner"));
        response.setOwnerName(rs.getString("ownerName"));
        return response;
    }

    private GetSellShipmentsItemResponse buildGetSellShipmentsItemResponse(ResultSet rs) throws SQLException {
        GetSellShipmentsItemResponse r = new GetSellShipmentsItemResponse();
        r.setId(rs.getString("id"));
        r.setShipmentsNumber(rs.getString("shipments_number"));
        r.setMaterialId(rs.getString("material_id"));
        r.setBatchNumber(rs.getString("batch_number"));
        r.setProductionDate(DateUtils.formatDate(rs.getDate("production_date"), "yyyy-MM-dd"));
        r.setCounts(rs.getDouble("counts"));
        r.setPurchasePrice(rs.getDouble("purchase_price"));
        r.setSellPrice(rs.getDouble("sell_price"));
        r.setDiscount(rs.getDouble("discount"));
        r.setAfterDiscount(rs.getDouble("after_discount"));
        r.setTaxRate(rs.getDouble("tax_rate"));
        r.setTax(rs.getDouble("tax"));
        r.setTotal(rs.getDouble("total"));
        r.setIsGift(rs.getBoolean("is_gift"));
        r.setRemark(rs.getString("remark"));
        r.setMaterialNo(rs.getString("material_no"));
        r.setMaterialName(rs.getString("material_name"));
        r.setSpecificationsModel(rs.getString("specifications_model"));
        r.setBrand(rs.getString("brand"));
        r.setBarcode(rs.getString("barcode"));
        r.setUnitName(rs.getString("unit_name"));
        return r;
    }

    public PagingResponse<GetSellShipmentsListResponse> getList(CommonBillsRequest request) {// 获取订单列表
        PagingResponse<GetSellShipmentsListResponse> response = new PagingResponse<GetSellShipmentsListResponse>();
        List<Object> queryList = new ArrayList<Object>();
        StringBuilder queryListSql = new StringBuilder();
        StringBuilder queryTotalSql = new StringBuilder();
        loadSellShipmentsSql(request, queryList, queryListSql, queryTotalSql);
        Integer total = 0;
        try {
            total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotalSql.toString(), queryList.toArray());
        } catch (Exception e) {
            return response;
        }
        queryListSql.append("ORDER BY o.bills_date desc,CAST(SUBSTR(o.shipments_number,3)AS SIGNED) desc");
        if (null != request.getPageNo() && null != request.getPageSize()) {
            queryListSql.append(" limit ?,? ");
            queryList.add((request.getPageNo() - 1) * request.getPageSize());
            queryList.add(request.getPageSize());
        }
        List<GetSellShipmentsListResponse> list = jdbcAccessContext.findWithoutSqlManager(queryListSql.toString(), new RowMapper<GetSellShipmentsListResponse>() {
            @Override
            public GetSellShipmentsListResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildSellShipmentsList(paramResultSet);
            }
        }, queryList.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    private GetSellShipmentsListResponse buildSellShipmentsList(ResultSet rs) throws SQLException {
        GetSellShipmentsListResponse response = new GetSellShipmentsListResponse();
        response.setShipmentsNumber(rs.getString("shipments_number"));
        response.setIsDraft(rs.getBoolean("is_draft"));
        response.setBillsDate(DateUtils.formatDate(rs.getDate("bills_date"), "yyyy-MM-dd"));
        response.setPartnerName(rs.getString("partner_name"));
        response.setSummary(rs.getString("summary"));
        response.setCounts(rs.getDouble("counts"));
        response.setTotalPrice(rs.getDouble("total_price"));
        response.setStorageName(rs.getString("storage_name"));
        response.setNickname(rs.getString("nickname"));
        response.setPartenerId(rs.getInt("partner_id"));
        response.setPartnerCode(rs.getString("partner_code"));
        response.setContactsFirstName(rs.getString("contacts_first_name"));
        response.setContactsLastName(rs.getString("contacts_last_name"));
        response.setContactsMobilephone(rs.getString("contacts_mobilephone"));
        response.setStorageId(rs.getInt("storage_id"));
        response.setOwner(rs.getString("owner"));
        response.setOwnerName(rs.getString("ownerName"));
        response.setFromBillsNo(rs.getString("from_bills_no"));
        return response;
    }

    private void loadSellShipmentsSql(CommonBillsRequest request, List<Object> queryList, StringBuilder queryListSql, StringBuilder queryTotalSql) {
        queryListSql
            .append("SELECT o.is_draft,o.owner,u.nickname ownerName,o.bills_date,o.shipments_number,o.from_bills_no,p.partner_name,o.summary,SUM(i.counts) counts,o.total_price,s.storage_name,u.nickname,p.id AS partner_id,p.partner_code,p.contacts_first_name,p.contacts_last_name,"
                + " p.contacts_mobilephone,s.id AS storage_id from sell_shipments_item i RIGHT JOIN sell_shipments o ON i.shipments_number=o.shipments_number LEFT JOIN business_partner p ON o.partner_id=p.id  LEFT JOIN storage s ON o.outbound_storage_id=s.id "
                + " LEFT JOIN usr u ON o.owner=u.id LEFT JOIN material m ON i.material_id=m.id ");
        queryTotalSql
            .append(" SELECT count(*) from (select i.shipments_number from sell_shipments_item i RIGHT JOIN sell_shipments o ON i.shipments_number=o.shipments_number LEFT JOIN business_partner p ON o.partner_id=p.id  LEFT JOIN storage s ON o.outbound_storage_id=s.id LEFT JOIN usr u ON "
                + " o.owner=u.id LEFT JOIN material m ON i.material_id=m.id ");
        if (null != request.getIsDraft()) {
            queryListSql.append(" WHERE o.is_draft=? ");
            queryTotalSql.append(" WHERE o.is_draft=? ");
            queryList.add(request.getIsDraft());
        } else {
            queryListSql.append(" WHERE 1=1 ");
            queryTotalSql.append(" WHERE 1=1 ");
        }
        if (StringUtils.hasText(request.getStartDate())) {
            queryListSql.append(" AND o.bills_date >= '").append(parseDate(request.getStartDate(), Boolean.FALSE)).append("'");
            queryTotalSql.append(" AND o.bills_date >= '").append(parseDate(request.getStartDate(), Boolean.FALSE)).append("'");
        }
        if (StringUtils.hasText(request.getEndDate())) {
            queryListSql.append("  AND o.bills_date <= '").append(parseDate(request.getEndDate(), Boolean.TRUE)).append("'");
            queryTotalSql.append("  AND o.bills_date <= '").append(parseDate(request.getEndDate(), Boolean.TRUE)).append("'");
        }
        if (StringUtils.hasText(request.getKeywords())) {
            queryListSql.append(" AND ( ").append(" p.partner_code like CONCAT('%',?,'%') ").append(" OR p.partner_name like CONCAT('%',?,'%') ");
            queryTotalSql.append(" AND ( ").append(" p.partner_code like CONCAT('%',?,'%') ").append(" OR p.partner_name like CONCAT('%',?,'%') ");
            // queryListSql.append(" OR s.storage_no like CONCAT('%',?,'%') ").append(" OR s.storage_name like CONCAT('%',?,'%') ");
            // queryTotalSql.append(" OR s.storage_no like CONCAT('%',?,'%') ").append(" OR s.storage_name like CONCAT('%',?,'%') ");
            queryListSql.append(" OR m.material_no like CONCAT('%',?,'%') ").append(" OR m.material_name like CONCAT('%',?,'%') ");
            queryTotalSql.append(" OR m.material_no like CONCAT('%',?,'%') ").append(" OR m.material_name like CONCAT('%',?,'%') ");
            queryListSql.append(" OR m.barcode like CONCAT('%',?,'%') ").append(" OR u.user_name like CONCAT('%',?,'%') ");
            queryTotalSql.append(" OR m.barcode like CONCAT('%',?,'%') ").append(" OR u.user_name like CONCAT('%',?,'%') ");
            queryListSql.append(" OR o.shipments_number like CONCAT('%',?,'%') ");
            queryTotalSql.append(" OR o.shipments_number like CONCAT('%',?,'%') ");
            for (int i = 0; i < 7; i++) {
                queryList.add(request.getKeywords());
            }
            queryListSql.append(") ");
            queryTotalSql.append(") ");
        }
        queryListSql.append(" GROUP BY i.shipments_number ");
        queryTotalSql.append("GROUP BY i.shipments_number ) as temp");
    }

    public Boolean delete(DeleteSellShipmentsRequest request) {
        if (null != request && !CollectionUtils.isEmpty(request.getShipmentsNumberList())) {
            String shipmentsNumbers = request.getShipmentsNumberList().toString().replace("[", "").replace("]", "");
            int deleteShipments = jdbcAccessContext.execute("SELL.SQL_DELETE_SELL_SHIPMENTS", new Object[] { shipmentsNumbers.trim() });
            if (-1 != deleteShipments) {
                int deleteShipmentsItem = jdbcAccessContext.execute("SELL.SQL_DELETE_SELL_SHIPMENTS_ITEMS", new Object[] { shipmentsNumbers.trim() });
                if (-1 != deleteShipmentsItem) {
                    addOperationLog("销售发货单", "删除销售发货单，单据编号：[{0}]", shipmentsNumbers, request.getUserId());
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    public Boolean adds(BatchAddSellShipmentsRequest request) {
        List<Object[]> updateStatusParams = new LinkedList<>();
        for (String orderNumber : request.getOrderNumbers()) {
            ReadyShipmentsResponse response = readyShipmentsRestService.get(orderNumber);
            AddSellShipmentsRequest sellShipments = new AddSellShipmentsRequest();
            sellShipments.setPartnerId(Integer.toString(0)); // 电商零售客户
            sellShipments.setFromBillsNo(response.getFromBillsNo());
            sellShipments.setOutboundStorage(response.getStorageId().toString());
            sellShipments.setSummary(response.getSummary());
            sellShipments.setIsDraft(false);
            sellShipments.setTotalPrice(response.getTotalPrice());
            sellShipments.setGiftPrice(response.getGiftPrice());
            sellShipments.setFavorablePrice(response.getFavorablePrice());
            sellShipments.setGatheringPrice(response.getGatheringPrice());
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            sellShipments.setBillsDate(dateFormat.format(now));
            sellShipments.setCreateBy(request.getLoginId().toString());
            sellShipments.setOwner(request.getLoginId().toString());
            List<AddSellShipmentsItemRequest> list = new LinkedList<>();
            for (ReadyShipmentsItemResponse item : response.getItems()) {
                AddSellShipmentsItemRequest i = new AddSellShipmentsItemRequest();
                i.setMaterialId(item.getMaterialId().toString());
                i.setBatchNumber(item.getBatchNumber());
                i.setProductionDate(item.getProductionDate());
                i.setCounts(item.getCounts());
                i.setPurchasePrice(item.getPurchasePrice());
                i.setSellPrice(item.getSellPrice());
                i.setDiscount(item.getDiscount());
                i.setAfterDiscount(item.getAfterDiscount());
                i.setTaxRate(item.getTaxRate());
                i.setTax(item.getTax());
                i.setTotal(item.getTotal());
                i.setIsGift(item.getIsGift());
                i.setRemark(item.getRemark());
                i.setStorageLocationId(item.getStorageLocationId());
                list.add(i);
            }
            sellShipments.setShipmentsItemList(list);
            if (add(sellShipments) != true) {
                return Boolean.FALSE;
            }
            updateStatusParams.add(new Object[] { 6, orderNumber });
            // reduceInventoryCount(sellShipments); // 确认销售发货，减少库存量
        }
        int[] updates = jdbcAccessContext.batchExecute("READYSHIPMENTS.SQL_UPDATE_READYSHIPMENTS_STATUS_FOR_AUDIT_BY_ORDERNUMBER", updateStatusParams);
        if (Arrays.asList(updates).contains(-1)) {
            throw new BusinessProcessException("修改状态失败");
        }
        return Boolean.TRUE;
    }
}
