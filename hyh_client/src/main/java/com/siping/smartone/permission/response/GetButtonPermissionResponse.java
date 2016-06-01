package com.siping.smartone.permission.response;

import java.io.Serializable;

public class GetButtonPermissionResponse implements Serializable {
    private static final long serialVersionUID = -4860634535702092465L;

    private String groupName;
    private Integer userId;
    private String menuName;
    private Integer menuLevel;
    private String menuUrl;
    private Integer addBtn;
    private Integer editBtn;
    private Integer delBtn;
    private Integer printBtn;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getAddBtn() {
        return addBtn;
    }

    public void setAddBtn(Integer addBtn) {
        this.addBtn = addBtn;
    }

    public Integer getEditBtn() {
        return editBtn;
    }

    public void setEditBtn(Integer editBtn) {
        this.editBtn = editBtn;
    }

    public Integer getDelBtn() {
        return delBtn;
    }

    public void setDelBtn(Integer delBtn) {
        this.delBtn = delBtn;
    }

    public Integer getPrintBtn() {
        return printBtn;
    }

    public void setPrintBtn(Integer printBtn) {
        this.printBtn = printBtn;
    }

}
