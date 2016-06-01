package com.siping.wms.pda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.usr.repository.UsrRepository;
import com.siping.smartone.inventory.request.InventoryReceiptRequest;
import com.siping.smartone.inventory.response.GetInventoryCheckResponseNew;
import com.siping.smartone.inventory.response.InventoryReceiptItemResponse;
import com.siping.smartone.inventory.response.InventoryReceiptResponse;
import com.siping.smartone.login.request.UserLoginRequest;
import com.siping.smartone.login.response.UserLoginResponse;
import com.siping.smartone.material.response.GetMaterialBatchResponse;
import com.siping.smartone.wms.request.AddInventoryAllocateRequest;
import com.siping.wms.pda.repository.PdaRepository;

@Service
public class PdaService {

    @Autowired
    private UsrRepository usrRepository;
    @Autowired
    private PdaRepository pdaRepository;

    public UserLoginResponse login(UserLoginRequest request) {
        UserLoginResponse user = null;
        if ("admin".equals(request.getUsername())) {
            throw new BusinessProcessException("此用户名不能登录");
        }
        user = usrRepository.login(request);
        return user;
    }

    public PagingResponse<GetInventoryCheckResponseNew> getInventoryCheckList(Integer pageNo, Integer pageSize, Integer userid, Integer status) {
        PagingResponse<GetInventoryCheckResponseNew> list = pdaRepository.getInventoryCheckList(pageNo, pageSize, userid, status);
        return list;
    }

    public PagingResponse<InventoryReceiptResponse> getReceiptOrderList(Integer pageNo, Integer pageSize, Integer userid, Integer status) {
        PagingResponse<InventoryReceiptResponse> list = pdaRepository.getReceiptOrderList(pageNo, pageSize, userid, status);
        return list;
    }

    public InventoryReceiptResponse get(String receiptNumber) {
        return pdaRepository.get(receiptNumber);
    }

    public boolean add(InventoryReceiptRequest request) {
        return pdaRepository.add(request);
    }

    public boolean delete(List<String> receiptNumbers) {
        return pdaRepository.delete(receiptNumbers);
    }

    public boolean edit(InventoryReceiptItemResponse request) {
        return pdaRepository.edit(request);
    }

    public boolean addItem(InventoryReceiptItemResponse request) {
        return pdaRepository.addItem(request);
    }

    public String getRowNumber(String batchNumber,int materialId) {
        return pdaRepository.getRowNumber(batchNumber, materialId);
    }

    public List<GetMaterialBatchResponse> getMaterialBatchByStorageLocation(String locationNo) {
        return pdaRepository.getMaterialBatchByStorageLocation(locationNo);
    }

    public List<GetMaterialBatchResponse> getStorageLocationWithMaterialBatchByMaterial(String materialNo) {
        return pdaRepository.getStorageLocationWithMaterialBatchByMaterial(materialNo);
    }

    public Boolean checkBatchNo(String batchNo, String materialId) {
        return pdaRepository.checkBatchNo(batchNo, materialId);
    }

    public boolean commitInventoryCheck(GetInventoryCheckResponseNew request) {
        // TODO Auto-generated method stub
        return pdaRepository.commitInventoryCheck(request);
    }

    public GetInventoryCheckResponseNew getInventoryCheck(String checkNumber) {
        // TODO Auto-generated method stub
        return pdaRepository.getInventoryCheck(checkNumber);
    }

    public Boolean checkAllocateNumber(AddInventoryAllocateRequest request) {
        return pdaRepository.checkAllocateNumber(request);
    }
}
