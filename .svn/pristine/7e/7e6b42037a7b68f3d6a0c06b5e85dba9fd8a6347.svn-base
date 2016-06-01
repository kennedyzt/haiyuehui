package com.siping.intranet.report.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.inventory.request.ProductExpirationRequest;
import com.siping.smartone.inventory.response.ProductExpirationResponse;

@Repository
public class ProductReportRepository {

    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    @SuppressWarnings("unchecked")
    public PagingResponse<ProductExpirationResponse> getExpListForSupplier(ProductExpirationRequest request) {
        String requestContent = JSONBinder.binder(ProductExpirationRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(ProductExpirationResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/material/rest/expiration/getlist").body(requestContent));
    }
}
