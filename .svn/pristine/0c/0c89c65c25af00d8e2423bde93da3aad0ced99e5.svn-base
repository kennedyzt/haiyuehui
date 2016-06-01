package com.siping.smartone.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.http.HTTPConstants;
import org.stroma.framework.core.http.URLInfo;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.site.URLBuilder;
import org.stroma.framework.core.setting.SiteSettings;

import com.siping.smartone.security.constants.SecuritySessionConstants;

public class LoginInterceptor extends AbstractInteceptor {
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private SiteSettings siteSettings;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            LoginRequired loginRequired = findAnnotation((HandlerMethod) handler, LoginRequired.class);
            if (loginRequired != null && !loggedIn()) {
                redirectToLoginPage(request, response);
                return Boolean.FALSE;
            }
            // String servletPath = request.getRequestURI();
            // if (loginRequired != null && loggedIn() &&
            // !StringUtils.hasText(request.getParameter("tab")) &&
            // !checkIsWelcomePath(servletPath)) {
            // redirectToWelcomePage(request, response);
            // return Boolean.FALSE;
            // }
        }
        return Boolean.TRUE;
    }

    @SuppressWarnings("unused")
    private Boolean checkIsWelcomePath(String path) {
        if (StringUtils.hasText(path)) { // 检验是不是welcome请求
            if (path.contains("welcome"))
                return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private void redirectToLoginPage(HttpServletRequest request, HttpServletResponse response) {
        final String ajaxHeader = request.getHeader("x-requested-with");
        if (StringUtils.hasText(ajaxHeader) && "XMLHttpRequest".equalsIgnoreCase(ajaxHeader)) {
            throw new BusinessProcessException("请重新登录！");
        } else {
            URLInfo url = URLInfo.load(request);
            URLBuilder builder = new URLBuilder();
            builder.setContext(request.getContextPath(), null);
            builder.setLogicalURL(siteSettings.getLoginUrl());
            response.setStatus(HTTPConstants.SC_MOVED_TEMPORARILY);
            response.setHeader(HTTPConstants.HEADER_REDIRECT_LOCATION, builder.buildRelativeURL());
            sessionContext.set(SecuritySessionConstants.LOGIN_REDIRECT_DESTINATION_URL, jsonConverter.toString(url));
        }
    }

    @SuppressWarnings("unused")
    private void redirectToWelcomePage(HttpServletRequest request, HttpServletResponse response) {
        URLBuilder builder = new URLBuilder(); // 登录后，拦截所有不是从菜单栏的请求，跳到welcome页面
        builder.setContext(request.getContextPath(), null);
        builder.setLogicalURL("/welcome");
        response.setStatus(HTTPConstants.SC_MOVED_TEMPORARILY);
        response.setHeader(HTTPConstants.HEADER_REDIRECT_LOCATION, builder.buildRelativeURL());
    }
}
