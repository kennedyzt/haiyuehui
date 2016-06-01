package com.siping.smartone.inventory.response;

import java.io.Serializable;

public class InventoryAmountResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Double inventoryAmount;
    private Integer materialId;
    private String materialName;

    public Double getInventoryAmount() {
        return inventoryAmount;
    }

    public void setInventoryAmount(Double inventoryAmount) {
        this.inventoryAmount = inventoryAmount;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}
