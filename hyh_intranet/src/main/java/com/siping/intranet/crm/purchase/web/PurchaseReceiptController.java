package com.siping.intranet.crm.purchase.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.SessionConstants;
import com.siping.intranet.common.service.CommonBillsNumberService;
import com.siping.intranet.crm.purchase.service.PurchaseOrderService;
import com.siping.intranet.crm.purchase.service.PurchaseReceiptService;
import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.purchase.response.PurchaseOrderResponse;
import com.siping.smartone.purchase.response.PurchaseReceiptResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
@LoginRequired
public class PurchaseReceiptController extends StromaController {
    @Autowired
    private PurchaseReceiptService purchaseReceiptService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private StorageService storageService;
    @Autowired
    private CommonBillsNumberService commonBillsNumberService;
    @Autowired
    PurchaseOrderService purchaseOrderService;

    @RequestMapping(value = "/purchaseorderreceipt/add", method = RequestMethod.GET)
    public String newPurchaseReceiptOrder(Map<String, Object> model) {
        List<StorageResponse> storages = storageService.getList();
        String loginUserName = sessionContext.get(SecuritySessionConstants.LOGGED_USER_NAME);
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        String receiptNumber = commonBillsNumberService.getGenerateBillsNumber("purchase_receipt", "receipt_number");
        model.put("receiptNumber", receiptNumber);
        model.put("storages", storages);
        model.put("loginId", loginId);
        model.put("loginUserName", loginUserName);
        return PagePath.ADD_RECEIPT_PO;
    }

    @RequestMapping(value = "/purchaseorderreceipt/edit", method = RequestMethod.GET)
    public String editPurchaseApplyOrder(@RequestParam("receiptNumber") String receiptNumber, Map<String, Object> model) {
        try {
            List<StorageResponse> storages = storageService.getList();
            PurchaseReceiptResponse response = purchaseReceiptService.get(receiptNumber);
            String loginUserName = sessionContext.get(SecuritySessionConstants.LOGGED_USER_NAME);
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            model.put("purchaseReceipt", response);
            model.put("storages", storages);
            model.put("loginId", loginId);
            model.put("loginUserName", loginUserName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PagePath.EDIT_RECEIPT_PO;
    }

    @RequestMapping(value = "/purchaseorderreceipt/get", method = RequestMethod.GET)
    public String get(@RequestParam("receiptNumber") String receiptNumber, Map<String, Object> model) {
        try {
            PurchaseReceiptResponse response = purchaseReceiptService.get(receiptNumber);
            model.put("purchaseReceipt", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PagePath.DETAIL_RECEIPT_PO;
    }

    @RequestMapping(value = "/purchaseorderreceipt/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getPurchaseReceiptList(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                         CommonBillsRequest request, Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.PURCHASE_RECEIPT_SEARCH_CONDITION)))
                request = jsonConverter.fromString(CommonBillsRequest.class, sessionContext.get(SessionConstants.PURCHASE_RECEIPT_SEARCH_CONDITION));
            else {
                sessionContext.set(SessionConstants.PURCHASE_RECEIPT_SEARCH_CONDITION, jsonConverter.toString(request));
                pageNo = 1;
                pageSize = 10;
            }
            request.setPageNo(pageNo);
            request.setPageSize(pageSize);
            if (null == request.getIsDraft() || !request.getIsDraft()) {
                request.setIsDraft(false);
                model.put("isDraft", false);
            } else {
                model.put("isDraft", true);
            }
            PagingResponse<PurchaseReceiptResponse> response = purchaseReceiptService.getList(request);
            PageModel<PurchaseReceiptResponse> pageModel = new PageModel<PurchaseReceiptResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("pageModel", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_RECEIPT_PO;
    }

    @RequestMapping(value = "/purchaseorderreceipt/getdraftlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getDraftPurchaseReceiptList(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                              CommonBillsRequest request, Map<String, Object> model) {
        request.setIsDraft(true);
        getPurchaseReceiptList(pageSize, pageNo, request, model);
        return PagePath.ALL_RECEIPT_PO;
    }

    @RequestMapping(value = "/purchaseorderreceipt/print", method = RequestMethod.GET)
    public String getPrint(@RequestParam("orderNumber") String orderNumber, Map<String, Object> model) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return PagePath.PRINT_RECEIPT_PO;
    }

    @RequestMapping(value = "/purchaseorderreceipt/copyfrom", method = { RequestMethod.POST, RequestMethod.GET })
    public String copyFrom(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo, CommonBillsRequest request,
                           Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.PO_SEARCH_CONDITION)))
                request = jsonConverter.fromString(CommonBillsRequest.class, sessionContext.get(SessionConstants.PO_SEARCH_CONDITION));
            else {
                sessionContext.set(SessionConstants.PO_SEARCH_CONDITION, jsonConverter.toString(request));
                pageNo = 1;
                pageSize = 10;
            }
            request.setPageNo(pageNo);
            request.setPageSize(pageSize);
            PagingResponse<PurchaseOrderResponse> response = new PagingResponse<PurchaseOrderResponse>();
            request.setStatus(3);
            request.setIsDraft(false);
            request.setReadytatus(0);
            response = purchaseOrderService.getList(request);
            PageModel<PurchaseOrderResponse> pageModel = new PageModel<PurchaseOrderResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("pageModel", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.LAYER_CHOOSE_ORDER;
    }

    @RequestMapping(value = "/purchaseorderreceipt/copyto", method = RequestMethod.GET)
    public String copyTo(@RequestParam("orderNumber") String orderNumber, Map<String, Object> model) {
        String returnsNumber = commonBillsNumberService.getGenerateBillsNumber("purchase_returns", "returns_number");
        List<StorageResponse> storages = storageService.getList();
        String loginUserName = sessionContext.get(SecuritySessionConstants.LOGGED_USER_NAME);
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        PurchaseReceiptResponse response = purchaseReceiptService.get(orderNumber);
        model.put("storages", storages);
        model.put("purchaseReceiptOrder", response);
        model.put("loginId", loginId);
        model.put("loginUserName", loginUserName);
        model.put("returnsNumber", returnsNumber);
        return PagePath.ADD_RETURN_PO;
    }
}
