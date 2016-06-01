package com.siping.smartone.common.request;

import java.io.Serializable;

public class CommonBillsRequest implements Serializable {
    private static final long serialVersionUID = 1567871989046705370L;
    private String startDate;
    private String endDate;
    private String keywords;
    private Integer pageNo;
    private Integer pageSize;
    private Boolean isDraft;
    private Boolean isPay; // 采购订单和销售订单需要考虑是否结算,其余单据没有该字段
    private Integer status; // 预发货单需要、采购订单状态
    private Integer readytatus;

    public CommonBillsRequest() {
    }

    public CommonBillsRequest(String startDate, String endDate, String keywords) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.keywords = keywords;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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

    public Boolean getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(Boolean isDraft) {
        this.isDraft = isDraft;
    }

    public Boolean getIsPay() {
        return isPay;
    }

    public void setIsPay(Boolean isPay) {
        this.isPay = isPay;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReadytatus() {
        return readytatus;
    }

    public void setReadytatus(Integer readytatus) {
        this.readytatus = readytatus;
    }
}
