package com.siping.service.material.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.smartone.material.request.AddMaterialGroupRelationRequest;
import com.siping.smartone.material.request.GetMaterialConditionRequest;
import com.siping.smartone.material.request.GetMaterialRequest;
import com.siping.smartone.material.response.GetMaterialGroupResponse;
import com.siping.smartone.material.response.GetMaterialResponse;

@Repository
public class MaterialGroupRelationRestRepository {
    @Autowired
    private JDBCAccessContext jdbcAccessContext;

    public Boolean add(AddMaterialGroupRelationRequest request) {
        verifyRequest(request);
        List<Object[]> params = new ArrayList<Object[]>();
        for (Integer materialId : request.getMaterialIdList()) {
            Object[] arr = new Object[] { materialId, request.getMaterialGroupId(), request.getIsDelete(), request.getCreateBy() };
            params.add(arr);
        }
        int[] batchExecute = jdbcAccessContext.batchExecute("MATERIAL.SQL_ADD_MATERIAL_GROUP_RELATION", params);
        if (Arrays.asList(batchExecute).contains(-1)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;

    }

    private void verifyRequest(AddMaterialGroupRelationRequest request) {
        // 判断商品组是否存在
        Integer isExistGroup = jdbcAccessContext.findInteger("MATERIAL.SQL_GET_MATERIAL_GROUP_IS_EXIST", new Object[] { request.getMaterialGroupId() });
        if (null == isExistGroup) {
            throw new BusinessProcessException("商品组不存在，无法新增！");
        }
        // 判断所有物料id是否都存在于数据库
        Integer count = jdbcAccessContext.findInteger("MATERIAL.SQL_GET_MATERIAL_ALL_IS_EXIST", new Object[] { request.getMaterialGroupId().toString().replace("[", "").replace("]", "") });
        if (null == count || (null != count && count < request.getMaterialIdList().size())) {
            throw new BusinessProcessException("一些商品不存在，无法新增！");
        }
    }

    public Boolean edit(AddMaterialGroupRelationRequest request) {
        int execute = jdbcAccessContext.execute("MATERIAL.SQL_DELETE_MATERIAL_GROUP_RELATION_BY_GROUP_ID", new Object[] { request.getMaterialGroupId() });
        if (-1 == execute) {
            return Boolean.FALSE;
        }
        return add(request);
    }

    public List<GetMaterialGroupResponse> get(GetMaterialRequest request) {
        List<Object> queryParam = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder(
            "SELECT DISTINCT g.id,g.group_name,g.description,g.is_delete,g.create_date,g.create_by FROM material_group_relation r LEFT JOIN material m ON r.material_id = m.material_no LEFT JOIN material_group g ON r.material_group_id = g.id WHERE g.is_delete=FALSE ");
        if (StringUtils.hasText(request.getId())) {
            sql.append("and m.id=? ");
            queryParam.add(request.getId());
        }
        if (StringUtils.hasText(request.getMaterialNo())) {
            sql.append("AND m.material_no=? ");
            queryParam.add(request.getMaterialNo());
        }
        return jdbcAccessContext.findWithoutSqlManager(sql.toString(), new RowMapper<GetMaterialGroupResponse>() {
            @Override
            public GetMaterialGroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                GetMaterialGroupResponse group = new GetMaterialGroupResponse();
                group.setGroupName(rs.getString("group_name"));
                group.setId(rs.getInt("id") + "");
                group.setDescription(rs.getString("description"));
                group.setIsDelete(rs.getBoolean("is_delete"));
                group.setCreateDate(DateUtils.date(rs.getDate("create_date"), rs.getTime("create_date")));
                group.setCreateBy(rs.getInt("create_by"));
                return group;
            }
        }, queryParam.toArray());
    }

    private static GetMaterialResponse buildMaterialResponse(final ResultSet rs) throws SQLException {
        GetMaterialResponse m = new GetMaterialResponse();
        m.setId(rs.getInt("id"));
        m.setMaterialNo(rs.getString("material_no"));
        m.setMaterialName(rs.getString("material_name"));
        m.setForeignName(rs.getString("foreign_name"));
        m.setBrand(rs.getString("brand"));
        m.setSpecificationsModel(rs.getString("specifications_model"));
        m.setSeason(rs.getString("season"));
        m.setIsPurchase(rs.getBoolean("is_purchase"));
        m.setIsSell(rs.getBoolean("is_sell"));
        m.setIsInventory(rs.getBoolean("is_inventory"));
        m.setUnitId(rs.getInt("unit_id"));
        m.setUnitName(rs.getString("unit_name"));
        m.setBarcode(rs.getString("barcode"));
        m.setShopName(rs.getString("shops"));
        m.setIsBatch(rs.getBoolean("is_batch"));
        m.setExpirationDate(rs.getInt("expiration_date"));
        m.setMinOrder(rs.getInt("min_order"));
        m.setMinInventory(rs.getInt("min_inventory"));
        m.setMaxInventory(rs.getInt("max_inventory"));
        m.setIsEnable(rs.getBoolean("is_enable"));
        m.setDescription(rs.getString("description"));
        m.setCreateDate(rs.getString("create_date"));
        m.setCreateBy(rs.getInt("create_by"));
        return m;
    }

