package com.siping.smartone.businesspartner.request;

import java.io.Serializable;

public class BusinessPartnerBatchAddErrorRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer errorRow;
    private Integer errorCell;
    private String errorMessage;
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
    private String bankAccount;
    private Boolean isDelete;
    private Boolean isEnable;
    private String contactsLastName;
    private String contactsFirstName;
    private String contactsGender;
    private String contactsPhone;
    private String contactsMobilephone;
    private String contactsDesription;
    private String address;

    public Integer getErrorRow() {
        return errorRow;
    }

    public void setErrorRow(Integer errorRow) {
        this.errorRow = errorRow;
    }

    public Integer getErrorCell() {
        return errorCell;
    }

    public void setErrorCell(Integer errorCell) {
        this.errorCell = errorCell;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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

    public String getContactsFirstName() {
        return contactsFirstName;
    }

    public void setContactsFirstName(String contactsFirstName) {
        this.contactsFirstName = contactsFirstName;
    }

    public String getContactsGender() {
        return contactsGender;
    }

    public void setContactsGender(String contactsGender) {
        this.contactsGender = contactsGender;
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

    public String getContactsDesription() {
        return contactsDesription;
    }

    public void setContactsDesription(String contactsDesription) {
        this.contactsDesription = contactsDesription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
