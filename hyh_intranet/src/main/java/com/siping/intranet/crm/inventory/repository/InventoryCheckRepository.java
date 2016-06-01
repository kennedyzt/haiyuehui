package com.siping.intranet.crm.inventory.repository;

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
import com.siping.smartone.inventory.request.AddInventoryCheckRequest;
import com.siping.smartone.inventory.request.AddInventoryCheckRequestNew;
import com.siping.smartone.inventory.request.DeleteInventoryCheckRequest;
import com.siping.smartone.inventory.request.GetInventoryCheckListRequest;
import com.siping.smartone.inventory.request.GetMaterialBatchNumberListRequest;
import com.siping.smartone.inventory.request.GetInventoryCheckRequest;
import com.siping.smartone.inventory.response.GetMaterialBatchNumberResponse;
import com.siping.smartone.inventory.response.GetInventoryCheckResponse;
import com.siping.smartone.inventory.response.GetInventoryCheckResponseNew;

@Repository
public class InventoryCheckRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(AddInventoryCheckRequest request) {
        validAddInventoryCheckRequest(request);
        String requestContent = JSONBinder.binder(AddInventoryCheckRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventorycheck/add").body(requestContent));
    }

    @Valid
    private void validAddInventoryCheckRequest(AddInventoryCheckRequest request) {
        // TODO 库存盘点的数据验证
    }

    public Boolean edit(AddInventoryCheckRequestNew request) {
        if (null == request || null == request.getCheckNumber()) {
            throw new BusinessProcessException("盘点编号不能为空！");
        }
        // validAddInventoryCheckRequest(request); // 也需要校验必填项
        String requestContent = JSONBinder.binder(AddInventoryCheckRequestNew.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/invnentorycheck/edit").body(requestContent));
    }

    public GetInventoryCheckResponseNew get(String checkNumber) {
        if (!StringUtils.hasText(checkNumber)) {
            throw new BusinessProcessException("缺少盘点编号不能获得盘点单信息!");
        }
        GetInventoryCheckRequest request = new GetInventoryCheckRequest();
        request.setCheckNumber(checkNumber);
        String requestContent = JSONBinder.binder(GetInventoryCheckRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(GetInventoryCheckResponseNew.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/invnentorycheck/get").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetInventoryCheckResponseNew> getList(Integer pageNo, Integer pageSize, GetInventoryCheckListRequest request) {
        request.setPageNo(pageNo.toString());
        request.setPageSize(pageSize.toString());
        String requestContent = JSONBinder.binder(GetInventoryCheckResponseNew.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetInventoryCheckResponseNew.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/invnentorycheck/getlist").body(requestContent));
    }

    public Boolean delete(List<String> ids, String updateBy) {
        if (null == ids) {
            throw new BusinessProcessException("不能删除没指定编号的！");
        }
        DeleteInventoryCheckRequest deleteRequest = new DeleteInventoryCheckRequest();
        deleteRequest.setIds(ids);
        deleteRequest.setUpdateBy(updateBy);
        String requestContent = JSONBinder.binder(DeleteInventoryCheckRequest.class).toJSON(deleteRequest);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventorycheck/delete").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<GetMaterialBatchNumberResponse> getMaterialBatchNumberList(String materialId, String storageId) {
        if (!StringUtils.hasText(materialId)) {
            throw new BusinessProcessException("请指定盘点物料的编号！");
        }
        if (!StringUtils.hasText(storageId)) {
            throw new BusinessProcessException("请指定盘点的仓库！");
        }
        GetMaterialBatchNumberListRequest request = new GetMaterialBatchNumberListRequest();
        request.setMaterialId(materialId);
        request.setStorageId(storageId);
        String requestContent = JSONBinder.binder(GetMaterialBatchNumberListRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(List.class).elementTypes(GetMaterialBatchNumberResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/invnentorycheck/getmaterialbatchnumberlist").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetInventoryCheckResponse> getInventorycheckByStorageIdWithDrft(String storageId) {
        if (!StringUtils.hasText(storageId)) {
            throw new BusinessProcessException("请指定盘点的仓库！");
        }
        GetInventoryCheckListRequest request = new GetInventoryCheckListRequest();
        request.setIsDraft(true);
        request.setStorageId(storageId);
        String requestContent = JSONBinder.binder(GetInventoryCheckListRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetInventoryCheckResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/invnentorycheck/getlist").body(requestContent));
    }

    public Boolean addNew(AddInventoryCheckRequestNew request) {
        String requestContent = JSONBinder.binder(AddInventoryCheckRequestNew.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventorycheck/addnew").body(requestContent));
    }

    public Boolean addPdaInventoryCheck(AddInventoryCheckRequestNew request) {
        String requestContent = JSONBinder.binder(AddInventoryCheckRequestNew.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/pdainventorycheck/add").body(requestContent));
    }
}
