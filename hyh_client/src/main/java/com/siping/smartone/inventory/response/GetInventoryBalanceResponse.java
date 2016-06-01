package com.siping.smartone.inventory.response;

import java.io.Serializable;

public class GetInventoryBalanceResponse implements Serializable {
    private static final long serialVersionUID = 8835510815142564798L;
    private String materialNo; // 商品货号
    private String materialName;
    private String barcode; // 国际编码即条形码
    private Integer materialTypeId;
    private String materialTypeName; // 商品分类
    private Integer materialUnitId;
    private String materialUnitName; // 计量单位
    private String stroageNo;
    private String stroageName; // 仓库
    private double count; // 数量
    private double cost; // 成本
    private Double totalPrice; // 总金额;

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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(Integer materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public Integer getMaterialUnitId() {
        return materialUnitId;
    }

    public void setMaterialUnitId(Integer materialUnitId) {
        this.materialUnitId = materialUnitId;
    }

    public String getMaterialUnitName() {
        return materialUnitName;
    }

    public void setMaterialUnitName(String materialUnitName) {
        this.materialUnitName = materialUnitName;
    }

    public String getStroageNo() {
        return stroageNo;
    }

    public void setStroageNo(String stroageNo) {
        this.stroageNo = stroageNo;
    }

    public String getStroageName() {
        return stroageName;
    }

    public void setStroageName(String stroageName) {
        this.stroageName = stroageName;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
