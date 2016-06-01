package com.siping.wms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.service.purchase.service.PurchaseOrderRestService;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.purchase.response.PurchaseOrderItemResponse;
import com.siping.smartone.purchase.response.PurchaseOrderResponse;
import com.siping.smartone.wms.request.GenerateReceiptParam;
import com.siping.smartone.wms.response.ReadyReceiptItemResponse;
import com.siping.smartone.wms.response.ReadyReceiptResponse;

@Repository
public class ReadyReceiptRestRepository extends BillsRepository {
    @Autowired
    private PurchaseOrderRestService purchaseOrderRestService;

    public ReadyReceiptResponse get(String purchaseOrder) {
        // 查询表头表尾信息
        ReadyReceiptResponse response = getReadyReceipt(purchaseOrder);
        if (null == response)
            throw new BusinessProcessException("采购订单不存在!");
        // 查询表体信息
        List<ReadyReceiptItemResponse> itemList = jdbcAccessContext.find("READYRECEIPT.SQL_SELECT_READYRECEIPTITEMS", new RowMapper<ReadyReceiptItemResponse>() {
            @Override
            public ReadyReceiptItemResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildReadyReceiptItemResponse(rs);
            }
        }, new Object[] { purchaseOrder });
        response.setItems(itemList);
        return response;
    }

    public PagingResponse<ReadyReceiptResponse> getList(CommonBillsRequest request) {
        PagingResponse<ReadyReceiptResponse> pagingResponse = new PagingResponse<ReadyReceiptResponse>();
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
        List<ReadyReceiptResponse> response = jdbcAccessContext.findWithoutSqlManager(sql.toString(), new RowMapper<ReadyReceiptResponse>() {
            @Override
            public ReadyReceiptResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildReadyReceiptResponse(rs);
            }
        }, paramsList.toArray());
        pagingResponse.setRecords(response);
        pagingResponse.setTotalRecords(totalRecords);
        return pagingResponse;
    }

    // 查询sql语句拼接
    private void buildListQuerySQL(CommonBillsRequest request, List<Object> paramsList, StringBuilder sql, StringBuilder countSql) {
        sql.append("SELECT DISTINCT po.bill_date billDate, po.order_number orderNumber, po.receipt_date receiptDate, s.id inBoundStorage, "
            + "s.storage_name inBoundStorageName, po.partner_id partnerId, bp.partner_code partnerCode, bp.partner_name partnerName, bp.contacts_first_name contactsFirstName, "
            + "bp.contacts_last_name contactsLastName, bp.mobilephone mobilePhone, po.total_price totalAmount, po.gift_price giftAmount, po.favorable_price discountAmount, po.pay_price payAmount, "
            + "po.is_draft isDraft, po.create_date createDate, po.create_by createBy, u.user_name createByName, po.audit_date auditDate, po.auditor auditBy, u2.user_name auditByName, "
            + "item.counts, item.receiptCounts, po.summary summary , po.ready_status readyStatus " + "FROM purchase_order po LEFT JOIN "
            + "( SELECT  order_number orderNumber,  sum(counts) counts,  sum(receipt_counts) receiptCounts FROM  purchase_order_item GROUP BY orderNumber) item "
            + "ON item.orderNumber = po.order_number LEFT JOIN purchase_order_item i ON po.order_number = i.order_number"
            + " left join material m on m.id = i.material_id LEFT JOIN business_partner bp ON bp.id = po.partner_id "
            + "LEFT JOIN `storage` s ON s.id = po.inbound_storage_id left join usr u on u.id = po.create_by left join usr u2 on u2.id = po.auditor WHERE po.status = 0");
        countSql.append("SELECT COUNT(1) FROM (SELECT DISTINCT PO.* " + "FROM purchase_order po LEFT JOIN "
            + "( SELECT  order_number orderNumber,  sum(counts) counts,  sum(receipt_counts) receiptCounts FROM  purchase_order_item GROUP BY orderNumber) item "
            + "ON item.orderNumber = po.order_number LEFT JOIN purchase_order_item i ON po.order_number = i.order_number"
            + " left join material m on m.id = i.material_id LEFT JOIN business_partner bp ON bp.id = po.partner_id "
            + "LEFT JOIN `storage` s ON s.id = po.inbound_storage_id left join usr u on u.id = po.create_by left join usr u2 on u2.id = po.auditor WHERE po.status = 0");
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
        if (100 != request.getStatus()) {
            sql.append(" and po.ready_status = ? ");
            countSql.append(" and po.ready_status = ? ");
            paramsList.add(request.getStatus());
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
    private ReadyReceiptItemResponse buildReadyReceiptItemResponse(ResultSet rs) throws SQLException {
        ReadyReceiptItemResponse response = new ReadyReceiptItemResponse();
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
        response.setUnitName(rs.getString("unitName"));
        response.setCounts(rs.getDouble("counts"));
        response.setRemark(rs.getString("remark"));
        response.setRowNumber(rs.getInt("rowNumber"));
        response.setReadyStatus(rs.getInt("readyStatus"));
        return response;
    }

    // 配置表头返回结果对象
    private ReadyReceiptResponse buildReadyReceiptResponse(ResultSet rs) throws SQLException {
        ReadyReceiptResponse response = new ReadyReceiptResponse();
        response.setOrderNumber(rs.getString("orderNumber"));
        response.setPartnerCode(rs.getString("partnerCode"));
        response.setPartnerName(rs.getString("partnerName"));
        response.setInBoundStorage(rs.getInt("inBoundStorage"));
        response.setInBoundStorageName(rs.getString("inBoundStorageName"));
        response.setSummary(rs.getString("summary"));
        response.setReceiptDate(DateUtils.formatDate(rs.getDate("receiptDate"), "yyyy-MM-dd"));
        response.setBillDate(DateUtils.formatDate(rs.getDate("billDate"), "yyyy-MM-dd"));
        response.setContactsFirstName(rs.getString("contactsFirstName"));
        response.setContactsLastName(rs.getString("contactsLastName"));
        response.setMobilePhone(rs.getString("mobilePhone"));
        response.setReadyStatus(rs.getInt("readyStatus"));
        try {
            response.setOwnerName(rs.getString("ownerName"));
        } catch (Exception e) {
        }
        return response;
    }

    private ReadyReceiptResponse getReadyReceipt(String orderNumber) {
        return jdbcAccessContext.findUniqueResult("READYRECEIPT.SQL_SELECT_READYRECEIPT_BYID", new RowMapper<ReadyReceiptResponse>() {
            @Override
            public ReadyReceiptResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildReadyReceiptResponse(rs);
            }
        }, new Object[] { orderNumber });
    }

    public Boolean generateReceipt(GenerateReceiptParam request) {
        PurchaseOrderResponse purchaseOrder = purchaseOrderRestService.get(request.getOrderNumber());
        Iterator<PurchaseOrderItemResponse> iter = purchaseOrder.getItems().iterator();
        // 判断采购订单是否被引用完
        Boolean orderComplete = true;
        while (iter.hasNext()) {
            PurchaseOrderItemResponse p = iter.next();
            if (!Arrays.asList(request.getRowNumbers()).contains(p.getRowNumber())) {
                if (p.getReadyStatus() == 0) {
                    orderComplete = false;
                }
                iter.remove();
            }
        }
        List<Object[]> param = new LinkedList<>();
        for (Integer i : request.getRowNumbers()) {
            Object[] obj = new Object[] { i, request.getOrderNumber() };
            param.add(obj);
        }
        // 修改purchase_order_item 中receipt_status 状态为已引用（1）
        int[] updates = jdbcAccessContext.batchExecute("READYRECEIPT.SQL_UPDATE_READYRECEIPT_ITEM_STATUS", param);
        if (Arrays.asList(updates).contains(-1)) {
            throw new BusinessProcessException("修改预收货单列表项状态失败");
        }
        // 推送到库存收货单inventory_receipt
        String receiptNumber = this.generateBillsNumber("inventory_receipt", "receipt_number");
        Object[] inventoryReceiptParam = new Object[] { receiptNumber, request.getOrderNumber(), request.getLoginId(), request.getConsignee(), request.getInboundStorageId(), request.getStartStation(),
                request.getEndStation(), request.getTransportationType() };
        int add = jdbcAccessContext.execute("READYRECEIPT.SQL_ADD_INVENTORY_RECEIPT", inventoryReceiptParam);
        if (-1 == add) {
            throw new BusinessProcessException("添加库存收货单失败");
        }
        List<Object[]> inventoryReceiptItemParam = new LinkedList<>();
        for (PurchaseOrderItemResponse p : purchaseOrder.getItems()) {
            inventoryReceiptItemParam.add(new Object[] { receiptNumber, p.getMaterialId(), p.getCounts(), request.getOrderNumber(), p.getRowNumber() });
        }
        int[] adds = jdbcAccessContext.batchExecute("READYRECEIPT.SQL_ADD_INVENTORY_RECEIPT_ITEM", inventoryReceiptItemParam);
        if (Arrays.asList(adds).contains(-1)) {
            throw new BusinessProcessException("添加库存收货单项失败");
        }
        int update;
        if (orderComplete == true) {
            update = jdbcAccessContext.execute("READYRECEIPT.SQL_UPDATE_READY_RECEIPT_STATUS", new Object[] { 2, request.getOrderNumber() });
        } else {
            update = jdbcAccessContext.execute("READYRECEIPT.SQL_UPDATE_READY_RECEIPT_STATUS", new Object[] { 1, request.getOrderNumber() });
        }
        if (-1 == update) {
            throw new BusinessProcessException("修改预收货单状态");
        }
        return Boolean.TRUE;
    }
}
