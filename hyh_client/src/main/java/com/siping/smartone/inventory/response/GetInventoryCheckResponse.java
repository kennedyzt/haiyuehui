package com.siping.smartone.inventory.response;

import java.io.Serializable;
import java.util.List;

public class GetInventoryCheckResponse implements Serializable {
    private static final long serialVersionUID = -7506183763209629511L;
    private String id;
    private String checkNumber;
    private Integer checkStorage;
    private String checkStorageName;
    private String billsDate;
    private String summary;
    private Boolean isDraft;
    private Boolean isIoss;
    private Double totalPrice;
    private Double giftPrice;
    private String createDate;
    private Integer createBy;
    private String createName;
    private Integer auditor;
    private String auditorName;
    private String auditDate;
    private Integer owner;
    private String ownerName;
    private String totalNumber;
    private List<GetInventoryCheckItemResponse> checkItems;

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Integer getCheckStorage() {
        return checkStorage;
    }

    public void setCheckStorage(Integer checkStorage) {
        this.checkStorage = checkStorage;
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

    public Boolean getIsIoss() {
        return isIoss;
    }

    public void setIsIoss(Boolean isIoss) {
        this.isIoss = isIoss;
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

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<GetInventoryCheckItemResponse> getCheckItems() {
        return checkItems;
    }

    public void setCheckItems(List<GetInventoryCheckItemResponse> checkItems) {
        this.checkItems = checkItems;
    }

    public String getCheckStorageName() {
        return checkStorageName;
    }

    public void setCheckStorageName(String checkStorageName) {
        this.checkStorageName = checkStorageName;
    }

    public String getBillsDate() {
        return billsDate;
    }

    public void setBillsDate(String billsDate) {
        this.billsDate = billsDate;
    }
}
