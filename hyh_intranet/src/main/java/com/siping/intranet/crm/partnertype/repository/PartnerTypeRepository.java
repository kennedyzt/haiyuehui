package com.siping.intranet.crm.partnertype.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.partnertype.request.PartnerTypeRequest;
import com.siping.smartone.partnertype.response.PartnerTypeResponse;

@Repository
public class PartnerTypeRepository {
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean addPartnerType(PartnerTypeRequest request) {
        if (request.getTypeName() == null) {
            throw new BusinessProcessException("类型名不能为空");
        }
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/partnertype/add").body(requestContent));
    }

    public Boolean editPartnerTypeById(PartnerTypeRequest request) {
        if (request.getTypeName() == null) {
            throw new BusinessProcessException("类型名不能为空");
        }
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/partnertype/edit").body(requestContent));
    }

    public Boolean deletePartnerTypeById(Integer id, Integer loginId) {
        if (null == id || null == loginId) {
            throw new BusinessProcessException("条件不足不能删除业务伙伴");
        }
        return stromaWebServiceApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/partnertype/delete/%s/%s").arguments(id, loginId));
    }

    @SuppressWarnings("unchecked")
    public List<PartnerTypeResponse> getAllPartnerType() {
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(PartnerTypeResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/partnertype/getAll"));
    }

}
