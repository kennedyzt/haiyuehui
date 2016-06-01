package com.siping.service.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.report.repository.SellReportRestRepository;
import com.siping.smartone.report.request.CustomerSaleRequest;
import com.siping.smartone.report.request.GetSalesRrequest;
import com.siping.smartone.report.response.CustomerSaleResponse;
import com.siping.smartone.report.response.GetSalesReportWithExtraResponse;
import com.siping.smartone.report.response.GetSalesResponse;

@Service
public class SellReportRestService extends DBSwitch {
    @Autowired
    private SellReportRestRepository sellReportRestRepository;

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GetSalesResponse getSellOrderCount(GetSalesRrequest request) {
        return sellReportRestRepository.getSellOrderCount(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GetSalesResponse getSaleroom(GetSalesRrequest request) {
        return sellReportRestRepository.getSaleroom(request);
    }

    public PagingResponse<CustomerSaleResponse> getCustomerSaleData(CustomerSaleRequest request) {
        return sellReportRestRepository.getCustomerSaleData(request);
    }

    public GetSalesReportWithExtraResponse getSaleroomWithExtra(GetSalesRrequest request) {
        return sellReportRestRepository.getSaleroomWithExtra(request);
    }

}
