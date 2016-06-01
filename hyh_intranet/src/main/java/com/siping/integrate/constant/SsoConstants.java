package com.siping.integrate.constant;

import org.stroma.framework.core.collection.Key;
import org.stroma.framework.core.setting.SsoSettings.SsoSite;

public class SsoConstants {
    public static final Key<SsoSite> JOBCONSOLE = Key.key("JOBCONSOLE", SsoSite.class);
    public static final Key<SsoSite> SERVICE = Key.key("SERVICE", SsoSite.class);
}
