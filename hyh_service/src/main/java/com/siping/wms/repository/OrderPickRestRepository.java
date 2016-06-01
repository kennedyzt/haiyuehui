package com.siping.wms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.wms.request.OrderPickItemRequest;
import com.siping.smartone.wms.request.OrderPickQueryParam;
import com.siping.smartone.wms.response.OrderPickItemResponse;
import com.siping.smartone.wms.response.OrderPickResponse;
import com.siping.smartone.wms.response.ReadyShipmentsItemResponse;
import com.siping.smartone.wms.response.StorageLocationOrderPickItemResponse;

@Repository
public class OrderPickRestRepository extends BillsRepository {

    public Boolean add(OrderPickQueryParam request) {
        List<ReadyShipmentsItemResponse> items = new LinkedList<>();
        for (String orderNumber : request.getOrderNumbers()) {
            List<ReadyShipmentsItemResponse> item = jdbcAccessContext.findWithoutSqlManager(buildOrderPickQuerySql(), new RowMapper<ReadyShipmentsItemResponse>() {
                @Override
                public ReadyShipmentsItemResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                    return buildReadyShipmentsItemResponse(rs);
                }
            }, new Object[] { orderNumber });
            items.addAll(item);
        }
        // 按库位生成拣货单
        Set<Integer> storageLocationIdSet = new HashSet<>();
        Map<Integer, List<ReadyShipmentsItemResponse>> stotageLocationMap = new HashMap<>();
        for (ReadyShipmentsItemResponse r : items) {
            if (storageLocationIdSet.contains(r.getStorageLocationId())) {
                stotageLocationMap.get(r.getStorageLocationId()).add(r);
            } else {
                storageLocationIdSet.add(r.getStorageLocationId());
                List<ReadyShipmentsItemResponse> readyShipmentsItems = new LinkedList<>();
                readyShipmentsItems.add(r);
                stotageLocationMap.put(r.getStorageLocationId(), readyShipmentsItems);
            }
        }
        // 根据库位不同添加不同拣货单
        for (Integer storageLocationId : stotageLocationMap.keySet()) {
            String orderNumber = generateBillsNumber("order_pick", "order_number");
            List<ReadyShipmentsItemResponse> list = stotageLocationMap.get(storageLocationId);
            // 循环计算出SKU数和商品总数 ,得出拣货单详情参数 ,合并商品id和批次号相同的列
            List<OrderPickItemRequest> orderPickItemRequestList = new LinkedList<>();
            HashSet<Integer> materialIds = new HashSet<Integer>(); // 商品总数
            Double counts = new Double(0);
            for (ReadyShipmentsItemResponse r : list) {
                counts = buildOrderPickRequest(request, orderNumber, orderPickItemRequestList, materialIds, counts, r);
            }
            // 添加拣货单表体信息
            List<Object[]> addOrderPickBatchParam = new LinkedList<>();
            List<String> fromBillsNos = new LinkedList<>();
            for (OrderPickItemRequest item : orderPickItemRequestList) {
                fromBillsNos.add(item.getFromBillsNo());
                addOrderPickBatchParam.add(new Object[] { orderNumber, item.getFromBillsNo(), item.getEcOrderNumber(), item.getMaterialId(), item.getBatchNumber(), item.getProductionDate(),
                        item.getCounts(), item.getStorageLocationId(), request.getLoginId() });
            }
            int[] adds = jdbcAccessContext.batchExecute("ORDERPICK.SQL_ADD_ORDER_PICK_ITEM", addOrderPickBatchParam);
            if (Arrays.asList(adds).contains(-1)) {
                throw new BusinessProcessException("添加拣货单表体信息出错");
            }
            // 添加拣货单表头信息
            int add = jdbcAccessContext.execute("ORDERPICK.SQL_ADD_ORDER_PICK",
                new Object[] { orderNumber, fromBillsNos.toString(), request.getLoginId(), list.get(0).getStorageId(), materialIds.size(), counts, request.getLoginId() });
            if (-1 == add) {
                throw new BusinessProcessException("添加拣货单表头信息出错");
            }
            addOperationLog("拣货单", "新增拣货单，单据编号：[{0}]", orderNumber, request.getLoginId() + "");
        }
        // 修改预发货单状态为 2：已生成拣货单
        List<Object[]> changeReadyShipmentsStatusParam = new LinkedList<>();
        for (String readyShipmentsOrderNumber : request.getOrderNumbers()) {
            changeReadyShipmentsStatusParam.add(new Object[] { 2, readyShipmentsOrderNumber });
        }
        int[] updates = jdbcAccessContext.batchExecute("READYSHIPMENTS.SQL_UPDATE_STATUS_BYID", changeReadyShipmentsStatusParam);
        if (Arrays.asList(updates).contains(-1)) {
            throw new BusinessProcessException("修改预发货单状态失败");
        }
        return Boolean.TRUE;
    }

    private Double buildOrderPickRequest(OrderPickQueryParam request, String orderNumber, List<OrderPickItemRequest> orderPickItemRequestList, HashSet<Integer> materialIds, Double counts,
                                         ReadyShipmentsItemResponse r) {
        materialIds.add(r.getMaterialId());
        counts += r.getCounts();
        OrderPickItemRequest orderPickItemRequest = new OrderPickItemRequest();
        orderPickItemRequest.setOrderNumber(orderNumber);
        orderPickItemRequest.setMaterialId(r.getMaterialId());
        orderPickItemRequest.setBatchNumber(r.getBatchNumber());
        orderPickItemRequest.setProductionDate(r.getProductionDate());
        orderPickItemRequest.setCounts(r.getCounts());
        orderPickItemRequest.setStorageLocationId(r.getStorageLocationId());
        orderPickItemRequest.setCreateBy(request.getLoginId());
        orderPickItemRequest.setFromBillsNo(r.getOrderNumber());
        orderPickItemRequest.setEcOrderNumber(r.getEcOrderNumber());
        if (orderPickItemRequestList.contains(orderPickItemRequest)) {
            Iterator<OrderPickItemRequest> itemIter = orderPickItemRequestList.iterator();
            while (itemIter.hasNext()) {
                OrderPickItemRequest item = itemIter.next();
                if (item.equals(orderPickItemRequest)) {
                    item.setCounts(item.getCounts() + orderPickItemRequest.getCounts());
                }
            }
        } else {
            orderPickItemRequestList.add(orderPickItemRequest);
        }
        return counts;
    }

    private String buildOrderPickQuerySql() {
        StringBuilder queryItemsSql = new StringBuilder();
        queryItemsSql.append(
            "SELECT i.* ,s.storage_id,r.from_bills_no from ready_shipments_item i left join storage_location s on s.id = i.storage_location_id LEFT JOIN ready_shipments r on r.order_number=i.order_number where i.order_number = ?");
        return queryItemsSql.toString();
    }

    protected ReadyShipmentsItemResponse buildReadyShipmentsItemResponse(ResultSet rs) throws SQLException {
        ReadyShipmentsItemResponse response = new ReadyShipmentsItemResponse();
        response.setStorageLocationId(rs.getInt("storage_location_id"));
        response.setMaterialId(rs.getInt("material_id"));
        response.setCounts(rs.getDouble("counts"));
        response.setOrderNumber(rs.getString("order_number"));
        response.setBatchNumber(rs.getString("batch_number"));
        response.setProductionDate(DateUtils.formatDate(rs.getDate("production_date"), "yyyy-MM-dd"));
        response.setStorageId(rs.getInt("storage_id"));
        response.setEcOrderNumber(rs.getString("from_bills_no"));
        return response;
    }

    public PagingResponse<OrderPickResponse> getList(CommonBillsRequest request) {
        PagingResponse<OrderPickResponse> pagingResponse = new PagingResponse<OrderPickResponse>();
        StringBuilder countSql = new StringBuilder();
        List<Object> paramsList = new ArrayList<Object>();
        List<Object> totalList = new ArrayList<Object>();
        List<OrderPickResponse> response = jdbcAccessContext.findWithoutSqlManager(buildOrderPickResponseSQL(request, countSql, paramsList, totalList), new RowMapper<OrderPickResponse>() {
            @Override
            public OrderPickResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildOrderPickResponse(rs);
            }
        }, paramsList.toArray());
        int totalRecords = jdbcAccessContext.findIntegerWithoutSqlManager(countSql.toString(), totalList.toArray());
        pagingResponse.setRecords(response);
        pagingResponse.setTotalRecords(totalRecords);
        return pagingResponse;
    }

    private String buildOrderPickResponseSQL(CommonBillsRequest request, StringBuilder countSql, List<Object> paramsList, List<Object> totalList) {
        StringBuilder sql = new StringBuilder(
            "SELECT o.order_number,o.from_bills_no, o.bills_date, o.owner, o.storage_id, o.sku_counts, o.material_counts ,s.storage_name,o.status FROM order_pick o LEFT JOIN storage s ON s.id = o.storage_id where 1=1 ");
        countSql.append("SELECT count(*) FROM order_pick o where 1=1 ");
        if (StringUtils.hasText(request.getKeywords())) {
            sql.append(" and o.order_number like CONCAT('%',?,'%') ");
            countSql.append(" and o.order_number like CONCAT('%',?,'%') ");
            paramsList.add(request.getKeywords());
            totalList.add(request.getKeywords());
        }
        if (100 != request.getStatus()) {
            sql.append(" and o.status = ? ");
            countSql.append(" and o.status = ? ");
            paramsList.add(request.getStatus());
            totalList.add(request.getStatus());
        }
        if (StringUtils.hasText(request.getStartDate())) {
            sql.append(" and o.bills_date > ? ");
            countSql.append(" and o.bills_date > ? ");
            paramsList.add(request.getStartDate());
            totalList.add(request.getStartDate());
        }
        if (StringUtils.hasText(request.getEndDate())) {
            sql.append(" and o.bills_date < ? ");
            countSql.append(" and o.bills_date < ? ");
            paramsList.add(request.getEndDate());
            totalList.add(request.getEndDate());
        }
        sql.append(" order by o.create_date desc");
        if (null != request.getPageNo() && null != request.getPageSize()) {
            sql.append(" limit ?,? ");
            paramsList.add((request.getPageNo() - 1) * request.getPageSize());
            paramsList.add(request.getPageSize());
        }
        return sql.toString();
    }

    protected OrderPickResponse buildOrderPickResponse(ResultSet rs) throws SQLException {
        OrderPickResponse response = new OrderPickResponse();
        response.setOrderNumber(rs.getString("order_number"));
        response.setBillsDate(DateUtils.formatDate(rs.getDate("bills_date"), "yyyy-MM-dd"));
        response.setSkuCounts(rs.getInt("sku_counts"));
        response.setMaterialCounts(rs.getDouble("material_counts"));
        response.setStorageId(rs.getInt("storage_id"));
        response.setStorageName(rs.getString("storage_name"));
        try {
            response.setFromBillsNo(rs.getString("from_bills_no"));
        } catch (Exception e) {
        }
        try {
            response.setLoginUserName(rs.getString("nickname"));
        } catch (Exception e) {
        }
        try {
            response.setStatus(rs.getInt("status"));
        } catch (Exception e) {
        }
        try {
            String fromBillsNumbers = rs.getString("from_bills_no").replace("[", "").replace("]", "");
            response.setFromBillsNumbers(fromBillsNumbers);
        } catch (Exception e) {
        }
        return response;
    }

    public OrderPickResponse get(String ordernumber) {
        OrderPickResponse response = jdbcAccessContext.findUniqueResult("ORDERPICK.SQl_GET_ORDER_PICK", new RowMapper<OrderPickResponse>() {
            @Override
            public OrderPickResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildOrderPickResponse(rs);
            }
        }, new Object[] { ordernumber });
        List<OrderPickItemResponse> items = jdbcAccessContext.find("ORDERPICK.SQl_GET_ORDER_PICK_ITEM", new RowMapper<OrderPickItemResponse>() {
            @Override
            public OrderPickItemResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildOrderPickItemResponse(rs);
            }
        }, new Object[] { ordernumber });
        response.setItems(items);
        // 按库位分配商品 ，用于前端显示
        List<StorageLocationOrderPickItemResponse> storageLocationOrderPickItems = new LinkedList<StorageLocationOrderPickItemResponse>();
        Map<Integer, List<OrderPickItemResponse>> map = new HashMap<>();
        for (OrderPickItemResponse o : items) {
            if (map.containsKey(o.getStorageLocationId())) {
                map.get(o.getStorageLocationId()).add(o);
            } else {
                List<OrderPickItemResponse> list = new LinkedList<>();
                list.add(o);
                map.put(o.getStorageLocationId(), list);
            }
        }
        for (Integer key : map.keySet()) {
            List<OrderPickItemResponse> item = map.get(key);
            StorageLocationOrderPickItemResponse s = new StorageLocationOrderPickItemResponse();
            s.setLocationName(item.get(0).getLocationName());
            s.setStorageLocationId(item.get(0).getStorageLocationId());
            s.setItems(item);
            storageLocationOrderPickItems.add(s);
        }
        response.setStorageLocationOrderPickItems(storageLocationOrderPickItems);
        return response;
    }

    protected OrderPickItemResponse buildOrderPickItemResponse(ResultSet rs) throws SQLException {
        OrderPickItemResponse response = new OrderPickItemResponse();
        response.setMaterialName(rs.getString("material_name"));
        response.setCounts(rs.getDouble("counts"));
        response.setUnitName(rs.getString("unit_name"));
        response.setMaterialNo(rs.getString("material_no"));
        response.setBarcode(rs.getString("barcode"));
        response.setLocationName(rs.getString("location_name"));
        response.setBatchNumber(rs.getString("batch_number"));
        response.setEcOrderNumber(rs.getString("ec_order_number"));
        response.setFromBillsNo(rs.getString("from_bills_no"));
        return response;
    }

    public Boolean updateStatus(String orderNumber, Integer loginId, Integer status) {
        int update = jdbcAccessContext.execute("ORDERPICK.SQL_UPDATE_STATUS", new Object[] { status, loginId, orderNumber });
        if (-1 == update) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
