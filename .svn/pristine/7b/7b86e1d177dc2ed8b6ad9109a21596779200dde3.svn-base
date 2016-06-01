package com.siping.intranet.crm.material.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.material.repository.MaterialTypeRepository;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.material.request.AddMaterialTypeRequest;
import com.siping.smartone.material.request.GetMaterialTypeListRequest;
import com.siping.smartone.material.response.GetMaterialTypeResponse;

@Service
public class MaterialTypeService {
    @Autowired
    private MaterialTypeRepository materialTypeRepository;

    public Boolean add(AddMaterialTypeRequest request) {
        return materialTypeRepository.add(request);
    }

    public Boolean edit(AddMaterialTypeRequest request) {
        return materialTypeRepository.edit(request);
    }

    public GetMaterialTypeResponse get(Integer id, String typeName) {
        return materialTypeRepository.get(id, typeName);
    }

    public List<GetMaterialTypeResponse> getList() {
        return materialTypeRepository.getList();
    }
    
    public List<GetMaterialTypeResponse> getList(String id) {
        return materialTypeRepository.getList(id);
    }

//    public Boolean delete(String id, String updateBy) {
//        return materialTypeRepository.delete(id, updateBy);
//    }

    public List<GetMaterialTypeResponse> getChild(String parentId) {
        return materialTypeRepository.getChild(parentId);
    }

    public PagingResponse<GetMaterialTypeResponse> getAllMaterialType(Integer pageNo, Integer pageSize, GetMaterialTypeListRequest request) {
        // TODO Auto-generated method stub
        return materialTypeRepository.getAllMaterialType(pageNo, pageSize, request);
    }
    
    public PagingResponse<GetMaterialTypeResponse> getAllMaterialType(Integer pageNo, Integer pageSize, CommonBillsRequest request) {
        // TODO Auto-generated method stub
        return materialTypeRepository.getAllMaterialType(pageNo, pageSize, request);
    }

    public boolean delete(List<Integer> ids, Integer updateBy) {
        return materialTypeRepository.delete(ids, updateBy);
    }

    public PagingResponse<GetMaterialTypeResponse> getListByParentId(GetMaterialTypeListRequest request) {
        return materialTypeRepository.getListByParentId(request);
    }
}
