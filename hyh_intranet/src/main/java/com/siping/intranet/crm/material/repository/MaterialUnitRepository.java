package com.siping.intranet.crm.material.repository;

import java.util.List;

import javax.validation.Valid;

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
import com.siping.smartone.material.request.AddMaterialUnitRequest;
import com.siping.smartone.material.request.DeleteMaterialRequest;
import com.siping.smartone.material.request.DeleteMaterialUnitRequest;
import com.siping.smartone.material.request.GetMaterialListRequest;
import com.siping.smartone.material.request.GetMaterialUnitListRequest;
import com.siping.smartone.material.request.GetMaterialUnitRequest;
import com.siping.smartone.material.response.GetMaterialResponse;
import com.siping.smartone.material.response.GetMaterialUnitResponse;

@Repository
public class MaterialUnitRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(AddMaterialUnitRequest request) {
        validAddMaterialUnitRequest(request);
        String requestContent = JSONBinder.binder(AddMaterialUnitRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialunit/add").body(requestContent));
    }

    @Valid
    private void validAddMaterialUnitRequest(AddMaterialUnitRequest request) {
        if (!StringUtils.hasText(request.getUnitName()))
            throw new BusinessProcessException("商品计量单位名称不能为空！");
    }

    public Boolean edit(AddMaterialUnitRequest request) {
        if (null == request || null == request.getId()) {
            throw new BusinessProcessException("未选择需要编辑的商品计量单位！");
        }
        validAddMaterialUnitRequest(request); // 也需要校验必填项
        String requestContent = JSONBinder.binder(AddMaterialUnitRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialunit/edit").body(requestContent));
    }

    public GetMaterialUnitResponse get(String id, String unitName) {
        if (!StringUtils.hasText(id) && !StringUtils.hasText(unitName)) {
            throw new BusinessProcessException("条件不足不能获得商品单位！");
        }
        GetMaterialUnitRequest request = new GetMaterialUnitRequest();
        request.setId(id);
        request.setUnitName(unitName);
        String requestContent = JSONBinder.binder(GetMaterialUnitRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(GetMaterialUnitResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialunit/get").body(requestContent));
    }

    public Boolean delete(List<String> ids, String updateBy) {
        if (null == ids) {
            throw new BusinessProcessException("缺失主键不能商品单位");
        }
        if (null == updateBy) {
            throw new BusinessProcessException("当前登录出现问题，无法修改");
        }
        DeleteMaterialUnitRequest deleteMaterialUnitRequest = new DeleteMaterialUnitRequest();
        deleteMaterialUnitRequest.setIds(ids);
        deleteMaterialUnitRequest.setUpdateBy(updateBy);
        String requestContent = JSONBinder.binder(DeleteMaterialUnitRequest.class).toJSON(deleteMaterialUnitRequest);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialunit/delete").body(requestContent));
    }
    
    @SuppressWarnings("unchecked")
    public PagingResponse<GetMaterialUnitResponse> getList(Integer pageNo, Integer pageSize, GetMaterialUnitListRequest request) {
        if (pageNo == null)
            pageNo = 1;
        if (pageSize == null)
            pageSize = 10;
        request.setPageNo(pageNo.toString());
        request.setPageSize(pageSize.toString());
        String requestContent = JSONBinder.binder(GetMaterialListRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetMaterialUnitResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialunit/getlist").body(requestContent));
    }
    
    @SuppressWarnings("unchecked")
    public List<GetMaterialUnitResponse> getList() {
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(GetMaterialUnitResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialunit/getalllist"));
    }

    @SuppressWarnings("unchecked")
    public List<GetMaterialUnitResponse> getListWithCondition(String keyword) {
        GetMaterialUnitListRequest request = new GetMaterialUnitListRequest();
        request.setKey(keyword);
        String requestContent = JSONBinder.binder(GetMaterialListRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(List.class).elementTypes(GetMaterialUnitResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialunit/getlistwithcondition").body(requestContent));
    }
}
