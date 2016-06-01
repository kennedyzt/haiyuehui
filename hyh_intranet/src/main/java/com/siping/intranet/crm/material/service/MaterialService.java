package com.siping.intranet.crm.material.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.material.repository.MaterialRepository;
import com.siping.smartone.inventory.response.GetInventoryCheckWithMaterialAndLocationInfo;
import com.siping.smartone.inventory.response.GetInventoryCheckWithMaterialAndLocationInfoRequest;
import com.siping.smartone.material.request.AddMaterialRequest;
import com.siping.smartone.material.request.GetMaterialListRequest;
import com.siping.smartone.material.request.GetOpeanWinMaterialRequest;
import com.siping.smartone.material.request.MaterialExportParamRequest;
import com.siping.smartone.material.response.GetMaterialBatchResponse;
import com.siping.smartone.material.response.GetMaterialResponse;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    public Boolean add(AddMaterialRequest request) {
        // if(request)
        return materialRepository.add(request);
    }

    public Boolean edit(AddMaterialRequest request) {
        return materialRepository.edit(request);
    }

    public GetMaterialResponse get(String id, String materialNo, String barcode, String description) {
        return materialRepository.get(id, materialNo, barcode, description);
    }

    public PagingResponse<GetMaterialResponse> getList(Integer pageNo, Integer pageSize, GetMaterialListRequest request) {
        return materialRepository.getList(pageNo, pageSize, request);
    }

    public PagingResponse<GetMaterialResponse> getListForSupplier(Integer pageNo, Integer pageSize, GetMaterialListRequest request) {
        return materialRepository.getListForSupplier(pageNo, pageSize, request);
    }

    public Boolean delete(List<String> ids, String updateBy) {
        return materialRepository.delete(ids, updateBy);
    }

    public List<GetMaterialResponse> getAllMaterials(MaterialExportParamRequest param) {
        return materialRepository.getAllMaterials(param);
    }

    public List<GetMaterialResponse> getList(String keyword,Boolean isPurchase,Boolean isSell,Boolean isInventory) {
        return materialRepository.getList(keyword,isPurchase,isSell,isInventory);
    }

    public List<GetMaterialResponse> getList(String keyword, String storageid) {
        return materialRepository.getList(keyword, storageid);
    }

    public PagingResponse<GetMaterialResponse> getOpenWinList(int pageNo, int pageSize, GetOpeanWinMaterialRequest request) {
        return materialRepository.getOpenWinList(pageNo,pageSize,request);
    }
    
    public GetMaterialBatchResponse getMaterialBatch(String materialId,String batchNumber){
        return materialRepository.getMaterialBatch(materialId,batchNumber);
    }

    public List<GetInventoryCheckWithMaterialAndLocationInfo> getInventoryCheckInfoWithStorageIdWithStorageArea(GetInventoryCheckWithMaterialAndLocationInfoRequest request) {
        return materialRepository.getInventoryCheckInfoWithStorageIdWithStorageArea(request);
    }
}
