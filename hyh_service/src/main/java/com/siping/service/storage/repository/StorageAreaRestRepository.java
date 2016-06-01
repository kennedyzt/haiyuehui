package com.siping.service.storage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;

import com.siping.service.common.CommonRepository;
import com.siping.smartone.storage.request.AddStorageAreaRequest;
import com.siping.smartone.storage.request.DeleteStorageRequest;
import com.siping.smartone.storage.response.StorageAreaResponese;
import com.siping.smartone.wms.response.StorageLocationResponse;

@Repository
public class StorageAreaRestRepository extends CommonRepository {

    public Boolean addStorageArea(AddStorageAreaRequest request) {
        checkAdd(request);
        int add = -1;
        add = jdbcAccessContext.execute("STORAGE.SQL_INSERT_STORAGE_AREA",
            new Object[] { request.getStorageId(), request.getAreaNo(), request.getAreaName(), request.getRemark(), request.getCreateBy() });
        if (-1 == add) {
            return Boolean.FALSE;
        }
        addOperationLog("库区", "新增库区:[" + request.getAreaName() + "]", null, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    public Boolean editStorageArea(AddStorageAreaRequest request) {
        checkEdit(request);
        int edit = -1;
        edit = jdbcAccessContext.execute("STORAGE.SQL_UPDATE_STORAGE_AREA", new Object[] { request.getAreaNo(), request.getAreaName(), request.getRemark(), request.getUpdateBy(), request.getId() });
        if (-1 == edit) {
            return Boolean.FALSE;
        }
        addOperationLog("库区", "更新库区:[" + request.getAreaName() + "]", null, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    private void checkAdd(AddStorageAreaRequest request) {
        Integer number = jdbcAccessContext.findInteger("STORAGE.SQL_FIND_STORAGE_AREA_NUMBER_BY_AREANO", new Object[] { request.getAreaNo() });
        if (number == 1) {
            throw new BusinessProcessException("库区编号'" + request.getAreaNo() + "'和其他库区编号重复了");
        } else if (number > 1) {
            throw new BusinessProcessException("库区编号'" + request.getAreaNo() + "'存在多个，请联系管理员修改");
        }
    }

    private void checkEdit(AddStorageAreaRequest request) {
    }

    // public Boolean edit(StorageRequest request) {
    // StorageResponse storageResponse = get(request.getId().toString());
    // if (null == storageResponse) {
    // throw new BusinessProcessException("仓库不存在，无法编辑");
    // }
    // if(!(request.getStorageName().equals(storageResponse.getStorageName()))){//未修改仓库名，不用判断是否有重复
    // checkEdit(request);
    // }
    // if(checkStorageUsedInfo(request)==1){
    // throw new BusinessProcessException("仓库已经被使用，无法编辑");
    // }
    // List<Object> updateParam = new ArrayList<Object>();
    // int edit =
    // jdbcAccessContext.executeWithoutSqlManager(StorageRestRepositoryHelper.loadDynamicUpdateStorageSql(request,
    // updateParam), updateParam.toArray());
    // if (-1 == edit) {
    // return Boolean.FALSE;
    // }
    // return Boolean.TRUE;
    // }

    public StorageAreaResponese get(String id) {
        StorageAreaResponese storageArea = jdbcAccessContext.findUniqueResult("STORAGE.SQL_GET_STORAGE_AREA_BY_AREAID", new RowMapper<StorageAreaResponese>() {
            @Override
            public StorageAreaResponese mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildStorageAreaResponse(rs);
            }
        }, new Object[] { id });
        return storageArea;
    }

    private Integer checkStorageAreaUsedInfo(String storageAreaId) {
        return jdbcAccessContext.findInteger("STORAGE.SQL_GET_STORAGE_AREA_USED_INFO", new Object[] { storageAreaId });
    }

    public Boolean delete(DeleteStorageRequest request) {
        String updateBy = request.getUpdateBy();
        List<String> ids = request.getIds();
        List<Object[]> deleteParams = new LinkedList<Object[]>();
        StringBuilder areaNames = null;
        for (String id : ids) {
            if (checkStorageAreaUsedInfo(id) == 1) {
                throw new BusinessProcessException("您所删除的库区'" + get(id).getAreaName() + "'已经被使用，无法删除！");
            }
            if (null == areaNames) {
                areaNames = new StringBuilder(get(id).getAreaName());
            } else {
                areaNames.append("," + get(id).getAreaName());
            }
            Object[] obj = new Object[] { updateBy, id };
            deleteParams.add(obj);
        }
        int[] delete = jdbcAccessContext.batchExecute("STORAGE.SQL_DELETE_STORAGE_AREA", deleteParams);
        if (Arrays.asList(delete).contains(-1)) {
            throw new BusinessProcessException("批量删除某条信息时失败,已回滚");
        }
        addOperationLog("库区", "删除库区:[" + areaNames.toString() + "]", null, request.getUpdateBy() + "");
        return Boolean.TRUE;
    }

    public List<StorageAreaResponese> getList(String key) {
        List<StorageAreaResponese> response = jdbcAccessContext.find("STORAGE.SQL_GET_STORAGE_AREA_BY_STORAGEID", new RowMapper<StorageAreaResponese>() {
            @Override
            public StorageAreaResponese mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildStorageAreaResponse(rs);
            }
        }, new Object[] { key });
        if (CollectionUtils.isEmpty(response)) {
            // throw new BusinessProcessException("没有关于仓库的数据");
        }
        return response;
    }

    private StorageAreaResponese buildStorageAreaResponse(final ResultSet rs) throws SQLException {
        StorageAreaResponese storageArea = new StorageAreaResponese();
        storageArea.setId(rs.getString("id"));
        storageArea.setAreaName(rs.getString("area_name"));
        storageArea.setAreaNo(rs.getString("area_no"));
        storageArea.setStorageId(rs.getString("storage_id"));
        storageArea.setRemark(rs.getString("remark"));
        storageArea.setCreateBy(rs.getInt("create_by"));
        storageArea.setCreateDate(rs.getString("create_date"));
        storageArea.setUpdateBy(rs.getInt("update_by"));
        storageArea.setUpdateDate(rs.getString("update_date"));
        return storageArea;
    }

    public List<StorageLocationResponse> getLocationListByAreaId(String storageAreaId) {
        List<StorageLocationResponse> response = jdbcAccessContext.find("STORAGE.SQL_GET_STORAGE_LOCATION_ID_BY_STORAGE_AREA_ID", new RowMapper<StorageLocationResponse>() {
            @Override
            public StorageLocationResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildStorageLocationResponse(rs);
            }
        }, new Object[] { storageAreaId });
        return response;
    }

    private StorageLocationResponse buildStorageLocationResponse(final ResultSet rs) throws SQLException {
        StorageLocationResponse response = new StorageLocationResponse();
        response.setStorageLocationId(rs.getInt("storageLocationId"));
        return response;
    }
}
