package com.siping.service.businesspartner.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.businesspartner.service.PartnerGroupRestService;
import com.siping.smartone.businesspartner.request.PartnerGroupManagementRequest;
import com.siping.smartone.businesspartner.request.PartnerGroupRequest;
import com.siping.smartone.businesspartner.response.PartnerGroupResponse;
import com.siping.smartone.material.request.GetPartnerGroupRequest;

@Controller
public class PartnerGroupRestContrller extends StromaWebserviceController {
    @Autowired
    PartnerGroupRestService partnerGroupService;

    @RequestMapping(value = "/partnergroup/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addPartnerGroup(@RequestBody PartnerGroupRequest request) {
        return partnerGroupService.add(request);
    }

    @RequestMapping(value = "/partnergroup/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean editPartnerGroup(@RequestBody PartnerGroupRequest request) {
        return partnerGroupService.edit(request);
    }

    //@RequestMapping(value = "/partnergroup/delete/{id}/{loginId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseBody
    public Boolean deletePartnerGroupById(@PathVariable Integer id, @PathVariable Integer loginId) {
        return partnerGroupService.deletePartnerGroupById(id, loginId);
    }

    @RequestMapping(value = "/partnergroup/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PartnerGroupResponse> getAllPartnerGroup() {
        return partnerGroupService.getAllPartnerGroup();
    }
    
    @RequestMapping(value = "/partnergroup/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<PartnerGroupResponse> getList(@RequestBody GetPartnerGroupRequest request) {
        return partnerGroupService.getList(request);
    }
    @RequestMapping(value = "/partnergroup/getlist/{partnerType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PartnerGroupResponse> getList(@PathVariable Integer partnerType) {
        return partnerGroupService.getList(partnerType);
    }
    
    @RequestMapping(value = "/partnergroup/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PartnerGroupResponse get(@PathVariable Integer id) {
        return partnerGroupService.get(id);
    }
    
    @RequestMapping(value = "/partnergroup/management", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean management(@RequestBody PartnerGroupManagementRequest request) {
        return partnerGroupService.management(request);
    }
    
    @RequestMapping(value = "/partnergroup/delete/{id}/{updateBy}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@PathVariable Integer id, @PathVariable Integer updateBy) {
        return partnerGroupService.delete(id, updateBy);
    }
}
