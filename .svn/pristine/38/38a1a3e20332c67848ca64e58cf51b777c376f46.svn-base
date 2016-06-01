package com.siping.smartone.wms;

import java.io.Serializable;

import com.siping.smartone.wms.request.OrderPickItemRequest;

public class OrderPickItemBase implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String orderNumber;

    private String fromBillsNo; // 预发货单号

    private String ecOrderNumber; // 电商原始订单号

    private Integer materialId;

    private String batchNumber;

    private String productionDate;

    private Double counts;

    private Double purchasePrice;

    private Double sellPrice;

    private Double discount;

    private Double afterDiscount;

    private Double taxRate;

    private Double tax;

    private Double total;

    private Boolean isGift;

    private Double shipmentsAmount;

    private String remark;

    private String createDate;

    private Integer createBy;

    private String shipmentsDate;

    private Integer storageLocationId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
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

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getAfterDiscount() {
        return afterDiscount;
    }

    public void setAfterDiscount(Double afterDiscount) {
        this.afterDiscount = afterDiscount;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getIsGift() {
        return isGift;
    }

    public void setIsGift(Boolean isGift) {
        this.isGift = isGift;
    }

    public Double getShipmentsAmount() {
        return shipmentsAmount;
    }

    public void setShipmentsAmount(Double shipmentsAmount) {
        this.shipmentsAmount = shipmentsAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public String getShipmentsDate() {
        return shipmentsDate;
    }

    public void setShipmentsDate(String shipmentsDate) {
        this.shipmentsDate = shipmentsDate;
    }

    public Integer getStorageLocationId() {
        return storageLocationId;
    }

    public void setStorageLocationId(Integer storageLocationId) {
        this.storageLocationId = storageLocationId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((batchNumber == null) ? 0 : batchNumber.hashCode());
        result = prime * result + ((materialId == null) ? 0 : materialId.hashCode());
        result = prime * result + ((productionDate == null) ? 0 : productionDate.hashCode());
        result = prime * result + ((fromBillsNo == null) ? 0 : fromBillsNo.hashCode());
        result = prime * result + ((ecOrderNumber == null) ? 0 : ecOrderNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else {
            if (this.getClass() == obj.getClass()) {
                OrderPickItemRequest o = (OrderPickItemRequest) obj;
                if (o.getEcOrderNumber().equals(this.getEcOrderNumber()) && o.getFromBillsNo().equals(this.getFromBillsNo()) && o.getMaterialId().equals(this.getMaterialId())
                    && o.getBatchNumber().equals(this.getBatchNumber()) && o.getProductionDate().equals(this.getProductionDate())) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getFromBillsNo() {
        return fromBillsNo;
    }

    public void setFromBillsNo(String fromBillsNo) {
        this.fromBillsNo = fromBillsNo;
    }

    public String getEcOrderNumber() {
        return ecOrderNumber;
    }

    public void setEcOrderNumber(String ecOrderNumber) {
        this.ecOrderNumber = ecOrderNumber;
    }

}
