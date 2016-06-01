package com.siping.service.purchase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.purchase.repository.PurchaseReturnsRestRepository;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.inventory.request.InventoryReceiptBatchDeleteRequest;
import com.siping.smartone.purchase.request.PurchaseReturnsBatchDeleteRequest;
import com.siping.smartone.purchase.request.PurchaseReturnsOrderQueryParam;
import com.siping.smartone.purchase.request.PurchaseReturnsRequest;
import com.siping.smartone.purchase.response.PurchaseReturnsResponse;

@Service
public class PurchaseReturnsRestService extends DBSwitch {
    @Autowired
    private PurchaseReturnsRestRepository purchaseReturnsRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ResultMsg add(PurchaseReturnsRequest request) {
        return purchaseReturnsRestRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(PurchaseReturnsBatchDeleteRequest request) {
        return purchaseReturnsRestRepository.delete(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(PurchaseReturnsRequest request) {
        return purchaseReturnsRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<PurchaseReturnsResponse> getList(PurchaseReturnsOrderQueryParam request) {
        return purchaseReturnsRestRepository.getList(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PurchaseReturnsResponse get(String returnsNumber) {
        return purchaseReturnsRestRepository.get(returnsNumber);
    }

}
