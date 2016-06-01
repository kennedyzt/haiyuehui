package com.siping.intranet.crm.purchase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.purchase.repository.PurchaseOrderRepository;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.purchase.request.PurchaseOrderRequest;
import com.siping.smartone.purchase.response.PurchaseOrderResponse;

@Service
public class PurchaseOrderService {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    public ResultMsg add(PurchaseOrderRequest purchaseOrder) {
        return purchaseOrderRepository.add(purchaseOrder);
    }

    public boolean delete(List<String> orderNumbers, Integer updateBy) {
        return purchaseOrderRepository.delete(orderNumbers, updateBy);
    }

    public Boolean edit(PurchaseOrderRequest request) {
        return purchaseOrderRepository.edit(request);
    }
    
    public PurchaseOrderResponse get(String orderNumber) {
        return purchaseOrderRepository.get(orderNumber);
    }

    public PagingResponse<PurchaseOrderResponse> getList(CommonBillsRequest request) {
        return purchaseOrderRepository.getList(request);
    }

}
