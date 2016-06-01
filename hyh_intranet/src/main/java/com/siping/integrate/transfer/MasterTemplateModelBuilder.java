package com.siping.integrate.transfer;

import java.util.Map;

import javax.inject.Inject;

import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.platform.web.site.layout.ModelBuilder;

public class MasterTemplateModelBuilder implements ModelBuilder {

    @SuppressWarnings("unused")
    private SessionContext sessionContext;

    @Override
    public void build(Map<String, Object> model) {
        // model.put("user",
        // sessionContext.get(SecuritySessionConstants.LOGGED_USER));
    }

    @Inject
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

}
