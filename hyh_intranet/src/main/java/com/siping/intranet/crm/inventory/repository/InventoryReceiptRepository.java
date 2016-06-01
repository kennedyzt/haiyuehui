package com.siping.intranet.crm.inventory.repository;

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
import com.siping.smartone.inventory.request.ChangeStatusParam;
import com.siping.smartone.inventory.request.GetInventoryReceiptListForm;
import com.siping.smartone.inventory.request.InventoryReceiptBatchDeleteRequest;
import com.siping.smartone.inventory.request.InventoryReceiptRequest;
import com.siping.smartone.inventory.request.InventoryReceitQueryParam;
import com.siping.smartone.inventory.response.InventoryReceiptResponse;
import com.siping.smartone.wms.request.ConfirmReceiptParam;

@Repository
public class InventoryReceiptRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Autowired
    private JSONConverter jsonConverter;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(InventoryReceiptRequest request) {
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventoryreceipt/add").body(requestContent));
    }

    public Boolean delete(List<String> receiptNumbers) {
        if (0 >= receiptNumbers.size()) {
            throw new BusinessProcessException("请选择需要删除的列！");
        }
        InventoryReceiptBatchDeleteRequest request = new InventoryReceiptBatchDeleteRequest();
        request.setReceiptNumbers(receiptNumbers);
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventoryreceipt/delete").body(requestContent));
    }

    public Boolean edit(InventoryReceiptRequest request) {
        if (null == request.getReceiptNumber()) {
            throw new BusinessProcessException("缺失主键，不能修改库存收货单！");
        }
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventoryreceipt/edit").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<InventoryReceiptResponse> getList(Integer pageNo, Integer pageSize, GetInventoryReceiptListForm request) {
        if (null == pageNo && null == pageSize) {
            throw new BusinessProcessException("条件不足不能查询");
        }
        InventoryReceitQueryParam inventoryReceitQueryParam = new InventoryReceitQueryParam();
        inventoryReceitQueryParam.setPageNo(pageNo);
        inventoryReceitQueryParam.setPageSize(pageSize);
        inventoryReceitQueryParam.setIsDraft(request.getIsDraft());
        inventoryReceitQueryParam.setKeywords(request.getKeywords());
        inventoryReceitQueryParam.setStartDate(request.getStartDate());
        inventoryReceitQueryParam.setEndDate(request.getEndDate());
        inventoryReceitQueryParam.setStatus(request.getStatus());
        String requestContent = jsonConverter.toString(inventoryReceitQueryParam);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(InventoryReceiptResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/inventoryreceipt/getlist").body(requestContent));
    }

    public InventoryReceiptResponse get(String receiptNumber) {
        return stromaWebServiceApi.get(EndPointBuilder.create(InventoryReceiptResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventoryreceipt/get/%s").arguments(receiptNumber));
    }

    public Boolean confirmReceipt(ConfirmReceiptParam request) {
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventoryreceipt/confirmreceipt").body(requestContent));
    }

    public Boolean changeStatus(ChangeStatusParam request) {
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventoryreceipt/changestatus").body(requestContent));
    }

}
