package com.siping.intranet.crm.usr.web;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.StringUtils;

import com.siping.intranet.crm.usr.service.UsrService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
public class UsrAjaxController extends StromaController {
    @Autowired
    private UsrService loginService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private UsrService usrService;

    @RequestMapping(value = "/usr/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg deleteUser(@RequestParam(required = true) Integer id, Map<String, Object> model) {
        ResultMsg resultMsg = null;
        try {
            Integer updateBy = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            loginService.delete(id, updateBy);
            resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.DELETE_SUCCESS));
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    // 注意：
    // locale必须为,如果德语，"GERMANY"或者"GERMAN",如果为英语,"US",如果为中文"CHINESE",或者"CHINA"
    @RequestMapping(value = "usr/languageswitch", method = { RequestMethod.GET, RequestMethod.POST })
    @LoginRequired
    @ResponseBody
    public void languageSwitch(@RequestParam(value = "language", required = true) String language, Map<String, Object> model) {
        if (!StringUtils.hasText(language)) { // 异步调用该方法
            throw new BusinessProcessException("切换语言失败！");
        }
        i18nUtil.setLocale(new Locale(language));
        i18nUtil.setIsLanguageSwitch(Boolean.TRUE);// 说明是通过浏览器按钮指定的国际化
        Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        usrService.languageSwitch(userId + "", language);
    }
}
