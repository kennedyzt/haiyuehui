package com.siping.smartone.report.response;

import java.io.Serializable;

public class OutwarehouseResponse implements Serializable {

    private static final long serialVersionUID = -2722212273003481935L;

    private String productNo;
    private String barcode;
    private String productName;
    private String productType;
    private String storageNo;
    private String storageName;
    private String unitName;
    private Double counts;

    public String getProductNo() {
        return productNo;
    }
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
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
    public Double getCounts() {
        return counts;
    }
    public void setCounts(Double counts) {
        this.counts = counts;
    }
}
