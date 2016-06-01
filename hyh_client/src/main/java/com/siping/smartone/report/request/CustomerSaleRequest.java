package com.siping.smartone.report.request;

import java.io.Serializable;

/**
 * @author SipingJava_003
 */
public class CustomerSaleRequest implements Serializable {

    private static final long serialVersionUID = -6174318376081260716L;

    private Integer pageNo;
    private Integer pageSize;
    private String dateFrom;
    private String dateTo;
    private String keyword;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Boolean getIsPaging() {
        return isPaging;
    }

    public void setIsPaging(Boolean isPaging) {
        this.isPaging = isPaging;
    }
}
