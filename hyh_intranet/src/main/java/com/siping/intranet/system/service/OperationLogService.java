package com.siping.intranet.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.system.repository.OperationLogRepository;
import com.siping.smartone.common.OperationLog;

@Service
public class OperationLogService {
    @Autowired
    private OperationLogRepository operationLogRepository;

    public PagingResponse<OperationLog> getList(Integer pageNo, Integer pageSize, OperationLog log) {
        return operationLogRepository.getList(pageNo, pageSize, log);
    }

    public Boolean delete(OperationLog log) {
        return operationLogRepository.delete(log);
    }
}
