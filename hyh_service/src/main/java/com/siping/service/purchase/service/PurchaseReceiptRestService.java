package com.siping.service.purchase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.purchase.repository.PurchaseReceiptRestRepository;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.common.request.GetGenerateBillsNumber;
import com.siping.smartone.purchase.request.DeletePurchaseReceiptRequest;
import com.siping.smartone.purchase.request.PurchaseReceiptRequest;
import com.siping.smartone.purchase.response.PurchaseReceiptResponse;

@Service
public class PurchaseReceiptRestService extends DBSwitch {
    @Autowired
    private PurchaseReceiptRestRepository purchaseReceiptRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ResultMsg add(PurchaseReceiptRequest request) {
        return purchaseReceiptRestRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(DeletePurchaseReceiptRequest request) {
        return purchaseReceiptRestRepository.delete(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(PurchaseReceiptRequest request) {
        return purchaseReceiptRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PurchaseReceiptResponse get(String orderNumber) {
        return purchaseReceiptRestRepository.get(orderNumber);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<PurchaseReceiptResponse> getList(CommonBillsRequest request) {
        return purchaseReceiptRestRepository.getList(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public String getGenerateBillsNumber(GetGenerateBillsNumber request) {
        return purchaseReceiptRestRepository.getGenerateBillsNumber(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ResultMsg updatePOStatus(String fromBillsNo, Integer userId) {
        return purchaseReceiptRestRepository.updatePOStatus(fromBillsNo, userId);
    }
}
