package com.siping.intranet.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.report.repository.ProductReportRepository;
import com.siping.smartone.inventory.request.ProductExpirationRequest;
import com.siping.smartone.inventory.response.ProductExpirationResponse;

@Service
public class ProductReportService {

    @Autowired
    private ProductReportRepository productReportRepository;

    public PagingResponse<ProductExpirationResponse> getExpListForSupplier(ProductExpirationRequest request) {
        return productReportRepository.getExpListForSupplier(request);
    }
}
