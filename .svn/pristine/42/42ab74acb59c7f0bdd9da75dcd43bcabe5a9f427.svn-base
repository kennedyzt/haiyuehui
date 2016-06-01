package com.siping.intranet.crm.group.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.group.request.GetGroupListForm;
import com.siping.smartone.group.request.GetGroupRequest;
import com.siping.smartone.group.request.GroupQueryParam;
import com.siping.smartone.group.request.GroupRequest;
import com.siping.smartone.group.request.Permission;
import com.siping.smartone.group.response.GroupResponse;

@Repository
public class GroupRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Autowired
    private JSONConverter jsonConverter;
    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean addGroup(GroupRequest request) {
        validGroupRequest(request);
        String requestContent = JSONBinder.binder(GroupRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/group/add").body(requestContent));
    }

    public Boolean editGroup(GroupRequest request) {
        validGroupRequest(request);
        String requestContent = JSONBinder.binder(GroupRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/group/edit").body(requestContent));
    }

    @Valid
    private void validGroupRequest(GroupRequest request) {
        if (!StringUtils.hasText(request.getGroupName()))
            throw new BusinessProcessException("请输入组名！");
    }

    public GroupResponse get(String id, String groupName) {
        if (!StringUtils.hasText(id) && !StringUtils.hasText(groupName)) {
            throw new BusinessProcessException("条件不足不能获得组！");
        }
        GetGroupRequest request = new GetGroupRequest();
        request.setId(id);
        request.setGroupName(groupName);
        String requestContent = JSONBinder.binder(GetGroupRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(GroupResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/group/get").body(requestContent));
    }

    public Boolean deleteGroup(Integer groupId, Integer deleteUserId) {
        if (null == groupId) {
            throw new BusinessProcessException("用户组id不能为空");
        }
        return stromaWebServiceApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/group/delete/%s/%s").arguments(groupId, deleteUserId));
    }

    public List<GroupResponse> getAllGroups() {
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(GroupResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/group/getalllist"));
    }

    public PagingResponse<GroupResponse> getAllGroups(Integer pageNo, Integer pageSize, GetGroupListForm request) {
        if (null == pageNo && null == pageSize) {
            throw new BusinessProcessException("条件不足不能查询");
        }
        GroupQueryParam groupQueryParam = new GroupQueryParam();
        groupQueryParam.setPageNo(pageNo);
        groupQueryParam.setPageSize(pageSize);
        groupQueryParam.setKeywords(request.getKeywords());

        String requestContent = jsonConverter.toString(groupQueryParam);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GroupResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/group/getpagelist")
            .body(requestContent));
    }

    public List<Permission> getGroupPermissions(Integer id, String userAccount) {
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(Permission.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/group/getGroupPermissions/%s/%s")
            .arguments(id, userAccount));
    }

    public Boolean editGroupPermissions(GroupRequest groupRequest) {
        String requestContent = JSONBinder.binder(GroupRequest.class).toJSON(groupRequest);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/group/editGroupPermissions").body(requestContent));
    }
}
