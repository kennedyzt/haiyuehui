package com.siping.service.postperiod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.postperiod.repository.PostPeriodRestRepository;
import com.siping.smartone.postperiod.request.AddPostPeriodRequest;
import com.siping.smartone.postperiod.request.DeletePostPeriodRequest;
import com.siping.smartone.postperiod.request.GetPostPeriodListRequest;
import com.siping.smartone.postperiod.response.GetPostPeriodResponse;
import com.siping.service.common.BillsRepository;


@Service
public class PostPeriodRestService extends DBSwitch {
    @Autowired
    private PostPeriodRestRepository postPeriodRestRepository;
    @Autowired
    private BillsRepository billsRepository;
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(AddPostPeriodRequest request) {
        return postPeriodRestRepository.add(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(AddPostPeriodRequest request) {
        return postPeriodRestRepository.edit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GetPostPeriodResponse get(String id) {
        return postPeriodRestRepository.get(id);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetPostPeriodResponse> getList(GetPostPeriodListRequest request) {
        return postPeriodRestRepository.getList(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(DeletePostPeriodRequest request) {
        return postPeriodRestRepository.delete(request);
    }
    
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GetPostPeriodResponse getMax() {
        return postPeriodRestRepository.getMax();
    }
    
}
