package com.siping.smartone.inventory.response;

import java.io.Serializable;

public class GetInventoryCheckWithMaterialAndLocationInfo implements Serializable {

    private static final long serialVersionUID = -7013308395681695365L;
    private String checkNumber;
    private String id;
    private String materialId;
    private String materialNo;
    private String materialName;
    private String barcode;
    private String materialType;
    private String unitName;
    private String locationNo;
    private String batchNumber;
    private String expirationDate;
    private String productionDate;
    private Double inventoryNumber;
    private Double actualNumber;
    private Double diffNumber;

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getLocationNo() {
        return locationNo;
    }

    public void setLocationNo(String locationNo) {
        this.locationNo = locationNo;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public Double getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(Double inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public Double getActualNumber() {
        return actualNumber;
    }

    public void setActualNumber(Double actualNumber) {
        this.actualNumber = actualNumber;
    }

    public Double getDiffNumber() {
        return diffNumber;
    }

    public void setDiffNumber(Double diffNumber) {
        this.diffNumber = diffNumber;
    }
}
