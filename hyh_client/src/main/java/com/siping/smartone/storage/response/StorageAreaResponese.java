package com.siping.smartone.storage.response;

import java.io.Serializable;

public class StorageAreaResponese implements Serializable {

    private static final long serialVersionUID = 4870968214595956302L;
    private String id;
    private String storageId;
    private String areaNo;
    private String areaName;
    private String remark;
    private Boolean isDelete;
    private String createDate;
    private Integer createBy;
    private String updateDate;
    private Integer updateBy;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getStorageId() {
        return storageId;
    }
    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }
    public String getAreaNo() {
        return areaNo;
    }
    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }
    public String getAreaName() {
        return areaName;
    }
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Boolean getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public Integer getCreateBy() {
        return createBy;
    }
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }
    public String getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
    public Integer getUpdateBy() {
        return updateBy;
    }
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

}
