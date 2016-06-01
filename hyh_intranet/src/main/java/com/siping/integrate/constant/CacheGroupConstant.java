package com.siping.integrate.constant;

import org.stroma.framework.core.platform.cache.StromaMemcachedGroup;

public final class CacheGroupConstant extends StromaMemcachedGroup {
    /**
     * 此组缓存后端用户数据
     */
    public static final String ROLEGROUP = "ROLEGROUP";

    /**
     * 此组缓存网站各种公共数据
     */
    public static final String INTEGRATEGROUP = "INTEGRATEGROUP";

    /**
     * 此组缓存网站的商品
     */
    public static final String SERVICEGROUP = "SERVICEGROUP";
}
