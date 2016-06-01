package com.siping.smartone.invoicing.sell.shipments.response;

import java.io.Serializable;
import java.util.List;

public class GetSellShipmentsResponse implements Serializable {
    private static final long serialVersionUID = -7394267090984058566L;
    private String shipmentsNumber; // 添加时，此字段不需要赋值，此为系统生成
    private String sellFlowId; // 销售统一编号，用于跟踪数据流向
    private String partnerId; // 客户
    private String partnerCode;
    private String partnerName;
    private String partnerContactsFirstName;
    private String partnerContactsLastName;
    private String partnerContactsMobilephone;
    private String outboundStorage; // 出库仓库
    private String storageName; // 出库仓库名
    private String summary; // 单据摘要
    private Boolean isDraft; // 是否为草稿
    private Double totalPrice; // 总计金额
    private Double giftPrice; // 赠品金额
    private Double favorablePrice; // 优惠
    private Double gatheringPrice; // 应收总金额
    private String createBy; // 单据创建人
    private String createDate;
    private String auditor;
    private String auditDate;
    private String owner;
    private String ownerName;
    private String billsDate; // 单据日期
    private String franchiseeId; // 加盟商
    private String franchiseeNo; // 加盟商编号
    private String franchiseeName; // 加盟商名称
    private List<GetSellShipmentsItemResponse> shipmentsItemList; // 销售订单项

    public String getShipmentsNumber() {
        return shipmentsNumber;
    }

    public void setShipmentsNumber(String shipmentsNumber) {
        this.shipmentsNumber = shipmentsNumber;
    }

    public String getSellFlowId() {
        return sellFlowId;
    }

    public void setSellFlowId(String sellFlowId) {
        this.sellFlowId = sellFlowId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getOutboundStorage() {
        return outboundStorage;
    }

    public void setOutboundStorage(String outboundStorage) {
        this.outboundStorage = outboundStorage;
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

    public Double getFavorablePrice() {
        return favorablePrice;
    }

    public void setFavorablePrice(Double favorablePrice) {
        this.favorablePrice = favorablePrice;
    }

    public Double getGatheringPrice() {
        return gatheringPrice;
    }

    public void setGatheringPrice(Double gatheringPrice) {
        this.gatheringPrice = gatheringPrice;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<GetSellShipmentsItemResponse> getShipmentsItemList() {
        return shipmentsItemList;
    }

    public void setShipmentsItemList(List<GetSellShipmentsItemResponse> shipmentsItemList) {
        this.shipmentsItemList = shipmentsItemList;
    }

    public String getBillsDate() {
        return billsDate;
    }

    public void setBillsDate(String billsDate) {
        this.billsDate = billsDate;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerContactsFirstName() {
        return partnerContactsFirstName;
    }

    public void setPartnerContactsFirstName(String partnerContactsFirstName) {
        this.partnerContactsFirstName = partnerContactsFirstName;
    }

    public String getPartnerContactsLastName() {
        return partnerContactsLastName;
    }

    public void setPartnerContactsLastName(String partnerContactsLastName) {
        this.partnerContactsLastName = partnerContactsLastName;
    }

    public String getPartnerContactsMobilephone() {
        return partnerContactsMobilephone;
    }

    public void setPartnerContactsMobilephone(String partnerContactsMobilephone) {
        this.partnerContactsMobilephone = partnerContactsMobilephone;
    }

    public String getFranchiseeId() {
        return franchiseeId;
    }

    public void setFranchiseeId(String franchiseeId) {
        this.franchiseeId = franchiseeId;
    }

    public String getFranchiseeNo() {
        return franchiseeNo;
    }

    public void setFranchiseeNo(String franchiseeNo) {
        this.franchiseeNo = franchiseeNo;
    }

    public String getFranchiseeName() {
        return franchiseeName;
    }

    public void setFranchiseeName(String franchiseeName) {
        this.franchiseeName = franchiseeName;
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
}
