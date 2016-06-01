package com.siping.smartone.login.request;

import java.io.Serializable;

public class AddLocalRequest implements Serializable {
    private static final long serialVersionUID = 2699120013804338674L;
    private String userId;
    private String language; // 为local字符串

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
