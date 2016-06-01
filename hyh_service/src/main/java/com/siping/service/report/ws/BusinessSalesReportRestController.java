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

import com.siping.service.report.service.BusinessSalesReportRestService;
import com.siping.smartone.report.CommonReportRequest;
import com.siping.smartone.report.request.InWarehouseRequest;
import com.siping.smartone.report.request.OutwarehouseRequest;
import com.siping.smartone.report.response.BusinessSalesReportResponse;
import com.siping.smartone.report.response.InWarehouseResponse;
import com.siping.smartone.report.response.OutwarehouseResponse;

@Controller
public class BusinessSalesReportRestController extends StromaWebserviceController {

    @Autowired
    private BusinessSalesReportRestService businessSalesReportRestService;

    @RequestMapping(value = "/report/businesssalesreport/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<BusinessSalesReportResponse> getList(@RequestBody CommonReportRequest request) {
        return businessSalesReportRestService.getList(request);
    }

    @RequestMapping(value = "/report/rest/inwarehouse/getdata", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<InWarehouseResponse> getInwarehouse(@RequestBody InWarehouseRequest request) {
        return businessSalesReportRestService.getInwarehouse(request);
    }

    @RequestMapping(value = "/report/rest/outwarehouse/getdata", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<OutwarehouseResponse> getOutwarehouse(@RequestBody OutwarehouseRequest request) {
        return businessSalesReportRestService.getOutwarehouse(request);
    }
}
