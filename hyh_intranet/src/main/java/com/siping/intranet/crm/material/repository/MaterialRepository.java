package com.siping.intranet.crm.material.repository;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.inventory.request.AddInventoryWarningRequest;
import com.siping.smartone.inventory.response.GetInventoryCheckWithMaterialAndLocationInfo;
import com.siping.smartone.inventory.response.GetInventoryCheckWithMaterialAndLocationInfoRequest;
import com.siping.smartone.material.request.AddMaterialRequest;
import com.siping.smartone.material.request.DeleteMaterialRequest;
import com.siping.smartone.material.request.GetMaterialBatchRequest;
import com.siping.smartone.material.request.GetMaterialListByStorageRequest;
import com.siping.smartone.material.request.GetMaterialListRequest;
import com.siping.smartone.material.request.GetMaterialRequest;
import com.siping.smartone.material.request.GetOpeanWinMaterialRequest;
import com.siping.smartone.material.request.MaterialExportParamRequest;
import com.siping.smartone.material.response.GetMaterialBatchResponse;
import com.siping.smartone.material.response.GetMaterialResponse;

@Repository
public class MaterialRepository {
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(AddMaterialRequest request) {
        validAddMaterialRequest(request);
        String requestContent = JSONBinder.binder(AddMaterialRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/material/add").body(requestContent));
    }

    @Valid
    private void validAddMaterialRequest(AddMaterialRequest request) {
        if (!StringUtils.hasText(request.getMaterialNo()))
            throw new BusinessProcessException("商品货号不能为空！");
        if (!StringUtils.hasText(request.getMaterialName()))
            throw new BusinessProcessException("商品名称不能为空！");
        if (!StringUtils.hasText(request.getBarcode())){
            throw new BusinessProcessException("商品国际码不能为空！");
        }else{
            request.setBarcode(request.getBarcode().trim());//去掉字符串首尾的空格
        }
        if (null == request.getUnitId() || 0 == request.getUnitId())
            throw new BusinessProcessException("申报单位不能为空！");
        List<AddInventoryWarningRequest> filterRequests = new ArrayList<AddInventoryWarningRequest>();// 过滤未输入最大最小值的库存预警
        for (AddInventoryWarningRequest inventoryWarningRequest : request.getAddInventoryWarningRequests()) {
            if (!(inventoryWarningRequest.getMaxInventory().equals("") && inventoryWarningRequest.getMinInventory().equals(""))) {
                filterRequests.add(inventoryWarningRequest);
            }
        }
        request.setAddInventoryWarningRequests(filterRequests);
    }

    public Boolean edit(AddMaterialRequest request) {
        if (null == request || null == request.getId()) {
            throw new BusinessProcessException("未选择需要编辑的商品！");
        }
        validAddMaterialRequest(request); // 也需要校验必填项
        String requestContent = JSONBinder.binder(AddMaterialRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/material/edit").body(requestContent));
    }

    public GetMaterialResponse get(String id, String materialNo, String barcode, String description) {
        if (!StringUtils.hasText(id) && !StringUtils.hasText(materialNo) && !StringUtils.hasText(barcode) && !StringUtils.hasText(description)) {
            throw new BusinessProcessException("条件不足不能获得商品！");
        }
        GetMaterialRequest request = new GetMaterialRequest();
        request.setId(id);
        request.setMaterialNo(materialNo);
        request.setBarcode(barcode);
        request.setDescription(description);
        String requestContent = JSONBinder.binder(GetMaterialRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(GetMaterialResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/material/get").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetMaterialResponse> getList(Integer pageNo, Integer pageSize, GetMaterialListRequest request) {
        if (pageNo == null)
            pageNo = 1;
        if (pageSize == null)
            pageSize = 10;
        request.setPageNo(pageNo.toString());
        request.setPageSize(pageSize.toString());
        String requestContent = JSONBinder.binder(GetMaterialListRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetMaterialResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/material/getlist")
            .body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetMaterialResponse> getListForSupplier(Integer pageNo, Integer pageSize, GetMaterialListRequest request) {
        if (pageNo == null)
            pageNo = 1;
        if (pageSize == null)
            pageSize = 10;
        request.setPageNo(pageNo.toString());
        request.setPageSize(pageSize.toString());
        String requestContent = JSONBinder.binder(GetMaterialListRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetMaterialResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/material/supplier/getlist")
            .body(requestContent));
    }

    public Boolean delete(List<String> ids, String updateBy) {
        if (null == ids) {
            throw new BusinessProcessException("缺失主键不能删除业务伙伴");
        }
        if (null == updateBy) {
            throw new BusinessProcessException("当前登录出现问题，无法修改");
        }
        DeleteMaterialRequest deleteMaterialRequest = new DeleteMaterialRequest();
        deleteMaterialRequest.setIds(ids);
        deleteMaterialRequest.setUpdateBy(updateBy);
        String requestContent = JSONBinder.binder(GetMaterialListRequest.class).toJSON(deleteMaterialRequest);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/material/delete").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<GetMaterialResponse> getAllMaterials(MaterialExportParamRequest param) {
        String requestContent = jsonConverter.toString(param);
        return stromaWebServiceApi.post(EndPointBuilder.create(List.class).elementTypes(GetMaterialResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/material/export")
            .body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<GetMaterialResponse> getList(String keyword, Boolean isPurchase, Boolean isSell, Boolean isInventory) {
        if (!StringUtils.hasText(keyword)) {
            throw new BusinessProcessException("条件为空不能查询");
        }
        GetMaterialRequest request = new GetMaterialRequest();
        request.setKeyword(keyword);
        request.setIsPurchase(isPurchase);
        request.setIsSell(isSell);
        request.setIsInventory(isInventory);
        String requestContent = JSONBinder.binder(GetMaterialListByStorageRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(List.class).elementTypes(GetMaterialResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/material/getlistbykey")
            .body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<GetMaterialResponse> getList(String keyword, String storageId) {
        GetMaterialListByStorageRequest request = new GetMaterialListByStorageRequest();
        request.setKeyword(keyword);
        request.setStorageId(storageId);
        String requestContent = JSONBinder.binder(GetMaterialListByStorageRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(List.class).elementTypes(GetMaterialResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/material/getlist")
            .body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetMaterialResponse> getOpenWinList(int pageNo, int pageSize, GetOpeanWinMaterialRequest request) {
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        String requestContent = jsonConverter.toString(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetMaterialResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/material/getopenwinlist").body(requestContent));
    }

    public GetMaterialBatchResponse getMaterialBatch(String materialId, String batchNumber) {
        if (!StringUtils.hasText(materialId) || !StringUtils.hasText(batchNumber)) {
            throw new BusinessProcessException("条件不足不能获得商品的批次信息！");
        }
        GetMaterialBatchRequest request = new GetMaterialBatchRequest();
        request.setMaterialId(materialId);
        request.setMaterialBatch(batchNumber);
        String requestContent = JSONBinder.binder(GetMaterialBatchRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(GetMaterialBatchResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/material/getmaterialbatch").body(requestContent));
    }
    
    @SuppressWarnings("unchecked")
    public List<GetInventoryCheckWithMaterialAndLocationInfo> getInventoryCheckInfoWithStorageIdWithStorageArea(GetInventoryCheckWithMaterialAndLocationInfoRequest request) {
        String requestContent = JSONBinder.binder(GetInventoryCheckWithMaterialAndLocationInfoRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(List.class).elementTypes(GetInventoryCheckWithMaterialAndLocationInfo.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/material/getInventoryCheckInfoWithStorageIdWithStorageArea").body(requestContent));
    }
}
