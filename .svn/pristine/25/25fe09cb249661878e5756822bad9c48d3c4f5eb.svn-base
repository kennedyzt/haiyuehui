package com.siping.service.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;

import com.siping.service.inventory.repository.InventoryRestRepository;
import com.siping.smartone.inventory.request.MaterialCountsRequest;
import com.siping.smartone.inventory.response.InventoryWarningMsg;
import com.siping.smartone.invoicing.inventory.inventory.request.AddInventoryRequest;
import com.siping.smartone.invoicing.inventory.inventory.request.GetInventoryIsEnoughRequest;
import com.siping.smartone.invoicing.inventory.inventory.response.GetInventoryLogResponse;
import com.siping.smartone.invoicing.inventory.inventory.response.GetInventoryResponse;

@Service
public class InventoryRestService extends DBSwitch {
    @Autowired
    private InventoryRestRepository inventoryRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(AddInventoryRequest request) {
        return inventoryRestRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(AddInventoryRequest request) {
        return inventoryRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GetInventoryResponse get(String id) {
        return inventoryRestRepository.get(id);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<GetInventoryLogResponse> getList(String materialId, String storageId) {
        return inventoryRestRepository.getList(materialId, storageId);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public Boolean isEnough(GetInventoryIsEnoughRequest request) {
        return inventoryRestRepository.isEnough(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public InventoryWarningMsg isMoreThan(List<MaterialCountsRequest> request) {
        return inventoryRestRepository.isMoreThan(request);
    }
}
