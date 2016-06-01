package com.siping.wms.ws;

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

import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.wms.request.GenerateReceiptParam;
import com.siping.smartone.wms.response.ReadyReceiptResponse;
import com.siping.wms.service.ReadyReceiptRestService;

@Controller
public class ReadyReceiptRestController extends StromaWebserviceController {
    @Autowired
    ReadyReceiptRestService readyReceiptRestService;

    @RequestMapping(value = "/readyreceipt/get/{orderNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ReadyReceiptResponse get(@PathVariable String orderNumber) {
        return readyReceiptRestService.get(orderNumber);
    }

    @RequestMapping(value = "/readyreceipt/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<ReadyReceiptResponse> getList(@RequestBody CommonBillsRequest request) {
        return readyReceiptRestService.getList(request);
    }

    @RequestMapping(value = "/readyreceipt/generateReceipt", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody GenerateReceiptParam request) {
        return readyReceiptRestService.generateReceipt(request);
    }
}
