package com.siping.intranet.crm.businesspartner.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.businesspartner.response.ShopResponse;
import com.siping.smartone.common.request.CommonBillsRequest;

@Repository
public class ShopRepository {
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Value("${site.api.appkeys}")
    private String appKey;
    
    @SuppressWarnings("unchecked")
    public PagingResponse<ShopResponse> getList(Integer pageNo, Integer pageSize, CommonBillsRequest request) {
        if (null == pageNo && null == pageSize) {
            throw new BusinessProcessException("条件不足不能查询");
        }
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(ShopResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/shop/getlist").body(requestContent));
    }
}
