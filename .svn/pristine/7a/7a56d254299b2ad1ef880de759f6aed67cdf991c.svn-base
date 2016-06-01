package com.siping.wms.report.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.smartone.report.CommonReportRequest;
import com.siping.smartone.report.response.QueryGoodsTransportResponse;

@Repository
public class QueryGoodsTransportRestRepository {
    @Autowired
    protected JDBCAccessContext jdbcAccessContext;

    public PagingResponse<QueryGoodsTransportResponse> getList(CommonReportRequest request) {
        PagingResponse<QueryGoodsTransportResponse> response = new PagingResponse<QueryGoodsTransportResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        List<Object> queryList = new ArrayList<Object>();
        loadDynamicSql(listSql, totalSql, queryList, request);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(totalSql.toString(), queryList.toArray());
        listSql.append(" ORDER BY item.material_id");
        if (request.getIsPaging() == true && null != request.getPageNo() && null != request.getPageSize()) {
            listSql.append(" limit ?,? ");
            queryList.add((request.getPageNo() - 1) * request.getPageSize());
            queryList.add(request.getPageSize());
        }
        List<QueryGoodsTransportResponse> auditList = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<QueryGoodsTransportResponse>() {
            @Override
            public QueryGoodsTransportResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildQueryGoodsTransportResponse(rs);
            }
        }, queryList.toArray());
        response.setRecords(auditList);
        response.setTotalRecords(total);
        return response;
    }

    protected QueryGoodsTransportResponse buildQueryGoodsTransportResponse(ResultSet rs) throws SQLException {
        QueryGoodsTransportResponse q = new QueryGoodsTransportResponse();
        q.setMaterialNo(rs.getString("material_no"));
        q.setBarcode(rs.getString("barcode"));
        q.setMaterialName(rs.getString("material_name"));
        q.setTypeName(rs.getString("type_name"));
        q.setBatchNumber(rs.getString("batch_number"));
        q.setStartStation(rs.getString("start_station"));
        q.setEndStation(rs.getString("end_station"));
        q.setTransportationType(rs.getString("transportation_type"));
        q.setUnitName(rs.getString("unit_name"));
        q.setReceiptCounts(rs.getDouble("receipt_counts"));
        return q;
    }

    private void loadDynamicSql(StringBuilder listSql, StringBuilder totalSql, List<Object> queryList, CommonReportRequest request) {
        listSql.append(
            "SELECT m.material_no,m.barcode,m.material_name,type.type_name,item.batch_number,i.start_station,i.end_station,i.transportation_type,unit.unit_name,item.receipt_counts FROM inventory_receipt_item item LEFT JOIN inventory_receipt i ON i.receipt_number=item.receipt_number LEFT JOIN material m ON m.id=item.material_id LEFT JOIN material_type type ON type.id=m.material_type LEFT JOIN material_unit unit ON unit.id=m.unit_id WHERE item.status=1 ");
        totalSql.append(
            "SELECT count(*) FROM inventory_receipt_item item LEFT JOIN inventory_receipt i ON i.receipt_number=item.receipt_number LEFT JOIN material m ON m.id=item.material_id LEFT JOIN material_type type ON type.id=m.material_type LEFT JOIN material_unit unit ON unit.id=m.unit_id  WHERE item.status=1 ");
        if (StringUtils.hasText(request.getStartDate())) {
            listSql.append(" and Date(i.bills_date) >= ? ");
            totalSql.append(" and Date(i.bills_date) >= ? ");
            queryList.add(request.getStartDate());
        }
        if (StringUtils.hasText(request.getEndDate())) {
            listSql.append(" and Date(i.bills_date) <= ? ");
            totalSql.append(" and Date(i.bills_date) <= ? ");
            queryList.add(request.getEndDate());
        }
        if (null != request.getMaterialType()) {
            listSql.append(" and FIND_IN_SET(m.material_type, queryAllChildMaterialType(?)) ");
            totalSql.append(" and FIND_IN_SET(m.material_type, queryAllChildMaterialType(?)) ");
            queryList.add(request.getMaterialType());
        }
        if (StringUtils.hasText(request.getKeywords())) {
            listSql.append(" AND (m.material_no LIKE CONCAT('%',?,'%') OR m.material_name LIKE CONCAT('%',?,'%')) ");
            totalSql.append(" AND (m.material_no LIKE CONCAT('%',?,'%') OR m.material_name LIKE CONCAT('%',?,'%')) ");
            queryList.add(request.getKeywords());
            queryList.add(request.getKeywords());
        }
    }
}
