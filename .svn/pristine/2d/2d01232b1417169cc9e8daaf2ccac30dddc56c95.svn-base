package com.siping.intranet.crm.purchase.web;

import java.util.List;
import java.util.Map;

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

import com.siping.intranet.crm.purchase.service.PurchaseOrderService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.purchase.request.PurchaseOrderRequest;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class PurchaseOrderAjaxController extends StromaController {

    @Autowired
    PurchaseOrderService purchaseOrderService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/purchaseorder/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(@RequestBody PurchaseOrderRequest purchaseOrder) {
        // 新增采购订单
        ResultMsg resultMsg = null;
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            purchaseOrder.setCreateBy(loginId);
            resultMsg = purchaseOrderService.add(purchaseOrder);
            resultMsg = new ResultMsg(Boolean.TRUE, "添加成功",resultMsg.getBillNumber());
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }

        return resultMsg;
    }

    @RequestMapping(value = "/purchaseorder/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg delete(@RequestParam(value = "orderNumbers", required = true) List<String> orderNumbers, Map<String, Object> model) {
        ResultMsg resultMsg = null;
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            if (purchaseOrderService.delete(orderNumbers, loginId)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.DELETE_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR));
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/purchaseorder/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg edit(@RequestBody PurchaseOrderRequest request, Map<String, Object> model) {
        try {
            Boolean isEdit = purchaseOrderService.edit(request);
            return new ResultMsg(isEdit, isEdit ? i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS) : i18nUtil.getMessage(ErrorCode.EDIT_ERROR_NO_PARAM));
        } catch (Exception e) {
            return new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
    }
}
