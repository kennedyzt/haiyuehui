package com.siping.service.material.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.material.repository.MaterialGroupRelationRestRepository;
import com.siping.smartone.material.request.AddMaterialGroupRelationRequest;
import com.siping.smartone.material.request.GetMaterialConditionRequest;
import com.siping.smartone.material.request.GetMaterialRequest;
import com.siping.smartone.material.response.GetMaterialGroupResponse;
import com.siping.smartone.material.response.GetMaterialResponse;

@Service
public class MaterialGroupRelationRestService extends DBSwitch {
    @Autowired
    private MaterialGroupRelationRestRepository materialGroupRelationRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(AddMaterialGroupRelationRequest request) {
        return materialGroupRelationRestRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(AddMaterialGroupRelationRequest request) {
        return materialGroupRelationRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<GetMaterialGroupResponse> get(GetMaterialRequest request) {
        return materialGroupRelationRestRepository.get(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetMaterialResponse> getList(GetMaterialConditionRequest request) {
        return materialGroupRelationRestRepository.getList(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(String id) {
        return materialGroupRelationRestRepository.delete(id);
    }
}
