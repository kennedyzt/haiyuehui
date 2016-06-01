package com.siping.service.report.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.report.service.SellReportRestService;
import com.siping.smartone.report.request.CustomerSaleRequest;
import com.siping.smartone.report.request.GetSalesRrequest;
import com.siping.smartone.report.response.CustomerSaleResponse;
import com.siping.smartone.report.response.GetSalesReportWithExtraResponse;
import com.siping.smartone.report.response.GetSalesResponse;

@Controller
public class SellReportRestController extends StromaWebserviceController {
    @Autowired
    private SellReportRestService sellReportRestService;

    @RequestMapping(value = "/sellreport/getsellordercount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetSalesResponse getSellOrderCount(@RequestBody GetSalesRrequest request) {
        return sellReportRestService.getSellOrderCount(request);
    }

    @RequestMapping(value = "/sellreport/getsaleroom", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetSalesResponse getSaleroom(@RequestBody GetSalesRrequest request) {
        return sellReportRestService.getSaleroom(request);
    }

    @RequestMapping(value = "/sellreport/getsaleroomwithextra", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetSalesReportWithExtraResponse getSaleroomWithExtra(@RequestBody GetSalesRrequest request) {
        return sellReportRestService.getSaleroomWithExtra(request);
    }
    
    @RequestMapping(value = "/report/rest/customersale/getdata", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<CustomerSaleResponse> getCustomerSaleData(@RequestBody CustomerSaleRequest request) {
        return sellReportRestService.getCustomerSaleData(request);
    }
}
