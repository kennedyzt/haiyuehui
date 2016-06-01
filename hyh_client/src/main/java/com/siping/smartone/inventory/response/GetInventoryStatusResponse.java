package com.siping.smartone.inventory.response;

import java.io.Serializable;

public class GetInventoryStatusResponse implements Serializable {
    private static final long serialVersionUID = 2073853397285979976L;
    private String materialNo;
    private String materialName;
    private String materialUnitName; // 计量单位'
    private Double inventoryAmount; // 存货量
    private Double promisedAmount; // 已承诺
    private Double orderedAmount; // 已订购
    private Double availableAmount; // 可用量
    private Double maxInventory; // 最大库存量
    private Double minInventory; // 最小库存量

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

    public Double getInventoryAmount() {
        return inventoryAmount;
    }

    public void setInventoryAmount(Double inventoryAmount) {
        this.inventoryAmount = inventoryAmount;
    }

    public Double getPromisedAmount() {
        return promisedAmount;
    }

    public void setPromisedAmount(Double promisedAmount) {
        this.promisedAmount = promisedAmount;
    }

    public Double getOrderedAmount() {
        return orderedAmount;
    }

    public void setOrderedAmount(Double orderedAmount) {
        this.orderedAmount = orderedAmount;
    }

    public Double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Double availableAmount) {
        this.availableAmount = availableAmount;
    }

    public Double getMaxInventory() {
        return maxInventory;
    }

    public void setMaxInventory(Double maxInventory) {
        this.maxInventory = maxInventory;
    }

    public Double getMinInventory() {
        return minInventory;
    }

    public void setMinInventory(Double minInventory) {
        this.minInventory = minInventory;
    }
}
