package com.siping.intranet.crm.usr.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.exception.ResourceNotFoundException;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SecureSessionContext;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.login.response.UserLoginResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class WelcomeController extends StromaController {
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private SecureSessionContext secureSessionContext;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private JSONConverter jsonConverter;

    @RequestMapping(value = "/login/index", method = RequestMethod.GET)
    public String indexPage(Map<String, Object> model) {

        return PagePath.INDEX_PAGE;
    }

    @RequestMapping(value = "/admin/welcome", method = RequestMethod.GET)
    public String adminWelcome(Map<String, Object> cache) {
        getUser(cache);
        return PagePath.ADMIN_WELCOME;
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Map<String, Object> cache) {
        getUser(cache);
        return PagePath.WELCOME;
    }

    private void getUser(Map<String, Object> cache) {
        UserLoginResponse response = jsonConverter.fromString(UserLoginResponse.class, sessionContext.get(SecuritySessionConstants.LOGGED_USER));
        cache.put("loginUser", response);
    }

    @RequestMapping(value = "/go", method = RequestMethod.GET)
    public String welcome(@RequestParam("site") String projectName) {
        sessionContext.set(SecuritySessionConstants.CURRENTSITE, projectName);
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut(HttpServletRequest request) {
        secureSessionContext.invalidate();
        sessionContext.invalidate();
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/duplicate-submit", method = RequestMethod.GET)
    public String duplicateSubmit(Map<String, Object> cache) {
        cache.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.SUBMIT_DUPLICATE_ERROR)));
        return welcome(cache);
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String duplicateSubmit(@RequestParam("code") String code) {
        return "/errors/" + code;
    }

    @RequestMapping(value = "/resource-not-found", method = RequestMethod.GET)
    public String homePageFor404() {
        throw new ResourceNotFoundException(null);
    }

    @RequestMapping(value = "/method-not-allowed", method = RequestMethod.GET)
    public String homePageFor405() {
        throw new ResourceNotFoundException(null);
    }
}
