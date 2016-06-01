package com.siping.smartone.inventory.request;

import java.io.Serializable;

public class GetInventoryCheckItemListRequest implements Serializable {

    private static final long serialVersionUID = 2244999602296290522L;
    private String pageNo;
    private String pageSize;

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
