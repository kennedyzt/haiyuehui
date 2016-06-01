package com.siping.smartone.report.request;

import java.io.Serializable;

public class InWarehouseRequest implements Serializable {

    private static final long serialVersionUID = 6990019479026540998L;

    private Integer pageNo;
    private Integer pageSize;
    private Boolean isPaging = true;
    private String keyword;
    private String storageId;
    private String dateFrom;
    private String dateTo;
    private String productType;

    public Boolean getIsPaging() {
        return isPaging;
    }

    public void setIsPaging(Boolean isPaging) {
        this.isPaging = isPaging;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

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
}
