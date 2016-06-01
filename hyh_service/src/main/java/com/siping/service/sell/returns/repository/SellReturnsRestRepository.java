package com.siping.service.sell.returns.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.service.inventory.service.InventoryRestService;
import com.siping.service.material.repository.MaterialRestRepository;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.invoicing.inventory.inventory.request.AddInventoryRequest;
import com.siping.smartone.invoicing.sell.returns.request.AddSellReturnsItemRequest;
import com.siping.smartone.invoicing.sell.returns.request.AddSellReturnsRequest;
import com.siping.smartone.invoicing.sell.returns.request.DeleteSellReturnsRequest;
import com.siping.smartone.invoicing.sell.returns.response.GetSellReturnsItemResponse;
import com.siping.smartone.invoicing.sell.returns.response.GetSellReturnsListResponse;
import com.siping.smartone.invoicing.sell.returns.response.GetSellReturnsResponse;

@Repository
public class SellReturnsRestRepository extends BillsRepository {
    @Autowired
    private InventoryRestService inventoryRestService;
    @Autowired
    private MaterialRestRepository materialRestRepository;

    public Boolean add(AddSellReturnsRequest request) {
        // checkPostPeriodDateTime();
        String returnsNumber = generateBillsNumber("sell_returns", "returns_number");
        Object[] params = new Object[] { returnsNumber, request.getPartnerId(), request.getInboundStorage(), request.getSummary(), request.getIsDraft(), request.getTotalPrice(),
                request.getGiftPrice(), request.getFavorablePrice(), request.getPayPrice(), request.getBillsDate(), new Date(), request.getCreateBy(), request.getAuditor(), null, request.getOwner() };
        int addSellOrder = jdbcAccessContext.execute("SELL.SQL_ADD_SELL_RETURNS", params);
        if (-1 != addSellOrder) {
            request.setReturnsNumber(returnsNumber);
            List<AddInventoryRequest> inventoryRequestList = new ArrayList<AddInventoryRequest>();
            int[] batchAddItems = addReturnsItems(request, returnsNumber, inventoryRequestList);
            if (Arrays.asList(batchAddItems).contains(-1)) {
                throw new BusinessProcessException("新增销售退货单订单项失败!");
            }

            // if (!request.getIsDraft()) {
            // changeInventoryAvailableAmount(inventoryRequestList);
            // }
            addOperationLog("销售退货单", "新增销售退货单，单据编号：[{0}]", returnsNumber, request.getCreateBy());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private void changeInventoryAvailableAmount(List<AddInventoryRequest> inventoryRequestList) {
        for (AddInventoryRequest inventory : inventoryRequestList) { // 销售退货，
            try {
                inventoryRestService.add(inventory);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessProcessException("添加库存日志时失败，请从新执行该操作");
            }

        }
    }

    private int[] addReturnsItems(AddSellReturnsRequest request, String returnsNumber, List<AddInventoryRequest> inventoryRequestList) {
        List<Object[]> paramList = new ArrayList<Object[]>();
        for (AddSellReturnsItemRequest item : request.getReturnsItemList()) {
            List<Object> arr = new ArrayList<Object>();
            arr.add(returnsNumber);
            arr.add(item.getMaterialId());
            arr.add(item.getBatchNumber());
            if (!StringUtils.hasText(item.getProductionDate()) && request.getIsDraft() == false) {
                throw new BusinessProcessException("你的商品名为‘" + materialRestRepository.get(item.getMaterialId().toString(), null, null).getMaterialName() + "’未输入商品生产日期，请输入商品的生产日期");
            }
            if (!StringUtils.hasText(item.getProductionDate())) {
                item.setProductionDate("0000-0-0");
            }
            arr.add(item.getProductionDate());
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
            inventoryRequestList.add(buildAddInventoryRequest(request, item));
        }
        int[] batchExecute = jdbcAccessContext.batchExecute("SELL.SQL_ADD_SELL_RETURNS_ITEMS", paramList);
        return batchExecute;
    }

    private AddInventoryRequest buildAddInventoryRequest(AddSellReturnsRequest request, AddSellReturnsItemRequest item) {
        AddInventoryRequest i = new AddInventoryRequest();
        i.setMaterialId(item.getMaterialId());
        i.setStorageId(StringUtils.hasText(request.getInboundStorage()) ? Integer.valueOf(request.getInboundStorage()) : null);
        i.setBatchNumber(item.getBatchNumber());
        i.setProductionDate(!StringUtils.hasText(item.getProductionDate()) ? null : item.getProductionDate());
        i.setCounts(item.getCounts());
        i.setTotal(item.getTotal());
        i.setPurchasePrice(item.getPurchasePrice());
        // i.setSellPrice(item.getAfterDiscount());
        i.setCreateBy(StringUtils.hasText(request.getCreateBy()) ? Integer.valueOf(request.getCreateBy()) : null);
        // i.setFromBillsId(request.getFromBillsNo());
        i.setIsInbound(Boolean.TRUE);
        i.setBillsNo(request.getReturnsNumber());
        i.setBillsDate(request.getBillsDate());
        i.setCreateBy(StringUtils.hasText(request.getCreateBy()) ? Integer.valueOf(request.getCreateBy()) : null);
        return i;
    }

    public Boolean edit(AddSellReturnsRequest request) {
        GetSellReturnsResponse returns = getSellReturns(request.getReturnsNumber());
        if (null == returns)
            throw new BusinessProcessException("销售退货单不存在!");
        if (!returns.getIsDraft())
            throw new BusinessProcessException("不能编辑正式的销售退货单!");
        Object[] params = new Object[] { request.getPartnerId(), request.getInboundStorage(), request.getSummary(), request.getIsDraft(), request.getTotalPrice(), request.getGiftPrice(),
                request.getFavorablePrice(), request.getPayPrice(), request.getBillsDate(), request.getAuditor(), null, request.getOwner(), request.getReturnsNumber() };
        int editOrder = jdbcAccessContext.execute("SELL.SQL_UPDATE_SELL_RETURNS", params); // 编辑退货单项
        if (-1 != editOrder) {// 删除以前的退货单项
            int deleteOrderItem = jdbcAccessContext.execute("SELL.SQL_DELETE_SELL_RETURNS_ITEM", new Object[] { request.getReturnsNumber() });
            if (-1 != deleteOrderItem) {// 新增退货单项
                List<AddInventoryRequest> inventoryRequestList = new ArrayList<AddInventoryRequest>();
                int[] batchAddItems = addReturnsItems(request, request.getReturnsNumber(), inventoryRequestList);
                while (Arrays.asList(batchAddItems).contains(-1)) {
                    throw new BusinessProcessException("编辑退货单项失败!");
                }
                // changeInventoryAvailableAmount(inventoryRequestList);
                addOperationLog("销售退货单", "编辑销售退货单，单据编号：[{0}]", request.getReturnsNumber(), request.getCreateBy());
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public GetSellReturnsResponse get(String returnsNumber) {
        GetSellReturnsResponse returns = getSellReturns(returnsNumber);
        if (null == returns)
            throw new BusinessProcessException("销售退货单不存在!");
        List<GetSellReturnsItemResponse> returnsItems = jdbcAccessContext.find("SELL.SQL_GET_SELL_RETURNS_ITEM", new RowMapper<GetSellReturnsItemResponse>() {
            @Override
            public GetSellReturnsItemResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildGetSellReturnsItemResponse(rs);
            }

        }, new Object[] { returnsNumber });
        if (CollectionUtils.isEmpty(returnsItems)){}
            //throw new BusinessProcessException("销售订单没有订单项!");
        returns.setReturnsItemList(returnsItems);
        return returns;
    }

    private GetSellReturnsResponse getSellReturns(String returnsNumber) {
        return jdbcAccessContext.findUniqueResult("SELL.SQL_GET_SELL_RETURNS", new RowMapper<GetSellReturnsResponse>() {
            @Override
            public GetSellReturnsResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildGetSellReturnsResponse(paramResultSet);
            }
        }, new Object[] { returnsNumber });
    }

    private GetSellReturnsResponse buildGetSellReturnsResponse(ResultSet rs) throws SQLException {
        GetSellReturnsResponse response = new GetSellReturnsResponse();
        response.setReturnsNumber(rs.getString("returns_number"));
        response.setPartnerId(rs.getInt("partner_id") + "");
        response.setPartnerCode(rs.getString("partner_code"));
        response.setPartnerName(rs.getString("partner_name"));
        response.setPartnerContactsFirstName(rs.getString("contacts_first_name"));
        response.setPartnerContactsLastName(rs.getString("contacts_last_name"));
        response.setPartnerContactsMobilephone(rs.getString("contacts_mobilephone"));
        response.setInboundStorage(rs.getString("inbound_storage_id"));
        response.setInboundStorageName(rs.getString("storage_name"));
        response.setSummary(rs.getString("summary"));
        response.setIsDraft(rs.getBoolean("is_draft"));
        response.setTotalPrice(rs.getDouble("total_price"));
        response.setGiftPrice(rs.getDouble("gift_price"));
        response.setFavorablePrice(rs.getDouble("favorable_price"));
        response.setPayPrice(rs.getDouble("pay_price"));
        response.setBillsDate(rs.getString("bills_date"));
        response.setCreateDate(rs.getString("create_date"));
        response.setCreateBy(rs.getString("create_by"));
        response.setAuditor(rs.getInt("auditor") + "");
        response.setAuditDate(rs.getString("audit_date"));
        response.setOwner(rs.getString("owner"));
        return response;
    }

    private GetSellReturnsItemResponse buildGetSellReturnsItemResponse(ResultSet rs) throws SQLException {
        GetSellReturnsItemResponse r = new GetSellReturnsItemResponse();
        r.setId(rs.getString("id"));
        r.setReturnsNumber(rs.getString("returns_number"));
        r.setMaterialName(rs.getString("material_name"));
        r.setMaterialNo(rs.getString("material_no"));
        r.setSpecificationsModel(rs.getString("specifications_model"));
        r.setBrand(rs.getString("brand"));
        r.setSeason(rs.getString("season"));
        r.setBarcode(rs.getString("barcode"));
        r.setUnitName(rs.getString("unit_name"));
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
        return r;
    }

    public PagingResponse<GetSellReturnsListResponse> getList(CommonBillsRequest request) {// 获取订单列表
        PagingResponse<GetSellReturnsListResponse> response = new PagingResponse<GetSellReturnsListResponse>();
        List<Object> queryList = new ArrayList<Object>();
        StringBuilder queryListSql = new StringBuilder();
        StringBuilder queryTotalSql = new StringBuilder();
        loadSellReturnsSql(request, queryList, queryListSql, queryTotalSql);
        Integer total = 0;
        try {
            total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotalSql.toString(), queryList.toArray());
        } catch (Exception e) {
            return response;
        }
        queryListSql.append("ORDER BY o.bills_date desc,CAST(SUBSTR(o.returns_number,3)AS SIGNED) desc ");
        queryTotalSql.append("ORDER BY o.bills_date desc,CAST(SUBSTR(o.returns_number,3)AS SIGNED) desc ");
        if (null != request.getPageNo() && null != request.getPageSize()) {
            queryListSql.append(" limit ?,? ");
            queryList.add((request.getPageNo() - 1) * request.getPageSize());
            queryList.add(request.getPageSize());
        }
        List<GetSellReturnsListResponse> list = jdbcAccessContext.findWithoutSqlManager(queryListSql.toString(), new RowMapper<GetSellReturnsListResponse>() {
            @Override
            public GetSellReturnsListResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildSellReturnsList(paramResultSet);
            }
        }, queryList.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    private GetSellReturnsListResponse buildSellReturnsList(ResultSet rs) throws SQLException {
        GetSellReturnsListResponse response = new GetSellReturnsListResponse();
        response.setReturnsNumber(rs.getString("returns_number"));
        response.setIsDraft(rs.getBoolean("is_draft"));
        response.setBillsDate(rs.getString("bills_date"));
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
        return response;
    }

    private void loadSellReturnsSql(CommonBillsRequest request, List<Object> queryList, StringBuilder queryListSql, StringBuilder queryTotalSql) {
        queryListSql
            .append("SELECT o.is_draft,o.bills_date,o.returns_number,p.partner_name,o.summary,SUM(i.counts) counts,o.total_price,s.storage_name,u.nickname,p.id AS partner_id,p.partner_code,p.contacts_first_name,"
                // .append("SELECT o.is_draft,o.bills_date,o.returns_number,p.partner_name,o.summary,SUM(i.counts) counts,o.total_price,s.storage_name,u.nickname,p.id AS partner_id,p.partner_code,p.contacts_first_name,"
                + " p.contacts_last_name,p.contacts_mobilephone,s.id AS storage_id from sell_returns o left JOIN sell_returns_item i ON o.returns_number=i.returns_number LEFT JOIN business_partner p ON o.partner_id=p.id  LEFT JOIN "
                + " storage s ON o.inbound_storage_id=s.id LEFT JOIN usr u ON o.owner=u.id LEFT JOIN material m ON i.material_id=m.id  ");
        queryTotalSql
            .append(" SELECT count(*) from (select o.returns_number from sell_returns o left join sell_returns_item i ON o.returns_number=i.returns_number LEFT JOIN business_partner p ON o.partner_id=p.id  LEFT JOIN storage s ON o.inbound_storage_id=s.id LEFT JOIN "
                + " usr u ON o.owner=u.id LEFT JOIN material m ON i.material_id=m.id ");
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
//            queryListSql.append(" OR s.storage_no like CONCAT('%',?,'%') ").append(" OR s.storage_name like CONCAT('%',?,'%') ");
//            queryTotalSql.append(" OR s.storage_no like CONCAT('%',?,'%') ").append(" OR s.storage_name like CONCAT('%',?,'%') ");
            queryListSql.append(" OR m.material_no like CONCAT('%',?,'%') ").append(" OR m.material_name like CONCAT('%',?,'%') ");
            queryTotalSql.append(" OR m.material_no like CONCAT('%',?,'%') ").append(" OR m.material_name like CONCAT('%',?,'%') ");
            queryListSql.append(" OR m.barcode like CONCAT('%',?,'%') ").append(" OR u.user_name like CONCAT('%',?,'%') ");
            queryTotalSql.append(" OR m.barcode like CONCAT('%',?,'%') ").append(" OR u.user_name like CONCAT('%',?,'%') ");
            queryListSql.append(" OR o.returns_number like CONCAT('%',?,'%') ");
            queryTotalSql.append(" OR o.returns_number like CONCAT('%',?,'%') ");
            for (int i = 0; i < 7; i++) {
                queryList.add(request.getKeywords());
            }
            queryListSql.append(") ");
            queryTotalSql.append(") ");
        }
        queryListSql.append(" GROUP BY o.returns_number ");
        queryTotalSql.append(" GROUP BY o.returns_number ) as temp");
    }

    public Boolean delete(DeleteSellReturnsRequest request) {
        if (null != request && !CollectionUtils.isEmpty(request.getReturnsNumberList())) {
            String returnsNumbers = request.getReturnsNumberList().toString().replace("[", "").replace("]", "");
            int deleteOrder = jdbcAccessContext.execute("SELL.SQL_DELETE_SELL_RETURNS", new Object[] { returnsNumbers.trim() });
            if (-1 != deleteOrder) {
                int deleteOrderItem = jdbcAccessContext.execute("SELL.SQL_DELETE_SELL_RETURNS_ITEM", new Object[] { returnsNumbers.trim() });
                if (-1 != deleteOrderItem) {
                    addOperationLog("销售退货单", "删除销售退货单，单据编号：[{0}]", returnsNumbers, request.getUserId());
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }
}
