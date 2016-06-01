package com.siping.service.businesspartner.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.businesspartner.service.ShopRestService;
import com.siping.smartone.businesspartner.response.ShopResponse;
import com.siping.smartone.common.request.CommonBillsRequest;
@Controller
public class ShopRestController  extends StromaWebserviceController{
    @Autowired
    private ShopRestService shopRestService;
    @RequestMapping(value = "/shop/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<ShopResponse> getList(@RequestBody CommonBillsRequest request) {
        return shopRestService.getList(request);
    }
}
