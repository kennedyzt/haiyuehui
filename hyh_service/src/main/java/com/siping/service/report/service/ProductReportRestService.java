package com.siping.service.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.report.repository.ProductReportRestRepository;
import com.siping.smartone.inventory.request.ProductExpirationRequest;
import com.siping.smartone.inventory.response.ProductExpirationResponse;

@Service
public class ProductReportRestService extends DBSwitch {

    @Autowired
    private ProductReportRestRepository productReportRestRepository;

    public PagingResponse<ProductExpirationResponse> getExpirationList(ProductExpirationRequest request) {
        return productReportRestRepository.getExpirationList(request);
    }
}
