package com.siping.service.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.inventory.repository.InventoryCheckRestRepository;
import com.siping.service.material.repository.MaterialRestRepository;
import com.siping.smartone.inventory.request.AddInventoryCheckRequestNew;
import com.siping.smartone.inventory.request.DeleteInventoryCheckRequest;
import com.siping.smartone.inventory.request.GetInventoryCheckListRequest;
import com.siping.smartone.inventory.request.GetMaterialBatchNumberListRequest;
import com.siping.smartone.inventory.request.GetInventoryCheckRequest;
import com.siping.smartone.inventory.response.GetMaterialBatchNumberResponse;
import com.siping.smartone.inventory.response.GetInventoryCheckResponseNew;

@Service
public class InventoryCheckRestService extends DBSwitch {
    @Autowired
    private InventoryCheckRestRepository inventoryCheckRestRepository;
    @Autowired
    private MaterialRestRepository materialRestRepository;

//    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//    public Boolean add(AddInventoryCheckRequest request) {
//        return inventoryCheckRestRepository.add(request);
//    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(AddInventoryCheckRequestNew request) {
        return inventoryCheckRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GetInventoryCheckResponseNew get(GetInventoryCheckRequest request) {
        return inventoryCheckRestRepository.get(request.getCheckNumber());
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetInventoryCheckResponseNew> getList(GetInventoryCheckListRequest request) {
        return inventoryCheckRestRepository.getList(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(DeleteInventoryCheckRequest request) {
        return inventoryCheckRestRepository.delete(request);
    }
    
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<GetMaterialBatchNumberResponse> getMaterialBatchNumberList(GetMaterialBatchNumberListRequest request){
        request.setMaterialResponse(materialRestRepository.get(request.getMaterialId(),null, null));
        return inventoryCheckRestRepository.getMaterialBatchNumberList(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addNew(AddInventoryCheckRequestNew request) {
        return inventoryCheckRestRepository.addNew(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addPdaInventoryCheck(AddInventoryCheckRequestNew request) {
        return inventoryCheckRestRepository.addPdaInventoryCheck(request);
    }
}
