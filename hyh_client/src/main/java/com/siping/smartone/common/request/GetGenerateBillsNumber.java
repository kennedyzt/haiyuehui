package com.siping.smartone.common.request;

import java.io.Serializable;

public class GetGenerateBillsNumber implements Serializable {

    private static final long serialVersionUID = -820117953268611358L;
    private String tableName;
    private String primaryKeyName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }
}
