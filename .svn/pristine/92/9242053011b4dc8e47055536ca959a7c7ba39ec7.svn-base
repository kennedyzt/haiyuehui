package com.siping.intranet.invoicing.sell.order.repository;

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
import com.siping.smartone.invoicing.sell.order.request.AddSellOrderRequest;
import com.siping.smartone.invoicing.sell.order.request.DeleteSellOrderRequest;
import com.siping.smartone.invoicing.sell.order.response.GetSellOrderListResponse;
import com.siping.smartone.invoicing.sell.order.response.GetSellOrderResponse;

@Repository
public class SellOrderRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(AddSellOrderRequest request) {
        validAddMaterialRequest(request);
        String requestContent = JSONBinder.binder(AddSellOrderRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellorder/add").body(requestContent));
    }

    @Valid
    private void validAddMaterialRequest(AddSellOrderRequest request) {
        if (!StringUtils.hasText(request.getPartnerId()))
            throw new BusinessProcessException("客户不能为空！");
        if (!StringUtils.hasText(request.getOutboundStorage()))
            throw new BusinessProcessException("出库仓库不能为空！");
        if (null == request.getOrderItemList() || CollectionUtils.isEmpty(request.getOrderItemList()))
            throw new BusinessProcessException("销售订单项不能为空！");
    }

    public Boolean edit(AddSellOrderRequest request) {
        if (null == request || !StringUtils.hasText(request.getOrderNumber())) {
            throw new BusinessProcessException("未选择需要编辑的销售订单！");
        }
        validAddMaterialRequest(request); // 也需要校验必填项
        String requestContent = JSONBinder.binder(AddSellOrderRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellorder/edit").body(requestContent));
    }

    public GetSellOrderResponse get(String orderNumber) {
        if (!StringUtils.hasText(orderNumber)) {
            throw new BusinessProcessException("条件不足不能获取销售订单！");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(GetSellOrderResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellorder/get/%s").arguments(orderNumber));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetSellOrderListResponse> getList(Integer pageNo, Integer pageSize, CommonBillsForm form) {
        CommonBillsRequest request = new CommonBillsRequest();
        request.setStartDate(form.getStartDate());
        request.setEndDate(form.getEndDate());
        request.setKeywords(form.getKeywords());
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        request.setIsDraft(form.getIsDraft());
        request.setIsPay(form.getIsPay());
        String requestContent = JSONBinder.binder(CommonBillsRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellorder/getlist").body(requestContent));
    }

    public Boolean delete(final List<String> orderNumberList, Integer userId) {
        if (CollectionUtils.isEmpty(orderNumberList))
            throw new BusinessProcessException("未选择需要删除的销售订单！");
        DeleteSellOrderRequest request = new DeleteSellOrderRequest(orderNumberList);
        request.setUserId(userId + "");
        String requestContent = JSONBinder.binder(DeleteSellOrderRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellorder/delete").body(requestContent));
    }
}
