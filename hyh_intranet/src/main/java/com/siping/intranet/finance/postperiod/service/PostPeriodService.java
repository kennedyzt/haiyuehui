package com.siping.intranet.finance.postperiod.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.finance.postperiod.repository.PostPeriodRepository;
import com.siping.smartone.postperiod.request.AddPostPeriodRequest;
import com.siping.smartone.postperiod.response.GetPostPeriodResponse;

@Service
public class PostPeriodService {
    @Autowired
    private PostPeriodRepository postPeriodRepository;
    
    public Boolean add(AddPostPeriodRequest request) {
        return postPeriodRepository.add(request);
    }

    public Boolean edit(AddPostPeriodRequest request) {
        return postPeriodRepository.edit(request);
    }

    public GetPostPeriodResponse get(String id) {
        return postPeriodRepository.get(id);
    }

    public PagingResponse<GetPostPeriodResponse> getList(Integer pageNo, Integer pageSize) {
        return postPeriodRepository.getList(pageNo, pageSize);
    }

    public Boolean delete(List<String> ids, String updateBy) {
        return postPeriodRepository.delete(ids, updateBy);
    }
    
    public GetPostPeriodResponse getMax(){
        return postPeriodRepository.getMax();
    }
}
