package com.siping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.Ordered;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.stroma.framework.core.platform.DefaultSiteWebConfig;
import org.stroma.framework.core.util.I18nUtil;

@Configuration
public class WebConfig extends DefaultSiteWebConfig {

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public I18nUtil i18nUtil() {
        return new I18nUtil();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // add memcache support.
        registry.addInterceptor(requestContextInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("/").setViewName("forward:/jobs");
    }
}
