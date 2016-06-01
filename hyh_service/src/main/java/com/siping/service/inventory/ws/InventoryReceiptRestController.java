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

import com.siping.service.inventory.service.InventoryReceiptRestService;
import com.siping.smartone.inventory.request.ChangeStatusParam;
import com.siping.smartone.inventory.request.InventoryReceiptBatchDeleteRequest;
import com.siping.smartone.inventory.request.InventoryReceiptRequest;
import com.siping.smartone.inventory.request.InventoryReceitQueryParam;
import com.siping.smartone.inventory.response.InventoryReceiptResponse;
import com.siping.smartone.wms.request.ConfirmReceiptParam;

@Controller
public class InventoryReceiptRestController extends StromaWebserviceController {
    @Autowired
    InventoryReceiptRestService inventoryReceiptRestService;

    @RequestMapping(value = "/inventoryreceipt/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody InventoryReceiptRequest request) {
        return inventoryReceiptRestService.add(request);
    }

    @RequestMapping(value = "/inventoryreceipt/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody InventoryReceiptBatchDeleteRequest request) {
        return inventoryReceiptRestService.delete(request);
    }

    @RequestMapping(value = "/inventoryreceipt/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody InventoryReceiptRequest request) {
        return inventoryReceiptRestService.edit(request);
    }
    
    @RequestMapping(value = "/inventoryreceipt/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<InventoryReceiptResponse> getList(@RequestBody InventoryReceitQueryParam request) {
        return inventoryReceiptRestService.getList(request);
    }
    
    @RequestMapping(value = "/inventoryreceipt/get/{receiptNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public InventoryReceiptResponse get(@PathVariable String receiptNumber) {
        return inventoryReceiptRestService.get(receiptNumber);
    }
    
    @RequestMapping(value = "/inventoryreceipt/confirmreceipt", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean confirmReceipt(@RequestBody ConfirmReceiptParam request) {
        return inventoryReceiptRestService.confirmReceipt(request);
    }
    
    @RequestMapping(value = "/inventoryreceipt/changestatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean changeStatus(@RequestBody ChangeStatusParam request) {
        return inventoryReceiptRestService.changeStatus(request);
    }

}
