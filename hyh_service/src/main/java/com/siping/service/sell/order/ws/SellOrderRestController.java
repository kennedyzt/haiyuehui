package com.siping.service.sell.order.ws;

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

import com.siping.service.sell.order.service.SellOrderRestService;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.invoicing.sell.order.request.AddSellOrderRequest;
import com.siping.smartone.invoicing.sell.order.request.DeleteSellOrderRequest;
import com.siping.smartone.invoicing.sell.order.response.GetSellOrderListResponse;
import com.siping.smartone.invoicing.sell.order.response.GetSellOrderResponse;

@Controller
public class SellOrderRestController extends StromaWebserviceController {
    @Autowired
    private SellOrderRestService sellOrderRestService;

    @RequestMapping(value = "/sellorder/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody AddSellOrderRequest request) {
        return sellOrderRestService.add(request);
    }

    @RequestMapping(value = "/sellorder/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody AddSellOrderRequest request) {
        return sellOrderRestService.edit(request);
    }

    @RequestMapping(value = "/sellorder/get/{ordernumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetSellOrderResponse get(@PathVariable String ordernumber) {
        return sellOrderRestService.get(ordernumber);
    }

    @RequestMapping(value = "/sellorder/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetSellOrderListResponse> getList(@RequestBody CommonBillsRequest request) {
        return sellOrderRestService.getList(request);
    }

    @RequestMapping(value = "/sellorder/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody DeleteSellOrderRequest request) {
        return sellOrderRestService.delete(request);
    }
}
