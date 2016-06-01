package com.siping.smartone.menu.response;

import java.io.Serializable;

import com.siping.smartone.common.Common;

public class MenuNodeResponse extends Common implements Serializable {

    private static final long serialVersionUID = 4040606665833478726L;

    /**
     * menu_node.node_name
     * @ibatorgenerated 2015-05-13 11:15:49
     */
    private String name;

    /**
     * menu_node.sequence
     * @ibatorgenerated 2015-05-13 11:15:49
     */
    private Integer sequence;

    /**
     * menu_node.description
     * @ibatorgenerated 2015-05-13 11:15:49
     */
    private String description;

    /**
     * menu_node.parent_id
     * @ibatorgenerated 2015-05-13 11:15:49
     */
    private Integer parentId;

    private String parentName;


    /**
     * menu_node.level
     * @ibatorgenerated 2015-05-13 11:15:49
     */
    private Integer level;

    /**
     * menu_node.is_root
     * @ibatorgenerated 2015-05-13 11:15:49
     */
    private Boolean isRoot;

    /**
     * menu_node.node_url
     * @ibatorgenerated 2015-05-13 11:15:49
     */
    private String nodeUrl;

    /**
     * menu_node.is_delete
     * @ibatorgenerated 2015-05-13 11:15:49
     */
    private Boolean isDelete;
    
    private String userAccount;

    private String icon;

    private String menuRoot;

    public String getName() {
        return name;
    }

    public void setName(String nodeName) {
        this.name = nodeName;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

}