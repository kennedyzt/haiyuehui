package com.siping.intranet.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.report.repository.BusinessSalesReportRepository;
import com.siping.smartone.report.CommonReportRequest;
import com.siping.smartone.report.request.InWarehouseRequest;
import com.siping.smartone.report.request.OutwarehouseRequest;
import com.siping.smartone.report.response.BusinessSalesReportResponse;
import com.siping.smartone.report.response.InWarehouseResponse;
import com.siping.smartone.report.response.OutwarehouseResponse;

@Service
public class BusinessSalesReportService {
    @Autowired
    private BusinessSalesReportRepository businessSalesReportRepository;

    public PagingResponse<BusinessSalesReportResponse> getList(CommonReportRequest request) {
        return businessSalesReportRepository.getList(request);
    }

    public PagingResponse<InWarehouseResponse> getInwarehouse(InWarehouseRequest request) {
        return businessSalesReportRepository.getInwarehouse(request);
    }

    public PagingResponse<OutwarehouseResponse> getOutwarehouse(OutwarehouseRequest request) {
        return businessSalesReportRepository.getOutwarehouse(request);
    }
}
