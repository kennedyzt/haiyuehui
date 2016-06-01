package com.siping.wms.readyreceipt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.wms.request.GenerateReceiptParam;
import com.siping.smartone.wms.response.ReadyReceiptResponse;
import com.siping.wms.readyreceipt.repository.ReadyReceiptOrderRepository;

@Service
public class ReadyReceiptOrderService {

    @Autowired
    ReadyReceiptOrderRepository readyReceiptOrderRepository;

    public ReadyReceiptResponse get(String orderNumber) {
        return readyReceiptOrderRepository.get(orderNumber);
    }

    public PagingResponse<ReadyReceiptResponse> getList(CommonBillsRequest request) {
        return readyReceiptOrderRepository.getList(request);
    }

    public Boolean generateReceipt(GenerateReceiptParam request) {
        return readyReceiptOrderRepository.generateReceipt(request);
    }

}
