package com.siping.smartone.inventory.response;

import java.io.Serializable;

public class GetInventoryWarningResponse implements Serializable {

    private static final long serialVersionUID = -7873872886922371549L;
    private String id;
    private String materialId;
    private String storageId;
    private String storageName;
    private String storageNo;
    private String maxInventory;
    private String minInventory;
    private String createTime;
    private String createBy;
    private String updateTime;
    private String updateBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getMaxInventory() {
        return maxInventory;
    }

    public void setMaxInventory(String maxInventory) {
        this.maxInventory = maxInventory;
    }

    public String getMinInventory() {
        return minInventory;
    }

    public void setMinInventory(String minInventory) {
        this.minInventory = minInventory;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getStorageNo() {
        return storageNo;
    }

    public void setStorageNo(String storageNo) {
        this.storageNo = storageNo;
    }
}
