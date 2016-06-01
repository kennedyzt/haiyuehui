package com.siping.intranet.crm.purchase.repository;

import java.util.List;

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
import com.siping.smartone.purchase.request.DeletePurchaseOrderRequest;
import com.siping.smartone.purchase.request.PurchaseOrderRequest;
import com.siping.smartone.purchase.response.PurchaseOrderResponse;

@Repository
public class PurchaseOrderRepository {

    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Value("${site.api.appkeys}")
    private String appKey;

    public ResultMsg add(PurchaseOrderRequest purchaseOrder) {
        String requestContent = jsonConverter.toString(purchaseOrder);
        return stromaWebServiceApi.post(EndPointBuilder.create(ResultMsg.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchaseorder/add").body(requestContent));
    }

    public boolean delete(List<String> orderNumbers, Integer updateBy) {
        if (null == orderNumbers) {
            throw new BusinessProcessException("缺失主键不能删除采购订单");
        }
        if (null == updateBy) {
            throw new BusinessProcessException("当前登录出现问题，无法修改");
        }

        DeletePurchaseOrderRequest request = new DeletePurchaseOrderRequest();
        request.setOrderNumber(orderNumbers);
        request.setUserId(updateBy + "");
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchaseorder/delete").body(requestContent));
    }

    public Boolean edit(PurchaseOrderRequest request) {
        if (null == request || !StringUtils.hasText(request.getPurchaseOrderNumber())) {
            throw new BusinessProcessException("未选择需要编辑的采购订单！");
        }
        validRequest(request); // 需要校验必填项
        String requestContent = JSONBinder.binder(PurchaseOrderRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchaseorder/edit").body(requestContent));
    }

    public PurchaseOrderResponse get(String orderNumber) {
        return stromaWebServiceApi.post(EndPointBuilder.create(PurchaseOrderResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchaseorder/get/%s").arguments(orderNumber));
    }

    /**
     * 数据验证
     * @param request
     */
    private void validRequest(PurchaseOrderRequest request) {

    }

    @SuppressWarnings("unchecked")
    public PagingResponse<PurchaseOrderResponse> getList(CommonBillsRequest request) {
        if (null == request.getPageNo() && null == request.getPageSize()) {
            throw new BusinessProcessException("条件不足不能查询");
        }
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchaseorder/getlist").body(requestContent));
    }

}
