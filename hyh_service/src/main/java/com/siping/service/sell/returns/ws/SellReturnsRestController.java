package com.siping.service.sell.returns.ws;

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

import com.siping.service.sell.returns.service.SellReturnsRestService;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.invoicing.sell.returns.request.AddSellReturnsRequest;
import com.siping.smartone.invoicing.sell.returns.request.DeleteSellReturnsRequest;
import com.siping.smartone.invoicing.sell.returns.response.GetSellReturnsListResponse;
import com.siping.smartone.invoicing.sell.returns.response.GetSellReturnsResponse;

@Controller
public class SellReturnsRestController extends StromaWebserviceController {
    @Autowired
    private SellReturnsRestService sellReturnsRestService;

    @RequestMapping(value = "/sellreturns/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody AddSellReturnsRequest request) {
        return sellReturnsRestService.add(request);
    }

    @RequestMapping(value = "/sellreturns/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody AddSellReturnsRequest request) {
        return sellReturnsRestService.edit(request);
    }

    @RequestMapping(value = "/sellreturns/get/{returnsnumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetSellReturnsResponse get(@PathVariable String returnsnumber) {
        return sellReturnsRestService.get(returnsnumber);
    }

    @RequestMapping(value = "/sellreturns/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetSellReturnsListResponse> getList(@RequestBody CommonBillsRequest request) {
        return sellReturnsRestService.getList(request);
    }

    @RequestMapping(value = "/sellreturns/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody DeleteSellReturnsRequest request) {
        return sellReturnsRestService.delete(request);
    }
}
