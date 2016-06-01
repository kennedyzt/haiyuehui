package com.siping.smartone.report.response;

import java.io.Serializable;

public class PurchaseOrderNumResponse implements Serializable {

    private static final long serialVersionUID = 8152730838977600533L;

    private String orderNo;
    private String billsDate;
    private String supplierName;
    private String productNo;
    private String barcode;
    private String productName;
    private String productType;
    private String unitName;
    private Double counts;
    private Double afterDiscount;
    private Double receiptCounts;

    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
    public Double getReceiptCounts() {
        return receiptCounts;
    }
    public void setReceiptCounts(Double receiptCounts) {
        this.receiptCounts = receiptCounts;
    }
    public Double getAfterDiscount() {
        return afterDiscount;
    }
    public void setAfterDiscount(Double afterDiscount) {
        this.afterDiscount = afterDiscount;
    }
}
