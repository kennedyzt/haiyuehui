package com.siping.service.group.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.service.usr.repository.UsrRestRepositoryHelper;
import com.siping.smartone.group.request.GetUserGroupRequest;
import com.siping.smartone.group.request.UserGroupRequest;
import com.siping.smartone.group.response.GroupResponse;
import com.siping.smartone.group.response.UserGroupResponse;
import com.siping.smartone.login.request.UpdateUserRequest;
import com.siping.smartone.login.response.UserLoginResponse;

@Repository
public class UserGroupRestRepository extends BillsRepository {

    public Boolean addUserGroup(UserGroupRequest request) {
        try {
            UpdateUserRequest updateUser = new UpdateUserRequest();
            updateUser.setId(request.getUserId());
            UserLoginResponse userLoginResponse = get(updateUser);
            UserGroupResponse userGroup = getUserGroupByIdWithPrivate(request.getGroupId());
            if (null != userLoginResponse && null != userGroup)
                addOperationLog("用户组", "为用户:[" + userLoginResponse.getUsername() + "]新增用户组:[" + userGroup.getGroupName() + "]", null, request.getCreateBy() + "");
            int delete = jdbcAccessContext.execute("GROUP.SQL_INSERT_USER_GROUP", new Object[] { request.getUserId(), request.getGroupId(), 0, request.getCreateBy() });
            if (-1 == delete) {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Boolean deleteUserGroup(Integer userGroupId, Integer userId) {
        UserGroupResponse userGroupResponse = getUserGroupByIdWithPrivate(userGroupId);
        if (null == userGroupResponse) {
            throw new BusinessProcessException("对象不存在，无法删除");
        }
        UpdateUserRequest updateUser = new UpdateUserRequest();
        updateUser.setId(userId);
        UserLoginResponse userLoginResponse = get(updateUser);
        UserGroupResponse userGroup = getUserGroupByIdWithPrivate(userGroupId);
        if (null != userLoginResponse && null != userGroup)
            addOperationLog("用户组", "删除用户:[" + userLoginResponse.getUsername() + "]组：[" + userGroup.getGroupName() + "]", null, userId + "");
        int delete = jdbcAccessContext.execute("GROUP.SQL_DELETE_USER_GROUP", new Object[] { userGroupId });
        if (-1 == delete) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean deleteGroupOfUser(Integer userId, Integer createBy) {
        UpdateUserRequest updateUser = new UpdateUserRequest();
        updateUser.setId(userId);
        UserLoginResponse userLoginResponse = get(updateUser);
        addOperationLog("用户组", "删除用户:[" + userLoginResponse.getUsername() + "]的所有组", null, createBy + "");
        int delete = jdbcAccessContext.execute("GROUP.SQL_DELETE_GROUP_OF_USER", new Object[] { userId });
        if (-1 == delete) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public UserGroupResponse getUserGroup(GetUserGroupRequest request) {
        List<Object> queryParam = new ArrayList<Object>();
        List<UserGroupResponse> userGroupResponses = null;
        userGroupResponses = jdbcAccessContext.findWithoutSqlManager(buildGetSql(request.getId(), request.getUserId(), queryParam), new RowMapper<UserGroupResponse>() {
            @Override
            public UserGroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildUserGroupResponse(rs);
            }
        }, queryParam.toArray());
        if (!CollectionUtils.isEmpty(userGroupResponses)) {
            return userGroupResponses.get(0);
        }
        return null;
    }

    private String buildGetSql(String id, String userId, List<Object> queryParam) {
        StringBuilder sql = new StringBuilder("SELECT id,user_id,group_id,is_delete,create_date,create_by FROM user_group  where is_delete=FALSE");
        if (StringUtils.hasText(id)) {
            sql.append(" and id=? ");
            queryParam.add(id);
        }
        if (StringUtils.hasText(userId)) {
            sql.append(" and user_id=?");
            queryParam.add(userId);
        }
        return sql.toString();
    }

    private UserGroupResponse buildUserGroupResponse(final ResultSet rs) throws SQLException {
        UserGroupResponse userGroup = new UserGroupResponse();
        userGroup.setId(rs.getInt("id"));
        userGroup.setUserId(Integer.valueOf(rs.getString("user_id")));
        userGroup.setGroupId(Integer.valueOf(rs.getString("group_id")));
        userGroup.setIsDelete(rs.getBoolean("is_delete"));
        userGroup.setCreateDate(DateUtils.date(rs.getDate("create_date"), rs.getTime("create_date")));
        userGroup.setCreateBy(rs.getInt("create_by"));
        return userGroup;

    }

    private UserGroupResponse getUserGroupByIdWithPrivate(Integer id) {
        List<UserGroupResponse> userGroupResponses = jdbcAccessContext.find("GROUP.SQL_GET_GROUP_BY_ID", new RowMapper<UserGroupResponse>() {
            @Override
            public UserGroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return UserGroupRestRepositoryHelper.buildUserGroupResponse(rs);
            }
        }, new Object[] { id });
        if (CollectionUtils.isEmpty(userGroupResponses)) {
            return null;
        }
        return userGroupResponses.get(0);
    }

    public UserGroupResponse get(GetUserGroupRequest request) {
        List<Object> queryParam = new ArrayList<Object>();
        List<UserGroupResponse> list = jdbcAccessContext.findWithoutSqlManager(UserGroupRestRepositoryHelper.loadDynamicGetUserGroupSql(request, queryParam), new RowMapper<UserGroupResponse>() {
            @Override
            public UserGroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return UserGroupRestRepositoryHelper.buildUserGroupResponse(rs);
            }
        }, queryParam.toArray());
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        throw new BusinessProcessException("查询的组不存在！");
    }

    public List<UserGroupResponse> getUserGroupList(GetUserGroupRequest request) {
        List<Object> queryParam = new ArrayList<Object>();
        List<UserGroupResponse> list = jdbcAccessContext.findWithoutSqlManager(UserGroupRestRepositoryHelper.loadDynamicGetUserGroupSql(request, queryParam), new RowMapper<UserGroupResponse>() {
            @Override
            public UserGroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return UserGroupRestRepositoryHelper.buildUserGroupResponse(rs);
            }
        }, queryParam.toArray());
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        throw new BusinessProcessException("查询的组不存在");
    }

    public List<GroupResponse> getGroupsOfUser(Integer id) {
        List<GroupResponse> userGroups = jdbcAccessContext.find("GROUP.SQL_GET_GROUP_OF_USER", new RowMapper<GroupResponse>() {
            @Override
            public GroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return UserGroupRestRepositoryHelper.buildGroupResponse(rs);
            }
        }, new Object[] { id });

        return userGroups;
    }

    public UserLoginResponse get(UpdateUserRequest request) {
        List<Object> queryParam = new ArrayList<Object>();
        List<UserLoginResponse> list = jdbcAccessContext.findWithoutSqlManager(UsrRestRepositoryHelper.loadDynamicGetUserSql(request, queryParam), new RowMapper<UserLoginResponse>() {
            @Override
            public UserLoginResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return UsrRestRepositoryHelper.buildUserLoginResponse(rs);
            }
        }, queryParam.toArray());
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        throw new BusinessProcessException("用户不存在！");
    }
}
