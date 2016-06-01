package com.siping.intranet.invoicing.sell.shipments.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
import com.siping.smartone.invoicing.sell.shipments.request.AddSellShipmentsItemRequest;
import com.siping.smartone.invoicing.sell.shipments.request.AddSellShipmentsRequest;
import com.siping.smartone.invoicing.sell.shipments.request.BatchAddSellShipmentsRequest;
import com.siping.smartone.invoicing.sell.shipments.request.DeleteSellShipmentsRequest;
import com.siping.smartone.invoicing.sell.shipments.response.GetSellShipmentsListResponse;
import com.siping.smartone.invoicing.sell.shipments.response.GetSellShipmentsResponse;

@Repository
public class SellShipmentsRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(AddSellShipmentsRequest request) {
        validAddMaterialRequest(request);
        String requestContent = JSONBinder.binder(AddSellShipmentsRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellshipments/add").body(requestContent));
    }

    @Valid
    private void validAddMaterialRequest(AddSellShipmentsRequest request) {
        if (!StringUtils.hasText(request.getOutboundStorage()))
            throw new BusinessProcessException("请选择出库仓库！");
        if (!StringUtils.hasText(request.getPartnerId()))
            throw new BusinessProcessException("请选择客户！");
        if (null == request.getShipmentsItemList() || CollectionUtils.isEmpty(request.getShipmentsItemList()))
            throw new BusinessProcessException("销售发货单项不能为空！");
        if (!request.getIsDraft()) {// 如果不是存为草稿，需要对数据的合法性进行进一步的验证
            List<AddSellShipmentsItemRequest> itemRequests = new ArrayList<AddSellShipmentsItemRequest>();
            itemRequests = request.getShipmentsItemList();
            for (int i = 0; i < itemRequests.size(); i++) {
                if (!StringUtils.hasText(itemRequests.get(i).getMaterialId())) {
                    throw new BusinessProcessException("您的第' " + (int) (i + 1) + " '行销售发货项,没有选择商品,无法添加销售发货单");
                }
                if (null == itemRequests.get(i).getCounts()) {
                    throw new BusinessProcessException("您的第' " + (int) (i + 1) + " '行销售发货数量为不合法数据,无法添加销售发货单");
                }
                if (itemRequests.get(i).getCounts().equals("0")) {
                    throw new BusinessProcessException("您的第' " + (int) (i + 1) + " '行销售发货数量为零,无法添加销售发货单");
                }
                if (!checkDoubleNumber(itemRequests.get(i).getCounts().toString())) {
                    throw new BusinessProcessException("您的第' " + (int) (i + 1) + " '行销售发货数量为不合法数据,无法添加销售发货单");
                }
                if (null == itemRequests.get(i).getSellPrice()) {
                    throw new BusinessProcessException("您的第' " + (int) (i + 1) + " '行销售发货价格为不合法数据,无法添加销售发货单");
                }
                if (itemRequests.get(i).getIsGift() == true) {// 为赠品的时候，销售单价可以为零
                    if ((!checkDoubleNumber(itemRequests.get(i).getSellPrice().toString()))) {
                        throw new BusinessProcessException("您的第' " + (int) (i + 1) + " '行销售发货单价为不合法数据,无法添加销售发货单");
                    }
                }
                if (itemRequests.get(i).getIsGift() == false) {
                    if (itemRequests.get(i).getSellPrice().equals("0")) {
                        throw new BusinessProcessException("您的第' " + (int) (i + 1) + " '行销售发货数量为不合法数据,无法添加销售发货单");
                    }
                    if ((!checkDoubleNumber(itemRequests.get(i).getSellPrice().toString()))) {
                        throw new BusinessProcessException("您的第' " + (int) (i + 1) + " '行销售发货单价为不合法数据,无法添加销售发货单");
                    }
                }

            }
        }
    }

    private boolean checkDoubleNumber(String checkString) {// 正则表达式验证
        final String test = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";
        Pattern p = Pattern.compile(test);
        Matcher m = p.matcher(checkString);
        return m.matches();
    }

    public Boolean edit(AddSellShipmentsRequest request) {
        if (null != request && !StringUtils.hasText(request.getShipmentsNumber()))
            throw new BusinessProcessException("未选择需要编辑的销发货单！");
        validAddMaterialRequest(request); // 也需要校验必填项
        String requestContent = JSONBinder.binder(AddSellShipmentsRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellshipments/edit").body(requestContent));
    }

    public GetSellShipmentsResponse get(String shipmentsnumber) {
        if (!StringUtils.hasText(shipmentsnumber)) {
            throw new BusinessProcessException("条件不足不能获得销售发货单！");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(GetSellShipmentsResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellshipments/get/%s").arguments(shipmentsnumber));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetSellShipmentsListResponse> getList(Integer pageNo, Integer pageSize, CommonBillsForm form) {
        CommonBillsRequest request = new CommonBillsRequest();
        request.setStartDate(form.getStartDate());
        request.setEndDate(form.getEndDate());
        request.setKeywords(form.getKeywords());
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        request.setIsDraft(form.getIsDraft());
        String requestContent = JSONBinder.binder(CommonBillsRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellshipments/getlist").body(requestContent));
    }

    public Boolean delete(final List<String> shipmentsNumberList, Integer userId) {
        if (CollectionUtils.isEmpty(shipmentsNumberList))
            throw new BusinessProcessException("未选择需要删除的销售发货单！");
        DeleteSellShipmentsRequest request = new DeleteSellShipmentsRequest(shipmentsNumberList);
        request.setUserId(userId + "");
        String requestContent = JSONBinder.binder(DeleteSellShipmentsRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellshipments/delete").body(requestContent));
    }

    public Boolean adds(BatchAddSellShipmentsRequest request) {
        String requestContent = JSONBinder.binder(AddSellShipmentsRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellshipments/adds").body(requestContent));
    }
}
