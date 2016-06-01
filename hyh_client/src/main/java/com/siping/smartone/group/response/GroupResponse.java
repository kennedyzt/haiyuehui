package com.siping.smartone.group.response;

import java.io.Serializable;

import com.siping.smartone.common.Common;

public class GroupResponse extends Common implements Serializable {

    private static final long serialVersionUID = -6170530966558722186L;
    private String groupName;
    private String description;
    private Boolean isDelete;
    private String userAccount;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
