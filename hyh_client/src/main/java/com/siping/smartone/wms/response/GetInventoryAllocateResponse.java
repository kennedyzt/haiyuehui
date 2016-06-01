package com.siping.smartone.wms.response;

import java.io.Serializable;

public class GetInventoryAllocateResponse implements Serializable {

    private static final long serialVersionUID = 7386665522696614928L;
    private String allocateNumber;
    private String billsDate;
    private Integer materialId;
    private String materialNo;
    private String materialName;
    private String barcode;
    private String specificationsModel;
    private Integer unitId;
    private String unitName;
    private String batchNumber;
    private String productionDate;
    private String expirationDate;
    private Integer fromLocationId;
    private String fromLocationName;
    private Integer fromStroageId;
    private String fromStroageNo;
    private String fromStroageName;
    private Integer toLocationId;
    private String toLocationName;
    private Integer toStroageId;
    private String toStroageNo;
    private String toStroageName;

    private String counts;
    private Integer createBy;
    private String createName;

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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getSpecificationsModel() {
        return specificationsModel;
    }

    public void setSpecificationsModel(String specificationsModel) {
        this.specificationsModel = specificationsModel;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
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

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getFromLocationName() {
        return fromLocationName;
    }

    public void setFromLocationName(String fromLocationName) {
        this.fromLocationName = fromLocationName;
    }

    public String getToLocationName() {
        return toLocationName;
    }

    public void setToLocationName(String toLocationName) {
        this.toLocationName = toLocationName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Integer getFromStroageId() {
        return fromStroageId;
    }

    public void setFromStroageId(Integer fromStroageId) {
        this.fromStroageId = fromStroageId;
    }

    public String getFromStroageNo() {
        return fromStroageNo;
    }

    public void setFromStroageNo(String fromStroageNo) {
        this.fromStroageNo = fromStroageNo;
    }

    public String getFromStroageName() {
        return fromStroageName;
    }

    public void setFromStroageName(String fromStroageName) {
        this.fromStroageName = fromStroageName;
    }

    public Integer getToStroageId() {
        return toStroageId;
    }

    public void setToStroageId(Integer toStroageId) {
        this.toStroageId = toStroageId;
    }

    public String getToStroageNo() {
        return toStroageNo;
    }

    public void setToStroageNo(String toStroageNo) {
        this.toStroageNo = toStroageNo;
    }

    public String getToStroageName() {
        return toStroageName;
    }

    public void setToStroageName(String toStroageName) {
        this.toStroageName = toStroageName;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }
}
