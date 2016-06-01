package com.siping.smartone.invoicing.sell.returns.request;

import java.io.Serializable;
import java.util.List;

public class AddSellReturnsRequest implements Serializable {
    private static final long serialVersionUID = -7394267090984058566L;
    private String returnsNumber; // 添加时，此字段不需要赋值，此为系统生成
    private Integer partnerId; // 客户
    private String inboundStorage; // 入库仓库
    private String summary; // 单据摘要
    private Boolean isDraft; // 是否为草稿
    private Double totalPrice; // 总计金额
    private Double giftPrice; // 赠品金额
    private Double favorablePrice; // 优惠
    private Double payPrice; // 应付总金额
    private String createBy; // 单据创建人
    private String updateBy;
    private String billsDate; // 单据日期
    private String auditor; // 审核人
    private String owner; // 拥有人
    private List<AddSellReturnsItemRequest> returnsItemList; // 销售退货单项
    private String fromBillsNo; // 上级单据编号，只有复制到或者复制从才会为该字段赋值

    public String getReturnsNumber() {
        return returnsNumber;
    }

    public void setReturnsNumber(String returnsNumber) {
        this.returnsNumber = returnsNumber;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public String getInboundStorage() {
        return inboundStorage;
    }

    public void setInboundStorage(String inboundStorage) {
        this.inboundStorage = inboundStorage;
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

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
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

    public List<AddSellReturnsItemRequest> getReturnsItemList() {
        return returnsItemList;
    }

    public void setReturnsItemList(List<AddSellReturnsItemRequest> returnsItemList) {
        this.returnsItemList = returnsItemList;
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
}