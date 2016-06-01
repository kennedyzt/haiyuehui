package com.siping.smartone.material.response;

import java.io.Serializable;
import java.util.Date;

public class GetMaterialResponseForInventoryCheck implements Serializable {
    private static final long serialVersionUID = 1742455193729475553L;
    private String id; // 新增时，该字段为空
    private String materialIdForHtml;
    private String materialNo; // 物料编号
    private String materialNoIdForHtml;
    private String materialName; // 物料名称
    private String materialNameIdForHtml;
    private String foreignName; // 外文名称
    private Integer materialType; // 物料类型
    private String brand;
    private String specificationsModel;
    private String specificationsModelIdForHtml;
    private String season;
    private Boolean isPurchase;
    private Boolean isSell;
    private Boolean isInventory;
    private Integer unitId; // 物料单位
    private String barcode; // 国际编码
    private String barcodeIdForHtml;
    private Integer shops;
    private String shopName;
    private Integer expirationDate;
    private Integer partnerId;
    private String partnerName;
    private Integer minOrder;
    private Integer maxInventory;
    private Integer minInventory;
    private String transactionId;
    private Boolean isEnable; // 是否启用
    private Boolean isBatch; // 是否启用批次管理
    private Boolean isDelete; // 是否删除
    private String description; // 描述
    private Integer createBy; // 新增时，该字段为空
    private Integer updateBy;
    private Date createDate;
    private Date updateDate;
    private String unitName; // 关联查询单位名
    private String unitNameIdForHtml;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }

    public Integer getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSpecificationsModel() {
        return specificationsModel;
    }

    public void setSpecificationsModel(String specificationsModel) {
        this.specificationsModel = specificationsModel;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Boolean getIsPurchase() {
        return isPurchase;
    }

    public void setIsPurchase(Boolean isPurchase) {
        this.isPurchase = isPurchase;
    }

    public Boolean getIsSell() {
        return isSell;
    }

    public void setIsSell(Boolean isSell) {
        this.isSell = isSell;
    }

    public Boolean getIsInventory() {
        return isInventory;
    }

    public void setIsInventory(Boolean isInventory) {
        this.isInventory = isInventory;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer getShops() {
        return shops;
    }

    public void setShops(Integer shops) {
        this.shops = shops;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Integer expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Integer getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(Integer minOrder) {
        this.minOrder = minOrder;
    }

    public Integer getMaxInventory() {
        return maxInventory;
    }

    public void setMaxInventory(Integer maxInventory) {
        this.maxInventory = maxInventory;
    }

    public Integer getMinInventory() {
        return minInventory;
    }

    public void setMinInventory(Integer minInventory) {
        this.minInventory = minInventory;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Boolean getIsBatch() {
        return isBatch;
    }

    public void setIsBatch(Boolean isBatch) {
        this.isBatch = isBatch;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getMaterialIdForHtml() {
        return materialIdForHtml;
    }

    public void setMaterialIdForHtml(String materialIdForHtml) {
        this.materialIdForHtml = materialIdForHtml;
    }

    public String getSpecificationsModelIdForHtml() {
        return specificationsModelIdForHtml;
    }

    public void setSpecificationsModelIdForHtml(String specificationsModelIdForHtml) {
        this.specificationsModelIdForHtml = specificationsModelIdForHtml;
    }

    public String getMaterialNameIdForHtml() {
        return materialNameIdForHtml;
    }

    public void setMaterialNameIdForHtml(String materialNameIdForHtml) {
        this.materialNameIdForHtml = materialNameIdForHtml;
    }

    public String getMaterialNoIdForHtml() {
        return materialNoIdForHtml;
    }

    public void setMaterialNoIdForHtml(String materialNoIdForHtml) {
        this.materialNoIdForHtml = materialNoIdForHtml;
    }

    public String getBarcodeIdForHtml() {
        return barcodeIdForHtml;
    }

    public void setBarcodeIdForHtml(String barcodeIdForHtml) {
        this.barcodeIdForHtml = barcodeIdForHtml;
    }

    public String getUnitNameIdForHtml() {
        return unitNameIdForHtml;
    }

    public void setUnitNameIdForHtml(String unitNameIdForHtml) {
        this.unitNameIdForHtml = unitNameIdForHtml;
    }

}
