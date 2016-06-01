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
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.purchase.service.PurchaseReceiptRestService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.common.request.GetGenerateBillsNumber;
import com.siping.smartone.purchase.request.DeletePurchaseReceiptRequest;
import com.siping.smartone.purchase.request.PurchaseReceiptRequest;
import com.siping.smartone.purchase.response.PurchaseReceiptResponse;

@Controller
public class PurchaseReceiptRestController extends StromaWebserviceController {
    @Autowired
    private PurchaseReceiptRestService purchaseReceiptRestService;

    @RequestMapping(value = "/purchasereceipt/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultMsg add(@RequestBody PurchaseReceiptRequest request) {
        ResultMsg resultMsg;
        resultMsg = purchaseReceiptRestService.add(request);
        if (resultMsg.isSuccess() && StringUtils.hasText(request.getFromBillsNo())) {
            purchaseReceiptRestService.updatePOStatus(request.getFromBillsNo(), request.getCreateBy());
        }
        return resultMsg;
    }

    @RequestMapping(value = "/purchasereceipt/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody DeletePurchaseReceiptRequest request) {
        return purchaseReceiptRestService.delete(request);
    }

    @RequestMapping(value = "/purchasereceipt/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody PurchaseReceiptRequest request) {
        return purchaseReceiptRestService.edit(request);
    }

    @RequestMapping(value = "/purchasereceipt/get/{orderNumber}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PurchaseReceiptResponse get(@PathVariable String orderNumber) {
        return purchaseReceiptRestService.get(orderNumber);
    }

    @RequestMapping(value = "/purchasereceipt/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<PurchaseReceiptResponse> getList(@RequestBody CommonBillsRequest request) {
        return purchaseReceiptRestService.getList(request);
    }

    @RequestMapping(value = "/purchasereceipt/getGenerateBillsNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getGenerateBillsNumber(@RequestBody GetGenerateBillsNumber request) {
        return purchaseReceiptRestService.getGenerateBillsNumber(request);
    }
}
