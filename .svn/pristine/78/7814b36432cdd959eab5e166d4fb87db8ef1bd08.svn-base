package com.siping.intranet.crm.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.inventory.repository.InventoryCheckRepository;
import com.siping.smartone.inventory.request.AddInventoryCheckRequest;
import com.siping.smartone.inventory.request.AddInventoryCheckRequestNew;
import com.siping.smartone.inventory.request.GetInventoryCheckListRequest;
import com.siping.smartone.inventory.response.GetMaterialBatchNumberResponse;
import com.siping.smartone.inventory.response.GetInventoryCheckResponse;
import com.siping.smartone.inventory.response.GetInventoryCheckResponseNew;

@Service
public class InventoryCheckService {
    @Autowired
    private InventoryCheckRepository inventoryCheckRepository;

    public Boolean add(AddInventoryCheckRequest request) {
        //if(request)
        return inventoryCheckRepository.add(request);
    }

    public Boolean edit(AddInventoryCheckRequestNew request) {
        return inventoryCheckRepository.edit(request);
    }

    public GetInventoryCheckResponseNew get(String checkNumber) {
        return inventoryCheckRepository.get(checkNumber);
    }

    public PagingResponse<GetInventoryCheckResponseNew> getList(Integer pageNo, Integer pageSize, GetInventoryCheckListRequest request) {
        if(null==pageNo||0==pageNo){
            pageNo = 1;
        }
        if(null==pageSize||0==pageSize){
            pageSize = 10;
        }
        return inventoryCheckRepository.getList(pageNo, pageSize, request);
    }

    public Boolean delete(List<String> ids, String updateBy) {
        return inventoryCheckRepository.delete(ids, updateBy);
    }
    
    public List<GetMaterialBatchNumberResponse> getMaterialBatchNumberList(String materialId,String storageId){
        return inventoryCheckRepository.getMaterialBatchNumberList(materialId, storageId);
    }
    
    public List<GetInventoryCheckResponse> getInventorycheckByStorageIdWithDrft(String storageId){
        return inventoryCheckRepository.getInventorycheckByStorageIdWithDrft(storageId).getRecords();
    }

    public Boolean addNew(AddInventoryCheckRequestNew request) {
        return inventoryCheckRepository.addNew(request);
    }

    public Boolean addPdaInventoryCheck(AddInventoryCheckRequestNew request) {
        return inventoryCheckRepository.addPdaInventoryCheck(request);
    }
}
