package com.siping.intranet.report.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.report.request.ProductPurchaseRequest;
import com.siping.smartone.report.request.PurchaseAnalysisRequest;
import com.siping.smartone.report.request.PurchaseOrderNumRequest;
import com.siping.smartone.report.request.SupplierPurchaseRequest;
import com.siping.smartone.report.response.ProductPurchaseResponse;
import com.siping.smartone.report.response.PurchaseAnalysisResponse;
import com.siping.smartone.report.response.PurchaseOrderNumResponse;
import com.siping.smartone.report.response.SupplierPurchaseResponse;

@Repository
public class PurchaseReportRepository {

    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    @SuppressWarnings("unchecked")
    public PagingResponse<SupplierPurchaseResponse> getSupplierPurchaseData(SupplierPurchaseRequest request) {
        String requestContent = JSONBinder.binder(SupplierPurchaseRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(SupplierPurchaseResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/report/rest/supplierpurchase/getdata").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<ProductPurchaseResponse> getProductPurchaseData(ProductPurchaseRequest request) {
        String requestContent = JSONBinder.binder(ProductPurchaseRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(ProductPurchaseResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/report/rest/productpurchase/getdata").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<PurchaseAnalysisResponse> getPurchaseAnalysisData(PurchaseAnalysisRequest request) {
        String requestContent = JSONBinder.binder(PurchaseAnalysisRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(PurchaseAnalysisResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/report/rest/purchaseanalysis/getdata").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<PurchaseOrderNumResponse> getPurchaseOrderNumData(PurchaseOrderNumRequest request) {
        String requestContent = JSONBinder.binder(PurchaseOrderNumRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(PurchaseOrderNumResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/report/rest/purchaseordernum/getdata").body(requestContent));
    }
    
}
