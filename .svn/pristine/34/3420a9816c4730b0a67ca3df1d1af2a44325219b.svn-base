package com.siping.intranet.invoicing.inventory.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siping.intranet.invoicing.inventory.inventory.repository.InventoryRepository;
import com.siping.smartone.inventory.request.MaterialCountsRequest;
import com.siping.smartone.inventory.response.InventoryWarningMsg;
import com.siping.smartone.invoicing.inventory.inventory.request.AddInventoryRequest;
import com.siping.smartone.invoicing.inventory.inventory.request.GetInventoryIsEnoughRequest;
import com.siping.smartone.invoicing.inventory.inventory.response.GetInventoryLogResponse;
import com.siping.smartone.invoicing.inventory.inventory.response.GetInventoryResponse;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public Boolean add(AddInventoryRequest request) {
        return inventoryRepository.add(request);
    }

    public Boolean edit(AddInventoryRequest request) {
        return inventoryRepository.edit(request);
    }

    public GetInventoryResponse get(String id) {
        return inventoryRepository.get(id);
    }

    public List<GetInventoryLogResponse> getList(String materialId, String storageId) {
        return inventoryRepository.getList(materialId, storageId);
    }

    public Boolean isEnough(GetInventoryIsEnoughRequest request) {
        return inventoryRepository.isEnough(request);
    }

    public InventoryWarningMsg isMoreThan(List<MaterialCountsRequest> request) {
        return inventoryRepository.isMoreThan(request);
    }

}
