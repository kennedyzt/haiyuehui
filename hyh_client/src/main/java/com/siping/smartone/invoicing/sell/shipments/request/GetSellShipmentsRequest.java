package com.siping.smartone.invoicing.sell.shipments.request;

import java.io.Serializable;

public class GetSellShipmentsRequest implements Serializable {
    private static final long serialVersionUID = -9160736006395318565L;
    private String shipmentsNumber;
    private String sellFlowId;

    public GetSellShipmentsRequest() {
    }

    public GetSellShipmentsRequest(String shipmentsNumber, String sellFlowId) {
        this.shipmentsNumber = shipmentsNumber;
        this.sellFlowId = sellFlowId;
    }

    public String getShipmentsNumber() {
        return shipmentsNumber;
    }

    public void setShipmentsNumber(String shipmentsNumber) {
        this.shipmentsNumber = shipmentsNumber;
    }

    public String getSellFlowId() {
        return sellFlowId;
    }

    public void setSellFlowId(String sellFlowId) {
        this.sellFlowId = sellFlowId;
    }

}
