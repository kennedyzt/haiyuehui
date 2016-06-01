package com.siping.service.sell.order.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.invoicing.sell.order.request.AddSellOrderItemRequest;
import com.siping.smartone.invoicing.sell.order.request.AddSellOrderRequest;
import com.siping.smartone.invoicing.sell.order.request.DeleteSellOrderRequest;
import com.siping.smartone.invoicing.sell.order.response.GetSellOrderItemResponse;
import com.siping.smartone.invoicing.sell.order.response.GetSellOrderListResponse;
import com.siping.smartone.invoicing.sell.order.response.GetSellOrderResponse;

@Repository
public class SellOrderRestRepository extends BillsRepository { // 需要生成单据编号的Repository才需要继承BillsRepository类

    public Boolean add(AddSellOrderRequest request) {
        // checkPostPeriodDateTime(); // 检查是否在过账期间
        String orderNumber = "";
        if (!StringUtils.hasText(request.getOrderNumber())) {
            orderNumber = generateBillsNumber("sell_order", "order_number");
        } else {
            orderNumber = request.getOrderNumber();
            int delete = jdbcAccessContext.execute("SELL.SQL_DELETE_SELLORDER_AND_SELLORDERITEM", new Object[] { request.getOrderNumber() });
            if (-1 == delete) {
                return Boolean.FALSE;
            }
        }
        Object[] params = new Object[] { orderNumber, request.getPartnerId(), request.getOutboundStorage(), request.getSummary(), request.getIsDraft(), request.getTotalPrice(),
                request.getGiftPrice(), request.getFavorablePrice(), request.getGatheringPrice(), request.getBillsDate(), request.getCreateBy(), request.getAuditor(), null, request.getOwner(),
                request.getShipmentsDate(), request.getFromBillsNo(), request.getTrackingNo(), request.getCustomsCode() };
        int addSellOrder = jdbcAccessContext.execute("SELL.SQL_ADD_SELL_ORDER", params);
        if (-1 != addSellOrder) {
            int[] batchAddItems = addOrderItems(request, orderNumber);
            if (Arrays.asList(batchAddItems).contains(-1)) {
                throw new BusinessProcessException("新增订单项失败!");
            }
            addOperationLog("销售订单", "新增销售订单，单据编号：[{0}]", orderNumber, request.getCreateBy() + "");
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private int[] addOrderItems(AddSellOrderRequest request, String orderNumber) {
        List<Object[]> paramList = new ArrayList<Object[]>();
        for (AddSellOrderItemRequest item : request.getOrderItemList()) {
            List<Object> arr = new ArrayList<Object>();
            arr.add(orderNumber);
            arr.add(item.getMaterialId());
            arr.add(item.getBatchNumber());
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
            arr.add(item.getShipmentsAmount());
            arr.add(item.getRemark());
            arr.add(new Date());
            arr.add(request.getCreateBy());
            arr.add(item.getShipmentsDate());
            paramList.add(arr.toArray());
        }
        int[] batchExecute = jdbcAccessContext.batchExecute("SELL.SQL_ADD_SELL_ORDER_ITEMS", paramList);
        return batchExecute;
    }

    public Boolean edit(AddSellOrderRequest request) {
        GetSellOrderResponse order = getSellOrder(request.getOrderNumber());
        if (null == order)
            throw new BusinessProcessException("销售订单不存在!");
        if (!order.getIsDraft())
            throw new BusinessProcessException("不能编辑正式的销售订单!");
        Object[] params = new Object[] { request.getPartnerId(), request.getOutboundStorage(), request.getSummary(), request.getIsDraft(), request.getTotalPrice(), request.getGiftPrice(),
                request.getFavorablePrice(), request.getGatheringPrice(), request.getBillsDate(), request.getAuditor(), request.getOwner(), request.getOrderNumber() };
        int editOrder = jdbcAccessContext.execute("SELL.SQL_UPDATE_SELL_ORDER", params); // 编辑订单项
        if (-1 != editOrder) {// 删除以前的订单项
            int deleteOrderItem = jdbcAccessContext.execute("SELL.SQL_DELETE_SELL_ORDER_ITEM", new Object[] { request.getOrderNumber() });
            if (-1 != deleteOrderItem) {// 新增订单项
                int[] batchAddItems = addOrderItems(request, request.getOrderNumber());
                while (Arrays.asList(batchAddItems).contains(-1)) {
                    throw new BusinessProcessException("编辑订单项失败!");
                }
                addOperationLog("销售订单", "更新销售订单，单据编号：[{0}]", request.getOrderNumber(), request.getCreateBy() + "");
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public GetSellOrderResponse get(String orderNumber) {// 获取单个订单
        GetSellOrderResponse order = getSellOrder(orderNumber);
        if (null == order)
            throw new BusinessProcessException("销售订单不存在!");
        List<GetSellOrderItemResponse> orderItems = jdbcAccessContext.find("SELL.SQL_GET_SELL_ORDER_ITEM", new RowMapper<GetSellOrderItemResponse>() {
            @Override
            public GetSellOrderItemResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildGetSellOrderItemResponse(rs);
            }

        }, new Object[] { orderNumber });
        if (CollectionUtils.isEmpty(orderItems)){}
            //throw new BusinessProcessException("销售订单没有订单项!");
        order.setOrderItemList(orderItems);
        return order;
    }

    public PagingResponse<GetSellOrderListResponse> getList(CommonBillsRequest request) {// 获取订单列表
        PagingResponse<GetSellOrderListResponse> response = new PagingResponse<GetSellOrderListResponse>();
        List<Object> queryList = new ArrayList<Object>();
        StringBuilder queryListSql = new StringBuilder();
        StringBuilder queryTotalSql = new StringBuilder();
        loadDynamicQuerySellOrderSql(request, queryList, queryListSql, queryTotalSql);
        Integer total = 0;
        try {
            total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotalSql.toString(), queryList.toArray());
        } catch (Exception e) {
            e.printStackTrace();
            return response;
        }
        queryListSql.append("ORDER BY o.bills_date desc,CAST(SUBSTR(o.order_number,3)AS SIGNED) desc ");
        if (null != request.getPageNo() && null != request.getPageSize()) {
            queryListSql.append(" limit ?,? ");
            queryList.add((request.getPageNo() - 1) * request.getPageSize());
            queryList.add(request.getPageSize());
        }
        List<GetSellOrderListResponse> list = jdbcAccessContext.findWithoutSqlManager(queryListSql.toString(), new RowMapper<GetSellOrderListResponse>() {
            @Override
            public GetSellOrderListResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildGetSellOrderListResponse(rs);
            }
        }, queryList.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    private GetSellOrderListResponse buildGetSellOrderListResponse(ResultSet rs) throws SQLException {
        GetSellOrderListResponse response = new GetSellOrderListResponse();
        response.setOrderNumber(rs.getString("order_number"));
        response.setIsDraft(rs.getBoolean("is_draft"));
        response.setBillsDate(DateUtils.formatDate(rs.getDate("bills_date"), "yyyy-MM-dd"));
        response.setPartenerId(rs.getInt("partner_id"));
        response.setPartnerCode(rs.getString("partner_code"));
        response.setContactsFirstName(rs.getString("contacts_first_name"));
        response.setContactsLastName(rs.getString("contacts_last_name"));
        response.setContactsMobilephone(rs.getString("contacts_mobilephone"));
        response.setPartnerName(rs.getString("partner_name"));
        response.setSummary(rs.getString("summary"));
        response.setCounts(rs.getDouble("counts"));
        response.setTotalPrice(rs.getDouble("total_price"));
        response.setStorageId(rs.getInt("storage_id"));
        response.setStorageName(rs.getString("storage_name"));
        response.setNickname(rs.getString("nickname"));
        response.setShipmentsDate(DateUtils.formatDate(rs.getDate("shipments_date"), "yyyy-MM-dd"));
        response.setFromBillsNo(rs.getString("from_bills_no"));
        return response;
    }

    private void loadDynamicQuerySellOrderSql(CommonBillsRequest request, List<Object> queryList, StringBuilder queryListSql, StringBuilder queryTotalSql) {
        queryListSql
            .append("SELECT o.is_draft,o.bills_date,o.order_number,o.from_bills_no,o.shipments_date,p.partner_name,o.summary,SUM(i.counts) counts,o.total_price,s.storage_name,u.nickname,p.partner_code,p.contacts_first_name,p.contacts_last_name,"
                + " p.contacts_mobilephone,p.id AS partner_id,s.id AS storage_id from sell_order o LEFT JOIN sell_order_item i  ON i.order_number=o.order_number LEFT JOIN business_partner p ON o.partner_id=p.id  LEFT JOIN storage s ON "
                + " o.outbound_storage_id=s.id LEFT JOIN usr u ON o.owner=u.id LEFT JOIN material m ON i.material_id=m.id ");
        queryTotalSql
            .append(" SELECT count(*) from (select o.order_number from sell_order o LEFT JOIN sell_order_item i  ON i.order_number=o.order_number LEFT JOIN business_partner p ON o.partner_id=p.id  LEFT JOIN storage s ON o.outbound_storage_id=s.id LEFT JOIN "
                + " usr u ON o.owner=u.id LEFT JOIN material m ON i.material_id=m.id ");
        if (null != request.getIsPay() && request.getIsPay()) {
            queryListSql.append(" WHERE i.shipments_amount>0 ");
            queryTotalSql.append(" WHERE i.shipments_amount>0 ");
        } else {
            queryListSql.append(" WHERE i.shipments_date IS NULL ");
            queryTotalSql.append(" WHERE i.shipments_date IS NULL ");
        }
        if (null != request.getIsDraft()) {
            queryListSql.append(" AND o.is_draft=? ");
            queryTotalSql.append(" AND o.is_draft=? ");
            queryList.add(request.getIsDraft());
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
            //queryListSql.append(" OR s.storage_no like CONCAT('%',?,'%') ").append(" OR s.storage_name like CONCAT('%',?,'%') ");
            //queryTotalSql.append(" OR s.storage_no like CONCAT('%',?,'%') ").append(" OR s.storage_name like CONCAT('%',?,'%') ");
            queryListSql.append(" OR m.material_no like CONCAT('%',?,'%') ").append(" OR m.material_name like CONCAT('%',?,'%') ");
            queryTotalSql.append(" OR m.material_no like CONCAT('%',?,'%') ").append(" OR m.material_name like CONCAT('%',?,'%') ");
            queryListSql.append(" OR m.barcode like CONCAT('%',?,'%') ").append(" OR u.user_name like CONCAT('%',?,'%') ");
            queryTotalSql.append(" OR m.barcode like CONCAT('%',?,'%') ").append(" OR u.user_name like CONCAT('%',?,'%') ");
            queryListSql.append(" OR o.order_number like CONCAT('%',?,'%') ");
            queryTotalSql.append(" OR o.order_number like CONCAT('%',?,'%') ");
            for (int i = 0; i < 7; i++) {
                queryList.add(request.getKeywords());
            }
            queryListSql.append(") ");
            queryTotalSql.append(") ");
        }
        queryListSql.append(" GROUP BY o.order_number ");
        queryTotalSql.append(" GROUP BY o.order_number) as temp");
    }

    private GetSellOrderResponse getSellOrder(String orderNumber) {
        return jdbcAccessContext.findUniqueResult("SELL.SQL_GET_SELL_ORDER", new RowMapper<GetSellOrderResponse>() {
            @Override
            public GetSellOrderResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildGetSellOrderResponse(paramResultSet);
            }
        }, new Object[] { orderNumber });
    }

    private GetSellOrderResponse buildGetSellOrderResponse(ResultSet rs) throws SQLException {
        GetSellOrderResponse response = new GetSellOrderResponse();
        response.setOrderNumber(rs.getString("order_number"));
        response.setPartnerId(rs.getInt("partner_id") + "");
        response.setPartnerCode(rs.getString("partner_code"));
        response.setPartnerName(rs.getString("partner_name"));
        response.setPartnerContactsFirstName(rs.getString("contacts_first_name"));
        response.setPartnerContactsLastName(rs.getString("contacts_last_name"));
        response.setPartnerContactsMobilephone(rs.getString("contacts_mobilephone"));
        response.setOutboundStorage(rs.getInt("outbound_storage_id"));
        response.setStorageName(rs.getString("storage_name"));
        response.setSummary(rs.getString("summary"));
        response.setIsDraft(rs.getBoolean("is_draft"));
        response.setTotalPrice(rs.getDouble("total_price"));
        response.setGiftPrice(rs.getDouble("gift_price"));
        response.setFavorablePrice(rs.getDouble("favorable_price"));
        response.setGatheringPrice(rs.getDouble("gathering_price"));
        response.setBillsDate(DateUtils.formatDate(rs.getDate("bills_date"), "yyyy-MM-dd"));
        response.setCreateDate(DateUtils.formatDate(rs.getDate("create_date"), "yyyy-MM-dd"));
        response.setCreateBy(rs.getString("create_by"));
        response.setAuditor(rs.getInt("auditor") + "");
        response.setAuditDate(DateUtils.formatDate(rs.getDate("audit_date"), "yyyy-MM-dd"));
        response.setOwner(rs.getString("owner"));
        response.setOwnerName(rs.getString("ownerName"));
        return response;
    }

    private GetSellOrderItemResponse buildGetSellOrderItemResponse(ResultSet rs) throws SQLException {
        GetSellOrderItemResponse r = new GetSellOrderItemResponse();
        r.setId(rs.getString("id"));
        r.setOrderNumber(rs.getString("order_number"));
        r.setMaterialId(rs.getString("material_id"));
        r.setMaterialNo(rs.getString("material_no"));
        r.setMaterialName(rs.getString("material_name"));
        r.setSpecificationsModel(rs.getString("specifications_model"));
        r.setBrand(rs.getString("brand"));
        r.setSeason(rs.getString("season"));
        r.setBarcode(rs.getString("barcode"));
        r.setUnitName(rs.getString("unit_name"));
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
        r.setShipmentsAmount(rs.getInt("shipments_amount"));
        r.setRemark(rs.getString("remark"));
        r.setShipmentsDate(DateUtils.formatDate(rs.getDate("shipments_date"), "yyyy-MM-dd"));
        return r;
    }

    public Boolean delete(DeleteSellOrderRequest request) { // 删除只能删除草稿
        if (null != request && !CollectionUtils.isEmpty(request.getOrderNumberList())) {
            String orderNumbers = request.getOrderNumberList().toString().replace("[", "").replace("]", "");
            int deleteOrder = jdbcAccessContext.execute("SELL.SQL_DELETE_SELL_ORDER", new Object[] { orderNumbers.trim() });
            if (-1 != deleteOrder) {
                int deleteOrderItem = jdbcAccessContext.execute("SELL.SQL_DELETE_SELL_ORDER_ITEM", new Object[] { orderNumbers.trim() });
                if (-1 != deleteOrderItem) {
                    addOperationLog("销售订单", "删除销售订单，单据编号：[{0}]", orderNumbers, request.getUserId());
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }
}
