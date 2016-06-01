package com.siping.wms.readyreceipt.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.wms.request.OrderPickQueryParam;
import com.siping.smartone.wms.response.OrderPickResponse;

@Repository
public class OrderPickRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(OrderPickQueryParam request) {
        String requestContent = JSONBinder.binder(CommonBillsRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/orderpick/add").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<OrderPickResponse> getList(CommonBillsRequest request) {
        String requestContent = JSONBinder.binder(CommonBillsRequest.class).toJSON(request);
        return stromaWebServiceApi
            .post(EndPointBuilder.create(PagingResponse.class).elementTypes(OrderPickResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/orderpick/getlist").body(requestContent));
    }

    public OrderPickResponse get(String orderNumber) {
        return stromaWebServiceApi.get(EndPointBuilder.create(OrderPickResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/orderpick/get/%s").arguments(orderNumber));
    }

    public Boolean updateStatus(String orderNumber, Integer loginId, Integer status) {
        return stromaWebServiceApi
            .get(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/orderpick/updatestatus/%s/%s/%s").arguments(orderNumber, loginId, status));
    }
}
