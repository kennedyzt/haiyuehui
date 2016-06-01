package com.siping.service.inventory.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.inventory.service.InventoryCheckItemRestService;
import com.siping.smartone.inventory.request.AddInventoryCheckItemRequest;
import com.siping.smartone.inventory.request.DeleteInventoryCheckItemRequest;
import com.siping.smartone.inventory.request.GetInventoryCheckItemListRequest;
import com.siping.smartone.inventory.request.GetInventoryCheckItemRequest;
import com.siping.smartone.inventory.response.GetInventoryCheckItemResponse;

@Controller
public class InventoryCheckItemRestController extends StromaWebserviceController {
    @Autowired
    private InventoryCheckItemRestService inventoryCheckItemRestService;

    @RequestMapping(value = "/inventorycheckitem/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody AddInventoryCheckItemRequest request) {
        return inventoryCheckItemRestService.add(request);
    }

    @RequestMapping(value = "/inventorycheckitem/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody AddInventoryCheckItemRequest request) {
        return inventoryCheckItemRestService.edit(request);
    }

    @RequestMapping(value = "/inventorycheckitem/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetInventoryCheckItemResponse get(@RequestBody GetInventoryCheckItemRequest request) {
        return inventoryCheckItemRestService.get(request);
    }

    @RequestMapping(value = "/inventorycheckitem/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetInventoryCheckItemResponse> getList(@RequestBody GetInventoryCheckItemListRequest request) {
        return inventoryCheckItemRestService.getList(request);
    }

    @RequestMapping(value = "/inventorycheckitem/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody DeleteInventoryCheckItemRequest request) {
        return inventoryCheckItemRestService.delete(request);
    }
}
