package com.siping.wms.readyreceipt.repository;

import java.util.List;

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
import com.siping.smartone.wms.request.AddPackRequest;
import com.siping.smartone.wms.request.ESaleOrderRequest;
import com.siping.smartone.wms.request.OrderPickQueryParam;
import com.siping.smartone.wms.request.ReadyShipmentsRequest;
import com.siping.smartone.wms.response.OrderPickResponse;
import com.siping.smartone.wms.response.ReadyShipmentsResponse;

@Repository
public class ReadyShipmentsRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(ESaleOrderRequest request) {
        String requestContent = JSONBinder.binder(AddSellOrderRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/readyshipments/add").body(requestContent));
    }

    public ReadyShipmentsResponse get(String orderNumber) {
        if (!StringUtils.hasText(orderNumber)) {
            throw new BusinessProcessException("条件不足不能获取销售订单！");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(ReadyShipmentsResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/readyshipments/get/%s").arguments(orderNumber));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<ReadyShipmentsResponse> getList(Integer pageNo, Integer pageSize, CommonBillsForm form) {
        CommonBillsRequest request = new CommonBillsRequest();
        request.setStartDate(form.getStartDate());
        request.setEndDate(form.getEndDate());
        request.setKeywords(form.getKeywords());
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        request.setIsDraft(form.getIsDraft());
        request.setIsPay(form.getIsPay());
        request.setStatus(form.getStatus());
        String requestContent = JSONBinder.binder(CommonBillsRequest.class).toJSON(request);
        return stromaWebServiceApi.post(
            EndPointBuilder.create(PagingResponse.class).elementTypes(ReadyShipmentsResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/readyshipments/getlist").body(requestContent));
    }

    public OrderPickResponse get(List<String> orderNumbers) {
        OrderPickQueryParam request = new OrderPickQueryParam();
        request.setOrderNumbers(orderNumbers);
        String requestContent = JSONBinder.binder(CommonBillsRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(OrderPickResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/readyshipments/get").body(requestContent));
    }

    public Boolean confirmAuditByOrderNumber(String orderNumber) {
        return stromaWebServiceApi.get(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/readyshipments/confirmAudit/%s").arguments(orderNumber));
    }

    public Boolean tempSaveAudit(ReadyShipmentsRequest request) {
        String requestContent = JSONBinder.binder(ReadyShipmentsRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/readyshipments/tempSaveAudit").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<ReadyShipmentsResponse> getAllTempAuditOrder() {
        return stromaWebServiceApi
            .post(EndPointBuilder.create(List.class).elementTypes(ReadyShipmentsResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/readyshipments/getAllTempAuditOrder"));
    }

    public ReadyShipmentsResponse getbyTempAudit(String orderNumber) {
        if (!StringUtils.hasText(orderNumber)) {
            throw new BusinessProcessException("条件不足不能获取发货订单！");
        }
        return stromaWebServiceApi
            .get(EndPointBuilder.create(ReadyShipmentsResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/readyshipments/getbyTempAudit/%s").arguments(orderNumber));
    }

    public boolean savePack(AddPackRequest request) {
        String requestContent = JSONBinder.binder(AddPackRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/readyshipments/savePack").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<ReadyShipmentsResponse> printEMS(int pageNo, int pageSize, CommonBillsForm form) {
        CommonBillsRequest request = new CommonBillsRequest();
        request.setStartDate(form.getStartDate());
        request.setEndDate(form.getEndDate());
        request.setKeywords(form.getKeywords());
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        request.setIsDraft(form.getIsDraft());
        request.setIsPay(form.getIsPay());
        request.setStatus(form.getStatus());
        String requestContent = JSONBinder.binder(CommonBillsRequest.class).toJSON(request);
        return stromaWebServiceApi.post(
            EndPointBuilder.create(PagingResponse.class).elementTypes(ReadyShipmentsResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/readyshipments/printems").body(requestContent));
    }

    public Boolean updateStatus(String id, int status,Integer userId) {
        return stromaWebServiceApi.get(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/readyshipments/updatestatus/%s/%s/%s").arguments(id, status,userId));
    }

}
