package com.siping.smartone.invoicing.sell.returns.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeleteSellReturnsRequest implements Serializable {
    private static final long serialVersionUID = -1326269612465873857L;
    private List<String> returnsNumberList = new ArrayList<String>();
    private String userId;

    public DeleteSellReturnsRequest() {
    }

    public DeleteSellReturnsRequest(List<String> returnsNumberList) {
        this.returnsNumberList.clear();
        this.returnsNumberList.addAll(returnsNumberList);
    }

    public List<String> getReturnsNumberList() {
        return returnsNumberList;
    }

    public void setReturnsNumberList(List<String> returnsNumberList) {
        this.returnsNumberList = returnsNumberList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}