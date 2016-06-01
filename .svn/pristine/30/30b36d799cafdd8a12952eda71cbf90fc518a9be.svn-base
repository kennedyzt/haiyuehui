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

import com.siping.service.purchase.service.PurchaseOrderRestService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.purchase.request.DeletePurchaseOrderRequest;
import com.siping.smartone.purchase.request.PurchaseOrderRequest;
import com.siping.smartone.purchase.response.PurchaseOrderResponse;

@Controller
public class PurchaseOrderRestController extends StromaWebserviceController {
    @Autowired
    PurchaseOrderRestService purchaseOrderRestService;

    @RequestMapping(value = "/purchaseorder/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultMsg add(@RequestBody PurchaseOrderRequest order) {
        return purchaseOrderRestService.add(order);
    }

    @RequestMapping(value = "/purchaseorder/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody DeletePurchaseOrderRequest request) {
        return purchaseOrderRestService.delete(request);
    }

    @RequestMapping(value = "/purchaseorder/get/{orderNumber}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PurchaseOrderResponse get(@PathVariable String orderNumber) {
        return purchaseOrderRestService.get(orderNumber);
    }

    @RequestMapping(value = "/purchaseorder/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<PurchaseOrderResponse> getList(@RequestBody CommonBillsRequest request) {
        return purchaseOrderRestService.getList(request);
    }

    @RequestMapping(value = "/purchaseorder/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody PurchaseOrderRequest request) {
        return purchaseOrderRestService.edit(request);
    }

}
