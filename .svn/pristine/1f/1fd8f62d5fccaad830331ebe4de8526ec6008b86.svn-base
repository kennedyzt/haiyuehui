package com.siping.intranet.report.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.report.request.MaterialSaleReportRequest;
import com.siping.smartone.report.request.MaterialWarningRequest;
import com.siping.smartone.report.request.SaleAnalysisReportRequest;
import com.siping.smartone.report.response.MaterialSaleReportResponse;
import com.siping.smartone.report.response.MaterialWarningResponse;
import com.siping.smartone.report.response.SaleAnalysisReportResponse;

@Repository
public class MaterialSaleReportRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    @SuppressWarnings("unchecked")
    public PagingResponse<MaterialSaleReportResponse> getList(MaterialSaleReportRequest request) {
        String requestContent = JSONBinder.binder(MaterialSaleReportRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(MaterialSaleReportResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/report/materialsalereport/getlist").body(requestContent));//this is a test
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<SaleAnalysisReportResponse> getsaleAnalysisList(SaleAnalysisReportRequest request) {
        String requestContent = JSONBinder.binder(SaleAnalysisReportRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(SaleAnalysisReportResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/report/saleanalysisreport/getlist").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<MaterialWarningResponse> getMaterialWarningList(MaterialWarningRequest request) {
        String requestContent = JSONBinder.binder(MaterialWarningRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(MaterialWarningResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/report/materialwarningreport/getlist").body(requestContent));
    }

}
