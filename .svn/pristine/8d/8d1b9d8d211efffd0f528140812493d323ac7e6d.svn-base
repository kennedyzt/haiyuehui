package com.siping.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.smartone.common.OperationLog;
import com.siping.system.repository.OperationLogRestRepository;

@Service
public class OperationLogRestService extends DBSwitch {
    @Autowired
    private OperationLogRestRepository operationLogRestRepository;

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<OperationLog> getList(OperationLog log) {
        return operationLogRestRepository.getList(log);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(OperationLog log) {
        return operationLogRestRepository.delete(log);
    }
}
