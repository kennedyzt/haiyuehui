package com.siping.smartone.storage.request;

import java.io.Serializable;

public class GetStorageListRequest implements Serializable {

    private static final long serialVersionUID = 7647783681434461082L;
    private String pageNo;
    private String pageSize;
    private String key;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
