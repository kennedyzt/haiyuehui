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
import com.siping.smartone.login.request.UpdateUserRequest;
import com.siping.smartone.postperiod.request.DeletePostPeriodRequest;
import com.siping.smartone.storage.request.DeleteStorageRequest;
import com.siping.smartone.inventory.request.AddInventoryCheckItemRequest;
import com.siping.smartone.inventory.response.GetInventoryCheckItemResponse;
import com.siping.smartone.inventory.request.GetInventoryCheckItemListRequest;
import com.siping.smartone.inventory.request.GetInventoryCheckItemRequest;

@Repository
public class InventoryCheckItemRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(AddInventoryCheckItemRequest request) {
        validAddInventoryCheckItemRequest(request);
        String requestContent = JSONBinder.binder(AddInventoryCheckItemRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventorycheck/add").body(requestContent));
    }

    @Valid
    private void validAddInventoryCheckItemRequest(AddInventoryCheckItemRequest request) {
        //TODO 库存盘点的数据验证
    }

    public Boolean edit(AddInventoryCheckItemRequest request) {
        if (null == request || null == request.getCheckNumber()) {
            throw new BusinessProcessException("盘点编号不能为空！");
        }
        validAddInventoryCheckItemRequest(request); // 也需要校验必填项
        String requestContent = JSONBinder.binder(UpdateUserRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/invnentorycheck/edit").body(requestContent));
    }

    public GetInventoryCheckItemResponse get(String checkNumber) {
        if (!StringUtils.hasText(checkNumber)) {
            throw new BusinessProcessException("条件不足不能获得物料！");
        }
        GetInventoryCheckItemRequest request = new GetInventoryCheckItemRequest();
        request.setCheckNumber(checkNumber);
        String requestContent = JSONBinder.binder(GetInventoryCheckItemRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(GetInventoryCheckItemResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/invnentorycheck/get").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetInventoryCheckItemResponse> getList(Integer pageNo, Integer pageSize) {
        GetInventoryCheckItemListRequest request = new GetInventoryCheckItemListRequest();
        request.setPageNo(pageNo == null ? "" : pageNo.toString());
        request.setPageSize(pageSize == null ? "" : pageSize.toString());
        String requestContent = JSONBinder.binder(GetInventoryCheckItemListRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetInventoryCheckItemResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/invnentorycheck/getlist")
                .body(requestContent));
    }

    public Boolean delete(List<String> ids, String updateBy) {
        if (null == ids) {
            throw new BusinessProcessException("不能删除没指定编号的！");
        }
        DeleteStorageRequest deleteStorageRequest = new DeleteStorageRequest();
        deleteStorageRequest.setIds(ids);
        String requestContent = JSONBinder.binder(DeletePostPeriodRequest.class).toJSON(deleteStorageRequest);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventorycheck/delete").body(requestContent));
    }
}
