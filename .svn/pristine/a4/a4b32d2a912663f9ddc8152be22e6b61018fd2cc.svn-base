package com.siping.smartone.purchase.response;

import java.io.Serializable;

import com.siping.smartone.purchase.PurchaseItemBase;

public class PurchaseReceiptItemResponse extends PurchaseItemBase implements Serializable {

    private static final long serialVersionUID = 8225856042640175229L;

    // 批次号
    private String batchNumber;
    // 生产日期
    private String productionDate;
    // 是否启用批次号
    private Boolean isBatch;
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

    public Boolean getIsBatch() {
        return isBatch;
    }

    public void setIsBatch(Boolean isBatch) {
        this.isBatch = isBatch;
    }

    public Double getAfterDiscount() {
        return afterDiscount;
    }

    public void setAfterDiscount(Double afterDiscount) {
        this.afterDiscount = afterDiscount;
    }
}
