package com.siping.service.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.report.repository.BusinessSalesReportRestRepository;
import com.siping.smartone.report.CommonReportRequest;
import com.siping.smartone.report.request.InWarehouseRequest;
import com.siping.smartone.report.request.OutwarehouseRequest;
import com.siping.smartone.report.response.BusinessSalesReportResponse;
import com.siping.smartone.report.response.InWarehouseResponse;
import com.siping.smartone.report.response.OutwarehouseResponse;

@Service
public class BusinessSalesReportRestService extends DBSwitch {
    @Autowired
    private BusinessSalesReportRestRepository businessSalesReportRestRepository;

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<BusinessSalesReportResponse> getList(CommonReportRequest request) {
        return businessSalesReportRestRepository.getList(request);
    }

    public PagingResponse<InWarehouseResponse> getInwarehouse(InWarehouseRequest request) {
        return businessSalesReportRestRepository.getInwarehouse(request);
    }

    public PagingResponse<OutwarehouseResponse> getOutwarehouse(OutwarehouseRequest request) {
        return businessSalesReportRestRepository.getOutwarehouse(request);
    }

}
