package com.siping.intranet.crm.inventory.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.inventory.request.InventoryReceiptItemBatchDeleteRequest;
import com.siping.smartone.inventory.request.InventoryReceiptItemRequest;

@Repository
public class InventoryReceiptItemRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Autowired
    private JSONConverter jsonConverter;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(InventoryReceiptItemRequest request) {
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventoryreceiptitem/add").body(requestContent));
    }

    public Boolean delete(List<Integer> ids) {
        InventoryReceiptItemBatchDeleteRequest request = new InventoryReceiptItemBatchDeleteRequest();
        request.setIds(ids);
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventoryreceiptitem/delete").body(requestContent));
    }

    public Boolean edit(InventoryReceiptItemRequest request) {
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventoryreceiptitem/edit").body(requestContent));
    }

}
