package com.siping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.stroma.framework.core.platform.intercept.TokenInteceptor;
import org.stroma.framework.core.platform.web.freemarker.view.DefaultFreemarkerViewResolver;

import com.siping.config.SiteConfig;
import com.siping.integrate.transfer.IntranetFreemarkerView;
import com.siping.smartone.security.interceptor.LoginInterceptor;

@Configuration
@PropertySource({ "classpath:/site-master.properties", "classpath:/site-version.properties" })
public class WebConfig extends SiteConfig {

    @Override
    public DefaultFreemarkerViewResolver freeMarkerViewResolver() {
        DefaultFreemarkerViewResolver resolver = super.freeMarkerViewResolver();
        resolver.setViewClass(IntranetFreemarkerView.class);
        return resolver;
    }

    @Bean
    public TokenInteceptor tokenInteceptor() {
        return new TokenInteceptor();
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addBusinessInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor());
        registry.addInterceptor(tokenInteceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("/").setViewName("forward:/welcome");
    }
}
