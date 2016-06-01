package com.siping.smartone.businesspartner.request;

import java.io.Serializable;

import com.siping.smartone.common.Common;

public class BusinessPartnerRequest extends Common implements Serializable {
    private static final long serialVersionUID = 1L;
    private String partnerCode;
    private Integer partnerType;
    private String partnerName;
    private String foreignName;
    private Integer partnerGroup;
    private String phone;
    private String mobilephone;
    private String fax;
    private String email;
    private String website;
    private String description;
    private Boolean isEnable;
    private String address;
    private String addressEn;
    private Integer businessType;
    private Integer countryRegionId;
    private String postalCode;
    private String contactsLastName;
    private String contactsFirstName;
    private Integer contactsGender;
    private String contactsPhone;
    private String contactsMobilephone;
    private String contactsFax;
    private String contactsEmail;
    private String contactsDescription;

    public String getAddressEn() {
        return addressEn;
    }

    public void setAddressEn(String addressEn) {
        this.addressEn = addressEn;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getCountryRegionId() {
        return countryRegionId;
    }

    public void setCountryRegionId(Integer countryRegionId) {
        this.countryRegionId = countryRegionId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getContactsFax() {
        return contactsFax;
    }

    public void setContactsFax(String contactsFax) {
        this.contactsFax = contactsFax;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public Integer getPartnerType() {
        return partnerType;
    }

    public String getContactsFirstName() {
        return contactsFirstName;
    }

    public void setContactsFirstName(String contactsFirstName) {
        this.contactsFirstName = contactsFirstName;
    }

    public String getContactsDescription() {
        return contactsDescription;
    }

    public void setContactsDescription(String contactsDescription) {
        this.contactsDescription = contactsDescription;
    }

    public void setPartnerType(Integer partnerType) {
        this.partnerType = partnerType;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }

    public Integer getPartnerGroup() {
        return partnerGroup;
    }

    public void setPartnerGroup(Integer partnerGroup) {
        this.partnerGroup = partnerGroup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getContactsGender() {
        return contactsGender;
    }

    public void setContactsGender(Integer contactsGender) {
        this.contactsGender = contactsGender;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public String getContactsLastName() {
        return contactsLastName;
    }

    public void setContactsLastName(String contactsLastName) {
        this.contactsLastName = contactsLastName;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getContactsMobilephone() {
        return contactsMobilephone;
    }

    public void setContactsMobilephone(String contactsMobilephone) {
        this.contactsMobilephone = contactsMobilephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactsEmail() {
        return contactsEmail;
    }

    public void setContactsEmail(String contactsEmail) {
        this.contactsEmail = contactsEmail;
    }
}