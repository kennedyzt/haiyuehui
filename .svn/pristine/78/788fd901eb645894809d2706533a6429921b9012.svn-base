package com.siping.intranet.crm.inventory.repository;

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
import com.siping.intranet.common.CommonBillsForm;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.inventory.request.AddInventoryShipmentsRequest;
import com.siping.smartone.inventory.request.DeleteInventoryShipmentsRequest;
import com.siping.smartone.inventory.response.GetInventoryShipmentsListResponse;
import com.siping.smartone.inventory.response.GetInventoryShipmentsResponse;

@Repository
public class InventoryShipmentsRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(AddInventoryShipmentsRequest request) {
        validAddMaterialRequest(request);
        String requestContent = JSONBinder.binder(AddInventoryShipmentsRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventoryshipments/add").body(requestContent));
    }

    @Valid
    private void validAddMaterialRequest(AddInventoryShipmentsRequest request) {
        if (!StringUtils.hasText(request.getOutboundStorage()))
            throw new BusinessProcessException("出库仓库不能为空！");
        if (null == request.getShipmentsItemList() || CollectionUtils.isEmpty(request.getShipmentsItemList()))
            throw new BusinessProcessException("库存发货单项不能为空！");
    }

    public Boolean edit(AddInventoryShipmentsRequest request) {
        if (null == request || !StringUtils.hasText(request.getShipmentsNnumber())) {
            throw new BusinessProcessException("未选择需要编辑的库存发货单！");
        }
        validAddMaterialRequest(request); // 也需要校验必填项
        String requestContent = JSONBinder.binder(AddInventoryShipmentsRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventoryshipments/edit").body(requestContent));
    }

    public GetInventoryShipmentsResponse get(String shipmentsNumber) {
        if (!StringUtils.hasText(shipmentsNumber)) {
            throw new BusinessProcessException("条件不足不能获取库存发货单！");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(GetInventoryShipmentsResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventoryshipments/get/%s")
            .arguments(shipmentsNumber));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetInventoryShipmentsListResponse> getList(Integer pageNo, Integer pageSize, CommonBillsForm form) {
        CommonBillsRequest request = new CommonBillsRequest();
        request.setStartDate(form.getStartDate());
        request.setEndDate(form.getEndDate());
        request.setKeywords(form.getKeywords());
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        request.setIsDraft(form.getIsDraft());
        String requestContent = JSONBinder.binder(CommonBillsRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventoryshipments/getlist").body(requestContent));
    }

    public Boolean delete(final List<String> list, Integer userId) {
        if (CollectionUtils.isEmpty(list))
            throw new BusinessProcessException("未选择需要删除的库存发货单！");
        DeleteInventoryShipmentsRequest request = new DeleteInventoryShipmentsRequest(list);
        request.setDeleteUserId(userId);
        String requestContent = JSONBinder.binder(DeleteInventoryShipmentsRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventoryshipments/delete").body(requestContent));
    }
}
