package com.siping.intranet.crm.material.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.material.repository.MaterialGroupRelationRepository;
import com.siping.smartone.material.request.AddMaterialGroupRelationRequest;
import com.siping.smartone.material.request.GetMaterialConditionRequest;
import com.siping.smartone.material.response.GetMaterialGroupResponse;
import com.siping.smartone.material.response.GetMaterialResponse;

@Service
public class MaterialGroupRelationService {
    @Autowired
    private MaterialGroupRelationRepository materialGroupRelationRepository;

    public Boolean add(AddMaterialGroupRelationRequest request) {
        return materialGroupRelationRepository.add(request);
    }

    public Boolean edit(AddMaterialGroupRelationRequest request) {
        return materialGroupRelationRepository.edit(request);
    }

    public List<GetMaterialGroupResponse> get(String id, String materialno) {
        return materialGroupRelationRepository.get(id, materialno);
    }

    public PagingResponse<GetMaterialResponse> getList(Integer pageNo, Integer pageSize, GetMaterialConditionRequest request) {
        return materialGroupRelationRepository.getList(pageNo, pageSize, request);
    }

    public Boolean delete(String id) {
        return materialGroupRelationRepository.delete(id);
    }
}
