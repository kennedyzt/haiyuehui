package com.siping.intranet.crm.partnertype.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siping.intranet.crm.partnertype.repository.PartnerTypeRepository;
import com.siping.smartone.partnertype.request.PartnerTypeRequest;
import com.siping.smartone.partnertype.response.PartnerTypeResponse;

@Service
public class PartnerTypeService {
    @Autowired
    PartnerTypeRepository partnerTypeRepository;

    public Boolean addPartnerType(PartnerTypeRequest request) {
        return partnerTypeRepository.addPartnerType(request);
    }

    public Boolean editPartnerTypeById(PartnerTypeRequest request) {
        return partnerTypeRepository.editPartnerTypeById(request);
    }

    public Boolean deletePartnerTypeById(Integer id, Integer loginId) {
        return partnerTypeRepository.deletePartnerTypeById(id, loginId);
    }

    public List<PartnerTypeResponse> getAllPartnerType() {
        return partnerTypeRepository.getAllPartnerType();
    }

}
