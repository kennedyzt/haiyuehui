package com.siping.intranet.crm.material.repository;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.material.request.AddMaterialGroupRelationRequest;
import com.siping.smartone.material.request.GetMaterialConditionRequest;
import com.siping.smartone.material.request.GetMaterialRequest;
import com.siping.smartone.material.response.GetMaterialGroupResponse;
import com.siping.smartone.material.response.GetMaterialResponse;

@Repository
public class MaterialGroupRelationRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(AddMaterialGroupRelationRequest request) {
        validAddMaterialRequest(request);
        String requestContent = JSONBinder.binder(AddMaterialGroupRelationRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialgrouprelation/add").body(requestContent));
    }

    @Valid
    private void validAddMaterialRequest(AddMaterialGroupRelationRequest request) {
        if (null == request.getMaterialGroupId())
            throw new BusinessProcessException("商品组不能为空！");
        if (CollectionUtils.isEmpty(request.getMaterialIdList()))
            throw new BusinessProcessException("未选择商品，不能加入商品组！");
    }

    public Boolean edit(AddMaterialGroupRelationRequest request) {
        if (null == request || null == request.getMaterialGroupId()) {
            throw new BusinessProcessException("未选择需要编辑的商品组！");
        }
        validAddMaterialRequest(request); // 也需要校验必填项
        String requestContent = JSONBinder.binder(AddMaterialGroupRelationRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialgrouprelation/edit").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<GetMaterialGroupResponse> get(String id, String materialno) {
        if (!StringUtils.hasText(id) && !StringUtils.hasText(materialno)) {
            throw new BusinessProcessException("条件不足不能获得物料组！");
        }
        GetMaterialRequest r = new GetMaterialRequest();
        r.setId(id);
        r.setMaterialNo(materialno);
        String requestContent = JSONBinder.binder(GetMaterialRequest.class).toJSON(r);
        return stromaWebServiceApi.post(EndPointBuilder.create(List.class).elementTypes(GetMaterialGroupResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialgrouprelation/get")
            .body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetMaterialResponse> getList(Integer pageNo, Integer pageSize, GetMaterialConditionRequest request) {
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        String requestContent = JSONBinder.binder(GetMaterialConditionRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialgrouprelation/getlist").body(requestContent));
    }

    public Boolean delete(String id) {
        if (!StringUtils.hasText(id)) {
            throw new BusinessProcessException("条件不足不能删除物料组！");
        }
        return stromaWebServiceApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialusage/delete/%s").arguments(id));
    }
}
