package com.siping.intranet.crm.purchase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.purchase.repository.PurchaseReturnsRepository;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.purchase.request.PurchaseReturnOrderForm;
import com.siping.smartone.purchase.request.PurchaseReturnsRequest;
import com.siping.smartone.purchase.response.PurchaseReturnsResponse;

@Service
public class PurchaseReturnsService {
    @Autowired
    private PurchaseReturnsRepository purchaseReturnsRepository;

    public ResultMsg add(PurchaseReturnsRequest request) {
        return purchaseReturnsRepository.add(request);

    }

    public Boolean edit(PurchaseReturnsRequest request) {
        return purchaseReturnsRepository.edit(request);
    }

    public Boolean delete(List<String> receiptNumbers, String userId) {
        return purchaseReturnsRepository.delete(receiptNumbers, userId);
    }

    public PagingResponse<PurchaseReturnsResponse> getList(Integer pageNo, Integer pageSize, PurchaseReturnOrderForm request) {
        return purchaseReturnsRepository.getList(pageNo, pageSize, request);
    }

    public PurchaseReturnsResponse get(String returnsNumber) {
        return purchaseReturnsRepository.get(returnsNumber);
    }

}
