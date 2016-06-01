package com.siping.service.group.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.smartone.group.request.GroupRequest;
import com.siping.smartone.group.request.Permission;
import com.siping.smartone.group.response.GroupResponse;

public class GroupRestRepositoryHelper {
    public static GroupResponse buildGroupResponse(final ResultSet rs) throws SQLException {
        GroupResponse group = new GroupResponse();
        group.setId(rs.getInt("id"));
        group.setGroupName(rs.getString("group_name"));
        group.setUserAccount(rs.getString("user_account"));
        group.setDescription(rs.getString("description"));
        group.setIsDelete(rs.getBoolean("is_delete"));
        group.setCreateDate(DateUtils.date(rs.getDate("create_date"), rs.getTime("create_date")));
        group.setCreateBy(rs.getInt("create_by"));
        group.setUpdateDate(DateUtils.date(rs.getDate("update_date"), rs.getTime("update_date")));
        group.setUpdateBy(rs.getInt("update_by"));
        return group;
    }

    public static GroupResponse buildGroupInfoResponse(final ResultSet rs) throws SQLException {
        GroupResponse group = new GroupResponse();
        group.setId(rs.getInt("id"));
        group.setGroupName(rs.getString("group_name"));
        group.setDescription(rs.getString("description"));
        group.setUserAccount(rs.getString("user_account"));
        return group;
    }

    public static String loadDynamicUpdateGroupSql(GroupRequest request, List<Object> updateParam) {
        StringBuilder sql = new StringBuilder("UPDATE groups SET ");
        int count = 0;
        count = assembleParams(request, updateParam, sql, count);
        sql.append("where id=?");
        updateParam.add(request.getId());
        return sql.toString();
    }

    private static int assembleParams(GroupRequest request, List<Object> updateParam, StringBuilder sql, int count) {
        if (StringUtils.hasText(request.getGroupName())) {
            if (count != 0)
                sql.append(",group_name=?");
            else
                sql.append("group_name=?");
            updateParam.add(request.getGroupName());
            count++;
        }
        if (StringUtils.hasText(request.getDescription())) {
            if (count != 0)
                sql.append(",description=?");
            else
                sql.append("description=?");
            updateParam.add(request.getDescription());
            count++;
        }
        if (null != request.getIsDelete() && request.getIsDelete()) {
            if (count != 0)
                sql.append(",is_delete=? ");
            else
                sql.append("is_delete=? ");
            updateParam.add(request.getIsDelete());
            count++;
        }
        if(count==0){
            throw new BusinessProcessException("您没有选择需要修改的项");
        }
        return count;
    }

    public static String loadDynamicGetGroupSql(GroupRequest request, List<Object> queryParam) {
        StringBuilder sql = new StringBuilder(
            "SELECT SELECT id,group_name,description,is_delete,create_date,create_by,update_date,update_by FROM groups  where is_delete=FALSE");
        if (null != request.getId()) {
            sql.append(" and id=? ");
            queryParam.add(request.getId());
        }
        if (StringUtils.hasText(request.getGroupName())) {
            sql.append(" and group_name=?");
            queryParam.add(request.getGroupName());
        }
        return sql.toString();
    }

    public static Permission buildGroupPermission(ResultSet rs) throws SQLException {
        Permission permission = new Permission();
        permission.setId(rs.getInt("id"));
        permission.setGroupId(rs.getInt("group_id"));
        permission.setNodeId(rs.getInt("node_id"));
        permission.setNodeName(rs.getString("node_name"));
        permission.setNodeUrl(rs.getString("node_url"));

        permission.setLevel(rs.getInt("level"));
        permission.setSequence(rs.getInt("sequence"));
        permission.setParentId(rs.getInt("parent_id"));
        permission.setIcon(rs.getString("icon"));
        permission.setMenuRoot(rs.getString("menu_root"));

        permission.setIsAdd(rs.getBoolean("is_add"));
        permission.setIsEdit(rs.getBoolean("is_edit"));
        permission.setIsPrint(rs.getBoolean("is_print"));
        permission.setIsDelete(rs.getBoolean("is_delete"));

        return permission;
    }

}
