package com.siping.service.sell.returns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.sell.returns.repository.SellReturnsRestRepository;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.invoicing.sell.returns.request.AddSellReturnsRequest;
import com.siping.smartone.invoicing.sell.returns.request.DeleteSellReturnsRequest;
import com.siping.smartone.invoicing.sell.returns.response.GetSellReturnsListResponse;
import com.siping.smartone.invoicing.sell.returns.response.GetSellReturnsResponse;

@Service
public class SellReturnsRestService extends DBSwitch {
    @Autowired
    private SellReturnsRestRepository sellReturnsRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(AddSellReturnsRequest request) {
        return sellReturnsRestRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(AddSellReturnsRequest request) {
        return sellReturnsRestRepository.edit(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public GetSellReturnsResponse get(String returnsNumber) {
        return sellReturnsRestRepository.get(returnsNumber);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetSellReturnsListResponse> getList(CommonBillsRequest request) {
        return sellReturnsRestRepository.getList(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(DeleteSellReturnsRequest request) {
        return sellReturnsRestRepository.delete(request);
    }
}
