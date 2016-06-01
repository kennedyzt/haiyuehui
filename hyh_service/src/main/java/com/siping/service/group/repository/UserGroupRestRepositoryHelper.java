package com.siping.service.group.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.stroma.framework.core.util.DateUtils;

import com.siping.smartone.group.request.GetUserGroupRequest;
import com.siping.smartone.group.response.GroupResponse;
import com.siping.smartone.group.response.UserGroupResponse;

public class UserGroupRestRepositoryHelper {

    public static UserGroupResponse buildUserGroupResponse(final ResultSet rs) throws SQLException {
        UserGroupResponse userGroup = new UserGroupResponse();
        userGroup.setId(rs.getInt("id"));
//        userGroup.setUserId(rs.getInt("user_id"));
//        userGroup.setGroupId(rs.getInt("group_id"));
        userGroup.setIsDelete(rs.getBoolean("is_delete"));
        userGroup.setCreateDate(DateUtils.date(rs.getDate("create_date"), rs.getTime("create_date")));
        userGroup.setCreateBy(rs.getInt("create_by"));
        userGroup.setGroupName(rs.getString("group_name"));
        return userGroup;
    }

    public static String loadDynamicGetUserGroupSql(GetUserGroupRequest request, List<Object> queryParam) {
        StringBuilder sql = new StringBuilder("SELECT id,user_id,group_id,is_delete,create_date,create_by FROM user_group  where is_delete=FALSE");
        if (null != request.getGroupId()) {
            sql.append(" and group_id=?");
            queryParam.add(request.getGroupId());
        }
        return sql.toString();
    }

    public static GroupResponse buildGroupResponse(ResultSet rs) throws SQLException {
        GroupResponse userGroup = new GroupResponse();
        userGroup.setId(rs.getInt("group_id"));
        userGroup.setGroupName(rs.getString("group_name"));
        return userGroup;
    }
}
