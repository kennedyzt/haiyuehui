package com.siping.intranet.crm.businesspartner.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.businesspartner.service.BankAccountService;
import com.siping.smartone.businesspartner.response.BankAccountResponse;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;

@Controller
@LoginRequired
public class BankAccountController extends StromaController {
    @Autowired
    private BankAccountService bankaccountService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/bankaccount/add", method = RequestMethod.GET)
    public String addBankAccountView() {
        return PagePath.ADD_MATERIAL;
    }

    @RequestMapping(value = "/bankaccount/edit", method = RequestMethod.GET)
    public String editBankAccountView(@RequestParam("id") Integer id, Map<String, Object> model) {
        try {
            BankAccountResponse bankaccountResponse = bankaccountService.get(id);
            model.put("bankaccount", bankaccountResponse);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.EDIT_PARTNER_BANK;
    }

    // 该方法也适用于修改密码
    @RequestMapping(value = "/bankaccount/get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> model) {
        BankAccountResponse response = null; // 查询单个物料可以单独用id查询，单独用物料编号查询，单独用国际编码查询均可，当然可以三个条件都提供来查询
        try {
            response = bankaccountService.get(id);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        model.put("bankAccounts", response);
        return PagePath.DETAIL_MATERIAL;
    }
}
