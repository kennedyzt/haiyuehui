package com.siping.config;

import java.lang.reflect.Method;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.context.WebApplicationContext;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.database.DBSwitchInterceptor;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.JDBCAccessFactory;
import org.stroma.framework.core.platform.DefaultAppConfig;

import com.siping.integrate.constant.Trans;

public abstract class DbConfig extends DefaultAppConfig {
    @Inject
    protected Environment env;

    @Bean(destroyMethod = "close")
    public DataSource readDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driver.name"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.readdb.proxy.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        dataSource.setTestOnBorrow(true);
        // TODO 数据合理性有待验证
        // 连接池数量设置
        dataSource.setMaxActive(200); // 最大连接数量
        dataSource.setMaxIdle(200); // 最大空闲连接
        dataSource.setMinIdle(0); // 最小空闲连接
        dataSource.setInitialSize(5); // 初始化连接
        dataSource.setValidationQuery("select 1 from dual");
        return dataSource;
    }

    @Bean(destroyMethod = "close")
    public DataSource writeDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driver.name"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.writedb.proxy.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        // TODO 数据合理性有待验证
        // 连接池数量设置
        dataSource.setMaxActive(200); // 最大连接数量
        dataSource.setMaxIdle(200); // 最大空闲连接
        dataSource.setMinIdle(0); // 最小空闲连接
        dataSource.setInitialSize(5); // 初始化连接
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("select 1 from dual");
        return dataSource;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public JDBCAccessFactory jdbcAccessFactory() {
        JDBCAccessFactory factory = new JDBCAccessFactory(writeDataSource(), readDataSource());
        return factory;
    }

    @Bean
    @Qualifier(Trans.JDBC)
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(writeDataSource());
        return transactionManager;
    }

    @Bean
    public DBSwitchInterceptor dbSwitchAdvice() {
        return new DBSwitchInterceptor();
    }

    @Bean
    public DefaultPointcutAdvisor dbSwitchAdvisor() {
        StaticMethodMatcherPointcut pointcut = new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                Class<?> superClass = targetClass.getSuperclass();
                while (null != superClass && !superClass.equals(Object.class)) {
                    if (superClass.equals(DBSwitch.class))
                        return Boolean.TRUE;
                    superClass = superClass.getSuperclass();
                }
                return Boolean.FALSE;
            }
        };
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setAdvice(dbSwitchAdvice());
        advisor.setPointcut(pointcut);
        return advisor;
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public JDBCAccessContext jdbcAccessContext() {
        return new JDBCAccessContext();
    }
}
