package com.siping.intranet.crm.businesspartner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.businesspartner.repository.ShopRepository;
import com.siping.smartone.businesspartner.response.ShopResponse;
import com.siping.smartone.common.request.CommonBillsRequest;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    public PagingResponse<ShopResponse> getList(Integer pageNo, Integer pageSize, CommonBillsRequest request) {
        return shopRepository.getList(pageNo,pageSize,request);
    }
}
