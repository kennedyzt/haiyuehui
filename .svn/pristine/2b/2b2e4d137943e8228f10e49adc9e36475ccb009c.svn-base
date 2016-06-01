package com.siping.service.inventory.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import com.siping.service.inventory.service.InventoryRestService;
import com.siping.service.purchase.service.PurchaseOrderRestService;
import com.siping.smartone.inventory.request.ChangeStatusParam;
import com.siping.smartone.inventory.request.InventoryReceiptBatchDeleteRequest;
import com.siping.smartone.inventory.request.InventoryReceiptItemRequest;
import com.siping.smartone.inventory.request.InventoryReceiptRequest;
import com.siping.smartone.inventory.request.InventoryReceitQueryParam;
import com.siping.smartone.inventory.request.ItemReceiptCountsRequest;
import com.siping.smartone.inventory.response.InventoryReceiptItemResponse;
import com.siping.smartone.inventory.response.InventoryReceiptResponse;
import com.siping.smartone.invoicing.inventory.inventory.request.AddInventoryRequest;
import com.siping.smartone.purchase.response.PurchaseOrderItemResponse;
import com.siping.smartone.purchase.response.PurchaseOrderResponse;
import com.siping.smartone.wms.request.ConfirmReceiptParam;

@Repository
public class InventoryReceiptRestRepository extends BillsRepository {
    @Autowired
    private PurchaseOrderRestService purchaseOrderRestService;
    @Autowired
    private InventoryRestService inventoryService;

    public Boolean add(InventoryReceiptRequest request) {
        /**
         * 系统维护；以MI开头，然后以1开始计数，系统自增。样式如：MI1、MI2……MIxxxx用户不可以修改。
         */
        String receiptNumber = "";
        // 如果receiptNumber为空即为新增，否则为修改，修改需物理删除以前数据
        if (!StringUtils.hasText(request.getReceiptNumber())) {
            receiptNumber = this.generateBillsNumber("inventory_receipt", "receipt_number");
        } else {
            receiptNumber = request.getReceiptNumber();
            // 关联删除库存收货单及收货单项
            int delete = jdbcAccessContext.execute("INVENTORYRECEIPT.SQL_DELETE_INVENTORYRECEIPT_AND_INVENTORYRECEIPTITEM_BYRECEIPTNUMBER", new Object[] { request.getReceiptNumber() });
            if (-1 == delete) {
                return Boolean.FALSE;
            }
        }
        Object[] addParams = new Object[] { receiptNumber, request.getInboundStorageId(), request.getSummary(), request.getIsDraft(), request.getTotalPrice(), request.getGiftPrice(),
                request.getBillsDate(), request.getAuditor(), request.getOwner(), request.getCreateBy() };
        int add = jdbcAccessContext.execute("INVENTORYRECEIPT.SQL_ADD_INVENTORYRECEIPT", addParams);
        if (-1 == add) {
            return Boolean.FALSE;
        }
        request.setReceiptNumber(receiptNumber);
        List<InventoryReceiptItemRequest> inventoryReceiptItemRequests = request.getInventoryReceiptItemRequests();
        List<Object[]> addParamsList = new LinkedList<Object[]>();
        for (InventoryReceiptItemRequest i : inventoryReceiptItemRequests) {
            Object[] obj = new Object[] { receiptNumber, i.getMaterialId(), i.getBatchNumber(), i.getProductionDate() == "" ? null : i.getProductionDate(), i.getCounts(), i.getPurchasePrice(),
                    i.getTotal(), i.getIsGift(), i.getRemark(), i.getCreateBy() };
            addParamsList.add(obj);
            // inventoryRequestList.add(buildAddInventoryRequest(request, i));
            // // 采购退货直接影响库存量
        }
        int[] adds = jdbcAccessContext.batchExecute("INVENTORYRECEIPT.SQL_ADD_INVENTORYRECEIPTITEM", addParamsList);
        if (Arrays.asList(adds).contains(-1)) {
            return Boolean.FALSE;
        }
        if (!request.getIsDraft()) {
            // checkPostPeriodDateTime(); // 检查是否在过账期间
            // changeInventoryAvailableAmount(inventoryRequestList);
        }
        return Boolean.TRUE;
    }

