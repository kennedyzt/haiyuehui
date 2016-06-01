package com.siping.smartone.inventory.request;

import java.io.Serializable;

public class GetInventoryAuditDetailRequest implements Serializable {
    private static final long serialVersionUID = 6831321850919214510L;
    private String materialNo;
    private Integer pageNo;
    private Integer pageSize;

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
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
