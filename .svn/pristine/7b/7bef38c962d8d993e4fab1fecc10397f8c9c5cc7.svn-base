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

import com.siping.smartone.inventory.request.ProductExpirationRequest;
import com.siping.smartone.inventory.response.ProductExpirationResponse;

@Repository
public class ProductReportRestRepository {

    @Autowired
    protected JDBCAccessContext jdbcAccessContext;

    public PagingResponse<ProductExpirationResponse> getExpirationList(ProductExpirationRequest request) {
        PagingResponse<ProductExpirationResponse> response = new PagingResponse<ProductExpirationResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        buildExpirationSql(request, listSql, totalSql, queryParam, totalParam);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(totalSql.toString(), totalParam.toArray());
        List<ProductExpirationResponse> auditList = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<ProductExpirationResponse>() {
            @Override
            public ProductExpirationResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildExpirationList(paramResultSet);
            }
        }, queryParam.toArray());
        response.setRecords(auditList);
        response.setTotalRecords(total);
        return response;
    }

    private void buildExpirationSql(ProductExpirationRequest request, StringBuilder listSql, StringBuilder totalSql, List<Object> queryParam, List<Object> totalParam) {
        listSql.append("SELECT m.id,m.material_no,m.material_name,m.expiration_date expDays,m.foreign_name,m.material_type,m.brand,m.specifications_model,m.season,"
            + "m.is_purchase,m.is_sell,m.is_inventory,m.unit_id,u.unit_name unitName,m.barcode,m.shops,m.is_batch,m.partner_id,p.partner_name partnerName,m.min_order,m.max_inventory,"
            + "m.min_inventory,m.is_enable,m.description,m.is_delete,m.transaction_id,m.create_date,m.create_by,m.update_date,m.update_by,t.type_name materialTypeName "
            + ",mb.production_date,mb.expiration_date,mb.is_inbound,mb.batch_number,s.storage_no,s.storage_name,sl.location_no,"
            + "sl.location_name,SUM(IF(mb.is_inbound = 1,mb.counts,0-mb.counts)) locationAmount,DATEDIFF(mb.expiration_date,NOW()) daysDiff "
            + "from inventory_log l LEFT JOIN  inventory i  ON l.inventory_id=i.id LEFT JOIN material m ON m.id = i.material_id LEFT JOIN material_unit u ON m.unit_id = u.id "
            + "LEFT JOIN business_partner p ON m.partner_id = p.id LEFT JOIN material_type t ON m.material_type = t.id LEFT JOIN storage_location sl ON i.storage_location_id = sl.id "
            + "LEFT JOIN `storage` s ON i.storage_id = s.id LEFT JOIN material_batch mb ON l.batch_uuid=mb.uuid "
            + "WHERE m.is_enable = 1 and m.is_delete = 0 and DATEDIFF(mb.expiration_date,NOW()) <= ? ");
        totalSql.append("SELECT COUNT(1) FROM (SELECT m.id from inventory_log l LEFT JOIN  inventory i  ON l.inventory_id=i.id LEFT JOIN material m ON m.id = i.material_id "
            + "LEFT JOIN material_unit u ON m.unit_id = u.id LEFT JOIN business_partner p ON m.partner_id = p.id LEFT JOIN material_type t ON m.material_type = t.id "
            + "LEFT JOIN storage_location sl ON i.storage_location_id = sl.id LEFT JOIN `storage` s ON i.storage_id = s.id LEFT JOIN material_batch mb ON l.batch_uuid=mb.uuid "
            + "WHERE m.is_enable = 1 and m.is_delete = 0 and DATEDIFF(mb.expiration_date,NOW()) <= ? ");
        queryParam.add(request.getRemainDays());
        totalParam.add(request.getRemainDays());
        if (request.getSupplierId() != null) {
            listSql.append("and m.partner_id = ? ");
            totalSql.append("and m.partner_id = ? ");
            queryParam.add(request.getSupplierId());
            totalParam.add(request.getSupplierId());
        }
        listSql.append("GROUP BY m.material_no,sl.location_no,mb.batch_number HAVING SUM(IF(mb.is_inbound = 1,mb.counts,0-mb.counts)) > 0 ORDER BY daysDiff ");
        totalSql.append("GROUP BY m.material_no,sl.location_no,mb.batch_number HAVING SUM(IF(mb.is_inbound = 1,mb.counts,0-mb.counts)) > 0 ) ab");
        if (request.getIsPaging() == true && request.getPageNo() != null && request.getPageSize() != null) {
            listSql.append(" limit ?,?");
            queryParam.add((request.getPageNo() - 1) * request.getPageSize());
            queryParam.add(request.getPageSize());
        }
    }

    private ProductExpirationResponse buildExpirationList(ResultSet rs) throws SQLException {
        ProductExpirationResponse response = new ProductExpirationResponse();
        response.setBarcode(rs.getString("barcode"));
        response.setBatchCode(rs.getString("batch_number"));
        response.setMaterialNo(rs.getString("material_no"));
        response.setMaterialName(rs.getString("material_name"));
        response.setForeignName(rs.getString("foreign_name"));
        response.setMaterialTypeName(rs.getString("materialTypeName"));
        response.setProductDate(rs.getString("production_date"));
        response.setExpireDate(rs.getString("expiration_date"));
        response.setStorageNo(rs.getString("storage_no"));
        response.setStorageName(rs.getString("storage_name"));
        response.setLocationNo(rs.getString("location_no"));
        response.setLocationName(rs.getString("location_name"));
        response.setSpecificationsModel(rs.getString("specifications_model"));
        response.setUnitName(rs.getString("unitName"));
        response.setStockQuantity(rs.getFloat("locationAmount"));
        response.setRemainDays(rs.getInt("daysDiff"));
        response.setPartnerName(rs.getString("partnerName"));
        return response;
    }

}
