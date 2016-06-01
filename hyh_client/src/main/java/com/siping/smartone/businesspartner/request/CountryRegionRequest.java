package com.siping.smartone.businesspartner.request;

import java.io.Serializable;

public class CountryRegionRequest implements Serializable {
    private static final long serialVersionUID = -780037084576319985L;
    private String countryRegionName;
    private Integer loginId;

    public String getCountryRegionName() {
        return countryRegionName;
    }

    public void setCountryRegionName(String countryRegionName) {
        this.countryRegionName = countryRegionName;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }
}
