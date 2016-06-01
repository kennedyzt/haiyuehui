package com.siping.service.report.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.service.material.service.MaterialTypeRestService;
import com.siping.smartone.inventory.request.GetInventoryBalanceRequest;
import com.siping.smartone.inventory.request.GetInventoryBatchRequest;
import com.siping.smartone.inventory.request.GetInventoryStatusRequest;
import com.siping.smartone.inventory.response.GetInventoryBalanceDetailResponse;
import com.siping.smartone.inventory.response.GetInventoryBalanceResponse;
import com.siping.smartone.inventory.response.GetInventoryBatchDetailResponse;
import com.siping.smartone.inventory.response.GetInventoryBatchResponse;
import com.siping.smartone.inventory.response.GetInventoryStatusDetailResponse;
import com.siping.smartone.inventory.response.GetInventoryStatusResponse;
import com.siping.smartone.report.response.SingleMaterialCostResponse;

@Repository
public class InventoryReportRestRepository extends BillsRepository { // 需要生成单据编号的Repository才需要继承BillsRepository类
    @Autowired
    private MaterialTypeRestService materialTypeRestService;

    public PagingResponse<GetInventoryBalanceResponse> getList(GetInventoryBalanceRequest request) {
        PagingResponse<GetInventoryBalanceResponse> response = new PagingResponse<GetInventoryBalanceResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        StringBuilder totalpriceSql = new StringBuilder(); // 查询不分页的总数和总金额
        List<Object> queryList = new ArrayList<Object>();
        loadDynamicSql(listSql, totalSql, totalpriceSql, queryList, request);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(totalSql.toString(), queryList.toArray());
        CountAndTotalPrice totalPrice = jdbcAccessContext.findUniqueResultWithoutSqlManager(totalpriceSql.toString(), new RowMapper<CountAndTotalPrice>() {
            @Override
            public CountAndTotalPrice mapRow(ResultSet rs, int paramInt) throws SQLException {
                CountAndTotalPrice totalPrice = new CountAndTotalPrice();
                totalPrice.setBalanceCount(rs.getDouble("count"));
                totalPrice.setBalanceTotalPrice(rs.getDouble("totalPrice"));
                return totalPrice;
            }
        }, queryList.toArray());

        listSql.append(" ORDER BY m.id ");
        if (null != request.getPageNo() && null != request.getPageSize()) {
            listSql.append(" limit ?,? ");
            queryList.add((request.getPageNo() - 1) * request.getPageSize());
            queryList.add(request.getPageSize());
        }
        List<GetInventoryBalanceResponse> balanceList = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<GetInventoryBalanceResponse>() {
            @Override
            public GetInventoryBalanceResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildInventoryBanlance(rs);
            }
        }, queryList.toArray());

        response.setRecords(balanceList);
        response.setTotalRecords(total);
        if (null != totalPrice) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("count", totalPrice.getBalanceCount());
            map.put("totalPrice", totalPrice.getBalanceTotalPrice());
            response.setStats(map);
        }
        return response;
    }

    public PagingResponse<GetInventoryBalanceDetailResponse> getDetaillist(GetInventoryBalanceRequest request) {
        PagingResponse<GetInventoryBalanceDetailResponse> response = new PagingResponse<GetInventoryBalanceDetailResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        StringBuilder totalpriceSql = new StringBuilder(); // 查询不分页的总数和总金额
        List<Object> queryList = new ArrayList<Object>();
        loadDynamicBalanceDetailSql(listSql, totalSql, totalpriceSql, queryList, request);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(totalSql.toString(), queryList.toArray());
        CountAndTotalPrice totalPrice = jdbcAccessContext.findUniqueResultWithoutSqlManager(totalpriceSql.toString(), new RowMapper<CountAndTotalPrice>() {
            @Override
            public CountAndTotalPrice mapRow(ResultSet rs, int paramInt) throws SQLException {
                CountAndTotalPrice totalPrice = new CountAndTotalPrice();
                totalPrice.setInboundCount(rs.getDouble("inboundCount"));
                totalPrice.setInboundTotalPrice(rs.getDouble("inboundTotalPrice"));
                totalPrice.setOutBoundCount(rs.getDouble("outboundCount"));
                totalPrice.setOutBoundTotalPrice(rs.getDouble("outboundTotalPrice"));
                return totalPrice;
            }
        }, queryList.toArray());

        listSql.append(" ORDER BY l.create_date ");
        if (request.getIsPaging() == true && null != request.getPageNo() && null != request.getPageSize()) {
            listSql.append(" limit ?,? ");
            queryList.add((request.getPageNo() - 1) * request.getPageSize());
            queryList.add(request.getPageSize());
        }
        List<GetInventoryBalanceDetailResponse> balanceList = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<GetInventoryBalanceDetailResponse>() {
            @Override
            public GetInventoryBalanceDetailResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildInventoryBanlanceDetail(rs);
            }
        }, queryList.toArray());
        handleBalanceRowData(balanceList);
        response.setRecords(balanceList);
        response.setTotalRecords(total);
        if (null != totalPrice) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("inboundCount", totalPrice.getInboundCount());
            map.put("inboundTotalPrice", totalPrice.getInboundTotalPrice());
            map.put("outboundCount", totalPrice.getOutBoundCount());
            map.put("outboundTotalPrice", totalPrice.getOutBoundTotalPrice());
            map.put("balanceCount", totalPrice.getBalanceCount());
            map.put("balanceTotalPrice", totalPrice.getBalanceTotalPrice());
            response.setStats(map);
        }
        return response;
    }

    private void handleBalanceRowData(List<GetInventoryBalanceDetailResponse> balanceList) { // 处理成本累计
        if (CollectionUtils.isNotEmpty(balanceList)) {
            double balanceCount = 0.0d;
            Double balanceCost = 0.0d;
            double balanceTotalPrice = 0.0d;
            for (GetInventoryBalanceDetailResponse row : balanceList) {
                double rowBalanceCount = (null == row.getBalanceCount() ? 0d : row.getBalanceCount());
                double rowBalanceTotalPrice = (null == row.getBalanceTotalPrice() ? 0d : row.getBalanceTotalPrice());
                balanceCount = balanceCount + rowBalanceCount;
                balanceTotalPrice = balanceTotalPrice + rowBalanceTotalPrice;
                balanceCost = (0.0d == balanceCount || 0.00d == balanceCount || 0d == balanceCount ? null : balanceTotalPrice / balanceCount);
                row.setBalanceCount(new BigDecimal(balanceCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                row.setBalanceTotalPrice(new BigDecimal(balanceTotalPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                if (null != balanceCost)
                    row.setBalanceCost(new BigDecimal(balanceCost).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
        }
    }

    private void loadDynamicBalanceDetailSql(StringBuilder listSql, StringBuilder totalSql, StringBuilder totalpriceSql, List<Object> queryList, GetInventoryBalanceRequest request) {
        listSql
            .append(" SELECT b.batch_number,l.from_bills_no bills_no,l.bills_date,getBusinessPartnerName(l.from_bills_no) businessPartnerName,S.storage_name, M.material_no,M.barcode,M.material_name, "
                + " T.type_name material_type_name,u.unit_name,ROUND(SUM(IF(b.is_inbound,b.counts,null)),2) inboundCount, "
                + " ROUND(SUM(IF(b.is_inbound,b.price*b.counts,null))/SUM(IF(b.is_inbound,b.counts,0)),2) inboundCost, "
                + " ROUND(SUM(IF(b.is_inbound,b.price*b.counts,null)),2) inboundTotalPrice,ROUND(SUM(IF(b.is_inbound,null,b.counts)),2) outboundCount, "
                + " ROUND(queryOutboundCost(b.batch_number,b.material_id,i.storage_id,l.create_date,b.is_inbound),2) outboundCost, "
                + " ROUND(SUM(IF(b.is_inbound,null,b.counts))*queryOutboundCost(b.batch_number,b.material_id,i.storage_id,l.create_date,b.is_inbound),2) outboundTotalPrice, "
                + " ROUND(queryBalanceCount(b.material_id,i.storage_id,l.create_date),2) balanceCount,ROUND(queryBalanceCost(b.material_id,i.storage_id,l.create_date),2) balanceCost, "
                + " ROUND(queryBalanceCount(b.material_id,i.storage_id,l.create_date)* queryBalanceCost(b.material_id,i.storage_id,l.create_date),2) balanceTotalPrice "
                + " ,l.is_inbound FROM inventory_log L LEFT JOIN material_batch B ON L.batch_uuid = B.uuid LEFT JOIN inventory I ON I.id = L.inventory_id LEFT JOIN material M ON I.material_id = M.id LEFT JOIN material_unit U ON U.id = M.unit_id "
                + " LEFT JOIN material_type T ON T.id = M.material_type LEFT JOIN STORAGE S ON S.id = I.storage_id WHERE m.is_delete = FALSE ");
        totalSql
            .append("SELECT count(0) from (SELECT count(0) FROM inventory_log L LEFT JOIN material_batch B ON L.batch_uuid = B.uuid LEFT JOIN inventory I ON I.id = L.inventory_id LEFT JOIN material M ON I.material_id = M.id "
                + " LEFT JOIN material_unit U ON U.id = M.unit_id LEFT JOIN material_type T ON T.id = M.material_type LEFT JOIN STORAGE S ON S.id = I.storage_id WHERE m.is_delete = FALSE");
        totalpriceSql
            .append("SELECT ROUND(SUM(IF(b.is_inbound,b.counts,0)),2) inboundCount,ROUND(SUM(IF(b.is_inbound,b.price*b.counts,0)),2) inboundTotalPrice,ROUND(SUM(IF(b.is_inbound,0,b.counts)),2) outboundCount,ROUND(SUM(IF(b.is_inbound,0,b.price*b.counts)),2) outboundTotalPrice, "
                + " ROUND(SUM(CONVERT (CONCAT(IF (b.is_inbound, '+', '-'),b.counts),DECIMAL)),2) balanceCount,ROUND(SUM(CONVERT (CONCAT(IF (b.is_inbound, '+', '-'),b.counts)*b.price,DECIMAL)),2)  balanceTotalPrice FROM inventory_log L LEFT JOIN material_batch B ON L.batch_uuid = B.uuid "
                + " LEFT JOIN inventory I ON I.id = L.inventory_id LEFT JOIN material M ON I.material_id = M.id LEFT JOIN material_unit U ON U.id = M.unit_id LEFT JOIN material_type T ON T.id = M.material_type LEFT JOIN STORAGE S ON S.id = I.storage_id WHERE m.is_delete = FALSE");
        if (StringUtils.hasText(request.getBillsDate()) && !"null".equals(request.getBillsDate())) {
            listSql.append(" AND l.bills_date <= '").append(parseDate(request.getBillsDate(), Boolean.TRUE)).append("'");
            totalSql.append(" AND l.bills_date <= '").append(parseDate(request.getBillsDate(), Boolean.TRUE)).append("'");
            totalpriceSql.append(" AND l.bills_date <= '").append(parseDate(request.getBillsDate(), Boolean.TRUE)).append("'");
        }
        if (StringUtils.hasText(request.getMaterial()) && !"null".equals(request.getMaterial())) {
            listSql.append(" AND m.material_no=? ");
            totalSql.append(" AND m.material_no=? ");
            totalpriceSql.append(" AND m.material_no=? ");
            queryList.add(request.getMaterial());
        }
        if (StringUtils.hasText(request.getStroage()) && !"null".equals(request.getStroage())) {
            listSql.append(" AND s.storage_no=? ");
            totalSql.append(" AND s.storage_no=? ");
            totalpriceSql.append(" AND s.storage_no=? ");
            queryList.add(request.getStroage());
        }
        if (StringUtils.hasText(request.getMaterialType()) && !"null".equals(request.getMaterialType())) {
            listSql.append(" AND FIND_IN_SET(m.material_type,queryAllChildMaterialType(?)) ");
            totalSql.append(" AND FIND_IN_SET(m.material_type,queryAllChildMaterialType(?)) ");
            totalpriceSql.append(" AND FIND_IN_SET(m.material_type,queryAllChildMaterialType(?)) ");
            queryList.add(request.getMaterialType());
        }
        totalSql.append(" GROUP BY l.from_bills_no,b.batch_number,b.is_inbound ");
        totalSql.append(" ) temp ");
        listSql.append(" GROUP BY l.from_bills_no,b.batch_number,b.is_inbound ");
    }

    private void loadDynamicSql(StringBuilder listSql, StringBuilder totalSql, StringBuilder totalpriceSql, List<Object> queryList, GetInventoryBalanceRequest request) {
        listSql.append(" SELECT I.material_id, M.material_no,M.material_name,M.barcode,T.id material_type_id,T.type_name material_type_name,S.storage_no,S.storage_name,u.unit_name "
            + " ,ROUND(SUM(CONVERT (CONCAT(IF (b.is_inbound, '+', '-'),b.counts),DECIMAL(18,6))),2) count, "
            + " ROUND(SUM(CONVERT (CONCAT(IF (b.is_inbound, '+', '-'),b.price),DECIMAL(18,6)) * b.counts) / SUM(CONVERT (CONCAT(IF (b.is_inbound, '+', '-'),b.counts),DECIMAL(18,6))),2) cost, "
            + " SUM(CONVERT (CONCAT(IF (b.is_inbound, '+', '-'),b.price),DECIMAL(18,6)) * b.counts) totalPrice FROM inventory_log L LEFT JOIN material_batch B ON L.batch_uuid=B.uuid "
            + " LEFT JOIN inventory I ON I.id= L.inventory_id LEFT JOIN material M ON I.material_id=M.id "
            + " LEFT JOIN material_unit U ON U.id=M.unit_id LEFT JOIN material_type T ON T.id=M.material_type LEFT JOIN storage S ON S.id=I.storage_id WHERE m.is_delete=FALSE ");
        totalSql.append("SELECT count(0) from ( SELECT count(0) FROM inventory_log L LEFT JOIN material_batch B ON L.batch_uuid=B.uuid LEFT JOIN inventory I ON I.id= L.inventory_id "
            + " LEFT JOIN material M ON I.material_id=M.id LEFT JOIN material_unit U ON U.id=M.unit_id LEFT JOIN material_type T ON T.id=M.material_type "
            + " LEFT JOIN storage S ON S.id=I.storage_id WHERE m.is_delete=FALSE ");
        totalpriceSql
            .append("SELECT ROUND(SUM(CONVERT(CONCAT(IF(b.is_inbound, '+', '-'),b.counts),DECIMAL(18,6))),2) count, ROUND(SUM(CONVERT(CONCAT(IF (b.is_inbound, '+', '-'),b.price),DECIMAL(18,6)) * b.counts),2) totalPrice "
                + " FROM inventory_log L LEFT JOIN material_batch B ON L.batch_uuid=B.uuid LEFT JOIN inventory I ON I.id= L.inventory_id LEFT JOIN material M ON I.material_id=M.id "
                + " LEFT JOIN material_unit U ON U.id=M.unit_id LEFT JOIN material_type T ON T.id=M.material_type LEFT JOIN storage S ON S.id=I.storage_id WHERE m.is_delete=FALSE");
        if (StringUtils.hasText(request.getBillsDate())) {
            listSql.append(" AND l.bills_date <= '").append(parseDate(request.getBillsDate(), Boolean.TRUE)).append("'");
            totalSql.append(" AND l.bills_date <= '").append(parseDate(request.getBillsDate(), Boolean.TRUE)).append("'");
            totalpriceSql.append(" AND l.bills_date <= '").append(parseDate(request.getBillsDate(), Boolean.TRUE)).append("'");
        }
        if (StringUtils.hasText(request.getMaterial())) {
            listSql.append(" AND (m.material_no LIKE CONCAT('%',?,'%') or m.material_name LIKE CONCAT('%',?,'%')) ");
            totalSql.append(" AND (m.material_no LIKE CONCAT('%',?,'%') or m.material_name LIKE CONCAT('%',?,'%')) ");
            totalpriceSql.append(" AND (m.material_no LIKE CONCAT('%',?,'%') or m.material_name LIKE CONCAT('%',?,'%')) ");
            queryList.add(request.getMaterial());
            queryList.add(request.getMaterial());
        }
        if (StringUtils.hasText(request.getStroage())) {
            listSql.append(" AND (s.storage_no LIKE CONCAT('%',?,'%') or s.storage_name LIKE CONCAT('%',?,'%')) ");
            totalSql.append(" AND (s.storage_no LIKE CONCAT('%',?,'%') or s.storage_name LIKE CONCAT('%',?,'%')) ");
            totalpriceSql.append(" AND (s.storage_no LIKE CONCAT('%',?,'%') or s.storage_name LIKE CONCAT('%',?,'%')) ");
            queryList.add(request.getStroage());
            queryList.add(request.getStroage());
        }
        if (StringUtils.hasText(request.getMaterialType())) {
            listSql.append(" AND FIND_IN_SET(m.material_type,queryAllChildMaterialType(?))");
            totalSql.append(" AND FIND_IN_SET(m.material_type,queryAllChildMaterialType(?)) ");
            totalpriceSql.append(" AND FIND_IN_SET(m.material_type,queryAllChildMaterialType(?)) ");
            queryList.add(request.getMaterialType());
        }
        totalSql.append(" GROUP BY I.storage_id,I.material_id ");
        totalSql.append(" ) temp ");
        listSql.append(" GROUP BY I.storage_id,I.material_id ");
    }

    private GetInventoryBalanceResponse buildInventoryBanlance(ResultSet rs) throws SQLException {
        GetInventoryBalanceResponse response = new GetInventoryBalanceResponse();
        response.setMaterialNo(rs.getString("material_no"));
        response.setMaterialName(rs.getString("material_name"));
        response.setBarcode(rs.getString("barcode"));
        response.setMaterialTypeId(rs.getInt("material_type_id"));
        response.setMaterialTypeName(rs.getString("material_type_name"));
        response.setStroageNo(rs.getString("storage_no"));
        response.setStroageName(rs.getString("storage_name"));
        response.setCount(rs.getDouble("count"));
        response.setCost(rs.getDouble("cost"));
        response.setTotalPrice(rs.getDouble("totalPrice"));
        response.setMaterialUnitName(rs.getString("unit_name"));
        return response;
    }

    private GetInventoryBalanceDetailResponse buildInventoryBanlanceDetail(ResultSet rs) throws SQLException {
        GetInventoryBalanceDetailResponse response = new GetInventoryBalanceDetailResponse();
        response.setBillsNo(rs.getString("bills_no"));
        response.setBillsDate(rs.getString("bills_date"));
        response.setBusinessPartnerName(rs.getString("businessPartnerName"));
        response.setStroageName(rs.getString("storage_name"));
        response.setMaterialNo(rs.getString("material_no"));
        response.setBarcode(rs.getString("barcode"));
        response.setMaterialName(rs.getString("material_name"));
        response.setMaterialTypeName(rs.getString("material_type_name"));
        response.setMaterialUnitName(rs.getString("unit_name"));
        response.setInboundCount(checkNumberIsZero(rs.getDouble("inboundCount")) ? null : rs.getDouble("inboundCount"));
        response.setInboundCost(checkNumberIsZero(rs.getDouble("inboundCost")) ? null : rs.getDouble("inboundCost"));
        response.setInboundTotalPrice(checkNumberIsZero(rs.getDouble("inboundTotalPrice")) ? null : rs.getDouble("inboundTotalPrice"));
        response.setOutBoundCount(checkNumberIsZero(rs.getDouble("outboundCount")) ? null : rs.getDouble("outboundCount"));
        response.setOutBoundCost(checkNumberIsZero(rs.getDouble("outboundCost")) ? null : rs.getDouble("outboundCost"));
        response.setOutBoundTotalPrice(checkNumberIsZero(rs.getDouble("outboundTotalPrice")) ? null : rs.getDouble("outboundTotalPrice"));
        response.setBalanceCount(checkNumberIsZero(rs.getDouble("balanceCount")) ? null : rs.getDouble("balanceCount"));
        response.setBalanceCost(checkNumberIsZero(rs.getDouble("balanceCost")) ? null : rs.getDouble("balanceCost"));
        response.setBalanceTotalPrice(checkNumberIsZero(rs.getDouble("balanceTotalPrice")) ? null : rs.getDouble("balanceTotalPrice"));
        response.setIsInbound(rs.getBoolean("is_inbound"));
        response.setBatchNumber(rs.getString("batch_number"));
        return response;
    }

    Boolean checkNumberIsZero(Double num) {
        if (null == num) {
            return Boolean.TRUE;
        } else if (num.equals(0.00d)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    class CountAndTotalPrice {
        private double inboundCount; // 入库总数
        private double inboundTotalPrice; // 入库总金额
        private double outBoundCount; // 出库总数
        private double outBoundTotalPrice; // 出库总金额
        private double balanceCount; // 结余总数
        private double balanceTotalPrice; // 结余总金额

        public double getInboundCount() {
            return inboundCount;
        }

        public void setInboundCount(double inboundCount) {
            this.inboundCount = inboundCount;
        }

        public double getInboundTotalPrice() {
            return inboundTotalPrice;
        }

        public void setInboundTotalPrice(double inboundTotalPrice) {
            this.inboundTotalPrice = inboundTotalPrice;
        }

        public double getOutBoundCount() {
            return outBoundCount;
        }

        public void setOutBoundCount(double outBoundCount) {
            this.outBoundCount = outBoundCount;
        }

        public double getOutBoundTotalPrice() {
            return outBoundTotalPrice;
        }

        public void setOutBoundTotalPrice(double outBoundTotalPrice) {
            this.outBoundTotalPrice = outBoundTotalPrice;
        }

        public double getBalanceCount() {
            return balanceCount;
        }

        public void setBalanceCount(double balanceCount) {
            this.balanceCount = balanceCount;
        }

        public double getBalanceTotalPrice() {
            return balanceTotalPrice;
        }

        public void setBalanceTotalPrice(double balanceTotalPrice) {
            this.balanceTotalPrice = balanceTotalPrice;
        }
    }

    public PagingResponse<GetInventoryStatusResponse> getStatusList(GetInventoryStatusRequest request) {
        PagingResponse<GetInventoryStatusResponse> response = new PagingResponse<GetInventoryStatusResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        List<Object> queryList = new ArrayList<Object>();
        loadDynamicStatusSql(listSql, totalSql, queryList, request);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(totalSql.toString(), queryList.toArray());
        listSql.append(" ORDER BY m.material_no ");
        if (null != request.getPageNo() && null != request.getPageSize()) {
            listSql.append(" limit ?,? ");
            queryList.add((request.getPageNo() - 1) * request.getPageSize());
            queryList.add(request.getPageSize());
        }
        List<GetInventoryStatusResponse> statusList = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<GetInventoryStatusResponse>() {
            @Override
            public GetInventoryStatusResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildInventoryStatus(rs);
            }
        }, queryList.toArray());
        response.setRecords(statusList);
        response.setTotalRecords(total);
        return response;
    }

    private GetInventoryStatusResponse buildInventoryStatus(final ResultSet rs) throws SQLException {
        GetInventoryStatusResponse r = new GetInventoryStatusResponse();
        r.setAvailableAmount(rs.getDouble("availableAmount"));
        r.setInventoryAmount(rs.getDouble("inventory_amount"));
        r.setMaterialName(rs.getString("material_name"));
        r.setMaterialNo(rs.getString("material_no"));
        r.setMaterialUnitName(rs.getString("unit_name"));
        r.setMaxInventory(rs.getDouble("max_inventory"));
        r.setMinInventory(rs.getDouble("min_inventory"));
        r.setOrderedAmount(rs.getDouble("ordered_amount"));
        r.setPromisedAmount(rs.getDouble("promised_amount"));
        return r;
    }

    private void loadDynamicStatusSql(StringBuilder listSql, StringBuilder totalSql, List<Object> queryList, GetInventoryStatusRequest request) {
        listSql
            .append("SELECT m.material_no,m.material_name,u.unit_name,i.inventory_amount,i.promised_amount,i.ordered_amount,i.inventory_amount + i.ordered_amount - i.promised_amount availableAmount,w.max_inventory,w.min_inventory FROM inventory I"
                + " LEFT JOIN material M ON I.material_id = M.id LEFT JOIN material_unit U ON M.unit_id = U.id LEFT JOIN inventory_warning w ON i.storage_id = w.storage_id AND i.material_id = w.material_id LEFT JOIN `storage` s ON i.storage_id=s.id ");
        totalSql
            .append("SELECT COUNT(DISTINCT i.material_id) FROM inventory I LEFT JOIN material M ON I.material_id = M.id LEFT JOIN material_unit U ON M.unit_id = U.id LEFT JOIN inventory_warning w ON i.storage_id = w.storage_id AND i.material_id = w.material_id LEFT JOIN `storage` s ON i.storage_id=s.id ");
        if (null != request.getMaterialTypeId()) {
            listSql.append(" INNER JOIN material_type t ON m.material_type =t.id WHERE m.material_type in(?) ");
            totalSql.append(" INNER JOIN material_type t ON m.material_type =t.id WHERE m.material_type in(?) ");
            queryList.add(materialTypeRestService.getAllTypeChilds(request.getMaterialTypeId() + ""));
        } else {
            listSql.append(" WHERE m.material_no IS NOT NULL ");
            totalSql.append(" WHERE m.material_no IS NOT NULL ");
        }
        if (StringUtils.hasText(request.getStorageId())) {
            listSql.append(" AND s.storage_no like CONCAT('%',?,'%') ");
            totalSql.append(" AND s.storage_no like CONCAT('%',?,'%') ");
            queryList.add(request.getStorageId());
        }
        if (StringUtils.hasText(request.getMaterialName())) {
            listSql.append(" AND m.material_name like CONCAT('%',?,'%') ");
            totalSql.append(" AND m.material_name like CONCAT('%',?,'%') ");
            queryList.add(request.getMaterialName());
        }

        if (null != request.getIsMaxInventory() && request.getIsMaxInventory()) {
            listSql.append(" AND I.inventory_amount>W.max_inventory ");
            totalSql.append(" AND I.inventory_amount>W.max_inventory ");
        }
        if (null != request.getIsMinInventory() && request.getIsMinInventory()) {
            listSql.append(" AND I.inventory_amount<W.min_inventory ");
            totalSql.append(" AND I.inventory_amount<W.min_inventory ");
        }
        listSql.append(" GROUP BY m.material_no ");
    }

    public List<GetInventoryStatusDetailResponse> getStatusDetailList(GetInventoryStatusRequest req) {
        List<GetInventoryStatusDetailResponse> result = new ArrayList<GetInventoryStatusDetailResponse>();
        StringBuilder querySql = new StringBuilder();
        List<Object> queryParam = new ArrayList<Object>();
        loadDynamicStatusDetailSql(querySql, queryParam, req);
        result = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<GetInventoryStatusDetailResponse>() {
            @Override
            public GetInventoryStatusDetailResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildStatusDetail(rs);
            }
        }, queryParam.toArray());
        return result;
    }

    private GetInventoryStatusDetailResponse buildStatusDetail(ResultSet rs) throws SQLException {
        GetInventoryStatusDetailResponse r = new GetInventoryStatusDetailResponse();
        r.setMaterialNo(rs.getString("material_no"));
        r.setMaterialName(rs.getString("material_name"));
        r.setUnitName(rs.getString("unit_name"));
        r.setBillsDate(rs.getString("bills_date"));
        r.setOrderedAmount(rs.getDouble("ordered_amount"));
        r.setPromisedAmount(rs.getDouble("promised_amount"));
        r.setReceiptDate(rs.getString("receiptDate"));
        r.setStroageName(rs.getString("storage_name"));
        r.setStorageNo(rs.getString("storage_no"));
        r.setBusinessPartnerName(rs.getString("partnerName"));
        r.setBillsNo(rs.getString("from_bills_no"));
        return r;
    }

    private void loadDynamicStatusDetailSql(StringBuilder listSql, List<Object> queryList, GetInventoryStatusRequest request) {
        listSql
            .append("SELECT DISTINCT L.from_bills_no, getBusinessPartnerName(L.from_bills_no) partnerName,M.material_no,M.material_name,l.bills_date,IF (left(L.from_bills_no,2)='SO',soi.shipments_date, poi.receipt_date) receiptDate,S.storage_name,U.unit_name,I.promised_amount,I.ordered_amount,s.storage_no FROM inventory_log L INNER JOIN inventory I ON L.inventory_id=I.id "
                + " INNER JOIN material M ON I.material_id=M.id LEFT JOIN material_unit U ON M.unit_id=U.id LEFT JOIN STORAGE s ON I.storage_id = S.ID LEFT JOIN purchase_order po ON po.order_number=l.from_bills_no LEFT JOIN purchase_order_item poi ON poi.order_number=po.order_number LEFT JOIN sell_order so ON so.order_number=l.from_bills_no "
                + " AND i.storage_id=so.outbound_storage_id LEFT JOIN sell_order_item soi ON soi.order_number=so.order_number ");
        if (null != request.getMaterialTypeId()) {
            listSql
                .append(" INNER JOIN material_type t ON m.material_type =t.id WHERE m.material_type in(?) and L.from_bills_no LIKE 'PO%' OR L.from_bills_no LIKE 'SO%' and i.storage_id=po.inbound_storage_id AND m.material_no=? ");
            queryList.add(materialTypeRestService.getAllTypeChilds(request.getMaterialTypeId() + ""));
        } else {
            listSql.append(" WHERE L.from_bills_no LIKE 'PO%' OR L.from_bills_no LIKE 'SO%' and i.storage_id=po.inbound_storage_id AND m.material_no=? ");
        }
        queryList.add(request.getMaterialNo());
        if (StringUtils.hasText(request.getStorageId())) {
            listSql.append(" AND s.storage_no like CONCAT('%',?,'%') ");
            queryList.add(request.getStorageId());
        }
        if (StringUtils.hasText(request.getMaterialName())) {
            listSql.append(" AND m.material_name like CONCAT('%',?,'%') ");
            queryList.add(request.getMaterialName());
        }

        if (null != request.getIsMaxInventory() && request.getIsMaxInventory()) {
            listSql.append(" AND I.inventory_amount>W.max_inventory ");
        }
        if (null != request.getIsMinInventory() && request.getIsMinInventory()) {
            listSql.append(" AND I.inventory_amount<W.min_inventory ");
        }
    }

    public PagingResponse<GetInventoryBatchResponse> getBatchList(GetInventoryBatchRequest request) {
        PagingResponse<GetInventoryBatchResponse> response = new PagingResponse<GetInventoryBatchResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        List<Object> queryList = new ArrayList<Object>();
        loadDynamicBatchSql(listSql, totalSql, queryList, request);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(totalSql.toString(), queryList.toArray());
        listSql.append(" ORDER BY m.material_no ");
        if (null != request.getPageNo() && null != request.getPageSize()) {
            listSql.append(" limit ?,? ");
            queryList.add((request.getPageNo() - 1) * request.getPageSize());
            queryList.add(request.getPageSize());
        }
        List<GetInventoryBatchResponse> statusList = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<GetInventoryBatchResponse>() {
            @Override
            public GetInventoryBatchResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildInventoryBatch(rs);
            }
        }, queryList.toArray());
        response.setRecords(statusList);
        response.setTotalRecords(total);
        return response;
    }

    private GetInventoryBatchResponse buildInventoryBatch(final ResultSet rs) throws SQLException {
        GetInventoryBatchResponse r = new GetInventoryBatchResponse();
        r.setMaterialName(rs.getString("material_name"));
        r.setMaterialNo(rs.getString("material_no"));
        r.setCounts(rs.getDouble("counts"));
        r.setBatchNo(rs.getString("batch_number"));
        String expiryDate = rs.getString("expiryDate");
        if (StringUtils.hasText(expiryDate)) {
            r.setExpiryDate(expiryDate.substring(0, 10));
        }
        String productionDate = rs.getString("production_date");
        if (StringUtils.hasText(productionDate)) {
            r.setProductionDate(productionDate.substring(0, 10));
        }
        r.setStroageName(rs.getString("storage_name"));
        return r;
    }

    private void loadDynamicBatchSql(StringBuilder listSql, StringBuilder totalSql, List<Object> queryList, GetInventoryBatchRequest request) {
        listSql
            .append("SELECT M.material_no,M.material_name,MB.batch_number,S.storage_no,S.storage_name,SUM(IF(MB.is_inbound,mb.counts,-mb.counts)) counts,mb.production_date,DATE_ADD(mb.production_date,INTERVAL m.expiration_date DAY) expiryDate FROM material_batch mb "
                + " LEFT JOIN inventory_log l ON l.batch_uuid = mb.uuid LEFT JOIN inventory I ON I.id=L.inventory_id INNER JOIN material M ON I.material_id = M.id LEFT JOIN STORAGE S ON I.storage_id = S.ID ");
        totalSql
            .append("SELECT count(1) from (SELECT count(DISTINCT 1) FROM material_batch mb LEFT JOIN inventory_log l ON l.batch_uuid = mb.uuid LEFT JOIN inventory I ON I.id=L.inventory_id INNER JOIN material M ON I.material_id = M.id LEFT JOIN STORAGE S ON I.storage_id = S.ID");
        if (null != request.getMaterialTypeId()) {
            listSql.append(" INNER JOIN material_type t ON m.material_type =t.id WHERE m.material_type in(?) and m.is_batch=TRUE ");
            totalSql.append(" INNER JOIN material_type t ON m.material_type =t.id WHERE m.material_type in(?) and m.is_batch=TRUE ");
            queryList.add(materialTypeRestService.getAllTypeChilds(request.getMaterialTypeId() + ""));
        } else {
            listSql.append(" WHERE m.is_batch=TRUE ");
            totalSql.append(" WHERE m.is_batch=TRUE ");
        }
        if (StringUtils.hasText(request.getStorageId())) {
            listSql.append(" AND s.storage_no like CONCAT('%',?,'%') ");
            totalSql.append(" AND s.storage_no like CONCAT('%',?,'%') ");
            queryList.add(request.getStorageId());
        }
        if (StringUtils.hasText(request.getMaterialName())) {
            listSql.append(" AND m.material_name like CONCAT('%',?,'%') ");
            totalSql.append(" AND m.material_name like CONCAT('%',?,'%') ");
            queryList.add(request.getMaterialName());
        }
        if (StringUtils.hasText(request.getStartDate())) {
            listSql.append(" AND l.bills_date >= '").append(parseDate(request.getStartDate(), Boolean.FALSE)).append("'");
            totalSql.append(" AND l.bills_date >= '").append(parseDate(request.getStartDate(), Boolean.FALSE)).append("'");
        }
        if (StringUtils.hasText(request.getEndDate())) {
            listSql.append("  AND l.bills_date <= '").append(parseDate(request.getEndDate(), Boolean.TRUE)).append("'");
            totalSql.append("  AND l.bills_date <= '").append(parseDate(request.getEndDate(), Boolean.TRUE)).append("'");
        }
        totalSql.append(" GROUP BY mb.batch_number,I.storage_id,i.material_id) temp");
        listSql.append(" GROUP BY mb.batch_number,I.storage_id,i.material_id ");
    }

    public List<GetInventoryBatchDetailResponse> getBatchDetailList(GetInventoryBatchRequest req) {
        List<GetInventoryBatchDetailResponse> result = new ArrayList<GetInventoryBatchDetailResponse>();
        StringBuilder querySql = new StringBuilder();
        List<Object> queryParam = new ArrayList<Object>();
        loadBatchDetailSql(querySql, queryParam, req);
        result = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<GetInventoryBatchDetailResponse>() {
            @Override
            public GetInventoryBatchDetailResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildBatchDetail(rs);
            }
        }, queryParam.toArray());
        return result;
    }

    private GetInventoryBatchDetailResponse buildBatchDetail(final ResultSet rs) throws SQLException {
        GetInventoryBatchDetailResponse r = new GetInventoryBatchDetailResponse();
        r.setBillsNo(rs.getString("from_bills_no"));
        r.setBillsDate(rs.getString("bills_date"));
        r.setStorageNo(rs.getString("storage_no"));
        r.setStroageName(rs.getString("storage_name"));
        r.setCounts(rs.getDouble("counts"));
        r.setDirection(rs.getString("direction"));
        r.setMaterialNo(rs.getString("material_no"));
        r.setMaterialName(rs.getString("material_name"));
        return r;
    }

    private void loadBatchDetailSql(StringBuilder listSql, List<Object> queryList, GetInventoryBatchRequest request) {
        listSql
            .append("SELECT DISTINCT mb.batch_number,l.from_bills_no,l.bills_date,s.storage_no,s.storage_name,if(mb.is_inbound,mb.counts,-mb.counts) counts, if(mb.is_inbound,'入','出') direction,m.material_no,m.material_name FROM material_batch mb LEFT JOIN inventory_log l ON l.batch_uuid = mb.uuid "
                + " LEFT JOIN inventory I ON I.id=L.inventory_id INNER JOIN material M ON I.material_id = M.id LEFT JOIN STORAGE S ON I.storage_id = S.ID ");
        if (null != request.getMaterialTypeId()) {
            listSql.append(" INNER JOIN material_type t ON m.material_type =t.id WHERE m.material_type in(?) and m.is_batch=TRUE and  m.material_no=?");
            queryList.add(materialTypeRestService.getAllTypeChilds(request.getMaterialTypeId() + ""));
        } else {
            listSql.append(" WHERE m.is_batch=TRUE and  m.material_no=?");
        }
        queryList.add(request.getMaterialNo());
        if (StringUtils.hasText(request.getStorageId())) {
            listSql.append(" AND s.storage_no like CONCAT('%',?,'%') ");
            queryList.add(request.getStorageId());
        }
        if (StringUtils.hasText(request.getMaterialName())) {
            listSql.append(" AND m.material_name like CONCAT('%',?,'%') ");
            queryList.add(request.getMaterialName());
        }
        if (StringUtils.hasText(request.getStartDate())) {
            listSql.append(" AND l.bills_date >= '").append(parseDate(request.getStartDate(), Boolean.FALSE)).append("'");
        }
        if (StringUtils.hasText(request.getEndDate())) {
            listSql.append("  AND l.bills_date <= '").append(parseDate(request.getEndDate(), Boolean.TRUE)).append("'");
        }
    }

    public List<SingleMaterialCostResponse> getCost(String materialid) {
        List<SingleMaterialCostResponse> costs = new ArrayList<SingleMaterialCostResponse>();
        String sql = "SELECT I.storage_id,I.material_id ,ROUND(SUM(CONVERT (CONCAT(IF (b.is_inbound, '+', '-'),b.price),DECIMAL(18,6)) * b.counts) / SUM(CONVERT (CONCAT(IF (b.is_inbound, '+', '-'),b.counts),DECIMAL(18,6))),2) cost "
            + " FROM inventory_log L LEFT JOIN material_batch B ON L.batch_uuid=B.uuid  LEFT JOIN inventory I ON I.id= L.inventory_id LEFT JOIN material M ON I.material_id=M.id "
            + " LEFT JOIN material_unit U ON U.id=M.unit_id LEFT JOIN material_type T ON T.id=M.material_type LEFT JOIN storage S ON S.id=I.storage_id WHERE m.is_delete=FALSE "
            + " and m.id=? GROUP BY I.storage_id,I.material_id ";
        costs = jdbcAccessContext.findWithoutSqlManager(sql, new RowMapper<SingleMaterialCostResponse>() {

            @Override
            public SingleMaterialCostResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                SingleMaterialCostResponse response = new SingleMaterialCostResponse();
                response.setCost(rs.getDouble("cost"));
                response.setMaterialId(rs.getInt("material_id"));
                response.setStorageId(rs.getInt("storage_id"));
                return response;
            }
        }, new Object[] { materialid });
        return costs;
    }
}
