package com.siping.service.inventory.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.smartone.inventory.request.AddInventoryCheckItemRequest;
import com.siping.smartone.inventory.request.DeleteInventoryCheckItemRequest;
import com.siping.smartone.inventory.request.GetInventoryCheckItemListRequest;
import com.siping.smartone.inventory.response.GetInventoryCheckItemResponse;
import com.siping.smartone.material.response.GetMaterialResponseForInventoryCheck;

@Repository
public class InventoryCheckItemRestRepository {
    @Autowired
    private JDBCAccessContext jdbcAccessContext;

    public Boolean add(AddInventoryCheckItemRequest request) {
        if (null != get(request.getCheckNumber())) {
            throw new BusinessProcessException("不能重复添加盘点！");
        }
        Object[] addParams = new Object[] { request.getCheckNumber(), request.getInventoryId(), request.getMaterialId(), request.getBatchNumber(), request.getProductionDate(), request.getCounts(),
                request.getPrice(), request.getTotal(), request.getIsGift(), request.getRemark(), request.getAvailableAmount(), request.getActualAmount(), request.getCreateDate(),
                request.getCreateBy() };
        int add = jdbcAccessContext.execute("INVENTORYCHECK.SQL_ADD_INVENTORYCHECK", addParams);
        if (-1 == add) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean edit(AddInventoryCheckItemRequest request) {
        if (null == get(request.getCheckNumber()))
            throw new BusinessProcessException("库存盘点不存在，无法编辑！");
        List<Object> editParam = new ArrayList<Object>();
        int edit = jdbcAccessContext.executeWithoutSqlManager(buildEditSql(request, editParam), editParam.toArray());
        if (-1 == edit) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private String buildEditSql(AddInventoryCheckItemRequest request, List<Object> list) {
        StringBuilder sql = new StringBuilder("UPDATE inventory_check SET ");
        assemblePrams(request, list, sql);
        sql.append(" WHERE check_number=? ");
        list.add(request.getCheckNumber());
        return sql.toString();
    }

    private void assemblePrams(AddInventoryCheckItemRequest request, List<Object> list, StringBuilder sql) {

    }

    public GetInventoryCheckItemResponse get(String checkNumber) {
        List<Object> queryParam = new ArrayList<Object>();
        List<GetInventoryCheckItemResponse> list = jdbcAccessContext.findWithoutSqlManager(buildGetSql(checkNumber, queryParam), new RowMapper<GetInventoryCheckItemResponse>() {
            @Override
            public GetInventoryCheckItemResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildInventoryCheckItemResponse(rs);
            }
        }, queryParam.toArray());
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    public static GetInventoryCheckItemResponse buildInventoryCheckItemResponse(final ResultSet rs) throws SQLException {
        GetInventoryCheckItemResponse m = new GetInventoryCheckItemResponse();
        m.setId(rs.getInt("id"));
        m.setCheckNumber(rs.getString("check_number"));
        m.setInventoryId(rs.getInt("inventory_id"));
        m.setMaterialId(rs.getString("material_id"));
        m.setBatchNumber(rs.getString("batch_number"));
        m.setProductionDate(rs.getString("production_date"));
        m.setCounts(rs.getDouble("counts"));
        m.setPrice(rs.getDouble("price"));
        m.setTotal(rs.getDouble("total"));
        m.setIsGift(rs.getBoolean("is_gift"));
        m.setRemark(rs.getString("remark"));
        m.setAvailableAmount(rs.getDouble("available_amount"));
        m.setActualAmount(rs.getDouble("actual_amount"));
        m.setCreateDate(rs.getString("create_date"));
        m.setCreateBy(rs.getInt("create_by"));
        return m;
    }

    public static GetInventoryCheckItemResponse buildInventoryCheckItemResponseAddMaterialInfo(final ResultSet rs) throws SQLException {
        GetInventoryCheckItemResponse m = new GetInventoryCheckItemResponse();
        m.setMaterial(new GetMaterialResponseForInventoryCheck());
        m.setId(rs.getInt("id"));
        m.setCheckNumber(rs.getString("check_number"));
        m.setInventoryId(rs.getInt("inventory_id"));
        m.setMaterialId(rs.getString("material_id"));
        m.getMaterial().setMaterialName(rs.getString("materialName"));
        m.getMaterial().setMaterialNo(rs.getString("materialNo"));
        m.getMaterial().setBarcode(rs.getString("barcode"));
        m.getMaterial().setSpecificationsModel(rs.getString("specificationsModel"));
        m.getMaterial().setUnitName(rs.getString("unitName"));
        m.setBatchNumber(rs.getString("batch_number"));
        m.setProductionDate(rs.getString("production_date"));
        m.setCounts(rs.getDouble("counts"));
        m.setPrice(rs.getDouble("price"));
        m.setTotal(rs.getDouble("total"));
        m.setIsGift(rs.getBoolean("is_gift"));
        m.setRemark(rs.getString("remark"));
        m.setAvailableAmount(rs.getDouble("available_amount"));
        m.setActualAmount(rs.getDouble("actual_amount"));
        m.setCreateDate(rs.getString("create_date"));
        m.setCreateBy(rs.getInt("create_by"));
        return m;
    }
    
    private String buildGetSql(String checkNumber, List<Object> queryParam) {
        StringBuilder sql = new StringBuilder(
            "SELECT c.check_number,c.check_storage,c.summary,c.is_draft,c.is_loss,c.total_price,c.gift_price,c.create_date,c.create_by,c.auditor,c.audit_date,c.owner from inventory_check c ");
        if (StringUtils.hasText(checkNumber)) {
            sql.append(" where c.check_number=? ");
            queryParam.add(checkNumber);
        }
        return sql.toString();
    }

    public PagingResponse<GetInventoryCheckItemResponse> getList(GetInventoryCheckItemListRequest request) { // 分页查询
        List<Object> queryParam = new ArrayList<Object>();
        PagingResponse<GetInventoryCheckItemResponse> response = new PagingResponse<GetInventoryCheckItemResponse>();
        StringBuilder querySql = new StringBuilder(
            "SELECT c.check_number,c.check_storage,c.summary,c.is_draft,c.is_loss,c.total_price,c.gift_price,c.create_date,c.create_by,c.auditor,c.aodit_date,c.owner from inventory_check c");
        StringBuilder queryTotal = new StringBuilder("SELECT COUNT(0) from inventory_check c");
        if (StringUtils.hasText(request.getPageNo()) && StringUtils.hasText(request.getPageSize())) {
            querySql.append(" limit ?,? ");
            queryParam.add((Integer.valueOf(request.getPageNo()) - 1) * Integer.valueOf(request.getPageSize()));
            queryParam.add(Integer.valueOf(request.getPageSize()));
        }
        List<GetInventoryCheckItemResponse> list = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<GetInventoryCheckItemResponse>() {
            @Override
            public GetInventoryCheckItemResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildInventoryCheckItemResponse(rs);
            }
        }, queryParam.toArray());
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotal.toString(), new Object[] {});
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    public Boolean delete(DeleteInventoryCheckItemRequest request) {
        if (null == request.getIds()) {
            throw new BusinessProcessException("库存盘点不存在，无法删除！");
        }
        String updateBy = request.getUpdateBy();
        List<String> ids = request.getIds();
        List<Object[]> deleteParams = new LinkedList<Object[]>();
        for (String id : ids) {
            Object[] obj = new Object[] { updateBy, id };
            deleteParams.add(obj);
        }
        int[] delete = jdbcAccessContext.batchExecute("POSTPERIOD.SQL_DELETE_POSTPERIOD", deleteParams);
        if (Arrays.asList(delete).contains(-1)) {
            throw new BusinessProcessException("批量删除某条信息时失败,已回滚");
        }
        return Boolean.TRUE;
    }
}
