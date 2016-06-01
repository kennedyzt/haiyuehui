package com.siping.smartone.wms;

import java.io.Serializable;

public class OrderPickBase implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String orderNumber;
    
    private String fromBillsNo;

    private String billsDate;

    private String owner;

    private Integer storageId;

    private Integer skuCounts;

    private Double materialCounts;

    private String createDate;

    private Integer createBy;

    private String updateDate;

    private Integer updateBy;
    
    private Integer status;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getBillsDate() {
        return billsDate;
    }

    public void setBillsDate(String billsDate) {
        this.billsDate = billsDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getStorageId() {
        return storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    public Integer getSkuCounts() {
        return skuCounts;
    }

    public void setSkuCounts(Integer skuCounts) {
        this.skuCounts = skuCounts;
    }

    public Double getMaterialCounts() {
        return materialCounts;
    }

    public void setMaterialCounts(Double materialCounts) {
        this.materialCounts = materialCounts;
    }


    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getFromBillsNo() {
        return fromBillsNo;
    }

    public void setFromBillsNo(String fromBillsNo) {
        this.fromBillsNo = fromBillsNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
