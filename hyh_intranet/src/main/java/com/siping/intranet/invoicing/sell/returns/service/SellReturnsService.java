package com.siping.intranet.invoicing.sell.returns.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.common.CommonBillsForm;
import com.siping.intranet.invoicing.sell.returns.repository.SellReturnsRepository;
import com.siping.smartone.invoicing.sell.returns.request.AddSellReturnsRequest;
import com.siping.smartone.invoicing.sell.returns.response.GetSellReturnsListResponse;
import com.siping.smartone.invoicing.sell.returns.response.GetSellReturnsResponse;

@Service
public class SellReturnsService {
    @Autowired
    private SellReturnsRepository sellReturnsRepository;

    public Boolean add(AddSellReturnsRequest request) {
        return sellReturnsRepository.add(request);
    }

    public Boolean edit(AddSellReturnsRequest request) {
        return sellReturnsRepository.edit(request);
    }

    public GetSellReturnsResponse get(String returnsNumber) {
        return sellReturnsRepository.get(returnsNumber);
    }

    public PagingResponse<GetSellReturnsListResponse> getList(Integer pageNo, Integer pageSize, CommonBillsForm form) {
        return sellReturnsRepository.getList(pageNo, pageSize, form);
    }

    public Boolean delete(final List<String> returnsNumberList, Integer userId) {
        return sellReturnsRepository.delete(returnsNumberList, userId);
    }
}
