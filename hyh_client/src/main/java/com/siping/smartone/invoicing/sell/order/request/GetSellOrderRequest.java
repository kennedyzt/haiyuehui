package com.siping.smartone.invoicing.sell.order.request;

import java.io.Serializable;

public class GetSellOrderRequest implements Serializable {
    private static final long serialVersionUID = -9160736006395318565L;
    private String orderNumber;
    private String sellFlowId;

    public GetSellOrderRequest() {
    }

    public GetSellOrderRequest(String orderNumber, String sellFlowId) {
        this.orderNumber = orderNumber;
        this.sellFlowId = sellFlowId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getSellFlowId() {
        return sellFlowId;
    }

    public void setSellFlowId(String sellFlowId) {
        this.sellFlowId = sellFlowId;
    }

}
