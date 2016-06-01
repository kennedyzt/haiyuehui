package com.siping.intranet.crm.inventory.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.URLUtils;

import com.siping.intranet.crm.inventory.service.InventoryReceiptService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.inventory.request.ChangeStatusParam;
import com.siping.smartone.inventory.request.InventoryReceiptRequest;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.wms.request.ConfirmReceiptParam;

@Controller
@LoginRequired
public class InventoryReceiptAjaxController extends StromaController {
    @Autowired
    private InventoryReceiptService inventoryReceiptService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/inventoryreceipt/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(@RequestBody InventoryReceiptRequest request) {
        ResultMsg resultMsg = null;
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setCreateBy(loginId);
            if (inventoryReceiptService.add(request)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_ERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/inventoryreceipt/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg delete(@RequestParam(value = "receiptNumbers", required = true) List<String> receiptNumbers, Map<String, Object> model) {
        ResultMsg resultMsg = null;
        try {
            if (inventoryReceiptService.delete(receiptNumbers)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.DELETE_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/inventoryreceipt/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg edit(InventoryReceiptRequest request) {
        ResultMsg resultMsg = null;
        try {
            if (inventoryReceiptService.edit(request)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.DELETE_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/inventoryreceipt/confirmreceipt", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg confirmReceipt(@RequestBody ConfirmReceiptParam request, HttpServletRequest req) throws Exception {
        ResultMsg resultMsg = null;
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        request.setLoginId(loginId);
        String remoteIp = URLUtils.getRemoteIp(req);
        request.setIp(remoteIp);
        request.setMac(URLUtils.getMACAddress(remoteIp));
        try {
            if (inventoryReceiptService.confirmReceipt(request)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_ERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/inventoryreceipt/changestatus", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg changeStatus(@RequestParam("orderNumber") String orderNumber) {
        ChangeStatusParam request = new ChangeStatusParam();
        ResultMsg resultMsg = null;
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        request.setLoginId(loginId);
        request.setOrderNumber(orderNumber);
        try {
            if (inventoryReceiptService.changeStatus(request)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

}
