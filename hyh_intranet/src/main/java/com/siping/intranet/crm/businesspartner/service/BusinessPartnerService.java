package com.siping.intranet.crm.businesspartner.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.businesspartner.repository.BusinessPartnerRepository;
import com.siping.smartone.businesspartner.request.BusinessPartnerExportParamRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerRequest;
import com.siping.smartone.businesspartner.request.GetBusinessPartnerListForm;
import com.siping.smartone.businesspartner.response.BusinessPartnerResponse;
import com.siping.smartone.businesspartner.response.UploadBusinessPartnerResponse;

@Service
public class BusinessPartnerService {
    @Autowired
    BusinessPartnerRepository businessPartnerRepository;

    public Boolean addBusinessPartner(BusinessPartnerRequest request) {
        return businessPartnerRepository.addBusinessPartner(request);
    }

    public Boolean editBusinessPartnerById(BusinessPartnerRequest request) {
        return businessPartnerRepository.editBusinessPartnerById(request);

    }

    public Boolean deleteBusinessPartnerById(List<Integer> id, Integer loginId) {
        return businessPartnerRepository.deleteBusinessPartnerById(id, loginId);
    }

    public PagingResponse<BusinessPartnerResponse> getAllBusinessPartner(Integer pageNo, Integer pageSize, Integer partnerType, GetBusinessPartnerListForm request) {
        return businessPartnerRepository.getAllBusinessPartner(pageNo, pageSize,partnerType,request);
    }

    public UploadBusinessPartnerResponse batchAdd(InputStream inputStream, Integer loginId) {
        return businessPartnerRepository.batchAdd(inputStream, loginId);
    }

    public List<BusinessPartnerResponse> get(BusinessPartnerExportParamRequest businessPartnerExportParamRequest) {
        return businessPartnerRepository.get(businessPartnerExportParamRequest);
    }

    public BusinessPartnerResponse get(Integer id) {
        return businessPartnerRepository.get(id);
    }

    public List<BusinessPartnerResponse> getList(String key, Integer type) {
        return businessPartnerRepository.getList(key, type);
    }
}
