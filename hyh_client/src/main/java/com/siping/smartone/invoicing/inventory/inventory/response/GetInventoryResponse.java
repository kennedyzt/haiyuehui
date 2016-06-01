package com.siping.smartone.invoicing.inventory.inventory.response;

import java.io.Serializable;
import java.util.List;

public class GetInventoryResponse implements Serializable {
    private static final long serialVersionUID = -7394267090984058566L;
    private Integer id;
    private Integer materialId; // 物料
    private String materialName;
    private Integer storageId; // 仓库
    private String storageName;
    private Integer inboundAmount; // 入库数量
    private Integer availableAmount; // 可用数量
    private Integer createBy; // 单据摘要
    private String createDate;
    private Integer updateBy;
    private String updateDate;
    private List<GetInventoryLogResponse> logList; // 库存日志项

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getInboundAmount() {
        return inboundAmount;
    }

    public void setInboundAmount(Integer inboundAmount) {
        this.inboundAmount = inboundAmount;
    }

    public Integer getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Integer availableAmount) {
        this.availableAmount = availableAmount;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public List<GetInventoryLogResponse> getLogList() {
        return logList;
    }

    public void setLogList(List<GetInventoryLogResponse> logList) {
        this.logList = logList;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }
}
