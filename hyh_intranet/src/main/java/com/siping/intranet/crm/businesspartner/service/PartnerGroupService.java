package com.siping.intranet.crm.businesspartner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.businesspartner.repository.PartnerGroupRepository;
import com.siping.smartone.businesspartner.request.PartnerGroupManagementRequest;
import com.siping.smartone.businesspartner.request.PartnerGroupRequest;
import com.siping.smartone.businesspartner.response.PartnerGroupResponse;

@Service
public class PartnerGroupService {
    @Autowired
    PartnerGroupRepository partnerGroupRepository;

    public Boolean add(PartnerGroupRequest request) {
        return partnerGroupRepository.add(request);
    }

    public Boolean edit(PartnerGroupRequest request) {
        return partnerGroupRepository.edit(request);
    }

    public Boolean deletePartnerGroupById(Integer id, Integer loginId) {
        return partnerGroupRepository.deletePartnerGroupById(id, loginId);
    }

    public List<PartnerGroupResponse> getList() {
        return partnerGroupRepository.getList();
    }

    public PagingResponse<PartnerGroupResponse> getList(Integer pageNo, Integer pageSize, Integer partnerType) {
        return partnerGroupRepository.getList(pageNo, pageSize, partnerType);
    }

    public List<PartnerGroupResponse> getList(Integer partnerType) {
        return partnerGroupRepository.getList(partnerType);
    }

    public PartnerGroupResponse get(Integer id) {
        return partnerGroupRepository.partnerGroupRepository(id);
    }

    public Boolean management(PartnerGroupManagementRequest request) {
        return partnerGroupRepository.management(request);
    }

    public Boolean delete(Integer id, Integer updateBy) {
        return partnerGroupRepository.delete(id, updateBy);
    }
}
