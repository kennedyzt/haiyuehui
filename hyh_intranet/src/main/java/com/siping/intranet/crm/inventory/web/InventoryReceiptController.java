package com.siping.intranet.crm.inventory.web;

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
import com.siping.intranet.crm.inventory.service.InventoryReceiptService;
import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.inventory.request.GetInventoryReceiptListForm;
import com.siping.smartone.inventory.response.InventoryReceiptResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
@LoginRequired
public class InventoryReceiptController extends StromaController {
    @Autowired
    private InventoryReceiptService inventoryReceiptService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private StorageService storageService;
    @Autowired
    private JSONConverter jsonConverter;

    @RequestMapping(value = "/inventoryreceipt/add", method = RequestMethod.GET)
    public String add(Map<String, Object> model) {
        getNecessaryData(model);
        return PagePath.ADD_INVENTORY_RECEIPT;
    }

    private void getNecessaryData(Map<String, Object> model) {
        List<StorageResponse> storages = storageService.getList();
        String loginUserName = sessionContext.get(SecuritySessionConstants.LOGGED_USER_NAME);
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        model.put("storages", storages);
        model.put("loginUserName", loginUserName);
        model.put("loginId", loginId);
    }

    @RequestMapping(value = "/inventoryreceipt/edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value = "receiptNumber", required = true) String receiptNumber, Map<String, Object> model) {
        try {
            getNecessaryData(model);
            InventoryReceiptResponse response = inventoryReceiptService.get(receiptNumber);
            model.put("inventoryReceipt", response);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.EDIT_INVENTORY_RECEIPT;
    }

    @RequestMapping(value = "/inventoryreceipt/get", method = RequestMethod.GET)
    public String get(@RequestParam("receiptNumber") String receiptNumber, Map<String, Object> model) {
        try {
            InventoryReceiptResponse response = inventoryReceiptService.get(receiptNumber);
            model.put("inventoryReceipt", response);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.DETAIL_INVENTORY_RECEIPT;
    }

    @RequestMapping(value = "/inventoryreceipt/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getList(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo, GetInventoryReceiptListForm request,
                          Map<String, Object> model) {
        request.setIsDraft(false);
        getPageModel(pageSize, pageNo, request, model);
        return PagePath.ALL_INVENTORY_RECEIPT;
    }

    private void getPageModel(Integer pageSize, Integer pageNo, GetInventoryReceiptListForm request, Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.INVENTORY_RECEIPT_SEARCH_CONDITION)))
                request = jsonConverter.fromString(GetInventoryReceiptListForm.class, sessionContext.get(SessionConstants.INVENTORY_RECEIPT_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.INVENTORY_RECEIPT_SEARCH_CONDITION, jsonConverter.toString(request));
            if (null == request.getStatus())
                request.setStatus(0);
            PagingResponse<InventoryReceiptResponse> pagingResponse = new PagingResponse<InventoryReceiptResponse>();
            pagingResponse = inventoryReceiptService.getList(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, request);
            PageModel<InventoryReceiptResponse> pageModel = new PageModel<InventoryReceiptResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, pagingResponse.getTotalRecords(),
                pagingResponse.getRecords());
            model.put("pageModel", pageModel);
            model.put("form", request);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
    }

    @RequestMapping(value = "/inventoryreceipt/getdraftlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getDraftList(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo,
                               GetInventoryReceiptListForm request, Map<String, Object> model) {
        request.setIsDraft(true);
        getPageModel(pageSize, pageNo, request, model);
        return PagePath.ALL_DRAFT_INVENTORY_RECEIPT;
    }

    @RequestMapping(value = "/inventoryreceipt/print", method = RequestMethod.GET)
    public String getPrint(Map<String, Object> model) {
        return PagePath.PRINT_INVENTORY_RECEIPT;
    }
}
