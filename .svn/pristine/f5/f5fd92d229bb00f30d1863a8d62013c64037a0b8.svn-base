package com.siping.intranet.report.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.report.request.CustomerSaleRequest;
import com.siping.smartone.report.request.GetSalesRrequest;
import com.siping.smartone.report.response.CustomerSaleResponse;
import com.siping.smartone.report.response.GetSalesReportWithExtraResponse;
import com.siping.smartone.report.response.GetSalesResponse;

@Repository
public class SellReportRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public GetSalesResponse getSellOrderCount(GetSalesRrequest request) {
        String requestContent = JSONBinder.binder(GetSalesRrequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(GetSalesResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellreport/getsellordercount").body(requestContent));
    }

    public GetSalesResponse getSaleroom(GetSalesRrequest request) {
        String requestContent = JSONBinder.binder(GetSalesRrequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(GetSalesResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellreport/getsaleroom").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<CustomerSaleResponse> getCustomerSaleData(CustomerSaleRequest request) {
        String requestContent = JSONBinder.binder(CustomerSaleRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(CustomerSaleResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/report/rest/customersale/getdata").body(requestContent));
    }

    public GetSalesReportWithExtraResponse getSaleroomWithExtra(GetSalesRrequest request) {
        String requestContent = JSONBinder.binder(GetSalesRrequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(GetSalesReportWithExtraResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/sellreport/getsaleroomwithextra").body(requestContent));
    }
}
