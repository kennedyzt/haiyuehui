package com.siping.intranet.invoicing.sell.returns.repository;

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
import com.siping.smartone.invoicing.sell.returns.request.AddSellReturnsRequest;
import com.siping.smartone.invoicing.sell.returns.request.DeleteSellReturnsRequest;
import com.siping.smartone.invoicing.sell.returns.response.GetSellReturnsListResponse;
import com.siping.smartone.invoicing.sell.returns.response.GetSellReturnsResponse;

@Repository
public class SellReturnsRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(AddSellReturnsRequest request) {
        validSellReturnsRequest(request);
        String requestContent = JSONBinder.binder(AddSellReturnsRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellreturns/add").body(requestContent));
    }

    @Valid
    private void validSellReturnsRequest(AddSellReturnsRequest request) {
        if (null == request.getPartnerId())
            throw new BusinessProcessException("客户不能为空！");
        if (!StringUtils.hasText(request.getInboundStorage()))
            throw new BusinessProcessException("入库仓库不能为空！");
        if (null == request.getReturnsItemList() || CollectionUtils.isEmpty(request.getReturnsItemList()))
            throw new BusinessProcessException("销售订单项不能为空！");
    }

    public Boolean edit(AddSellReturnsRequest request) {
        if (null != request && !StringUtils.hasText(request.getReturnsNumber()))
            throw new BusinessProcessException("未选择需要编辑的销售退货单！");
        validSellReturnsRequest(request); // 也需要校验必填项
        String requestContent = JSONBinder.binder(AddSellReturnsRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellreturns/edit").body(requestContent));
    }

    public GetSellReturnsResponse get(String returnsNumber) {
        if (!StringUtils.hasText(returnsNumber)) {
            throw new BusinessProcessException("条件不足不能获取销售退货单！");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(GetSellReturnsResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellreturns/get/%s").arguments(returnsNumber));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetSellReturnsListResponse> getList(Integer pageNo, Integer pageSize, CommonBillsForm form) {
        CommonBillsRequest request = new CommonBillsRequest();
        request.setStartDate(form.getStartDate());
        request.setEndDate(form.getEndDate());
        request.setKeywords(form.getKeywords());
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        request.setIsDraft(form.getIsDraft());
        String requestContent = JSONBinder.binder(CommonBillsRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellreturns/getlist").body(requestContent));
    }

    public Boolean delete(final List<String> returnsNumberList, Integer userId) {
        if (CollectionUtils.isEmpty(returnsNumberList))
            throw new BusinessProcessException("未选择需要删除的销售退货单！");
        DeleteSellReturnsRequest request = new DeleteSellReturnsRequest(returnsNumberList);
        request.setUserId(userId + "");
        String requestContent = JSONBinder.binder(DeleteSellReturnsRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellreturns/delete").body(requestContent));
    }
}
