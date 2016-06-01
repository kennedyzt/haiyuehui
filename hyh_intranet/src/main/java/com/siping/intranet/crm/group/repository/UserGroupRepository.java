package com.siping.intranet.crm.group.repository;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.group.request.GetUserGroupRequest;
import com.siping.smartone.group.request.UserGroupRequest;
import com.siping.smartone.group.response.UserGroupResponse;
import com.siping.smartone.material.request.GetMaterialListRequest;

@Repository
public class UserGroupRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Value("${site.api.appkeys}")
    private String appKey;

    @Valid
    private void validUserGroupRequest(UserGroupRequest request) {
        System.out.println("验证数据合法性");
    }

    public Boolean addUserGroup(UserGroupRequest request) {
        validUserGroupRequest(request);
        String requestContent = JSONBinder.binder(UserGroupRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/usergroup/add").body(requestContent));
    }

    public boolean deleteUserGroup(Integer userGroupId, Integer userid) {
        if (null == userGroupId) {
            throw new BusinessProcessException("id不能为空");
        }
        return stromaWebServiceApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/usergroup/delete/%s/%s").arguments(userGroupId, userid));
    }

    public UserGroupResponse getUserGroup(String id, String userId) {
        if (null == id && null == userId) {
            throw new BusinessProcessException("条件不足，无法查询");
        }
        GetUserGroupRequest getUserGroupRequest = new GetUserGroupRequest();
        getUserGroupRequest.setId(id);
        getUserGroupRequest.setUserId(userId);
        String requestContent = JSONBinder.binder(GetMaterialListRequest.class).toJSON(getUserGroupRequest);
        return stromaWebServiceApi.post(EndPointBuilder.create(UserGroupResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/usergroup/get").body(requestContent));
    }

    public UserGroupResponse getUserGroupList(String groupId) {
        if (null == groupId) {
            throw new BusinessProcessException("条件不足，无法查询");
        }
        GetUserGroupRequest getUserGroupRequest = new GetUserGroupRequest();
        getUserGroupRequest.setGroupId(groupId);
        String requestContent = JSONBinder.binder(GetMaterialListRequest.class).toJSON(getUserGroupRequest);
        return stromaWebServiceApi.post(EndPointBuilder.create(UserGroupResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/usergroup/getList").body(requestContent));
    }
}
