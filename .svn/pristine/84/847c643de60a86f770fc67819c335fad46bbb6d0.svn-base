package com.siping.intranet.crm.businesspartner.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.businesspartner.request.PartnerGroupManagementRequest;
import com.siping.smartone.businesspartner.request.PartnerGroupRequest;
import com.siping.smartone.businesspartner.response.PartnerGroupResponse;
import com.siping.smartone.material.request.GetPartnerGroupRequest;

@Repository
public class PartnerGroupRepository {
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(PartnerGroupRequest request) {
        if (null == request.getGroupName()) {
            throw new BusinessProcessException("组名不能为空");
        }
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/partnergroup/add").body(requestContent));
    }

    public Boolean edit(PartnerGroupRequest request) {
        if (request.getId() == null) {
            throw new BusinessProcessException("主键缺失不能修改组信息");
        }
        if (null == request.getGroupName()) {
            throw new BusinessProcessException("组名不能为空");
        }
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/partnergroup/edit").body(requestContent));
    }

    public Boolean deletePartnerGroupById(Integer id, Integer loginId) {
        if (null == id || null == loginId) {
            throw new BusinessProcessException("主键缺失不能删除业组");
        }
        return stromaWebServiceApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/partnergroup/delete/%s/%s").arguments(id, loginId));
    }

    @SuppressWarnings("unchecked")
    public List<PartnerGroupResponse> getList() {
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(PartnerGroupResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/partnergroup/getAll"));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<PartnerGroupResponse> getList(Integer pageNo, Integer pageSize, Integer partnerType) {
        GetPartnerGroupRequest request = new GetPartnerGroupRequest();
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        request.setPartnerType(partnerType);
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi
            .post(EndPointBuilder.create(PagingResponse.class).elementTypes(PartnerGroupResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/partnergroup/getlist").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<PartnerGroupResponse> getList(Integer partnerType) {
        return stromaWebServiceApi
            .get(EndPointBuilder.create(List.class).elementTypes(PartnerGroupResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/partnergroup/getlist/%s").arguments(partnerType));
    }

    public PartnerGroupResponse partnerGroupRepository(Integer id) {
        return stromaWebServiceApi.get(EndPointBuilder.create(PartnerGroupResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/partnergroup/get/%s").arguments(id));
    }

    public Boolean management(PartnerGroupManagementRequest request) {
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/partnergroup/management").body(requestContent));
    }

    public Boolean delete(Integer id, Integer updateBy) {
        if(null == id){
            throw new BusinessProcessException("缺失主键，不能删除partner组");
        }
        if (null == updateBy) {
            throw new BusinessProcessException("当前登录出现问题，无法修改");
        }
        return stromaWebServiceApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/partnergroup/delete/%s/%s").arguments(id, updateBy));
    }
}
