package com.siping.smartone.inventory.request;

import java.io.Serializable;

public class ChangeStatusParam implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer loginId;
    private String orderNumber;

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

}
