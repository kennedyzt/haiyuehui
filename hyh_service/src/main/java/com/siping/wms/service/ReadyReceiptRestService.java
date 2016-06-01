package com.siping.wms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.wms.request.GenerateReceiptParam;
import com.siping.smartone.wms.response.ReadyReceiptResponse;
import com.siping.wms.repository.ReadyReceiptRestRepository;

@Service
public class ReadyReceiptRestService extends DBSwitch {
    @Autowired
    ReadyReceiptRestRepository readyReceiptRestRepository;

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public ReadyReceiptResponse get(String orderNubmer) {
        return readyReceiptRestRepository.get(orderNubmer);
    }
    
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<ReadyReceiptResponse> getList(CommonBillsRequest request) {
        return readyReceiptRestRepository.getList(request);
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean generateReceipt(GenerateReceiptParam request) {
        return readyReceiptRestRepository.generateReceipt(request);
    }
}
