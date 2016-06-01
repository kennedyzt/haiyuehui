package com.siping.service.storage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.smartone.storage.request.StorageRequest;
import com.siping.smartone.storage.response.StorageResponse;

public class StorageRestRepositoryHelper {

    public static StorageResponse buildStorageResponse(final ResultSet rs) throws SQLException {
        StorageResponse storage = new StorageResponse();
        storage.setId(rs.getInt("id"));
        storage.setStorageName(rs.getString("storage_name"));
        storage.setStorageNo(rs.getString("storage_no"));
        storage.setDescription(rs.getString("description"));
        storage.setIsDelete(rs.getBoolean("is_delete"));
        storage.setCreateDate(DateUtils.date(rs.getDate("create_date"), rs.getTime("create_date")));
        storage.setCreateBy(rs.getInt("create_by"));
        storage.setUpdateDate(DateUtils.date(rs.getDate("update_date"), rs.getTime("update_date")));
        storage.setUpdateBy(rs.getInt("update_by"));
        try {
            storage.setIsEnableLocation(rs.getBoolean("is_enable_location"));//由于某些SQL没有将这个字段查询出来，所以加上try catch
        } catch (Exception e) {
        }
        return storage;
    }
    
    public static String loadDynamicUpdateStorageSql(StorageRequest request, List<Object> updateParam) {
        StringBuilder sql = new StringBuilder("UPDATE storage SET ");
        int count = 0;
        count = assembleParams(request, updateParam, sql, count);
        sql.append("where id=?");
        updateParam.add(request.getId());
        return sql.toString();
    }

    private static int assembleParams(StorageRequest request, List<Object> updateParam, StringBuilder sql, int count) {
        if (request.getIsEnableLocation()!=null) {
            if (count != 0)
                sql.append(",is_enable_location=?");
            else
                sql.append("is_enable_location=?");
            updateParam.add(request.getIsEnableLocation());
            count++;
        }
        if (StringUtils.hasText(request.getStorageNo())) {
            if (count != 0)
                sql.append(",storage_no=?");
            else
                sql.append("storage_no=?");
            updateParam.add(request.getStorageNo());
            count++;
        }
        if (StringUtils.hasText(request.getStorageName())) {
            if (count != 0)
                sql.append(",storage_name=?");
            else
                sql.append("storage_name=?");
            updateParam.add(request.getStorageName());
            count++;
        }
        if (StringUtils.hasText(request.getDescription())) {
            if (count != 0)
                sql.append(",description=?");
            else
                sql.append("description=?");
            updateParam.add(request.getDescription());
            count++;
        }
//        if (null != request.getIsDelete() && request.getIsDelete()) {
//            if (count != 0)
//                sql.append(",is_delete=? ");
//            else
//                sql.append("is_delete=? ");
//            updateParam.add(request.getIsDelete());
//            count++;
//        }
        if(count==0){
            throw new BusinessProcessException("您没有选择修改的项");
        }
        return count;
    }

    public static String loadDynamicGetStorageSql(String storageName, String storageNo, List<Object> queryParam) {
        StringBuilder sql = new StringBuilder(
            "SELECT id,storage_no,storage_name,description,is_delete,create_date,create_by,update_date,update_by FROM storage  where is_delete=FALSE");
        if (StringUtils.hasText(storageName)){
            sql.append(" and storage_name=? ");
            queryParam.add(storageName);
        }
        if (StringUtils.hasText(storageNo)) {
            sql.append(" and storage_no=?");
            queryParam.add(storageNo);
        }
        return sql.toString();
    }

}
