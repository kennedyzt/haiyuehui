package com.siping.intranet.crm.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siping.intranet.crm.storage.repository.StorageAreaRepository;
import com.siping.smartone.storage.request.AddStorageAreaRequest;
import com.siping.smartone.storage.request.AddStorageLocationRequest;
import com.siping.smartone.storage.response.StorageAreaResponese;
import com.siping.smartone.storage.response.StorageLocationResponese;

@Service
public class StorageAreaService {
    @Autowired
    private StorageAreaRepository storageAreaRepository;
    
    public Boolean add(AddStorageAreaRequest request) {
        return storageAreaRepository.add(request);
    }
    
    public List<StorageAreaResponese> getList(String storageId) {
        return storageAreaRepository.getList(storageId);
    }
    
    public StorageAreaResponese get(String id){
        return storageAreaRepository.get(id);
    }
    
    public Boolean edit(AddStorageAreaRequest request){
        return storageAreaRepository.edit(request);
    }
    
    public Boolean delete(List<String> ids,String updateBy){
        return storageAreaRepository.delete(ids, updateBy);
}
}
