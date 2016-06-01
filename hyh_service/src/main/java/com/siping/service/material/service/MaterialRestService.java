package com.siping.service.material.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.material.repository.MaterialRestRepository;
import com.siping.smartone.inventory.response.GetInventoryCheckWithMaterialAndLocationInfo;
import com.siping.smartone.inventory.response.GetInventoryCheckWithMaterialAndLocationInfoRequest;
import com.siping.smartone.material.request.AddMaterialRequest;
import com.siping.smartone.material.request.DeleteMaterialRequest;
import com.siping.smartone.material.request.GetMaterialBatchRequest;
import com.siping.smartone.material.request.GetMaterialListByStorageRequest;
import com.siping.smartone.material.request.GetMaterialListRequest;
import com.siping.smartone.material.request.GetMaterialRequest;
import com.siping.smartone.material.request.GetOpeanWinMaterialRequest;
import com.siping.smartone.material.request.MaterialExportParamRequest;
import com.siping.smartone.material.response.GetMaterialBatchResponse;
import com.siping.smartone.material.response.GetMaterialResponse;

@Service
public class MaterialRestService extends DBSwitch {
    @Autowired
    private MaterialRestRepository materialRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(AddMaterialRequest request) {
        return materialRestRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(AddMaterialRequest request) {
        return materialRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GetMaterialResponse get(GetMaterialRequest request) {
        return materialRestRepository.get(request.getId(), request.getMaterialNo(), request.getBarcode());
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GetMaterialResponse get(String id) {
        return materialRestRepository.get(id, null, null);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetMaterialResponse> getList(GetMaterialListRequest request) {
        return materialRestRepository.getList(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetMaterialResponse> getListForSupplier(GetMaterialListRequest request) {
        return materialRestRepository.getListForSupplier(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(DeleteMaterialRequest request) {
        return materialRestRepository.delete(request);
    }

    public List<GetMaterialResponse> export(MaterialExportParamRequest request) {
        return materialRestRepository.export(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<GetMaterialResponse> getList(GetMaterialRequest request) {
        return materialRestRepository.getList(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<GetMaterialResponse> getList(GetMaterialListByStorageRequest request) {
        return materialRestRepository.getList(request);
    }

    public PagingResponse<GetMaterialResponse> getOpenWinList(GetOpeanWinMaterialRequest request) {
        return materialRestRepository.getOpenWinList(request);
    }

    public GetMaterialBatchResponse getMaterialBatch(GetMaterialBatchRequest request) {
        return materialRestRepository.getMaterialBatch(request);
    }

    public List<GetInventoryCheckWithMaterialAndLocationInfo> getInventoryCheckInfoWithStorageIdWithStorageArea(GetInventoryCheckWithMaterialAndLocationInfoRequest request) {
        return materialRestRepository.assemInventoryCheckInfoWithStorageIdWithStorageArea(request);
    }
    
   
}
