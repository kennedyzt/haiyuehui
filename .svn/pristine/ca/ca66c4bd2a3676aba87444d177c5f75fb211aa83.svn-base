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

import com.siping.service.report.service.MaterialSaleReportRestService;
import com.siping.smartone.report.request.MaterialSaleReportRequest;
import com.siping.smartone.report.request.MaterialWarningRequest;
import com.siping.smartone.report.request.SaleAnalysisReportRequest;
import com.siping.smartone.report.response.MaterialSaleReportResponse;
import com.siping.smartone.report.response.MaterialWarningResponse;
import com.siping.smartone.report.response.SaleAnalysisReportResponse;

@Controller
public class MaterialSaleReportRestController extends StromaWebserviceController {

    @Autowired
    private MaterialSaleReportRestService materialSaleReportRestService;

    @RequestMapping(value = "/report/materialsalereport/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<MaterialSaleReportResponse> getList(@RequestBody MaterialSaleReportRequest request) {
        return materialSaleReportRestService.getList(request);
    }
    
    @RequestMapping(value = "/report/saleanalysisreport/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<SaleAnalysisReportResponse> getAnalysisList(@RequestBody SaleAnalysisReportRequest request) {
        return materialSaleReportRestService.getAnalysisList(request);
    }
    
    @RequestMapping(value = "/report/materialwarningreport/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<MaterialWarningResponse> getMaterialWaringList(@RequestBody MaterialWarningRequest request) {
        return materialSaleReportRestService.getMaterialWaringList(request);
    }
}
