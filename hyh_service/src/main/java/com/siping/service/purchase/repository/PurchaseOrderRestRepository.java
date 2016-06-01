package com.siping.service.purchase.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.Convert;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.service.inventory.service.InventoryRestService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.invoicing.inventory.inventory.request.AddInventoryRequest;
import com.siping.smartone.purchase.request.DeletePurchaseOrderRequest;
import com.siping.smartone.purchase.request.PurchaseOrderItemRequest;
import com.siping.smartone.purchase.request.PurchaseOrderRequest;
import com.siping.smartone.purchase.response.PurchaseOrderItemResponse;
import com.siping.smartone.purchase.response.PurchaseOrderResponse;

@Repository
public class PurchaseOrderRestRepository extends BillsRepository {

    @Autowired
    private JDBCAccessContext jdbcAccessContext;
    @Autowired
    private InventoryRestService inventoryService;

    public ResultMsg add(PurchaseOrderRequest order) {
        // checkPostPeriodDateTime();
        String orderNumber = generateBillsNumber("purchase_order", "order_number");
        if (!order.getPurchaseOrderNumber().equals(orderNumber)) {
            order.setPurchaseOrderNumber(orderNumber);
        }
        order.setPurchaseOrderNumber(orderNumber);
        Object[] addTitleParams = new Object[] { order.getPurchaseOrderNumber(), order.getFromBillsNo(), order.getSupplierId(), order.getBillDate(),
                order.getReceiptDate() == "" ? null : order.getReceiptDate(), order.getInBoundStorage(), order.getSummary(), order.getIsDraft(), order.getTotalAmount(), order.getGiftAmount(),
                order.getDiscountAmount(), order.getPayAmount(), order.getCreateBy(), order.getOwner(), order.getCurrency(), order.getExchangeRate() };
        // 插入表头数据
        int add = 1;
        try {
            add = jdbcAccessContext.execute("PURCHASEORDER.SQL_ADD_PURCHASEORDER", addTitleParams);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessProcessException("新增采购订单失败");
        }
        if (-1 != add) {
            if (order.getItems() == null || order.getItems().isEmpty()) {
                throw new BusinessProcessException("缺少表体信息");
            }
            // 插入表体数据
            int[] adds = addOrderItems(order, orderNumber);

            if (Arrays.asList(adds).contains(-1)) {
                throw new BusinessProcessException("新增订单项失败!");
            }
            addOperationLog("采购订单", "新增采购订单，单据编号：[{0}]", orderNumber, order.getCreateBy() + "");
            return new ResultMsg(true, "新增成功", order.getPurchaseOrderNumber());
        }
        return new ResultMsg(false, "新增失败");
    }

