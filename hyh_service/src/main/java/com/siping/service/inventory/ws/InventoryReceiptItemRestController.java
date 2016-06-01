package com.siping.service.inventory.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.inventory.service.InventoryReceiptItemRestService;
import com.siping.smartone.inventory.request.InventoryReceiptItemBatchDeleteRequest;
import com.siping.smartone.inventory.request.InventoryReceiptItemRequest;

@Controller
public class InventoryReceiptItemRestController extends StromaWebserviceController {
    @Autowired
    InventoryReceiptItemRestService inventoryReceiptItemRestService;

    @RequestMapping(value = "/inventoryreceiptitem/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody InventoryReceiptItemRequest request) {
        return inventoryReceiptItemRestService.add(request);
    }

    @RequestMapping(value = "/inventoryreceiptitem/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody InventoryReceiptItemBatchDeleteRequest request) {
        return inventoryReceiptItemRestService.delete(request);
    }

    @RequestMapping(value = "/inventoryreceiptitem/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody InventoryReceiptItemRequest request) {
        return inventoryReceiptItemRestService.edit(request);
    }

}
