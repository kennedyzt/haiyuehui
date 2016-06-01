package com.siping.intranet.invoicing.sell.returns.web;

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

import com.siping.intranet.invoicing.sell.returns.service.SellReturnsService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.invoicing.sell.returns.request.AddSellReturnsRequest;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class SellReturnsAjaxController extends StromaController {
    @Autowired
    private SellReturnsService sellReturnsService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;

    @RequestMapping(value = "/sellreturns/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(@RequestBody AddSellReturnsRequest request) {
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setCreateBy("1");
            Boolean isAdd = sellReturnsService.add(request);
            return new ResultMsg(isAdd, isAdd ? i18nUtil.getMessage(ErrorCode.ADD_SUCCESS) : i18nUtil.getMessage(ErrorCode.ADD_ERROR_NO_PARAM));
        } catch (Exception e) {
            return new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
    }

    @RequestMapping(value = "/sellreturns/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg edit(@RequestBody AddSellReturnsRequest request) {
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setCreateBy(userId.toString());
            Boolean isEdit = sellReturnsService.edit(request);
            return new ResultMsg(isEdit, isEdit ? i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS) : i18nUtil.getMessage(ErrorCode.EDIT_ERROR_NO_PARAM));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
    }

    @RequestMapping(value = "/sellreturns/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg delete(@RequestParam List<String> returnsNumberList, Map<String, Object> model) {
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            Boolean isDelete = sellReturnsService.delete(returnsNumberList, userId);
            return new ResultMsg(isDelete, isDelete ? i18nUtil.getMessage(ErrorCode.DELETE_SUCCESS) : i18nUtil.getMessage(ErrorCode.DELETE_ERROR_NO_PARAM));
        } catch (Exception e) {
            return new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
    }
}
