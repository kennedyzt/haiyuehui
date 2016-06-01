package com.siping.wms.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.smartone.report.CommonReportRequest;
import com.siping.smartone.report.response.QueryGoodsTransportResponse;
import com.siping.wms.report.repository.QueryGoodsTransportRestRepository;

@Service
public class QueryGoodsTransportRestService extends DBSwitch {
    @Autowired
    private QueryGoodsTransportRestRepository queryGoodsTransportRestRepository;

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<QueryGoodsTransportResponse> getList(CommonReportRequest request) {
        return queryGoodsTransportRestRepository.getList(request);
    }

}
