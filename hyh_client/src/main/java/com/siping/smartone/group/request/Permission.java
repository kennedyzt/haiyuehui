package com.siping.smartone.group.request;

import java.io.Serializable;

public class Permission implements Serializable {
    private static final long serialVersionUID = -2014839422265596808L;
    private Integer id;
    private Integer groupId;
    private String groupName;
    private Integer nodeId;
    private Integer level;
    private Integer sequence;
    private Integer parentId;
    private String nodeName;
    private String nodeUrl;
    private String icon;
    private String menuRoot;
    private Boolean isAdd = false;
    private Boolean isEdit = false;
    private Boolean isDelete = false;
    private Boolean isPrint = false;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getGroupId() {
        return groupId;
    }
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public Integer getNodeId() {
        return nodeId;
    }
    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
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
    public String getNodeName() {
        return nodeName;
    }
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    public String getNodeUrl() {
        return nodeUrl;
    }
    public void setNodeUrl(String nodeUrl) {
        this.nodeUrl = nodeUrl;
    }
    public Boolean getIsAdd() {
        return isAdd;
    }
    public void setIsAdd(Boolean isAdd) {
        this.isAdd = isAdd;
    }
    public Boolean getIsEdit() {
        return isEdit;
    }
    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
    public Boolean getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
    public Boolean getIsPrint() {
        return isPrint;
    }
    public void setIsPrint(Boolean isPrint) {
        this.isPrint = isPrint;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getMenuRoot() {
        return menuRoot;
    }
    public void setMenuRoot(String menuRoot) {
        this.menuRoot = menuRoot;
    }

}
