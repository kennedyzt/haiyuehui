package com.siping.smartone.businesspartner.request;

import java.io.Serializable;
import java.util.List;

public class BusinessPartnerBatchAddRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer createdBy;

    private List<BusinessPartnerBatchAddSuccessRequest> businessPartners;

    private List<BusinessPartnerBatchAddErrorRequest> errors;

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public List<BusinessPartnerBatchAddErrorRequest> getErrors() {
        return errors;
    }

    public void setErrors(List<BusinessPartnerBatchAddErrorRequest> errors) {
        this.errors = errors;
    }

    public List<BusinessPartnerBatchAddSuccessRequest> getBusinessPartners() {
        return businessPartners;
    }

    public void setBusinessPartners(List<BusinessPartnerBatchAddSuccessRequest> businessPartners) {
        this.businessPartners = businessPartners;
    }

}
