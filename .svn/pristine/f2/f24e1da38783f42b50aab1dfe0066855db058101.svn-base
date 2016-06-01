package com.siping.service.businesspartner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.businesspartner.repository.PartnerGroupRestRepository;
import com.siping.smartone.businesspartner.request.PartnerGroupManagementRequest;
import com.siping.smartone.businesspartner.request.PartnerGroupRequest;
import com.siping.smartone.businesspartner.response.PartnerGroupResponse;
import com.siping.smartone.material.request.GetPartnerGroupRequest;

@Service
public class PartnerGroupRestService extends DBSwitch {
    @Autowired
    PartnerGroupRestRepository partnerGroupRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(PartnerGroupRequest request) {
        return partnerGroupRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(PartnerGroupRequest request) {
        return partnerGroupRepository.edit(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean deletePartnerGroupById(Integer id, Integer loginId) {
        return partnerGroupRepository.deletePartnerGroupById(id, loginId);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<PartnerGroupResponse> getAllPartnerGroup() {
        return partnerGroupRepository.getAllPartnerGroup();
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<PartnerGroupResponse> getList(GetPartnerGroupRequest request) {
        return partnerGroupRepository.getList(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<PartnerGroupResponse> getList(Integer partnerType) {
        return partnerGroupRepository.getList(partnerType);
    }

    public PartnerGroupResponse get(Integer id) {
        return partnerGroupRepository.get(id);
    }

    public Boolean management(PartnerGroupManagementRequest request) {
        return partnerGroupRepository.management(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(Integer id, Integer updateBy) {
        return partnerGroupRepository.delete(id, updateBy);
    }

}
