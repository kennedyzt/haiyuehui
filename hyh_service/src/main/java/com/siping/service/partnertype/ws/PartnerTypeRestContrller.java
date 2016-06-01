package com.siping.service.partnertype.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.partnertype.service.PartnerTypeRestService;
import com.siping.smartone.partnertype.request.PartnerTypeRequest;
import com.siping.smartone.partnertype.response.PartnerTypeResponse;

@Controller
public class PartnerTypeRestContrller extends StromaWebserviceController {
    @Autowired
    PartnerTypeRestService partnerTypeService;

    @RequestMapping(value = "/partnertype/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addPartnerType(@RequestBody PartnerTypeRequest request) {
        return partnerTypeService.addPartnerType(request);
    }

    @RequestMapping(value = "/partnertype/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean editPartnerType(@RequestBody PartnerTypeRequest request) {
        return partnerTypeService.editPartnerType(request);
    }

    @RequestMapping(value = "/partnertype/delete/{id}/{loginId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean deletePartnerTypeById(@PathVariable Integer id, @PathVariable Integer loginId) {
        return partnerTypeService.deletePartnerTypeById(id, loginId);
    }

    @RequestMapping(value = "/partnertype/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PartnerTypeResponse> getAllPartnerType() {
        return partnerTypeService.getAllPartnerType();
    }

}
