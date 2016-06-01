package com.siping.smartone.purchase.request;

import java.io.Serializable;
import java.util.List;

public class PurchaseReturnsRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String billsDate;
    private String fromBillsNo;
    private String returnsNumber;
    private String purchaseFlowId;
    private Integer partnerId;
    private Integer outboundStorageId;
    private String summary;
    private Boolean isDraft = false;
    private Double totalPrice;
    private Double giftPrice;
    private Double payPrice;
    private Double favorablePrice;
    private Integer createBy;
    private Integer auditor;
    private String auditDate;
    private Integer owner;

    private List<PurchaseReturnsItemRequest> purchaseReturnsItemRequests;
    
    private String ip; // 跟业务无关，用于记录日志
    private String mac; // 跟业务无关，用于记录日志

    public List<PurchaseReturnsItemRequest> getPurchaseReturnsItemRequests() {
        return purchaseReturnsItemRequests;
    }

    public void setPurchaseReturnsItemRequests(List<PurchaseReturnsItemRequest> purchaseReturnsItemRequests) {
        this.purchaseReturnsItemRequests = purchaseReturnsItemRequests;
    }

    public String getBillsDate() {
        return billsDate;
    }

    public String getFromBillsNo() {
        return fromBillsNo;
    }

    public void setFromBillsNo(String fromBillsNo) {
        this.fromBillsNo = fromBillsNo;
    }

    public void setBillsDate(String billsDate) {
        this.billsDate = billsDate;
    }

    public String getReturnsNumber() {
        return returnsNumber;
    }

    public void setReturnsNumber(String returnsNumber) {
        this.returnsNumber = returnsNumber;
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

    public Integer getOutboundStorageId() {
        return outboundStorageId;
    }

    public void setOutboundStorageId(Integer outboundStorageId) {
        this.outboundStorageId = outboundStorageId;
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

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    public Double getFavorablePrice() {
        return favorablePrice;
    }

    public void setFavorablePrice(Double favorablePrice) {
        this.favorablePrice = favorablePrice;
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
