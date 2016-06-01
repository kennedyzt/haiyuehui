package com.siping.service.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.inventory.repository.InventoryShipmentsRestRepository;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.inventory.request.AddInventoryShipmentsRequest;
import com.siping.smartone.inventory.request.DeleteInventoryShipmentsRequest;
import com.siping.smartone.inventory.response.GetInventoryShipmentsListResponse;
import com.siping.smartone.inventory.response.GetInventoryShipmentsResponse;

@Service
public class InventoryShipmentsRestService extends DBSwitch {
    @Autowired
    private InventoryShipmentsRestRepository inventoryShipmentsRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(AddInventoryShipmentsRequest request) {
        return inventoryShipmentsRestRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(AddInventoryShipmentsRequest request) {
        return inventoryShipmentsRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GetInventoryShipmentsResponse get(String shipmentsNumber) {
        return inventoryShipmentsRestRepository.get(shipmentsNumber);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetInventoryShipmentsListResponse> getList(CommonBillsRequest request) {
        return inventoryShipmentsRestRepository.getList(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(DeleteInventoryShipmentsRequest request) {
        return inventoryShipmentsRestRepository.delete(request);
    }

}
