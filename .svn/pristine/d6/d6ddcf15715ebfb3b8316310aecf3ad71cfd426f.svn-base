package com.siping.intranet.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.report.repository.InventoryReportRepository;
import com.siping.smartone.inventory.request.GetInventoryAuditListRequest;
import com.siping.smartone.inventory.request.GetInventoryBalanceRequest;
import com.siping.smartone.inventory.request.GetInventoryBatchRequest;
import com.siping.smartone.inventory.request.GetInventoryStatusRequest;
import com.siping.smartone.inventory.response.GetInventoryAuditResponse;
import com.siping.smartone.inventory.response.GetInventoryBalanceDetailResponse;
import com.siping.smartone.inventory.response.GetInventoryBalanceResponse;
import com.siping.smartone.inventory.response.GetInventoryBatchDetailResponse;
import com.siping.smartone.inventory.response.GetInventoryBatchResponse;
import com.siping.smartone.inventory.response.GetInventoryStatusDetailResponse;
import com.siping.smartone.inventory.response.GetInventoryStatusResponse;
import com.siping.smartone.report.response.SingleMaterialCostResponse;

@Service
public class InventoryReportService {
    @Autowired
    private InventoryReportRepository inventoryReportRepository;

    public PagingResponse<GetInventoryBalanceResponse> getList(GetInventoryBalanceRequest request) {
        return inventoryReportRepository.getList(request);
    }

    public PagingResponse<GetInventoryBalanceDetailResponse> getBalanceDetailList(GetInventoryBalanceRequest request) {
        return inventoryReportRepository.getBalanceDetailList(request);
    }

    public GetInventoryAuditResponse getDetailList(GetInventoryAuditListRequest request) {
        return inventoryReportRepository.getDetailList(request);
    }

    public PagingResponse<GetInventoryStatusResponse> getStatusList(Integer pageNo, Integer pageSize, GetInventoryStatusRequest request) {
        return inventoryReportRepository.getStatusList(pageNo, pageSize, request);
    }

    public List<GetInventoryStatusDetailResponse> getStatusDetailList(GetInventoryStatusRequest req) {
        return inventoryReportRepository.getStatusDetailList(req);
    }

    public PagingResponse<GetInventoryBatchResponse> getBatchList(Integer pageNo, Integer pageSize, GetInventoryBatchRequest request) {
        return inventoryReportRepository.getBatchList(pageNo, pageSize, request);
    }

    public List<GetInventoryBatchDetailResponse> getBatchDetailList(GetInventoryBatchRequest request) {
        return inventoryReportRepository.getBatchDetailList(request);
    }

    public List<SingleMaterialCostResponse> getCost(String materialid) {
        return inventoryReportRepository.getCost(materialid);
    }
}
