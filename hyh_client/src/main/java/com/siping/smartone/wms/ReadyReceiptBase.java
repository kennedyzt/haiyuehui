package com.siping.smartone.wms;

import java.io.Serializable;

/**
 * 预收货单即ERP中的采购订单（去掉单价、折扣、金额、税率、税额、总计、是否赠品，增加项号，其余字段与采购订单一样）
 * @author 51102
 */
public class ReadyReceiptBase implements Serializable {

    private static final long serialVersionUID = 3987710551891548774L;

    private String orderNumber;
    private Integer partnerId;
    private Integer inBoundStorage;
    private String summary;
    private String receiptDate;
    private String billDate;
    private String createDate;
    private Integer createBy;
    private Integer auditor;
    private String auditDate;
    private Integer owner;
    private Integer readyStatus;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Integer getInBoundStorage() {
        return inBoundStorage;
    }

    public void setInBoundStorage(Integer inBoundStorage) {
        this.inBoundStorage = inBoundStorage;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
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

    public Integer getReadyStatus() {
        return readyStatus;
    }

    public void setReadyStatus(Integer readyStatus) {
        this.readyStatus = readyStatus;
    }

}
