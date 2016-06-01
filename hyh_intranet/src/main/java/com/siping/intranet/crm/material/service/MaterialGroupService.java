package com.siping.intranet.crm.material.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siping.intranet.crm.material.repository.MaterialGroupRepository;
import com.siping.smartone.material.request.AddMaterialGroupRequest;
import com.siping.smartone.material.response.GetMaterialGroupResponse;

@Service
public class MaterialGroupService {
    @Autowired
    private MaterialGroupRepository materialGroupRepository;

    public Boolean add(AddMaterialGroupRequest request) {
        return materialGroupRepository.add(request);
    }

    public Boolean edit(AddMaterialGroupRequest request) {
        return materialGroupRepository.edit(request);
    }

    public GetMaterialGroupResponse get(String id, String groupName) {
        return materialGroupRepository.get(id, groupName);
    }

    public List<GetMaterialGroupResponse> getList() {
        return materialGroupRepository.getList();
    }

    public Boolean delete(String id, String updateBy) {
        return materialGroupRepository.delete(id, updateBy);
    }
}
