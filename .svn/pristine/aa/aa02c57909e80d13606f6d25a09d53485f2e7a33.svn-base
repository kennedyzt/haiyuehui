package com.siping.service.purchase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.purchase.repository.PurchaseOrderRestRepository;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.purchase.request.DeletePurchaseOrderRequest;
import com.siping.smartone.purchase.request.PurchaseOrderRequest;
import com.siping.smartone.purchase.response.PurchaseOrderResponse;

@Service
public class PurchaseOrderRestService extends DBSwitch {
    @Autowired
    PurchaseOrderRestRepository purchaseOrderRestRepoitory;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ResultMsg add(PurchaseOrderRequest purchaseOrder) {
        return purchaseOrderRestRepoitory.add(purchaseOrder);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(DeletePurchaseOrderRequest request) {
        return purchaseOrderRestRepoitory.delete(request);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(PurchaseOrderRequest request) {
        return purchaseOrderRestRepoitory.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PurchaseOrderResponse get(String orderNubmer) {
        return purchaseOrderRestRepoitory.get(orderNubmer);
    }
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<PurchaseOrderResponse> getList(CommonBillsRequest request) {
        return purchaseOrderRestRepoitory.getList(request);
    }
}
