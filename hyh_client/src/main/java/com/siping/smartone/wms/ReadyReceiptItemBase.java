package com.siping.smartone.wms;

import java.io.Serializable;

public class ReadyReceiptItemBase implements Serializable {
    private static final long serialVersionUID = 1L;
    // 单据编号
    private String orderNumber;
    // 流程编号
    private String purchaseFlowId;
    // 订单项id
    private Integer id;
    // 物料id
    private Integer materialId;
    // 物料编号
    private String materialNo;
    // 物料描述(名称)
    private String materialName;
    // 物料备注
    private String description;
    // 规格及型号
    private String specificationsModel;
    // 物料品牌
    private String brand;
    // 季节
    private String season;
    // 国际编码
    private String barcode;
    // 计量单位id
    private Integer unitId;
    // 计量单位名称
    private String unitName;
    // 物料数量
    private double counts;
    // 行号
    private Integer rowNumber;
    // 单据上的物料描述
    private String remark;
    private Integer readyStatus;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPurchaseFlowId() {
        return purchaseFlowId;
    }

    public void setPurchaseFlowId(String purchaseFlowId) {
        this.purchaseFlowId = purchaseFlowId;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecificationsModel() {
        return specificationsModel;
    }

    public void setSpecificationsModel(String specificationsModel) {
        this.specificationsModel = specificationsModel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public double getCounts() {
        return counts;
    }

    public void setCounts(double counts) {
        this.counts = counts;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getReadyStatus() {
        return readyStatus;
    }

    public void setReadyStatus(Integer readyStatus) {
        this.readyStatus = readyStatus;
    }
}
