package com.siping.smartone.inventory.response;

import java.io.Serializable;

public class GetInventoryStatusDetailResponse implements Serializable {
    private static final long serialVersionUID = 2073853397285979976L;
    private String materialNo;
    private String materialName;
    private String billsDate; // 订单日期
    private String receiptDate; // 交货日期
    private String storageNo;
    private String stroageName;
    private String unitName;
    private Double promisedAmount; // 已承诺
    private Double orderedAmount; // 已订购
    private String billsNo; // 单据编码
    private String businessPartnerName;

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getBillsDate() {
        return billsDate;
    }

    public void setBillsDate(String billsDate) {
        this.billsDate = billsDate;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getStroageName() {
        return stroageName;
    }

    public void setStroageName(String stroageName) {
        this.stroageName = stroageName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Double getPromisedAmount() {
        return promisedAmount;
    }

    public void setPromisedAmount(Double promisedAmount) {
        this.promisedAmount = promisedAmount;
    }

    public Double getOrderedAmount() {
        return orderedAmount;
    }

    public void setOrderedAmount(Double orderedAmount) {
        this.orderedAmount = orderedAmount;
    }

    public String getStorageNo() {
        return storageNo;
    }

    public void setStorageNo(String storageNo) {
        this.storageNo = storageNo;
    }

    public String getBillsNo() {
        return billsNo;
    }

    public void setBillsNo(String billsNo) {
        this.billsNo = billsNo;
    }

    public String getBusinessPartnerName() {
        return businessPartnerName;
    }

    public void setBusinessPartnerName(String businessPartnerName) {
        this.businessPartnerName = businessPartnerName;
    }
}
