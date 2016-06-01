package com.siping.service.group.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.smartone.group.request.GroupQueryParam;
import com.siping.smartone.group.request.GroupRequest;
import com.siping.smartone.group.request.Permission;
import com.siping.smartone.group.response.GroupResponse;

@Repository
public class GroupRestRepository extends BillsRepository {

    public Boolean addGroup(GroupRequest request) {
        if (null != get(null, request.getGroupName())) {
            throw new BusinessProcessException("不能添加相同的组！");
        }
        int delete = jdbcAccessContext.execute("GROUP.SQL_INSERT_GROUP", new Object[] { request.getGroupName(), request.getDescription(), 0, request.getCreateBy(), request.getUserAccount() });
        if (-1 == delete) {
            return Boolean.FALSE;
        }
        addOperationLog("组", "新增加组:[" + request.getGroupName() + "]", null, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    public Boolean editGroup(GroupRequest request) {
        /*
         * GroupResponse groupResponse =
         * getGroupByIdWithPrivate(request.getId()); if (null == groupResponse)
         * { throw new BusinessProcessException("组不存在，无法编辑"); }
         */
        int edit = jdbcAccessContext.executeWithoutSqlManager("update groups set group_name = ?,description = ? where id = ?",
            new Object[] { request.getGroupName(), request.getDescription(), request.getId() });
        if (-1 == edit) {
            return Boolean.FALSE;
        }
        addOperationLog("组", "更新组:[" + request.getGroupName() + "", null, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    public GroupResponse get(String id, String groupName) {
        List<Object> queryParam = new ArrayList<Object>();
        List<GroupResponse> list = jdbcAccessContext.findWithoutSqlManager(buildGetSql(id, groupName, queryParam), new RowMapper<GroupResponse>() {
            @Override
            public GroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildGroupResponse(rs);
            }
        }, queryParam.toArray());
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    private String buildGetSql(String id, String groupName, List<Object> queryParam) {
        StringBuilder sql = new StringBuilder("SELECT id,group_name,description,is_delete,create_date,create_by,update_date,update_by FROM groups  where is_delete=0");
        if (StringUtils.hasText(id)) {
            sql.append(" and id=? ");
            queryParam.add(id);
        }
        if (StringUtils.hasText(groupName)) {
            sql.append(" and group_name=?");
            queryParam.add(groupName);
        }
        return sql.toString();
    }

    private static GroupResponse buildGroupResponse(final ResultSet rs) throws SQLException {
        GroupResponse group = new GroupResponse();
        group.setId(rs.getInt("id"));
        group.setGroupName(rs.getString("group_name"));
        group.setDescription(rs.getString("description"));
        group.setIsDelete(rs.getBoolean("is_delete"));
        group.setCreateDate(DateUtils.date(rs.getDate("create_date"), rs.getTime("create_date")));
        group.setCreateBy(rs.getInt("create_by"));
        group.setUpdateDate(DateUtils.date(rs.getDate("update_date"), rs.getTime("update_date")));
        group.setUpdateBy(rs.getInt("update_by"));
        return group;
    }

    public Boolean deleteGroup(Integer groupId, Integer userId) {
        /*
         * GroupResponse groupResponse = getGroupByIdWithPrivate(id); if (null
         * == groupResponse) { throw new BusinessProcessException("组不存在，无法删除");
         * }
         */
        int cnt = jdbcAccessContext.findIntegerWithoutSqlManager("select COUNT(*) from permission p where p.group_id = ? and is_add = 1", new Object[] { groupId });
        if (cnt > 0) {
            throw new BusinessProcessException("此用户组包含至少一条菜单权限，不能删除！");
        }
        int cnt2 = jdbcAccessContext.findIntegerWithoutSqlManager("select COUNT(*) from user_group p where p.group_id = ?", new Object[] { groupId });
        if (cnt2 > 0) {
            throw new BusinessProcessException("此用户组包含至少一个用户，不能删除！");
        }
        int delete = jdbcAccessContext.execute("GROUP.SQL_DELETE_GROUP", new Object[] { groupId });
        if (-1 == delete) {
            return Boolean.FALSE;
        }
        GroupResponse groupResponse = get(groupId + "", null);
        if (null != groupResponse)
            addOperationLog("组", "删除用户组:[" + groupResponse.getGroupName() + "]", null, userId + "");
        return Boolean.TRUE;
    }

    public GroupResponse get(GroupRequest request) {
        List<Object> queryParam = new ArrayList<Object>();
        List<GroupResponse> list = jdbcAccessContext.findWithoutSqlManager(GroupRestRepositoryHelper.loadDynamicGetGroupSql(request, queryParam), new RowMapper<GroupResponse>() {
            @Override
            public GroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return GroupRestRepositoryHelper.buildGroupResponse(rs);
            }
        }, queryParam.toArray());
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        throw new BusinessProcessException("查询的组不存在！");
    }

    public List<GroupResponse> getGroupList(GroupRequest request) {
        List<Object> queryParam = new ArrayList<Object>();
        List<GroupResponse> list = jdbcAccessContext.findWithoutSqlManager(GroupRestRepositoryHelper.loadDynamicGetGroupSql(request, queryParam), new RowMapper<GroupResponse>() {
            @Override
            public GroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return GroupRestRepositoryHelper.buildGroupResponse(rs);
            }
        }, queryParam.toArray());
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        throw new BusinessProcessException("查询的组不存在");
    }

    private GroupResponse getGroupByIdWithPrivate(Integer id) {
        List<GroupResponse> GroupResponses = jdbcAccessContext.find("GROUP.SQL_GET_GROUP_BY_ID", new RowMapper<GroupResponse>() {
            @Override
            public GroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return GroupRestRepositoryHelper.buildGroupResponse(rs);
            }
        }, new Object[] { id });
        if (CollectionUtils.isEmpty(GroupResponses)) {
            return null;
        }
        return GroupResponses.get(0);
    }

    public List<GroupResponse> getGroupList() {
        List<GroupResponse> GroupResponses = jdbcAccessContext.find("GROUP.SQL_GET_ALL_GROUPS", new RowMapper<GroupResponse>() {
            @Override
            public GroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return GroupRestRepositoryHelper.buildGroupResponse(rs);
            }
        });
        return GroupResponses;
    }

