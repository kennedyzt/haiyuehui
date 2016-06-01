package com.siping.wms.readyreceipt.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;
import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.wms.request.GenerateReceiptParam;
import com.siping.smartone.wms.response.ReadyReceiptResponse;

@Repository
public class ReadyReceiptOrderRepository {

    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Value("${site.api.appkeys}")
    private String appKey;

    public ReadyReceiptResponse get(String orderNumber) {
        return stromaWebServiceApi.get(EndPointBuilder.create(ReadyReceiptResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/readyreceipt/get/%s").arguments(orderNumber));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<ReadyReceiptResponse> getList(CommonBillsRequest request) {
        if (null == request.getPageNo() && null == request.getPageSize()) {
            throw new BusinessProcessException("条件不足不能查询");
        }
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi
            .post(EndPointBuilder.create(PagingResponse.class).elementTypes(ReadyReceiptResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/readyreceipt/getlist").body(requestContent));
    }

    public Boolean generateReceipt(GenerateReceiptParam request) {
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/readyreceipt/generateReceipt").body(requestContent));
    }

}
