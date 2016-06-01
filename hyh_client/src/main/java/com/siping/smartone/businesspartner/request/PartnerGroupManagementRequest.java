package com.siping.smartone.businesspartner.request;

import java.io.Serializable;
import java.util.List;

public class PartnerGroupManagementRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Integer> partnerIds;
    private Integer partnerGroupId;

    public List<Integer> getPartnerIds() {
        return partnerIds;
    }

    public void setPartnerIds(List<Integer> partnerIds) {
        this.partnerIds = partnerIds;
    }

    public Integer getPartnerGroupId() {
        return partnerGroupId;
    }

    public void setPartnerGroupId(Integer partnerGroupId) {
        this.partnerGroupId = partnerGroupId;
    }

}
