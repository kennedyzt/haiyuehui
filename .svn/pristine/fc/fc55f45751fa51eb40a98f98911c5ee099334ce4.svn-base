package com.siping.intranet.crm.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.inventory.repository.InventoryCheckItemRepository;
import com.siping.smartone.inventory.request.AddInventoryCheckItemRequest;
import com.siping.smartone.inventory.response.GetInventoryCheckItemResponse;

@Service
public class InventoryCheckItemService {
    @Autowired
    private InventoryCheckItemRepository inventoryCheckItemRepository;

    public Boolean add(AddInventoryCheckItemRequest request) {
        //if(request)
        return inventoryCheckItemRepository.add(request);
    }

    public Boolean edit(AddInventoryCheckItemRequest request) {
        return inventoryCheckItemRepository.edit(request);
    }

    public GetInventoryCheckItemResponse get(String checkNumber) {
        return inventoryCheckItemRepository.get(checkNumber);
    }

    public PagingResponse<GetInventoryCheckItemResponse> getList(Integer pageNo, Integer pageSize) {
        return inventoryCheckItemRepository.getList(pageNo, pageSize);
    }

    public Boolean delete(List<String> ids, String updateBy) {
        return inventoryCheckItemRepository.delete(ids, updateBy);
    }
}
