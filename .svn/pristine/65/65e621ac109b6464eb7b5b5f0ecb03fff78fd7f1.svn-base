package com.siping.smartone.inventory.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeleteInventoryShipmentsRequest implements Serializable {
    private static final long serialVersionUID = -1326269612465873857L;
    private List<String> shipmentsNumberList = new ArrayList<String>();
    private Integer deleteUserId;

    public DeleteInventoryShipmentsRequest() {
    }

    public DeleteInventoryShipmentsRequest(List<String> shipmentsNumberList) {
        this.shipmentsNumberList.clear();
        this.shipmentsNumberList.addAll(shipmentsNumberList);
    }

    public List<String> getShipmentsNumberList() {
        return shipmentsNumberList;
    }

    public void setShipmentsNumberList(List<String> shipmentsNumberList) {
        this.shipmentsNumberList = shipmentsNumberList;
    }

    public Integer getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(Integer deleteUserId) {
        this.deleteUserId = deleteUserId;
    }
}