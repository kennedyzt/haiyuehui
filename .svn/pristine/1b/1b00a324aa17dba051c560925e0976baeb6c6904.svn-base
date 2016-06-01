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

import com.siping.service.report.service.PurchaseReportRestService;
import com.siping.smartone.report.request.ProductPurchaseRequest;
import com.siping.smartone.report.request.PurchaseAnalysisRequest;
import com.siping.smartone.report.request.PurchaseOrderNumRequest;
import com.siping.smartone.report.request.SupplierPurchaseRequest;
import com.siping.smartone.report.response.ProductPurchaseResponse;
import com.siping.smartone.report.response.PurchaseAnalysisResponse;
import com.siping.smartone.report.response.PurchaseOrderNumResponse;
import com.siping.smartone.report.response.SupplierPurchaseResponse;

@Controller
public class PurchaseReportRestController extends StromaWebserviceController {

    @Autowired
    private PurchaseReportRestService purchaseReportRestService;

    @RequestMapping(value = "/report/rest/supplierpurchase/getdata", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<SupplierPurchaseResponse> getSupplierPurchaseData(@RequestBody SupplierPurchaseRequest request) {
        return purchaseReportRestService.getSupplierPurchaseData(request);
    }

    @RequestMapping(value = "/report/rest/productpurchase/getdata", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<ProductPurchaseResponse> getProductPurchaseData(@RequestBody ProductPurchaseRequest request) {
        return purchaseReportRestService.getProductPurchaseData(request);
    }

    @RequestMapping(value = "/report/rest/purchaseanalysis/getdata", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<PurchaseAnalysisResponse> getPurchaseAnalysisData(@RequestBody PurchaseAnalysisRequest request) {
        return purchaseReportRestService.getPurchaseAnalysisData(request);
    }

    @RequestMapping(value = "/report/rest/purchaseordernum/getdata", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<PurchaseOrderNumResponse> getPurchaseOrderNumData(@RequestBody PurchaseOrderNumRequest request) {
        return purchaseReportRestService.getPurchaseOrderNumData(request);
    }
}
