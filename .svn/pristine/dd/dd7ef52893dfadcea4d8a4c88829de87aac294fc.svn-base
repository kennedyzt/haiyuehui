/**********************************************************************
 * Copyright (c): 2014-2049 chengdu nstechs company, All rights reserved.
 * Technical Support:Chengdu nstechs company Contact:
 * chris.qin@nstechs.com,15202879502
 **********************************************************************/
package com.siping;

import java.io.IOException;
import java.net.InetAddress;

import javax.inject.Inject;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.stroma.framework.core.database.manager.SqlMappingRegistry;
import org.stroma.framework.core.database.redis.RedisAccess;
import org.stroma.framework.core.log.LogSettings;
import org.stroma.framework.core.platform.StromaAppConfig;
import org.stroma.framework.core.platform.StromaScopeResolver;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.siping.integrate.log.IntranetLogMessageFilter;

@Configuration
@ComponentScan(basePackageClasses = AppConfig.class, scopeResolver = StromaScopeResolver.class)
@EnableAspectJAutoProxy
@EnableTransactionManagement
@PropertySource({ "classpath:/site-social.properties", "classpath:/site-redis.properties" })
public class AppConfig extends StromaAppConfig {
    @Inject
    protected Environment env;

    // @Bean
    // public MailSender mailSender() {
    // MailSender mailSender = new MailSender();
    // mailSender.setHost(env.getProperty("site.mail.host"));
    // mailSender.setPort(env.getProperty("site.mail.port", int.class));
    // mailSender.setUsername(env.getProperty("site.mail.username"));
    // mailSender.setPassword(env.getProperty("site.mail.password"));
    // return mailSender;
    // }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        return multipartResolver;
    }

    @Override
    public LogSettings logSettings() {
        LogSettings congig = super.logSettings();
        congig.setLogMessageFilter(new IntranetLogMessageFilter());
        return congig;
    }

    // @Override
    // public MemcachedGroups memcachedGroups() {
    // Document document = new
    // XMLParser().parse(IOUtils.bytes(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/memcached/config.xml")));
    // String xml = DOMUtils.text(document);
    // return this.xmlConverter().fromString(MemcachedGroups.class, xml);
    // }

    @Override
    public void registrySqlMapping(SqlMappingRegistry registry) {
        // registry.registSqlMapping("config/db/*-sqlmap.xml");
    }

    @Bean
    @Qualifier("messageSource")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
        bundleMessageSource.setBasename("i18n.siping-i18n");
        bundleMessageSource.setUseCodeAsDefaultMessage(true);
        return bundleMessageSource;
    }

    @Bean
    public RedisAccess redisAccess() throws IOException {
        RedisAccess redisAccess = new RedisAccess();
        JedisPoolConfig config = new JedisPoolConfig();
        String hostAddress = "";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            hostAddress = localHost.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        config.setMaxIdle(env.getProperty("site.redis.maxidle", Integer.class, 5)); // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxWaitMillis(env.getProperty("site.redis.maxwaitmillis", Long.class, 100000L)); // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        config.setTestOnBorrow(env.getProperty("site.redis.testonborrow", Boolean.class, Boolean.TRUE)); // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        JedisPool pool = new JedisPool(config, env.getProperty("site.redis.server", hostAddress), env.getProperty("site.redis.port", Integer.class, 6379));
        redisAccess.setJedisPool(pool);
//        redisAccess.setPassword("ybRIPvhn1");
        return redisAccess;
    }
}
