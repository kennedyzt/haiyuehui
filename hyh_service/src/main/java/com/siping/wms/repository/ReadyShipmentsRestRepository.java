package com.siping.wms.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.wms.ReadyShipmentsItemBase;
import com.siping.smartone.wms.request.ESaleOrderItemRequest;
import com.siping.smartone.wms.request.ESaleOrderRequest;
import com.siping.smartone.wms.request.OrderPickQueryParam;
import com.siping.smartone.wms.request.ReadyShipmentsItemRequest;
import com.siping.smartone.wms.request.ReadyShipmentsRequest;
import com.siping.smartone.wms.response.MaterialBatchCountsResponse;
import com.siping.smartone.wms.response.OrderPickResponse;
import com.siping.smartone.wms.response.ReadyShipmentsItemResponse;
import com.siping.smartone.wms.response.ReadyShipmentsResponse;
import com.siping.smartone.wms.response.StorageLocationResponse;

@Repository
public class ReadyShipmentsRestRepository extends BillsRepository {
    public synchronized Boolean add(ESaleOrderRequest request) {
        Map<Integer, List<ReadyShipmentsItemRequest>> storageMap = new LinkedHashMap<>(); // 分仓库
        List<Object[]> promisedLocationAmountPram = new LinkedList<>(); // 修改库存库位已承诺数量参数
        List<Object[]> promisedAmountPram = new LinkedList<>(); // 修改仓库已承诺数量参数
        for (final ESaleOrderItemRequest eItem : request.getItems()) {
            promisedAmountPram.add(new Object[] { eItem.getMaterialId(), eItem.getMaterialId() });
            /** 根据物料id 查询material_batch中批次、剩余数量，并按生产日期排序升序排列 */
            List<MaterialBatchCountsResponse> materialBatchCountsList = jdbcAccessContext.find("READYSHIPMENTS.SQL_GET_BATCH_COUNTS", new RowMapper<MaterialBatchCountsResponse>() {
                @Override
                public MaterialBatchCountsResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                    return buildMaterialBatchCountsResponse(rs, eItem);
                }
            }, new Object[] { eItem.getMaterialId() });
            /** 根据该商品出库数量分配批次 */
            Double needCounts = eItem.getCounts(); // 需出库数量
            List<ReadyShipmentsItemRequest> readyShipmentsItemRequests = new LinkedList<ReadyShipmentsItemRequest>(); // 添加预发货单商品参数
            for (MaterialBatchCountsResponse materialBatchCountsResponse : materialBatchCountsList) {
                if (materialBatchCountsResponse.getCounts() - needCounts >= 0) {
                    ReadyShipmentsItemRequest item = buildReadyShipmentsItemRequest(materialBatchCountsResponse, eItem, needCounts);
                    readyShipmentsItemRequests.add(item);
                    promisedLocationAmountPram.add(new Object[] { needCounts, materialBatchCountsResponse.getInventoryId() });
                    needCounts -= needCounts;
                    break;
                } else {
                    ReadyShipmentsItemRequest item = buildReadyShipmentsItemRequest(materialBatchCountsResponse, eItem, materialBatchCountsResponse.getCounts());
                    readyShipmentsItemRequests.add(item);
                    promisedLocationAmountPram.add(new Object[] { materialBatchCountsResponse.getCounts(), materialBatchCountsResponse.getInventoryId() });
                    needCounts -= materialBatchCountsResponse.getCounts();
                }
            }
            if (0.0 != needCounts) {
                throw new BusinessProcessException("商品库存不足");
            }
            // 分仓库
            for (int i = 0; i < readyShipmentsItemRequests.size(); i++) {
                ReadyShipmentsItemRequest item = readyShipmentsItemRequests.get(i);
                Integer storageId = item.getStorageId();
                if (storageMap.containsKey(storageId)) {
                    storageMap.get(storageId).add(item);
                } else {
                    List<ReadyShipmentsItemRequest> list = new LinkedList<>();
                    list.add(item);
                    storageMap.put(storageId, list);
                }
            }
        }
        for (Integer storageId : storageMap.keySet()) {
            String orderNumber = generateBillsNumber("ready_shipments", "order_number");
            List<Object[]> storageItem = new LinkedList<>();
            List<ReadyShipmentsItemRequest> list = storageMap.get(storageId);
            for (ReadyShipmentsItemRequest r : list) {
                storageItem.add(new Object[] { orderNumber, r.getMaterialId(), r.getBatchNumber(), r.getProductionDate(), r.getDueDate(), r.getCounts(), r.getPurchasePrice(), r.getSellPrice(),
                        r.getDiscount(), r.getAfterDiscount(), r.getTaxRate(), r.getTax(), r.getTotal(), r.getIsGift(), r.getStorageLocationId() });
            }
            // 插入预发货单表头信息
            try {
                int add = jdbcAccessContext.execute("READYSHIPMENTS.ADD_READY_SHIPMENTS",
                    new Object[] { orderNumber, request.getTotalPrice(), request.getGiftPrice(), request.getFavorablePrice(), request.getGatheringPrice(), request.getEcOrderNumber(), storageId,
                            request.getBillsDate(), request.getConsigneeName(), request.getConsigneeAddress(), request.getConsignessPhone(), request.getConsignessPostcode(), request.getSummary(),
                            request.getShopNumber(), request.getPartnerId()});
                if (-1 == add) {
                    throw new BusinessProcessException("插入预发货单商品信息失败");
                }
            } catch (DuplicateKeyException e) {
                throw new DuplicateKeyException("原始订单号重复");
            }
            // 插入预发货单商品信息
            int[] adds = jdbcAccessContext.batchExecute("READYSHIPMENTS.ADD_READY_SHIPMENTS_ITEM", storageItem);
            if (Arrays.asList(adds).contains(-1)) {
                throw new BusinessProcessException("插入预发货单商品信息失败");
            }
        }
        // 修改库存库位已承诺数量
        int[] updates = jdbcAccessContext.batchExecute("READYSHIPMENTS.UPDATE_PROMISED_LOCATION_AMOUNT", promisedLocationAmountPram);
        if (Arrays.asList(updates).contains(-1)) {
            throw new BusinessProcessException("修改库存已承诺数失败");
        }
        return Boolean.TRUE;
    }

    private ReadyShipmentsItemRequest buildReadyShipmentsItemRequest(MaterialBatchCountsResponse materialBatchCountsResponse, ESaleOrderItemRequest eItem, Double counts) {
        ReadyShipmentsItemRequest item = new ReadyShipmentsItemRequest();
        Double discount = eItem.getDiscount() == null ? 100 : eItem.getDiscount();
        Double afterDiscount = eItem.getTotal() == null ? 0.0 : eItem.getTotal();
        Double tax = eItem.getTax() == null ? 0.0 : eItem.getTax();
        Double total = afterDiscount + tax;
        if(0.0 != afterDiscount){
            BigDecimal b = new BigDecimal(tax / afterDiscount * 100);
            Double taxRate = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            item.setTaxRate(taxRate);
        }else{
            item.setTaxRate(0.0);
        }
        item.setMaterialId(materialBatchCountsResponse.getMaterialId());
        item.setBatchNumber(materialBatchCountsResponse.getBatchNumber());
        item.setProductionDate(materialBatchCountsResponse.getProductionDate());
        item.setDueDate(materialBatchCountsResponse.getDueDate());
        item.setPurchasePrice(materialBatchCountsResponse.getPurchasePrice());
        item.setSellPrice(eItem.getSellPrice());
        item.setDiscount(discount);
        item.setCounts(counts);
        item.setAfterDiscount(afterDiscount);
        item.setTotal(total);
        item.setTax(tax);
        item.setIsGift(eItem.getIsGift());
        item.setStorageLocationId(materialBatchCountsResponse.getStorageLocationId());
        item.setStorageId(materialBatchCountsResponse.getStorageId());
        return item;
    }

    private MaterialBatchCountsResponse buildMaterialBatchCountsResponse(ResultSet rs, ESaleOrderItemRequest eItem) throws SQLException {
        MaterialBatchCountsResponse response = new MaterialBatchCountsResponse();
        response.setMaterialId(rs.getInt("material_id"));
        response.setBatchNumber(rs.getString("batch_number"));
        response.setCounts(rs.getDouble("counts"));
        response.setStorageLocationId(rs.getInt("storage_location_id"));
        response.setProductionDate(DateUtils.formatDate(rs.getDate("production_date"), "yyyy-MM-dd"));
        response.setDueDate(DateUtils.formatDate(rs.getDate("expiration_date"), "yyyy-MM-dd"));
        response.setStorageId(rs.getInt("storage_id"));
        response.setInventoryId(rs.getInt("inventory_id"));
        response.setPurchasePrice(rs.getDouble("price"));
        response.setSalePrice(eItem.getSellPrice());
        response.setPromisedAmount(rs.getDouble("promised_amount"));
        return response;
    }

    public ReadyShipmentsResponse get(String orderNumber) {// 获取单个订单
        ReadyShipmentsResponse response = jdbcAccessContext.findUniqueResult("READYSHIPMENTS.SQL_SELECT_READY_SHIPMENTS_BYID", new RowMapper<ReadyShipmentsResponse>() {
            @Override
            public ReadyShipmentsResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildReadyShipmentsResponse(rs);
            }
        }, new Object[] { orderNumber });
        List<ReadyShipmentsItemResponse> items = jdbcAccessContext.find("READYSHIPMENTS.SQL_SELECT_READY_SHIPMENTS_ITEMS_BYID", new RowMapper<ReadyShipmentsItemResponse>() {
            @Override
            public ReadyShipmentsItemResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildReadyShipmentsItemResponse(rs);
            }
        }, new Object[] { orderNumber });
        response.setItems(items);
        return response;
    }

    protected ReadyShipmentsItemResponse buildReadyShipmentsItemResponse(ResultSet rs) throws SQLException {

        ReadyShipmentsItemResponse response = new ReadyShipmentsItemResponse();
        response.setStorageLocationId(rs.getInt("storage_location_id"));
        try {
            response.setBatchNumber(rs.getString("batch_number"));
        } catch (Exception e) {
        }
        response.setMaterialId(rs.getInt("material_id"));
        try {
            response.setBarcode(rs.getString("barcode"));
        } catch (Exception e) {
        }
        response.setMaterialNo(rs.getString("material_no"));
        response.setMaterialName(rs.getString("material_name"));
        response.setUnitName(rs.getString("unit_name"));
        response.setCounts(rs.getDouble("counts"));
        response.setOrderNumber(rs.getString("order_number"));
        try {
            response.setConfirmAmount(rs.getString("confirm_amount"));
        } catch (Exception e) {
        }
        try {
            response.setId(rs.getInt("id"));
        } catch (Exception e) {
        }
        try {
            response.setSpecificationsModel(rs.getString("specifications_model"));
        } catch (Exception e) {
        }
        try {
            response.setLocationName(rs.getString("location_name"));
        } catch (Exception e) {
        }
        try {
            response.setPurchasePrice(rs.getDouble("purchase_price"));
        } catch (Exception e) {
        }
        try {
            response.setSellPrice(rs.getDouble("sell_price"));
        } catch (Exception e) {
        }
        try {
            response.setDiscount(rs.getDouble("discount"));
        } catch (Exception e) {
        }
        try {
            response.setAfterDiscount(rs.getDouble("after_discount"));
        } catch (Exception e) {
        }
        try {
            response.setTaxRate(rs.getDouble("tax_rate"));
        } catch (Exception e) {
        }
        try {
            response.setTax(rs.getDouble("tax"));
        } catch (Exception e) {
        }
        try {
            response.setTotal(rs.getDouble("total"));
        } catch (Exception e) {
        }
        try {
            response.setIsGift(rs.getBoolean("is_gift"));
        } catch (Exception e) {
        }
        try {
            response.setProductionDate(DateUtils.formatDate(rs.getDate("production_date"), "yyyy-MM-dd"));
        } catch (Exception e) {
        }
        try {
            response.setDueDate(DateUtils.formatDate(rs.getDate("due_date"), "yyyy-MM-dd"));
        } catch (Exception e) {
        }
        try {
            rs.getDouble("wight");
        } catch (Exception e) {
        }
        return response;
    }

    public PagingResponse<ReadyShipmentsResponse> getList(CommonBillsRequest request) {// 获取订单列表
        PagingResponse<ReadyShipmentsResponse> response = new PagingResponse<ReadyShipmentsResponse>();
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
        queryListSql.append(" order by r.create_date desc ");
        if (null != request.getPageNo() && null != request.getPageSize()) {
            queryListSql.append(" limit ?,? ");
            queryList.add((request.getPageNo() - 1) * request.getPageSize());
            queryList.add(request.getPageSize());
        }
        List<ReadyShipmentsResponse> list = jdbcAccessContext.findWithoutSqlManager(queryListSql.toString(), new RowMapper<ReadyShipmentsResponse>() {
            @Override
            public ReadyShipmentsResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildReadyShipmentsResponse(rs);
            }
        }, queryList.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    private ReadyShipmentsResponse buildReadyShipmentsResponse(ResultSet rs) throws SQLException {
        ReadyShipmentsResponse response = new ReadyShipmentsResponse();
        response.setOrderNumber(rs.getString("order_number"));
        try {
            response.setFromBillsNo(rs.getString("from_bills_no"));
        } catch (Exception e) {
        }
        response.setBillsDate(DateUtils.formatDate(rs.getDate("bills_date"), "yyyy-MM-dd"));
        response.setShipmentsDate(DateUtils.formatDate(rs.getDate("shipments_date"), "yyyy-MM-dd"));
        response.setConsigneeName(rs.getString("consignee_name"));
        response.setConsignessPhone(rs.getString("consigness_phone"));
        response.setConsigneeAddress(rs.getString("consignee_address"));
        response.setConsignessPostcode(rs.getString("consigness_postcode"));
        response.setSummary(rs.getString("summary"));
        response.setStatus(rs.getInt("status"));
        try {
            response.setTotalPrice(rs.getDouble("total_price"));
        } catch (Exception e) {
        }
        try {
            response.setGiftPrice(rs.getDouble("gift_price"));
        } catch (Exception e) {
        }
        try {
            response.setFavorablePrice(rs.getDouble("favorable_price"));
        } catch (Exception e) {
        }
        try {
            response.setGatheringPrice(rs.getDouble("gathering_price"));
        } catch (Exception e) {
        }
        try {
            response.setAuditNumber(rs.getString("auditNumber"));
        } catch (Exception e) {
        }
        try {
            response.setNoAuditNumber(rs.getString("noAuditNumber"));
        } catch (Exception e) {
        }
        try {
            response.setStorageName(rs.getString("storage_name"));
        } catch (Exception e) {
        }
        try {
            response.setFromBillsNo(rs.getString("from_bills_no"));
        } catch (Exception e) {
        }
        try {
            response.setStorageId(rs.getInt("storage_id"));
        } catch (Exception e) {
        }
        try {
            response.setShopName(rs.getString("shop_name"));
        } catch (Exception e) {
        }
        try {
            response.setPartnerName(rs.getString("partner_name"));
        } catch (Exception e) {
        }
        try {
            response.setTrackingNo(rs.getString("tracking_no"));
        } catch (Exception e) {
        }
        try {
            response.setCustomsCode(rs.getString("customs_code"));
        } catch (Exception e) {
        }
        try {
            response.setCounts(rs.getDouble("counts"));
        } catch (Exception e) {
        }
        try {
            response.setWeight(rs.getDouble("weight"));
        } catch (Exception e) {
        }
        try {
            response.setMaterialsWeight(rs.getDouble("materialsWeight"));
        } catch (Exception e) {
        }
        return response;
    }

    private void loadDynamicQuerySellOrderSql(CommonBillsRequest request, List<Object> queryList, StringBuilder queryListSql, StringBuilder queryTotalSql) {
        queryListSql.append(
            "SELECT r.order_number, r.from_bills_no, r.bills_date, r.partner_id, r.summary, r.total_price, r.gift_price, r.favorable_price, r.gathering_price, r.create_date, r.create_by, r.auditor, "
                + "r.audit_date, r.owner, r.shipments_date, r.consignee_name, r.consignee_address, r.consigness_phone, r.consigness_postcode, r.status,r.storage_id,r.weight,s.storage_name from ready_shipments r left join storage s on s.id = r.storage_id where 1=1");
        queryTotalSql.append(" SELECT count(*) from ready_shipments r where 1=1");
        if (StringUtils.hasText(request.getStartDate())) {
            queryListSql.append(" AND r.bills_date >= '").append(parseDate(request.getStartDate(), Boolean.FALSE)).append("'");
            queryTotalSql.append(" AND r.bills_date >= '").append(parseDate(request.getStartDate(), Boolean.FALSE)).append("'");
        }
        if (StringUtils.hasText(request.getEndDate())) {
            queryListSql.append("  AND r.bills_date <= '").append(parseDate(request.getEndDate(), Boolean.TRUE)).append("'");
            queryTotalSql.append("  AND r.bills_date <= '").append(parseDate(request.getEndDate(), Boolean.TRUE)).append("'");
        }
        if (100 != request.getStatus()) {
            queryListSql.append(" AND r.status = ? ");
            queryTotalSql.append(" AND r.status = ? ");
            queryList.add(request.getStatus());
        }
        if (StringUtils.hasText(request.getKeywords())) {
            queryListSql.append(" AND ( ").append(" r.order_number like CONCAT('%',?,'%') ");
            queryTotalSql.append(" AND ( ").append(" r.order_number like CONCAT('%',?,'%') ");
            queryList.add(request.getKeywords());
            queryListSql.append(") ");
            queryTotalSql.append(") ");
        }
    }

    public OrderPickResponse get(OrderPickQueryParam request) {
        OrderPickResponse response = new OrderPickResponse();
        List<StorageLocationResponse> storageLocationList = new LinkedList<>();
        Object[] queryLocationIds = new Object[request.getOrderNumbers().size()];
        StringBuilder queryItemsSql = new StringBuilder();
        List<StorageLocationResponse> storageLocation = jdbcAccessContext.findWithoutSqlManager(buildOrderPickQuerySql(request, queryLocationIds, queryItemsSql),
            new RowMapper<StorageLocationResponse>() {
                @Override
                public StorageLocationResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                    StorageLocationResponse s = new StorageLocationResponse();
                    s.setStorageLocationId(rs.getInt("storage_location_id"));
                    s.setStorageLocationName(rs.getString("location_name"));
                    return s;
                }
            }, queryLocationIds);
        Object[] queryItems = Arrays.copyOf(queryLocationIds, queryLocationIds.length + 1);
        for (int i = 0; i < storageLocation.size(); i++) {
            queryItems[queryItems.length - 1] = (Object) storageLocation.get(i).getStorageLocationId();
            List<ReadyShipmentsItemResponse> items = jdbcAccessContext.findWithoutSqlManager(queryItemsSql.toString(), new RowMapper<ReadyShipmentsItemResponse>() {
                @Override
                public ReadyShipmentsItemResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                    return buildReadyShipmentsItemResponse(rs);
                }
            }, queryItems);
            StorageLocationResponse storageLocationResponse = new StorageLocationResponse();
            storageLocationResponse.setItems(items);
            storageLocationResponse.setStorageLocationName(storageLocation.get(i).getStorageLocationName());
            storageLocationList.add(storageLocationResponse);
        }
        // response.setStorageLocationList(storageLocationList);
        return response;
    }

    private String buildOrderPickQuerySql(OrderPickQueryParam request, Object[] queryParam, StringBuilder queryItemsSql) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT i.storage_location_id , s.location_name from ready_shipments_item i LEFT JOIN storage_location s ON s.id = i.storage_location_id where i.order_number in (");
        queryItemsSql.append("SELECT * from ready_shipments_item  i where i.order_number in (");
        for (int i = 0; i < request.getOrderNumbers().size(); i++) {
            queryParam[i] = request.getOrderNumbers().get(i);
            sql.append("? ,");
            queryItemsSql.append("? ,");
        }
        sql.deleteCharAt(sql.length() - 1);
        queryItemsSql.deleteCharAt(queryItemsSql.length() - 1);
        sql.append(") GROUP BY i.storage_location_id ");
        queryItemsSql.append(") and i.storage_location_id = ?");
        return sql.toString();
    }

    public Boolean confirmAuditByOrderNumber(String ordernumber) {
        int update = 0;
        update = jdbcAccessContext.execute("READYSHIPMENTS.SQL_UPDATE_READYSHIPMENTS_STATUS_FOR_AUDIT_BY_ORDERNUMBER", new Object[] { new Integer(4), ordernumber });
        if (-1 == update) {
            update = 0;
            throw new BusinessProcessException("更改发货单状态时出现错误");
        }
        update = jdbcAccessContext.execute("READYSHIPMENTS.SQL_UPDATE_READYSHIPMENTS_ITEM_CONFIRMNUMBER_FOR_AUDIT_BY_ORDERNUMBER", new Object[] { ordernumber });
        if (-1 == update) {
            update = 0;
            throw new BusinessProcessException("更改发货单条目确认数量时出现错误");
        }
        return true;
    }

    public Boolean changeReadyShipmentsStatus(Integer status, String orderNumber) throws Exception {// 这个方法不抛出异常
        int update = 0;
        update = jdbcAccessContext.execute("READYSHIPMENTS.SQL_UPDATE_READYSHIPMENTS_STATUS_FOR_AUDIT_BY_ORDERNUMBER", new Object[] { status, orderNumber });
        if (-1 == update) {
            throw new BusinessProcessException("修改状态失败！");
        } else {
            return true;
        }
    }

    public void changeReadyShipmentsWeight(Double weight, String orderNumber) throws Exception {
        int update = 0;
        update = jdbcAccessContext.execute("READYSHIPMENTS.SQL_UPDATE_READYSHIPMENTS_WEIGHT_FOR_PACK_BY_ORDERNUMBER", new Object[] { weight, orderNumber });
        if (-1 == update) {
            throw new BusinessProcessException("修改预发货单重量失败!");
        }
    }

    public Boolean tempSaveAudit(ReadyShipmentsRequest request) {
        int update = 0;
        update = jdbcAccessContext.execute("READYSHIPMENTS.SQL_UPDATE_READYSHIPMENTS_STATUS_FOR_AUDIT_BY_ORDERNUMBER", new Object[] { new Integer(3), request.getOrderNumber() });
        if (-1 == update) {
            update = 0;
            throw new BusinessProcessException("更改发货单为暂存时出现错误");
        }
        List<Object[]> auditItemListParams = new ArrayList<Object[]>();
        for (ReadyShipmentsItemBase item : request.getItems()) {
            Object[] itemParam = new Object[] { item.getConfirmAmount(), item.getId() };
            auditItemListParams.add(itemParam);
        }
        int[] itemUpdateFlags = jdbcAccessContext.batchExecute("READYSHIPMENTS.SQL_UPDATE_READYSHIPMENTS_ITEM_CONFIRMNUMBER_FOR_AUDIT_BY_ITEMID", auditItemListParams);
        if (Arrays.asList(itemUpdateFlags).contains(-1)) {
            throw new BusinessProcessException("修改发货单项暂存数量时出现错误");
        }
        return true;
    }

    public List<ReadyShipmentsResponse> getAllTempAuditOrder() {
        List<ReadyShipmentsResponse> responses = jdbcAccessContext.find("READYSHIPMENTS.SQL_SELECT_READY_SHIPMENTS_BY_STATUS", new RowMapper<ReadyShipmentsResponse>() {
            @Override
            public ReadyShipmentsResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildReadyShipmentsResponse(rs);
            }
        }, new Object[] { 3 });
        return responses;
    }

    public ReadyShipmentsResponse getbyTempAudit(String orderNumber) {
        ReadyShipmentsResponse response = jdbcAccessContext.findUniqueResult("READYSHIPMENTS.SQL_SELECT_READY_SHIPMENTS_BYID_WITH_EXTRA_INFO", new RowMapper<ReadyShipmentsResponse>() {
            @Override
            public ReadyShipmentsResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildReadyShipmentsResponse(rs);
            }
        }, new Object[] { orderNumber });
        List<ReadyShipmentsItemResponse> items = jdbcAccessContext.find("READYSHIPMENTS.SQL_SELECT_READY_SHIPMENTS_ITEMS_BYID", new RowMapper<ReadyShipmentsItemResponse>() {
            @Override
            public ReadyShipmentsItemResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildReadyShipmentsItemResponse(rs);
            }
        }, new Object[] { orderNumber });
        response.setItems(items);
        return response;
    }

    public PagingResponse<ReadyShipmentsResponse> printEMS(CommonBillsRequest request) {
        PagingResponse<ReadyShipmentsResponse> response = new PagingResponse<ReadyShipmentsResponse>();
        List<Object> queryList = new ArrayList<Object>();
        StringBuilder queryListSql = new StringBuilder();
        StringBuilder queryTotalSql = new StringBuilder();
        loadDynamicQueryPrintEMSSql(request, queryList, queryListSql, queryTotalSql);
        Integer total = 0;
        try {
            total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotalSql.toString(), queryList.toArray());
        } catch (Exception e) {
            e.printStackTrace();
            return response;
        }
        queryListSql.append(" order by r.create_date desc ");
        if (null != request.getPageNo() && null != request.getPageSize()) {
            queryListSql.append(" limit ?,? ");
            queryList.add((request.getPageNo() - 1) * request.getPageSize());
            queryList.add(request.getPageSize());
        }
        List<ReadyShipmentsResponse> list = jdbcAccessContext.findWithoutSqlManager(queryListSql.toString(), new RowMapper<ReadyShipmentsResponse>() {
            @Override
            public ReadyShipmentsResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildReadyShipmentsResponse(rs);
            }
        }, queryList.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    private void loadDynamicQueryPrintEMSSql(CommonBillsRequest request, List<Object> queryList, StringBuilder queryListSql, StringBuilder queryTotalSql) {
        queryListSql.append(
            "SELECT r.order_number, r.from_bills_no, r.bills_date, r.partner_id, r.summary, r.total_price, r.gift_price, r.favorable_price, r.gathering_price, r.create_date, r.create_by, r.auditor, "
                + "r.audit_date, r.owner, r.shipments_date, r.consignee_name, r.consignee_address, r.consigness_phone, r.consigness_postcode, r.print_status status,r.storage_id,s.storage_name from ready_shipments r left join storage s on s.id = r.storage_id where 1=1 ");
        queryTotalSql.append(" SELECT count(*) from ready_shipments r where 1=1");
        if (StringUtils.hasText(request.getStartDate())) {
            queryListSql.append(" AND r.bills_date >= '").append(parseDate(request.getStartDate(), Boolean.FALSE)).append("'");
            queryTotalSql.append(" AND r.bills_date >= '").append(parseDate(request.getStartDate(), Boolean.FALSE)).append("'");
        }
        if (StringUtils.hasText(request.getEndDate())) {
            queryListSql.append("  AND r.bills_date <= '").append(parseDate(request.getEndDate(), Boolean.TRUE)).append("'");
            queryTotalSql.append("  AND r.bills_date <= '").append(parseDate(request.getEndDate(), Boolean.TRUE)).append("'");
        }
        if (100 != request.getStatus()) {
            queryListSql.append(" AND r.print_status = ? ");
            queryTotalSql.append(" AND r.print_status = ? ");
            queryList.add(request.getStatus());
        }
        if (StringUtils.hasText(request.getKeywords())) {
            queryListSql.append(" AND ( ").append(" r.order_number like CONCAT('%',?,'%') ");
            queryTotalSql.append(" AND ( ").append(" r.order_number like CONCAT('%',?,'%') ");
            queryList.add(request.getKeywords());
            queryListSql.append(") ");
            queryTotalSql.append(") ");
        }
    }

    public Boolean updateStatus(String id, Integer status, Integer userId) {
        int update = jdbcAccessContext.execute("READYSHIPMENTS.SQL_UPDATE_PRINT_STATUS_BYID", new Object[] { status, id });
        if (-1 == update) {
            throw new BusinessProcessException("修改预发货单状态错误");
        }
        return true;
    }
}
