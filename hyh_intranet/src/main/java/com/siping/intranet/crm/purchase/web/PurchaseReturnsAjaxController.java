package com.siping.intranet.crm.purchase.web;

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

import com.siping.intranet.crm.purchase.service.PurchaseReturnsService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.purchase.request.PurchaseReturnsRequest;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class PurchaseReturnsAjaxController extends StromaController {
    @Autowired
    private PurchaseReturnsService purchaseReturnsService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/purchasereturns/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(@RequestBody PurchaseReturnsRequest request, HttpServletRequest req) {
        ResultMsg resultMsg = null;
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        try {
            String remoteIp = URLUtils.getRemoteIp(req);
            request.setIp(remoteIp);
            request.setMac(URLUtils.getMACAddress(remoteIp));
            request.setCreateBy(loginId);
            resultMsg = purchaseReturnsService.add(request);
            resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_SUCCESS), resultMsg.getBillNumber());
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    // 删除采购退货单并关联删除采购退货单项
    @RequestMapping(value = "/purchasereturns/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg delete(@RequestParam(value = "returnsNumber", required = true) List<String> returnsNumbers, Map<String, Object> model) {
        ResultMsg resultMsg = null;
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            if (purchaseReturnsService.delete(returnsNumbers, loginId + "")) {
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

    @RequestMapping(value = "/purchasereturns/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg edit(@RequestBody PurchaseReturnsRequest request) {
        ResultMsg resultMsg = null;
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        request.setCreateBy(loginId);
        try {
            if (purchaseReturnsService.edit(request)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR));
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

}
