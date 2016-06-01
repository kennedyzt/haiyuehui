package com.siping.smartone.material.request;

import java.io.Serializable;
import java.util.List;

public class AddMaterialGroupRelationRequest implements Serializable {
    private static final long serialVersionUID = 5084910872075814005L;
    private String id; // 新增时，该字段为空
    private Integer materialGroupId; // 商品组
    private List<Integer> materialIdList; // 商品id
    private Boolean isDelete;
    private Integer createBy; 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getMaterialGroupId() {
        return materialGroupId;
    }

    public void setMaterialGroupId(Integer materialGroupId) {
        this.materialGroupId = materialGroupId;
    }

    public List<Integer> getMaterialIdList() {
        return materialIdList;
    }

    public void setMaterialIdList(List<Integer> materialIdList) {
        this.materialIdList = materialIdList;
    }
}
