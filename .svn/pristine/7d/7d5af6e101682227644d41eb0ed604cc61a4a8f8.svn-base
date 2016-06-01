package com.siping.service.inventory.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.service.material.service.MaterialTypeRestService;
import com.siping.smartone.inventory.request.GetInventoryAuditListRequest;
import com.siping.smartone.inventory.request.GetInventoryBalanceRequest;
import com.siping.smartone.inventory.response.GetInventoryAuditResponse;
import com.siping.smartone.inventory.response.GetInventoryBalanceResponse;

@Repository
public class InventoryAuditRestRepository extends BillsRepository { // 需要生成单据编号的Repository才需要继承BillsRepository类
    @Autowired
    private MaterialTypeRestService materialTypeRestService;

    public PagingResponse<GetInventoryBalanceResponse> getList(GetInventoryBalanceRequest request) {
        PagingResponse<GetInventoryBalanceResponse> response = new PagingResponse<GetInventoryBalanceResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        List<Object> queryList = new ArrayList<Object>();
        // loadDynamicSql(listSql, totalSql, queryList, request);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(totalSql.toString(), queryList.toArray());
        listSql.append(" ORDER BY m.material_no ");
        if (null != request.getPageNo() && null != request.getPageSize()) {
            listSql.append(" limit ?,? ");
            queryList.add((request.getPageNo() - 1) * request.getPageSize());
            queryList.add(request.getPageSize());
        }
        List<GetInventoryAuditResponse> auditList = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<GetInventoryAuditResponse>() {
            @Override
            public GetInventoryAuditResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildInventoryAudit(paramResultSet);
            }
        }, queryList.toArray());
        // response.setRecords(auditList);
        response.setTotalRecords(total);
        return response;
    }

    private void loadDynamicSql(StringBuilder listSql, StringBuilder totalSql, List<Object> queryList, GetInventoryAuditListRequest request) {
        listSql
            .append("SELECT m.material_no,m.material_name,u.unit_name,i.inventory_amount,SUM(IF(l.is_inbound,l.purchase_price,0)*IF(l.is_inbound,l.counts,0)) / sum(IF(l.is_inbound,l.counts,0)) cost,SUM(CONVERT(CONCAT(IF(l.is_inbound,'+','-'),l.purchase_price),DECIMAL)*l.counts) inventoryTotalPrice FROM material m");
        totalSql.append("SELECT count(DISTINCT i.material_id) FROM material m ");
        if (null != request.getMaterialTypeId()) {
            listSql
                .append(" INNER JOIN material_type t ON m.material_type =t.id LEFT JOIN inventory i ON m.id = i.material_id LEFT JOIN inventory_log l ON l.inventory_id = i.id LEFT JOIN material_unit u ON m.unit_id = u.id LEFT JOIN STORAGE s ON i.storage_id = s.id WHERE m.material_no IS NOT NULL and t.id in (?) ");
            totalSql
                .append(" INNER JOIN material_type t ON m.material_type =t.id LEFT JOIN inventory i ON m.id = i.material_id LEFT JOIN inventory_log l ON l.inventory_id = i.id LEFT JOIN material_unit u ON m.unit_id = u.id LEFT JOIN STORAGE s ON i.storage_id = s.id WHERE m.material_no IS NOT NULL and t.id in (?) ");
            queryList.add(materialTypeRestService.getAllTypeChilds(request.getMaterialTypeId() + ""));
        } else {
            listSql
                .append(" LEFT JOIN inventory i ON m.id = i.material_id LEFT JOIN inventory_log l ON l.inventory_id = i.id LEFT JOIN material_unit u ON m.unit_id = u.id LEFT JOIN STORAGE s ON i.storage_id = s.id WHERE m.material_no IS NOT NULL");
            totalSql
                .append(" LEFT JOIN inventory i ON m.id = i.material_id LEFT JOIN inventory_log l ON l.inventory_id = i.id LEFT JOIN material_unit u ON m.unit_id = u.id LEFT JOIN STORAGE s ON i.storage_id = s.id WHERE m.material_no IS NOT NULL");
        }
        if (StringUtils.hasText(request.getStorageNo())) {
            listSql.append(" AND s.storage_no LIKE CONCAT('%',?,'%')");
            totalSql.append(" AND s.storage_no LIKE CONCAT('%',?,'%')");
            queryList.add(request.getStorageNo());
        }
        if (StringUtils.hasText(request.getStorageName())) {
            listSql.append(" AND s.storage_name LIKE CONCAT('%',?,'%') ");
            totalSql.append(" AND s.storage_name LIKE CONCAT('%',?,'%') ");
            queryList.add(request.getStorageName());
        }
        if (StringUtils.hasText(request.getMaterialNo())) {
            listSql.append(" AND m.material_no LIKE CONCAT('%',?,'%')");
            totalSql.append(" AND m.material_no LIKE CONCAT('%',?,'%')");
            queryList.add(request.getMaterialNo());
        }
        if (StringUtils.hasText(request.getMaterialName())) {
            listSql.append(" AND m.material_name LIKE CONCAT('%',?,'%')");
            totalSql.append(" AND m.material_name LIKE CONCAT('%',?,'%')");
            queryList.add(request.getMaterialName());
        }
        if (StringUtils.hasText(request.getBarcode())) {
            listSql.append(" AND m.barcode LIKE CONCAT('%',?,'%')");
            totalSql.append(" AND m.barcode LIKE CONCAT('%',?,'%')");
            queryList.add(request.getBarcode());
        }
        listSql.append(" GROUP BY i.material_id ");
    }

    private GetInventoryAuditResponse buildInventoryAudit(ResultSet rs) throws SQLException {
        GetInventoryAuditResponse r = new GetInventoryAuditResponse();
        r.setMaterialNo(rs.getString("material_no"));
        r.setMaterialName(rs.getString("material_name"));
        r.setMaterialUnitName(rs.getString("unit_name"));
        r.setCost(rs.getDouble("cost"));
        r.setCounts(rs.getDouble("inventory_amount"));
        r.setInventoryTotalPrice(rs.getDouble("inventoryTotalPrice"));
        return r;
    }
}
