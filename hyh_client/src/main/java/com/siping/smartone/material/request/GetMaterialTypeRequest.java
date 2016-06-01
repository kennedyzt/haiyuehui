package com.siping.smartone.material.request;

import java.io.Serializable;

public class GetMaterialTypeRequest implements Serializable {
    private static final long serialVersionUID = 5084910872075814005L;
    private Integer id; // 新增时，该字段为空
    private String typeName; // 物料组名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
