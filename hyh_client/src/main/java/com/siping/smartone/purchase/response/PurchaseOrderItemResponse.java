package com.siping.smartone.purchase.response;

import java.io.Serializable;

import com.siping.smartone.purchase.PurchaseItemBase;

public class PurchaseOrderItemResponse extends PurchaseItemBase implements Serializable {

    private static final long serialVersionUID = 6288341007065989798L;

    // 物料单价
    private Double purchasePrice;
    // 折扣（优惠率）
    private Double discountRate;
    // 折扣（优惠率）
    private Double afterDiscount;
    // 税率
    private Double taxRate;
    // 税额
    private Double tax;
    // 总计=数量*单价+税额-折扣%*单价*数量
    private Double amount;
    // 是否赠品
    private Boolean isGift;
    // 收获数量
    private Double receiptCounts;
    // 是否启用批次
    private Boolean isBatch;
    
    private Integer rowNumber;
    
    private Double notReferencedAmount;
    
    private Integer readyStatus; //预收货单使用该字段
    
    private Double currencyPrice;

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getIsGift() {
        return isGift;
    }

    public void setIsGift(Boolean isGift) {
        this.isGift = isGift;
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

    public Boolean getIsBatch() {
        return isBatch;
    }

    public void setIsBatch(Boolean isBatch) {
        this.isBatch = isBatch;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Double getNotReferencedAmount() {
        return notReferencedAmount;
    }

    public void setNotReferencedAmount(Double notReferencedAmount) {
        this.notReferencedAmount = notReferencedAmount;
    }

    public Integer getReadyStatus() {
        return readyStatus;
    }

    public void setReadyStatus(Integer readyStatus) {
        this.readyStatus = readyStatus;
    }

    public Double getCurrencyPrice() {
        return currencyPrice;
    }

    public void setCurrencyPrice(Double currencyPrice) {
        this.currencyPrice = currencyPrice;
    }
}