    public Boolean delete(InventoryReceiptBatchDeleteRequest request) {
        List<String> receiptNumbers = request.getReceiptNumbers();
        List<Object[]> deleteParams = new LinkedList<Object[]>();
        for (String str : receiptNumbers) {
            deleteParams.add(new Object[] { str });
        }
        int[] delete = jdbcAccessContext.batchExecute("INVENTORYRECEIPT.SQL_DELETE_INVENTORYRECEIPT", deleteParams);
        if (Arrays.asList(delete).contains(-1)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean edit(InventoryReceiptRequest request) {
        Object[] editParams = new Object[] { request.getInboundStorageId(), request.getSummary(), request.getIsDraft(), request.getTotalPrice(), request.getGiftPrice(), request.getAuditor(),
                request.getAuditDate(), request.getOwner(), request.getReceiptNumber() };
        int edit = jdbcAccessContext.execute("INVENTORYRECEIPT.SQL_UPDATE_INVENTORYRECEIPT", editParams);
        if (-1 == edit) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public PagingResponse<InventoryReceiptResponse> getList(InventoryReceitQueryParam request) {
        PagingResponse<InventoryReceiptResponse> pagingResponse = new PagingResponse<InventoryReceiptResponse>();
        long begin = (request.getPageNo() - 1) * request.getPageSize();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParams = new ArrayList<Object>();
        StringBuilder totalRecordsSql = new StringBuilder();
        List<InventoryReceiptResponse> response = jdbcAccessContext.findWithoutSqlManager(buildGetListSql(request, begin, queryParam, totalRecordsSql, totalParams),
            new RowMapper<InventoryReceiptResponse>() {
                @Override
                public InventoryReceiptResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                    return buildInventoryReceiptResponse(rs);
                }
            }, queryParam.toArray());
        int totalRecords = jdbcAccessContext.findIntegerWithoutSqlManager(totalRecordsSql.toString(), totalParams.toArray());
        pagingResponse.setRecords(response);
        pagingResponse.setTotalRecords(totalRecords);
        return pagingResponse;
    }

    protected InventoryReceiptResponse buildInventoryReceiptResponse(ResultSet rs) throws SQLException {
        InventoryReceiptResponse i = new InventoryReceiptResponse();
        i.setReceiptNumber(rs.getString("receiptNumber"));
        i.setBillsDate(DateUtils.formatDate(rs.getDate("billsDate"), "yyyy-MM-dd"));
        i.setInboundStorageId(rs.getInt("inboundStorageId"));
        i.setStorageName(rs.getString("inboundStorageName"));
        i.setCounts(rs.getDouble("counts"));
        i.setReceiptCounts(rs.getDouble("receiptCounts"));
        i.setTotalPrice(rs.getDouble("totalPrice"));
        i.setOwner(rs.getInt("owner"));
        i.setOwnerName(rs.getString("ownerName"));
        i.setSummary(rs.getString("summary"));
        i.setStatus(rs.getInt("status"));
        i.setConsigneeName(rs.getString("consigneeName"));
        i.setStartStation(rs.getString("startStation"));
        i.setEndStation(rs.getString("endStation"));
        i.setTransportationType(rs.getString("transportationType"));
        return i;
    }

    private String buildGetListSql(InventoryReceitQueryParam request, long begin, List<Object> queryParam, StringBuilder total, List<Object> totalParams) {
        Integer pageSize = request.getPageSize();
        String keyWords = request.getKeywords();
        String startDate = request.getStartDate();
        String endDate = request.getEndDate();
        Boolean isDraft = request.getIsDraft();
        total
            .append("SELECT COUNT(DISTINCT i.receipt_number) FROM inventory_receipt i LEFT JOIN inventory_receipt_item inv ON i.receipt_number = inv.receipt_number LEFT JOIN material m ON inv.material_id = m.id LEFT JOIN STORAGE s ON s.id = i.inbound_storage_id LEFT JOIN usr u ON u.id = i.OWNER LEFT JOIN usr u1 ON u1.id = i.consignee WHERE 1 = 1 ");
        StringBuilder sql = new StringBuilder(
            " SELECT son.receiptNumber,son.inboundStorageId,son.startStation,son.endStation,son.transportationType,son.inboundStorageName,son.summary,son.isDraft,son.totalPrice,son.giftPrice,son.billsDate,son.createDate,son.createBy,son.auditor,son.auditDate,son.owner, son.status,son.ownerName,son.nickname,son.consigneeName,son.materialId,SUM(son.counts) counts,SUM(son.receiptCounts) receiptCounts FROM(SELECT i.receipt_number receiptNumber,i.inbound_storage_id inboundStorageId,i.start_station startStation,i.end_station endStation,i.transportation_type transportationType,s.storage_name inboundStorageName,i.summary,i.is_draft isDraft,i.total_price totalPrice,i.gift_price giftPrice,i.bills_date billsDate,i.create_date createDate,i.create_by createBy,i.auditor,i.audit_date auditDate,i.OWNER,i.STATUS,u.user_name ownerName,u.nickname,u1.nickname consigneeName,inv.material_id materialId,inv.counts,SUM(IF (inv.status = 1,inv.receipt_counts,0)) receiptCounts FROM inventory_receipt i LEFT JOIN inventory_receipt_item inv ON i.receipt_number = inv.receipt_number LEFT JOIN material m ON inv.material_id = m.id LEFT JOIN STORAGE s ON s.id = i.inbound_storage_id LEFT JOIN usr u ON u.id = i.OWNER LEFT JOIN usr u1 ON u1.id = i.consignee WHERE 1 = 1 ");
        if (StringUtils.hasText(keyWords)) {
            sql.append(" and (i.receipt_number like CONCAT('%',?,'%') or m.material_name like CONCAT('%',?,'%') or i.auditor like CONCAT('%',?,'%') or u1.nickname like CONCAT('%',?,'%'))");
            total.append(" and (i.receipt_number like CONCAT('%',?,'%') or m.material_name like CONCAT('%',?,'%') or i.auditor like CONCAT('%',?,'%') or u1.nickname like CONCAT('%',?,'%'))");
            queryParam.add(keyWords);
            queryParam.add(keyWords);
            queryParam.add(keyWords);
            queryParam.add(keyWords);
        }
        if (StringUtils.hasText(startDate)) {
            sql.append(" and Date(i.bills_date) >= ? ");
            total.append(" and Date(i.bills_date) >= ? ");
            queryParam.add(startDate);
        }
        if (StringUtils.hasText(endDate)) {
            sql.append(" and Date(i.bills_date) <= ? ");
            total.append(" and Date(i.bills_date) <= ? ");
            queryParam.add(endDate);
        }
        if (null != isDraft) {
            sql.append(" and i.is_draft = ? ");
            total.append(" and i.is_draft = ? ");
            queryParam.add(isDraft);
        }
        if (100 != request.getStatus()) {
            sql.append(" and i.status = ? ");
            total.append(" and i.status = ? ");
            queryParam.add(request.getStatus());
        }
        sql.append(" GROUP BY i.receipt_number,inv.material_id,row_number) son GROUP BY son.receiptNumber ORDER BY son.createDate DESC ");
        totalParams.addAll(queryParam);
        queryParam.add(begin);
        queryParam.add(pageSize);
        sql.append(" limit ?,? ");
        return sql.toString();
    }

    public InventoryReceiptResponse get(String receiptNumber) {
        InventoryReceiptResponse response = jdbcAccessContext.findUniqueResult("INVENTORYRECEIPT.SQL_GET_INVENTORYRECEIPT", new RowMapper<InventoryReceiptResponse>() {
            @Override
            public InventoryReceiptResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildGetInventoryReceiptResponse(rs);
            }
        }, new Object[] { receiptNumber });
        List<InventoryReceiptItemResponse> list = jdbcAccessContext.find("INVENTORYRECEIPT.SQL_GET_INVENTORYITEMRECEIPT", new RowMapper<InventoryReceiptItemResponse>() {
            @Override
            public InventoryReceiptItemResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildInventoryReceiptItemResponse(rs);
            }
        }, new Object[] { response.getReceiptNumber() });
        response.setInventoryReceiptItemResponses(list);
        return response;
    }

