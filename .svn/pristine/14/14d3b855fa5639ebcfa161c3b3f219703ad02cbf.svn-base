package com.siping.service.permission.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;

import com.siping.service.common.BillsRepository;
import com.siping.service.group.service.GroupRestService;
import com.siping.service.menu.service.MenuNodeRestService;
import com.siping.service.usr.service.UsrRestService;
import com.siping.smartone.group.request.GetGroupRequest;
import com.siping.smartone.group.response.GroupResponse;
import com.siping.smartone.login.request.UpdateUserRequest;
import com.siping.smartone.login.response.UserLoginResponse;
import com.siping.smartone.menu.response.MenuNodeResponse;
import com.siping.smartone.permission.request.AddNodePermissionRequest;
import com.siping.smartone.permission.request.AddPermissionRequest;
import com.siping.smartone.permission.request.AddUserPermissionRequest;
import com.siping.smartone.permission.response.GetButtonPermissionResponse;
import com.siping.smartone.permission.response.GetGroupPermissionResponse;
import com.siping.smartone.permission.response.GetNodePermissionResponse;
import com.siping.smartone.permission.response.GetUserPermissionResponse;

@Repository
public class PermissionRestRepository extends BillsRepository {
    @Autowired
    private GroupRestService groupRestService;
    @Autowired
    private UsrRestService usrRestService;
    @Autowired
    private MenuNodeRestService menuNodeRestService;

    public Boolean addPermissionByGroupId(AddPermissionRequest request) {
        GetGroupPermissionResponse groupPermission = getGroupPermission(request.getGroupId());
        if (null != groupPermission && !CollectionUtils.isEmpty(groupPermission.getNodeList())) {
            throw new BusinessProcessException("当前组已有权限，只能通过编辑来增加权限!");
        }
        return addPermission(request);
    }

