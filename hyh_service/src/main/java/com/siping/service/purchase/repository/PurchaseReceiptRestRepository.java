package com.siping.service.purchase.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
import com.siping.service.purchase.service.PurchaseOrderRestService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.common.request.GetGenerateBillsNumber;
import com.siping.smartone.invoicing.inventory.inventory.request.AddInventoryRequest;
import com.siping.smartone.purchase.request.DeletePurchaseReceiptRequest;
import com.siping.smartone.purchase.request.PurchaseReceiptItemRequest;
import com.siping.smartone.purchase.request.PurchaseReceiptRequest;
import com.siping.smartone.purchase.response.PurchaseOrderItemResponse;
import com.siping.smartone.purchase.response.PurchaseOrderResponse;
import com.siping.smartone.purchase.response.PurchaseReceiptItemResponse;
import com.siping.smartone.purchase.response.PurchaseReceiptResponse;

@Repository
public class PurchaseReceiptRestRepository extends BillsRepository {
    @Autowired
    private InventoryRestService inventoryService;
    @Autowired
    private PurchaseOrderRestService purchaseOrderRestService;

    public ResultMsg add(PurchaseReceiptRequest order) {
        // checkPostPeriodDateTime();
        String receiptNumber = generateBillsNumber("purchase_receipt", "receipt_number");
        if (!order.getReceiptNumber().equals(receiptNumber)) {
            order.setReceiptNumber(receiptNumber);
        }
        Object[] addTitleParams = new Object[] { order.getReceiptNumber(), order.getFromBillsNo(), order.getPartnerId(), order.getBillDate(), order.getInboundStorageId(), order.getSummary(),
                order.getIsDraft(), order.getTotalAmount(), order.getGiftAmount(), order.getDiscountAmount(), order.getPayAmount(), order.getCreateBy(), order.getOwner() };
        // 插入表头数据
        int add = 1;
        try {
            add = jdbcAccessContext.execute("PURCHASERECEIPT.SQL_ADD_PURCHASERECEIPT", addTitleParams);
        } catch (Exception e) {
            throw new BusinessProcessException("新增订单时出现错误");
        }
        if (-1 != add) {
            if (order.getItems() == null || order.getItems().isEmpty()) {
                throw new BusinessProcessException("缺少表体信息");
            }
            // 插入表体数据
            int[] adds = addOrderItems(order, order.getReceiptNumber());

            if (Arrays.asList(adds).contains(-1)) {
                throw new BusinessProcessException("新增订单项失败!");
            }
            addOperationLog("采购收货单", "新增采购收货单，单据编号：[{0}],请求的ip为：[" + order.getIp() + "]，mac为：[" + order.getMac() + "]", receiptNumber, order.getCreateBy()  + "");
            return new ResultMsg(Boolean.TRUE, "新增成功", order.getReceiptNumber());
        }
        return new ResultMsg(Boolean.FALSE, "新增失败", order.getReceiptNumber());
    }

