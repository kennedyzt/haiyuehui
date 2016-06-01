package com.siping.smartone.ec.request;

import java.io.Serializable;

public class AddCustomerRequest implements Serializable {
    private static final long serialVersionUID = 7823733796398592430L;
    private String partnerCode; // 客户编号
    private String partnerName; // 客户名称
    private String token;

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}