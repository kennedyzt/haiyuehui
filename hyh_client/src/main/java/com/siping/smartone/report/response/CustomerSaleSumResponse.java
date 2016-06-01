package com.siping.smartone.report.response;

import java.io.Serializable;

public class CustomerSaleSumResponse implements Serializable {

    private static final long serialVersionUID = -4348003899191926045L;

    private Integer pageCount;
    private Double totalMoney;

    public Integer getPageCount() {
        return pageCount;
    }
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
    public Double getTotalMoney() {
        return totalMoney;
    }
    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
