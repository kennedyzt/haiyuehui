package com.siping.smartone.report.request;

import java.io.Serializable;

public class PurchaseOrderNumRequest implements Serializable {

    private static final long serialVersionUID = 5496011985214768090L;

    private Integer pageNo;
    private Integer pageSize;
    private String keyword;
    private String productType;
    private String dateFrom;
    private String dateTo;
    private String supplierId;
    private String orderNo;
    private Boolean isPaging = true;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Boolean getIsPaging() {
        return isPaging;
    }

    public void setIsPaging(Boolean isPaging) {
        this.isPaging = isPaging;
    }
}
