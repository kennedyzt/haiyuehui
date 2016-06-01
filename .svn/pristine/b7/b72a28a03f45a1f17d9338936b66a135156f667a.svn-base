package com.siping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.stroma.framework.core.platform.StromaSiteWebConfig;
import org.stroma.framework.core.platform.im4j.service.manager.ThumbnailProviderType;
import org.stroma.framework.core.platform.intercept.LocaleInterceptor;
import org.stroma.framework.core.platform.web.RuntimeEnvironment;
import org.stroma.framework.core.platform.web.session.SessionProviderType;
import org.stroma.framework.core.platform.web.site.layout.ModelBuilder;
import org.stroma.framework.core.platform.web.site.layout.ModelBuilderInterceptor;
import org.stroma.framework.core.setting.CDNSettings;
import org.stroma.framework.core.setting.DefaultCDNSettings;
import org.stroma.framework.core.setting.DeploymentSettings;
import org.stroma.framework.core.setting.SiteSettings;
import org.stroma.framework.core.setting.SsoSettings;
import org.stroma.framework.core.setting.ThumbnailSettings;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.TimeLength;

import com.siping.integrate.constant.SsoConstants;
import com.siping.integrate.transfer.MasterLayout;
import com.siping.integrate.transfer.MasterTemplateModelBuilder;

public abstract class SiteConfig extends StromaSiteWebConfig {

    @Override
    public DeploymentSettings deploymentSettings() {
        DeploymentSettings settings = super.deploymentSettings();
        settings.setHost(env.getProperty("site.host"));
        settings.setDeploymentContext(env.getProperty("site.deploymentcontext"));
        settings.setHTTPPort(null != env.getProperty("site.port") ? Integer.valueOf(env.getProperty("site.port")) : 80);
        settings.setHTTPSPort(443);
        return settings;
    }

    @Override
    public SiteSettings siteSettings() {
        SiteSettings settings = new SiteSettings();
        settings.setEnvironment(env.getProperty("site.environment", RuntimeEnvironment.class, RuntimeEnvironment.PROD));

        settings.setErrorPage("/error");
        settings.setResourceNotFoundPage("/errors/404");

        settings.setSessionProviderType(env.getProperty("site.sessionprovider", SessionProviderType.class, SessionProviderType.LOCAL));
        settings.setSessionTimeOut(TimeLength.minutes(env.getProperty("site.sessiontimeout", long.class, 30L)));
        settings.setSessionTimeOutPage("redirect:" + env.getProperty("site.sso.auth.url"));

        settings.setLoginUrl(env.getProperty("site.sso.auth.url"));

        settings.setStaticDir(env.getRequiredProperty("site.staticdir"));
        settings.setNfsDir(env.getRequiredProperty("site.nfsdir"));
        settings.setJsDir(env.getRequiredProperty("site.jsdir"));
        settings.setCssDir(env.getRequiredProperty("site.cssdir"));
        settings.setVersion(getVersion());
        return settings;
    }

    @Override
    public CDNSettings cdnSettings() {
        DefaultCDNSettings cdnSettings = new DefaultCDNSettings();
        cdnSettings.setSupportHTTPS(true);
        cdnSettings.setSupportS3(env.getRequiredProperty("site.s3.open", Boolean.class));
        cdnSettings.setBucketName(env.getProperty("site.s3.bucketname", String.class));

        cdnSettings.setCDNHostsWithCommaDelimitedValue(env.getRequiredProperty("site.cdn.host"));
        cdnSettings.setNfsHosts(env.getRequiredProperty("site.image.host"));
        cdnSettings.setLocalPath(env.getRequiredProperty("site.image.mountpath"));
        return cdnSettings;
    }

    @Override
    public SsoSettings ssoSettings() {
        SsoSettings ssoSettings = new SsoSettings();
        ssoSettings.setSsoConstantsClassName(SsoConstants.class.getName());
        ssoSettings.setGlobalDns(env.getRequiredProperty("site.sso.global.dns"));
        ssoSettings.setCookieDomain(env.getRequiredProperty("site.sso.cookie.domain"));
        ssoSettings.setCookiePath(env.getRequiredProperty("site.sso.cookie.path"));
        return ssoSettings;
    }

    private String getVersion() {
        String version = env.getRequiredProperty("runtime.version");
        // for local build, where version is not populated , by maven
        return version.contains("${") ? "current" : version;
    }

    @Override
    public ThumbnailSettings thumbnailSettings() {
        ThumbnailSettings settings = new ThumbnailSettings();
        settings.setThumbnailProviderType(ThumbnailProviderType.IM4J);
        return settings;
    }

    @Bean
    public ModelBuilder modelBuilder() {
        return new MasterTemplateModelBuilder();
    }

    @Bean
    public ModelBuilderInterceptor modelBuilderInterceptor() {
        ModelBuilderInterceptor interceptor = new ModelBuilderInterceptor();
        interceptor.registerModelBuilder(MasterLayout.class, modelBuilder());
        return interceptor;
    }

    @Bean
    public LocaleInterceptor localeInterceptor() {
        return new LocaleInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 1.启用Session
        registry.addInterceptor(cookieInterceptor());

        registry.addInterceptor(requestContextInterceptor());

        registry.addInterceptor(sessionInterceptor());

        // 2.启用http,https转换
        registry.addInterceptor(httpSchemeEnforceInterceptor());

        // 3.启用Spring向freemarker对象传输
        registry.addInterceptor(modelBuilderInterceptor());

        // 4.启用国际化
        registry.addInterceptor(localeInterceptor());

        // 5.启用页面渲染防止出现很难看的错误页面
        registry.addInterceptor(exceptionInterceptor());

        // 6.增加业务拦截器
        addBusinessInterceptors(registry);
    }

    public abstract void addBusinessInterceptors(InterceptorRegistry registry);

    @Bean
    @Scope("singleton")
    public I18nUtil i18nUtil() {
        return new I18nUtil();
    }
}
