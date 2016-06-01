package com.siping.service.sell.shipments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.sell.shipments.repository.SellShipmentsRestRepository;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.invoicing.sell.shipments.request.AddSellShipmentsRequest;
import com.siping.smartone.invoicing.sell.shipments.request.BatchAddSellShipmentsRequest;
import com.siping.smartone.invoicing.sell.shipments.request.DeleteSellShipmentsRequest;
import com.siping.smartone.invoicing.sell.shipments.response.GetSellShipmentsListResponse;
import com.siping.smartone.invoicing.sell.shipments.response.GetSellShipmentsResponse;

@Service
public class SellShipmentsRestService extends DBSwitch {
    @Autowired
    private SellShipmentsRestRepository sellShipmentsRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(AddSellShipmentsRequest request) {
        return sellShipmentsRestRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(AddSellShipmentsRequest request) {
        return sellShipmentsRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GetSellShipmentsResponse get(String shipmentsNumber) {
        return sellShipmentsRestRepository.get(shipmentsNumber);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetSellShipmentsListResponse> getList(CommonBillsRequest request) {
        return sellShipmentsRestRepository.getList(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(DeleteSellShipmentsRequest request) {
        return sellShipmentsRestRepository.delete(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean adds(BatchAddSellShipmentsRequest request) {
        return sellShipmentsRestRepository.adds(request);
    }

}
