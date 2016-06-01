package com.siping.smartone.wms.request;

import java.io.Serializable;
import java.util.List;

public class OrderPickQueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<String> orderNumbers;
    private Integer storageId;
    private Integer loginId;

    public List<String> getOrderNumbers() {
        return orderNumbers;
    }

    public void setOrderNumbers(List<String> orderNumbers) {
        this.orderNumbers = orderNumbers;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public Integer getStorageId() {
        return storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }
}
