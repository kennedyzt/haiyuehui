package com.siping.intranet.report.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.report.CommonReportRequest;
import com.siping.smartone.report.request.InWarehouseRequest;
import com.siping.smartone.report.request.OutwarehouseRequest;
import com.siping.smartone.report.response.BusinessSalesReportResponse;
import com.siping.smartone.report.response.InWarehouseResponse;
import com.siping.smartone.report.response.OutwarehouseResponse;

@Repository
public class BusinessSalesReportRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    @SuppressWarnings("unchecked")
    public PagingResponse<BusinessSalesReportResponse> getList(CommonReportRequest request) {
        String requestContent = JSONBinder.binder(CommonReportRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(BusinessSalesReportResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/report/businesssalesreport/getlist").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<InWarehouseResponse> getInwarehouse(InWarehouseRequest request) {
        String requestContent = JSONBinder.binder(InWarehouseRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(InWarehouseResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/report/rest/inwarehouse/getdata").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<OutwarehouseResponse> getOutwarehouse(OutwarehouseRequest request) {
        String requestContent = JSONBinder.binder(OutwarehouseRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(OutwarehouseResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/report/rest/outwarehouse/getdata").body(requestContent));
    }

}
