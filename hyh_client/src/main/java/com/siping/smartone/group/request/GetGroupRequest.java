package com.siping.smartone.group.request;

import java.io.Serializable;

public class GetGroupRequest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4691815480531595889L;
    private String id;
    private String groupName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
