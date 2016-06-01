package com.siping.intranet.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.report.repository.PurchaseReportRepository;
import com.siping.smartone.report.request.ProductPurchaseRequest;
import com.siping.smartone.report.request.PurchaseAnalysisRequest;
import com.siping.smartone.report.request.PurchaseOrderNumRequest;
import com.siping.smartone.report.request.SupplierPurchaseRequest;
import com.siping.smartone.report.response.ProductPurchaseResponse;
import com.siping.smartone.report.response.PurchaseAnalysisResponse;
import com.siping.smartone.report.response.PurchaseOrderNumResponse;
import com.siping.smartone.report.response.SupplierPurchaseResponse;

@Service
public class PurchaseReportService {

    @Autowired
    private PurchaseReportRepository purchaseReportRepository;

    public PagingResponse<SupplierPurchaseResponse> getSupplierPurchaseData(SupplierPurchaseRequest request) {
        return purchaseReportRepository.getSupplierPurchaseData(request);
    }

    public PagingResponse<ProductPurchaseResponse> getProductPurchaseData(ProductPurchaseRequest request) {
        return purchaseReportRepository.getProductPurchaseData(request);
    }

    public PagingResponse<PurchaseAnalysisResponse> getPurchaseAnalysisData(PurchaseAnalysisRequest request) {
        return purchaseReportRepository.getPurchaseAnalysisData(request);
    }

    public PagingResponse<PurchaseOrderNumResponse> getPurchaseOrderNumData(PurchaseOrderNumRequest request) {
        return purchaseReportRepository.getPurchaseOrderNumData(request);
    }
}
