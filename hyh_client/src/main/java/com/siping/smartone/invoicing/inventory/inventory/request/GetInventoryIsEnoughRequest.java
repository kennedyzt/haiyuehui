package com.siping.smartone.invoicing.inventory.inventory.request;

import java.io.Serializable;

public class GetInventoryIsEnoughRequest implements Serializable {
    private static final long serialVersionUID = -7394267090984058566L;
    private Integer materialId; // 物料
    private Integer storageId; // 仓库
    private Double counts; // 数量
    private String batchNumber; // 批次号
    private String productionDate; // 生产日期

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Integer getStorageId() {
        return storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    public Double getCounts() {
        return counts;
    }

    public void setCounts(Double counts) {
        this.counts = counts;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }
}
