package com.siping.intranet.crm.material.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.material.repository.MaterialUnitRepository;
import com.siping.smartone.material.request.AddMaterialUnitRequest;
import com.siping.smartone.material.request.GetMaterialUnitListRequest;
import com.siping.smartone.material.response.GetMaterialResponse;
import com.siping.smartone.material.response.GetMaterialUnitResponse;

@Service
public class MaterialUnitService {
    @Autowired
    private MaterialUnitRepository materialUnitRepository;

    public Boolean add(AddMaterialUnitRequest request) {
        return materialUnitRepository.add(request);
    }

    public Boolean edit(AddMaterialUnitRequest request) {
        return materialUnitRepository.edit(request);
    }

    public GetMaterialUnitResponse get(String id, String unitName) {
        return materialUnitRepository.get(id, unitName);
    }
    
    public Boolean delete(List<String> ids, String updateBy) {
        return materialUnitRepository.delete(ids, updateBy);
    }

    public PagingResponse<GetMaterialUnitResponse> getList(Integer pageNo, Integer pageSize, GetMaterialUnitListRequest request) {
        return  materialUnitRepository.getList(pageNo,pageSize,request);
    }
    /*
     * 得到所有计量单位的方法，不采用分页
     * */
    public List<GetMaterialUnitResponse> getList() {
        return  materialUnitRepository.getList();
    }

    public List<GetMaterialUnitResponse> getListWithCondition(String keyword) {
        return materialUnitRepository.getListWithCondition(keyword);
    }
}
