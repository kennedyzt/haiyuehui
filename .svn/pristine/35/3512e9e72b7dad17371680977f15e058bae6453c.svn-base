package com.siping.intranet.invoicing.inventory.inventory.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.inventory.request.MaterialCountsRequest;
import com.siping.smartone.inventory.response.InventoryWarningMsg;
import com.siping.smartone.invoicing.inventory.inventory.request.AddInventoryRequest;
import com.siping.smartone.invoicing.inventory.inventory.request.GetInventoryIsEnoughRequest;
import com.siping.smartone.invoicing.inventory.inventory.response.GetInventoryLogResponse;
import com.siping.smartone.invoicing.inventory.inventory.response.GetInventoryResponse;

@Repository
public class InventoryRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(AddInventoryRequest request) {
        validAddMaterialRequest(request);
        String requestContent = JSONBinder.binder(AddInventoryRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventory/add").body(requestContent));
    }

    @Valid
    private void validAddMaterialRequest(AddInventoryRequest request) {
        if (null == request.getMaterialId())
            throw new BusinessProcessException("物料不能为空！");
        if (null == request.getStorageId())
            throw new BusinessProcessException("仓库不能为空！");
        if (!StringUtils.hasText(request.getBatchNumber()))
            throw new BusinessProcessException("物料批次号不能为空！");
        if (!StringUtils.hasText(request.getProductionDate()))
            throw new BusinessProcessException("生产日期不能为空！");
    }

    public Boolean edit(AddInventoryRequest request) {
        if (null == request || null != request.getId()) {
            throw new BusinessProcessException("未选择需要更新的库存！");
        }
        validAddMaterialRequest(request); // 也需要校验必填项
        String requestContent = JSONBinder.binder(AddInventoryRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventory/edit").body(requestContent));
    }

    public GetInventoryResponse get(String id) {
        if (!StringUtils.hasText(id)) {
            throw new BusinessProcessException("条件不足不能获取库存！");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(GetInventoryResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventory/get/%s").arguments(id));
    }

    @SuppressWarnings("unchecked")
    public List<GetInventoryLogResponse> getList(String materialId, String storageId) {
        if (!StringUtils.hasText(materialId) || !StringUtils.hasText(storageId)) {
            throw new BusinessProcessException("条件不足不能获取库存日志！");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(GetInventoryLogResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventorylog/getlist/%s/%s")
            .arguments(materialId, storageId));
    }

    public Boolean isEnough(GetInventoryIsEnoughRequest request) {
        if (null == request.getMaterialId() || null == request.getStorageId() || null == request.getCounts()) {
            throw new BusinessProcessException("条件不足不能判断当前库存数是否足够！");
        }
        String requestContent = JSONBinder.binder(GetInventoryIsEnoughRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventory/isenough").body(requestContent));
    }

    public InventoryWarningMsg isMoreThan(List<MaterialCountsRequest> request) {
        String requestContent = JSONBinder.binder(GetInventoryIsEnoughRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(InventoryWarningMsg.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/inventory/ismorethan").body(requestContent));
    }
}
