package com.siping.smartone.storage.request;

import java.io.Serializable;
import java.util.List;

public class DeleteStorageRequest implements Serializable {

    private static final long serialVersionUID = 5280375782963285723L;
    private List<String> ids;
    private String updateBy;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
