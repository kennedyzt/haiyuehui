package com.siping.intranet.crm.countryregion.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;
import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.businesspartner.request.CountryRegionRequest;
import com.siping.smartone.businesspartner.response.CountryRegionResponse;
import com.siping.smartone.group.request.GroupRequest;

@Repository
public class CountryRegionRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(CountryRegionRequest request) {
        String requestContent = JSONBinder.binder(GroupRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/countryregion/add").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<CountryRegionResponse> getList() {
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(CountryRegionResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/countryregion/getlist"));
    }

    public CountryRegionResponse get(Integer id) {
        return stromaWebServiceApi.get(EndPointBuilder.create(CountryRegionResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/countryregion/get/%s").arguments(id));
    }

    public Boolean delete(Integer id, Integer loginId) {
        return stromaWebServiceApi.get(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/countryregion/delete/%s/%s").arguments(id, loginId));
    }

}
