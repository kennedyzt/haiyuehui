package com.siping.intranet.crm.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.common.CommonBillsForm;
import com.siping.intranet.crm.inventory.repository.InventoryShipmentsRepository;
import com.siping.smartone.inventory.request.AddInventoryShipmentsRequest;
import com.siping.smartone.inventory.response.GetInventoryShipmentsListResponse;
import com.siping.smartone.inventory.response.GetInventoryShipmentsResponse;

@Service
public class InventoryShipmentsService {
    @Autowired
    private InventoryShipmentsRepository inventoryShipmentsRepository;

    public Boolean add(AddInventoryShipmentsRequest request) {
        return inventoryShipmentsRepository.add(request);
    }

    public Boolean edit(AddInventoryShipmentsRequest request) {
        return inventoryShipmentsRepository.edit(request);
    }

    public GetInventoryShipmentsResponse get(String shipmentsNumber) {
        return inventoryShipmentsRepository.get(shipmentsNumber);
    }

    public PagingResponse<GetInventoryShipmentsListResponse> getList(Integer pageNo, Integer pageSize, CommonBillsForm form) {
        return inventoryShipmentsRepository.getList(pageNo, pageSize, form);
    }

    public Boolean delete(final List<String> shipmentsNumberList, Integer userId) {
        return inventoryShipmentsRepository.delete(shipmentsNumberList, userId);
    }
}
