package com.siping.service.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.report.repository.PurchaseReportRestRepository;
import com.siping.smartone.report.request.ProductPurchaseRequest;
import com.siping.smartone.report.request.PurchaseAnalysisRequest;
import com.siping.smartone.report.request.PurchaseOrderNumRequest;
import com.siping.smartone.report.request.SupplierPurchaseRequest;
import com.siping.smartone.report.response.ProductPurchaseResponse;
import com.siping.smartone.report.response.PurchaseAnalysisResponse;
import com.siping.smartone.report.response.PurchaseOrderNumResponse;
import com.siping.smartone.report.response.SupplierPurchaseResponse;

@Service
public class PurchaseReportRestService extends DBSwitch {

    @Autowired
    private PurchaseReportRestRepository purchaseReportRestRepository;

    public PagingResponse<SupplierPurchaseResponse> getSupplierPurchaseData(SupplierPurchaseRequest request) {
        return purchaseReportRestRepository.getSupplierPurchaseData(request);
    }

    public PagingResponse<ProductPurchaseResponse> getProductPurchaseData(ProductPurchaseRequest request) {
        return purchaseReportRestRepository.getProductPurchaseData(request);
    }

    public PagingResponse<PurchaseAnalysisResponse> getPurchaseAnalysisData(PurchaseAnalysisRequest request) {
        return purchaseReportRestRepository.getPurchaseAnalysisData(request);
    }

    public PagingResponse<PurchaseOrderNumResponse> getPurchaseOrderNumData(PurchaseOrderNumRequest request) {
        return purchaseReportRestRepository.getPurchaseOrderNumData(request);
    }

}
