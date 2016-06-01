package com.siping.service.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.report.repository.InventoryReportRestRepository;
import com.siping.smartone.inventory.request.GetInventoryBalanceRequest;
import com.siping.smartone.inventory.request.GetInventoryBatchRequest;
import com.siping.smartone.inventory.request.GetInventoryStatusRequest;
import com.siping.smartone.inventory.response.GetInventoryBalanceDetailResponse;
import com.siping.smartone.inventory.response.GetInventoryBalanceResponse;
import com.siping.smartone.inventory.response.GetInventoryBatchDetailResponse;
import com.siping.smartone.inventory.response.GetInventoryBatchResponse;
import com.siping.smartone.inventory.response.GetInventoryStatusDetailResponse;
import com.siping.smartone.inventory.response.GetInventoryStatusResponse;
import com.siping.smartone.report.response.SingleMaterialCostResponse;

@Service
public class InventoryReportRestService extends DBSwitch {
    @Autowired
    private InventoryReportRestRepository inventoryReportRestRepository;

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetInventoryBalanceResponse> getList(GetInventoryBalanceRequest request) {
        return inventoryReportRestRepository.getList(request);
    }

    public PagingResponse<GetInventoryBalanceDetailResponse> getDetaillist(GetInventoryBalanceRequest request) {
        return inventoryReportRestRepository.getDetaillist(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetInventoryStatusResponse> getStatusList(GetInventoryStatusRequest request) {
        return inventoryReportRestRepository.getStatusList(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<GetInventoryStatusDetailResponse> getStatusDetailList(GetInventoryStatusRequest req) {
        return inventoryReportRestRepository.getStatusDetailList(req);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GetInventoryBatchResponse> getBatchList(GetInventoryBatchRequest request) {
        return inventoryReportRestRepository.getBatchList(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<GetInventoryBatchDetailResponse> getBatchDetailList(GetInventoryBatchRequest req) {
        return inventoryReportRestRepository.getBatchDetailList(req);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<SingleMaterialCostResponse> getCost(String materialid) {
        return inventoryReportRestRepository.getCost(materialid);
    }
}
