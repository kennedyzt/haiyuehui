package com.siping.intranet.crm.businesspartner.repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.businesspartner.request.BusinessPartnerBatchAddErrorRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerBatchAddRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerBatchAddSuccessRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerBatchDeleteRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerExportParamRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerRequest;
import com.siping.smartone.businesspartner.request.GetBusinessPartnerListForm;
import com.siping.smartone.businesspartner.response.BusinessPartnerQueryParam;
import com.siping.smartone.businesspartner.response.BusinessPartnerResponse;
import com.siping.smartone.businesspartner.response.UploadBusinessPartnerResponse;

@Repository
public class BusinessPartnerRepository {
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean addBusinessPartner(BusinessPartnerRequest request) {
        if (!StringUtils.hasText(request.getPartnerCode())) {
            throw new BusinessProcessException("业务伙伴编码不能为空");
        }
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/businesspartner/add").body(requestContent));
    }

    public Boolean editBusinessPartnerById(BusinessPartnerRequest request) {
        if (request.getId() == null) {
            throw new BusinessProcessException("缺失主键不能修改");
        }
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/businesspartner/edit").body(requestContent));
    }

    public Boolean deleteBusinessPartnerById(List<Integer> ids, Integer loginId) {
        if (null == ids || null == loginId) {
            throw new BusinessProcessException("缺失主键不能删除业务伙伴");
        }
        BusinessPartnerBatchDeleteRequest request = new BusinessPartnerBatchDeleteRequest();
        request.setLoginId(loginId);
        request.setIds(ids);
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/businesspartner/delete").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<BusinessPartnerResponse> getAllBusinessPartner(Integer pageNo, Integer pageSize, Integer partnerType, GetBusinessPartnerListForm request) {
        if (null == pageNo && null == pageSize && null == partnerType) {
            throw new BusinessProcessException("条件不足不能查询");
        }
        BusinessPartnerQueryParam businessPartnerQueryParam = new BusinessPartnerQueryParam();
        businessPartnerQueryParam.setPageNo(pageNo);
        businessPartnerQueryParam.setPageSize(pageSize);
        businessPartnerQueryParam.setPartnerType(partnerType);
        businessPartnerQueryParam.setKeywords(request.getKeywords());
        businessPartnerQueryParam.setPartnerGroup(request.getPartnerGroup());
        businessPartnerQueryParam.setIsEnable(request.getIsEnable());
        String requestContent = jsonConverter.toString(businessPartnerQueryParam);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/businesspartner/getlist").body(requestContent));
    }

    public UploadBusinessPartnerResponse batchAdd(InputStream inputStream, Integer loginId) {
        List<BusinessPartnerBatchAddSuccessRequest> successBusinessPartners = new ArrayList<BusinessPartnerBatchAddSuccessRequest>(); // 保存成功信息
        List<BusinessPartnerBatchAddErrorRequest> errorBusinessPartners = new ArrayList<BusinessPartnerBatchAddErrorRequest>(); // 保存失败信息
        BusinessParnerRepositoryHelper.load(inputStream, successBusinessPartners, errorBusinessPartners);
        if (errorBusinessPartners.isEmpty() && successBusinessPartners.isEmpty())
            throw new BusinessProcessException("上传文件中至少包含一个物料信息");
        BusinessPartnerBatchAddRequest request = new BusinessPartnerBatchAddRequest();
        request.setBusinessPartners(successBusinessPartners);
        request.setErrors(errorBusinessPartners);
        request.setCreatedBy(loginId);
        String requestContent = JSONBinder.binder(BusinessPartnerBatchAddRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(UploadBusinessPartnerResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/businesspartner/batchadd").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartnerResponse> get(BusinessPartnerExportParamRequest businessPartnerExportParamRequest) {
        String requestContent = jsonConverter.toString(businessPartnerExportParamRequest);
        return stromaWebServiceApi.post(EndPointBuilder.create(List.class).elementTypes(BusinessPartnerResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/businesspartner/export")
            .body(requestContent));
    }

    public BusinessPartnerResponse get(Integer id) {
        if (null == id) {
            throw new BusinessProcessException("缺失主键不能查询");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(BusinessPartnerResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/businesspartner/get/%s").arguments(id));
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartnerResponse> getList(String key, Integer type) {
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(BusinessPartnerResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/businesspartner/getlistbykey/%s/%s").arguments(key, type));
    }
}
