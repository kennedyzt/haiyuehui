package com.siping.intranet.report.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;

import com.siping.intranet.report.service.BusinessSalesReportService;
import com.siping.intranet.report.service.MaterialSaleReportService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.report.CommonReportRequest;
import com.siping.smartone.report.request.MaterialSaleReportRequest;
import com.siping.smartone.report.request.MaterialWarningRequest;
import com.siping.smartone.report.request.SaleAnalysisReportRequest;
import com.siping.smartone.report.response.BusinessSalesReportResponse;
import com.siping.smartone.report.response.MaterialSaleReportResponse;
import com.siping.smartone.report.response.MaterialWarningResponse;
import com.siping.smartone.report.response.SaleAnalysisReportResponse;

@Controller
@LoginRequired
class MaterialSaleReportController extends StromaController {
    @Autowired
    MaterialSaleReportService materialSaleReportService;

    @RequestMapping(value = "report/materialsalereport", method = RequestMethod.GET)
    public String materialSale(Map<String, Object> model) {
        return PagePath.MATERIAL_SALE_REPORT;
    }

    @RequestMapping(value = "report/saleanalysisreport", method = RequestMethod.GET)
    public String saleAnalysis(@RequestParam(value = "startDate", required = false, defaultValue = "") String startDate,
                               @RequestParam(value = "endDate", required = false, defaultValue = "") String endDate,
                               @RequestParam(value = "materialNo", required = false, defaultValue = "") String materialNo,
                               @RequestParam(value = "materialName", required = false, defaultValue = "") String materialName,
                               @RequestParam(value = "partnerNo", required = false, defaultValue = "") String partnerNo,
                               @RequestParam(value = "partnerName", required = false, defaultValue = "") String partnerName,
                               @RequestParam(value = "franchiseeNo", required = false, defaultValue = "") String franchiseeNo,
                               @RequestParam(value = "franchiseeName", required = false, defaultValue = "") String franchiseeName,
                               @RequestParam(value = "shopNo", required = false, defaultValue = "") String shopNo,
                               @RequestParam(value = "shopName", required = false, defaultValue = "") String shopName, ModelMap map) {
        map.put("materialNo", materialNo);
        map.put("materialName", materialName);
        map.put("partnerNo", partnerNo);
        map.put("partnerName", partnerName);
        map.put("franchiseeNo", franchiseeNo);
        map.put("franchiseeName", franchiseeName);
        map.put("shopNo", shopNo);
        map.put("shopName", shopName);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        return PagePath.SALE_ANALYSIS_REPORT;
    }

    @RequestMapping(value = "report/materialsalereport/getlist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageModel<MaterialSaleReportResponse> getList(@RequestBody MaterialSaleReportRequest request, Map<String, Object> model) {
        PagingResponse<MaterialSaleReportResponse> response = new PagingResponse<MaterialSaleReportResponse>();
        PageModel<MaterialSaleReportResponse> pageModel = null;
        try {
            response = materialSaleReportService.getList(request);
            if (request.getIsPaging()) {
                pageModel = new PageModel<MaterialSaleReportResponse>(request.getPageNo(), request.getPageSize(), response.getTotalRecords(), response.getRecords(), response.getStats());
            } else {
                pageModel = new PageModel<MaterialSaleReportResponse>(1, 100, response.getTotalRecords(), response.getRecords(), response.getStats());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageModel;
    }

    @RequestMapping(value = "report/saleanalysisreport/getlist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageModel<SaleAnalysisReportResponse> getsaleAnalysisList(@RequestBody SaleAnalysisReportRequest request, Map<String, Object> model) {
        PagingResponse<SaleAnalysisReportResponse> response = new PagingResponse<SaleAnalysisReportResponse>();
        PageModel<SaleAnalysisReportResponse> pageModel = null;
        try {
            response = materialSaleReportService.getsaleAnalysisList(request);
            if (request.getIsPaging()) {
                pageModel = new PageModel<SaleAnalysisReportResponse>(request.getPageNo(), request.getPageSize(), response.getTotalRecords(), response.getRecords(), response.getStats());
            } else {
                pageModel = new PageModel<SaleAnalysisReportResponse>(0, 100, response.getTotalRecords(), response.getRecords(), response.getStats());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageModel;
    }

    @RequestMapping(value = "/report/materialwarningreport", method = RequestMethod.GET)
    public String materialWarning(Map<String, Object> model) {
        return PagePath.MATERIAL_WARNING_REPORT;
    }

    @RequestMapping(value = "report/materialwarningreport/getlist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageModel<MaterialWarningResponse> getMaterialWarningList(@RequestBody MaterialWarningRequest request, Map<String, Object> model) {
        PagingResponse<MaterialWarningResponse> response = new PagingResponse<MaterialWarningResponse>();
        PageModel<MaterialWarningResponse> pageModel = null;
        try {
            response = materialSaleReportService.getMaterialWarningList(request);
            if (request.getIsPaging()) {
                pageModel = new PageModel<MaterialWarningResponse>(request.getPageNo(), request.getPageSize(), response.getTotalRecords(), response.getRecords(), response.getStats());
            } else {
                pageModel = new PageModel<MaterialWarningResponse>(1, 100, response.getTotalRecords(), response.getRecords(), response.getStats());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageModel;
    }
}
