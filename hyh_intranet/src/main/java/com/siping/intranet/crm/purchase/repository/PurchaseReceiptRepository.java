package com.siping.intranet.crm.purchase.repository;

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

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.login.request.UpdateUserRequest;
import com.siping.smartone.purchase.request.DeletePurchaseReceiptRequest;
import com.siping.smartone.purchase.request.PurchaseReceiptRequest;
import com.siping.smartone.purchase.response.PurchaseReceiptResponse;

@Repository
public class PurchaseReceiptRepository {
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public ResultMsg add(PurchaseReceiptRequest request) {
        validPurchaseReceiptRequest(request);
        String requestContent = JSONBinder.binder(PurchaseReceiptRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(ResultMsg.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchasereceipt/add").body(requestContent));
    }

    @Valid
    private Boolean validPurchaseReceiptRequest(PurchaseReceiptRequest request) {
        return true;
    }

    public Boolean edit(PurchaseReceiptRequest request) {
        if (!validPurchaseReceiptRequest(request)) {// 也需要校验必填项
            throw new BusinessProcessException("数据有问题哈");
        }
            
        String requestContent = JSONBinder.binder(UpdateUserRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchasereceipt/edit").body(requestContent));
    }

    public PurchaseReceiptResponse get(String orderNumber) {
        return stromaWebServiceApi.post(EndPointBuilder.create(PurchaseReceiptResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchasereceipt/get/%s").arguments(orderNumber));
    }

    public boolean delete(List<String> orderNumbers, Integer loginId) {
        if (null == orderNumbers) {
            throw new BusinessProcessException("缺失主键不能删除采购订单");
        }
        if (null == loginId) {
            throw new BusinessProcessException("当前登录出现问题，无法修改");
        }
        
        DeletePurchaseReceiptRequest request = new DeletePurchaseReceiptRequest();
        request.setOrderNumber(orderNumbers);
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchasereceipt/delete").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<PurchaseReceiptResponse> getList(CommonBillsRequest request) {
        if (null == request.getPageNo() && null == request.getPageSize()) {
            throw new BusinessProcessException("条件不足不能查询");
        }
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchasereceipt/getlist").body(requestContent));
    }
}