    public Boolean delete(DeletePurchaseReceiptRequest request) {
        if (null != request && !CollectionUtils.isEmpty(request.getOrderNumbers())) {
            String receiptNumbers = request.getOrderNumbers().toString().replace("[", "").replace("]", "");

            // 删除草稿表头表尾
            int deleteOrder = jdbcAccessContext.execute("PURCHASERECEIPT.SQL_DELETE_PURCHASERECEIPT", new Object[] { receiptNumbers });

            if (-1 != deleteOrder) {
                int deleteOrderItem = jdbcAccessContext.execute("PURCHASERECEIPT.SQL_DELETE_PURCHASERECEIPTITEM", new Object[] { receiptNumbers });
                if (-1 != deleteOrderItem) {
                    addOperationLog("采购收货单", "删除采购收货单，单据编号：[{0}]", receiptNumbers, request.getUserId() + "");
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    // 编辑单据
    public Boolean edit(PurchaseReceiptRequest request) {
        PurchaseReceiptResponse response = getPurchaseReceipt(request.getReceiptNumber());
        if (null == response) {
            throw new BusinessProcessException("采购收货不存在!");
        }
        if (!response.getIsDraft())
            throw new BusinessProcessException("不能编辑正式的采购收货单!");
        Object[] editParams = new Object[] { request.getPartnerId(), request.getInboundStorageId(), request.getSummary(), request.getIsDraft(), request.getTotalAmount(), request.getGiftAmount(),
                request.getDiscountAmount(), request.getPayAmount(), request.getBillDate(), request.getAuditDate(), request.getAuditor(), request.getReceiptNumber() };
        // 编辑表头表尾信息
        int edit = jdbcAccessContext.execute("PURCHASERECEIPT.SQL_UPDATE_PURCHASERECEIPT", editParams);
        if (-1 != edit) {
            int deleteOrderItem = jdbcAccessContext.execute("PURCHASERECEIPT.SQL_DELETE_PURCHASERECEIPTITEM", new Object[] { request.getReceiptNumber() });
            if (-1 != deleteOrderItem) {// 新增订单项
                int[] batchAddItems = addOrderItems(request, request.getReceiptNumber());
                while (Arrays.asList(batchAddItems).contains(-1)) {
                    throw new BusinessProcessException("编辑收货单项失败!");
                }
                addOperationLog("采购收货单", "更新采购收货单，单据编号：[{0}]", request.getReceiptNumber(), request.getCreateBy() + "");
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public PurchaseReceiptResponse get(String receiptNumber) {
        // 查询表头表尾信息
        PurchaseReceiptResponse response = getPurchaseReceipt(receiptNumber);
        if (null == response)
            throw new BusinessProcessException("采购订单不存在!");

        // 查询表体信息
        List<PurchaseReceiptItemResponse> itemList = jdbcAccessContext.find("PURCHASERECEIPT.SQL_SELECT_PURCHASERECEIPTITEMS", new RowMapper<PurchaseReceiptItemResponse>() {
            @Override
            public PurchaseReceiptItemResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPurchaseReceiptItemResponse(rs);
            }
        }, new Object[] { receiptNumber });
        if (CollectionUtils.isEmpty(itemList))
            throw new BusinessProcessException("采购订单没有订单项!");
        response.setItems(itemList);
        return response;
    }

    // 配置表体返回结果
    protected PurchaseReceiptItemResponse buildPurchaseReceiptItemResponse(ResultSet rs) throws SQLException {
        PurchaseReceiptItemResponse response = new PurchaseReceiptItemResponse();
        response.setOrderNumber(rs.getString("receiptNumber"));
        response.setId(rs.getInt("id"));
        response.setMaterialId(rs.getInt("materialId"));
        response.setMaterialNo(rs.getString("materialNo"));
        response.setMaterialName(rs.getString("materialName"));
        response.setSpecificationsModel(rs.getString("specificationsModel"));
        response.setBrand(rs.getString("brand"));
        response.setSeason(rs.getString("season"));
        response.setBarcode(rs.getString("barcode"));
        response.setUnitId(rs.getInt("unitId"));
        response.setUnitName(rs.getString("unitName"));
        response.setCounts(rs.getDouble("counts"));
        response.setPurchasePrice(rs.getDouble("purchasePrice"));
        response.setDiscountRate(rs.getDouble("discountRate"));
        response.setAfterDiscount(rs.getDouble("afterDiscount"));
        response.setTaxRate(rs.getDouble("taxRate"));
        response.setTax(rs.getDouble("tax"));
        response.setBatchNumber(rs.getString("batchNumber"));
        response.setProductionDate(rs.getString("productionDate"));
        response.setIsBatch(rs.getBoolean("isBatch"));
        response.setAmount(rs.getDouble("total"));
        response.setIsGift(rs.getBoolean("isGift"));
        response.setRemark(rs.getString("remark"));
        return response;
    }

    // 配置表头返回结果对象
    private static PurchaseReceiptResponse buildPurchaseReceiptResponse(ResultSet rs) throws SQLException {
        PurchaseReceiptResponse response = new PurchaseReceiptResponse();
        response.setOrderNumber(rs.getString("receiptNumber"));
        // response.setPurchaseFlowId(rs.getString("purchaseFlowId"));
        response.setSupplierId(rs.getInt("supplierId"));
        response.setSupplierCode(rs.getString("supplierCode"));
        response.setSupplierName(rs.getString("supplierName"));
        response.setContactsFirstName(rs.getString("contactsFirstName"));
        response.setContactsLastName(rs.getString("contactsLastName"));
        response.setMobile(rs.getString("mobile"));
        response.setBillDate(rs.getString("billDate"));
        response.setInBoundStorage(rs.getInt("inBoundStorage"));
        response.setInBoundStorageName(rs.getString("inBoundStorageName"));
        response.setSummary(rs.getString("summary"));
        response.setIsDraft(rs.getBoolean("isDraft"));
        response.setTotalAmount(rs.getDouble("totalAmount"));
        response.setGiftAmount(rs.getDouble("giftAmount"));
        response.setDiscountAmount(rs.getDouble("discountAmount"));
        response.setPayAmount(rs.getDouble("payAmount"));
        response.setCreateDate(rs.getString("createDate"));
        response.setCreateBy(rs.getInt("createBy"));
        response.setCreateByName(rs.getString("createByName"));
        response.setAuditDate(rs.getString("auditDate"));
        try {
            response.setOwner(rs.getString("owner"));
            response.setOwnerName(rs.getString("ownerName"));
        } catch (Exception e) {
        }
        return response;
    }

    private PurchaseReceiptResponse getPurchaseReceipt(String receiptNumber) {
        return jdbcAccessContext.findUniqueResult("PURCHASERECEIPT.SQL_SELECT_PURCHASERECEIPT_BYID", new RowMapper<PurchaseReceiptResponse>() {
            @Override
            public PurchaseReceiptResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPurchaseReceiptResponse(rs);
            }
        }, new Object[] { receiptNumber });
    }

    private int[] addOrderItems(PurchaseReceiptRequest order, String receiptNumber) { // 新增表体数据函数
        List<PurchaseReceiptItemRequest> list = order.getItems();
        List<Object[]> addParamsList = new ArrayList<Object[]>();
        List<Object[]> fillReferencedAmountParams = new ArrayList<Object[]>();
        
        for (int i=0;i<list.size();i++) {
            PurchaseReceiptItemRequest item = list.get(i);
            // 检查批次号不能重复
            int counts=jdbcAccessContext.findInteger("PURCHASERECEIPT.CHECK_BATCH_NUMBER", new Object[]{item.getBatchNumber(),item.getMaterialId()});
            if(1<counts){
                throw new BusinessProcessException("第"+i+1+"行,批次号"+item.getBatchNumber()+"不能重复");
            }
            addParamsList.add(new Object[] { receiptNumber, item.getMaterialId(), item.getCounts(), item.getPurchasePrice(), item.getDiscountRate(), item.getAfterDiscount(), item.getTaxRate(),
                    item.getTax(), item.getBatchNumber(), item.getProductionDate() == "" ? null : item.getProductionDate(), item.getDueDate() == "" ? null : item.getDueDate(), item.getAmount(),
                    item.getIsGift(), item.getRemark(), item.getCreateBy() });
            if (!order.getIsDraft()) {
                if (!order.getIsDraft()) {
                    changeInventory(order, item);
                }
                if (null != item.getRowNumber()) { // 有行号代表是引用过来的
                    fillReferencedAmountParams.add(new Object[] { item.getCounts(), item.getRowNumber(), order.getFromBillsNo() });
                }
            }
        }
        if (0 < fillReferencedAmountParams.size()) { // 反写采购订单收货数量receipt_counts参数
            int[] fills = jdbcAccessContext.batchExecute("PURCHASERECEIPT.SQL_FILL_RECEIPT_COUNTS", fillReferencedAmountParams);
            if (Arrays.asList(fills).contains(-1)) {
                throw new BusinessProcessException("数据反写出错!");
            }
        }
        int[] adds = jdbcAccessContext.batchExecute("PURCHASERECEIPT.SQL_ADD_PURCHASERECEIPTITEM", addParamsList); // 插入表体数据
        if (Arrays.asList(adds).contains(-1)) {
            throw new BusinessProcessException("插入商品信息出错!");
        }
        return adds;
    }

    private void changeInventory(PurchaseReceiptRequest order, PurchaseReceiptItemRequest item) {
        AddInventoryRequest inventoryRequest = new AddInventoryRequest();
        inventoryRequest.setMaterialId(item.getMaterialId());
        inventoryRequest.setStorageId(0); // 仓库id为0，商品入到虚拟仓
        inventoryRequest.setStorageLocationId(null); // 虚拟仓没有库位
        inventoryRequest.setIsInbound(true);
        inventoryRequest.setBillsNo(order.getReceiptNumber());
        inventoryRequest.setBillsDate(order.getBillDate());
        inventoryRequest.setCounts(item.getCounts());
        inventoryRequest.setTotal(item.getAmount());
        inventoryRequest.setPurchasePrice(item.getPurchasePrice() * item.getDiscountRate() / 100);
        inventoryRequest.setSellPrice(null);
        inventoryRequest.setCreateBy(order.getCreateBy());
        inventoryRequest.setBatchNumber(item.getBatchNumber());
        inventoryRequest.setProductionDate(item.getProductionDate());
        inventoryRequest.setExpirationDate(item.getDueDate());
        inventoryService.add(inventoryRequest);
    }

    public PagingResponse<PurchaseReceiptResponse> getList(CommonBillsRequest request) {
        PagingResponse<PurchaseReceiptResponse> pagingResponse = new PagingResponse<PurchaseReceiptResponse>();
        List<Object> paramsList = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        buildListQuerySQL(request, paramsList, sql, countSql);
        // 采购入库单总条数
        int totalRecords = jdbcAccessContext.findIntegerWithoutSqlManager(countSql.toString(), paramsList.toArray());

        if (null != request.getPageNo() && null != request.getPageSize()) {
            sql.append(" limit ?,? ");
            paramsList.add((request.getPageNo() - 1) * request.getPageSize());
            paramsList.add(request.getPageSize());
        }

        // 采购入库单表头信息数据
        List<PurchaseReceiptResponse> response = jdbcAccessContext.findWithoutSqlManager(sql.toString(), new RowMapper<PurchaseReceiptResponse>() {
            @Override
            public PurchaseReceiptResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPurchaseReceiptResponse(rs);
            }
        }, paramsList.toArray());
        pagingResponse.setRecords(response);
        pagingResponse.setTotalRecords(totalRecords);
        return pagingResponse;
    }

    // 查询sql语句拼接
    private void buildListQuerySQL(CommonBillsRequest request, List<Object> paramsList, StringBuilder sql, StringBuilder countSql) {
        sql.append(" SELECT DISTINCT pr.bill_date billDate, pr.receipt_number receiptNumber, s.id inBoundStorage, s.storage_name inBoundStorageName, "
            + "pr.partner_id supplierId, bp.partner_code supplierCode, bp.partner_name supplierName, bp.contacts_first_name contactsFirstName, bp.contacts_last_name contactsLastName, "
            + "bp.mobilephone mobile, pr.total_price totalAmount, pr.gift_price giftAmount, pr.favorable_price discountAmount, pr.pay_price payAmount, pr.is_draft isDraft, "
            + "pr.create_date createDate, pr.create_by createBy, u.user_name createByName, pr.audit_date auditDate, pr.auditor auditBy, u2.user_name auditByName, item.counts, pr.summary summary "
            + "FROM purchase_receipt pr "
            + "LEFT JOIN ( SELECT receipt_number receiptNumber,   sum(counts) counts FROM purchase_receipt_item GROUP BY receipt_number) item ON item.receiptNumber = pr.receipt_number "
            + "LEFT JOIN business_partner bp ON bp.id = pr.partner_id LEFT JOIN `storage` s ON s.id = pr.inbound_storage_id LEFT JOIN usr u ON u.id = pr.create_by "
            + "LEFT JOIN usr u2 ON u2.id = pr.auditor LEFT JOIN purchase_receipt_item i on i.receipt_number = pr.receipt_number left join material m on m.id = i.material_id WHERE 1=1");
        countSql.append(" SELECT COUNT(1) FROM( SELECT DISTINCT PR.* FROM purchase_receipt pr "
            + "LEFT JOIN ( SELECT receipt_number receiptNumber, sum(counts) counts FROM  purchase_receipt_item GROUP BY  receipt_number) item ON item.receiptNumber = pr.receipt_number "
            + "LEFT JOIN business_partner bp ON bp.id = pr.partner_id LEFT JOIN `storage` s ON s.id = pr.inbound_storage_id LEFT JOIN usr u ON u.id = pr.create_by "
            + "LEFT JOIN usr u2 ON u2.id = pr.auditor LEFT JOIN purchase_receipt_item i on i.receipt_number = pr.receipt_number left join material m on m.id = i.material_id WHERE 1=1");
        if (null != request.getIsDraft()) {
            sql.append(" and pr.is_draft = ?");
            countSql.append(" and pr.is_draft = ?");
            paramsList.add(request.getIsDraft());
        }
        // 处理查询时间
        if (StringUtils.hasText(request.getStartDate())) {
            sql.append(" and Date(pr.bill_date) >= ? ");
            countSql.append(" and Date(pr.bill_date) >= ? ");
            paramsList.add(request.getStartDate());
        }
        if (StringUtils.hasText(request.getEndDate())) {
            sql.append(" and Date(pr.bill_date) <= ? ");
            countSql.append(" and Date(pr.bill_date) <= ? ");
            paramsList.add(request.getEndDate());
        }

        if (StringUtils.hasText(request.getKeywords())) {
            // 处理关键字查询
            // 合作伙伴编码、名称
            sql.append(" AND ( ").append(" bp.partner_code like CONCAT('%',?,'%') ").append(" OR bp.partner_name like CONCAT('%',?,'%') ");
            countSql.append(" AND ( ").append(" bp.partner_code like CONCAT('%',?,'%') ").append(" OR bp.partner_name like CONCAT('%',?,'%') ");
            // 仓库编码、名称
            sql.append(" OR s.storage_no like CONCAT('%',?,'%') ").append(" OR s.storage_name like CONCAT('%',?,'%') ");
            countSql.append(" OR s.storage_no like CONCAT('%',?,'%') ").append(" OR s.storage_name like CONCAT('%',?,'%') ");
            sql.append(" OR pr.receipt_number like CONCAT('%',?,'%') ");
            countSql.append(" OR pr.receipt_number like CONCAT('%',?,'%') ");
            // 商品货号、名称
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
        sql.append(" order by pr.create_date desc");
        countSql.append(") a");
    }

    public String getGenerateBillsNumber(GetGenerateBillsNumber request) {
        return generateBillsNumber(request.getTableName(), request.getPrimaryKeyName());
    }

    public ResultMsg updatePOStatus(String fromBillsNo, Integer userId) {
        ResultMsg resultMsg = new ResultMsg();
        PurchaseOrderResponse purchaseOrder = purchaseOrderRestService.get(fromBillsNo);
        List<Double> notReceiptCounts = new LinkedList<Double>();
        for (PurchaseOrderItemResponse p : purchaseOrder.getItems()) {
            notReceiptCounts.add(p.getCounts() - p.getReceiptCounts());
        }
        String notReceiptStr = notReceiptCounts.toString().replace(",", "").replace(".", "").replace("[", "").replace("]", "").replace(" ", "");
        if (notReceiptStr.matches("[0]+") && notReceiptStr.length() != 0) { // 修改状态
            int update = jdbcAccessContext.execute("PURCHASEORDER.SQL_UPDATE_STATUS", new Object[] { 2, fromBillsNo });
            if (-1 == update) {
                throw new BusinessProcessException("修改订单状态失败");
            }
        } else {
            int update = jdbcAccessContext.execute("PURCHASEORDER.SQL_UPDATE_STATUS", new Object[] { 1, fromBillsNo });
            if (-1 == update) {
                throw new BusinessProcessException("修改订单状态失败");
            }
        }
        addOperationLog("采购收货单", "更新采购收货单，单据编号：[{0}] 状态", fromBillsNo, userId + "");
        return resultMsg;
    }
}
