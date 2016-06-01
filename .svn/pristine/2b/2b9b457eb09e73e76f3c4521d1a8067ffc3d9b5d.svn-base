package com.siping.wms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;
import com.siping.smartone.wms.request.AddInventoryAllocateRequest;
import com.siping.smartone.wms.request.GetInventoryAllocateListRequest;
import com.siping.smartone.wms.response.GetInventoryAllocateResponse;
import com.siping.wms.repository.InventoryAllocateRestRepository;

@Service
public class InventoryAllocateRestService extends DBSwitch {

    @Autowired
    private InventoryAllocateRestRepository inventoryAllocateRestRepository;

    public PagingResponse<GetInventoryAllocateResponse> getList(GetInventoryAllocateListRequest request) {
        return inventoryAllocateRestRepository.getList(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GetInventoryAllocateResponse get(String receiptNumber) {
        return inventoryAllocateRestRepository.get(receiptNumber);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(AddInventoryAllocateRequest request) {
        return inventoryAllocateRestRepository.add(request);
    }

}