    private InventoryReceiptItemResponse buildInventoryReceiptItemResponse(ResultSet rs) throws SQLException {
        InventoryReceiptItemResponse i = new InventoryReceiptItemResponse();
        i.setMaterialId(rs.getInt("materialId"));
        i.setRowNumber(rs.getInt("rowNumber"));
        i.setRemark(rs.getString("remark"));
        i.setSpecificationsModel(rs.getString("specificationsModel"));
        i.setBrand(rs.getString("brand"));
        i.setSeason(rs.getString("season"));
        i.setBarcode(rs.getString("barcode"));
        i.setBatchNumber(rs.getString("batchNumber"));
        i.setProductionDate(null == rs.getDate("productionDate") ? "" : DateUtils.formatDate(rs.getDate("productionDate"), "yyyy-MM-dd"));
        i.setExpirationDate(null == rs.getDate("expirationDate") ? "" : DateUtils.formatDate(rs.getDate("expirationDate"), "yyyy-MM-dd"));
        i.setUnitName(rs.getString("unitName"));
        i.setCounts(rs.getDouble("counts"));
        i.setReceiptCounts(rs.getDouble("receiptCounts"));
        i.setTotal(rs.getDouble("total"));
        i.setIsGift(rs.getBoolean("isGift"));
        i.setMaterialName(rs.getString("materialName"));
        i.setPurchasePrice(rs.getDouble("purchasePrice"));
        i.setIsBatch(rs.getBoolean("isBatch"));
        i.setMaterialNo(rs.getString("materialNo"));
        i.setStatus(rs.getInt("status"));
        return i;
    }

