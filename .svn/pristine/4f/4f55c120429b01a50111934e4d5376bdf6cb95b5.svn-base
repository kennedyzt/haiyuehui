package com.siping.service.inventory.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.service.inventory.service.InventoryRestService;
import com.siping.service.material.service.MaterialRestService;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.inventory.request.AddInventoryShipmentsItemRequest;
import com.siping.smartone.inventory.request.AddInventoryShipmentsRequest;
import com.siping.smartone.inventory.request.DeleteInventoryShipmentsRequest;
import com.siping.smartone.inventory.response.GetInventoryShipmentsItemResponse;
import com.siping.smartone.inventory.response.GetInventoryShipmentsListResponse;
import com.siping.smartone.inventory.response.GetInventoryShipmentsResponse;
import com.siping.smartone.invoicing.inventory.inventory.request.AddInventoryRequest;
import com.siping.smartone.material.response.GetMaterialResponse;

@Repository
public class InventoryShipmentsRestRepository extends BillsRepository { // 需要生成单据编号的Repository才需要继承BillsRepository类
    @Autowired
    private InventoryRestService inventoryRestService;
    @Autowired
    private MaterialRestService materialRestService;

    public synchronized Boolean add(AddInventoryShipmentsRequest request) {
        if (request.getIsDraft() == false) {
            // checkPostPeriodDateTime();// 检查是否在过账期间？存为草稿是否需要验证过账期间
        }

        String shipmentsNnumber = generateBillsNumber("inventory_shipments", "shipments_number");
        Object[] params = new Object[] { shipmentsNnumber, request.getOutboundStorage(), request.getSummary(), request.getIsDraft(), request.getTotalPrice(), request.getGiftPrice(),
                request.getBillsDate(), request.getCreateBy(), request.getAuditor(), null, request.getOwner() };
        int addSellOrder = jdbcAccessContext.execute("INVENTORY.SQL_ADD_INVENTORY_SHIPMENTS", params);
        if (-1 != addSellOrder) {
            List<AddInventoryRequest> inventoryRequestList = new ArrayList<AddInventoryRequest>();
            request.setShipmentsNnumber(shipmentsNnumber);
            int[] batchAddItems = addShipmentsItems(request, shipmentsNnumber, inventoryRequestList);
            if (Arrays.asList(batchAddItems).contains(-1)) {
                throw new BusinessProcessException("新增库存发货单项失败!");
            }
            for (AddInventoryRequest i : inventoryRequestList) { //
                // 调用公用service改变库存量
                inventoryRestService.add(i);
            }
            addOperationLog("库存发货单", "新增库存发货单，单据编号：[{0}],请求的ip为：[" + request.getIp() + "]，mac为：[" + request.getMac() + "]", shipmentsNnumber, request.getCreateBy()  + "");
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private int[] addShipmentsItems(AddInventoryShipmentsRequest request, String shipmentsNumber, List<AddInventoryRequest> inventoryList) { // 新增库存发货项
        List<Object[]> paramList = new ArrayList<Object[]>();
        for (AddInventoryShipmentsItemRequest item : request.getShipmentsItemList()) {
            List<Object> arr = new ArrayList<Object>();
            AddInventoryRequest inventory = new AddInventoryRequest();
            arr.add(shipmentsNumber);
            arr.add(item.getMaterialId());
            inventory.setMaterialId(item.getMaterialId());
            arr.add(item.getBatchNumber());
            inventory.setBatchNumber(item.getBatchNumber());
            arr.add(!StringUtils.hasText(item.getProductionDate()) ? null : item.getProductionDate());
            inventory.setProductionDate(!StringUtils.hasText(item.getProductionDate()) ? null : item.getProductionDate());
            arr.add(item.getCounts());
            inventory.setCounts(item.getCounts());
            arr.add(item.getPurchasePrice());
            inventory.setPurchasePrice(item.getPurchasePrice());
            arr.add(item.getSellPrice());
            inventory.setSellPrice(item.getSellPrice());
            arr.add(item.getTotal());
            inventory.setTotal(item.getTotal());
            arr.add(item.getIsGift());
            arr.add(item.getRemark());
            arr.add(new Date());
            inventory.setStorageId(0); // 耗材只能从虚拟仓里出库
            inventory.setStorageLocationId(null);
            inventory.setCreateBy(request.getCreateBy());
            inventory.setBillsNo(request.getShipmentsNnumber());
            inventory.setBillsDate(request.getBillsDate());
            inventory.setIsInbound(Boolean.FALSE);
            GetMaterialResponse materialResponse = materialRestService.get(item.getMaterialId().toString());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                inventory.setExpirationDate(df.format(new Date(df.parse(item.getProductionDate()).getTime() + materialResponse.getExpirationDate() * 24 * 60 * 60 * 1000)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            arr.add(request.getCreateBy());
            paramList.add(arr.toArray());
            inventoryList.add(inventory);
        }
        int[] batchExecute = jdbcAccessContext.batchExecute("INVENTORY.SQL_ADD_INVENTORY_SHIPMENTS_ITEMS", paramList);
        return batchExecute;
    }

    public Boolean edit(AddInventoryShipmentsRequest request) {
        GetInventoryShipmentsResponse order = getInventoryShipments(request.getShipmentsNnumber());
        if (null == order)
            throw new BusinessProcessException("库存发货单不存在!");
        if (!order.getIsDraft())
            throw new BusinessProcessException("不能编辑正式的库存发货单项!");
        Object[] params = new Object[] { request.getOutboundStorage(), request.getSummary(), request.getIsDraft(), request.getTotalPrice(), request.getGiftPrice(), request.getBillsDate(),
                request.getAuditor(), null, request.getOwner(), request.getShipmentsNnumber() };
        int editOrder = jdbcAccessContext.execute("INVENTORY.SQL_UPDATE_INVENTORY_SHIPMENTS", params); // 编辑订单项
        if (-1 != editOrder) {// 删除以前的订单项
            int deleteOrderItem = jdbcAccessContext.execute("INVENTORY.SQL_DELETE_INVENTORY_SHIPMENTS_ITEM", new Object[] { request.getShipmentsNnumber() });
            if (-1 != deleteOrderItem) {// 新增订单项
                List<AddInventoryRequest> inventoryRequest = new ArrayList<AddInventoryRequest>();
                int[] batchAddItems = addShipmentsItems(request, request.getShipmentsNnumber(), inventoryRequest);
                while (Arrays.asList(batchAddItems).contains(-1)) {
                    throw new BusinessProcessException("编辑库存发货单项失败!");
                }
                addOperationLog("库存发货单", "更新库存发货单，单据编号：[{0}]", request.getShipmentsNnumber(), request.getCreateBy() + "");
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public GetInventoryShipmentsResponse get(String shipmentsNumber) {// 获取单个订单
        GetInventoryShipmentsResponse order = getInventoryShipments(shipmentsNumber);
        if (null == order)
            throw new BusinessProcessException("库存发货单项不存在!");
        List<GetInventoryShipmentsItemResponse> orderItems = jdbcAccessContext.find("INVENTORY.SQL_GET_INVENTORY_SHIPMENTS_ITEM", new RowMapper<GetInventoryShipmentsItemResponse>() {
            @Override
            public GetInventoryShipmentsItemResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildShipmentsItemResponse(rs);
            }

        }, new Object[] { shipmentsNumber });
        if (CollectionUtils.isEmpty(orderItems)) {
        }
        // throw new BusinessProcessException("库存发货单项没有发货项!");
        order.setShipmentsItemList(orderItems);
        return order;
    }

    private GetInventoryShipmentsResponse getInventoryShipments(String shipmentsNumber) {
        return jdbcAccessContext.findUniqueResult("INVENTORY.SQL_GET_INVENTORY_SHIPMENTS", new RowMapper<GetInventoryShipmentsResponse>() {
            @Override
            public GetInventoryShipmentsResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildShipmentsResponse(paramResultSet);
            }
        }, new Object[] { shipmentsNumber });
    }

    private GetInventoryShipmentsResponse buildShipmentsResponse(ResultSet rs) throws SQLException {
        GetInventoryShipmentsResponse response = new GetInventoryShipmentsResponse();
        response.setShipmentsNumber(rs.getString("shipments_number"));
        response.setOutboundStorage(rs.getString("outbound_storage"));
        response.setOutboundStorageName(rs.getString("storage_name"));
        response.setSummary(rs.getString("summary"));
        response.setIsDraft(rs.getBoolean("is_draft"));
        response.setTotalPrice(rs.getDouble("total_price"));
        response.setGiftPrice(rs.getDouble("gift_price"));
        response.setBillsDate(rs.getString("bills_date"));
        try {
            response.setCreateDate(rs.getString("create_date"));
        } catch (Exception e) {
        }
        response.setCreateBy(rs.getString("create_by"));
        response.setAuditor(rs.getInt("auditor") + "");
        response.setAuditDate(rs.getString("audit_date"));
        response.setOwner(rs.getString("owner"));
        response.setOwnerName(rs.getString("ownerName"));
        return response;
    }

    private GetInventoryShipmentsItemResponse buildShipmentsItemResponse(ResultSet rs) throws SQLException {
        GetInventoryShipmentsItemResponse r = new GetInventoryShipmentsItemResponse();
        r.setId(rs.getString("id"));
        r.setShipmentsNumber(rs.getString("shipments_number"));
        r.setMaterialId(rs.getString("material_id"));
        r.setBatchNumber(rs.getString("batch_number"));
        String productionDate = rs.getString("production_date");
        if (StringUtils.hasText(productionDate)) {
            r.setProductionDate(productionDate.substring(0, 10));
        }
        r.setCounts(rs.getDouble("counts"));
        r.setPurchasePrice(rs.getDouble("purchase_price"));
        r.setSellPrice(rs.getDouble("sell_price"));
        r.setTotal(rs.getDouble("total"));
        r.setIsGift(rs.getBoolean("is_gift"));
        r.setRemark(rs.getString("remark"));
        r.setMaterialNo(rs.getString("material_no"));
        r.setMaterialName(rs.getString("materialName"));
        r.setMaterialDescription(rs.getString("description"));
        r.setSpecificationsModel(rs.getString("specifications_model"));
        r.setBrand(rs.getString("brand"));
        r.setSeason(rs.getString("season"));
        r.setBarcode(rs.getString("barcode"));
        r.setUnitName(rs.getString("unit_name"));
        return r;
    }

    public PagingResponse<GetInventoryShipmentsListResponse> getList(CommonBillsRequest request) {// 获取订单列表
        PagingResponse<GetInventoryShipmentsListResponse> response = new PagingResponse<GetInventoryShipmentsListResponse>();
        List<Object> queryList = new ArrayList<Object>();
        StringBuilder queryListSql = new StringBuilder();
        StringBuilder queryTotalSql = new StringBuilder();
        loadInventoryShipmentsSql(request, queryList, queryListSql, queryTotalSql);
        Integer total = 0;
        try {
            total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotalSql.toString(), queryList.toArray());
        } catch (Exception e) {
            return response;
        }
        queryListSql.append("ORDER BY o.bills_date desc,CAST(SUBSTR(o.shipments_number,3)AS SIGNED) desc ");
        if (null != request.getPageNo() && null != request.getPageSize()) {
            queryListSql.append(" limit ?,? ");
            queryList.add((request.getPageNo() - 1) * request.getPageSize());
            queryList.add(request.getPageSize());
        }
        List<GetInventoryShipmentsListResponse> list = jdbcAccessContext.findWithoutSqlManager(queryListSql.toString(), new RowMapper<GetInventoryShipmentsListResponse>() {
            @Override
            public GetInventoryShipmentsListResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildInventoryShipmentsList(paramResultSet);
            }
        }, queryList.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    private GetInventoryShipmentsListResponse buildInventoryShipmentsList(ResultSet rs) throws SQLException {
        GetInventoryShipmentsListResponse response = new GetInventoryShipmentsListResponse();
        response.setShipmentsNumber(rs.getString("shipments_number"));
        response.setIsDraft(rs.getBoolean("is_draft"));
        // response.setSellFlowId(rs.getString("sell_flow_id"));
        response.setBillsDate(rs.getString("bills_date"));
        response.setSummary(rs.getString("summary"));
        response.setCounts(rs.getDouble("counts"));
        response.setTotalPrice(rs.getDouble("total_price"));
        response.setStorageName(rs.getString("storage_name"));
        response.setNickname(rs.getString("nickname"));
        return response;
    }

    private void loadInventoryShipmentsSql(CommonBillsRequest request, List<Object> queryList, StringBuilder queryListSql, StringBuilder queryTotalSql) {
        queryListSql
            .append("SELECT o.is_draft,o.bills_date,o.shipments_number,o.summary,SUM(i.counts) counts,o.total_price,s.storage_name,u.nickname from inventory_shipments_item i RIGHT JOIN inventory_shipments o ON i.shipments_number=o.shipments_number "
                + " LEFT JOIN storage s ON o.outbound_storage_id=s.id LEFT JOIN usr u ON o.owner=u.id LEFT JOIN material m ON i.material_id=m.id ");
        queryTotalSql
            .append("SELECT count(*) from(select i.shipments_number from inventory_shipments_item i RIGHT JOIN inventory_shipments o ON i.shipments_number=o.shipments_number LEFT JOIN storage s ON o.outbound_storage_id=s.id LEFT JOIN usr u ON o.owner=u.id LEFT JOIN material m ON i.material_id=m.id ");
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
            queryListSql.append(" AND ( ");
            queryTotalSql.append(" AND ( ");
            // queryListSql.append(" s.storage_no like CONCAT('%',?,'%') ").append(" OR s.storage_name like CONCAT('%',?,'%') ");//仓库不再用作查询条件
            // queryTotalSql.append(" s.storage_no like CONCAT('%',?,'%') ").append(" OR s.storage_name like CONCAT('%',?,'%') ");
            queryListSql.append("  m.material_no like CONCAT('%',?,'%') ").append(" OR m.material_name like CONCAT('%',?,'%') ");
            queryTotalSql.append("  m.material_no like CONCAT('%',?,'%') ").append(" OR m.material_name like CONCAT('%',?,'%') ");
            queryListSql.append(" OR m.barcode like CONCAT('%',?,'%') ").append(" OR u.user_name like CONCAT('%',?,'%') ");
            queryTotalSql.append(" OR m.barcode like CONCAT('%',?,'%') ").append(" OR u.user_name like CONCAT('%',?,'%') ");
            queryListSql.append(" OR o.shipments_number like CONCAT('%',?,'%') ").append(" OR o.summary like CONCAT('%',?,'%') ");
            queryTotalSql.append(" OR o.shipments_number like CONCAT('%',?,'%') ").append(" OR o.summary like CONCAT('%',?,'%') ");
            for (int i = 0; i < 6; i++) {
                queryList.add(request.getKeywords());
            }
            queryListSql.append(") ");
            queryTotalSql.append(") ");
        }
        queryListSql.append(" GROUP BY i.shipments_number ");
        queryTotalSql.append(" GROUP BY i.shipments_number ) as temp");
    }

    public Boolean delete(DeleteInventoryShipmentsRequest request) { // 删除只能删除草稿
        if (null != request && !CollectionUtils.isEmpty(request.getShipmentsNumberList())) {
            String orderNumbers = request.getShipmentsNumberList().toString().replace("[", "").replace("]", "");
            int deleteOrder = jdbcAccessContext.execute("INVENTORY.SQL_DELETE_INVENTORY_SHIPMENTS", new Object[] { orderNumbers });
            if (-1 != deleteOrder) {
                int deleteOrderItem = jdbcAccessContext.execute("INVENTORY.SQL_DELETE_INVENTORY_SHIPMENTS_ITEM", new Object[] { orderNumbers });
                if (-1 != deleteOrderItem) {
                    addOperationLog("库存发货单", "删除库存发货单，单据编号：[{0}]", orderNumbers, request.getDeleteUserId() + "");
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }
}
