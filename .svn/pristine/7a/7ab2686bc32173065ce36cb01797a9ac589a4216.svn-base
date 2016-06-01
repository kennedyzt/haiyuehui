package com.siping.integrate.transfer;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.stroma.framework.core.platform.web.freemarker.view.DefaultFreemarkerView;

import freemarker.template.TemplateModelException;

public class IntranetFreemarkerView extends DefaultFreemarkerView {
    private static final String TAG_INTRANET_PAGE = "pageByParam";
    private static final String TAG_INTRANET_BUTTON = "button";

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
    }

    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        super.exposeHelpers(model, request);
        registerIntranetPageTag(model, request);
        registerIntranetButtonTag(model, request);
    }

    private void registerIntranetPageTag(Map<String, Object> model, HttpServletRequest request) throws TemplateModelException {
        Object previousValue = model.put(TAG_INTRANET_PAGE, new ParamPageTag(request, deploymentSettings, siteSettings));
        assertTagNameIsAvailable(previousValue, TAG_INTRANET_PAGE);
    }

    private void registerIntranetButtonTag(Map<String, Object> model, HttpServletRequest request) throws TemplateModelException {
        Object previousValue = model.put(TAG_INTRANET_BUTTON, new ButtonTag(sessionContext, request));
        assertTagNameIsAvailable(previousValue, TAG_INTRANET_BUTTON);
    }
}
