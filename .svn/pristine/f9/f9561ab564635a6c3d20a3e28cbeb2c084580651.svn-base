package com.siping.service.report.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.report.service.ProductReportRestService;
import com.siping.smartone.inventory.request.ProductExpirationRequest;
import com.siping.smartone.inventory.response.ProductExpirationResponse;

@Controller
public class ProductReportRestController extends StromaWebserviceController {

    @Autowired
    private ProductReportRestService productReportRestService;
    

    @RequestMapping(value = "/material/rest/expiration/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<ProductExpirationResponse> getExpirationList(@RequestBody ProductExpirationRequest request) {
        return productReportRestService.getExpirationList(request);
    }

}
