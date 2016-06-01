package com.siping.smartone.report.request;

import java.io.Serializable;

public class GetSellOrderCountRrequest implements Serializable {
    private static final long serialVersionUID = 2101323957310916902L;
    private String type;
    private String keys;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }
}
