package com.siping.intranet.crm.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.inventory.repository.InventoryReceiptRepository;
import com.siping.smartone.inventory.request.ChangeStatusParam;
import com.siping.smartone.inventory.request.GetInventoryReceiptListForm;
import com.siping.smartone.inventory.request.InventoryReceiptRequest;
import com.siping.smartone.inventory.response.InventoryReceiptResponse;
import com.siping.smartone.wms.request.ConfirmReceiptParam;

@Service
public class InventoryReceiptService {
    @Autowired
    private InventoryReceiptRepository inventoryReceiptRepository;

    public Boolean add(InventoryReceiptRequest request) {
        return inventoryReceiptRepository.add(request);

    }

    public Boolean edit(InventoryReceiptRequest request) {
        return inventoryReceiptRepository.edit(request);
    }

    public Boolean delete(List<String> receiptNumbers) {
        return inventoryReceiptRepository.delete(receiptNumbers);
    }

    public PagingResponse<InventoryReceiptResponse> getList(Integer pageNo, Integer pageSize, GetInventoryReceiptListForm request) {
        return inventoryReceiptRepository.getList(pageNo, pageSize, request);
    }

    public InventoryReceiptResponse get(String receiptNumber) {
        return inventoryReceiptRepository.get(receiptNumber);
    }

    public Boolean confirmReceipt(ConfirmReceiptParam request) {
        return inventoryReceiptRepository.confirmReceipt(request);
    }

    public Boolean changeStatus(ChangeStatusParam request) {
        return inventoryReceiptRepository.changeStatus(request);
    }

}
