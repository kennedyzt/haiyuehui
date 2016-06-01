package com.siping.service.sell.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.sell.order.repository.SellOrderRestRepository;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.invoicing.sell.order.request.AddSellOrderRequest;
import com.siping.smartone.invoicing.sell.order.request.DeleteSellOrderRequest;
import com.siping.smartone.invoicing.sell.order.response.GetSellOrderListResponse;
import com.siping.smartone.invoicing.sell.order.response.GetSellOrderResponse;

@Service
public class SellOrderRestService extends DBSwitch {
    @Autowired
    private SellOrderRestRepository sellOrderRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(AddSellOrderRequest request) {
        return sellOrderRestRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(AddSellOrderRequest request) {
        return sellOrderRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GetSellOrderResponse get(String orderNumber) {
        return sellOrderRestRepository.get(orderNumber);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetSellOrderListResponse> getList(CommonBillsRequest request) {
        return sellOrderRestRepository.getList(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(DeleteSellOrderRequest request) {
        return sellOrderRestRepository.delete(request);
    }

}
