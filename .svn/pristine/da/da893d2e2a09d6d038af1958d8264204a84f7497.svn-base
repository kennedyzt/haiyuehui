package com.siping.intranet.crm.storage.repository;

import java.util.List;

import javax.validation.Valid;

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
import com.siping.smartone.inventory.request.GetInventoryCheckListRequest;
import com.siping.smartone.storage.request.DeleteStorageRequest;
import com.siping.smartone.storage.request.GetStorageListRequest;
import com.siping.smartone.storage.request.StorageRequest;
import com.siping.smartone.storage.response.StorageResponse;

@Repository
public class StorageRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(StorageRequest request) {
        validStorageRequest(request);
        String requestContent = JSONBinder.binder(StorageRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storage/add").body(requestContent));
    }

    public Boolean edit(StorageRequest request) {
        validStorageRequest(request);
        String requestContent = JSONBinder.binder(StorageRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storage/edit").body(requestContent));
    }

    @Valid
    private void validStorageRequest(StorageRequest request) {
        if(request.getId() != null){
            if (request.getId() == 0) {
                throw new BusinessProcessException("虚拟仓库不能编辑");
            }
        }
        if (!StringUtils.hasText(request.getStorageNo())) {
            throw new BusinessProcessException("仓库编码不能为空");
        }
        if (!StringUtils.hasText(request.getStorageName())) {
            throw new BusinessProcessException("仓库名称不能为空");
        }
    }

    public StorageResponse get(String id) {
        if (null == id) {
            throw new BusinessProcessException("仓库id不指定，无法获取数据");
        };
        return stromaWebServiceApi.get(EndPointBuilder.create(StorageResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storage/get/%s").arguments(id));
    }

    public Boolean delete(List<String> ids, String updateBy) {
        if (null == ids) {
            throw new BusinessProcessException("指定仓库不存在，无法删除");
        }
        if (null == updateBy) {
            throw new BusinessProcessException("当前登录出现问题，无法修改");
        }
        DeleteStorageRequest deleteStorageRequest = new DeleteStorageRequest();
        deleteStorageRequest.setIds(ids);
        deleteStorageRequest.setUpdateBy(updateBy);
        String requestContent = JSONBinder.binder(DeleteStorageRequest.class).toJSON(deleteStorageRequest);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storage/delete").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<StorageResponse> getList() {
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(StorageResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storage/getlist"));
    }

    @SuppressWarnings("unchecked")
    public List<StorageResponse> getList(String key) {
        if (!StringUtils.hasText(key)) {
            throw new BusinessProcessException("条件为空不能查询");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(StorageResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storage/getlist/%s").arguments(key));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<StorageResponse> getList(Integer pageNo, Integer pageSize, GetStorageListRequest request) {
        request.setPageNo(pageNo.toString());
        request.setPageSize(pageSize.toString());
        String requestContent = JSONBinder.binder(GetInventoryCheckListRequest.class).toJSON(request);
        return stromaWebServiceApi
            .post(EndPointBuilder.create(PagingResponse.class).elementTypes(StorageResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storage/getlist").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<StorageResponse> getList(Boolean isEnableLocation) {
        if (null == isEnableLocation) {
            throw new BusinessProcessException("条件为空不能查询");
        }
        return stromaWebServiceApi.get(
            EndPointBuilder.create(List.class).elementTypes(StorageResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storage/getlistbyislocation/%s").arguments(isEnableLocation));
    }

}
