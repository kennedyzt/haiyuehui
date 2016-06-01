package com.siping.smartone.report.response;

import java.io.Serializable;

public class PurchaseAnalysisResponse implements Serializable {

    private static final long serialVersionUID = -6051976093347714276L;

    private String billsNo;
    private String billsDate;
    private String supplierName;
    private String productNo;
    private String barcode;
    private String prodcutName;
    private String typeName;
    private String unitName;
    private Double quantity;
    private Double money;
    private Double favorMoney;

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
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
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
    public String getProdcutName() {
        return prodcutName;
    }
    public void setProdcutName(String prodcutName) {
        this.prodcutName = prodcutName;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getUnitName() {
        return unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    public Double getMoney() {
        return money;
    }
    public void setMoney(Double money) {
        this.money = money;
    }
    public Double getFavorMoney() {
        return favorMoney;
    }
    public void setFavorMoney(Double favorMoney) {
        this.favorMoney = favorMoney;
    }
}
