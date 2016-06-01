package com.siping.service.businesspartner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.businesspartner.repository.ShopRestRepository;
import com.siping.smartone.businesspartner.response.ShopResponse;
import com.siping.smartone.common.request.CommonBillsRequest;

@Service
public class ShopRestService extends DBSwitch{

    @Autowired
    private ShopRestRepository shopRestRepository;
    public PagingResponse<ShopResponse> getList(CommonBillsRequest request) {
        return shopRestRepository.getList(request);
    }

}
