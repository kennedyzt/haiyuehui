package com.siping.service.sell.shipments.ws;

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

import com.siping.service.sell.shipments.service.SellShipmentsRestService;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.invoicing.sell.shipments.request.AddSellShipmentsRequest;
import com.siping.smartone.invoicing.sell.shipments.request.BatchAddSellShipmentsRequest;
import com.siping.smartone.invoicing.sell.shipments.request.DeleteSellShipmentsRequest;
import com.siping.smartone.invoicing.sell.shipments.response.GetSellShipmentsListResponse;
import com.siping.smartone.invoicing.sell.shipments.response.GetSellShipmentsResponse;

@Controller
public class SellShipmentsRestController extends StromaWebserviceController {
    @Autowired
    private SellShipmentsRestService sellShipmentsRestService;

    @RequestMapping(value = "/sellshipments/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody AddSellShipmentsRequest request) {
        return sellShipmentsRestService.add(request);
    }

    @RequestMapping(value = "/sellshipments/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody AddSellShipmentsRequest request) {
        return sellShipmentsRestService.edit(request);
    }

    @RequestMapping(value = "/sellshipments/get/{shipmentsnumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetSellShipmentsResponse get(@PathVariable String shipmentsnumber) {
        return sellShipmentsRestService.get(shipmentsnumber);
    }

    @RequestMapping(value = "/sellshipments/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetSellShipmentsListResponse> getList(@RequestBody CommonBillsRequest request) {
        return sellShipmentsRestService.getList(request);
    }

    @RequestMapping(value = "/sellshipments/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody DeleteSellShipmentsRequest request) {
        return sellShipmentsRestService.delete(request);
    }
    
    @RequestMapping(value = "/sellshipments/adds", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean adds(@RequestBody BatchAddSellShipmentsRequest request) {
        return sellShipmentsRestService.adds(request);
    }
}
