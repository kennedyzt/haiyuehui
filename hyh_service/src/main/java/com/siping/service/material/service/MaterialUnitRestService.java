package com.siping.service.material.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.material.repository.MaterialUnitRestRepository;
import com.siping.smartone.material.request.AddMaterialUnitRequest;
import com.siping.smartone.material.request.DeleteMaterialUnitRequest;
import com.siping.smartone.material.request.GetMaterialUnitListRequest;
import com.siping.smartone.material.request.GetMaterialUnitRequest;
import com.siping.smartone.material.response.GetMaterialUnitResponse;

@Service
public class MaterialUnitRestService extends DBSwitch {
    @Autowired
    private MaterialUnitRestRepository materialUnitRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(AddMaterialUnitRequest request) {
        return materialUnitRestRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(AddMaterialUnitRequest request) {
        return materialUnitRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GetMaterialUnitResponse get(GetMaterialUnitRequest request) {
        return materialUnitRestRepository.get(request.getId(), request.getUnitName());
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetMaterialUnitResponse> getList(GetMaterialUnitListRequest request) {
        return materialUnitRestRepository.getList(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<GetMaterialUnitResponse> getList() {
        return materialUnitRestRepository.getList();
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(DeleteMaterialUnitRequest request) {
        return materialUnitRestRepository.delete(request);
    }
    
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<GetMaterialUnitResponse> getListWithCondition(GetMaterialUnitListRequest request) {
        return materialUnitRestRepository.getListWithCondition(request);
    }
    
}
