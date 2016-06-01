package com.siping.smartone.security.interceptor;

import java.lang.annotation.Annotation;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.stroma.framework.core.platform.web.session.SessionContext;

import com.siping.smartone.security.constants.SecuritySessionConstants;

public abstract class AbstractInteceptor extends HandlerInterceptorAdapter {
    @Autowired
    protected SessionContext sessionContext;

    protected boolean loggedIn() {
        Boolean loggedIn = sessionContext.get(SecuritySessionConstants.LOGGED_IN);
        return Boolean.TRUE.equals(loggedIn);
    }

    protected <T extends Annotation> T findAnnotation(HandlerMethod handler, Class<T> annotationType) {
        T annotation = handler.getBeanType().getAnnotation(annotationType);
        if (annotation != null)
            return annotation;
        return handler.getMethodAnnotation(annotationType);
    }

    protected Timestamp getSystemTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    protected String removeJsessionIdInfo(String path) {
        int flag = path.indexOf(';');
        return flag > -1 ? path.substring(0, flag) : path;
    }
}
