package com.siping.smartone.inventory.response;

import java.io.Serializable;

public class GetMaterialBatchNumberResponse implements Serializable {

    private static final long serialVersionUID = 3405726366551391608L;
    private String materialId;
    private String materialNo;
    private String materialName;
    private String specificationsModel;
    private String barcode;
    private String unitName;
    private String smNumber;
    private Double price; //采购价格
    private String inventoryId;
    private String flowId;
    private Boolean isInbound;
    private String fromBillsId;
    private String batchNumber;
    private String productionDate;
    private Double counts;
    private String expirationDate;

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Boolean getIsInbound() {
        return isInbound;
    }

    public void setIsInbound(Boolean isInbound) {
        this.isInbound = isInbound;
    }

    public String getFromBillsId() {
        return fromBillsId;
    }

    public void setFromBillsId(String fromBillsId) {
        this.fromBillsId = fromBillsId;
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

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getSpecificationsModel() {
        return specificationsModel;
    }

    public void setSpecificationsModel(String specificationsModel) {
        this.specificationsModel = specificationsModel;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getSmNumber() {
        return smNumber;
    }

    public void setSmNumber(String smNumber) {
        this.smNumber = smNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
