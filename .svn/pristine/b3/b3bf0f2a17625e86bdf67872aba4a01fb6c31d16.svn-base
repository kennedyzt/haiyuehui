package com.siping.service.inventory.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.inventory.service.InventoryRestService;
import com.siping.smartone.inventory.request.MaterialCountsRequest;
import com.siping.smartone.inventory.response.InventoryWarningMsg;
import com.siping.smartone.invoicing.inventory.inventory.request.AddInventoryRequest;
import com.siping.smartone.invoicing.inventory.inventory.request.GetInventoryIsEnoughRequest;
import com.siping.smartone.invoicing.inventory.inventory.response.GetInventoryLogResponse;
import com.siping.smartone.invoicing.inventory.inventory.response.GetInventoryResponse;

@Controller
public class InventoryRestController extends StromaWebserviceController {
    @Autowired
    private InventoryRestService inventoryRestService;

    @RequestMapping(value = "/inventory/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody AddInventoryRequest request) {
        return inventoryRestService.add(request);
    }

    @RequestMapping(value = "/inventory/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody AddInventoryRequest request) {
        return inventoryRestService.edit(request);
    }

    @RequestMapping(value = "/inventory/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetInventoryResponse get(@PathVariable String id) {
        return inventoryRestService.get(id);
    }

    @RequestMapping(value = "/inventorylog/getlist/{materialid}/{storageid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetInventoryLogResponse> get(@PathVariable String materialid, @PathVariable String storageid) {
        return inventoryRestService.getList(materialid, storageid);
    }

    @RequestMapping(value = "/inventory/isenough", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean isEnough(@RequestBody GetInventoryIsEnoughRequest request) {
        return inventoryRestService.isEnough(request);
    }

    @RequestMapping(value = "/inventory/ismorethan", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public InventoryWarningMsg isMoreThan(@RequestBody List<MaterialCountsRequest> request) {
        return inventoryRestService.isMoreThan(request);
    }
}
