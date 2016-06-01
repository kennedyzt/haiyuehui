package com.siping.service.purchase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.purchase.repository.PurchaseApplyOrderRestRepository;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.purchase.request.DeletePurchaseApplyOrderRequest;
import com.siping.smartone.purchase.request.PurchaseApplyOrderRequest;
import com.siping.smartone.purchase.response.PurchaseApplyOrderResponse;

@Service
public class PurchaseApplyOrderRestService extends DBSwitch {
    @Autowired
    private PurchaseApplyOrderRestRepository purchaseApplyOrderRestRepoitory;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ResultMsg add(PurchaseApplyOrderRequest purchaseApplyOrder) {
        return purchaseApplyOrderRestRepoitory.add(purchaseApplyOrder);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(DeletePurchaseApplyOrderRequest request) {
        return purchaseApplyOrderRestRepoitory.delete(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(PurchaseApplyOrderRequest request) {
        return purchaseApplyOrderRestRepoitory.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PurchaseApplyOrderResponse get(String orderNubmer) {
        return purchaseApplyOrderRestRepoitory.get(orderNubmer);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<PurchaseApplyOrderResponse> getAllApplyPO(CommonBillsRequest request) {
        return purchaseApplyOrderRestRepoitory.getAllApplyPO(request);
    }
}
