package com.siping.wms.allocate.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.siping.smartone.inventory.request.InventoryReceiptBatchDeleteRequest;
import com.siping.smartone.inventory.request.InventoryReceiptItemRequest;
import com.siping.smartone.inventory.request.PdaReceiptOrderParam;
import com.siping.smartone.inventory.response.InventoryReceiptResponse;
import com.siping.smartone.storage.request.StorageRequest;
import com.siping.smartone.wms.request.AddInventoryAllocateRequest;
import com.siping.smartone.wms.request.GetInventoryAllocateListRequest;
import com.siping.smartone.wms.response.GetInventoryAllocateResponse;

@Repository
public class InventoryAllocateRepository {

    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Autowired
    private JSONConverter jsonConverter;
    @Value("${site.api.appkeys}")
    private String appKey;

    @SuppressWarnings("unchecked")
    public PagingResponse<GetInventoryAllocateResponse> getInventoryAllocationList(Integer pageNo, Integer pageSize, GetInventoryAllocateListRequest getInventoryAllocateListRequest) {
        getInventoryAllocateListRequest.setPageNo(pageNo);
        getInventoryAllocateListRequest.setPageSize(pageSize);
        String requestContent = JSONBinder.binder(GetInventoryAllocateResponse.class).toJSON(getInventoryAllocateListRequest);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetInventoryAllocateResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/pda/inventoryallocate/getlist").body(requestContent));
    }
    
    public GetInventoryAllocateResponse get(String allocateNumber) {
        return stromaWebServiceApi.get(EndPointBuilder.create(GetInventoryAllocateResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/pda/inventoryallocate/get/%s").arguments(allocateNumber));
    }

    public boolean add(AddInventoryAllocateRequest request) {
        validAllocateBillsDate(request);
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/pda/inventoryallocate/add").body(requestContent));
    }

    private void validAllocateBillsDate(AddInventoryAllocateRequest request){//截取位数来格式化日期格式
            request.setBillsDate(request.getBillsDate().substring(0, 4)+request.getBillsDate().substring(5,7)+request.getBillsDate().substring(8,10));
    }
}
