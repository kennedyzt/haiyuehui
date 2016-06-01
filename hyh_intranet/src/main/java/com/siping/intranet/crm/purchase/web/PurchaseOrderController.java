package com.siping.intranet.crm.purchase.web;

import java.util.Iterator;
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
import com.siping.intranet.crm.purchase.service.PurchaseApplyOrderService;
import com.siping.intranet.crm.purchase.service.PurchaseOrderService;
import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.purchase.response.PurchaseApplyOrderResponse;
import com.siping.smartone.purchase.response.PurchaseOrderItemResponse;
import com.siping.smartone.purchase.response.PurchaseOrderResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
@LoginRequired
public class PurchaseOrderController extends StromaController {

    @Autowired
    PurchaseOrderService purchaseOrderService;
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
    PurchaseApplyOrderService paoService;

    @RequestMapping(value = "/purchaseorder/add", method = RequestMethod.GET)
    public String newPurchaseOrder(Map<String, Object> model) {
        List<StorageResponse> storages = storageService.getList();
        String loginUserName = sessionContext.get(SecuritySessionConstants.LOGGED_USER_NAME);
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        String purchaseOrderNumber = commonBillsNumberService.getGenerateBillsNumber("purchase_order", "order_number");
        model.put("purchaseOrderNumber", purchaseOrderNumber);
        model.put("storages", storages);
        model.put("loginId", loginId);
        model.put("loginUserName", loginUserName);
        return PagePath.ADD_PO;
    }

    @RequestMapping(value = "/purchaseorder/edit", method = RequestMethod.GET)
    public String editPurchaseOrder(@RequestParam("orderNumber") String orderNumber, Map<String, Object> model) {
        try {
            String loginUserName = sessionContext.get(SecuritySessionConstants.LOGGED_USER_NAME);
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            List<StorageResponse> storages = storageService.getList();
            PurchaseOrderResponse response = purchaseOrderService.get(orderNumber);
            model.put("purchaseOrder", response);
            model.put("storages", storages);
            model.put("loginId", loginId);
            model.put("loginUserName", loginUserName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PagePath.EDIT_PO;
    }

    // @RequestMapping(value = "/purchaseorder/print", method =
    // RequestMethod.GET)
    // public String printPurchaseOrder(@RequestParam("orderNumber") String
    // orderNumber, Map<String, Object> model) {
    // try {
    // List<StorageResponse> storages = storageService.getList();
    // PurchaseOrderResponse response = purchaseOrderService.get(orderNumber);
    // model.put("purchaseOrder", response);
    // model.put("storages", storages);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return PagePath.EDIT_PO;
    // }
    //
    @RequestMapping(value = "/purchaseorder/get", method = RequestMethod.GET)
    public String get(@RequestParam("orderNumber") String orderNumber, Map<String, Object> model) {
        try {
            PurchaseOrderResponse response = purchaseOrderService.get(orderNumber);
            model.put("purchaseOrder", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PagePath.DETAIL_PO;
    }

    @RequestMapping(value = "/purchaseorder/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getPurchaseOrderList(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                       CommonBillsRequest request, Map<String, Object> model) {
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
            if (null == request.getIsDraft() || !request.getIsDraft()) {
                request.setIsDraft(false);
                model.put("isDraft", false);
            } else {
                model.put("isDraft", true);
            }
            if (null == request.getStartDate())
                request.setStatus(0);
            PagingResponse<PurchaseOrderResponse> response = new PagingResponse<PurchaseOrderResponse>();
            response = purchaseOrderService.getList(request);
            PageModel<PurchaseOrderResponse> pageModel = new PageModel<PurchaseOrderResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("pageModel", pageModel);
            model.put("form", request);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_PO;
    }

    @RequestMapping(value = "/purchaseorder/getdraftlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getDraftApplyPOs(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo, CommonBillsRequest request,
                                   Map<String, Object> model) {
        request.setIsDraft(true);
        getPurchaseOrderList(pageSize, pageNo, request, model);
        return PagePath.ALL_PO;
    }

    @RequestMapping(value = "/purchaseorder/print", method = RequestMethod.GET)
    public String getPrint(@RequestParam("orderNumber") String orderNumber, Map<String, Object> model) {
        try {
            PurchaseOrderResponse response = purchaseOrderService.get(orderNumber);
            model.put("purchaseOrder", response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return PagePath.PRINT_PO;
    }

    @RequestMapping(value = "/purchaseorder/copyfrom", method = { RequestMethod.POST, RequestMethod.GET })
    public String copyFrom(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo, CommonBillsRequest request,
                           Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.APPLY_PO_SEARCH_CONDITION)))
                request = jsonConverter.fromString(CommonBillsRequest.class, sessionContext.get(SessionConstants.APPLY_PO_SEARCH_CONDITION));
            else {
                sessionContext.set(SessionConstants.APPLY_PO_SEARCH_CONDITION, jsonConverter.toString(request));
                pageNo = 1;
                pageSize = 10;
            }
            request.setPageNo(pageNo);
            request.setPageSize(pageSize);
            if (null == request.getIsDraft()) {
                request.setIsDraft(false);
            }
            PagingResponse<PurchaseApplyOrderResponse> response = new PagingResponse<PurchaseApplyOrderResponse>();
            response = paoService.getAllApplyPO(request);
            PageModel<PurchaseApplyOrderResponse> pageModel = new PageModel<PurchaseApplyOrderResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("pageModel", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.LAYER_CHOOSE_APPLAY;
    }

    @RequestMapping(value = "/purchaseorder/copyto", method = RequestMethod.GET)
    public String copyTo(@RequestParam("orderNumber") String orderNumber, Map<String, Object> model) {
        String receiptNumber = commonBillsNumberService.getGenerateBillsNumber("purchase_receipt", "receipt_number");
        List<StorageResponse> storages = storageService.getList();
        String loginUserName = sessionContext.get(SecuritySessionConstants.LOGGED_USER_NAME);
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        PurchaseOrderResponse response = purchaseOrderService.get(orderNumber);
        // 排除引用完了的项
        Iterator<PurchaseOrderItemResponse> iter = response.getItems().iterator();
        while (iter.hasNext()) {
            PurchaseOrderItemResponse p = iter.next();
            if (p.getNotReferencedAmount() == 0)
                iter.remove();
        }
        model.put("storages", storages);
        model.put("purchaseOrder", response);
        model.put("loginId", loginId);
        model.put("loginUserName", loginUserName);
        model.put("receiptNumber", receiptNumber);
        return PagePath.ADD_RECEIPT_PO;
    }
}
