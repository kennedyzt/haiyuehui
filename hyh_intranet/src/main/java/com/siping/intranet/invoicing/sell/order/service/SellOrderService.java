package com.siping.intranet.invoicing.sell.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.common.CommonBillsForm;
import com.siping.intranet.invoicing.sell.order.repository.SellOrderRepository;
import com.siping.smartone.invoicing.sell.order.request.AddSellOrderRequest;
import com.siping.smartone.invoicing.sell.order.response.GetSellOrderListResponse;
import com.siping.smartone.invoicing.sell.order.response.GetSellOrderResponse;

@Service
public class SellOrderService {
    @Autowired
    private SellOrderRepository sellOrderRepository;

    public Boolean add(AddSellOrderRequest request) {
        return sellOrderRepository.add(request);
    }

    public Boolean edit(AddSellOrderRequest request) {
        return sellOrderRepository.edit(request);
    }

    public GetSellOrderResponse get(String orderNumber) {
        return sellOrderRepository.get(orderNumber);
    }

    public PagingResponse<GetSellOrderListResponse> getList(Integer pageNo, Integer pageSize, CommonBillsForm form) {
        return sellOrderRepository.getList(pageNo, pageSize, form);
    }

    public Boolean delete(final List<String> orderNumberList, Integer userId) {
        return sellOrderRepository.delete(orderNumberList, userId);
    }
}
