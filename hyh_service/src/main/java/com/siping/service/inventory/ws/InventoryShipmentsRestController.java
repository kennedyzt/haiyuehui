package com.siping.service.inventory.ws;

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

import com.siping.service.inventory.service.InventoryShipmentsRestService;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.inventory.request.AddInventoryShipmentsRequest;
import com.siping.smartone.inventory.request.DeleteInventoryShipmentsRequest;
import com.siping.smartone.inventory.response.GetInventoryShipmentsListResponse;
import com.siping.smartone.inventory.response.GetInventoryShipmentsResponse;

@Controller
public class InventoryShipmentsRestController extends StromaWebserviceController {
    @Autowired
    private InventoryShipmentsRestService inventoryShipmentsRestService;

    @RequestMapping(value = "/inventoryshipments/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody AddInventoryShipmentsRequest request) {
        return inventoryShipmentsRestService.add(request);
    }

    @RequestMapping(value = "/inventoryshipments/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody AddInventoryShipmentsRequest request) {
        return inventoryShipmentsRestService.edit(request);
    }

    @RequestMapping(value = "/inventoryshipments/get/{shipmentsnumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetInventoryShipmentsResponse get(@PathVariable String shipmentsnumber) {
        return inventoryShipmentsRestService.get(shipmentsnumber);
    }

    @RequestMapping(value = "/inventoryshipments/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetInventoryShipmentsListResponse> getList(@RequestBody CommonBillsRequest request) {
        return inventoryShipmentsRestService.getList(request);
    }

    @RequestMapping(value = "/inventoryshipments/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody DeleteInventoryShipmentsRequest request) {
        return inventoryShipmentsRestService.delete(request);
    }
}
