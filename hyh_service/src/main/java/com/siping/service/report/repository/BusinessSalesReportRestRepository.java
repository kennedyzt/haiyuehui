package com.siping.service.report.repository;

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
import com.siping.smartone.report.request.InWarehouseRequest;
import com.siping.smartone.report.request.OutwarehouseRequest;
import com.siping.smartone.report.response.BusinessSalesReportResponse;
import com.siping.smartone.report.response.InWarehouseResponse;
import com.siping.smartone.report.response.OutwarehouseResponse;

@Repository
public class BusinessSalesReportRestRepository {
    @Autowired
    protected JDBCAccessContext jdbcAccessContext;

    public PagingResponse<BusinessSalesReportResponse> getList(CommonReportRequest request) {
        PagingResponse<BusinessSalesReportResponse> response = new PagingResponse<BusinessSalesReportResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        List<Object> queryList = new ArrayList<Object>();
        loadDynamicSql(listSql, totalSql, queryList, request);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(totalSql.toString(), queryList.toArray());
        listSql.append(" ORDER BY sell.bills_date");
        if (request.getIsPaging() == true && null != request.getPageNo() && null != request.getPageSize()) {
            listSql.append(" limit ?,? ");
            queryList.add((request.getPageNo() - 1) * request.getPageSize());
            queryList.add(request.getPageSize());
        }
        List<BusinessSalesReportResponse> auditList = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<BusinessSalesReportResponse>() {
            @Override
            public BusinessSalesReportResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildBusinessSalesReportResponse(rs);
            }
        }, queryList.toArray());
        response.setRecords(auditList);
        response.setTotalRecords(total);
        return response;
    }

    protected BusinessSalesReportResponse buildBusinessSalesReportResponse(ResultSet rs) throws SQLException {
        BusinessSalesReportResponse b = new BusinessSalesReportResponse();
        b.setShopNo(rs.getString("shop_no"));
        b.setShopName(rs.getString("shop_name"));
        b.setTotalPrice(rs.getDouble("totalPrice"));
        return b;
    }

    private void loadDynamicSql(StringBuilder listSql, StringBuilder totalSql, List<Object> queryList, CommonReportRequest request) {
        listSql.append(
            "SELECT s.shop_no,s.shop_name,SUM(after_discount) totalPrice from sell_shipments_item item LEFT JOIN material m on item.material_id=m.id LEFT JOIN shop s ON m.shops=s.id LEFT JOIN sell_shipments sell on sell.shipments_number=item.shipments_number where 1=1 ");
        totalSql.append(
            " SELECT count(*) from (SELECT count(*) from sell_shipments_item item LEFT JOIN material m on item.material_id=m.id LEFT JOIN shop s ON m.shops=s.id LEFT JOIN sell_shipments sell on sell.shipments_number=item.shipments_number where 1=1 ");
        if (StringUtils.hasText(request.getStartDate())) {
            listSql.append(" and Date(sell.bills_date) >= ? ");
            totalSql.append(" and Date(sell.bills_date) >= ? ");
            queryList.add(request.getStartDate());
        }
        if (StringUtils.hasText(request.getEndDate())) {
            listSql.append(" and Date(sell.bills_date) <= ? ");
            totalSql.append(" and Date(sell.bills_date) <= ? ");
            queryList.add(request.getEndDate());
        }
        if (StringUtils.hasText(request.getKeywords())) {
            listSql.append(" AND (s.shop_no LIKE CONCAT('%',?,'%') OR s.shop_name LIKE CONCAT('%',?,'%')) ");
            totalSql.append(" AND (s.shop_no LIKE CONCAT('%',?,'%') OR s.shop_name LIKE CONCAT('%',?,'%')) ");
            queryList.add(request.getKeywords());
            queryList.add(request.getKeywords());
        }
        totalSql.append(" GROUP BY s.shop_no) A");
        listSql.append(" GROUP BY s.shop_no");
    }

    public PagingResponse<InWarehouseResponse> getInwarehouse(InWarehouseRequest request) {
        PagingResponse<InWarehouseResponse> response = new PagingResponse<InWarehouseResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        StringBuilder totalMoneySql = new StringBuilder();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        buildInwarehouseSql(request, listSql, totalSql, totalMoneySql, queryParam, totalParam);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(totalSql.toString(), totalParam.toArray());
        List<InWarehouseResponse> list = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<InWarehouseResponse>() {
            @Override
            public InWarehouseResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildInwarehouseList(paramResultSet);
            }
        }, queryParam.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    private InWarehouseResponse buildInwarehouseList(ResultSet rs) throws SQLException {
        InWarehouseResponse response = new InWarehouseResponse();
        response.setBarcode(rs.getString("barcode"));
        response.setCounts(rs.getDouble("counts"));
        response.setProductName(rs.getString("material_name"));
        response.setProductNo(rs.getString("material_no"));
        response.setProductType(rs.getString("type_name"));
        response.setUnitName(rs.getString("unit_name"));
        response.setStorageName(rs.getString("storage_name"));
        response.setStorageNo(rs.getString("storage_no"));
        return response;
    }

    private void buildInwarehouseSql(InWarehouseRequest request, StringBuilder listSql, StringBuilder totalSql, StringBuilder totalMoneySql, List<Object> queryParam, List<Object> totalParam) {
        listSql.append("SELECT m.material_no,m.barcode,m.material_name,m.material_type typeId,mt.type_name,s.storage_no,s.storage_name,u.unit_name,SUM(p.counts) counts "
            + "FROM material m LEFT JOIN purchase_receipt_item p ON p.material_id = m.id LEFT JOIN material_type mt ON m.material_type = mt.id LEFT JOIN "
            + "purchase_receipt i ON p.receipt_number = i.receipt_number LEFT JOIN `storage` s ON i.inbound_storage_id = s.id LEFT JOIN material_unit u "
            + "ON m.unit_id = u.id WHERE m.is_delete = 0 AND m.is_enable = 1 ");
        totalSql.append("SELECT COUNT(1) pageCount FROM (SELECT m.material_no,m.barcode,m.material_name,m.material_type typeId,mt.type_name,s.storage_no,s.storage_name,u.unit_name,SUM(p.counts) counts "
            + "FROM material m LEFT JOIN purchase_receipt_item p ON p.material_id = m.id LEFT JOIN material_type mt ON m.material_type = mt.id LEFT JOIN "
            + "purchase_receipt i ON p.receipt_number = i.receipt_number LEFT JOIN `storage` s ON i.inbound_storage_id = s.id LEFT JOIN material_unit u "
            + "ON m.unit_id = u.id WHERE m.is_delete = 0 AND m.is_enable = 1 ");
        if (StringUtils.hasText(request.getDateFrom())) {
            listSql.append("AND DATE_FORMAT(i.create_date,'%Y-%m-%d') >= ? ");
            totalSql.append("AND DATE_FORMAT(i.create_date,'%Y-%m-%d') >= ? ");
            queryParam.add(request.getDateFrom());
        }
        if (StringUtils.hasText(request.getDateTo())) {
            listSql.append("AND DATE_FORMAT(i.create_date,'%Y-%m-%d') <= ? ");
            totalSql.append("AND DATE_FORMAT(i.create_date,'%Y-%m-%d') <= ? ");
            queryParam.add(request.getDateTo());
        }
        if (StringUtils.hasText(request.getKeyword())) {
            listSql.append("AND (m.material_no LIKE CONCAT('%',?,'%') OR m.barcode LIKE CONCAT('%',?,'%') OR m.material_name LIKE CONCAT('%',?,'%')) ");
            totalSql.append("AND (m.material_no LIKE CONCAT('%',?,'%') OR m.barcode LIKE CONCAT('%',?,'%') OR m.material_name LIKE CONCAT('%',?,'%')) ");
            queryParam.add(request.getKeyword());
            queryParam.add(request.getKeyword());
            queryParam.add(request.getKeyword());
        }
        if (StringUtils.hasText(request.getProductType())) {
            listSql.append("AND FIND_IN_SET(m.material_type,queryAllChildMaterialType( ? )) ");
            totalSql.append("AND FIND_IN_SET(m.material_type,queryAllChildMaterialType( ? )) ");
            queryParam.add(request.getProductType());
        }
        if (StringUtils.hasText(request.getStorageId())) {
            listSql.append("AND s.id = ? ");
            totalSql.append("AND s.id = ? ");
            queryParam.add(request.getStorageId());
        }
        listSql.append("GROUP BY m.id ORDER BY SUM(p.counts) DESC ");
        totalSql.append("GROUP BY m.id) t");
        totalParam.addAll(queryParam);
        if (request.getIsPaging() == true && request.getPageNo() != null && request.getPageSize() != null) {
            listSql.append(" limit ?,?");
            queryParam.add((request.getPageNo() - 1) * request.getPageSize());
            queryParam.add(request.getPageSize());
        }
    }

    public PagingResponse<OutwarehouseResponse> getOutwarehouse(OutwarehouseRequest request) {
        PagingResponse<OutwarehouseResponse> response = new PagingResponse<OutwarehouseResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        StringBuilder totalMoneySql = new StringBuilder();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        buildOutwarehouseSql(request, listSql, totalSql, totalMoneySql, queryParam, totalParam);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(totalSql.toString(), totalParam.toArray());
        List<OutwarehouseResponse> list = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<OutwarehouseResponse>() {
            @Override
            public OutwarehouseResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildOutwarehouseList(paramResultSet);
            }
        }, queryParam.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    private OutwarehouseResponse buildOutwarehouseList(ResultSet rs) throws SQLException {
        OutwarehouseResponse response = new OutwarehouseResponse();
        response.setBarcode(rs.getString("barcode"));
        response.setCounts(rs.getDouble("counts"));
        response.setProductName(rs.getString("material_name"));
        response.setProductNo(rs.getString("material_no"));
        response.setProductType(rs.getString("type_name"));
        response.setUnitName(rs.getString("unit_name"));
        response.setStorageName(rs.getString("storage_name"));
        response.setStorageNo(rs.getString("storage_no"));
        return response;
    }

    private void buildOutwarehouseSql(OutwarehouseRequest request, StringBuilder listSql, StringBuilder totalSql, StringBuilder totalMoneySql, List<Object> queryParam, List<Object> totalParam) {
        listSql.append("SELECT m.material_no,m.barcode,m.material_name,m.material_type typeId,mt.type_name,s.storage_no,s.storage_name,u.unit_name,"
            + "SUM(p.counts) counts FROM material m LEFT JOIN sell_shipments_item p ON p.material_id = m.id LEFT JOIN material_type mt ON m.material_type = "
            + "mt.id LEFT JOIN sell_shipments i ON p.shipments_number = i.shipments_number LEFT JOIN `storage` s ON i.outbound_storage_id = s.id LEFT JOIN "
            + "material_unit u ON m.unit_id = u.id WHERE m.is_delete = 0 AND m.is_enable = 1 ");
        totalSql.append("SELECT COUNT(1) pageCount FROM (SELECT m.material_no,m.barcode,m.material_name,m.material_type typeId,mt.type_name,s.storage_no,s.storage_name,u.unit_name,"
            + "SUM(p.counts) counts FROM material m LEFT JOIN sell_shipments_item p ON p.material_id = m.id LEFT JOIN material_type mt ON m.material_type = "
            + "mt.id LEFT JOIN sell_shipments i ON p.shipments_number = i.shipments_number LEFT JOIN `storage` s ON i.outbound_storage_id = s.id LEFT JOIN "
            + "material_unit u ON m.unit_id = u.id WHERE m.is_delete = 0 AND m.is_enable = 1 ");
        if (StringUtils.hasText(request.getDateFrom())) {
            listSql.append("AND DATE_FORMAT(i.bills_date,'%Y-%m-%d') >= ? ");
            totalSql.append("AND DATE_FORMAT(i.bills_date,'%Y-%m-%d') >= ? ");
            queryParam.add(request.getDateFrom());
        }
        if (StringUtils.hasText(request.getDateTo())) {
            listSql.append("AND DATE_FORMAT(i.bills_date,'%Y-%m-%d') <= ? ");
            totalSql.append("AND DATE_FORMAT(i.bills_date,'%Y-%m-%d') <= ? ");
            queryParam.add(request.getDateTo());
        }
        if (StringUtils.hasText(request.getKeyword())) {
            listSql.append("AND (m.material_no LIKE CONCAT('%',?,'%') OR m.barcode LIKE CONCAT('%',?,'%') OR m.material_name LIKE CONCAT('%',?,'%')) ");
            totalSql.append("AND (m.material_no LIKE CONCAT('%',?,'%') OR m.barcode LIKE CONCAT('%',?,'%') OR m.material_name LIKE CONCAT('%',?,'%')) ");
            queryParam.add(request.getKeyword());
            queryParam.add(request.getKeyword());
            queryParam.add(request.getKeyword());
        }
        if (StringUtils.hasText(request.getProductType())) {
            listSql.append("AND FIND_IN_SET(m.material_type,queryAllChildMaterialType( ? )) ");
            totalSql.append("AND FIND_IN_SET(m.material_type,queryAllChildMaterialType( ? )) ");
            queryParam.add(request.getProductType());
        }
        if (StringUtils.hasText(request.getStorageId())) {
            listSql.append("AND s.id = ? ");
            totalSql.append("AND s.id = ? ");
            queryParam.add(request.getStorageId());
        }
        listSql.append("GROUP BY m.id ORDER BY SUM(p.counts) DESC ");
        totalSql.append("GROUP BY m.id) t");
        totalParam.addAll(queryParam);
        if (request.getIsPaging() == true && request.getPageNo() != null && request.getPageSize() != null) {
            listSql.append(" limit ?,?");
            queryParam.add((request.getPageNo() - 1) * request.getPageSize());
            queryParam.add(request.getPageSize());
        }
    }
}