    public InventoryReceiptResponse buildGetInventoryReceiptResponse(ResultSet rs) throws SQLException {
        InventoryReceiptResponse i = new InventoryReceiptResponse();
        i.setReceiptNumber(rs.getString("receiptNumber"));
        i.setBillsDate(DateUtils.formatDate(rs.getDate("billsDate"), "yyyy-MM-dd"));
        i.setInboundStorageId(rs.getInt("inboundStorageId"));
        i.setStorageName(rs.getString("storageName"));
        i.setTotalPrice(rs.getDouble("totalPrice"));
        i.setGiftPrice(rs.getDouble("giftPrice"));
        i.setOwner(rs.getInt("owner"));
        i.setOwnerName(rs.getString("ownerName"));
        i.setSummary(rs.getString("summary"));
        i.setAuditor(rs.getInt("auditor"));
        i.setFromBillsNo(rs.getString("fromBillsNo"));
        i.setStatus(rs.getInt("status"));
        i.setConsigneeName(rs.getString("consigneeName"));
        return i;
    }

    public InventoryReceiptResponse buildInventoryReceiptDetailsResponse(ResultSet rs) throws SQLException {
        InventoryReceiptResponse i = new InventoryReceiptResponse();
        i.setReceiptNumber(rs.getString("receiptNumber"));
        i.setBillsDate(DateUtils.formatDate(rs.getDate("billsDate"), "yyyy-MM-dd"));
        i.setInboundStorageId(rs.getInt("inboundStorageId"));
        i.setStorageName(rs.getString("storageName"));
        i.setCounts(rs.getDouble("counts"));
        i.setTotalPrice(rs.getDouble("totalPrice"));
        i.setGiftPrice(rs.getDouble("giftPrice"));
        i.setOwner(rs.getInt("owner"));
        i.setSummary(rs.getString("summary"));
        i.setAuditor(rs.getInt("auditor"));
        return i;
    }

