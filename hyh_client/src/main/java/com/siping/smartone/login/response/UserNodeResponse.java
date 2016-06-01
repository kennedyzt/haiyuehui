package com.siping.smartone.login.response;

import java.io.Serializable;

public class UserNodeResponse implements Serializable {
    private static final long serialVersionUID = 7059617560417566018L;
    private Integer id;
    private String nodeName;
    private Integer sequence;
    private Integer parentId;
    private Integer menuTreeId;
    private Integer level;
    private Boolean isRoot;
    private String nodeUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getMenuTreeId() {
        return menuTreeId;
    }

    public void setMenuTreeId(Integer menuTreeId) {
        this.menuTreeId = menuTreeId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(Boolean isRoot) {
        this.isRoot = isRoot;
    }

    public String getNodeUrl() {
        return nodeUrl;
    }

    public void setNodeUrl(String nodeUrl) {
        this.nodeUrl = nodeUrl;
    }
}
