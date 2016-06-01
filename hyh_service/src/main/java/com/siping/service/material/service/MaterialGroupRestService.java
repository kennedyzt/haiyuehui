package com.siping.service.material.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;

import com.siping.service.material.repository.MaterialGroupRestRepository;
import com.siping.smartone.material.request.AddMaterialGroupRequest;
import com.siping.smartone.material.request.GetMaterialUsageRequest;
import com.siping.smartone.material.response.GetMaterialGroupResponse;

@Service
public class MaterialGroupRestService extends DBSwitch {
    @Autowired
    private MaterialGroupRestRepository materialGroupRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(AddMaterialGroupRequest request) {
        return materialGroupRestRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(AddMaterialGroupRequest request) {
        return materialGroupRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GetMaterialGroupResponse get(GetMaterialUsageRequest request) {
        return materialGroupRestRepository.get(request.getId(), request.getUsageName());
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<GetMaterialGroupResponse> getList() {
        return materialGroupRestRepository.getList();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(String id, String updateBy) {
        return materialGroupRestRepository.delete(id, updateBy);
    }
}
