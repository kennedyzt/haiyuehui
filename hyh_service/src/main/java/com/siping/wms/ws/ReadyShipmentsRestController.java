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

import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.wms.request.AddPackRequest;
import com.siping.smartone.wms.request.ESaleOrderRequest;
import com.siping.smartone.wms.request.OrderPickQueryParam;
import com.siping.smartone.wms.request.ReadyShipmentsRequest;
import com.siping.smartone.wms.response.OrderPickResponse;
import com.siping.smartone.wms.response.ReadyShipmentsResponse;
import com.siping.wms.service.ReadyShipmentsRestService;

@Controller
public class ReadyShipmentsRestController extends StromaWebserviceController {
    @Autowired
    private ReadyShipmentsRestService readyShipmentsRestService;

    @RequestMapping(value = "/readyshipments/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody ESaleOrderRequest request) {
        return readyShipmentsRestService.add(request);
    }

    @RequestMapping(value = "/readyshipments/get/{ordernumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ReadyShipmentsResponse get(@PathVariable String ordernumber) {
        return readyShipmentsRestService.get(ordernumber);
    }

    @RequestMapping(value = "/readyshipments/getbyTempAudit/{orderNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ReadyShipmentsResponse getbyTempAudit(@PathVariable String orderNumber) {
        return readyShipmentsRestService.getbyTempAudit(orderNumber);
    }

    @RequestMapping(value = "/readyshipments/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<ReadyShipmentsResponse> getList(@RequestBody CommonBillsRequest request) {
        return readyShipmentsRestService.getList(request);
    }

    @RequestMapping(value = "/readyshipments/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OrderPickResponse get(@RequestBody OrderPickQueryParam request) {
        return readyShipmentsRestService.get(request);
    }

    @RequestMapping(value = "/readyshipments/confirmAudit/{ordernumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean confirmAuditByOrderNumber(@PathVariable String ordernumber) {
        return readyShipmentsRestService.confirmAuditByOrderNumber(ordernumber);
    }

    @RequestMapping(value = "/readyshipments/tempSaveAudit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean tempSaveAudit(@RequestBody ReadyShipmentsRequest request) {
        return readyShipmentsRestService.tempSaveAudit(request);
    }

    @RequestMapping(value = "/readyshipments/savePack", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean savePack(@RequestBody AddPackRequest request) {
        return readyShipmentsRestService.savePack(request);
    }

    @RequestMapping(value = "/readyshipments/getAllTempAuditOrder", method = RequestMethod.POST)
    @ResponseBody
    public List<ReadyShipmentsResponse> getAllTempAuditOrder() {
        return readyShipmentsRestService.getAllTempAuditOrder();
    }

    @RequestMapping(value = "/readyshipments/printems", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<ReadyShipmentsResponse> printEMS(@RequestBody CommonBillsRequest request) {
        return readyShipmentsRestService.printEMS(request);
    }

    @RequestMapping(value = "/readyshipments/updatestatus/{id}/{status}/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean updateStatus(@PathVariable String id, @PathVariable Integer status, @PathVariable Integer userId) {
        return readyShipmentsRestService.updateStatus(id, status, userId);
    }
}
