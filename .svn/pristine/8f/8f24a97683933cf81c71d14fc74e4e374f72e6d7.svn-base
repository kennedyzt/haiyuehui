package com.siping.intranet.crm.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.storage.repository.StorageLocationRepository;
import com.siping.smartone.storage.request.AddStorageAreaRequest;
import com.siping.smartone.storage.request.AddStorageLocationRequest;
import com.siping.smartone.storage.request.GetStorageLocationListRequest;
import com.siping.smartone.storage.response.StorageLocationResponese;

@Service
public class StorageLocationService {
    @Autowired
    private StorageLocationRepository storageLocationRepository;
    
    public Boolean add(AddStorageLocationRequest request){
        return storageLocationRepository.add(request);
    }

    public PagingResponse<StorageLocationResponese> getList(Integer pageNo,Integer pageSize,String storageId, GetStorageLocationListRequest request) {
        return storageLocationRepository.getList(pageNo,pageSize,storageId, request);
    }

    public StorageLocationResponese get(String id) {
        return storageLocationRepository.get(id);
    }

    public Boolean edit(AddStorageLocationRequest request) {
        return storageLocationRepository.edit(request);
    }
    
    public Boolean delete(List<String> ids,String updateBy){
            return storageLocationRepository.delete(ids, updateBy);
    }
}
