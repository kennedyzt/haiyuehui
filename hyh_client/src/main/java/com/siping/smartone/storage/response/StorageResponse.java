package com.siping.smartone.storage.response;

import java.io.Serializable;

import com.siping.smartone.common.Common;

public class StorageResponse extends Common implements Serializable {

    private static final long serialVersionUID = -579329315229780864L;
    private String storageNo;
    private String storageName;
    private String description;
    private Boolean isDelete;
    private Boolean isEnableLocation;

    public String getStorageNo() {
        return storageNo;
    }

    public void setStorageNo(String storageNo) {
        this.storageNo = storageNo;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Boolean getIsEnableLocation() {
        return isEnableLocation;
    }

    public void setIsEnableLocation(Boolean isEnableLocation) {
        this.isEnableLocation = isEnableLocation;
    }
}