    public Boolean delete(DeletePurchaseOrderRequest request) {
        if (null != request && !CollectionUtils.isEmpty(request.getOrderNumbers())) {
            String orderNumbers = request.getOrderNumbers().toString().replace("[", "").replace("]", "");

            // 删除草稿表头表尾
            int deleteOrder = jdbcAccessContext.execute("PURCHASEORDER.SQL_DELETE_PURCHASEORDER", new Object[] { orderNumbers });

            if (-1 != deleteOrder) {
                int deleteOrderItem = jdbcAccessContext.execute("PURCHASEORDER.SQL_DELETE_PURCHASEORDERITEM", new Object[] { orderNumbers });
                if (-1 != deleteOrderItem) {
                    addOperationLog("采购订单", "删除采购订单，单据编号：[{0}]", orderNumbers, request.getUserId());
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    // 编辑单据
    public Boolean edit(PurchaseOrderRequest request) {
        PurchaseOrderResponse order = getPAOOrder(request.getPurchaseOrderNumber());
        if (null == order)
            throw new BusinessProcessException("采购订单不存在!");
        if (!order.getIsDraft())
            throw new BusinessProcessException("不能编辑正式的采购订单!");
        Object[] params = new Object[] { request.getSupplierId(), request.getInBoundStorage(), request.getSummary(), request.getIsDraft(), request.getTotalAmount(), request.getGiftAmount(),
                request.getDiscountAmount(), request.getPayAmount(), Convert.toDate(request.getReceiptDate(), null), Convert.toDate(request.getBillDate(), null), request.getAuditor(),
                request.getPurchaseOrderNumber() };
        // 编辑表头表尾信息
        int editOrder = jdbcAccessContext.execute("PURCHASEORDER.SQL_UPDATE_PURCHASEORDER", params);

        if (-1 != editOrder) {// 删除以前的订单项
            int deleteOrderItem = jdbcAccessContext.execute("PURCHASEORDER.SQL_DELETE_PURCHASEORDERITEM", new Object[] { request.getPurchaseOrderNumber() });
            if (-1 != deleteOrderItem) {// 新增订单项
                int[] batchAddItems = addOrderItems(request, request.getPurchaseOrderNumber());
                while (Arrays.asList(batchAddItems).contains(-1)) {
                    throw new BusinessProcessException("编辑订单项失败!");
                }
                addOperationLog("采购订单", "更新采购订单，单据编号：[{0}]", request.getPurchaseOrderNumber(), request.getCreateBy() + "");
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public PurchaseOrderResponse get(String purchaseOrder) {
        // 查询表头表尾信息
        PurchaseOrderResponse response = getPAOOrder(purchaseOrder);
        if (null == response)
            throw new BusinessProcessException("采购订单不存在!");
        // 查询表体信息
        List<PurchaseOrderItemResponse> itemList = jdbcAccessContext.find("PURCHASEORDER.SQL_SELECT_PURCHASEORDERITEMS", new RowMapper<PurchaseOrderItemResponse>() {
            @Override
            public PurchaseOrderItemResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPurchaseOrderItemResponse(rs);
            }
        }, new Object[] { purchaseOrder });
        response.setItems(itemList);
        return response;
    }

    public PagingResponse<PurchaseOrderResponse> getList(CommonBillsRequest request) {
        PagingResponse<PurchaseOrderResponse> pagingResponse = new PagingResponse<PurchaseOrderResponse>();
        List<Object> paramsList = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        buildListQuerySQL(request, paramsList, sql, countSql);
        // 采购申请单总条数
        int totalRecords = jdbcAccessContext.findIntegerWithoutSqlManager(countSql.toString(), paramsList.toArray());

        if (null != request.getPageNo() && null != request.getPageSize()) {
            sql.append(" limit ?,? ");
            paramsList.add((request.getPageNo() - 1) * request.getPageSize());
            paramsList.add(request.getPageSize());
        }
        // 采购申请单表头信息数据
        List<PurchaseOrderResponse> response = jdbcAccessContext.findWithoutSqlManager(sql.toString(), new RowMapper<PurchaseOrderResponse>() {
            @Override
            public PurchaseOrderResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPurchaseOrderResponse(rs);
            }
        }, paramsList.toArray());
        pagingResponse.setRecords(response);
        pagingResponse.setTotalRecords(totalRecords);
        return pagingResponse;
    }

    // 查询sql语句拼接
    private void buildListQuerySQL(CommonBillsRequest request, List<Object> paramsList, StringBuilder sql, StringBuilder countSql) {
        sql.append("SELECT DISTINCT po.bill_date billDate, po.order_number orderNumber, po.receipt_date receiptDate, s.id inBoundStorage, "
            + "s.storage_name inBoundStorageName, po.partner_id supplierId, bp.partner_code supplierCode, bp.partner_name supplierName, bp.contacts_first_name contactsFirstName, "
            + "bp.contacts_last_name contactsLastName, bp.mobilephone mobile, po.total_price totalAmount, po.gift_price giftAmount, po.favorable_price discountAmount, po.pay_price payAmount, "
            + "po.is_draft isDraft, po.create_date createDate, po.create_by createBy, u.user_name createByName, po.audit_date auditDate, po.auditor auditBy, u2.user_name auditByName, "
            + "item.counts, item.receiptCounts, po.summary summary , po.status " + "FROM purchase_order po LEFT JOIN "
            + "( SELECT  order_number orderNumber,  sum(counts) counts,  sum(receipt_counts) receiptCounts FROM  purchase_order_item GROUP BY orderNumber) item "
            + "ON item.orderNumber = po.order_number LEFT JOIN purchase_order_item i ON po.order_number = i.order_number"
            + " left join material m on m.id = i.material_id LEFT JOIN business_partner bp ON bp.id = po.partner_id "
            + "LEFT JOIN `storage` s ON s.id = po.inbound_storage_id left join usr u on u.id = po.create_by left join usr u2 on u2.id = po.auditor WHERE 1=1");
        countSql.append("SELECT COUNT(1) FROM (SELECT DISTINCT PO.* " + "FROM purchase_order po LEFT JOIN "
            + "( SELECT  order_number orderNumber,  sum(counts) counts,  sum(receipt_counts) receiptCounts FROM  purchase_order_item GROUP BY orderNumber) item "
            + "ON item.orderNumber = po.order_number LEFT JOIN purchase_order_item i ON po.order_number = i.order_number"
            + " left join material m on m.id = i.material_id LEFT JOIN business_partner bp ON bp.id = po.partner_id "
            + "LEFT JOIN `storage` s ON s.id = po.inbound_storage_id left join usr u on u.id = po.create_by left join usr u2 on u2.id = po.auditor WHERE 1=1");
        if (null != request.getIsDraft()) {
            sql.append(" and po.is_draft = ?");
            countSql.append(" and po.is_draft = ?");
            paramsList.add(request.getIsDraft());
        }
        // 处理查询时间
        if (StringUtils.hasText(request.getStartDate())) {
            sql.append(" and Date(po.bill_date) >= ? ");
            countSql.append(" and Date(po.bill_date) >= ? ");
            paramsList.add(request.getStartDate());
        }
        if (StringUtils.hasText(request.getEndDate())) {
            sql.append(" and Date(po.bill_date) <= ? ");
            countSql.append(" and Date(po.bill_date) <= ? ");
            paramsList.add(request.getEndDate());
        }
        if (null != request.getReadytatus()) {
            sql.append(" and po.ready_status = ? ");
            countSql.append(" and po.ready_status = ?  ");
            paramsList.add(request.getReadytatus());
        }
        if (100 != request.getStatus()) {
            if (3 == request.getStatus()) {
                sql.append(" and po.status != 2 ");
                countSql.append(" and po.status != 2 ");
            } else {
                sql.append(" and po.status = ? ");
                countSql.append(" and po.status = ? ");
                paramsList.add(request.getStatus());
            }
        }
        if (StringUtils.hasText(request.getKeywords())) {
            // 处理关键字查询
            // 合作伙伴编码、名称
            sql.append(" AND ( ").append(" bp.partner_code like CONCAT('%',?,'%') ").append(" OR bp.partner_name like CONCAT('%',?,'%') ");
            countSql.append(" AND ( ").append(" bp.partner_code like CONCAT('%',?,'%') ").append(" OR bp.partner_name like CONCAT('%',?,'%') ");
            // 仓库编码、名称
            sql.append(" OR s.storage_no like CONCAT('%',?,'%') ").append(" OR s.storage_name like CONCAT('%',?,'%') ");
            countSql.append(" OR s.storage_no like CONCAT('%',?,'%') ").append(" OR s.storage_name like CONCAT('%',?,'%') ");
            // 商品货号、名称
            sql.append(" OR po.order_number LIKE CONCAT('%' ,?, '%') ");
            countSql.append(" OR po.order_number LIKE CONCAT('%' ,?, '%') ");
            sql.append(" OR m.material_no like CONCAT('%',?,'%') ").append(" OR m.material_name like CONCAT('%',?,'%') ");
            countSql.append(" OR m.material_no like CONCAT('%',?,'%') ").append(" OR m.material_name like CONCAT('%',?,'%') ");
            // 商品国际编码 所有人名称
            sql.append(" OR m.barcode like CONCAT('%',?,'%') ").append(" OR u.user_name like CONCAT('%',?,'%') ");
            countSql.append(" OR m.barcode like CONCAT('%',?,'%') ").append(" OR u.user_name like CONCAT('%',?,'%') ");
            for (int i = 0; i < 9; i++) {
                paramsList.add(request.getKeywords());
            }
            sql.append(") ");
            countSql.append(") ");
        }
        sql.append(" order by po.create_date desc");
        countSql.append(") a");
    }

    // 配置表体返回结果
    private PurchaseOrderItemResponse buildPurchaseOrderItemResponse(ResultSet rs) throws SQLException {
        PurchaseOrderItemResponse response = new PurchaseOrderItemResponse();
        response.setOrderNumber(rs.getString("orderNumber"));
        response.setId(rs.getInt("id"));
        response.setMaterialId(rs.getInt("materialId"));
        response.setMaterialNo(rs.getString("materialNo"));
        response.setMaterialName(rs.getString("materialName"));
        response.setDescription(rs.getString("description"));
        response.setSpecificationsModel(rs.getString("specificationsModel"));
        response.setBrand(rs.getString("brand"));
        response.setSeason(rs.getString("season"));
        response.setBarcode(rs.getString("barcode"));
        // response.setUnitId(rs.getInt("unitId"));
        response.setUnitName(rs.getString("unitName"));
        response.setCounts(rs.getDouble("counts"));
        response.setPurchasePrice(rs.getDouble("purchasePrice"));
        response.setDiscountRate(rs.getDouble("discountRate"));
        response.setAfterDiscount(rs.getDouble("afterDiscount"));
        response.setTaxRate(rs.getDouble("taxRate"));
        response.setTax(rs.getDouble("tax"));
        response.setAmount(rs.getDouble("amount"));
        response.setIsGift(rs.getBoolean("isGift"));
        response.setReceiptCounts(rs.getDouble("receiptCounts"));
        response.setRemark(rs.getString("remark"));
        response.setExpirationDate(rs.getInt("expirationDate"));
        response.setIsBatch(rs.getBoolean("isBatch"));
        response.setRowNumber(rs.getInt("rowNumber"));
        response.setNotReferencedAmount(rs.getDouble("counts") - rs.getDouble("receiptCounts"));
        response.setReadyStatus(rs.getInt("readyStatus"));
        response.setMaterialType(rs.getString("typeName"));
        try {
            response.setCurrencyPrice(rs.getDouble("currency_price"));
        } catch (Exception e) {
            // TODO: handle exception
        }
        return response;
    }

    // 配置表头返回结果对象
    private PurchaseOrderResponse buildPurchaseOrderResponse(ResultSet rs) throws SQLException {
        PurchaseOrderResponse response = new PurchaseOrderResponse();
        response.setOrderNumber(rs.getString("orderNumber"));
        response.setSupplierId(rs.getInt("supplierId"));
        response.setSupplierCode(rs.getString("supplierCode"));
        response.setSupplierName(rs.getString("supplierName"));
        response.setContactsFirstName(rs.getString("contactsFirstName"));
        response.setContactsLastName(rs.getString("contactsLastName"));
        response.setMobile(rs.getString("mobile"));
        response.setBillDate(rs.getString("billDate"));
        response.setReceiptDate(rs.getString("receiptDate"));
        response.setInBoundStorage(rs.getInt("inBoundStorage"));
        response.setInBoundStorageName(rs.getString("inBoundStorageName"));
        response.setSummary(rs.getString("summary"));
        response.setIsDraft(rs.getBoolean("isDraft"));
        try {
            response.setCounts(rs.getDouble("counts"));
        } catch (Exception e) {
        }
        response.setTotalAmount(rs.getDouble("totalAmount"));
        response.setGiftAmount(rs.getDouble("giftAmount"));
        response.setDiscountAmount(rs.getDouble("discountAmount"));
        response.setPayAmount(rs.getDouble("payAmount"));
        response.setCreateDate(rs.getString("createDate"));
        response.setCreateBy(rs.getInt("createBy"));
        response.setCreateByName(rs.getString("createByName"));
        response.setAuditDate(rs.getString("auditDate"));
        response.setAuditBy(rs.getInt("auditBy"));
        response.setAuditByName(rs.getString("auditByName"));
        try {
            response.setOwner(rs.getString("owner"));
            response.setOwnerName(rs.getString("ownerName"));
        } catch (Exception e) {
        }
        try {
            response.setStatus(rs.getInt("status"));
        } catch (Exception e) {
        }
        try {
            response.setReadyStatus(rs.getInt("readyStatus"));
        } catch (Exception e) {
        }
        try {
            response.setCurrency(rs.getString("currency"));
            response.setExchangeRate(rs.getDouble("exchange_rate"));
        } catch (Exception e) {

        }
        return response;
    }

    private PurchaseOrderResponse getPAOOrder(String orderNumber) {
        return jdbcAccessContext.findUniqueResult("PURCHASEORDER.SQL_SELECT_PURCHASEORDER_BYID", new RowMapper<PurchaseOrderResponse>() {
            @Override
            public PurchaseOrderResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPurchaseOrderResponse(rs);
            }
        }, new Object[] { orderNumber });
    }

    // 新增表体数据函数
    private int[] addOrderItems(PurchaseOrderRequest request, String orderNumber) {
        List<PurchaseOrderItemRequest> list = request.getItems();
        List<Object[]> addParamsList = new ArrayList<Object[]>();
        for (PurchaseOrderItemRequest item : list) {
            Object[] addTableParams = new Object[] { orderNumber, item.getMaterialId(),
                    // item.getUnitId(),
                    item.getCounts(), item.getPurchasePrice(), item.getDiscountRate(), item.getAfterDiscount(), item.getTaxRate(), item.getTax(), item.getAmount(), item.getIsGift(), item.getRemark(),
                    item.getRowNumber(), request.getFromBillsNo(), item.getCurrencyPrice() };
            addParamsList.add(addTableParams);
            // if(request.getIsDraft() == false){
            // changeInvetory(request, orderNumber, item);
            // }
        }
        // 插入表体数据
        int[] adds = jdbcAccessContext.batchExecute("PURCHASEORDER.SQL_ADD_PURCHASEORDERITEM", addParamsList);
        return adds;
    }

    private void changeInvetory(PurchaseOrderRequest request, String orderNumber, PurchaseOrderItemRequest item) {
        AddInventoryRequest inventoryRequest = new AddInventoryRequest();
        inventoryRequest.setMaterialId(item.getMaterialId());
        inventoryRequest.setStorageId(request.getInBoundStorage());
        inventoryRequest.setIsInbound(Boolean.TRUE);
        inventoryRequest.setBillsNo(orderNumber);
        inventoryRequest.setBillsDate(request.getBillDate());
        inventoryRequest.setBatchNumber(null);
        inventoryRequest.setProductionDate(null);
        inventoryRequest.setCounts(item.getCounts());
        inventoryRequest.setPurchasePrice(item.getAfterDiscount());
        inventoryRequest.setTotal(item.getAmount());
        inventoryRequest.setCreateBy(item.getCreateBy());
        inventoryService.add(inventoryRequest);
    }
}
