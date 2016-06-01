package com.siping.service.partnertype.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;

import com.siping.service.partnertype.repository.PartnerTypeRestRepository;
import com.siping.smartone.partnertype.request.PartnerTypeRequest;
import com.siping.smartone.partnertype.response.PartnerTypeResponse;

@Service
public class PartnerTypeRestService extends DBSwitch {
    @Autowired
    PartnerTypeRestRepository partnerTypeRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addPartnerType(PartnerTypeRequest request) {
        return partnerTypeRepository.addPartnerType(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean editPartnerType(PartnerTypeRequest request) {
        return partnerTypeRepository.editPartnerType(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean deletePartnerTypeById(Integer id, Integer loginId) {
        return partnerTypeRepository.deletePartnerTypeById(id, loginId);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<PartnerTypeResponse> getAllPartnerType() {
        return partnerTypeRepository.getAllPartnerType();
    }

}
