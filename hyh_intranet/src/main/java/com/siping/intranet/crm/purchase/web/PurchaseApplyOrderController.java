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
import com.siping.intranet.crm.purchase.service.PurchaseApplyOrderService;
import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.purchase.response.PurchaseApplyOrderResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
@LoginRequired
public class PurchaseApplyOrderController extends StromaController {

    @Autowired
    PurchaseApplyOrderService paoService;
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

    @RequestMapping(value = "/purchaseapplyorder/add", method = RequestMethod.GET)
    public String newPurchaseApplyOrder(Map<String, Object> model) {
        String purchaseOrderNumber = commonBillsNumberService.getGenerateBillsNumber("purchase_apply_order", "order_number");
        model.put("purchaseOrderNumber", purchaseOrderNumber);
        String loginUserName = sessionContext.get(SecuritySessionConstants.LOGGED_USER_NAME);
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        model.put("loginId", loginId);
        model.put("loginUserName", loginUserName);
        return PagePath.ADD_APPLY_PO;
    }

    @RequestMapping(value = "/purchaseapplyorder/copyto", method = RequestMethod.GET)
    public String copyTo(@RequestParam("orderNumber") String orderNumber, Map<String, Object> model) {
        String purchaseOrderNumber = commonBillsNumberService.getGenerateBillsNumber("purchase_order", "order_number");
        List<StorageResponse> storages = storageService.getList();
        String loginUserName = sessionContext.get(SecuritySessionConstants.LOGGED_USER_NAME);
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        PurchaseApplyOrderResponse response = paoService.get(orderNumber);
        model.put("storages", storages);
        model.put("purchaseApplyOrder", response);
        model.put("loginId", loginId);
        model.put("loginUserName", loginUserName);
        model.put("purchaseOrderNumber", purchaseOrderNumber);
        return PagePath.ADD_PO;
    }

    @RequestMapping(value = "/purchaseapplyorder/edit", method = RequestMethod.GET)
    public String editPurchaseApplyOrder(@RequestParam("orderNumber") String orderNumber, Map<String, Object> model) {
        try {
            PurchaseApplyOrderResponse response = paoService.get(orderNumber);
            model.put("purchaseApplyOrder", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PagePath.EDIT_APPLY_PO;
    }

    @RequestMapping(value = "/purchaseapplyorder/get", method = RequestMethod.GET)
    public String get(@RequestParam("orderNumber") String orderNumber, Map<String, Object> model) {
        try {
            PurchaseApplyOrderResponse response = paoService.get(orderNumber);
            model.put("purchaseApplyOrder", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PagePath.DETAIL_APPLY_PO;
    }

    @RequestMapping(value = "/purchaseapplyorder/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getApplyPOs(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo, CommonBillsRequest request,
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
        model.put("isDraft", false);
        return PagePath.ALL_APPLY_PO;
    }

    @RequestMapping(value = "/purchaseapplyorder/getdraftlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getDraftApplyPOs(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo, CommonBillsRequest request,
                                   Map<String, Object> model) {
        request.setIsDraft(true);
        getApplyPOs(pageSize, pageNo, request, model);
        model.put("isDraft", true);
        return PagePath.ALL_APPLY_PO;
    }

    @RequestMapping(value = "/purchaseapplyorder/print", method = RequestMethod.GET)
    public String getPrint(@RequestParam("orderNumber") String orderNumber, Map<String, Object> model) {
        try {
            PurchaseApplyOrderResponse response = paoService.get(orderNumber);
            model.put("purchaseApplyOrder", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PagePath.PRINT_APPLY_PO;
    }
}
