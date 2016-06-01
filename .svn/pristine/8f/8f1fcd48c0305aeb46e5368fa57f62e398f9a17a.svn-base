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

import com.siping.intranet.report.service.PurchaseReportService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.report.request.ProductPurchaseRequest;
import com.siping.smartone.report.request.PurchaseAnalysisRequest;
import com.siping.smartone.report.request.PurchaseOrderNumRequest;
import com.siping.smartone.report.request.SupplierPurchaseRequest;
import com.siping.smartone.report.response.ProductPurchaseResponse;
import com.siping.smartone.report.response.PurchaseAnalysisResponse;
import com.siping.smartone.report.response.PurchaseOrderNumResponse;
import com.siping.smartone.report.response.SupplierPurchaseResponse;

@Controller
@LoginRequired
public class PurchaseReportController extends StromaController {

    @Autowired
    private PurchaseReportService purchaseReportService;

    @RequestMapping(value = "/report/supplierpurchase", method = RequestMethod.GET)
    public String supplierPurchase(Map<String, Object> model) {
        return PagePath.SUPPLIER_PURCHASE_ANALYSIS;
    }

    @RequestMapping(value = "/report/supplierpurchase/getdata", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageModel<SupplierPurchaseResponse> getSupplierPurchaseData(@RequestBody SupplierPurchaseRequest request) {
        PagingResponse<SupplierPurchaseResponse> response = new PagingResponse<SupplierPurchaseResponse>();
        response = purchaseReportService.getSupplierPurchaseData(request);
        PageModel<SupplierPurchaseResponse> pageModel = null;
        if (request.getIsPaging() == true) {
            pageModel = new PageModel<SupplierPurchaseResponse>(request.getPageNo(), request.getPageSize(), response.getTotalRecords(), response.getRecords(), response.getStats());
        } else {
            pageModel = new PageModel<SupplierPurchaseResponse>(1, 100, response.getTotalRecords(), response.getRecords(), response.getStats());
        }
        return pageModel;
    }

    @RequestMapping(value = "/report/productpurchase", method = RequestMethod.GET)
    public String productPurchase() {
        return PagePath.PRODUCT_PURCHASE_ANALYSIS;
    }

    @RequestMapping(value = "/report/productpurchase/getdata", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageModel<ProductPurchaseResponse> getProductPurchaseData(@RequestBody ProductPurchaseRequest request) {
        PagingResponse<ProductPurchaseResponse> response = new PagingResponse<ProductPurchaseResponse>();
        response = purchaseReportService.getProductPurchaseData(request);
        PageModel<ProductPurchaseResponse> pageModel = null;
        if (request.getIsPaging()) {
            pageModel = new PageModel<ProductPurchaseResponse>(request.getPageNo(), request.getPageSize(), response.getTotalRecords(), response.getRecords(), response.getStats());
        } else {
            pageModel = new PageModel<ProductPurchaseResponse>(1, 100, response.getTotalRecords(), response.getRecords(), response.getStats());
        }
        return pageModel;
    }

    @RequestMapping(value = "/report/purchaseanalysis", method = RequestMethod.GET)
    public String purchaseAnalysis(@RequestParam(value = "productNo", required = false, defaultValue = "") String productNo,
                                   @RequestParam(value = "supplierNo", required = false, defaultValue = "") String supplierNo,
                                   @RequestParam(value = "productName", required = false, defaultValue = "") String productName,
                                   @RequestParam(value = "supplierName", required = false, defaultValue = "") String supplierName, @RequestParam(value = "dateFrom", required = false) String dateFrom,
                                   @RequestParam(value = "dateTo", required = false) String dateTo, ModelMap map) {
        map.put("productNo", productNo);
        map.put("productName", productName);
        map.put("supplierNo", supplierNo);
        map.put("supplierName", supplierName);
        map.put("dateFrom", dateFrom);
        map.put("dateTo", dateTo);
        return PagePath.PURCHASE_ANALYSIS;
    }

    @RequestMapping(value = "/report/purchaseanalysis/getdata", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageModel<PurchaseAnalysisResponse> getPurchaseAnalysisData(@RequestBody PurchaseAnalysisRequest request) {
        PagingResponse<PurchaseAnalysisResponse> response = new PagingResponse<PurchaseAnalysisResponse>();
        response = purchaseReportService.getPurchaseAnalysisData(request);
        PageModel<PurchaseAnalysisResponse> pageModel = null;
        if (request.getIsPaging()) {
            pageModel = new PageModel<PurchaseAnalysisResponse>(request.getPageNo(), request.getPageSize(), response.getTotalRecords(), response.getRecords(), response.getStats());
        } else {
            pageModel = new PageModel<PurchaseAnalysisResponse>(1, 100, response.getTotalRecords(), response.getRecords(), response.getStats());
        }
        return pageModel;
    }

    @RequestMapping(value = "/report/purchaseordernum", method = RequestMethod.GET)
    public String purchaseOrder() {
        return PagePath.PURCHASE_ORDER_STATISTIC;
    }

    @RequestMapping(value = "/report/purchaseordernum/getdata", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageModel<PurchaseOrderNumResponse> getPurchaseOrderNumData(@RequestBody PurchaseOrderNumRequest request) {
        PagingResponse<PurchaseOrderNumResponse> response = new PagingResponse<PurchaseOrderNumResponse>();
        try {
            response = purchaseReportService.getPurchaseOrderNumData(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageModel<PurchaseOrderNumResponse> pageModel = null;
        if (request.getIsPaging()) {
            pageModel = new PageModel<PurchaseOrderNumResponse>(request.getPageNo(), request.getPageSize(), response.getTotalRecords(), response.getRecords(), response.getStats());
        } else {
            pageModel = new PageModel<PurchaseOrderNumResponse>(1, 100, response.getTotalRecords(), response.getRecords(), response.getStats());
        }
        return pageModel;
    }
}
