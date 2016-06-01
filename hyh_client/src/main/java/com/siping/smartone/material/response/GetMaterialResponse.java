package com.siping.smartone.material.response;

import java.io.Serializable;
import java.util.List;

import com.siping.smartone.inventory.response.GetInventoryWarningResponse;

public class GetMaterialResponse implements Serializable {
    private static final long serialVersionUID = 5084910872075814005L;
    private Integer id; // 新增时，该字段为空
    private String materialNo; // 物料编号
    private String materialName; // 物料名称
    private String foreignName; // 外文名称
    private Integer materialType; // 物料类型
    private String materialTypeName;
    private String brand;
    private String specificationsModel;
    private String season;
    private Boolean isPurchase;
    private Boolean isSell;
    private Boolean isInventory;
    private Integer unitId; // 物料单位
    private String barcode; // 国际编码
    private Integer shops;
    private String shopName;
    private Integer expirationDate;
    private Integer partnerId;
    private String partnerName;
    private Integer minOrder; // 起订量
    private Integer maxInventory;
    private Integer minInventory;
    private String transactionId;
    private Boolean isEnable; // 是否启用
    private Boolean isBatch; // 是否启用批次管理
    private Boolean isDelete; // 是否删除
    private String description; // 描述
    private Integer createBy; // 新增时，该字段为空
    private Integer updateBy;
    private String createDate;
    private String updateDate;
    private String unitName; // 关联查询单位名
    private Integer legalUnit; // 法定单位
    private String legalName;
    private Float legalTranslationQuantity; // 法定折算数量
    private Integer entryUnit; // 入区单位
    private String entryName;
    private Float entryTranslationQuantity; // 入区折算数量
    private String manufacturer; // 生产商
    private String provenance; // 原产地
    private String ebec; // 电商企业代码
    private String eben; // 电商企业名称
    private String hscode; // hs编码
    private String postTaxNumber; // 行邮税税号
    private String custom1; // 自定义1
    private String custom2;
    private String custom3;
    private Boolean isUsed;
    private String itemNo;
    private Double postTaxRate;
    private Double weight;
    private List<GetInventoryWarningResponse> inventoryWarnings; // 库存预警列表项
    private float stockQuantity;

    public Integer getLegalUnit() {
        return legalUnit;
    }

    public void setLegalUnit(Integer legalUnit) {
        this.legalUnit = legalUnit;
    }

    public Float getLegalTranslationQuantity() {
        return legalTranslationQuantity;
    }

    public void setLegalTranslationQuantity(Float legalTranslationQuantity) {
        this.legalTranslationQuantity = legalTranslationQuantity;
    }

    public Integer getEntryUnit() {
        return entryUnit;
    }

    public void setEntryUnit(Integer entryUnit) {
        this.entryUnit = entryUnit;
    }

    public Float getEntryTranslationQuantity() {
        return entryTranslationQuantity;
    }

    public void setEntryTranslationQuantity(Float entryTranslationQuantity) {
        this.entryTranslationQuantity = entryTranslationQuantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public String getEbec() {
        return ebec;
    }

    public void setEbec(String ebec) {
        this.ebec = ebec;
    }

    public String getEben() {
        return eben;
    }

    public void setEben(String eben) {
        this.eben = eben;
    }

    public String getHscode() {
        return hscode;
    }

    public void setHscode(String hscode) {
        this.hscode = hscode;
    }

    public String getPostTaxNumber() {
        return postTaxNumber;
    }

    public void setPostTaxNumber(String postTaxNumber) {
        this.postTaxNumber = postTaxNumber;
    }

    public String getCustom1() {
        return custom1;
    }

    public void setCustom1(String custom1) {
        this.custom1 = custom1;
    }

    public String getCustom2() {
        return custom2;
    }

    public void setCustom2(String custom2) {
        this.custom2 = custom2;
    }

    public String getCustom3() {
        return custom3;
    }

    public void setCustom3(String custom3) {
        this.custom3 = custom3;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public List<GetInventoryWarningResponse> getInventoryWarnings() {
        return inventoryWarnings;
    }

    public void setInventoryWarnings(List<GetInventoryWarningResponse> inventoryWarnings) {
        this.inventoryWarnings = inventoryWarnings;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public Double getPostTaxRate() {
        return postTaxRate;
    }

    public void setPostTaxRate(Double postTaxRate) {
        this.postTaxRate = postTaxRate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public float getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(float stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
