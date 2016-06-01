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
import com.siping.smartone.storage.request.AddStorageAreaRequest;
import com.siping.smartone.storage.request.AddStorageLocationRequest;
import com.siping.smartone.storage.request.DeleteStorageRequest;
import com.siping.smartone.storage.request.GetStorageListRequest;
import com.siping.smartone.storage.response.StorageAreaResponese;
import com.siping.smartone.storage.response.StorageResponse;
import com.siping.smartone.storage.response.StorageLocationResponese;

@Repository
public class StorageAreaRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Value("${site.api.appkeys}")
    private String appKey;

    @Valid
    private void  validStorageAreaRequest(AddStorageAreaRequest request) {
        if(!StringUtils.hasText(request.getAreaNo())){
            throw new BusinessProcessException("库区编码不能为空");
        }
        if(!StringUtils.hasText(request.getAreaName())){
            throw new BusinessProcessException("库区名称不能为空");
        }
    }
    
    public Boolean add(AddStorageAreaRequest request) {
        validStorageAreaRequest(request);
        String requestContent = JSONBinder.binder(AddStorageAreaRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storagearea/add").body(requestContent));
    }
    
    @SuppressWarnings("unchecked")
    public List<StorageAreaResponese> getList(String storageId) {
        if(!StringUtils.hasText(storageId)){
            throw new BusinessProcessException("请选择需要查看库区的仓库");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(StorageAreaResponese.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storagearea/getlist/%s").arguments(storageId));
    }
    
    public StorageAreaResponese get(String id){
        if (null == id) {
            throw new BusinessProcessException("请指定需要修改的库位的ID号");
        };
        return stromaWebServiceApi.get(EndPointBuilder.create(StorageAreaResponese.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storagearea/get/%s").arguments(id));
    }
    
    public Boolean edit(AddStorageAreaRequest request) {
        validStorageAreaRequest(request);
        String requestContent = JSONBinder.binder(AddStorageAreaRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storagearea/edit").body(requestContent));
    }
    
    public Boolean delete(List<String> ids, String updateBy) {
        if (null == ids) {
            throw new BusinessProcessException("指定库区不存在，无法删除");
        }
        if (null == updateBy) {
            throw new BusinessProcessException("当前登录出现问题，无法修改");
        }
        DeleteStorageRequest deleteStorageRequest = new DeleteStorageRequest();
        deleteStorageRequest.setIds(ids);
        deleteStorageRequest.setUpdateBy(updateBy);
        String requestContent = JSONBinder.binder(DeleteStorageRequest.class).toJSON(deleteStorageRequest);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storagearea/delete").body(requestContent));
    }
}
