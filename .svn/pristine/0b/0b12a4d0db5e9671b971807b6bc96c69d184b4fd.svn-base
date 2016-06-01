package com.siping.service.purchase.ws;

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

import com.siping.service.purchase.service.PurchaseReturnsRestService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.purchase.request.PurchaseReturnsBatchDeleteRequest;
import com.siping.smartone.purchase.request.PurchaseReturnsOrderQueryParam;
import com.siping.smartone.purchase.request.PurchaseReturnsRequest;
import com.siping.smartone.purchase.response.PurchaseReturnsResponse;

@Controller
public class PurchaseReturnsRestController extends StromaWebserviceController {
    @Autowired
    PurchaseReturnsRestService purchaseReturnsRestService;

    @RequestMapping(value = "/purchasereturns/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultMsg add(@RequestBody PurchaseReturnsRequest request) {
        return purchaseReturnsRestService.add(request);
    }

    @RequestMapping(value = "/purchasereturns/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody PurchaseReturnsBatchDeleteRequest request) {
        return purchaseReturnsRestService.delete(request);
    }

    @RequestMapping(value = "/purchasereturns/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody PurchaseReturnsRequest request) {
        return purchaseReturnsRestService.edit(request);
    }

    @RequestMapping(value = "/purchasereturns/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<PurchaseReturnsResponse> getList(@RequestBody PurchaseReturnsOrderQueryParam request) {
        return purchaseReturnsRestService.getList(request);
    }

    @RequestMapping(value = "/purchasereturns/get/{returnsNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PurchaseReturnsResponse get(@PathVariable String returnsNumber) {
        return purchaseReturnsRestService.get(returnsNumber);
    }

}
