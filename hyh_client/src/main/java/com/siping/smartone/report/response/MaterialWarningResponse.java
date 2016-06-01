package com.siping.smartone.report.response;

import java.io.Serializable;

public class MaterialWarningResponse implements Serializable {

    private static final long serialVersionUID = -781387859647899780L;
    private String materialNo;
    private String barcode;
    private String materialName;
    private String materialTypeName;
    private String storageNo;
    private String storageName;
    private String unitName;
    private Double inventoryNumber;
    private Double warningNumber;
    private Double minInventory;
    private Double maxInventory;
    private Double diffNumber;

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public String getStorageNo() {
        return storageNo;
    }

    public void setStorageNo(String storageNo) {
        this.storageNo = storageNo;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Double getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(Double inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public Double getWarningNumber() {
        return warningNumber;
    }

    public void setWarningNumber(Double warningNumber) {
        this.warningNumber = warningNumber;
    }

    public Double getDiffNumber() {
        return diffNumber;
    }

    public void setDiffNumber(Double diffNumber) {
        this.diffNumber = diffNumber;
    }

    public Double getMinInventory() {
        return minInventory;
    }

    public void setMinInventory(Double minInventory) {
        this.minInventory = minInventory;
    }

    public Double getMaxInventory() {
        return maxInventory;
    }

    public void setMaxInventory(Double maxInventory) {
        this.maxInventory = maxInventory;
    }

}
