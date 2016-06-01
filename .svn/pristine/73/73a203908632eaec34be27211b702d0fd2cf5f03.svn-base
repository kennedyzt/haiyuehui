package com.siping.service.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.inventory.repository.InventoryCheckItemRestRepository;
import com.siping.smartone.inventory.request.AddInventoryCheckItemRequest;
import com.siping.smartone.inventory.request.DeleteInventoryCheckItemRequest;
import com.siping.smartone.inventory.request.GetInventoryCheckItemListRequest;
import com.siping.smartone.inventory.request.GetInventoryCheckItemRequest;
import com.siping.smartone.inventory.response.GetInventoryCheckItemResponse;

@Service
public class InventoryCheckItemRestService extends DBSwitch {
    @Autowired
    private InventoryCheckItemRestRepository inventoryCheckItemRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(AddInventoryCheckItemRequest request) {
        return inventoryCheckItemRestRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(AddInventoryCheckItemRequest request) {
        return inventoryCheckItemRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GetInventoryCheckItemResponse get(GetInventoryCheckItemRequest request) {
        return inventoryCheckItemRestRepository.get(request.getCheckNumber());
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetInventoryCheckItemResponse> getList(GetInventoryCheckItemListRequest request) {
        return inventoryCheckItemRestRepository.getList(request);
    }

    public Boolean delete(DeleteInventoryCheckItemRequest request) {
        return inventoryCheckItemRestRepository.delete(request);
    }

}
