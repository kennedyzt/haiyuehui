package com.siping.smartone.invoicing.inventory.inventory.request;

import java.io.Serializable;

public class AddInventoryRequest implements Serializable {
    private static final long serialVersionUID = -7394267090984058566L;
    private Integer id; // 新增时不需要此id，只有更新时需要
    private Integer materialId; // 物料
    private Integer storageId; // 仓库
    private Boolean isInbound; // 是否入库
    // private String fromBillsId; // 上级单据编号
    private String billsNo; // 单据编号
    private String billsDate; // 单据日期
    private String batchNumber; // 批次号
    private String productionDate; // 生产日期
    private Double counts; // 数量
    private Double purchasePrice; // 采购价格
    private Double sellPrice; // 销售单价
    private Double total; // 总计
    private Integer createBy; //
    private Integer updateBy;
    private String expirationDate; // 过期时间，实际上是指过期的天数
    private Integer storageLocationId; // 库位id

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

    public Boolean getIsInbound() {
        return isInbound;
    }

    public void setIsInbound(Boolean isInbound) {
        this.isInbound = isInbound;
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

    public Double getCounts() {
        return counts;
    }

    public void setCounts(Double counts) {
        this.counts = counts;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getBillsDate() {
        return billsDate;
    }

    public void setBillsDate(String billsDate) {
        this.billsDate = billsDate;
    }

    public String getBillsNo() {
        return billsNo;
    }

    public void setBillsNo(String billsNo) {
        this.billsNo = billsNo;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getStorageLocationId() {
        return storageLocationId;
    }

    public void setStorageLocationId(Integer storageLocationId) {
        this.storageLocationId = storageLocationId;
    }
}
