package com.siping.smartone.inventory.request;

import java.io.Serializable;
import java.util.Date;

public class GetInventoryCheckRequest implements Serializable {

    private static final long serialVersionUID = 6963759141282305226L;
    private String checkNumber;
    private Integer checkStorage;
    private String summary;
    private Boolean isDraft;
    private Boolean isIoss;
    private Double totalPrice;
    private Double giftPrice;
    private Date createDate;
    private Integer createBy;
    private Integer auditor;
    private Date auditDate;
    private Integer owner;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
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

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }
}
