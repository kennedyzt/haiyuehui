package com.siping.intranet.invoicing.sell.shipments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.common.CommonBillsForm;
import com.siping.intranet.invoicing.sell.shipments.repository.SellShipmentsRepository;
import com.siping.smartone.invoicing.sell.shipments.request.AddSellShipmentsRequest;
import com.siping.smartone.invoicing.sell.shipments.request.BatchAddSellShipmentsRequest;
import com.siping.smartone.invoicing.sell.shipments.response.GetSellShipmentsListResponse;
import com.siping.smartone.invoicing.sell.shipments.response.GetSellShipmentsResponse;

@Service
public class SellShipmentsService {
    @Autowired
    private SellShipmentsRepository sellShipmentsRepository;

    public Boolean add(AddSellShipmentsRequest request) {
        return sellShipmentsRepository.add(request);
    }

    public Boolean edit(AddSellShipmentsRequest request) {
        return sellShipmentsRepository.edit(request);
    }

    public GetSellShipmentsResponse get(String shipmentsnumber) {
        return sellShipmentsRepository.get(shipmentsnumber);
    }

    public PagingResponse<GetSellShipmentsListResponse> getList(Integer pageNo, Integer pageSize, CommonBillsForm form) {
        return sellShipmentsRepository.getList(pageNo, pageSize, form);
    }

    public Boolean delete(final List<String> shipmentsNumberList, Integer userId) {
        return sellShipmentsRepository.delete(shipmentsNumberList, userId);
    }

    public Boolean adds(BatchAddSellShipmentsRequest request) {
        return sellShipmentsRepository.adds(request);
    }
}
