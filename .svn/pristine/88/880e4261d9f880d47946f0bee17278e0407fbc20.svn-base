package com.siping.intranet.crm.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siping.intranet.crm.inventory.repository.InventoryReceiptItemRepository;
import com.siping.smartone.inventory.request.InventoryReceiptItemRequest;

@Service
public class InventoryReceiptItemService {
    @Autowired
    private InventoryReceiptItemRepository inventoryReceiptItemRepository;

    public Boolean add(InventoryReceiptItemRequest request) {
        return inventoryReceiptItemRepository.add(request);

    }

    public Boolean edit(InventoryReceiptItemRequest request) {
        return inventoryReceiptItemRepository.edit(request);
    }

    public Boolean delete(List<Integer> ids) {
        return inventoryReceiptItemRepository.delete(ids);
    }

}
