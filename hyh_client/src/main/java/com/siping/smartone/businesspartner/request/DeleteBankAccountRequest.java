package com.siping.smartone.businesspartner.request;

import java.io.Serializable;
import java.util.List;

public class DeleteBankAccountRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Integer> ids;
    private Integer updateBy;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

}
