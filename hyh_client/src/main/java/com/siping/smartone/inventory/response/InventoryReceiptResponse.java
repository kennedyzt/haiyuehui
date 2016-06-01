package com.siping.smartone.inventory.response;

import java.io.Serializable;
import java.util.List;

public class InventoryReceiptResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String receiptNumber;

    private String fromBillsNo;

    private Integer inboundStorageId;
    private String startStation;
    private String endStation;
    private String transportationType;

    private String storageName;

    private String billsDate;

    private String summary;

    private Boolean isDraft = false;

    private Double totalPrice;

    private Double giftPrice;

    private Integer createBy;

    private Integer auditor;

    private String auditDate;

    private Integer owner;

    private String ownerName;

    private Integer consignee;

    private String consigneeName; // 收货人即收货单所有人

    private Integer status;

    // 关联库存收货单项
    private Double counts;
    private Double receiptCounts;

    private List<InventoryReceiptItemResponse> inventoryReceiptItemResponses;

    public List<InventoryReceiptItemResponse> getInventoryReceiptItemResponses() {
        return inventoryReceiptItemResponses;
    }

    public void setInventoryReceiptItemResponses(List<InventoryReceiptItemResponse> inventoryReceiptItemResponses) {
        this.inventoryReceiptItemResponses = inventoryReceiptItemResponses;
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

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
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

    public String getBillsDate() {
        return billsDate;
    }

    public void setBillsDate(String billsDate) {
        this.billsDate = billsDate;
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

    public Integer getConsignee() {
        return consignee;
    }

    public void setConsignee(Integer consignee) {
        this.consignee = consignee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getFromBillsNo() {
        return fromBillsNo;
    }

    public void setFromBillsNo(String fromBillsNo) {
        this.fromBillsNo = fromBillsNo;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getTransportationType() {
        return transportationType;
    }

    public void setTransportationType(String transportationType) {
        this.transportationType = transportationType;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

}
