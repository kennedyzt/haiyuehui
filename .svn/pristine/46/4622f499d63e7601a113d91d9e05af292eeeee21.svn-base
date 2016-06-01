package com.siping.wms.allocate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.usr.repository.UsrRepository;
import com.siping.smartone.inventory.request.InventoryReceiptItemRequest;
import com.siping.smartone.inventory.response.InventoryReceiptResponse;
import com.siping.smartone.login.request.UserLoginRequest;
import com.siping.smartone.login.response.UserLoginResponse;
import com.siping.smartone.wms.request.AddInventoryAllocateRequest;
import com.siping.smartone.wms.request.GetInventoryAllocateListRequest;
import com.siping.smartone.wms.response.GetInventoryAllocateResponse;
import com.siping.wms.allocate.repository.InventoryAllocateRepository;
import com.siping.wms.pda.repository.PdaRepository;

@Service
public class InventoryAllocateService {

    @Autowired
    private UsrRepository usrRepository;
    @Autowired
    private InventoryAllocateRepository inventoryAllocateRepository;

    public UserLoginResponse login(UserLoginRequest request) {
        UserLoginResponse user = null;
        if ("admin".equals(request.getUsername())) {
            throw new BusinessProcessException("此用户名不能登录");
        }
        user = usrRepository.login(request);
        return user;
    }

    public PagingResponse<GetInventoryAllocateResponse> getInventoryAllocationList(Integer pageNo, Integer pageSize, GetInventoryAllocateListRequest getInventoryAllocateListRequest) {
        return inventoryAllocateRepository.getInventoryAllocationList(pageNo, pageSize, getInventoryAllocateListRequest);
    }
    
    public GetInventoryAllocateResponse get(String allocateNumber){
        return inventoryAllocateRepository.get(allocateNumber);
    }

    public boolean add(AddInventoryAllocateRequest request) {
        return inventoryAllocateRepository.add(request);
    }
}
