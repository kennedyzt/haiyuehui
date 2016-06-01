package com.siping.smartone.invoicing.sell.shipments.request;

import java.io.Serializable;
import java.util.List;

public class AddSellShipmentsRequest implements Serializable {
    private static final long serialVersionUID = -7394267090984058566L;
    private String shipmentsNumber; // 添加时，此字段不需要赋值，此为系统生成
    private String partnerId; // 客户
    private String outboundStorage; // 出库仓库
    private String summary; // 单据摘要
    private Boolean isDraft; // 是否为草稿
    private Double totalPrice; // 总计金额
    private Double giftPrice; // 赠品金额
    private Double favorablePrice; // 优惠
    private Double gatheringPrice; // 应收总金额
    private String createBy; // 单据创建人
    private String updateBy;
    private String billsDate; // 单据日期
    private String auditor; // 审核人
    private String owner; // 拥有人
    private List<AddSellShipmentsItemRequest> shipmentsItemList; // 销售发货单项
    private String fromBillsNo; // 上级单据编号，只有复制到或者复制从才会为该字段赋值
    private String ip; // 跟业务无关，用于记录日志
    private String mac; // 跟业务无关，用于记录日志


    public String getShipmentsNumber() {
        return shipmentsNumber;
    }

    public void setShipmentsNumber(String shipmentsNumber) {
        this.shipmentsNumber = shipmentsNumber;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public List<AddSellShipmentsItemRequest> getShipmentsItemList() {
        return shipmentsItemList;
    }

    public void setShipmentsItemList(List<AddSellShipmentsItemRequest> shipmentsItemList) {
        this.shipmentsItemList = shipmentsItemList;
    }

    public String getBillsDate() {
        return billsDate;
    }

    public void setBillsDate(String billsDate) {
        this.billsDate = billsDate;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getFromBillsNo() {
        return fromBillsNo;
    }

    public void setFromBillsNo(String fromBillsNo) {
        this.fromBillsNo = fromBillsNo;
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