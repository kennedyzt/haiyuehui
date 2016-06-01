package com.siping.smartone.invoicing.sell.shipments.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeleteSellShipmentsRequest implements Serializable {
    private static final long serialVersionUID = -2747549355828755509L;
    private List<String> shipmentsNumberList = new ArrayList<String>();
    private String userId;

    public DeleteSellShipmentsRequest() {
    }

    public DeleteSellShipmentsRequest(List<String> shipmentsNumberList) {
        this.shipmentsNumberList.clear();
        this.shipmentsNumberList.addAll(shipmentsNumberList);
    }

    public List<String> getShipmentsNumberList() {
        return shipmentsNumberList;
    }

    public void setShipmentsNumberList(List<String> shipmentsNumberList) {
        this.shipmentsNumberList = shipmentsNumberList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