    public PagingResponse<GetMaterialResponse> getList(GetMaterialConditionRequest request) {
        PagingResponse<GetMaterialResponse> pagingResponse = new PagingResponse<GetMaterialResponse>();
        List<Object> queryParams = new ArrayList<Object>();
        List<Object> queryTotalParams = new ArrayList<Object>();
        StringBuilder querySql = new StringBuilder();
        StringBuilder queryTotal = new StringBuilder();
        buildGetListSql(request, querySql, queryTotal, queryParams, queryTotalParams);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotal.toString(), queryTotalParams.toArray());
        List<GetMaterialResponse> response = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<GetMaterialResponse>() {
            @Override
            public GetMaterialResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildMaterialResponse(rs);
            }
        }, queryParams.toArray());
        pagingResponse.setRecords(response);
        pagingResponse.setTotalRecords(total);
        return pagingResponse;
    }

    public Boolean delete(String id) {
        List<Object[]> ids = new ArrayList<Object[]>();
        ids.add(new Object[] { id });
        return deleteMaterialGroupRelation(ids);
    }

    private Boolean deleteMaterialGroupRelation(List<Object[]> ids) {
        int[] isDelete = jdbcAccessContext.batchExecute("MATERIAL.SQL_DELETE_MATERIAL_GROUP_RELATION", ids);
        if (Arrays.asList(isDelete).contains(-1)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private void buildGetListSql(GetMaterialConditionRequest request, StringBuilder querySql, StringBuilder queryTatal, List<Object> list, List<Object> queryTotalParams) {
        querySql
            .append("SELECT m.id, material_no, m.material_name,m.foreign_name,m.brand,m.specifications_model,m.season,m.is_purchase,m.is_sell,m.is_inventory,m.unit_id,u.unit_name,m.barcode,m.shops,m.is_batch,m.expiration_date,m.partner_id,"
                + "m.min_order,m.min_inventory,m.max_inventory,m.is_enable,m.description,m.create_date,m.create_by FROM material m LEFT JOIN material_unit u ON m.unit_id = u.id ");
        queryTatal.append(" SELECT count(1) FROM material m LEFT JOIN material_unit u ON m.unit_id = u.id ");
        if (null != request.getMaterialGroupId()) {
            querySql.append(" JOIN material_group_relation r ON m.id = r.material_id WHERE r.material_group_id =? ");
            queryTatal.append(" JOIN material_group_relation r ON m.id = r.material_id WHERE r.material_group_id =? ");
            list.add(request.getMaterialGroupId());
            queryTotalParams.add(request.getMaterialGroupId());
        }
        if (StringUtils.hasText(request.getKeyword())) {
            querySql.append(list.size() == 0 ? " where (m.material_name LIKE CONCAT('%' ,?, '%') or m.material_no LIKE CONCAT('%' ,?, '%'))" : " AND (m.material_name LIKE CONCAT('%' ,?, '%') or m.material_no LIKE CONCAT('%' ,?, '%')) ");
            queryTatal.append(list.size() == 0 ? " where (m.material_name LIKE CONCAT('%' ,?, '%') or m.material_no LIKE CONCAT('%' ,?, '%')) " : " AND (m.material_name LIKE CONCAT('%' ,?, '%') or m.material_no LIKE CONCAT('%' ,?, '%')) ");
            list.add(request.getKeyword());
            list.add(request.getKeyword());
            queryTotalParams.add(request.getKeyword());
            queryTotalParams.add(request.getKeyword());
        }
        if (StringUtils.hasText(request.getBarcode())) {
            querySql.append(list.size() == 0 ? " where m.barcode LIKE CONCAT('%' ,?, '%') " : " AND m.barcode LIKE CONCAT('%' ,?, '%')  ");
            queryTatal.append(list.size() == 0 ? " where m.barcode LIKE CONCAT('%' ,?, '%') " : " AND m.barcode LIKE CONCAT('%' ,?, '%')  ");
            list.add(request.getMaterialNo());
            queryTotalParams.add(request.getMaterialNo());
        }
        querySql.append(" ORDER BY m.material_no ").append(" LIMIT ?,? ");
        list.add((request.getPageNo() - 1) * request.getPageSize());
        list.add(request.getPageSize());
    }
}
