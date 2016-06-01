package com.siping.smartone.inventory.response;

import java.io.Serializable;

public class GetInventoryBalanceDetailResponse implements Serializable {
    private static final long serialVersionUID = 8835510815142564798L;
    private String billsNo; // 单据编号
    private String billsDate; // 单据日期
    private String businessPartnerName; // 业务伙伴
    private String stroageName; // 仓库
    private String materialNo; // 商品货号
    private String barcode; // 国际编码即条形码
    private String materialName; // 商品名称
    private String materialTypeName; // 商品分类
    private String materialUnitName; // 计量单位
    private Double inboundCount; // 入库数量
    private Double inboundCost; // 入库成本
    private Double inboundTotalPrice; // 入库总金额
    private Double outBoundCount; // 出库数量
    private Double outBoundCost; // 出库成本
    private Double outBoundTotalPrice; // 出库总金额
    private Double balanceCount; // 结余数量
    private Double balanceCost; // 结余成本
    private Double balanceTotalPrice; // 结余总金额
    private Boolean isInbound;
    private String batchNumber; // 批次

    public String getBillsNo() {
        return billsNo;
    }

    public void setBillsNo(String billsNo) {
        this.billsNo = billsNo;
    }

    public String getBillsDate() {
        return billsDate;
    }

    public void setBillsDate(String billsDate) {
        this.billsDate = billsDate;
    }

    public String getBusinessPartnerName() {
        return businessPartnerName;
    }

    public void setBusinessPartnerName(String businessPartnerName) {
        this.businessPartnerName = businessPartnerName;
    }

    public String getStroageName() {
        return stroageName;
    }

    public void setStroageName(String stroageName) {
        this.stroageName = stroageName;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public String getMaterialUnitName() {
        return materialUnitName;
    }

    public void setMaterialUnitName(String materialUnitName) {
        this.materialUnitName = materialUnitName;
    }

    public Double getInboundCount() {
        return inboundCount;
    }

    public void setInboundCount(Double inboundCount) {
        this.inboundCount = inboundCount;
    }

    public Double getInboundCost() {
        return inboundCost;
    }

    public void setInboundCost(Double inboundCost) {
        this.inboundCost = inboundCost;
    }

    public Double getInboundTotalPrice() {
        return inboundTotalPrice;
    }

    public void setInboundTotalPrice(Double inboundTotalPrice) {
        this.inboundTotalPrice = inboundTotalPrice;
    }

    public Double getOutBoundCount() {
        return outBoundCount;
    }

    public void setOutBoundCount(Double outBoundCount) {
        this.outBoundCount = outBoundCount;
    }

    public Double getOutBoundCost() {
        return outBoundCost;
    }

    public void setOutBoundCost(Double outBoundCost) {
        this.outBoundCost = outBoundCost;
    }

    public Double getOutBoundTotalPrice() {
        return outBoundTotalPrice;
    }

    public void setOutBoundTotalPrice(Double outBoundTotalPrice) {
        this.outBoundTotalPrice = outBoundTotalPrice;
    }

    public Double getBalanceCount() {
        return balanceCount;
    }

    public void setBalanceCount(Double balanceCount) {
        this.balanceCount = balanceCount;
    }

    public Double getBalanceCost() {
        return balanceCost;
    }

    public void setBalanceCost(Double balanceCost) {
        this.balanceCost = balanceCost;
    }

    public Double getBalanceTotalPrice() {
        return balanceTotalPrice;
    }

    public void setBalanceTotalPrice(Double balanceTotalPrice) {
        this.balanceTotalPrice = balanceTotalPrice;
    }

    public Boolean getIsInbound() {
        return isInbound;
    }

    public void setIsInbound(Boolean isInbound) {
        this.isInbound = isInbound;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }
}
