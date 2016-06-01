package com.siping.wms.readyreceipt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.wms.request.OrderPickQueryParam;
import com.siping.smartone.wms.response.OrderPickResponse;
import com.siping.wms.readyreceipt.repository.OrderPickRepository;

@Service
public class OrderPickService {
    @Autowired
    private OrderPickRepository orderPickRepository;

    public Boolean add(OrderPickQueryParam request) {
        return orderPickRepository.add(request);
    }

    public PagingResponse<OrderPickResponse> getList(CommonBillsRequest request) {
        return orderPickRepository.getList(request);
    }

    public OrderPickResponse get(String orderNumber) {
        return orderPickRepository.get(orderNumber);
    }

    public Boolean updateStatus(String orderNumber, Integer loginId, Integer status) {
        return orderPickRepository.updateStatus(orderNumber, loginId, status);
    }
}
