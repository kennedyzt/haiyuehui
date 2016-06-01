package com.siping.intranet.crm.businesspartner.web;

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

import com.siping.intranet.crm.businesspartner.service.BankAccountService;
import com.siping.smartone.businesspartner.request.BankAccountRequest;
import com.siping.smartone.businesspartner.response.BankAccountResponse;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class BankAccountAjaxController extends StromaController {
    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;

    @RequestMapping(value = "/bankaccount/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg delete(@RequestParam("id") List<Integer> ids) {
        ResultMsg resultMsg = null;
        try {
            Integer updateBy = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            if (bankAccountService.delete(ids, updateBy))
                ;
            {
                resultMsg = new ResultMsg(Boolean.TRUE, "删除成功");
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/bankaccount/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(BankAccountRequest request) {
        ResultMsg resultMsg = null;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setCreateBy(userId);
            bankAccountService.add(request);
            resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_SUCCESS));
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/bankaccount/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg edit(BankAccountRequest request) {
        ResultMsg resultMsg = null;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setUpdateBy(userId);
            bankAccountService.edit(request);
            resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS));
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/bankaccount/getlist", method = RequestMethod.GET)
    @ResponseBody
    public List<BankAccountResponse> getList(@RequestParam("partnerId") Integer id, Map<String, Object> model) {
        List<BankAccountResponse> response = null;
        try {
            response = bankAccountService.getList(id);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return response;
    }
}
