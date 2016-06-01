/**********************************************************************
 * Copyright (c): 2014-2049 chengdu nstechs company, All rights reserved.
 * Technical Support:Chengdu nstechs company Contact:
 * chris.qin@nstechs.com,15202879502
 **********************************************************************/
package com.siping.integrate.log;

import org.apache.http.client.HttpClient;
import org.stroma.framework.core.http.FormPost;
import org.stroma.framework.core.log.LogMessageFilter;
import org.stroma.framework.core.platform.web.filter.StromaFilter;

public class ServiceLogMessageFilter extends LogMessageFilter {

    static final String MATRIX_FILTER_LOGGER = StromaFilter.class.getName();

    static final String FORM_POST_LOGGER = FormPost.class.getName();

    static final String HTTP_CLIENT_LOGGER = HttpClient.class.getName();

    @Override
    public String filter(String loggerName, String message) {
        if (MATRIX_FILTER_LOGGER.equals(loggerName) || FORM_POST_LOGGER.equals(loggerName)) {
            return this.filterForRequestParams(message);
        } else if (HTTP_CLIENT_LOGGER.equals(loggerName)) {
            return this.filterForHTTPClient(message);
        }
        return message;
    }

    private String filterForRequestParams(String message) {
        if (message.startsWith("[param] password="))
            return this.mask(message, "password=(.*)");
        if (message.startsWith("[param] confirmPassword="))
            return this.mask(message, "confirmPassword=(.*)");
        if (message.startsWith("[param] name="))
            return this.mask(message, "name=(.*)");
        if (message.startsWith("[param] phone="))
            return this.mask(message, "phone=(.*)");
        if (message.startsWith("[param] appid="))
            return this.mask(message, "appid=(.*)");
        if (message.startsWith("[param] appkey="))
            return this.mask(message, "appkey=(.*)");
        return message;
    }

    private String filterForHTTPClient(String message) {
        return message;
    }

}
