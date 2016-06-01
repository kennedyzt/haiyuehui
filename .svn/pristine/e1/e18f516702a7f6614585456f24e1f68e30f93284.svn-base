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
import com.siping.smartone.storage.request.AddStorageLocationRequest;
import com.siping.smartone.storage.request.DeleteStorageRequest;
import com.siping.smartone.storage.request.GetStorageListRequest;
import com.siping.smartone.storage.request.GetStorageLocationListRequest;
import com.siping.smartone.storage.response.StorageResponse;
import com.siping.smartone.storage.response.StorageLocationResponese;

@Repository
public class StorageLocationRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(AddStorageLocationRequest request) {
        if(request.getPickOrder()==null){
            request.setPickOrder(0);
        }
        validStorageLocationRequest(request);
        String requestContent = JSONBinder.binder(AddStorageLocationRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storagelocation/add").body(requestContent));
    }

    @Valid
    private void  validStorageLocationRequest(AddStorageLocationRequest request) {
        if(!StringUtils.hasText(request.getLocationNo())){
            throw new BusinessProcessException("库位编码不能为空");
        }
        if(!StringUtils.hasText(request.getLocationName())){
            throw new BusinessProcessException("库位名称不能为空");
        }
//        if(null==request.getStorageAreaId()){
//            throw new BusinessProcessException("所属库区必须选择");
//        }库区不用必须选择
        if(null==request.getStorageId()){
            throw new BusinessProcessException("所属仓库必须选择");
        }
    }
    

    public StorageLocationResponese get(String id) {
        if (null == id) {
            throw new BusinessProcessException("请指定需要修改的库位的ID号");
        };
        return stromaWebServiceApi.get(EndPointBuilder.create(StorageLocationResponese.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storagelocation/get/%s").arguments(id));
    }

    public Boolean delete(List<String> ids, String updateBy) {
        if (null == ids) {
            throw new BusinessProcessException("指定库位不存在，无法删除");
        }
        if (null == updateBy) {
            throw new BusinessProcessException("当前登录出现问题，无法修改");
        }
        DeleteStorageRequest deleteStorageRequest = new DeleteStorageRequest();
        deleteStorageRequest.setIds(ids);
        deleteStorageRequest.setUpdateBy(updateBy);
        String requestContent = JSONBinder.binder(DeleteStorageRequest.class).toJSON(deleteStorageRequest);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storagelocation/delete").body(requestContent));
    }
    
    @SuppressWarnings("unchecked")
    public PagingResponse<StorageLocationResponese> getList(Integer pageNo, Integer pageSize, String storageId, GetStorageLocationListRequest request) {
        if(!StringUtils.hasText(storageId)){//分页后这里需要判断两次
            if(!StringUtils.hasText(request.getId())){
                throw new BusinessProcessException("请选择需要查看库位的仓库");
            }
        }
        pageNo = pageNo==null?1:pageNo;
        pageSize = pageSize==null?10:pageSize;
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        if(storageId!=null){
            request.setId(storageId);
        }
        String requestContent = JSONBinder.binder(GetStorageLocationListRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(StorageLocationResponese.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storagelocation/getlist").body(requestContent));
    }

    public Boolean edit(AddStorageLocationRequest request) {
        validStorageLocationRequest(request);
        String requestContent = JSONBinder.binder(AddStorageLocationRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/storagelocation/edit").body(requestContent));
    }

    
}
