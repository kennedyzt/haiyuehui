package com.siping.smartone.inventory.response;

import java.io.Serializable;

public class GetInventoryBatchDetailResponse implements Serializable {
    private static final long serialVersionUID = 2073853397285979976L;
    private String materialNo;
    private String materialName;
    private String billsNo; // 单据编码
    private String billsDate; // 订单日期
    private String storageNo;
    private String stroageName;
    private Double counts; // 数量
    private String direction; // 返回 "入" or "出"

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

    public String getBillsNo() {
        return billsNo;
    }

    public void setBillsNo(String billsNo) {
        this.billsNo = billsNo;
    }

    public String getBillsDate() {
        return billsDate;
    }

    public void setBillsDate(String billsDate) {
        this.billsDate = billsDate;
    }

    public String getStorageNo() {
        return storageNo;
    }

    public void setStorageNo(String storageNo) {
        this.storageNo = storageNo;
    }

    public String getStroageName() {
        return stroageName;
    }

    public void setStroageName(String stroageName) {
        this.stroageName = stroageName;
    }

    public Double getCounts() {
        return counts;
    }

    public void setCounts(Double counts) {
        this.counts = counts;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
