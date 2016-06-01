package com.siping.intranet.crm.material.repository;

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
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.login.request.UpdateUserRequest;
import com.siping.smartone.material.request.AddMaterialTypeRequest;
import com.siping.smartone.material.request.DeleteMaterialTypeRequest;
import com.siping.smartone.material.request.GetMaterialTypeListRequest;
import com.siping.smartone.material.request.GetMaterialTypeRequest;
import com.siping.smartone.material.response.GetMaterialTypeResponse;

@Repository
public class MaterialTypeRepository {
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(AddMaterialTypeRequest request) {
        validAddMaterialRequest(request);
        String requestContent = JSONBinder.binder(AddMaterialTypeRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialtype/add").body(requestContent));
    }

    @Valid
    private void validAddMaterialRequest(AddMaterialTypeRequest request) {
        if (!StringUtils.hasText(request.getTypeName()))
            throw new BusinessProcessException("商品分类不能为空！");
    }

    public Boolean edit(AddMaterialTypeRequest request) {
        if (null == request || null == request.getId()) {
            throw new BusinessProcessException("未选择需要编辑的商品分类！");
        }
        validAddMaterialRequest(request); // 也需要校验必填项
        String requestContent = JSONBinder.binder(UpdateUserRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialtype/edit").body(requestContent));
    }

    public GetMaterialTypeResponse get(Integer id, String typeName) {
        if (!StringUtils.hasText(id.toString()) && !StringUtils.hasText(typeName)) {
            throw new BusinessProcessException("条件不足不能获得商品分类！");
        }
        GetMaterialTypeRequest request = new GetMaterialTypeRequest();
        request.setId(id);
        request.setTypeName(typeName);
        String requestContent = JSONBinder.binder(GetMaterialTypeRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(GetMaterialTypeResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialtype/get").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<GetMaterialTypeResponse> getList() {
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(GetMaterialTypeResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialtype/getlist"));
    }

//    public Boolean delete(String id, String updateBy) {
//        if (null == id) {
//            throw new BusinessProcessException("用户id为空不能删除！");
//        }
//        return stromaWebServiceApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialtype/delete/%s/%s").arguments(id, updateBy));
//    }

    @SuppressWarnings("unchecked")
    public List<GetMaterialTypeResponse> getChild(String parentId) {
        if (!StringUtils.hasText(parentId))
            throw new BusinessProcessException("商品分类不能为空！");
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(GetMaterialTypeResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialtype/getchild/%s")
            .arguments(parentId));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetMaterialTypeResponse> getAllMaterialType(Integer pageNo, Integer pageSize, GetMaterialTypeListRequest request) {
        if (null == pageNo && null == pageSize) {
            throw new BusinessProcessException("条件不足不能查询");
        }
        request.setPageNo(pageNo.toString());
        request.setPageSize(pageSize.toString());
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetMaterialTypeResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialtype/getlistforlist").body(requestContent));
    }
    
    @SuppressWarnings("unchecked")
    public PagingResponse<GetMaterialTypeResponse> getAllMaterialType(Integer pageNo, Integer pageSize, CommonBillsRequest request) {
        if (null == pageNo && null == pageSize) {
            throw new BusinessProcessException("条件不足不能查询");
        }
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetMaterialTypeResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialtype/getlistforlist").body(requestContent));
    }

    public boolean delete(List<Integer> ids, Integer updateBy) {
        if (null == ids) {
            throw new BusinessProcessException("缺失主键不能商品单位");
        }
        if (null == updateBy) {
            throw new BusinessProcessException("当前登录出现问题，无法修改");
        }
        DeleteMaterialTypeRequest deleteMaterialTypeRequest = new DeleteMaterialTypeRequest();
        deleteMaterialTypeRequest.setIds(ids);
        deleteMaterialTypeRequest.setUpdateBy(updateBy.toString());
        String requestContent = JSONBinder.binder(DeleteMaterialTypeRequest.class).toJSON(deleteMaterialTypeRequest);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialtype/delete").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<GetMaterialTypeResponse> getList(String id) {
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(GetMaterialTypeResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/materialtype/geteditlist/%s").arguments(id));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetMaterialTypeResponse> getListByParentId(GetMaterialTypeListRequest request) {
        String requestContent = JSONBinder.binder(GetMaterialTypeListRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetMaterialTypeResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/materialtype/getlistbyparentid").body(requestContent));
    }
}
