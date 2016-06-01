package com.siping.service.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.inventory.repository.InventoryReceiptRestRepository;
import com.siping.smartone.inventory.request.ChangeStatusParam;
import com.siping.smartone.inventory.request.InventoryReceiptBatchDeleteRequest;
import com.siping.smartone.inventory.request.InventoryReceiptRequest;
import com.siping.smartone.inventory.request.InventoryReceitQueryParam;
import com.siping.smartone.inventory.response.InventoryReceiptResponse;
import com.siping.smartone.wms.request.ConfirmReceiptParam;

@Service
public class InventoryReceiptRestService extends DBSwitch {
    @Autowired
    private InventoryReceiptRestRepository inventoryReceiptRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(InventoryReceiptRequest request) {
        return inventoryReceiptRestRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(InventoryReceiptBatchDeleteRequest request) {
        return inventoryReceiptRestRepository.delete(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(InventoryReceiptRequest request) {
        return inventoryReceiptRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<InventoryReceiptResponse> getList(InventoryReceitQueryParam request) {
        return inventoryReceiptRestRepository.getList(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public InventoryReceiptResponse get(String receiptNumber) {
        return inventoryReceiptRestRepository.get(receiptNumber);
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean confirmReceipt(ConfirmReceiptParam request) {
        return inventoryReceiptRestRepository.confirmReceipt(request);
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean changeStatus(ChangeStatusParam request) {
        return inventoryReceiptRestRepository.changeStatus(request);
    }

}
