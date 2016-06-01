package com.siping.smartone.report.response;

import java.io.Serializable;

public class SingleMaterialCostResponse implements Serializable {
    private static final long serialVersionUID = 3687867297479562124L;
    private Integer storageId;
    private Integer materialId;
    private Double cost; // 成本

    public Integer getStorageId() {
        return storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
