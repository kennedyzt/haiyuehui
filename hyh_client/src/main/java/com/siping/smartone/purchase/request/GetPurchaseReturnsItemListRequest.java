package com.siping.smartone.purchase.request;

import java.io.Serializable;

public class GetPurchaseReturnsItemListRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer pageNo;
    private Integer pageSize;

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
