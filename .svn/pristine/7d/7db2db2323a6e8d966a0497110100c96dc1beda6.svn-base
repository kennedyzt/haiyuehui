package com.siping.intranet.crm.purchase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.purchase.repository.PurchaseReceiptRepository;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.purchase.request.PurchaseReceiptRequest;
import com.siping.smartone.purchase.response.PurchaseReceiptResponse;

@Service
public class PurchaseReceiptService {
    @Autowired
    private PurchaseReceiptRepository purchaseReceiptRepository;

    public ResultMsg add(PurchaseReceiptRequest request) {
        return purchaseReceiptRepository.add(request);
    }

    public Boolean edit(PurchaseReceiptRequest request) {
        return purchaseReceiptRepository.edit(request);
    }

    public PurchaseReceiptResponse get(String orderNumber) {
        return purchaseReceiptRepository.get(orderNumber);
    }

    public boolean delete(List<String> orderNumbers, Integer loginId) {
        return purchaseReceiptRepository.delete(orderNumbers, loginId);
    }

    public PagingResponse<PurchaseReceiptResponse> getList(CommonBillsRequest request) {
        return purchaseReceiptRepository.getList(request);
    }
}
