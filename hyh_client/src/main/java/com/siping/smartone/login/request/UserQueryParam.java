package com.siping.smartone.login.request;

import java.io.Serializable;

public class UserQueryParam implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer pageNo;
    private Integer pageSize;
    private String keywords;
    private Boolean isEnable;

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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

}
