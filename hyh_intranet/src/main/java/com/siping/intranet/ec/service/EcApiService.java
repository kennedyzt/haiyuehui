package com.siping.intranet.ec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siping.intranet.ec.repository.EcApiRepository;
import com.siping.smartone.ec.request.AddCustomerRequest;
import com.siping.smartone.ec.request.AddVendorRequest;
import com.siping.smartone.ec.response.ProductCustomsResponse;
import com.siping.smartone.wms.request.ESaleOrderRequest;

@Service
public class EcApiService {
    @Autowired
    private EcApiRepository ecApiRepository;

    public Boolean addShop(AddVendorRequest request) {
        return ecApiRepository.addShop(request);
    }

    public Boolean addCustomer(AddCustomerRequest request) {
        return ecApiRepository.addCustomer(request);
    }

    public ProductCustomsResponse checkMaterialNo(String materialno) {
        return ecApiRepository.checkMaterialNo(materialno);
    }

    public String getOnhandBalance(String productNo) {
        return ecApiRepository.getOnhandBalance(productNo);
    }

    public boolean addEcSalesorder(ESaleOrderRequest request) {
        return ecApiRepository.addEcSalesorder(request);
    }

    public boolean subtractQuantity(String productNo, Float quantity) {
        return ecApiRepository.subtractQuantity(productNo, quantity);
    }

    public boolean addQuantity(String productNo, Float quantity) {
        return ecApiRepository.addQuantity(productNo, quantity);
    }
}