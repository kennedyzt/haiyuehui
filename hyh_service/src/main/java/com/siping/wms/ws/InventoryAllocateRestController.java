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
import com.siping.smartone.wms.request.AddInventoryAllocateRequest;
import com.siping.smartone.wms.request.GetInventoryAllocateListRequest;
import com.siping.smartone.wms.response.GetInventoryAllocateResponse;
import com.siping.wms.service.InventoryAllocateRestService;

@Controller
public class InventoryAllocateRestController extends StromaWebserviceController {

    @Autowired
    private InventoryAllocateRestService inventoryAllocateRestService;

    @RequestMapping(value = "/pda/inventoryallocate/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetInventoryAllocateResponse> getList(@RequestBody GetInventoryAllocateListRequest request) {
        return inventoryAllocateRestService.getList(request);
    }
    
    @RequestMapping(value = "pda/inventoryallocate/get/{allocateNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetInventoryAllocateResponse get(@PathVariable String allocateNumber) {
        return inventoryAllocateRestService.get(allocateNumber);
    }
    
    @RequestMapping(value = "/pda/inventoryallocate/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody AddInventoryAllocateRequest request) {
        return inventoryAllocateRestService.add(request);
    }

}
