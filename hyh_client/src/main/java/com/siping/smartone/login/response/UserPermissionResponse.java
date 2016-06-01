package com.siping.smartone.login.response;

import java.util.List;

public class UserPermissionResponse {
    private Integer treeId;
    private String treeName;
    private List<UserNodeResponse> nodeList;

    public Integer getTreeId() {
        return treeId;
    }

    public void setTreeId(Integer treeId) {
        this.treeId = treeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public List<UserNodeResponse> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<UserNodeResponse> nodeList) {
        this.nodeList = nodeList;
    }
}
