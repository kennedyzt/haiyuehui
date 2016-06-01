package com.siping.smartone.storage.request;

import java.io.Serializable;

public class GetStorageLocationListRequest implements Serializable {

    private static final long serialVersionUID = 404273512294460660L;
    private Integer pageNo;
    private Integer pageSize;
    private String id;
    private String storageAreaNo;
    private String storageLocationNo;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStorageAreaNo() {
        return storageAreaNo;
    }

    public void setStorageAreaNo(String storageAreaNo) {
        this.storageAreaNo = storageAreaNo;
    }

    public String getStorageLocationNo() {
        return storageLocationNo;
    }

    public void setStorageLocationNo(String storageLocationNo) {
        this.storageLocationNo = storageLocationNo;
    }
}
