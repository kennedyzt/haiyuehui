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
import com.siping.service.businesspartner.service.CountryRegionRestService;
import com.siping.smartone.businesspartner.request.CountryRegionRequest;
import com.siping.smartone.businesspartner.response.CountryRegionResponse;

@Controller
public class CountryRegionRestController {
    @Autowired
    CountryRegionRestService countryRegionRestService;

    @RequestMapping(value = "/countryregion/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addBusinessPartner(@RequestBody CountryRegionRequest request) {
        return countryRegionRestService.add(request);
    }

    @RequestMapping(value = "/countryregion/getlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CountryRegionResponse> getList() {
        return countryRegionRestService.getList();
    }

    @RequestMapping(value = "/countryregion/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CountryRegionResponse get(@PathVariable Integer id) {
        return countryRegionRestService.get(id);
    }
    @RequestMapping(value = "/countryregion/delete/{id}/{loginId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@PathVariable Integer id, @PathVariable Integer loginId) {
        return countryRegionRestService.delete(id, loginId);
    }
}
