package com.siping;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.stroma.framework.core.platform.DefaultCacheConfig;
import org.stroma.framework.core.platform.cache.CacheRegistry;
import org.stroma.framework.core.util.TimeLength;

import com.siping.integrate.constant.CacheConstant;
import com.siping.integrate.constant.CacheGroupConstant;

@Configuration
@EnableCaching(proxyTargetClass = true)
public class CacheConfig extends DefaultCacheConfig {

    @Override
    protected void addCaches(CacheRegistry registry) {
        registry.addCache(CacheGroupConstant.SERVICEGROUP, CacheConstant.CITY, TimeLength.days(30));
    }
}
