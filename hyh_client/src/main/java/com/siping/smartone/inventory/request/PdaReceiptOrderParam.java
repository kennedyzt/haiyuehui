package com.siping.smartone.inventory.request;

import java.io.Serializable;

public class PdaReceiptOrderParam implements Serializable {

    private static final long serialVersionUID = -5478702813262893029L;

    private Integer pageNo;
    private Integer pageSize;
    private Integer userid;
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

}
