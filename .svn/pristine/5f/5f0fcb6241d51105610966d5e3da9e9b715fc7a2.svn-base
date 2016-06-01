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

import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.wms.request.OrderPickQueryParam;
import com.siping.smartone.wms.response.OrderPickResponse;
import com.siping.wms.service.OrderPickRestService;

@Controller
public class OrderPickRestController extends StromaWebserviceController {
    @Autowired
    private OrderPickRestService orderPickRestService;

    @RequestMapping(value = "/orderpick/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody OrderPickQueryParam request) {
        return orderPickRestService.add(request);
    }

    @RequestMapping(value = "/orderpick/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<OrderPickResponse> getList(@RequestBody CommonBillsRequest request) {
        return orderPickRestService.getList(request);
    }

    @RequestMapping(value = "/orderpick/get/{ordernumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OrderPickResponse get(@PathVariable String ordernumber) {
        return orderPickRestService.get(ordernumber);
    }

    @RequestMapping(value = "/orderpick/updatestatus/{orderNumber}/{loginId}/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean updateStatus(@PathVariable String orderNumber, @PathVariable Integer loginId, @PathVariable Integer status) {
        return orderPickRestService.updateStatus(orderNumber, loginId, status);
    }
}
