package com.siping.smartone.invoicing.sell.order.response;

import java.io.Serializable;

public class GetSellOrderListResponse implements Serializable {
    private static final long serialVersionUID = -1977232815053669853L;
    private String orderNumber;
    private String sellFlowId;
    private Boolean isDraft;
    private String billsDate; // 单据日期
    private Integer partenerId;
    private String partnerCode; // 客户编号
    private String partnerName;
    private String summary;
    private Double counts;
    private Double totalPrice;
    private Integer storageId;
    private String storageName;
    private String nickname;
    private String contactsFirstName;
    private String contactsLastName;
    private String contactsMobilephone;
    private String shipmentsDate;
    private String fromBillsNo; //原始订单号

    public String getShipmentsDate() {
        return shipmentsDate;
    }

    public void setShipmentsDate(String shipmentsDate) {
        this.shipmentsDate = shipmentsDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getSellFlowId() {
        return sellFlowId;
    }

    public void setSellFlowId(String sellFlowId) {
        this.sellFlowId = sellFlowId;
    }

    public Boolean getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(Boolean isDraft) {
        this.isDraft = isDraft;
    }

    public String getBillsDate() {
        return billsDate;
    }

    public void setBillsDate(String billsDate) {
        this.billsDate = billsDate;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Double getCounts() {
        return counts;
    }

    public void setCounts(Double counts) {
        this.counts = counts;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getContactsFirstName() {
        return contactsFirstName;
    }

    public void setContactsFirstName(String contactsFirstName) {
        this.contactsFirstName = contactsFirstName;
    }

    public String getContactsLastName() {
        return contactsLastName;
    }

    public void setContactsLastName(String contactsLastName) {
        this.contactsLastName = contactsLastName;
    }

    public String getContactsMobilephone() {
        return contactsMobilephone;
    }

    public void setContactsMobilephone(String contactsMobilephone) {
        this.contactsMobilephone = contactsMobilephone;
    }

    public Integer getPartenerId() {
        return partenerId;
    }

    public void setPartenerId(Integer partenerId) {
        this.partenerId = partenerId;
    }

    public Integer getStorageId() {
        return storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    public String getFromBillsNo() {
        return fromBillsNo;
    }

    public void setFromBillsNo(String fromBillsNo) {
        this.fromBillsNo = fromBillsNo;
    }
}
