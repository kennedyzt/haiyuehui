package com.siping.smartone.permission.response;

import java.io.Serializable;
import java.util.List;

public class GetUserPermissionResponse implements Serializable {
    private static final long serialVersionUID = -5172560488216524578L;
    private Integer userId;
    private String userName;
    private List<GetNodePermissionResponse> nodeList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<GetNodePermissionResponse> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<GetNodePermissionResponse> nodeList) {
        this.nodeList = nodeList;
    }
}
