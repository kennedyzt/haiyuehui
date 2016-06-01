package com.siping.service.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.report.repository.MaterialSaleReportRestRepository;
import com.siping.smartone.report.request.MaterialSaleReportRequest;
import com.siping.smartone.report.request.MaterialWarningRequest;
import com.siping.smartone.report.request.SaleAnalysisReportRequest;
import com.siping.smartone.report.response.MaterialSaleReportResponse;
import com.siping.smartone.report.response.MaterialWarningResponse;
import com.siping.smartone.report.response.SaleAnalysisReportResponse;

@Service
public class MaterialSaleReportRestService extends DBSwitch {
    @Autowired
    private MaterialSaleReportRestRepository materialSaleReportRestRepository;

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<MaterialSaleReportResponse> getList(MaterialSaleReportRequest request) {
        return materialSaleReportRestRepository.getList(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<SaleAnalysisReportResponse> getAnalysisList(SaleAnalysisReportRequest request) {
        return materialSaleReportRestRepository.getAnalysisList(request);
    }

    public PagingResponse<MaterialWarningResponse> getMaterialWaringList(MaterialWarningRequest request) {
        return materialSaleReportRestRepository.getMaterialWaringList(request);
    }

}
