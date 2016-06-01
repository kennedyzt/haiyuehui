package com.siping.service.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;

import com.siping.service.inventory.repository.InventoryReceiptItemRestRepository;
import com.siping.smartone.inventory.request.InventoryReceiptItemBatchDeleteRequest;
import com.siping.smartone.inventory.request.InventoryReceiptItemRequest;

@Service
public class InventoryReceiptItemRestService extends DBSwitch {
    @Autowired
    private InventoryReceiptItemRestRepository inventoryReceiptItemRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(InventoryReceiptItemRequest request) {
        return inventoryReceiptItemRestRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(InventoryReceiptItemBatchDeleteRequest request) {
        return inventoryReceiptItemRestRepository.delete(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(InventoryReceiptItemRequest request) {
        return inventoryReceiptItemRestRepository.edit(request);
    }

}
