package com.siping.smartone.wms.response;

import java.io.Serializable;
import java.util.List;

import com.siping.smartone.wms.ReadyShipmentsBase;

public class ReadyShipmentsResponse extends ReadyShipmentsBase implements Serializable {
    private static final long serialVersionUID = 1L;
    private String partnerCode;
    private String partnerName;
    private String contactsFirstName;
    private String contactsLastName;
    private String contactsMobilephone;
    private Double counts;
    private String ownerName;
    private String storageName;
    private String auditNumber;
    private String noAuditNumber;
    private String shopName;
    private Double materialsWeight; // 商品总总量
    private String sellOrderNumber; // 关联销售订单号
    private String trackingNo; // 运单号
    private String customsCode; // 通过条码
    private List<ReadyShipmentsItemResponse> items;

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

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Double getCounts() {
        return counts;
    }

    public void setCounts(Double counts) {
        this.counts = counts;
    }

    public List<ReadyShipmentsItemResponse> getItems() {
        return items;
    }

    public void setItems(List<ReadyShipmentsItemResponse> items) {
        this.items = items;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getAuditNumber() {
        return auditNumber;
    }

    public void setAuditNumber(String auditNumber) {
        this.auditNumber = auditNumber;
    }

    public String getNoAuditNumber() {
        return noAuditNumber;
    }

    public void setNoAuditNumber(String noAuditNumber) {
        this.noAuditNumber = noAuditNumber;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSellOrderNumber() {
        return sellOrderNumber;
    }

    public void setSellOrderNumber(String sellOrderNumber) {
        this.sellOrderNumber = sellOrderNumber;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public Double getMaterialsWeight() {
        return materialsWeight;
    }

    public void setMaterialsWeight(Double materialsWeight) {
        this.materialsWeight = materialsWeight;
    }

}
