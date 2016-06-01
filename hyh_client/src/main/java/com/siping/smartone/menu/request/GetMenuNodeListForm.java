package com.siping.smartone.menu.request;

import java.io.Serializable;

public class GetMenuNodeListForm implements Serializable {
    private static final long serialVersionUID = -463585780067369115L;
    private String keywords;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
