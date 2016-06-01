package com.siping.intranet.common;

import org.stroma.framework.core.platform.web.form.Form;
import org.stroma.framework.core.platform.web.form.FormParam;

@Form
public class CommonBillsForm {
    @FormParam(value = "startDate")
    private String startDate;

    @FormParam(value = "endDate")
    private String endDate;

    @FormParam(value = "keywords")
    private String keywords;

    @FormParam(value = "isDraft")
    private Boolean isDraft;

    @FormParam(value = "isPay")
    private Boolean isPay; // 采购订单和销售订单需要考虑是否结算,其余单据没有该字段
    
    @FormParam(value = "status")
    private Integer status; // 预发货单需要

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public CommonBillsForm() {
    }

    public CommonBillsForm(String startDate, String endDate, String keywords) {
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
}
