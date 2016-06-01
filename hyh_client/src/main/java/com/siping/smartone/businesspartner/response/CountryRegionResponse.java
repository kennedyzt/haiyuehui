package com.siping.smartone.businesspartner.response;

import java.io.Serializable;

public class CountryRegionResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String countryRegionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryRegionName() {
        return countryRegionName;
    }

    public void setCountryRegionName(String countryRegionName) {
        this.countryRegionName = countryRegionName;
    }

}
