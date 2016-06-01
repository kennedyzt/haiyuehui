package com.siping.service.ec.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.ec.service.EcApiRestService;
import com.siping.smartone.ec.request.AddVendorRequest;
import com.siping.smartone.ec.request.PromisedProduct;
import com.siping.smartone.ec.response.ProductCustomsResponse;
import com.siping.smartone.wms.request.ESaleOrderRequest;

@Controller
public class EcApiRestController extends StromaWebserviceController {
    @Autowired
    private EcApiRestService ecApiRestService;

    @RequestMapping(value = "/rest/ec/addshop", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addShop(@RequestBody AddVendorRequest request) {
        return ecApiRestService.addShop(request);
    }

    @RequestMapping(value = "/rest/ec/checkmaterialno/{materialno}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ProductCustomsResponse checkMaterialNo(@PathVariable String materialno) {
        return ecApiRestService.checkMaterialNo(materialno);
    }

    @RequestMapping(value = "/rest/ec/getonhandbalance/{productno}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getOnhandBalance(@PathVariable String productno) {
        return ecApiRestService.getOnhandBalance(productno);
    }

    @RequestMapping(value = "/rest/ec/addecsalesorder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addEcSalesorder(@RequestBody ESaleOrderRequest request) {
        return ecApiRestService.addEcSalesorder(request);
    }

    @RequestMapping(value = "/ec/promisedquantity/subtract", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean subtractQuantity(@RequestBody PromisedProduct request) {
        return ecApiRestService.subtractQuantity(request);
    }

    @RequestMapping(value = "/ec/promisedquantity/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addQuantity(@RequestBody PromisedProduct request) {
        return ecApiRestService.addQuantity(request);
    }
}
