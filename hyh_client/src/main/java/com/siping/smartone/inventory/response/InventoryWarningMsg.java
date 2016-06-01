package com.siping.smartone.inventory.response;

import java.io.Serializable;

public class InventoryWarningMsg implements Serializable {
    private static final long serialVersionUID = 1L;
    private Boolean isMoreThan;
    private Integer materialId;
    private String materialName;

    public Boolean getIsMoreThan() {
        return isMoreThan;
    }

    public void setIsMoreThan(Boolean isMoreThan) {
        this.isMoreThan = isMoreThan;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

}
