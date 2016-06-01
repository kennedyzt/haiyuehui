package com.siping.intranet.crm.purchase.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.purchase.request.PurchaseReturnOrderForm;
import com.siping.smartone.purchase.request.PurchaseReturnsBatchDeleteRequest;
import com.siping.smartone.purchase.request.PurchaseReturnsOrderQueryParam;
import com.siping.smartone.purchase.request.PurchaseReturnsRequest;
import com.siping.smartone.purchase.response.PurchaseReturnsResponse;

@Repository
public class PurchaseReturnsRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Autowired
    private JSONConverter jsonConverter;

    @Value("${site.api.appkeys}")
    private String appKey;

    public ResultMsg add(PurchaseReturnsRequest request) {
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(ResultMsg.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchasereturns/add").body(requestContent));
    }

    public Boolean delete(List<String> returnsNumbers, String userId) {
        PurchaseReturnsBatchDeleteRequest request = new PurchaseReturnsBatchDeleteRequest();
        request.setReturnsNumbers(returnsNumbers);
        request.setUserId(userId);
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchasereturns/delete").body(requestContent));
    }

    public Boolean edit(PurchaseReturnsRequest request) {
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchasereturns/edit").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<PurchaseReturnsResponse> getList(Integer pageNo, Integer pageSize, PurchaseReturnOrderForm request) {
        if (null == pageNo && null == pageSize) {
            throw new BusinessProcessException("条件不足不能查询");
        }
        PurchaseReturnsOrderQueryParam purchaseReturnsOrderQueryParam = new PurchaseReturnsOrderQueryParam();
        purchaseReturnsOrderQueryParam.setPageNo(pageNo);
        purchaseReturnsOrderQueryParam.setPageSize(pageSize);
        purchaseReturnsOrderQueryParam.setKeywords(request.getKeywords());
        purchaseReturnsOrderQueryParam.setStartDate(request.getStartDate());
        purchaseReturnsOrderQueryParam.setEndDate(request.getEndDate());
        purchaseReturnsOrderQueryParam.setIsDraft(request.getIsDraft());
        String requestContent = jsonConverter.toString(purchaseReturnsOrderQueryParam);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(PurchaseReturnsResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/purchasereturns/getlist").body(requestContent));
    }

    public PurchaseReturnsResponse get(String returnsNumber) {
        return stromaWebServiceApi.get(EndPointBuilder.create(PurchaseReturnsResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchasereturns/get/%s").arguments(returnsNumber));
    }

}
