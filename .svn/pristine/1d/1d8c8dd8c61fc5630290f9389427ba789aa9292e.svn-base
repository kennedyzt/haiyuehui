package com.siping.service.material.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.stroma.framework.core.database.PreparedStatementCreatorImpl;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.smartone.material.request.AddMaterialGroupRequest;
import com.siping.smartone.material.response.GetMaterialGroupResponse;

@Repository
public class MaterialGroupRestRepository extends BillsRepository {

    public Boolean add(AddMaterialGroupRequest request) {
        if (null != get(null, request.getGroupName())) {
            throw new BusinessProcessException("不能重复添加商品组！");
        }
        Object[] addParams = new Object[] { request.getGroupName(), request.getDescription(), false, new Date(), request.getCreateBy() };
        int add = jdbcAccessContext.execute("MATERIAL.SQL_ADD_MATERIAL_GROUP", addParams);
        if (-1 == add) {
            return Boolean.FALSE;
        }
        addOperationLog("商品组", "新增商品组:[" + request.getGroupName() + "]", null, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    public Boolean edit(AddMaterialGroupRequest request) {
        List<Object> paramList = new ArrayList<Object>();
        if (null == get(request.getId(), null))
            throw new BusinessProcessException("用户组不存在，无法编辑！");
        int edit = jdbcAccessContext.executeWithoutSqlManager(buildEditSql(request, paramList), paramList.toArray());
        if (-1 == edit) {
            return Boolean.FALSE;
        }
        addOperationLog("商品组", request.getIsDelete() ? "删除" : "更新" + "商品组:[" + request.getGroupName() + "]", null, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    private static String buildEditSql(AddMaterialGroupRequest request, List<Object> list) {
        StringBuilder sql = new StringBuilder("UPDATE material_group SET ");
        if (StringUtils.hasText(request.getGroupName())) {
            sql.append("group_name=? ");
            list.add(request.getGroupName());
        }
        if (StringUtils.hasText(request.getDescription())) {
            if (list.size() > 0)
                sql.append(",description=? ");
            else
                sql.append("description=?");
            list.add(request.getDescription());
        }
        if (null != request.getIsDelete() && request.getIsDelete()) {
            if (list.size() > 0)
                sql.append(",is_delete=? ");
            else
                sql.append("is_delete=? ");
            list.add(request.getIsDelete());
        }
        if (null != request.getUpdateBy() && request.getUpdateBy() != 0) {
            if (list.size() > 0)
                sql.append(",update_by=?,update_date=? ");
            else
                sql.append("update_by=?,update_date=? ");
            list.add(request.getUpdateBy());
            list.add(new Date());
        }
        sql.append("where id=? ");
        list.add(Integer.valueOf(request.getId()));
        return sql.toString();
    }

    public GetMaterialGroupResponse get(String id, String groupName) {
        List<Object> queryParam = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder("SELECT g.id,g.group_name,g.description,g.is_delete,g.create_date,g.create_by from material_group g where g.is_delete=FALSE ");
        if (StringUtils.hasText(id)) {
            sql.append("and g.id=? ");
            queryParam.add(id);
        }
        if (StringUtils.hasText(groupName)) {
            sql.append("and g.group_name=? ");
            queryParam.add(groupName);
        }
        List<GetMaterialGroupResponse> list = jdbcAccessContext.findWithoutSqlManager(sql.toString(), new RowMapper<GetMaterialGroupResponse>() {
            @Override
            public GetMaterialGroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialUsageResponse(rs);
            }
        }, queryParam.toArray());
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    private static GetMaterialGroupResponse buildMaterialUsageResponse(final ResultSet rs) throws SQLException {
        GetMaterialGroupResponse group = new GetMaterialGroupResponse();
        group.setGroupName(rs.getString("group_name"));
        group.setId(rs.getInt("id") + "");
        group.setDescription(rs.getString("description"));
        group.setIsDelete(rs.getBoolean("is_delete"));
        group.setCreateDate(DateUtils.date(rs.getDate("create_date"), rs.getTime("create_date")));
        group.setCreateBy(rs.getInt("create_by"));
        return group;
    }

    public List<GetMaterialGroupResponse> getList() {
        return jdbcAccessContext.find("MATERIAL.SQL_GET_ALL_MATERIAL_GROUP", new RowMapper<GetMaterialGroupResponse>() {
            @Override
            public GetMaterialGroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialUsageResponse(rs);
            }
        });
    }

    public Boolean delete(String id, String updateBy) {
        GetMaterialGroupResponse group = get(id, null);
        if (null == group)
            throw new BusinessProcessException("物料组不存在，无法删除！");
        AddMaterialGroupRequest request = new AddMaterialGroupRequest();
        request.setId(id);
        request.setIsDelete(Boolean.TRUE);
        request.setUpdateBy(Integer.valueOf(updateBy));
        return edit(request);
    }

    @SuppressWarnings("unused")
    private String executeAndReturnPrimarykey(final String aliasName, Object[] params) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        int execute = jdbcAccessContext.executeAndReturnPrimarykey(new PreparedStatementCreatorImpl(aliasName, params, jdbcAccessContext), keyHolder);
        return keyHolder.getKey().toString();
    }
}
