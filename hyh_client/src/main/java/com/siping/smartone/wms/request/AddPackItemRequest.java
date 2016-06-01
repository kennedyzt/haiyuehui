package com.siping.smartone.wms.request;

import java.io.Serializable;

public class AddPackItemRequest implements Serializable {

    private static final long serialVersionUID = -1710458241848321354L;
    private String materialId;
    private String materialName;
    private String number;

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}
