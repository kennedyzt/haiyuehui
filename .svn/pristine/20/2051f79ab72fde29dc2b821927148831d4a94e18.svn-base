package com.siping.intranet.crm.countryregion.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siping.intranet.crm.countryregion.repository.CountryRegionRepository;
import com.siping.smartone.businesspartner.request.CountryRegionRequest;
import com.siping.smartone.businesspartner.response.CountryRegionResponse;

@Service
public class CountryRegionService {
    @Autowired
    private CountryRegionRepository countryRegionRepository;

    public Boolean add(CountryRegionRequest request) {
        return countryRegionRepository.add(request);
    }

    public List<CountryRegionResponse> getList() {
        return countryRegionRepository.getList();
    }

    public CountryRegionResponse get(Integer id) {
        return countryRegionRepository.get(id);
    }

    public Boolean delete(Integer id, Integer loginId) {
        return countryRegionRepository.delete(id, loginId);
    }

}
