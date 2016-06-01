package com.siping.service.material.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.material.repository.MaterialTypeRestRepository;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.material.request.AddMaterialTypeRequest;
import com.siping.smartone.material.request.DeleteMaterialTypeRequest;
import com.siping.smartone.material.request.GetMaterialTypeListRequest;
import com.siping.smartone.material.request.GetMaterialTypeRequest;
import com.siping.smartone.material.response.GetMaterialTypeResponse;

@Service
public class MaterialTypeRestService extends DBSwitch {
    @Autowired
    private MaterialTypeRestRepository materialTypeRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(AddMaterialTypeRequest request) {
        return materialTypeRestRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(AddMaterialTypeRequest request) {
        return materialTypeRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GetMaterialTypeResponse get(GetMaterialTypeRequest request) {
        return materialTypeRestRepository.get(request.getId(), request.getTypeName());
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<GetMaterialTypeResponse> getList() {
        return materialTypeRestRepository.getList();
    }

//    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//    public Boolean delete(String id, String updateBy) {
//        return materialTypeRestRepository.delete(id, updateBy);
//    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(DeleteMaterialTypeRequest request) {
        return materialTypeRestRepository.delete(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<GetMaterialTypeResponse> getChild(String parentId) {
        return materialTypeRestRepository.getChild(parentId);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public String getAllTypeChilds(String parentId) {
        return materialTypeRestRepository.getAllTypeChilds(parentId);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetMaterialTypeResponse> getAllMaterialType(GetMaterialTypeListRequest request) {
        // TODO Auto-generated method stub
        return materialTypeRestRepository.getAllMaterialType(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetMaterialTypeResponse> getAllMaterialType(CommonBillsRequest request) {
        // 
        return materialTypeRestRepository.getAllMaterialType(request);
    }


    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<GetMaterialTypeResponse> getList(String id) {
        return materialTypeRestRepository.getList(id);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetMaterialTypeResponse> getListByParentId(GetMaterialTypeListRequest request) {
        return materialTypeRestRepository.getListByParentId(request);
    }
}
