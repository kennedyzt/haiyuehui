package com.siping.smartone.inventory.request;

import java.io.Serializable;

import com.siping.smartone.material.response.GetMaterialResponse;

public class GetMaterialBatchNumberListRequest implements Serializable {

    private static final long serialVersionUID = 6415951432421435487L;
    private String materialId;
    private String storageId;
    private GetMaterialResponse materialResponse;
    
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

    public GetMaterialResponse getMaterialResponse() {
        return materialResponse;
    }

    public void setMaterialResponse(GetMaterialResponse materialResponse) {
        this.materialResponse = materialResponse;
    }
}
