package com.siping.smartone.material.response;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.siping.smartone.common.DateJsonDeserializer;
import com.siping.smartone.common.DateJsonSerializer;

public class GetMaterialUsageResponse implements Serializable {
    private static final long serialVersionUID = 5084910872075814005L;
    private String id;
    private String usageName;
    private Boolean isDelete;
    private String description;
    private Integer createBy;
    private Date createDate;
    private Integer updateBy;
    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsageName() {
        return usageName;
    }

    public void setUsageName(String usageName) {
        this.usageName = usageName;
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

    @JsonSerialize(using = DateJsonSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonDeserialize(using = DateJsonDeserializer.class)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    @JsonSerialize(using = DateJsonSerializer.class)
    public Date getUpdateDate() {
        return updateDate;
    }

    @JsonDeserialize(using = DateJsonDeserializer.class)
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
