package com.siping.smartone.inventory.request;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class InventoryReceiptRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private String receiptNumber;
    private Integer inboundStorageId;
    private String billsDate;
    private String summary;
    private Boolean isDraft = false;
    private Double totalPrice;
    private Double giftPrice; // 礼物价格
    private Integer createBy;
    private Integer auditor;
    private String auditDate;
    private Integer owner;
    private String consignee; // 收货人ID
    private Integer status; // 状态
    private List<InventoryReceiptItemRequest> inventoryReceiptItemRequests;

    public List<InventoryReceiptItemRequest> getInventoryReceiptItemRequests() {
        return inventoryReceiptItemRequests;
    }

    public void setInventoryReceiptItemRequests(List<InventoryReceiptItemRequest> inventoryReceiptItemRequests) {
        this.inventoryReceiptItemRequests = inventoryReceiptItemRequests;
    }

    public void setInventoryReceiptItemArraysRequest(InventoryReceiptItemRequest[] inventoryReceiptItemArraysRequest) {
        this.inventoryReceiptItemRequests = Arrays.asList(inventoryReceiptItemArraysRequest);
    }

    public String getBillsDate() {
        return billsDate;
    }

    public void setBillsDate(String billsDate) {
        this.billsDate = billsDate;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(Double giftPrice) {
        this.giftPrice = giftPrice;
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

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
