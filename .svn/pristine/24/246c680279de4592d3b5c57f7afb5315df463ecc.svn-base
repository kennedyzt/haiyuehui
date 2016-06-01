package com.siping.intranet.report.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;

import com.siping.intranet.report.service.BusinessSalesReportService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.report.CommonReportRequest;
import com.siping.smartone.report.request.InWarehouseRequest;
import com.siping.smartone.report.request.OutwarehouseRequest;
import com.siping.smartone.report.response.BusinessSalesReportResponse;
import com.siping.smartone.report.response.InWarehouseResponse;
import com.siping.smartone.report.response.OutwarehouseResponse;

@Controller
@LoginRequired
class BusinessSalesReportController extends StromaController {
    @Autowired
    BusinessSalesReportService businessSalesReportService;

    @RequestMapping(value = "/report/businesssalesreport", method = RequestMethod.GET)
    public String supplierPurchase(Map<String, Object> model) {
        return PagePath.REPORT_BUSINESS_SALES;
    }

    @RequestMapping(value = "/report/businesssalesreport/getlist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageModel<BusinessSalesReportResponse> getList(@RequestBody CommonReportRequest request, Map<String, Object> model) {
        PagingResponse<BusinessSalesReportResponse> response = new PagingResponse<BusinessSalesReportResponse>();
        PageModel<BusinessSalesReportResponse> pageModel = null;
        try {
            response = businessSalesReportService.getList(request);
            if (request.getIsPaging() == true) {
                pageModel = new PageModel<BusinessSalesReportResponse>(request.getPageNo(), request.getPageSize(), response.getTotalRecords(), response.getRecords());
            } else {
                pageModel = new PageModel<BusinessSalesReportResponse>(1, 100, response.getTotalRecords(), response.getRecords());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageModel;
    }

    @RequestMapping(value = "/report/inwarehouse", method = RequestMethod.GET)
    public String inwarehouse() {
        return PagePath.IN_WAREHOUSE;
    }

    @RequestMapping(value = "/report/outwarehouse", method = RequestMethod.GET)
    public String outwarehouse() {
        return PagePath.OUT_WAREHOUSE;
    }

    @RequestMapping(value = "/report/inwarehouse/getdata", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageModel<InWarehouseResponse> getInwarehouse(@RequestBody InWarehouseRequest request) {
        PagingResponse<InWarehouseResponse> response = new PagingResponse<InWarehouseResponse>();
        PageModel<InWarehouseResponse> pageModel = null;
        try {
            response = businessSalesReportService.getInwarehouse(request);
            if (request.getIsPaging() == true) {
                pageModel = new PageModel<InWarehouseResponse>(request.getPageNo(), request.getPageSize(), response.getTotalRecords(), response.getRecords());
            } else {
                pageModel = new PageModel<InWarehouseResponse>(1, 100, response.getTotalRecords(), response.getRecords());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageModel;
    }

    @RequestMapping(value = "/report/outwarehouse/getdata", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageModel<OutwarehouseResponse> getOutwarehouse(@RequestBody OutwarehouseRequest request) {
        PagingResponse<OutwarehouseResponse> response = new PagingResponse<OutwarehouseResponse>();
        PageModel<OutwarehouseResponse> pageModel = null;
        try {
            response = businessSalesReportService.getOutwarehouse(request);
            if (request.getIsPaging() == true) {
                pageModel = new PageModel<OutwarehouseResponse>(request.getPageNo(), request.getPageSize(), response.getTotalRecords(), response.getRecords());
            } else {
                pageModel = new PageModel<OutwarehouseResponse>(1, 100, response.getTotalRecords(), response.getRecords());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageModel;
    }
}
