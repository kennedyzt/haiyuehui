package com.siping.intranet.crm.permission.repository;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.permission.request.AddPermissionRequest;
import com.siping.smartone.permission.request.AddUserPermissionRequest;
import com.siping.smartone.permission.response.GetButtonPermissionResponse;
import com.siping.smartone.permission.response.GetGroupPermissionResponse;
import com.siping.smartone.permission.response.GetUserPermissionResponse;

@Repository
public class PermissionRepository {
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean addPermissionByGroupId(AddPermissionRequest request) {
        validateAddPermissionRequest(request);
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/permission/addlist").body(requestContent));
    }

    @Valid
    private void validateAddPermissionRequest(AddPermissionRequest request) {
        if (null == request.getGroupId()) {
            throw new BusinessProcessException("主键缺失不能添加节点！");
        }
        if (CollectionUtils.isEmpty(request.getNodePermissionList())) {
            throw new BusinessProcessException("未选择节点，不能添加权限！");
        }
    }

    public Boolean updatePermissionByGroupId(AddPermissionRequest request) {
        validateAddPermissionRequest(request);
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/permission/editlist").body(requestContent));
    }

    public Boolean addUserPermissionByUserId(AddUserPermissionRequest request) {
        validateAddUserPermissionRequest(request);
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/userpermission/addlist").body(requestContent));
    }

    public Boolean editUserPermissionByUserId(AddUserPermissionRequest request) {
        validateAddUserPermissionRequest(request);
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/userpermission/editlist").body(requestContent));
    }

    @Valid
    private void validateAddUserPermissionRequest(AddUserPermissionRequest request) {
        if (null == request.getUserId()) {
            throw new BusinessProcessException("主键缺失不能添加节点");
        }
        if (CollectionUtils.isEmpty(request.getNodePermissionList())) {
            throw new BusinessProcessException("未选择节点，不能添加权限！");
        }
    }

    public Boolean deleteUserPermissionByUserId(AddUserPermissionRequest request) {
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/userpermission/delete").arguments(requestContent));
    }

    public Boolean deletePermissionByGroupId(Integer groupId) {
        if (null == groupId) {
            throw new BusinessProcessException("条件不足不能删除该节点");
        }
        return stromaWebServiceApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/permission/deletelist/%s").arguments(groupId));
    }

    public GetGroupPermissionResponse getGroupPermission(Integer groupId) {
        if (null == groupId) {
            throw new BusinessProcessException("条件不足不能获取组权限！");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(GetGroupPermissionResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/permission/get/%s").arguments(groupId));
    }

    public GetUserPermissionResponse getUserPermission(Integer userId) {
        if (null == userId) {
            throw new BusinessProcessException("条件不足不能获取用户权限！");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(GetUserPermissionResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/userpermission/get/%s").arguments(userId));
    }

    public GetButtonPermissionResponse getButtonPermissions(Integer userId, String url){
        return stromaWebServiceApi.get(EndPointBuilder.create(GetButtonPermissionResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/permission/getButtonPermissions/%s/%s").arguments(userId, url));
    }
}