    private Boolean addPermission(AddPermissionRequest request) {
        List<Object[]> batchParams = new ArrayList<Object[]>();
        StringBuilder nodeIds = null;
        for (AddNodePermissionRequest nodePermission : request.getNodePermissionList()) {
            Object[] obj = new Object[] { request.getGroupId(), nodePermission.getNodeId(), nodePermission.getIsAdd(), nodePermission.getIsEdit(), nodePermission.getIsDelete(),
                    nodePermission.getIsPrint(), request.getLoginId() };
            batchParams.add(obj);
            if (null == nodeIds) {
                nodeIds = new StringBuilder(nodePermission.getNodeId());
            } else {
                nodeIds.append("," + nodePermission.getNodeId());
            }
        }
        int[] add = jdbcAccessContext.batchExecute("PERMISSION.SQL_INSERT_PERMISSION", batchParams);
        if (Arrays.asList(add).contains(-1)) {
            return Boolean.FALSE;
        }
        GetGroupRequest group = new GetGroupRequest();
        group.setId(request.getGroupId() + "");
        GroupResponse groupResponse = groupRestService.get(group);
        List<MenuNodeResponse> nodeListByIds = menuNodeRestService.getNodeListByIds(nodeIds.toString());
        List<String> nodeNames = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(nodeListByIds)) {
            for (MenuNodeResponse node : nodeListByIds) {
                nodeNames.add(node.getName());
            }
        }
        addOperationLog("权限", "为组:[" + groupResponse.getGroupName() + "]新增权限:[" + nodeNames.toString().replace("[", "").replace("]", "") + "]", null, request.getLoginId() + "");
        return Boolean.TRUE;
    }

    public Boolean updatePermissionByGroupId(AddPermissionRequest request) {
        StringBuilder nodeIds = null;
        GetGroupPermissionResponse groupPermission = getGroupPermission(request.getGroupId());
        if (null == groupPermission || CollectionUtils.isEmpty(groupPermission.getNodeList())) {
            throw new BusinessProcessException("当前组没有权限，只能通过新增来添f加权限!");
        }
        Boolean deleteAllPermission = deletePermissionByGroupId(request.getGroupId());
        if (deleteAllPermission) {
            return addPermission(request);
        }
        for (AddNodePermissionRequest nodePermission : request.getNodePermissionList()) {
            if (null == nodeIds) {
                nodeIds = new StringBuilder(nodePermission.getNodeId());
            } else {
                nodeIds.append("," + nodePermission.getNodeId());
            }
        }
        GetGroupRequest group = new GetGroupRequest();
        group.setId(request.getGroupId() + "");
        GroupResponse groupResponse = groupRestService.get(group);
        List<MenuNodeResponse> nodeListByIds = menuNodeRestService.getNodeListByIds(nodeIds.toString());
        List<String> nodeNames = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(nodeListByIds)) {
            for (MenuNodeResponse node : nodeListByIds) {
                nodeNames.add(node.getName());
            }
        }
        addOperationLog("组权限", "更新组:[" + groupResponse.getGroupName() + "]权限:[" + nodeNames.toString().replace("[", "").replace("]", "") + "]", null, request.getLoginId() + "");
        return Boolean.FALSE;
    }

    public Boolean addUserPermissionByUserId(AddUserPermissionRequest request) {
        StringBuilder nodeIds = null;
        Integer exist = jdbcAccessContext.findInteger("PERMISSION.SQL_GET_USER_PERMISSION_IS_EXIST", new Object[] { request.getLoginId() });
        if (null != exist && exist > 0) {
            throw new BusinessProcessException("当前用户已自定义权限，只能通过编辑来修改权限!");
        }
        Boolean addUserPermission = addUserPermission(request);
        if (addUserPermission) {// 反写usr表状态，表示该用户已经自定义权限
            return updateUsrHasPermission(Boolean.TRUE, request.getUserId());
        }
        UpdateUserRequest updateUser = new UpdateUserRequest();
        updateUser.setId(request.getUserId());
        UserLoginResponse userLoginResponse = usrRestService.get(updateUser);
        for (AddNodePermissionRequest nodePermission : request.getNodePermissionList()) {
            if (null == nodeIds) {
                nodeIds = new StringBuilder(nodePermission.getNodeId());
            } else {
                nodeIds.append("," + nodePermission.getNodeId());
            }
        }
        List<MenuNodeResponse> nodeListByIds = menuNodeRestService.getNodeListByIds(nodeIds.toString());
        List<String> nodeNames = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(nodeListByIds)) {
            for (MenuNodeResponse node : nodeListByIds) {
                nodeNames.add(node.getName());
            }
        }
        addOperationLog("用户权限", "为用户：[" + userLoginResponse.getUsername() + "]权限:[" + nodeNames.toString().replace("[", "").replace("]", "") + "]", null, request.getLoginId() + "");
        return Boolean.FALSE;
    }

    @SuppressWarnings("unused")
    public Boolean editUserPermissionByUserId(AddUserPermissionRequest request) {
        Integer exist = jdbcAccessContext.findInteger("PERMISSION.SQL_GET_USER_PERMISSION_IS_EXIST", new Object[] { request.getLoginId() });
        if (null == exist) {
            throw new BusinessProcessException("当前用户没有自定义权限，不能编辑用户权限!");
        }
        if (deleteUserPermissionByUserId(request)) {
            if (addUserPermission(request)) {
                updateUsrHasPermission(Boolean.TRUE, request.getUserId());
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private Boolean updateUsrHasPermission(Boolean hasPermission, Integer userId) {
        Integer updateHasPermission = jdbcAccessContext.execute("PERMISSION.SQL_UPDATE_USER_HAS_PERMISSION", new Object[] { hasPermission, userId });
        if (-1 == updateHasPermission)
            return Boolean.FALSE;
        return Boolean.TRUE;
    }

    private Boolean addUserPermission(AddUserPermissionRequest request) {
        List<Object[]> batchParams = new ArrayList<Object[]>();
        StringBuilder nodeIds = null;
        for (AddNodePermissionRequest userpPermission : request.getNodePermissionList()) {
            Object[] obj = new Object[] { request.getUserId(), userpPermission.getPermissionId(), userpPermission.getIsAdd(), userpPermission.getIsEdit(), userpPermission.getIsDelete(),
                    userpPermission.getIsPrint(), request.getUserId() };
            batchParams.add(obj);
        }
        int[] add = jdbcAccessContext.batchExecute("PERMISSION.SQL_INSERT_USER_RERMISSION", batchParams);
        if (Arrays.asList(add).contains(-1)) {
            return Boolean.FALSE;
        }
        UpdateUserRequest updateUser = new UpdateUserRequest();
        updateUser.setId(request.getUserId());
        UserLoginResponse userLoginResponse = usrRestService.get(updateUser);
        for (AddNodePermissionRequest nodePermission : request.getNodePermissionList()) {
            if (null == nodeIds) {
                nodeIds = new StringBuilder(nodePermission.getNodeId());
            } else {
                nodeIds.append("," + nodePermission.getNodeId());
            }
        }
        List<MenuNodeResponse> nodeListByIds = menuNodeRestService.getNodeListByIds(nodeIds.toString());
        StringBuilder nodes = null;
        if (CollectionUtils.isNotEmpty(nodeListByIds)) {
            for (MenuNodeResponse node : nodeListByIds) {
                if (null == nodes)
                    nodes = new StringBuilder(node.getName());
                else
                    nodes.append("," + node.getName());
            }
        }
        addOperationLog("用户权限", "为用户：[" + userLoginResponse.getUsername() + "]新增权限:[" + nodes.toString() + "]", null, request.getLoginId() + "");
        return Boolean.TRUE;
    }

    public Boolean deletePermissionByGroupId(Integer groupId) {
        int add = jdbcAccessContext.execute("PERMISSION.SQL_DELETE_PERMISSION_BY_GROUPID", new Object[] { groupId });
        if (-1 == add) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean deleteUserPermissionByUserId(AddUserPermissionRequest request) {
        Integer isDelete = jdbcAccessContext.execute("PERMISSION.SQL_DELETE_USER_RERMISSION", new Object[] { request.getUserId() });
        if (-1 == isDelete) {
            return Boolean.FALSE;
        }
        updateUsrHasPermission(Boolean.FALSE, request.getUserId());
        return Boolean.TRUE;
    }

    public GetGroupPermissionResponse getGroupPermission(final Integer groupId) {
        GetGroupRequest request = new GetGroupRequest();
        request.setId(groupId.toString());
        GroupResponse group = groupRestService.get(request);
        GetGroupPermissionResponse response = new GetGroupPermissionResponse();
        if (null != response) {
            response.setGroupId(groupId);
            response.setGroupName(group.getGroupName());
            List<GetNodePermissionResponse> permissionListo = jdbcAccessContext.find("PERMISSION.SQL_GET_PERMISSION_BY_GROUPID", new RowMapper<GetNodePermissionResponse>() {
                @Override
                public GetNodePermissionResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                    return buildNodePermission(rs);
                }

            }, new Object[] { groupId });
            if (!CollectionUtils.isEmpty(permissionListo)) {
                response.setNodeList(permissionListo);
            }
        }

        return response;
    }

    private GetNodePermissionResponse buildNodePermission(ResultSet rs) throws SQLException {
        GetNodePermissionResponse node = new GetNodePermissionResponse();
        node.setNodeId(rs.getInt("id"));
        node.setNodeName(rs.getString("node_name"));
        node.setSequence(rs.getInt("sequence"));
        node.setDescription(rs.getString("description"));
        node.setParentId(rs.getInt("parent_id"));
        node.setMenuTreeId(rs.getInt("menu_tree_id"));
        node.setLevel(rs.getInt("level"));
        node.setIsRoot(rs.getBoolean("is_root"));
        node.setNodeUrl(rs.getString("node_url"));
        node.setIsAdd(rs.getBoolean("is_add"));
        node.setIsEdit(rs.getBoolean("is_edit"));
        node.setIsDelete(rs.getBoolean("is_delete"));
        node.setIsPrint(rs.getBoolean("is_print"));
        return node;
    }

    public GetUserPermissionResponse getUserPermission(final Integer userId) {
        GetUserPermissionResponse userPermission = new GetUserPermissionResponse();
        UpdateUserRequest request = new UpdateUserRequest();
        request.setId(userId);
        UserLoginResponse user = usrRestService.get(request);
        if (null != user) {
            userPermission.setUserId(userId);
            userPermission.setUserName(user.getUsername());
            List<GetNodePermissionResponse> nodeList = jdbcAccessContext.find("PERMISSION.SQL_GET_USER_PERMISSION_BY_USERID", new RowMapper<GetNodePermissionResponse>() {
                @Override
                public GetNodePermissionResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                    return buildNodePermission(rs);
                }
            }, new Object[] { userId });
            if (!CollectionUtils.isEmpty(nodeList))
                userPermission.setNodeList(nodeList);
        }
        return userPermission;
    }

    public GetButtonPermissionResponse getButtonPermissions(Integer userId, String url) {
        return jdbcAccessContext.findUniqueResult("PERMISSION.SQL_SELECT_BUTTONPERMISSION", new RowMapper<GetButtonPermissionResponse>() {
            @Override
            public GetButtonPermissionResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildButtonPermissionResponse(rs);
            }
        }, new Object[] { userId, url });
    }

    private GetButtonPermissionResponse buildButtonPermissionResponse(ResultSet rs) throws SQLException {
        GetButtonPermissionResponse node = new GetButtonPermissionResponse();
        node.setAddBtn(rs.getInt("addBtn"));
        node.setDelBtn(rs.getInt("delByn"));
        node.setEditBtn(rs.getInt("editbtn"));
        node.setPrintBtn(rs.getInt("printbtn"));
        node.setGroupName(rs.getString("groupName"));
        node.setMenuLevel(rs.getInt("menuLevel"));
        node.setMenuName(rs.getString("menuName"));
        node.setMenuUrl(rs.getString("menuUrl"));
        node.setUserId(rs.getInt("userId"));
        return node;
    }
}
