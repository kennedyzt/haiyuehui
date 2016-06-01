package com.siping.intranet.crm.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.storage.repository.StorageRepository;
import com.siping.smartone.storage.request.GetStorageListRequest;
import com.siping.smartone.storage.request.StorageRequest;
import com.siping.smartone.storage.response.StorageResponse;

@Service
public class StorageService {
    @Autowired
    private StorageRepository storageRepository;

    public Boolean add(StorageRequest request) {
        return storageRepository.add(request);
    }

    public Boolean edit(StorageRequest request) {
        return storageRepository.edit(request);
    }

    public StorageResponse get(String id) {
        return storageRepository.get(id);
    }

    public Boolean delete(List<String> ids, String updateBy) {
        return storageRepository.delete(ids, updateBy);
    }

    public List<StorageResponse> getList() {
        return storageRepository.getList();
    }

    public PagingResponse<StorageResponse> getList(Integer pageNo, Integer pageSize, GetStorageListRequest request) {
        if (null == pageNo || 0 == pageNo) {
            pageNo = 1;
        }
        if (null == pageSize || 0 == pageSize) {
            pageSize = 10;
        }
        return storageRepository.getList(pageNo, pageSize, request);
    }

    public List<StorageResponse> getList(String key) {
        return storageRepository.getList(key);
    }

    public List<StorageResponse> getList(Boolean isEnableLocation) {
        return storageRepository.getList(isEnableLocation);
    }

}
