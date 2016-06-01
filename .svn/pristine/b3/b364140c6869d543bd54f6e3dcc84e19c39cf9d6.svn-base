package com.siping.smartone.invoicing.sell.order.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeleteSellOrderRequest implements Serializable {
    private static final long serialVersionUID = -1326269612465873857L;
    private List<String> orderNumberList = new ArrayList<String>();
    private String userId;

    public DeleteSellOrderRequest() {
    }

    public DeleteSellOrderRequest(List<String> orderNumberList) {
        this.orderNumberList.clear();
        this.orderNumberList.addAll(orderNumberList);
    }

    public List<String> getOrderNumberList() {
        return orderNumberList;
    }

    public void setOrderNumberList(List<String> orderNumberList) {
        this.orderNumberList = orderNumberList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
