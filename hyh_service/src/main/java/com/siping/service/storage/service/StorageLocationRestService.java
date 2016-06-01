package com.siping.service.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.storage.repository.StorageLocationRestRepository;
import com.siping.smartone.storage.request.AddStorageLocationRequest;
import com.siping.smartone.storage.request.DeleteStorageRequest;
import com.siping.smartone.storage.request.GetStorageListRequest;
import com.siping.smartone.storage.request.GetStorageLocationListRequest;
import com.siping.smartone.storage.request.StorageRequest;
import com.siping.smartone.storage.response.StorageLocationResponese;
import com.siping.smartone.storage.response.StorageResponse;
import com.siping.smartone.wms.response.StorageLocationResponse;

@Service
public class StorageLocationRestService extends DBSwitch {
    @Autowired
    private StorageLocationRestRepository storageLocationRestRepository;


    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addStorageLocation(AddStorageLocationRequest request) {
        return storageLocationRestRepository.addStorageLocation(request);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean editStorageLocation(AddStorageLocationRequest request) {
        return storageLocationRestRepository.editStorageLocation(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public StorageLocationResponese get(String id) {
        return storageLocationRestRepository.get(id);
    }
    
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public StorageLocationResponese getByLocationNo(String locationNo) {
        return storageLocationRestRepository.getByLocationNo(locationNo);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(DeleteStorageRequest request) {
        return storageLocationRestRepository.delete(request);
    }
    

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<StorageLocationResponese> getList(GetStorageLocationListRequest request) {
        return storageLocationRestRepository.getList(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<StorageLocationResponse> getLocationListByStorageId(String storageId) {
        return storageLocationRestRepository.getLocationListByStorageId(storageId);
    }
}
