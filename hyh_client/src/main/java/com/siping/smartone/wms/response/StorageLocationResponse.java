package com.siping.smartone.wms.response;

import java.io.Serializable;
import java.util.List;

public class StorageLocationResponse implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer storageLocationId;
    private String storageLocationName;
    private List<ReadyShipmentsItemResponse> items;

    public Integer getStorageLocationId() {
        return storageLocationId;
    }

    public void setStorageLocationId(Integer storageLocationId) {
        this.storageLocationId = storageLocationId;
    }

    public String getStorageLocationName() {
        return storageLocationName;
    }

    public void setStorageLocationName(String storageLocationName) {
        this.storageLocationName = storageLocationName;
    }

    public List<ReadyShipmentsItemResponse> getItems() {
        return items;
    }

    public void setItems(List<ReadyShipmentsItemResponse> items) {
        this.items = items;
    }
}
