package com.siping.smartone.storage.response;

import java.io.Serializable;

public class StorageLocationResponese implements Serializable {

    private static final long serialVersionUID = -5412342258501213964L;
    private String id;
    private String locationNo;
    private String locationName;
    private String locationBarcode;
    private String description;
    private Integer storageId;
    private Integer storageAreaId;
    private String storageAreaNo;
    private String storageAreaName;
    private String createDate;
    private Integer createBy;
    private String updateDate;
    private Integer updateBy;
    private Integer pickOrder;
    private String storageName;
    private String storageNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationNo() {
        return locationNo;
    }

    public void setLocationNo(String locationNo) {
        this.locationNo = locationNo;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationBarcode() {
        return locationBarcode;
    }

    public void setLocationBarcode(String locationBarcode) {
        this.locationBarcode = locationBarcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStorageId() {
        return storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getStorageAreaId() {
        return storageAreaId;
    }

    public void setStorageAreaId(Integer storageAreaId) {
        this.storageAreaId = storageAreaId;
    }

    public String getStorageAreaNo() {
        return storageAreaNo;
    }

    public void setStorageAreaNo(String storageAreaNo) {
        this.storageAreaNo = storageAreaNo;
    }

    public String getStorageAreaName() {
        return storageAreaName;
    }

    public void setStorageAreaName(String storageAreaName) {
        this.storageAreaName = storageAreaName;
    }

    public Integer getPickOrder() {
        return pickOrder;
    }

    public void setPickOrder(Integer pickOrder) {
        this.pickOrder = pickOrder;
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