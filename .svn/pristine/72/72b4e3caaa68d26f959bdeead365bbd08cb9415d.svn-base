package com.siping.wms.ws;

import java.util.List;

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

import com.siping.service.material.service.MaterialRestService;
import com.siping.service.storage.service.StorageLocationRestService;
import com.siping.smartone.inventory.request.AddInventoryCheckItemRequest;
import com.siping.smartone.inventory.request.InventoryReceiptBatchDeleteRequest;
import com.siping.smartone.inventory.request.PdaReceiptOrderParam;
import com.siping.smartone.inventory.response.GetInventoryCheckResponseNew;
import com.siping.smartone.inventory.response.InventoryReceiptItemResponse;
import com.siping.smartone.inventory.response.InventoryReceiptResponse;
import com.siping.smartone.material.response.GetMaterialBatchResponse;
import com.siping.smartone.wms.request.AddInventoryAllocateRequest;
import com.siping.wms.service.PdaRestService;

@Controller
public class PdaRestController extends StromaWebserviceController {

    @Autowired
    private PdaRestService pdaRestService;
    @Autowired
    private MaterialRestService materialRestService;
    @Autowired
    private StorageLocationRestService storageLocationRestService;

    @RequestMapping(value = "/pda/inventorycheck/commit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean commitInventoryCheck(@RequestBody GetInventoryCheckResponseNew request) {
        return pdaRestService.commitInventoryCheck(request);
    }

    @RequestMapping(value = "pda/inventorycheck/get/{checkNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetInventoryCheckResponseNew getInventoryCheck(@PathVariable String checkNumber) {
        return pdaRestService.getInventoryCheck(checkNumber);
    }

    @RequestMapping(value = "/pda/inventorycheck/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetInventoryCheckResponseNew> getInventoryCheckList(@RequestBody PdaReceiptOrderParam request) {
        return pdaRestService.getInventoryCheckList(request);
    }

    @RequestMapping(value = "/pda/inventoryreceipt/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<InventoryReceiptResponse> getList(@RequestBody PdaReceiptOrderParam request) {
        return pdaRestService.getList(request);
    }

    @RequestMapping(value = "/pda/inventoryreceipt/get/{receiptNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public InventoryReceiptResponse get(@PathVariable String receiptNumber) {
        return pdaRestService.get(receiptNumber);
    }

    // @RequestMapping(value = "/pda/inventoryreceipt/add", method =
    // RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    // @ResponseBody
    // public Boolean add(@RequestBody InventoryReceiptRequest request) {
    // return pdaRestService.add(request);
    // }

    @RequestMapping(value = "/pda/inventoryreceipt/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody InventoryReceiptBatchDeleteRequest request) {
        return pdaRestService.delete(request);
    }

    @RequestMapping(value = "/pda/inventoryreceiptitem/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addItem(@RequestBody InventoryReceiptItemResponse request) {
        return pdaRestService.addItem(request);
    }

    @RequestMapping(value = "/pda/inventoryreceiptitem/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody InventoryReceiptItemResponse request) {
        return pdaRestService.edit(request);
    }

    @RequestMapping(value = "/pda/inventoryreceiptitem/getrownumber/{batchNumber}/{materialid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getRowNumber(@PathVariable String batchNumber, @PathVariable int materialid) {
        return pdaRestService.getRowNumber(batchNumber, materialid);
    }

    @RequestMapping(value = "/pda/getstoragelocationmaterialwithbatch/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetMaterialBatchResponse> getMaterialBatchByStorageLocation(@RequestBody String locationNo) {
        return pdaRestService.getMaterialBatchByStorageLocation(locationNo);
    }

    @RequestMapping(value = "/pda/getStorageLocationWithMaterialBatchByMaterial/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetMaterialBatchResponse> getStorageLocationWithMaterialBatchByMaterial(@RequestBody String materialNo) {
        return pdaRestService.getStorageLocationWithMaterialBatchByMaterial(materialNo);
    }

    @RequestMapping(value = "/pda/checkbatchno/{batchNo}/{materialId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean checkBatchNo(@PathVariable String batchNo, @PathVariable String materialId) {
        return pdaRestService.checkBatchNo(batchNo, materialId);
    }

    @RequestMapping(value = "/pda/checkallocatenumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean checkAllocateNumber(@RequestBody AddInventoryAllocateRequest request) {
        return pdaRestService.checkAllocateNumber(request);
    }
}
