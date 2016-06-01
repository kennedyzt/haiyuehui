package com.siping;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.stroma.framework.core.database.manager.SqlMappingRegistry;
import org.stroma.framework.core.log.LogSettings;
import org.stroma.framework.core.platform.StromaScopeResolver;

import com.siping.config.DbConfig;
import com.siping.integrate.log.ServiceLogMessageFilter;

@Configuration
@ComponentScan(basePackageClasses = AppConfig.class, scopeResolver = StromaScopeResolver.class)
@EnableAspectJAutoProxy
@EnableTransactionManagement
@PropertySource({ "classpath:/site-master.properties", "classpath:/site-jdbc.properties", "classpath:/site-version.properties" })
public class AppConfig extends DbConfig {

    @Bean
    @Qualifier("messageSource")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
        bundleMessageSource.setBasename("i18n.nstechs-i18n");
        bundleMessageSource.setUseCodeAsDefaultMessage(true);
        return bundleMessageSource;
    }

    @Override
    public LogSettings logSettings() {
        LogSettings congig = super.logSettings();
        congig.setLogMessageFilter(new ServiceLogMessageFilter());
        return congig;
    }

    @Override
    protected void registrySqlMapping(SqlMappingRegistry registry) {
        registry.registSqlMapping("config/db/user/user-sqlmap.xml");
        registry.registSqlMapping("config/db/user/menu-sqlmap.xml");
        registry.registSqlMapping("config/db/user/group-sqlmap.xml");
        registry.registSqlMapping("config/db/erp/storage-sqlmap.xml");
        registry.registSqlMapping("config/db/user/permission.xml");
        registry.registSqlMapping("config/db/erp/material-sqlmap.xml");
        registry.registSqlMapping("config/db/user/partnergroup.xml");
        registry.registSqlMapping("config/db/user/partnertype.xml");
        registry.registSqlMapping("config/db/erp/business-partner.xml");
        registry.registSqlMapping("config/db/erp/buli-partner-errors.xml");
        registry.registSqlMapping("config/db/erp/bulk-partner-transaction.xml");
        registry.registSqlMapping("config/db/erp/bank-account.xml");
        registry.registSqlMapping("config/db/erp/postperiod-sqlmap.xml");
        registry.registSqlMapping("config/db/erp/sell-sqlmap.xml");
        registry.registSqlMapping("config/db/erp/purchase-apply-order.xml");
        registry.registSqlMapping("config/db/erp/purchase-order.xml");
        registry.registSqlMapping("config/db/erp/purchase-receipt.xml");
        registry.registSqlMapping("config/db/erp/inventory-receipt.xml");
        registry.registSqlMapping("config/db/erp/purchase-returns.xml");
        registry.registSqlMapping("config/db/erp/inventorycheck-sqlmap.xml");
        registry.registSqlMapping("config/db/erp/inventory.xml");
        registry.registSqlMapping("config/db/ec/ec.xml");
        registry.registSqlMapping("config/db/wms/ready-receipt.xml");
        registry.registSqlMapping("config/db/wms/ready-shipments.xml");
        registry.registSqlMapping("config/db/wms/order-pick.xml");
        registry.registSqlMapping("config/db/wms/inventory-allocate.xml");
        registry.registSqlMapping("config/db/erp/country-region.xml");
    }
}
