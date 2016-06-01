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

import com.siping.service.purchase.service.PurchaseApplyOrderRestService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.purchase.request.DeletePurchaseApplyOrderRequest;
import com.siping.smartone.purchase.request.PurchaseApplyOrderRequest;
import com.siping.smartone.purchase.response.PurchaseApplyOrderResponse;

@Controller
public class PurchaseApplyOrderRestController extends StromaWebserviceController {
    @Autowired
    private PurchaseApplyOrderRestService purchaseApplyOrderRestService;

    @RequestMapping(value = "/purchaseapplyorder/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultMsg add(@RequestBody PurchaseApplyOrderRequest order) {
        return purchaseApplyOrderRestService.add(order);
    }

    @RequestMapping(value = "/purchaseapplyorder/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody DeletePurchaseApplyOrderRequest request) {
        return purchaseApplyOrderRestService.delete(request);
    }

    @RequestMapping(value = "/purchaseapplyorder/get/{orderNumber}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PurchaseApplyOrderResponse get(@PathVariable String orderNumber) {
        return purchaseApplyOrderRestService.get(orderNumber);
    }

    @RequestMapping(value = "/purchaseapplyorder/getallapplypo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<PurchaseApplyOrderResponse> getAllApplyPO(@RequestBody CommonBillsRequest request) {
        return purchaseApplyOrderRestService.getAllApplyPO(request);
    }

    @RequestMapping(value = "/purchaseapplyorder/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody PurchaseApplyOrderRequest request) {
        return purchaseApplyOrderRestService.edit(request);
    }
}