    public PagingResponse<GroupResponse> getPageGroupList(GroupQueryParam request) {
        PagingResponse<GroupResponse> pagingResponse = new PagingResponse<GroupResponse>();
        long begin = (request.getPageNo() - 1) * request.getPageSize();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParams = new ArrayList<Object>();
        StringBuilder totalRecordsSql = new StringBuilder();
        List<GroupResponse> response = jdbcAccessContext.findWithoutSqlManager(buildGetListSql(request, begin, queryParam, totalRecordsSql, totalParams), new RowMapper<GroupResponse>() {
            @Override
            public GroupResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return GroupRestRepositoryHelper.buildGroupInfoResponse(rs);
            }
        }, queryParam.toArray());
        int totalRecords = jdbcAccessContext.findIntegerWithoutSqlManager(totalRecordsSql.toString(), totalParams.toArray());
        pagingResponse.setRecords(response);
        pagingResponse.setTotalRecords(totalRecords);
        return pagingResponse;
    }

    private String buildGetListSql(GroupQueryParam request, long begin, List<Object> queryParam, StringBuilder total, List<Object> totalParams) {
        Integer pageSize = request.getPageSize();
        String keyWords = request.getKeywords();

        total.append("SELECT count(*) FROM groups g");
        StringBuilder sql = new StringBuilder("SELECT g.id, g.group_name, g.description, g.user_account FROM groups g");
        if (StringUtils.hasText(keyWords)) {
            sql.append(" where (g.group_name like CONCAT('%',?,'%')) and g.is_delete = 0");
            total.append(" where (g.group_name like CONCAT('%',?,'%')) and g.is_delete = 0");
            queryParam.add(keyWords);
        } else {
            sql.append(" where g.is_delete = 0");
            total.append(" where g.is_delete = 0");
        }

        totalParams.addAll(queryParam);
        queryParam.add(begin);
        queryParam.add(pageSize);
        sql.append(" limit ?,? ");
        return sql.toString();
    }

    public List<Permission> getGroupPermissions(Integer id, String userAccount) {
        List<Permission> groupPermissions = jdbcAccessContext.find("GROUP.SQL_GET_GROUP_PERMISSIONS", new RowMapper<Permission>() {
            @Override
            public Permission mapRow(ResultSet rs, int arg1) throws SQLException {
                return GroupRestRepositoryHelper.buildGroupPermission(rs);
            }
        }, new Object[] { id, userAccount });
        if (CollectionUtils.isEmpty(groupPermissions)) {
            return null;
        }
        return groupPermissions;
    }

    public void deleteGroupPermissions(Integer groupid, Integer userId) {
        GroupResponse groupResponse = get(groupid + "", null);
        if (null != groupResponse)
            addOperationLog("组权限", "删除组权限:[" + groupResponse.getGroupName() + "", null, userId + "");
        jdbcAccessContext.execute("GROUP.SQL_DELETE_GROUP_PERMISSIONS", new Object[] { groupid });
    }

    public void addGroupPermissions(List<Permission> permissions,Integer userId) {
        List<Object[]> paramList = new ArrayList<Object[]>();
        for (Permission permission : permissions) {
            Object[] param = new Object[] { permission.getGroupId(), permission.getNodeId(), permission.getIsAdd(), permission.getIsDelete(), permission.getIsEdit(), permission.getIsPrint() };
            paramList.add(param);
        }
        jdbcAccessContext.batchExecute("GROUP.SQL_ADD_GROUP_PERMISSIONS", paramList);
        addOperationLog("组权限", "批量新增组权限", null, userId + "");
    }
}
