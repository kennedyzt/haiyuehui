package com.siping.smartone.security.constants;

import org.stroma.framework.core.collection.Key;

public class SecuritySessionConstants {
    public static final Key<Integer> LOGGED_ID = Key.intKey("usr:loggedId");
    public static final Key<Boolean> LOGGED_IN = Key.booleanKey("usr:loggedIn");
    public static final Key<String> LOGGED_USER = Key.stringKey("usr:loggedUser");
    public static final Key<String> LOGGED_USER_NAME = Key.stringKey("usr:loggedUserName");
    public static final Key<Integer> USER_GROUPID = Key.intKey("usr:userGroupId");
    public static final Key<Integer> USER_EMPLOYEE_ID = Key.intKey("usr:userEmployeeId");
    public static final Key<String> LOGIN_REDIRECT_DESTINATION_URL = Key.stringKey("admin:loginRedirectDestinationUrl");
    public static final Key<String> CURRENTSITE = Key.stringKey("common:currentSite");
    public static final Key<Integer> LOGGED_USER_TYPE = Key.intKey("usr:loggedUserType");
}
