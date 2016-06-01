package com.siping.service.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.storage.repository.StorageRestRepository;
import com.siping.smartone.storage.request.DeleteStorageRequest;
import com.siping.smartone.storage.request.GetStorageListRequest;
import com.siping.smartone.storage.request.StorageRequest;
import com.siping.smartone.storage.response.StorageResponse;

@Service
public class StorageRestService extends DBSwitch {
    @Autowired
    private StorageRestRepository storageRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addStorage(StorageRequest request) {
        return storageRestRepository.addStorage(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(StorageRequest request) {
        return storageRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public StorageResponse get(String id) {
        return storageRestRepository.get(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(DeleteStorageRequest request) {
        return storageRestRepository.delete(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<StorageResponse> getList() {
        return storageRestRepository.getList();
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<StorageResponse> getList(GetStorageListRequest request) {
        return storageRestRepository.getList(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<StorageResponse> getList(String key) {
        return storageRestRepository.getList(key);
    }

    public List<StorageResponse> getList(Boolean isEnableLocation) {
        return storageRestRepository.getList(isEnableLocation);
    }

}
