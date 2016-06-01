package com.siping.smartone.wms.request;

import java.io.Serializable;
import java.util.List;

public class ESaleOrderRequest implements Serializable {
    private static final long serialVersionUID = -7394267090984058566L;
    private String orderNumber; // 添加时，此字段不需要赋值，此为系统生成
    private Integer partnerId; //客户id
    private String ecOrderNumber; // 电商平台销售订单号
    private String billsDate; // 单据日期
    private String shipmentsDate;
    private Double totalPrice; // 总计金额
    private Double giftPrice; // 赠品金额
    private Double favorablePrice; // 优惠
    private Double gatheringPrice; // 应收总金额
    private String createBy; // 单据创建人
    private String summary; // 单据摘要
    private String updateBy;
    private String auditor; // 审核人
    private String owner; // 拥有人
    private String customerNo; //客户编号
    private String customerName; //客户名称
    private String consigneeName;
    private String consigneeAddress;
    private String consignessPhone;
    private String consignessPostcode;
    private Short status;
    private String token; // 电商接口验证码，仅电商接口需要
    private String shopNumber; // 商家id
    private String trackingNo; //物流快递单号
    private String customsCode; //海关通关号
    private List<ESaleOrderItemRequest> items; // 销售订单项

    public String getBillsDate() {
        return billsDate;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsignessPhone() {
        return consignessPhone;
    }

    public void setConsignessPhone(String consignessPhone) {
        this.consignessPhone = consignessPhone;
    }

    public String getConsignessPostcode() {
        return consignessPostcode;
    }

    public void setConsignessPostcode(String consignessPostcode) {
        this.consignessPostcode = consignessPostcode;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public void setBillsDate(String billsDate) {
        this.billsDate = billsDate;
    }

    public String getShipmentsDate() {
        return shipmentsDate;
    }

    public void setShipmentsDate(String shipmentsDate) {
        this.shipmentsDate = shipmentsDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(Double giftPrice) {
        this.giftPrice = giftPrice;
    }

    public Double getFavorablePrice() {
        return favorablePrice;
    }

    public void setFavorablePrice(Double favorablePrice) {
        this.favorablePrice = favorablePrice;
    }

    public Double getGatheringPrice() {
        return gatheringPrice;
    }

    public void setGatheringPrice(Double gatheringPrice) {
        this.gatheringPrice = gatheringPrice;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<ESaleOrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ESaleOrderItemRequest> items) {
        this.items = items;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEcOrderNumber() {
        return ecOrderNumber;
    }

    public void setEcOrderNumber(String ecOrderNumber) {
        this.ecOrderNumber = ecOrderNumber;
    }

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

}
