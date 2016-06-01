package com.siping.intranet.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.report.repository.MaterialSaleReportRepository;
import com.siping.smartone.report.request.MaterialSaleReportRequest;
import com.siping.smartone.report.request.MaterialWarningRequest;
import com.siping.smartone.report.request.SaleAnalysisReportRequest;
import com.siping.smartone.report.response.MaterialSaleReportResponse;
import com.siping.smartone.report.response.MaterialWarningResponse;
import com.siping.smartone.report.response.SaleAnalysisReportResponse;

@Service
public class MaterialSaleReportService {
    @Autowired
    private MaterialSaleReportRepository materialSaleReportRepository;

    public PagingResponse<MaterialSaleReportResponse> getList(MaterialSaleReportRequest request) {
        return materialSaleReportRepository.getList(request);
    }

    public PagingResponse<SaleAnalysisReportResponse> getsaleAnalysisList(SaleAnalysisReportRequest request) {
        return materialSaleReportRepository.getsaleAnalysisList(request);
    }

    public PagingResponse<MaterialWarningResponse> getMaterialWarningList(MaterialWarningRequest request) {
        return materialSaleReportRepository.getMaterialWarningList(request);
    }
}
