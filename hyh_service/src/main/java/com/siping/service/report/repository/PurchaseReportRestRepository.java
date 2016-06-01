package com.siping.service.report.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.smartone.report.request.ProductPurchaseRequest;
import com.siping.smartone.report.request.PurchaseAnalysisRequest;
import com.siping.smartone.report.request.PurchaseOrderNumRequest;
import com.siping.smartone.report.request.SupplierPurchaseRequest;
import com.siping.smartone.report.response.ProductPurchaseResponse;
import com.siping.smartone.report.response.ProductPurchaseSumResponse;
import com.siping.smartone.report.response.PurchaseAnalysisResponse;
import com.siping.smartone.report.response.PurchaseOrderNumResponse;
import com.siping.smartone.report.response.PurchaseOrderNumSumResponse;
import com.siping.smartone.report.response.SupplierPurchaseResponse;

@Repository
public class PurchaseReportRestRepository {

    @Autowired
    protected JDBCAccessContext jdbcAccessContext;

    public PagingResponse<SupplierPurchaseResponse> getSupplierPurchaseData(SupplierPurchaseRequest request) {
        PagingResponse<SupplierPurchaseResponse> response = new PagingResponse<SupplierPurchaseResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        StringBuilder totalMoneySql = new StringBuilder();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        Map<String, Object> countMap = new HashMap<String, Object>();
        buildSupplierPurchaseSql(request, listSql, totalSql, totalMoneySql, queryParam, totalParam);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(totalSql.toString(), totalParam.toArray());
        String totalMoney = jdbcAccessContext.findStringWithoutSqlManager(totalMoneySql.toString(), totalParam.toArray());
        List<SupplierPurchaseResponse> list = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<SupplierPurchaseResponse>() {
            @Override
            public SupplierPurchaseResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildSupplierPurchaseList(paramResultSet);
            }
        }, queryParam.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        countMap.put("totalMoney", totalMoney);
        response.setStats(countMap);
        return response;
    }

    private void buildSupplierPurchaseSql(SupplierPurchaseRequest request, StringBuilder listSql, StringBuilder totalSql, StringBuilder totalMoneySql, List<Object> queryParam,
                                          List<Object> totalParam) {
        listSql.append("SELECT t.partner_id,t.partner_code,t.partner_name,t.countryName,SUM(IF(rowType = 'out',-t.payPrice,t.payPrice)) payPrice FROM "
            + "(SELECT b.id partner_id,b.partner_code,b.partner_name,c.id countryId,c.country_region_name countryName,SUM(i.after_discount) payPrice,'in' rowType,p.bill_date billDate "
            + "FROM business_partner b LEFT JOIN purchase_receipt p ON  b.id = p.partner_id " + "LEFT JOIN purchase_receipt_item i ON p.receipt_number = i.receipt_number "
            + "LEFT JOIN country_region c ON b.country_region_id = c.id WHERE b.partner_type = 2 ");
        if (StringUtils.hasText(request.getDateFrom())) {
            listSql.append("AND DATE_FORMAT(p.bill_date,'%Y-%m-%d') >= ? ");
            queryParam.add(request.getDateFrom());
        }
        if (StringUtils.hasText(request.getDateTo())) {
            listSql.append("AND DATE_FORMAT(p.bill_date,'%Y-%m-%d') <= ? ");
            queryParam.add(request.getDateTo());
        }
        listSql.append("GROUP BY b.id UNION ALL ");
        listSql.append("SELECT b.id partner_id,b.partner_code,b.partner_name,c.id countryId,c.country_region_name countryName,SUM(i.after_discount) payPrice,'out' rowType,p.bills_date billDate "
            + "FROM business_partner b LEFT JOIN purchase_returns p ON b.id = p.partner_id " + "LEFT JOIN purchase_returns_item i ON p.returns_number = i.returns_number "
            + "LEFT JOIN country_region c ON b.country_region_id = c.id WHERE b.partner_type = 2 ");
        if (StringUtils.hasText(request.getDateFrom())) {
            listSql.append("AND DATE_FORMAT(p.bills_date,'%Y-%m-%d') >= ? ");
            queryParam.add(request.getDateFrom());
        }
        if (StringUtils.hasText(request.getDateTo())) {
            listSql.append("AND DATE_FORMAT(p.bills_date,'%Y-%m-%d') <= ? ");
            queryParam.add(request.getDateTo());
        }
        listSql.append("GROUP BY b.id) t WHERE 1=1 ");
        totalSql.append("SELECT count(1) FROM (SELECT t.partner_id FROM "
            + "(SELECT b.id partner_id,b.partner_code,b.partner_name,c.id countryId,c.country_region_name countryName,SUM(i.after_discount) payPrice,'in' rowType,p.bill_date billDate "
            + "FROM business_partner b LEFT JOIN purchase_receipt p ON  b.id = p.partner_id " + "LEFT JOIN purchase_receipt_item i ON p.receipt_number = i.receipt_number "
            + "LEFT JOIN country_region c ON b.country_region_id = c.id WHERE b.partner_type = 2 ");
        if (StringUtils.hasText(request.getDateFrom())) {
            totalSql.append("AND DATE_FORMAT(p.bill_date,'%Y-%m-%d') >= ? ");
        }
        if (StringUtils.hasText(request.getDateTo())) {
            totalSql.append("AND DATE_FORMAT(p.bill_date,'%Y-%m-%d') <= ? ");
        }
        totalSql.append("GROUP BY b.id UNION ALL ");
        totalSql.append("SELECT b.id partner_id,b.partner_code,b.partner_name,c.id countryId,c.country_region_name countryName,SUM(i.after_discount) payPrice,'out' rowType,p.bills_date billDate "
            + "FROM business_partner b LEFT JOIN purchase_returns p ON b.id = p.partner_id " + "LEFT JOIN purchase_returns_item i ON p.returns_number = i.returns_number "
            + "LEFT JOIN country_region c ON b.country_region_id = c.id WHERE b.partner_type = 2 ");
        if (StringUtils.hasText(request.getDateFrom())) {
            totalSql.append("AND DATE_FORMAT(p.bills_date,'%Y-%m-%d') >= ? ");
        }
        if (StringUtils.hasText(request.getDateTo())) {
            totalSql.append("AND DATE_FORMAT(p.bills_date,'%Y-%m-%d') <= ? ");
        }
        totalSql.append("GROUP BY b.id ) t WHERE 1=1 ");
        totalMoneySql.append("SELECT SUM(al.payPrice) totalAll FROM (SELECT SUM(IF(rowType = 'out',-t.payPrice,t.payPrice)) payPrice FROM "
            + "(SELECT b.id partner_id,b.partner_code,b.partner_name,c.id countryId,c.country_region_name countryName,SUM(i.after_discount) payPrice,'in' rowType,p.bill_date billDate "
            + "FROM business_partner b LEFT JOIN purchase_receipt p ON  b.id = p.partner_id " + "LEFT JOIN purchase_receipt_item i ON p.receipt_number = i.receipt_number "
            + "LEFT JOIN country_region c ON b.country_region_id = c.id WHERE b.partner_type = 2 ");
        if (StringUtils.hasText(request.getDateFrom())) {
            totalMoneySql.append("AND DATE_FORMAT(p.bill_date,'%Y-%m-%d') >= ? ");
        }
        if (StringUtils.hasText(request.getDateTo())) {
            totalMoneySql.append("AND DATE_FORMAT(p.bill_date,'%Y-%m-%d') <= ? ");
        }
        totalMoneySql.append("GROUP BY b.id UNION ALL ");
        totalMoneySql.append("SELECT b.id partner_id,b.partner_code,b.partner_name,c.id countryId,c.country_region_name countryName,SUM(i.after_discount) payPrice,'out' rowType,p.bills_date billDate "
            + "FROM business_partner b LEFT JOIN purchase_returns p ON b.id = p.partner_id " + "LEFT JOIN purchase_returns_item i ON p.returns_number = i.returns_number "
            + "LEFT JOIN country_region c ON b.country_region_id = c.id WHERE b.partner_type = 2 ");
        if (StringUtils.hasText(request.getDateFrom())) {
            totalMoneySql.append("AND DATE_FORMAT(p.bills_date,'%Y-%m-%d') >= ? ");
        }
        if (StringUtils.hasText(request.getDateTo())) {
            totalMoneySql.append("AND DATE_FORMAT(p.bills_date,'%Y-%m-%d') <= ? ");
        }
        totalMoneySql.append("GROUP BY b.id) t WHERE 1=1 ");
        if (StringUtils.hasText(request.getKeyword())) {
            listSql.append("AND (t.partner_code LIKE CONCAT('%',?,'%') OR t.partner_name LIKE CONCAT('%',?,'%')) ");
            totalSql.append("AND (t.partner_code LIKE CONCAT('%',?,'%') OR t.partner_name LIKE CONCAT('%',?,'%')) ");
            totalMoneySql.append("AND (t.partner_code LIKE CONCAT('%',?,'%') OR t.partner_name LIKE CONCAT('%',?,'%')) ");
            queryParam.add(request.getKeyword());
            queryParam.add(request.getKeyword());
        }
        if (StringUtils.hasText(request.getCountry())) {
            listSql.append("AND t.countryId = ? ");
            totalSql.append("AND t.countryId = ? ");
            totalMoneySql.append("AND t.countryId = ? ");
            queryParam.add(request.getCountry());
        }
        listSql.append("GROUP BY t.partner_id ");
        totalSql.append("GROUP BY t.partner_id) d ");
        totalMoneySql.append("GROUP BY t.partner_id) al ");
        totalParam.addAll(queryParam);
        if (request.getIsPaging() == true && request.getPageNo() != null && request.getPageSize() != null) {
            listSql.append(" limit ?,?");
            queryParam.add((request.getPageNo() - 1) * request.getPageSize());
            queryParam.add(request.getPageSize());
        }
    }

    private SupplierPurchaseResponse buildSupplierPurchaseList(ResultSet rs) throws SQLException {
        SupplierPurchaseResponse response = new SupplierPurchaseResponse();
        response.setSupplierNo(rs.getString("partner_code"));
        response.setSupplierName(rs.getString("partner_name"));
        response.setCountry(rs.getString("countryName"));
        response.setTotal(rs.getDouble("payPrice"));
        return response;
    }

    public PagingResponse<ProductPurchaseResponse> getProductPurchaseData(ProductPurchaseRequest request) {
        PagingResponse<ProductPurchaseResponse> response = new PagingResponse<ProductPurchaseResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        buildProductPurchaseSql(request, listSql, totalSql, queryParam, totalParam);
        ProductPurchaseSumResponse total = jdbcAccessContext.findUniqueResultWithoutSqlManager(totalSql.toString(), new RowMapper<ProductPurchaseSumResponse>() {
            @Override
            public ProductPurchaseSumResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildProductPurchaseSumList(paramResultSet);
            }
        }, totalParam.toArray());
        List<ProductPurchaseResponse> auditList = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<ProductPurchaseResponse>() {
            @Override
            public ProductPurchaseResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildProductPurchaseList(paramResultSet);
            }
        }, queryParam.toArray());
        response.setRecords(auditList);
        response.setTotalRecords(total.getCounts());
        map.put("counts", total.getQuantity());
        map.put("money", total.getMoney());
        response.setStats(map);
        return response;
    }

    private ProductPurchaseSumResponse buildProductPurchaseSumList(ResultSet rs) throws SQLException {
        ProductPurchaseSumResponse response = new ProductPurchaseSumResponse();
        response.setCounts(rs.getInt("counts"));
        response.setMoney(rs.getDouble("money"));
        response.setQuantity(rs.getDouble("quantity"));
        return response;
    }

    private ProductPurchaseResponse buildProductPurchaseList(ResultSet rs) throws SQLException {
        ProductPurchaseResponse response = new ProductPurchaseResponse();
        response.setBarCode(rs.getString("barcode"));
        response.setMoney(rs.getDouble("money"));
        response.setProductName(rs.getString("productName"));
        response.setProductNo(rs.getString("productNo"));
        response.setProductType(rs.getString("typeName"));
        response.setQuantity(rs.getString("counts"));
        response.setUnitName(rs.getString("unitName"));
        return response;
    }

    private void buildProductPurchaseSql(ProductPurchaseRequest request, StringBuilder listSql, StringBuilder totalSql, List<Object> queryParam, List<Object> totalParam) {
        listSql.append("SELECT t.id,t.material_no productNo,t.barcode,t.material_name productName,t.unit_name unitName,t.typeId,t.type_name typeName,"
            + "SUM(IF(t.type='p',t.counts,-t.counts)) counts,SUM(IF(t.type='p',t.total,-t.total)) money,t.create_date FROM "
            + "(SELECT m.id,m.material_no,m.barcode,m.material_name,mu.unit_name,mt.id typeId,mt.type_name,SUM(p.counts) counts,SUM(IF(p.is_gift=1,0,p.after_discount)) total,'p' type,p.create_date FROM material m "
            + "LEFT JOIN purchase_receipt_item p ON m.id = p.material_id LEFT JOIN material_unit mu ON m.unit_id = mu.id " + "LEFT JOIN material_type mt ON m.material_type = mt.id WHERE 1=1 ");
        if (StringUtils.hasText(request.getDateFrom())) {
            listSql.append("AND DATE_FORMAT(p.create_date,'%Y-%m-%d') >= ? ");
            queryParam.add(request.getDateFrom());
        }
        if (StringUtils.hasText(request.getDateTo())) {
            listSql.append("AND DATE_FORMAT(p.create_date,'%Y-%m-%d') <= ? ");
            queryParam.add(request.getDateTo());
        }
        listSql.append("GROUP BY m.id UNION ALL ");
        listSql.append(
            "SELECT m.id,m.material_no,m.barcode,m.material_name,mu.unit_name,mt.id typeId,mt.type_name,SUM(p.counts) counts,SUM(IF(p.is_gift=1,0,p.after_discount)) total,'r' type,p.create_date FROM material m "
                + "LEFT JOIN purchase_returns_item p ON m.id = p.material_id LEFT JOIN material_unit mu ON m.unit_id = mu.id " + "LEFT JOIN material_type mt ON m.material_type = mt.id WHERE 1=1 ");
        if (StringUtils.hasText(request.getDateFrom())) {
            listSql.append("AND DATE_FORMAT(p.create_date,'%Y-%m-%d') >= ? ");
            queryParam.add(request.getDateFrom());
        }
        if (StringUtils.hasText(request.getDateTo())) {
            listSql.append("AND DATE_FORMAT(p.create_date,'%Y-%m-%d') <= ? ");
            queryParam.add(request.getDateTo());
        }
        listSql.append("GROUP BY m.id) t WHERE 1=1 ");
        totalSql.append(
            "SELECT COUNT(1) counts,SUM(d.counts) quantity,SUM(d.money) money FROM (SELECT t.id,t.material_no productNo,t.barcode,t.material_name productName,t.unit_name unitName,t.typeId,t.type_name typeName,"
                + "SUM(IF(t.type='p',t.counts,-t.counts)) counts,SUM(IF(t.type='p',t.total,-t.total)) money,t.create_date FROM "
                + "(SELECT m.id,m.material_no,m.barcode,m.material_name,mu.unit_name,mt.id typeId,mt.type_name,SUM(p.counts) counts,SUM(IF(p.is_gift=1,0,p.after_discount)) total,'p' type,p.create_date FROM material m "
                + "LEFT JOIN purchase_receipt_item p ON m.id = p.material_id LEFT JOIN material_unit mu ON m.unit_id = mu.id " + "LEFT JOIN material_type mt ON m.material_type = mt.id WHERE 1=1 ");
        if (StringUtils.hasText(request.getDateFrom())) {
            totalSql.append("AND DATE_FORMAT(p.create_date,'%Y-%m-%d') >= ? ");
        }
        if (StringUtils.hasText(request.getDateTo())) {
            totalSql.append("AND DATE_FORMAT(p.create_date,'%Y-%m-%d') <= ? ");
        }
        totalSql.append("GROUP BY m.id UNION ALL ");
        totalSql.append(
            "SELECT m.id,m.material_no,m.barcode,m.material_name,mu.unit_name,mt.id typeId,mt.type_name,SUM(p.counts) counts,SUM(IF(p.is_gift=1,0,p.after_discount)) total,'r' type,p.create_date FROM material m "
                + "LEFT JOIN purchase_returns_item p ON m.id= p.material_id LEFT JOIN material_unit mu ON m.unit_id = mu.id " + "LEFT JOIN material_type mt ON m.material_type = mt.id WHERE 1=1 ");
        if (StringUtils.hasText(request.getDateFrom())) {
            totalSql.append("AND DATE_FORMAT(p.create_date,'%Y-%m-%d') >= ? ");
        }
        if (StringUtils.hasText(request.getDateTo())) {
            totalSql.append("AND DATE_FORMAT(p.create_date,'%Y-%m-%d') <= ? ");
        }
        totalSql.append("GROUP BY m.id) t WHERE 1=1 ");
        if (StringUtils.hasText(request.getKeyword())) {
            listSql.append("AND (t.material_no LIKE CONCAT('%',?,'%') OR t.barcode LIKE CONCAT('%',?,'%') OR t.material_name LIKE CONCAT('%',?,'%')) ");
            totalSql.append("AND (t.material_no LIKE CONCAT('%',?,'%') OR t.barcode LIKE CONCAT('%',?,'%') OR t.material_name LIKE CONCAT('%',?,'%')) ");
            queryParam.add(request.getKeyword());
            queryParam.add(request.getKeyword());
            queryParam.add(request.getKeyword());
        }
        if (StringUtils.hasText(request.getProductType())&&(!request.getProductType().equals("0"))) {
            listSql.append("AND FIND_IN_SET(t.typeId,queryAllChildMaterialType( ? )) ");
            totalSql.append("AND FIND_IN_SET(t.typeId,queryAllChildMaterialType( ? )) ");
            queryParam.add(request.getProductType());
        }
        listSql.append("GROUP BY t.id ");
        totalSql.append("GROUP BY t.id) d ");
        totalParam.addAll(queryParam);
        if (request.getIsPaging() == true && request.getPageNo() != null && request.getPageSize() != null) {
            listSql.append(" limit ?,?");
            queryParam.add((request.getPageNo() - 1) * request.getPageSize());
            queryParam.add(request.getPageSize());
        }
    }

    public PagingResponse<PurchaseAnalysisResponse> getPurchaseAnalysisData(PurchaseAnalysisRequest request) {
        PagingResponse<PurchaseAnalysisResponse> response = new PagingResponse<PurchaseAnalysisResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        buildPurchaseAnalysisSql(request, listSql, totalSql, queryParam, totalParam);
        ProductPurchaseSumResponse total = jdbcAccessContext.findUniqueResultWithoutSqlManager(totalSql.toString(), new RowMapper<ProductPurchaseSumResponse>() {
            @Override
            public ProductPurchaseSumResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildPurchaseAnalysisSumList(paramResultSet);
            }
        }, totalParam.toArray());
        List<PurchaseAnalysisResponse> auditList = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<PurchaseAnalysisResponse>() {
            @Override
            public PurchaseAnalysisResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildPurchaseAnalysisList(paramResultSet);
            }
        }, queryParam.toArray());
        response.setRecords(auditList);
        response.setTotalRecords(total.getCounts());
        map.put("counts", total.getQuantity());
        map.put("money", total.getMoney());
        response.setStats(map);
        return response;
    }

    private PurchaseAnalysisResponse buildPurchaseAnalysisList(ResultSet rs) throws SQLException {
        PurchaseAnalysisResponse response = new PurchaseAnalysisResponse();
        response.setBarcode(rs.getString("barcode"));
        response.setBillsDate(rs.getString("billsDate"));
        response.setBillsNo(rs.getString("billsNo"));
        response.setMoney(rs.getObject("total") == null ? null : rs.getDouble("total"));
        response.setProdcutName(rs.getString("material_name"));
        response.setProductNo(rs.getString("material_no"));
        response.setQuantity(rs.getDouble("counts"));
        response.setSupplierName(rs.getString("partner_name"));
        response.setTypeName(rs.getString("type_name"));
        response.setUnitName(rs.getString("unit_name"));
        response.setFavorMoney(rs.getDouble("favorable_price"));
        return response;
    }

    private ProductPurchaseSumResponse buildPurchaseAnalysisSumList(ResultSet rs) throws SQLException {
        ProductPurchaseSumResponse response = new ProductPurchaseSumResponse();
        response.setCounts(rs.getInt("totalNum"));
        response.setMoney(rs.getDouble("totalMoney"));
        response.setQuantity(rs.getDouble("counts"));
        return response;
    }

    private void buildPurchaseAnalysisSql(PurchaseAnalysisRequest request, StringBuilder listSql, StringBuilder totalSql, List<Object> queryParam, List<Object> totalParam) {
        listSql.append("SELECT t.billsNo,t.billsDate,t.partnerCode,t.partner_name,t.material_no,t.barcode,t.material_name,t.type_name,t.unit_name,t.counts counts,t.total total,t.favorable_price "
            + "FROM (SELECT pr.receipt_number billsNo,pr.bill_date billsDate,b.partner_code partnerCode,b.partner_name,m.material_no,m.barcode,m.material_name,mt.type_name,mu.unit_name,p.counts counts,IF(p.is_gift=1,null,p.after_discount) total,pr.favorable_price "
            + "FROM purchase_receipt_item p LEFT JOIN purchase_receipt pr ON p.receipt_number = pr.receipt_number "
            + "LEFT JOIN business_partner b ON pr.partner_id = b.id LEFT JOIN material m ON p.material_id = m.id LEFT JOIN material_unit mu ON m.unit_id = mu.id LEFT JOIN material_type mt ON m.material_type = mt.id "
            + "UNION ALL SELECT pr.returns_number billsNo,pr.bills_date billsDate,b.partner_code partnerCode,b.partner_name,m.material_no,m.barcode,m.material_name,mt.type_name,mu.unit_name,-p.counts counts,IF(p.is_gift=1,null,-p.after_discount) total,pr.favorable_price "
            + "FROM purchase_returns_item p LEFT JOIN purchase_returns pr ON p.returns_number = pr.returns_number "
            + "LEFT JOIN business_partner b ON pr.partner_id = b.id LEFT JOIN material m ON p.material_id = m.id "
            + "LEFT JOIN material_unit mu ON m.unit_id = mu.id LEFT JOIN material_type mt ON m.material_type = mt.id ) t WHERE 1=1 ");
        totalSql.append("SELECT COUNT(t.billsNo) totalNum,SUM(t.counts) counts,SUM(t.total) totalMoney "
            + "FROM (SELECT pr.receipt_number billsNo,pr.bill_date billsDate,b.partner_code partnerCode,b.partner_name,m.material_no,m.barcode,m.material_name,mt.type_name,mu.unit_name,p.counts counts,IF(p.is_gift=1,null,p.after_discount) total,pr.favorable_price "
            + "FROM purchase_receipt_item p LEFT JOIN purchase_receipt pr ON p.receipt_number = pr.receipt_number "
            + "LEFT JOIN business_partner b ON pr.partner_id = b.id LEFT JOIN material m ON p.material_id = m.id LEFT JOIN material_unit mu ON m.unit_id = mu.id LEFT JOIN material_type mt ON m.material_type = mt.id "
            + "UNION ALL SELECT pr.returns_number billsNo,pr.bills_date billsDate,b.partner_code partnerCode,b.partner_name,m.material_no,m.barcode,m.material_name,mt.type_name,mu.unit_name,-p.counts counts,IF(p.is_gift=1,null,-p.after_discount) total,pr.favorable_price "
            + "FROM purchase_returns_item p LEFT JOIN purchase_returns pr ON p.returns_number = pr.returns_number "
            + "LEFT JOIN business_partner b ON pr.partner_id = b.id LEFT JOIN material m ON p.material_id = m.id "
            + "LEFT JOIN material_unit mu ON m.unit_id = mu.id LEFT JOIN material_type mt ON m.material_type = mt.id ) t WHERE 1=1 ");
        if (StringUtils.hasText(request.getDateFrom())) {
            listSql.append("AND DATE_FORMAT(t.billsDate,'%Y-%m-%d') >= ? ");
            totalSql.append("AND DATE_FORMAT(t.billsDate,'%Y-%m-%d') >= ? ");
            queryParam.add(request.getDateFrom());
        }
        if (StringUtils.hasText(request.getDateTo())) {
            listSql.append("AND DATE_FORMAT(t.billsDate,'%Y-%m-%d') <= ? ");
            totalSql.append("AND DATE_FORMAT(t.billsDate,'%Y-%m-%d') <= ? ");
            queryParam.add(request.getDateTo());
        }
        if (StringUtils.hasText(request.getProductNo())) {
            listSql.append("AND t.material_no = ? ");
            totalSql.append("AND t.material_no = ? ");
            queryParam.add(request.getProductNo());
        }
        if (StringUtils.hasText(request.getSupplierNo())) {
            listSql.append("AND t.partnerCode = ? ");
            totalSql.append("AND t.partnerCode = ? ");
            queryParam.add(request.getSupplierNo());
        }
        listSql.append("ORDER BY t.billsNo, t.billsDate DESC ");
        totalParam.addAll(queryParam);
        if (request.getIsPaging() && request.getPageNo() != null && request.getPageSize() != null) {
            listSql.append(" limit ?,?");
            queryParam.add((request.getPageNo() - 1) * request.getPageSize());
            queryParam.add(request.getPageSize());
        }
    }

    public PagingResponse<PurchaseOrderNumResponse> getPurchaseOrderNumData(PurchaseOrderNumRequest request) {
        PagingResponse<PurchaseOrderNumResponse> response = new PagingResponse<PurchaseOrderNumResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        buildPurchaseOrderNumSql(request, listSql, totalSql, queryParam, totalParam);
        PurchaseOrderNumSumResponse total = jdbcAccessContext.findUniqueResultWithoutSqlManager(totalSql.toString(), new RowMapper<PurchaseOrderNumSumResponse>() {
            @Override
            public PurchaseOrderNumSumResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildPurchaseOrderNumSumList(paramResultSet);
            }
        }, totalParam.toArray());
        List<PurchaseOrderNumResponse> list = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<PurchaseOrderNumResponse>() {
            @Override
            public PurchaseOrderNumResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildPurchaseOrderNumList(paramResultSet);
            }
        }, queryParam.toArray());
        response.setRecords(list);
        response.setTotalRecords(total.getPageCounts());
        map.put("counts", total.getSumCounts());
        map.put("receiptCounts", total.getSumReceiptCounts());
        response.setStats(map);
        return response;
    }

    private PurchaseOrderNumResponse buildPurchaseOrderNumList(ResultSet rs) throws SQLException {
        PurchaseOrderNumResponse response = new PurchaseOrderNumResponse();
        response.setBarcode(rs.getString("barcode"));
        response.setBillsDate(rs.getString("bill_date"));
        response.setCounts(rs.getDouble("counts"));
        response.setOrderNo(rs.getString("order_number"));
        response.setProductName(rs.getString("material_name"));
        response.setProductNo(rs.getString("material_no"));
        response.setProductType(rs.getString("type_name"));
        response.setReceiptCounts(rs.getDouble("receipt_counts"));
        response.setAfterDiscount(rs.getDouble("after_discount"));
        response.setSupplierName(rs.getString("partner_name"));
        response.setUnitName(rs.getString("unit_name"));
        return response;
    }

    private PurchaseOrderNumSumResponse buildPurchaseOrderNumSumList(ResultSet rs) throws SQLException {
        PurchaseOrderNumSumResponse response = new PurchaseOrderNumSumResponse();
        response.setPageCounts(rs.getInt("pageCounts"));
        response.setSumCounts(rs.getDouble("counts"));
        response.setSumReceiptCounts(rs.getDouble("receiptCounts"));
        return response;
    }

    private void buildPurchaseOrderNumSql(PurchaseOrderNumRequest request, StringBuilder listSql, StringBuilder totalSql, List<Object> queryParam, List<Object> totalParam) {
        listSql.append("SELECT p.order_number,p.bill_date,b.id supplierId,b.partner_name,m.material_no,m.barcode,m.material_name,mt.type_name,mt.id typeId,"
            + "mu.unit_name,i.counts,i.receipt_counts,i.after_discount FROM purchase_order p LEFT JOIN purchase_order_item i ON p.order_number = i.order_number LEFT JOIN "
            + "business_partner b ON p.partner_id = b.id LEFT JOIN material m ON i.material_id = m.id LEFT JOIN material_type mt ON m.material_type = mt.id "
            + "LEFT JOIN material_unit mu ON m.unit_id = mu.id WHERE 1=1 ");
        totalSql.append("SELECT COUNT(1) pageCounts,SUM(i.counts) counts,SUM(i.receipt_counts) receiptCounts "
            + "FROM purchase_order p LEFT JOIN purchase_order_item i ON p.order_number = i.order_number LEFT JOIN business_partner b ON p.partner_id = b.id "
            + "LEFT JOIN material m ON i.material_id = m.id LEFT JOIN material_type mt ON m.material_type = mt.id LEFT JOIN material_unit mu ON m.unit_id = mu.id WHERE 1=1 ");
        if (StringUtils.hasText(request.getDateFrom())) {
            listSql.append("AND DATE_FORMAT(p.bill_date,'%Y-%m-%d') >= ? ");
            totalSql.append("AND DATE_FORMAT(p.bill_date,'%Y-%m-%d') >= ? ");
            queryParam.add(request.getDateFrom());
        }
        if (StringUtils.hasText(request.getDateTo())) {
            listSql.append("AND DATE_FORMAT(p.bill_date,'%Y-%m-%d') <= ? ");
            totalSql.append("AND DATE_FORMAT(p.bill_date,'%Y-%m-%d') <= ? ");
            queryParam.add(request.getDateTo());
        }
        if (StringUtils.hasText(request.getKeyword())) {
            listSql.append("AND (m.material_no LIKE CONCAT('%',?,'%') OR m.barcode LIKE CONCAT('%',?,'%') OR m.material_name LIKE CONCAT('%',?,'%')) ");
            totalSql.append("AND (m.material_no LIKE CONCAT('%',?,'%') OR m.barcode LIKE CONCAT('%',?,'%') OR m.material_name LIKE CONCAT('%',?,'%')) ");
            queryParam.add(request.getKeyword());
            queryParam.add(request.getKeyword());
            queryParam.add(request.getKeyword());
        }
        if (StringUtils.hasText(request.getProductType())&&(!request.getProductType().equals("0"))) {
            listSql.append("AND FIND_IN_SET(mt.id,queryAllChildMaterialType( ? )) ");
            totalSql.append("AND FIND_IN_SET(mt.id,queryAllChildMaterialType( ? )) ");
            queryParam.add(request.getProductType());
        }
        if (StringUtils.hasText(request.getOrderNo())) {
            listSql.append("AND p.order_number LIKE CONCAT('%',?,'%') ");
            totalSql.append("AND p.order_number LIKE CONCAT('%',?,'%') ");
            queryParam.add(request.getOrderNo());
        }
        if (StringUtils.hasText(request.getSupplierId())) {
            listSql.append("AND b.id = ? ");
            totalSql.append("AND b.id = ? ");
            queryParam.add(request.getSupplierId());
        }
        listSql.append("ORDER BY p.bill_date DESC ");
        totalParam.addAll(queryParam);
        if (request.getIsPaging() == true && request.getPageNo() != null && request.getPageSize() != null) {
            listSql.append(" limit ?,?");
            queryParam.add((request.getPageNo() - 1) * request.getPageSize());
            queryParam.add(request.getPageSize());
        }
    }
}
