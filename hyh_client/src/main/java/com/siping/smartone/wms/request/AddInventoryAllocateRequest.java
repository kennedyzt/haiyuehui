package com.siping.smartone.wms.request;

import java.io.Serializable;

public class AddInventoryAllocateRequest implements Serializable {

    private static final long serialVersionUID = -5078763590379210494L;
    private String allocateNumber;
    private String billsDate;
    private Integer materialId;
    private String materialNo;
    private String batchNumber;
    private String productionDate;
    private String expirationDate;
    private Integer fromLocationId;
    private String fromLocationNo;
    private String fromStorageId;
    private Integer toLocationId;
    private String toLocationNo;
    private String toStorageId;
    private String counts;
    private Integer createBy;
    private String ip; // 跟业务无关，用于记录日志
    private String mac; // 跟业务无关，用于记录日志

    public String getAllocateNumber() {
        return allocateNumber;
    }

    public void setAllocateNumber(String allocateNumber) {
        this.allocateNumber = allocateNumber;
    }

    public String getBillsDate() {
        return billsDate;
    }

    public void setBillsDate(String billsDate) {
        this.billsDate = billsDate;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getFromLocationId() {
        return fromLocationId;
    }

    public void setFromLocationId(Integer fromLocationId) {
        this.fromLocationId = fromLocationId;
    }

    public Integer getToLocationId() {
        return toLocationId;
    }

    public void setToLocationId(Integer toLocationId) {
        this.toLocationId = toLocationId;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public String getFromLocationNo() {
        return fromLocationNo;
    }

    public void setFromLocationNo(String fromLocationNo) {
        this.fromLocationNo = fromLocationNo;
    }

    public String getToLocationNo() {
        return toLocationNo;
    }

    public void setToLocationNo(String toLocationNo) {
        this.toLocationNo = toLocationNo;
    }

    public String getFromStorageId() {
        return fromStorageId;
    }

    public void setFromStorageId(String fromStorageId) {
        this.fromStorageId = fromStorageId;
    }

    public String getToStorageId() {
        return toStorageId;
    }

    public void setToStorageId(String toStorageId) {
        this.toStorageId = toStorageId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
