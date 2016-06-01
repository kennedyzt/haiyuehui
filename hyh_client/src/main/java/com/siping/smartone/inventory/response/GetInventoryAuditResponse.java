package com.siping.smartone.inventory.response;

import java.io.Serializable;
import java.util.List;

public class GetInventoryAuditResponse implements Serializable {
    private static final long serialVersionUID = 2073853397285979976L;
    private String materialNo;
    private String materialName;
    private String materialUnitName; // 计量单位
    private Double counts; // 数量
    private Double cost; // 成本
    private Double inventoryTotalPrice; // 库存总金额
    private Boolean isInbound; // 是否入库
    private Boolean isBatch; // 是否启用批次
    private List<GetInventoryAuditDetailResponse> detailList;

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialUnitName() {
        return materialUnitName;
    }

    public void setMaterialUnitName(String materialUnitName) {
        this.materialUnitName = materialUnitName;
    }

    public Double getCounts() {
        return counts;
    }

    public void setCounts(Double counts) {
        this.counts = counts;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getInventoryTotalPrice() {
        return inventoryTotalPrice;
    }

    public void setInventoryTotalPrice(Double inventoryTotalPrice) {
        this.inventoryTotalPrice = inventoryTotalPrice;
    }

    public List<GetInventoryAuditDetailResponse> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<GetInventoryAuditDetailResponse> detailList) {
        this.detailList = detailList;
    }

    public Boolean getIsInbound() {
        return isInbound;
    }

    public void setIsInbound(Boolean isInbound) {
        this.isInbound = isInbound;
    }

    public Boolean getIsBatch() {
        return isBatch;
    }

    public void setIsBatch(Boolean isBatch) {
        this.isBatch = isBatch;
    }
}
