package com.siping.wms.report.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.report.CommonReportRequest;
import com.siping.smartone.report.response.QueryGoodsTransportResponse;

@Repository
public class QueryGoodsTransportRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    @SuppressWarnings("unchecked")
    public PagingResponse<QueryGoodsTransportResponse> getList(CommonReportRequest request) {
        String requestContent = JSONBinder.binder(CommonReportRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(QueryGoodsTransportResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/report/querygoodstransport/getlist").body(requestContent));
    }

}
