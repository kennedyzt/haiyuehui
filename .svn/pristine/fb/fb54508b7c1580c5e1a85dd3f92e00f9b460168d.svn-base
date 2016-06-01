package com.siping.wms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.wms.request.OrderPickQueryParam;
import com.siping.smartone.wms.response.OrderPickResponse;
import com.siping.wms.repository.OrderPickRestRepository;

@Service
public class OrderPickRestService extends DBSwitch {
    @Autowired
    private OrderPickRestRepository orderPickRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(OrderPickQueryParam request) {
        return orderPickRestRepository.add(request);
    }
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<OrderPickResponse> getList(CommonBillsRequest request) {
        return orderPickRestRepository.getList(request);
    }
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public OrderPickResponse get(String ordernumber) {
        return orderPickRestRepository.get(ordernumber);
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean updateStatus(String orderNumber, Integer loginId, Integer status) {
        return orderPickRestRepository.updateStatus(orderNumber,loginId,status);
    }

}
