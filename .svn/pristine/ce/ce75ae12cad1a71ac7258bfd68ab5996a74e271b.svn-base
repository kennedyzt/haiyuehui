package com.siping.wms.readyreceipt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.wms.request.GenerateReceiptParam;
import com.siping.wms.readyreceipt.service.ReadyReceiptOrderService;

@Controller
@LoginRequired
public class ReadyReceiptOrderAjaxController extends StromaController {

    @Autowired
    ReadyReceiptOrderService readyReceiptOrderService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;
    
    @RequestMapping(value = "/readyreceipt/generateReceipt", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg generateReceipt(@RequestBody GenerateReceiptParam request) {
        ResultMsg resultMsg = null;
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        request.setLoginId(loginId);
        if (readyReceiptOrderService.generateReceipt(request)) {
            resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_SUCCESS));
        } else {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR));
        }
        return resultMsg;
    }
}
