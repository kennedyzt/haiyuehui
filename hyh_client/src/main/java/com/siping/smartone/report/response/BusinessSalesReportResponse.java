package com.siping.smartone.report.response;

import java.io.Serializable;

public class BusinessSalesReportResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String shopNo;
    private String shopName;
    private Double totalPrice;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

}
