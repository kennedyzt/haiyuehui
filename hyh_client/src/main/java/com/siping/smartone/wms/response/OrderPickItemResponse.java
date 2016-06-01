package com.siping.smartone.wms.response;

import java.io.Serializable;

import com.siping.smartone.wms.OrderPickItemBase;

public class OrderPickItemResponse extends OrderPickItemBase implements Serializable {

    private static final long serialVersionUID = -6567174039646428105L;
    private String materialName;
    private String materialNo;
    private String unitName;
    private String locationName;
    private String barcode;

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

}
