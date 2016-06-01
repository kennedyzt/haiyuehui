package com.siping.intranet.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.report.repository.SellReportRepository;
import com.siping.smartone.report.request.CustomerSaleRequest;
import com.siping.smartone.report.request.GetSalesRrequest;
import com.siping.smartone.report.response.CustomerSaleResponse;
import com.siping.smartone.report.response.GetSalesReportWithExtraResponse;
import com.siping.smartone.report.response.GetSalesResponse;

@Service
public class SellReportService {
    @Autowired
    private SellReportRepository sellReportRepository;

    public GetSalesResponse getSellOrderCount(GetSalesRrequest request) {
        return sellReportRepository.getSellOrderCount(request);
    }

    public GetSalesResponse getSaleroom(GetSalesRrequest request) {
        return sellReportRepository.getSaleroom(request);
    }

    public PagingResponse<CustomerSaleResponse> getCustomerSaleData(CustomerSaleRequest request) {
        return sellReportRepository.getCustomerSaleData(request);
    }

    public GetSalesReportWithExtraResponse getSaleroomWithExtra(GetSalesRrequest request) {
        // TODO Auto-generated method stub
        return sellReportRepository.getSaleroomWithExtra(request);
    }
}
