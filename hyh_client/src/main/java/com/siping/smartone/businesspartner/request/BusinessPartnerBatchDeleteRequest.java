package com.siping.smartone.businesspartner.request;

import java.io.Serializable;
import java.util.List;

public class BusinessPartnerBatchDeleteRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Integer> ids;
    private Integer loginId;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

}
