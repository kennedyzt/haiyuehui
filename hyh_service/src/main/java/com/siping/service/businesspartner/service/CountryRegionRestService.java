package com.siping.service.businesspartner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;

import com.siping.service.businesspartner.repository.CountryRegionRestRepository;
import com.siping.smartone.businesspartner.request.CountryRegionRequest;
import com.siping.smartone.businesspartner.response.CountryRegionResponse;

@Service
public class CountryRegionRestService extends DBSwitch {
    @Autowired
    private CountryRegionRestRepository countryRegionRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(CountryRegionRequest request) {
        return countryRegionRestRepository.add(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<CountryRegionResponse> getList() {
        return countryRegionRestRepository.getList();
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public CountryRegionResponse get(Integer id) {
        return countryRegionRestRepository.get(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(Integer id, Integer loginId) {
        return countryRegionRestRepository.delete(id,loginId);
    }

}
