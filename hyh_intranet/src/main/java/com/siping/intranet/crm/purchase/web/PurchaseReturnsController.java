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
import com.siping.intranet.crm.purchase.service.PurchaseReceiptService;
import com.siping.intranet.crm.purchase.service.PurchaseReturnsService;
import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.purchase.request.PurchaseReturnOrderForm;
import com.siping.smartone.purchase.response.PurchaseReceiptResponse;
import com.siping.smartone.purchase.response.PurchaseReturnsResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
@LoginRequired
public class PurchaseReturnsController extends StromaController {
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private StorageService storageService;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private PurchaseReturnsService purchaseReturnsService;
    @Autowired
    private CommonBillsNumberService commonBillsNumberService;
    @Autowired
    private PurchaseReceiptService purchaseReceiptService;

    @RequestMapping(value = "/purchaseorderreturn/add", method = RequestMethod.GET)
    public String newPurchaseReturnOrder(Map<String, Object> model) {
        List<StorageResponse> storages = storageService.getList();
        String loginUserName = sessionContext.get(SecuritySessionConstants.LOGGED_USER_NAME);
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        String returnsNumber = commonBillsNumberService.getGenerateBillsNumber("purchase_returns", "returns_number");
        model.put("returnsNumber", returnsNumber);
        model.put("storages", storages);
        model.put("loginUserName", loginUserName);
        model.put("loginId", loginId);
        return PagePath.ADD_RETURN_PO;
    }

    @RequestMapping(value = "/purchaseorderreturn/edit", method = RequestMethod.GET)
    public String editPurchaseReturnOrder(@RequestParam("returnsNumber") String returnsNumber, Map<String, Object> model) {
        List<StorageResponse> storages = storageService.getList();
        String loginUserName = sessionContext.get(SecuritySessionConstants.LOGGED_USER_NAME);
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        model.put("storages", storages);
        model.put("loginUserName", loginUserName);
        model.put("loginId", loginId);
        PurchaseReturnsResponse response = purchaseReturnsService.get(returnsNumber);
        model.put("purchaseReturns", response);
        return PagePath.EDIT_RETURN_PO;
    }

    @RequestMapping(value = "/purchaseorderreturn/get", method = RequestMethod.GET)
    public String getPurchaseReturnOrder(@RequestParam("returnsNumber") String returnsNumber, Map<String, Object> model) {
        PurchaseReturnsResponse response = purchaseReturnsService.get(returnsNumber);
        model.put("purchaseReturns", response);
        return PagePath.DETAIL_RETURN_PO;
    }

    @RequestMapping(value = "/purchaseorderreturn/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getlist(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo, PurchaseReturnOrderForm request,
                          Map<String, Object> model) {
        request.setIsDraft(false);
        buildPurchaseReturnResponse(pageSize, pageNo, request, model);
        return PagePath.ALL_RETURN_PO;
    }

    @RequestMapping(value = "/purchaseorderreturn/getdraftlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getdraftlist(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo, PurchaseReturnOrderForm request,
                               Map<String, Object> model) {
        request.setIsDraft(true);
        buildPurchaseReturnResponse(pageSize, pageNo, request, model);
        return PagePath.ALL_DRAFT_RETURN_PO;
    }

    private void buildPurchaseReturnResponse(Integer pageSize, Integer pageNo, PurchaseReturnOrderForm request, Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.PURCHASE_RETURN_ORDERFORM_SEARCH_CONDITION)))
                request = jsonConverter.fromString(PurchaseReturnOrderForm.class, sessionContext.get(SessionConstants.PURCHASE_RETURN_ORDERFORM_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.PURCHASE_RETURN_ORDERFORM_SEARCH_CONDITION, jsonConverter.toString(request));

            PagingResponse<PurchaseReturnsResponse> pagingResponse = new PagingResponse<PurchaseReturnsResponse>();
            pagingResponse = purchaseReturnsService.getList(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, request);
            PageModel<PurchaseReturnsResponse> pageModel = new PageModel<PurchaseReturnsResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, pagingResponse.getTotalRecords(),
                pagingResponse.getRecords());
            model.put("pageModel", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
    }

    @RequestMapping(value = "/purchaseorderreturn/print", method = RequestMethod.GET)
    public String getPrint() {
        return PagePath.PRINT_RETURN_PO;
    }
    @RequestMapping(value = "/purchaseorderreturn/copyfrom", method = { RequestMethod.POST, RequestMethod.GET })
    public String copyFrom(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo,
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
            PagingResponse<PurchaseReceiptResponse> response = purchaseReceiptService.getList(request);
            PageModel<PurchaseReceiptResponse> pageModel = new PageModel<PurchaseReceiptResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("pageModel", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.LAYER_CHOOSE_RECEIPT;
    }
}
