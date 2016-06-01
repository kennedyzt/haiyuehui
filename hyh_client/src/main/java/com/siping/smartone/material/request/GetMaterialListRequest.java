package com.siping.smartone.material.request;

import java.io.Serializable;

public class GetMaterialListRequest implements Serializable {
    private static final long serialVersionUID = 5084910872075814005L;
    private String pageNo;
    private String pageSize;
    private String key;
    private String materialTypeId;
    private String materialType;
    private String supplierId;
    private String supplierName;
    private Boolean isEnable;
    private Integer loggedSupId;

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public String getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(String materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getsupplierName() {
        return supplierName;
    }

    public void setsupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getLoggedSupId() {
        return loggedSupId;
    }

    public void setLoggedSupId(Integer loggedSupId) {
        this.loggedSupId = loggedSupId;
    }
}
