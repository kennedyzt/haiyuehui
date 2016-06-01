package com.siping.smartone.inventory.response;

import java.io.Serializable;
import java.util.List;

public class GetInventoryCheckWithMaterialAndLocationInfoRequest implements Serializable {
    private static final long serialVersionUID = 8745885037453902393L;
    private List<String> ids;
    private String storageId;
    private String storageAreaId;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getStorageAreaId() {
        return storageAreaId;
    }

    public void setStorageAreaId(String storageAreaId) {
        this.storageAreaId = storageAreaId;
    }
}
