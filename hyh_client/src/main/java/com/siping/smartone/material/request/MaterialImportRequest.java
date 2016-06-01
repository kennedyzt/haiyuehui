package com.siping.smartone.material.request;

import java.io.Serializable;
import java.util.List;

public class MaterialImportRequest implements Serializable {

    private static final long serialVersionUID = 6462409540965543937L;

    private Integer createdBy;
    
    private List<MaterialImportSuccessRequest> successList;
    
    private List<MaterialImportFailureRequest> failureList;

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public List<MaterialImportSuccessRequest> getSuccessList() {
        return successList;
    }

    public void setSuccessList(List<MaterialImportSuccessRequest> successList) {
        this.successList = successList;
    }

    public List<MaterialImportFailureRequest> getFailureList() {
        return failureList;
    }

    public void setFailureList(List<MaterialImportFailureRequest> failureList) {
        this.failureList = failureList;
    }
}
