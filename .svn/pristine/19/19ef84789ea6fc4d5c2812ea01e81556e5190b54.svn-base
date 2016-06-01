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
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.purchase.request.DeletePurchaseApplyOrderRequest;
import com.siping.smartone.purchase.request.PurchaseApplyOrderRequest;
import com.siping.smartone.purchase.response.PurchaseApplyOrderResponse;

@Repository
public class PurchaseApplyOrderRepository {
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Value("${site.api.appkeys}")
    private String appKey;

    public ResultMsg add(PurchaseApplyOrderRequest purchaseApplyOrder) {
        String requestContent = JSONBinder.binder(PurchaseApplyOrderRequest.class).toJSON(purchaseApplyOrder);
        return stromaWebServiceApi.post(EndPointBuilder.create(ResultMsg.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchaseapplyorder/add").body(requestContent));
    }

    public Boolean delete(List<String> orderNumbers, Integer updateBy) {
        if (null == orderNumbers) {
            throw new BusinessProcessException("缺失主键不能删除采购申请单");
        }
        if (null == updateBy) {
            throw new BusinessProcessException("当前登录出现问题，无法修改");
        }
        DeletePurchaseApplyOrderRequest request = new DeletePurchaseApplyOrderRequest();
        request.setOrderNumber(orderNumbers);
        request.setUserId(updateBy + "");
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchaseapplyorder/delete").body(requestContent));
    }

    public PurchaseApplyOrderResponse get(String orderNumber) {
        return stromaWebServiceApi.post(EndPointBuilder.create(PurchaseApplyOrderResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchaseapplyorder/get/%s").arguments(orderNumber));
    }

    public Boolean edit(PurchaseApplyOrderRequest request) {
        if (null == request || !StringUtils.hasText(request.getPurchaseOrderNumber())) {
            throw new BusinessProcessException("未选择需要编辑的采购申请单！");
        }
        validRequest(request); // 需要校验必填项
        String requestContent = JSONBinder.binder(PurchaseApplyOrderRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchaseapplyorder/edit").body(requestContent));
    }

    @Valid
    /**
     * 数据验证
     * @param request
     */
    private void validRequest(PurchaseApplyOrderRequest request) {

    }

    @SuppressWarnings("unchecked")
    public PagingResponse<PurchaseApplyOrderResponse> getAllApplyPO(CommonBillsRequest request) {
        if (null == request.getPageNo() && null == request.getPageSize()) {
            throw new BusinessProcessException("条件不足不能查询");
        }
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchaseapplyorder/getallapplypo").body(requestContent));
    }

}
