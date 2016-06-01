package com.siping.wms.readyreceipt.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.wms.request.OrderPickQueryParam;
import com.siping.wms.readyreceipt.service.OrderPickService;

@Controller
@LoginRequired
public class OrderPickAjaxController extends StromaController {
    @Autowired
    private OrderPickService orderPickService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;

    @RequestMapping(value = "/orderpick/add", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg get(@RequestParam(value = "orderNumbers", required = true) List<String> orderNumbers, @RequestParam(value = "storageId", required = true) Integer storageId,Map<String, Object> model) {
        ResultMsg resultMsg;
        try {
            OrderPickQueryParam request = new OrderPickQueryParam();
            request.setOrderNumbers(orderNumbers);
            request.setStorageId(storageId);
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setLoginId(loginId);
            orderPickService.add(request);
            resultMsg = new ResultMsg(true, i18nUtil.getMessage(ErrorCode.ADD_SUCCESS));
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }
    
    @RequestMapping(value = "/orderpick/updatestatus", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg updateStatus(@RequestParam(value = "orderNumber", required = true) String orderNumber ,@RequestParam(value = "status", required = true) Integer status,Map<String, Object> model) {
        ResultMsg resultMsg;
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            orderPickService.updateStatus(orderNumber,loginId,status);
            resultMsg = new ResultMsg(true, i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS));
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }
}
