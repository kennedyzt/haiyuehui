package com.siping.service.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.storage.repository.StorageAreaRestRepository;
import com.siping.service.storage.repository.StorageLocationRestRepository;
import com.siping.smartone.storage.request.AddStorageAreaRequest;
import com.siping.smartone.storage.request.AddStorageLocationRequest;
import com.siping.smartone.storage.request.DeleteStorageRequest;
import com.siping.smartone.storage.request.GetStorageListRequest;
import com.siping.smartone.storage.request.StorageRequest;
import com.siping.smartone.storage.response.StorageAreaResponese;
import com.siping.smartone.storage.response.StorageLocationResponese;
import com.siping.smartone.storage.response.StorageResponse;
import com.siping.smartone.wms.response.StorageLocationResponse;

@Service
public class StorageAreaRestService extends DBSwitch {
    @Autowired
    private StorageAreaRestRepository storageAreaRestRepository;


    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addStorageArea(AddStorageAreaRequest request) {
        return storageAreaRestRepository.addStorageArea(request);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean editStorageArea(AddStorageAreaRequest request) {
        return storageAreaRestRepository.editStorageArea(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public StorageAreaResponese get(String id) {
        return storageAreaRestRepository.get(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(DeleteStorageRequest request) {
        return storageAreaRestRepository.delete(request);
    }
    

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<StorageAreaResponese> getList(String key) {
        return storageAreaRestRepository.getList(key);
    }
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<StorageLocationResponse> getLocationListByAreaId(String storageAreaId){
        return storageAreaRestRepository.getLocationListByAreaId(storageAreaId);
    }
}
