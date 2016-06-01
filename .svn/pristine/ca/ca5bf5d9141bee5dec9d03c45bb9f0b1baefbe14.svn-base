package com.siping.smartone.purchase.request;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class PurchaseOrderRequest implements Serializable {

    private static final long serialVersionUID = 1319465845761358782L;

    // 采购单单据编号
    private String purchaseOrderNumber;
    // 来自单据
    private String fromBillsNo;
    // 供应商id
    private Integer supplierId;
    // 单据日期
    private String billDate;
    // 收货日期
    private String receiptDate;
    // 入库仓库
    private Integer inBoundStorage;
    // 单据摘要
    private String summary;
    // 是否是草稿
    private Boolean isDraft;

    /**
     * 表尾信息
     */
    // 总金额
    private Double totalAmount;
    // 赠品金额
    private Double giftAmount;
    // 优惠金额
    private Double discountAmount;
    // 应付金额=总金额-优惠金额
    private Double payAmount;

    // 创建人id
    private int createBy;
    // 审核人id
    private int auditor;
    // 采购订单的所有人id
    private int owner;
    // 更新人id
    private Integer updateBy;
    // 表体对象列表
    private List<PurchaseOrderItemRequest> items;
    // 采购订单币种
    private String currency;
    // 汇率
    private Double exchangeRate;
    
    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getFromBillsNo() {
        return fromBillsNo;
    }

    public void setFromBillsNo(String fromBillsNo) {
        this.fromBillsNo = fromBillsNo;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Integer getInBoundStorage() {
        return inBoundStorage;
    }

    public void setInBoundStorage(Integer inBoundStorage) {
        this.inBoundStorage = inBoundStorage;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(Double giftAmount) {
        this.giftAmount = giftAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public int getAuditor() {
        return auditor;
    }

    public void setAuditor(int auditor) {
        this.auditor = auditor;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public List<PurchaseOrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<PurchaseOrderItemRequest> items) {
        this.items = items;
    }

    public Boolean getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(Boolean isDraft) {
        this.isDraft = isDraft;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public void setItemList(PurchaseOrderItemRequest[] itemList) {
        this.items = Arrays.asList(itemList);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
