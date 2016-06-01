package com.siping.smartone.purchase.request;

import java.io.Serializable;
import java.util.List;

public class PurchaseReceiptRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    // 采购收货单单据编号
    private String receiptNumber;
    private String fromBillsNo;
    // 流程编号
    private String purchaseFlowId;
    // 供应商id
    private Integer partnerId;
    // 单据日期
    private String billDate;
    // 入库仓库
    private Integer inboundStorageId;
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
    // 创建日期
    private String createDate;
    // 创建人id
    private Integer createBy;
    // 审核人id
    private Integer auditor;
    // 审核日期
    private String auditDate;
    // 采购订单的所有人id
    private Integer owner;
    // 更新日期
    private String updateDate;
    // 更新人id
    private Integer updateBy;

    private List<PurchaseReceiptItemRequest> items;

    private String ip; // 跟业务无关，用于记录日志

    private String mac; // 跟业务无关，用于记录日志

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromBillsNo() {
        return fromBillsNo;
    }

    public void setFromBillsNo(String fromBillsNo) {
        this.fromBillsNo = fromBillsNo;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getPurchaseFlowId() {
        return purchaseFlowId;
    }

    public void setPurchaseFlowId(String purchaseFlowId) {
        this.purchaseFlowId = purchaseFlowId;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Integer getInboundStorageId() {
        return inboundStorageId;
    }

    public void setInboundStorageId(Integer inboundStorageId) {
        this.inboundStorageId = inboundStorageId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(Boolean isDraft) {
        this.isDraft = isDraft;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getAuditor() {
        return auditor;
    }

    public void setAuditor(Integer auditor) {
        this.auditor = auditor;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
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

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public List<PurchaseReceiptItemRequest> getItems() {
        return items;
    }

    public void setItems(List<PurchaseReceiptItemRequest> items) {
        this.items = items;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
