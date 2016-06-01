package com.siping.wms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.material.repository.MaterialRestRepository;
import com.siping.smartone.inventory.request.InventoryReceiptBatchDeleteRequest;
import com.siping.smartone.inventory.request.PdaReceiptOrderParam;
import com.siping.smartone.inventory.response.GetInventoryCheckResponseNew;
import com.siping.smartone.inventory.response.InventoryReceiptItemResponse;
import com.siping.smartone.inventory.response.InventoryReceiptResponse;
import com.siping.smartone.material.response.GetMaterialBatchResponse;
import com.siping.smartone.wms.request.AddInventoryAllocateRequest;
import com.siping.wms.repository.InventoryAllocateRestRepository;
import com.siping.wms.repository.PdaRestRepository;

@Service
public class PdaRestService extends DBSwitch {

    @Autowired
    private PdaRestRepository pdaRestRepository;
    @Autowired
    private MaterialRestRepository materialRestRepository;
    @Autowired
    private InventoryAllocateRestRepository inventoryAllocateRestRepository;

    public PagingResponse<GetInventoryCheckResponseNew> getInventoryCheckList(PdaReceiptOrderParam request) {
        return pdaRestRepository.getInventoryCheckList(request);
    }

    public PagingResponse<InventoryReceiptResponse> getList(PdaReceiptOrderParam request) {
        return pdaRestRepository.getList(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public InventoryReceiptResponse get(String receiptNumber) {
        return pdaRestRepository.get(receiptNumber);
    }

    // public Boolean add(InventoryReceiptRequest request) {
    // return pdaRestRepository.add(request);
    // }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(InventoryReceiptBatchDeleteRequest request) {
        return pdaRestRepository.delete(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addItem(InventoryReceiptItemResponse request) {
        return pdaRestRepository.addItem(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(InventoryReceiptItemResponse request) {
        return pdaRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public String getRowNumber(String batchNumber, int materialid) {
        return pdaRestRepository.getRowNumber(batchNumber, materialid);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<GetMaterialBatchResponse> getMaterialBatchByStorageLocation(String locationId) {
        return pdaRestRepository.getMaterialBatchByStorageLocation(locationId);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<GetMaterialBatchResponse> getStorageLocationWithMaterialBatchByMaterial(String materialNo) {
        return pdaRestRepository.getStorageLocationWithMaterialBatchByMaterial(materialNo);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public Boolean checkBatchNo(String batchNo, String materialId) {
        return pdaRestRepository.checkBatchNumber(batchNo, materialId);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean commitInventoryCheck(GetInventoryCheckResponseNew request) {
        // TODO Auto-generated method stub
        return pdaRestRepository.commitInventoryCheck(request);
    }

    public GetInventoryCheckResponseNew getInventoryCheck(String checkNumber) {
        // TODO Auto-generated method stub
        return pdaRestRepository.getInventoryCheck(checkNumber);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public Boolean checkAllocateNumber(AddInventoryAllocateRequest request) { // 校验仓库中的商品数量是否足够
        // TODO Auto-generated method stub
        return inventoryAllocateRestRepository.checkAllocateNumber(request);
    }
}
