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

import com.siping.service.businesspartner.service.BusinessPartnerRestService;
import com.siping.smartone.businesspartner.request.BusinessPartnerBatchAddRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerBatchDeleteRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerExportParamRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerRequest;
import com.siping.smartone.businesspartner.response.BusinessPartnerQueryParam;
import com.siping.smartone.businesspartner.response.BusinessPartnerResponse;
import com.siping.smartone.businesspartner.response.UploadBusinessPartnerResponse;

@Controller
public class BusinessPartnerRestController extends StromaWebserviceController {
    @Autowired
    BusinessPartnerRestService businessPartnerService;

    @RequestMapping(value = "/businesspartner/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addBusinessPartner(@RequestBody BusinessPartnerRequest request) {
        return businessPartnerService.addBusinessPartner(request);
    }

    @RequestMapping(value = "/businesspartner/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean editBusinessPartner(@RequestBody BusinessPartnerRequest request) {
        return businessPartnerService.editBusinessPartner(request);
    }

    @RequestMapping(value = "/businesspartner/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean deleteBusinessPartnerById(@RequestBody BusinessPartnerBatchDeleteRequest request) {
        return businessPartnerService.deleteBusinessPartnerById(request);
    }

    @RequestMapping(value = "/businesspartner/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<BusinessPartnerResponse> getAllBusinessPartner(@RequestBody BusinessPartnerQueryParam request) {
        return businessPartnerService.getAllBusinessPartner(request);
    }

    @RequestMapping(value = "/businesspartner/batchadd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UploadBusinessPartnerResponse batchAdd(@RequestBody BusinessPartnerBatchAddRequest request) {
        UploadBusinessPartnerResponse response = businessPartnerService.batchAdd(request);
        return response;
    }

    @RequestMapping(value = "/businesspartner/export", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<BusinessPartnerResponse> export(@RequestBody BusinessPartnerExportParamRequest request) {
        return businessPartnerService.export(request);
    }

    @RequestMapping(value = "/businesspartner/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BusinessPartnerResponse get(@PathVariable Integer id) {
        return businessPartnerService.get(id);
    }

    @RequestMapping(value = "/businesspartner/getlistbykey/{key}/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<BusinessPartnerResponse> getList(@PathVariable String key, @PathVariable Integer type) {
        return businessPartnerService.getList(key, type);
    }
}
