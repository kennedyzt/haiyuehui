package com.siping.wms.pda.repository;

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
import com.siping.smartone.inventory.request.AddInventoryCheckItemRequest;
import com.siping.smartone.inventory.request.InventoryReceiptBatchDeleteRequest;
import com.siping.smartone.inventory.request.InventoryReceiptRequest;
import com.siping.smartone.inventory.request.PdaReceiptOrderParam;
import com.siping.smartone.inventory.response.GetInventoryCheckResponseNew;
import com.siping.smartone.inventory.response.InventoryReceiptItemResponse;
import com.siping.smartone.inventory.response.InventoryReceiptResponse;
import com.siping.smartone.material.response.GetMaterialBatchResponse;
import com.siping.smartone.wms.request.AddInventoryAllocateRequest;

@Repository
public class PdaRepository {

    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Autowired
    private JSONConverter jsonConverter;
    @Value("${site.api.appkeys}")
    private String appKey;

    @SuppressWarnings("unchecked")
    public PagingResponse<InventoryReceiptResponse> getReceiptOrderList(Integer pageNo, Integer pageSize, Integer userid, Integer status) {
        PdaReceiptOrderParam param = new PdaReceiptOrderParam();
        param.setPageNo(pageNo);
        param.setPageSize(pageSize);
        param.setStatus(status);
        param.setUserid(userid);
        String requestContent = jsonConverter.toString(param);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(InventoryReceiptResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/pda/inventoryreceipt/getlist").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetInventoryCheckResponseNew> getInventoryCheckList(Integer pageNo, Integer pageSize, Integer userid, Integer status) {
        PdaReceiptOrderParam param = new PdaReceiptOrderParam();
        param.setPageNo(pageNo);
        param.setPageSize(pageSize);
        param.setStatus(status);
        param.setUserid(userid);
        String requestContent = jsonConverter.toString(param);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetInventoryCheckResponseNew.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/pda/inventorycheck/getlist").body(requestContent));
    }

    public InventoryReceiptResponse get(String receiptNumber) {
        return stromaWebServiceApi
            .get(EndPointBuilder.create(InventoryReceiptResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/pda/inventoryreceipt/get/%s").arguments(receiptNumber));
    }

    public boolean add(InventoryReceiptRequest request) {
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/pda/inventoryreceipt/add").body(requestContent));
    }

    public boolean delete(List<String> receiptNumbers) {
        if (0 >= receiptNumbers.size()) {
            throw new BusinessProcessException("请选择需要删除的列！");
        }
        InventoryReceiptBatchDeleteRequest request = new InventoryReceiptBatchDeleteRequest();
        request.setReceiptNumbers(receiptNumbers);
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/pda/inventoryreceipt/delete").body(requestContent));
    }

    public boolean addItem(InventoryReceiptItemResponse request) {
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/pda/inventoryreceiptitem/add").body(requestContent));
    }

    public String getRowNumber(String batchNumber,int materialId) {
        return stromaWebServiceApi
            .get(EndPointBuilder.create(String.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/pda/inventoryreceiptitem/getrownumber/%s/%d").arguments(batchNumber, materialId));
    }

    public boolean edit(InventoryReceiptItemResponse request) {
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/pda/inventoryreceiptitem/edit").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<GetMaterialBatchResponse> getMaterialBatchByStorageLocation(String locationNo) {
        String requestContent = jsonConverter.toString(locationNo);
        return stromaWebServiceApi.post(EndPointBuilder.create(List.class).elementTypes(GetMaterialBatchResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/pda/getstoragelocationmaterialwithbatch/getlist").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<GetMaterialBatchResponse> getStorageLocationWithMaterialBatchByMaterial(String materialNo) {
        String requestContent = jsonConverter.toString(materialNo);
        return stromaWebServiceApi.post(EndPointBuilder.create(List.class).elementTypes(GetMaterialBatchResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/pda/getStorageLocationWithMaterialBatchByMaterial/getlist").body(requestContent));
    }

    public Boolean checkBatchNo(String batchNo, String materialId) {
        return stromaWebServiceApi.get(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/pda/checkbatchno/%s/%s").arguments(batchNo, materialId));
    }

    public boolean commitInventoryCheck(GetInventoryCheckResponseNew request) {
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/pda/inventorycheck/commit").body(requestContent));
    }

    public GetInventoryCheckResponseNew getInventoryCheck(String checkNumber) {
        return stromaWebServiceApi
            .get(EndPointBuilder.create(GetInventoryCheckResponseNew.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/pda/inventorycheck/get/%s").arguments(checkNumber));
    }

    public Boolean checkAllocateNumber(AddInventoryAllocateRequest request) {
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/pda/checkallocatenumber").body(requestContent));
    }
}
