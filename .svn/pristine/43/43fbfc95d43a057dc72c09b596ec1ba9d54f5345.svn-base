package com.siping.wms.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.smartone.report.CommonReportRequest;
import com.siping.smartone.report.response.QueryGoodsTransportResponse;
import com.siping.wms.report.repository.QueryGoodsTransportRepository;

@Service
public class QueryGoodsTransportService {
    @Autowired
    private QueryGoodsTransportRepository queryGoodsTransportRepository;

    public PagingResponse<QueryGoodsTransportResponse> getList(CommonReportRequest request) {
        return queryGoodsTransportRepository.getList(request);
    }
}
