package com.siping.service.businesspartner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.businesspartner.repository.BusinessPartnerRestRepository;
import com.siping.smartone.businesspartner.request.BusinessPartnerBatchAddRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerBatchDeleteRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerExportParamRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerRequest;
import com.siping.smartone.businesspartner.response.BusinessPartnerQueryParam;
import com.siping.smartone.businesspartner.response.BusinessPartnerResponse;
import com.siping.smartone.businesspartner.response.UploadBusinessPartnerResponse;

@Service
public class BusinessPartnerRestService extends DBSwitch {
    @Autowired
    BusinessPartnerRestRepository businessPartnerRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addBusinessPartner(BusinessPartnerRequest request) {
        return businessPartnerRepository.addBusinessPartner(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean editBusinessPartner(BusinessPartnerRequest request) {
        return businessPartnerRepository.editBusinessPartner(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean deleteBusinessPartnerById(BusinessPartnerBatchDeleteRequest request) {
        return businessPartnerRepository.deleteBusinessPartnerById(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<BusinessPartnerResponse> getAllBusinessPartner(BusinessPartnerQueryParam request) {
        return businessPartnerRepository.getAllBusinessPartner(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UploadBusinessPartnerResponse batchAdd(BusinessPartnerBatchAddRequest request) {
        return businessPartnerRepository.batchAdd(request);
    }

    public List<BusinessPartnerResponse> export(BusinessPartnerExportParamRequest request) {
        return businessPartnerRepository.export(request);
    }

    public BusinessPartnerResponse get(Integer id) {
        return businessPartnerRepository.get(id);
    }

    public List<BusinessPartnerResponse> getList(String key, Integer type) {
        return businessPartnerRepository.getList(key, type);
    }
}
