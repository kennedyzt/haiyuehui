package com.siping.smartone.material.request;

import java.io.Serializable;

public class AddMaterialGroupRequest implements Serializable {
    private static final long serialVersionUID = 5084910872075814005L;
    private String id; // 新增时，该字段为空
    private String groupName; // 物料用途
    private Boolean isDelete; // 是否删除
    private String description; // 描述
    private Integer createBy; // 新增时，该字段为空
    private Integer updateBy;

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

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
}