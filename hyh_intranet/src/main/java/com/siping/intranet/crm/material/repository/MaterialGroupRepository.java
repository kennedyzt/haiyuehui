package com.siping.intranet.crm.material.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.material.request.AddMaterialGroupRequest;
import com.siping.smartone.material.request.GetMaterialUsageRequest;
import com.siping.smartone.material.response.GetMaterialGroupResponse;

@Repository
public class MaterialGroupRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(AddMaterialGroupRequest request) {
        validAddMaterialRequest(request);
        String requestContent = JSONBinder.binder(AddMaterialGroupRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialgroup/add").body(requestContent));
    }

    @Valid
    private void validAddMaterialRequest(AddMaterialGroupRequest request) {
        if (!StringUtils.hasText(request.getGroupName()))
            throw new BusinessProcessException("物料组不能为空！");
    }

    public Boolean edit(AddMaterialGroupRequest request) {
        if (null == request || null == request.getId()) {
            throw new BusinessProcessException("未选择需要编辑的物料组！");
        }
        validAddMaterialRequest(request); // 也需要校验必填项
        String requestContent = JSONBinder.binder(AddMaterialGroupRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialgroup/edit").body(requestContent));
    }

    public GetMaterialGroupResponse get(String id, String groupName) {
        if (!StringUtils.hasText(id) && !StringUtils.hasText(groupName)) {
            throw new BusinessProcessException("条件不足不能获得物料组！");
        }
        GetMaterialUsageRequest request = new GetMaterialUsageRequest();
        request.setId(id);
        request.setUsageName(groupName);
        String requestContent = JSONBinder.binder(GetMaterialUsageRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(GetMaterialGroupResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialgroup/get").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<GetMaterialGroupResponse> getList() {
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(GetMaterialGroupResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialgroup/getlist"));
    }

    public Boolean delete(String id, String updateBy) {
        if (!StringUtils.hasText(id)) {
            throw new BusinessProcessException("条件不足不能删除物料组！");
        }
        return stromaWebServiceApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialgroup/delete/%s/%s").arguments(id, updateBy));
    }
}