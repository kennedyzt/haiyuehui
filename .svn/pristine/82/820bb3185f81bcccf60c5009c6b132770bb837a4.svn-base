package com.siping.service.inventory.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.inventory.service.InventoryCheckRestService;
import com.siping.smartone.inventory.request.AddInventoryCheckRequest;
import com.siping.smartone.inventory.request.AddInventoryCheckRequestNew;
import com.siping.smartone.inventory.request.DeleteInventoryCheckRequest;
import com.siping.smartone.inventory.request.GetInventoryCheckListRequest;
import com.siping.smartone.inventory.request.GetMaterialBatchNumberListRequest;
import com.siping.smartone.inventory.request.GetInventoryCheckRequest;
import com.siping.smartone.inventory.response.GetMaterialBatchNumberResponse;
import com.siping.smartone.inventory.response.GetInventoryCheckResponse;
import com.siping.smartone.inventory.response.GetInventoryCheckResponseNew;

@Controller
public class InventoryCheckRestController extends StromaWebserviceController {
    @Autowired
    private InventoryCheckRestService inventoryCheckRestService;

//    @RequestMapping(value = "/inventorycheck/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Boolean add(@RequestBody AddInventoryCheckRequest request) {
//        return inventoryCheckRestService.add(request);
//    }

    @RequestMapping(value = "/invnentorycheck/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody AddInventoryCheckRequestNew request) {
        return inventoryCheckRestService.edit(request);
    }

    @RequestMapping(value = "/invnentorycheck/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetInventoryCheckResponseNew get(@RequestBody GetInventoryCheckRequest request) {
        return inventoryCheckRestService.get(request);
    }

    @RequestMapping(value = "/invnentorycheck/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetInventoryCheckResponseNew> getList(@RequestBody GetInventoryCheckListRequest request) {
        return inventoryCheckRestService.getList(request);
    }

    @RequestMapping(value = "/inventorycheck/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody DeleteInventoryCheckRequest request) {
        return inventoryCheckRestService.delete(request);
    }
    
    @RequestMapping(value = "/invnentorycheck/getmaterialbatchnumberlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetMaterialBatchNumberResponse> getMaterialBatchNumberList(@RequestBody GetMaterialBatchNumberListRequest request) {
        return inventoryCheckRestService.getMaterialBatchNumberList(request);
    }
    
    @RequestMapping(value = "/inventorycheck/addnew", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addNew(@RequestBody AddInventoryCheckRequestNew request) {
        return inventoryCheckRestService.addNew(request);
    }

    @RequestMapping(value = "/pdainventorycheck/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addPdaInventoryCheck(@RequestBody AddInventoryCheckRequestNew request) {
        return inventoryCheckRestService.addPdaInventoryCheck(request);
    }
}
