package com.siping.service.report.ws;

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

import com.siping.service.report.service.InventoryReportRestService;
import com.siping.smartone.inventory.request.GetInventoryBalanceRequest;
import com.siping.smartone.inventory.request.GetInventoryBatchRequest;
import com.siping.smartone.inventory.request.GetInventoryStatusRequest;
import com.siping.smartone.inventory.response.GetInventoryBalanceDetailResponse;
import com.siping.smartone.inventory.response.GetInventoryBalanceResponse;
import com.siping.smartone.inventory.response.GetInventoryBatchDetailResponse;
import com.siping.smartone.inventory.response.GetInventoryBatchResponse;
import com.siping.smartone.inventory.response.GetInventoryStatusDetailResponse;
import com.siping.smartone.inventory.response.GetInventoryStatusResponse;
import com.siping.smartone.report.response.SingleMaterialCostResponse;

@Controller
public class InventoryReportRestController extends StromaWebserviceController {
    @Autowired
    private InventoryReportRestService inventoryReportRestService;

    @RequestMapping(value = "/inventorybalance/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetInventoryBalanceResponse> get(@RequestBody GetInventoryBalanceRequest request) {
        return inventoryReportRestService.getList(request);
    }

    @RequestMapping(value = "/inventorybalance/getDetaillist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetInventoryBalanceDetailResponse> getDetaillist(@RequestBody GetInventoryBalanceRequest request) {
        return inventoryReportRestService.getDetaillist(request);
    }

    @RequestMapping(value = "/reportinventorystatus/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetInventoryStatusResponse> getStatusList(@RequestBody GetInventoryStatusRequest request) {
        return inventoryReportRestService.getStatusList(request);
    }

    @RequestMapping(value = "/reportinventorystatus/detail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetInventoryStatusDetailResponse> getStatusDetailList(@RequestBody GetInventoryStatusRequest req) {
        return inventoryReportRestService.getStatusDetailList(req);
    }

    @RequestMapping(value = "/reportinventorybatch/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetInventoryBatchResponse> getBatchList(@RequestBody GetInventoryBatchRequest request) {
        return inventoryReportRestService.getBatchList(request);
    }

    @RequestMapping(value = "/reportinventorybatch/detail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetInventoryBatchDetailResponse> getBatchDetailList(@RequestBody GetInventoryBatchRequest req) {
        return inventoryReportRestService.getBatchDetailList(req);
    }

    @RequestMapping(value = "/inventorybalance/getcost/{materialid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<SingleMaterialCostResponse> getCost(@PathVariable String materialid) {
        return inventoryReportRestService.getCost(materialid);
    }
}
