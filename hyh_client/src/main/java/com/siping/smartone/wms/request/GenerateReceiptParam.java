package com.siping.smartone.wms.request;

import java.io.Serializable;

public class GenerateReceiptParam implements Serializable {
    private static final long serialVersionUID = 1017983338144299316L;
    private String orderNumber;
    private Integer[] rowNumbers;
    private Integer loginId;
    private Integer consignee; // 收货人id
    private Integer inboundStorageId;
    private String startStation;
    private String endStation;
    private String transportationType;

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getTransportationType() {
        return transportationType;
    }

    public void setTransportationType(String transportationType) {
        this.transportationType = transportationType;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer[] getRowNumbers() {
        return rowNumbers;
    }

    public void setRowNumbers(Integer[] rowNumbers) {
        this.rowNumbers = rowNumbers;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public Integer getConsignee() {
        return consignee;
    }

    public void setConsignee(Integer consignee) {
        this.consignee = consignee;
    }

    public Integer getInboundStorageId() {
        return inboundStorageId;
    }

    public void setInboundStorageId(Integer inboundStorageId) {
        this.inboundStorageId = inboundStorageId;
    }
}