    public Boolean confirmReceipt(ConfirmReceiptParam request) {
        PurchaseOrderResponse response = purchaseOrderRestService.get(request.getFromBillsNo()); // 查出采购订单
        String purchaseReceiptNumber = generateBillsNumber("purchase_receipt", "receipt_number");
        List<Object[]> addParamsList = new ArrayList<Object[]>(); // 添加采购收货单项参数
        List<Object[]> combineParamsList = new ArrayList<Object[]>(); // 合并项参数
        List<Object[]> deleteParamsList = new ArrayList<Object[]>(); // 删除被合并项参数
        List<Object[]> updateInventoryItemStatusParams = new ArrayList<Object[]>(); // 修改收货单项状态为1已确认
        List<Object[]> updatePurchaseOrderReciptCounts = new ArrayList<Object[]>(); // 反写采购订单收货数量
        Iterator<ItemReceiptCountsRequest> items = request.getItemReceiptCounts().iterator();
        Double totalAmount = new Double(0);
        addOperationLog("确认收货", "确认收货，单据编号：[{0}],请求的ip为：[" + request.getIp() + "]，mac为：[" + request.getMac() + "]", purchaseReceiptNumber, request.getLoginId() + "");
        while (items.hasNext()) {
            ItemReceiptCountsRequest i = items.next();
            PurchaseOrderItemResponse p = jdbcAccessContext.findUniqueResult("PURCHASEORDER.SQL_SELECT_PURCHASEORDERITEMS_BY_ROWNUMBER", new RowMapper<PurchaseOrderItemResponse>() {
                @Override
                public PurchaseOrderItemResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                    return buildPurchaseOrderItemResponse(rs);
                }
            }, new Object[] { request.getFromBillsNo(), i.getRowNumber() });
            Double afterDiscount = i.getReceiptCounts() * p.getPurchasePrice() * p.getDiscountRate() / 100;
            Double tax = afterDiscount * p.getTaxRate() / 100;
            Double total = afterDiscount + tax;
            addParamsList.add(new Object[] { purchaseReceiptNumber, p.getMaterialId(), i.getReceiptCounts(), p.getPurchasePrice(), p.getDiscountRate(), afterDiscount, p.getTaxRate(), tax,
                    i.getBatchNumber(), i.getProductionDate(), null, total, p.getIsGift(), p.getRemark(), null });
            totalAmount += total;
            changeInventory(request, response, purchaseReceiptNumber, p, i);
            updatePurchaseOrderReciptCounts.add(new Object[] { i.getReceiptCounts(), i.getRowNumber(), request.getFromBillsNo() });
            // 合并商品、批次号、生产日期相同的项
            int isExist = jdbcAccessContext.findInteger("INVENTORYRECEIPT.SQL_ITEM_IS_EXIST", new Object[] { i.getBatchNumber(), i.getProductionDate(), i.getRowNumber(), request.getReceiptNumber() });
            if (isExist == 1) {
                combineParamsList.add(new Object[] { i.getReceiptCounts(), i.getBatchNumber(), i.getProductionDate(), i.getRowNumber(), request.getReceiptNumber() });
                deleteParamsList.add(new Object[] { i.getBatchNumber(), i.getProductionDate(), i.getRowNumber(), request.getReceiptNumber() });
            } else {
                updateInventoryItemStatusParams.add(new Object[] { i.getBatchNumber(), i.getProductionDate(), i.getRowNumber(), request.getReceiptNumber() });
            }
        }
        int counts = jdbcAccessContext.findInteger("INVENTORYRECEIPT.SQL_ITEM_IS_USED", new Object[] { request.getFromBillsNo() }); // 查询该单据是否生成过采购收货单，优惠金额和赠品只带人一次
        if (counts > 0) {
            response.setGiftAmount(0d);
            response.setDiscountAmount(0d);
        }
        Object[] addTitleParams = new Object[] { purchaseReceiptNumber, request.getReceiptNumber(), response.getSupplierId(), response.getBillDate(), 0, response.getSummary(), response.getIsDraft(),
                totalAmount, response.getGiftAmount(), response.getDiscountAmount(), totalAmount - response.getDiscountAmount(), response.getCreateBy(), response.getOwner() };
        try {
            jdbcAccessContext.execute("PURCHASERECEIPT.SQL_ADD_PURCHASERECEIPT", addTitleParams);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessProcessException("新增采购收货单失败");
        }
        int[] adds = jdbcAccessContext.batchExecute("PURCHASERECEIPT.SQL_ADD_PURCHASERECEIPTITEM", addParamsList); // 插入表体数据
        if (Arrays.asList(adds).contains(-1)) {
            throw new BusinessProcessException("插入采购收货单商品信息出错!");
        }
        int update = jdbcAccessContext.execute("INVENTORYRECEIPT.SQL_UPDATE_STATUS", new Object[] { request.getLoginId(), 1, request.getReceiptNumber() });
        if (-1 == update) {
            throw new BusinessProcessException("修该收货单状态失败");
        }
        // 合并商品、批次号、生产日期相同的项
        int[] combines = jdbcAccessContext.batchExecute("INVENTORYRECEIPT.SQL_UPDATE_ITEM_RECEIPT_COUNTS", combineParamsList);
        if (Arrays.asList(combines).contains(-1)) {
            throw new BusinessProcessException("合并收货单项失败");
        }
        // 删除被合并的项
        int[] detetes = jdbcAccessContext.batchExecute("INVENTORYRECEIPT.SQL_DELETE_ITEM_BY_COMBINES", deleteParamsList);
        if (Arrays.asList(detetes).contains(-1)) {
            throw new BusinessProcessException("删除被合并的项失败");
        }
        // 修改收货单项状态为:1已确认
        int[] updateItems = jdbcAccessContext.batchExecute("INVENTORYRECEIPT.SQL_UPDATE_ITEM_STATUS", updateInventoryItemStatusParams);
        if (Arrays.asList(updateItems).contains(-1)) {
            throw new BusinessProcessException("修改收货单项状态失败");
        }
        // 反写收货数量
        int[] updates = jdbcAccessContext.batchExecute("PURCHASEORDER.SQL_UPDATE_RECEIPT_COUNTS", updatePurchaseOrderReciptCounts);
        if (Arrays.asList(updates).contains(-1)) {
            throw new BusinessProcessException("修改采购收货数量失败");
        }
        return Boolean.TRUE;
    }

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
        return response;
    }

    private void changeInventory(ConfirmReceiptParam request, PurchaseOrderResponse response, String purchaseReceiptNumber, PurchaseOrderItemResponse p, ItemReceiptCountsRequest i) {
        AddInventoryRequest inventoryRequest = new AddInventoryRequest();
        inventoryRequest.setMaterialId(p.getMaterialId());
        inventoryRequest.setStorageId(0); // 仓库id为0，商品入到虚拟仓
        inventoryRequest.setStorageLocationId(null); // 虚拟仓没有库位
        inventoryRequest.setIsInbound(true);
        inventoryRequest.setBillsNo(purchaseReceiptNumber);
        inventoryRequest.setBillsDate(response.getBillDate());
        inventoryRequest.setCounts(i.getReceiptCounts());
        inventoryRequest.setTotal(i.getReceiptCounts() * p.getPurchasePrice() * p.getDiscountRate() / 100);
        inventoryRequest.setPurchasePrice(p.getPurchasePrice() * p.getDiscountRate() / 100);
        inventoryRequest.setSellPrice(null);
        inventoryRequest.setCreateBy(request.getLoginId());
        inventoryRequest.setBatchNumber(i.getBatchNumber());
        inventoryRequest.setProductionDate(i.getProductionDate());
        if (null != i.getProductionDate() && null != p.getExpirationDate()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date parse = dateFormat.parse(i.getProductionDate());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(parse);
                calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + p.getExpirationDate());
                inventoryRequest.setExpirationDate(dateFormat.format(calendar.getTime()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        inventoryService.add(inventoryRequest);

    }

    public Boolean changeStatus(ChangeStatusParam request) {
        int update = jdbcAccessContext.execute("INVENTORYRECEIPT.SQL_UPDATE_STATUS", new Object[] { request.getLoginId(), 2, request.getOrderNumber() });
        if (-1 == update) {
            throw new BusinessProcessException("修该收货单状态失败");
        }
        return Boolean.TRUE;
    }
}
