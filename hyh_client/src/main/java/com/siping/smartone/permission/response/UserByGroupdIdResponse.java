package com.siping.smartone.permission.response;

import java.io.Serializable;

public class UserByGroupdIdResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer userId;
    private Integer groupId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

}
