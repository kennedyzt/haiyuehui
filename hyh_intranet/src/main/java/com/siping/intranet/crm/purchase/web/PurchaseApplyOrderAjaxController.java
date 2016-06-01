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
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.purchase.service.PurchaseApplyOrderService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.purchase.request.PurchaseApplyOrderRequest;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class PurchaseApplyOrderAjaxController extends StromaController {
    @Autowired
    private PurchaseApplyOrderService paoService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private SessionContext sessionContext;

    @RequestMapping(value = "/purchaseapplyorder/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg newPAO(@RequestBody PurchaseApplyOrderRequest purchaseApplyOrder) {
        // 新增采购申请订单
        ResultMsg resultMsg = null;
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            purchaseApplyOrder.setCreateBy(loginId);
            resultMsg = paoService.add(purchaseApplyOrder);
            resultMsg = new ResultMsg(Boolean.TRUE, "添加成功",resultMsg.getBillNumber());
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }

        return resultMsg;
    }

    @RequestMapping(value = "/purchaseapplyorder/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg delete(@RequestParam("ids") List<String> orderNumbers) {
        ResultMsg resultMsg = null;
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            if (paoService.delete(orderNumbers, loginId)) {
                resultMsg = new ResultMsg(Boolean.TRUE, "删除成功");
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/purchaseapplyorder/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg edit(@RequestBody PurchaseApplyOrderRequest request, Map<String, Object> model) {
        try {
            Boolean isEdit = paoService.edit(request);
            return new ResultMsg(isEdit, isEdit ? i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS) : i18nUtil.getMessage(ErrorCode.EDIT_ERROR_NO_PARAM));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
    }
}
