package com.siping.intranet.crm.purchase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.purchase.repository.PurchaseApplyOrderRepository;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.purchase.request.PurchaseApplyOrderRequest;
import com.siping.smartone.purchase.response.PurchaseApplyOrderResponse;

@Service
public class PurchaseApplyOrderService {
    @Autowired
    private PurchaseApplyOrderRepository purchaseApplyOrderRepository;

    public ResultMsg add(PurchaseApplyOrderRequest purchaseApplyOrder) {
        return purchaseApplyOrderRepository.add(purchaseApplyOrder);
    }

    public Boolean delete(List<String> orderNumbers, Integer updateBy) {
        return purchaseApplyOrderRepository.delete(orderNumbers, updateBy);
    }

    public PurchaseApplyOrderResponse get(String orderNumber) {
        return purchaseApplyOrderRepository.get(orderNumber);
    }

    public Boolean edit(PurchaseApplyOrderRequest request) {
        return purchaseApplyOrderRepository.edit(request);
    }

    public PagingResponse<PurchaseApplyOrderResponse> getAllApplyPO(CommonBillsRequest request) {
        return purchaseApplyOrderRepository.getAllApplyPO(request);
    }
}
