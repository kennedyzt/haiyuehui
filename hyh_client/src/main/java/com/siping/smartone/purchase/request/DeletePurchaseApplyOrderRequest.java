package com.siping.smartone.purchase.request;

import java.io.Serializable;
import java.util.List;

public class DeletePurchaseApplyOrderRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    // 采购申请单的单据号
    private List<String> orderNumbers;

    private String userId;

    public List<String> getOrderNumbers() {
        return orderNumbers;
    }

    public void setOrderNumber(List<String> orderNumbers) {
        this.orderNumbers = orderNumbers;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}