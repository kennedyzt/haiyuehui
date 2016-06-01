package com.siping.intranet.report.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.EndPoint;
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

@Repository
public class InventoryReportRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    @SuppressWarnings("unchecked")
    public PagingResponse<GetInventoryBalanceResponse> getList(GetInventoryBalanceRequest request) {
        String requestContent = JSONBinder.binder(GetInventoryBalanceRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetInventoryBalanceResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/inventorybalance/getlist").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetInventoryBalanceDetailResponse> getBalanceDetailList(GetInventoryBalanceRequest request) {
        if (null == request.getPageNo()) {
            request.setPageNo(1);
        }
        if (null == request.getPageSize()) {
            request.setPageSize(10);
        }
        String requestContent = JSONBinder.binder(GetInventoryBalanceRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetInventoryBalanceDetailResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/inventorybalance/getDetaillist").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetInventoryAuditResponse> getList(Integer pageNo, Integer pageSize, GetInventoryAuditListRequest request) {
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        String requestContent = JSONBinder.binder(GetInventoryAuditListRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetInventoryAuditResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/reportauditlist/getlist").body(requestContent));
    }

    public GetInventoryAuditResponse getDetailList(GetInventoryAuditListRequest request) {
        if (null != request && !StringUtils.hasText(request.getMaterialNo())) {
            throw new BusinessProcessException("条件不足不能获取库存审核明细！");
        }
        String requestContent = JSONBinder.binder(GetInventoryAuditListRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(GetInventoryAuditResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/reportauditlist/detail").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetInventoryStatusResponse> getStatusList(Integer pageNo, Integer pageSize, GetInventoryStatusRequest request) {
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        String requestContent = JSONBinder.binder(GetInventoryStatusRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetInventoryStatusResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/reportinventorystatus/getlist").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<GetInventoryStatusDetailResponse> getStatusDetailList(GetInventoryStatusRequest req) {
        if (null != req && !StringUtils.hasText(req.getMaterialNo())) {
            throw new BusinessProcessException("条件不足不能获取库存审核明细！");
        }
        String requestContent = JSONBinder.binder(GetInventoryStatusRequest.class).toJSON(req);
        return stromaWebServiceApi.post(EndPointBuilder.create(List.class).elementTypes(GetInventoryStatusDetailResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/reportinventorystatus/detail").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetInventoryBatchResponse> getBatchList(Integer pageNo, Integer pageSize, GetInventoryBatchRequest request) {
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        String requestContent = JSONBinder.binder(GetInventoryBatchRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetInventoryBatchResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/reportinventorybatch/getlist").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<GetInventoryBatchDetailResponse> getBatchDetailList(GetInventoryBatchRequest req) {
        if (null != req && !StringUtils.hasText(req.getMaterialNo())) {
            throw new BusinessProcessException("条件不足不能获取批号交易明细！");
        }
        String requestContent = JSONBinder.binder(GetInventoryBatchRequest.class).toJSON(req);
        return stromaWebServiceApi.post(EndPointBuilder.create(List.class).elementTypes(GetInventoryBatchDetailResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/reportinventorybatch/detail").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<SingleMaterialCostResponse> getCost(String materialid) {
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(SingleMaterialCostResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/inventorybalance/getcost/%s").arguments(materialid));
    }
}
