package com.siping.smartone.material.request;

import java.io.Serializable;
import java.util.List;

public class DeleteMaterialTypeRequest implements Serializable {

    private static final long serialVersionUID = 952616761235897021L;
    private List<Integer> ids;
    private String updateBy;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

}
