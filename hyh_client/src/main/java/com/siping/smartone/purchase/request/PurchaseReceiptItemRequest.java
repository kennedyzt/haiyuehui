package com.siping.smartone.purchase.request;

import java.io.Serializable;

import com.siping.smartone.purchase.PurchaseItemBase;

public class PurchaseReceiptItemRequest extends PurchaseItemBase implements Serializable {

    private static final long serialVersionUID = 1866579203391966294L;

    // 批次号
    private String batchNumber;
    // 生产日期
    private String productionDate;
    // 到期日期
    private String dueDate;
    // 是否启用批次号
    private String isBatch;
    // 物料单价
    private Double purchasePrice;
    // 折扣（优惠率）
    private Double discountRate;
    // 折后金额
    private Double afterDiscount;
    // 税率
    private Double taxRate;
    // 税额
    private Double tax;
    // 总计=数量*单价+税额-折扣%*单价*数量
    private Double amount;
    // 是否赠品
    private Boolean isGift;
    // 采购备注(非物料)
    private String remark;

    // 创建日期
    private String createDate;
    // 创建人id
    private int createBy;
    private String fromBillsId; // 如果有复制从复制到，则需要为该字段赋值，上级单据编号
    private Integer rowNumber;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
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

    public String getIsBatch() {
        return isBatch;
    }

    public void setIsBatch(String isBatch) {
        this.isBatch = isBatch;
    }

    public String getFromBillsId() {
        return fromBillsId;
    }

    public void setFromBillsId(String fromBillsId) {
        this.fromBillsId = fromBillsId;
    }

    public Double getAfterDiscount() {
        return afterDiscount;
    }

    public void setAfterDiscount(Double afterDiscount) {
        this.afterDiscount = afterDiscount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }
}
