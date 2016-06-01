package com.siping.service.storage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.CommonRepository;
import com.siping.smartone.storage.request.AddStorageLocationRequest;
import com.siping.smartone.storage.request.DeleteStorageRequest;
import com.siping.smartone.storage.request.GetStorageLocationListRequest;
import com.siping.smartone.storage.response.StorageLocationResponese;
import com.siping.smartone.wms.response.StorageLocationResponse;

@Repository
public class StorageLocationRestRepository extends CommonRepository {

    public Boolean addStorageLocation(AddStorageLocationRequest request) {
        checkAdd(request);
        int add = -1;
        add = jdbcAccessContext.execute(
            "STORAGE.SQL_INSERT_STORAGE_LOCATION",
            new Object[] { request.getLocationNo(), request.getLocationName(), request.getLocationBarcode(), request.getDescription(), request.getStorageId(), request.getCreateBy(),
                    request.getStorageAreaId(), request.getPickOrder() });
        if (-1 == add) {
            return Boolean.FALSE;
        }
        addOperationLog("库位", "新增库位:[" + request.getLocationName() + "]", null, request.getUpdateBy() + "");
        return Boolean.TRUE;
    }

    public Boolean editStorageLocation(AddStorageLocationRequest request) {
        checkEdit(request);
        int edit = -1;
        edit = jdbcAccessContext.execute(
            "STORAGE.SQL_UPDATE_STORAGE_LOCATION",
            new Object[] { request.getLocationNo(), request.getLocationName(), request.getLocationBarcode(), request.getDescription(), request.getCreateBy(), request.getStorageAreaId(),
                    request.getPickOrder(), request.getId() });
        if (-1 == edit) {
            return Boolean.FALSE;
        }
        addOperationLog("库位", "更新库位:[" + request.getLocationName() + "]", null, request.getUpdateBy() + "");
        return Boolean.TRUE;
    }

    private void checkAdd(AddStorageLocationRequest request) {
        Integer number = jdbcAccessContext.findInteger("STORAGE.SQL_FIND_STORAGE_LOCATION_NUMBER_BY_LOCATIONNO", new Object[] { request.getLocationNo() });
        if (number == 1) {
            throw new BusinessProcessException("库位编号'" + request.getLocationNo() + "'和其他库位编号重复了");
        }
        if (number > 1) {
            throw new BusinessProcessException("库位编号'" + request.getLocationNo() + "'存在多个，请联系管理员修改");
        }
    }

    private void checkEdit(AddStorageLocationRequest request) {
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

    public StorageLocationResponese get(String id) {
        StorageLocationResponese storageLocation = jdbcAccessContext.findUniqueResult("STORAGE.SQL_GET_STORAGE_LOCATION_BY_LOCATIONID", new RowMapper<StorageLocationResponese>() {
            @Override
            public StorageLocationResponese mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildStorageLocationResponseForEdit(rs);
            }
        }, new Object[] { id });
        return storageLocation;
    }

    private Integer checkStorageLocationUsedInfo(String storageLocationId) {
        return 0;
    }

    public Boolean delete(DeleteStorageRequest request) {
        String updateBy = request.getUpdateBy();
        List<String> ids = request.getIds();
        List<Object[]> deleteParams = new LinkedList<Object[]>();
        StringBuilder locationNames = null;
        for (String id : ids) {
            if (checkStorageLocationUsedInfo(id) == 1) {
                throw new BusinessProcessException("您所删除的库位'" + get(id).getLocationName() + "'已经被使用，无法删除！");
            }
            if (null == locationNames) {
                locationNames = new StringBuilder(get(id).getLocationName());
            } else {
                locationNames.append("," + get(id).getLocationName());
            }
            Object[] obj = new Object[] { updateBy, id };
            deleteParams.add(obj);
        }
        int[] delete = jdbcAccessContext.batchExecute("STORAGE.SQL_DELETE_STORAGE_LOCATION", deleteParams);
        if (Arrays.asList(delete).contains(-1)) {
            throw new BusinessProcessException("批量删除某条信息时失败,已回滚");
        }
        addOperationLog("库位", "删除库位:[" + locationNames.toString() + "]", null, request.getUpdateBy() + "");
        return Boolean.TRUE;
    }

    public PagingResponse<StorageLocationResponese> getList(GetStorageLocationListRequest request) {
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        PagingResponse<StorageLocationResponese> response = new PagingResponse<StorageLocationResponese>();
        StringBuilder querySql = new StringBuilder(
            "SELECT s.id,s.location_no,s.location_name,s.location_barcode,s.description,s.storage_id,s.storage_area_id,A.area_name storageAreaName,A.area_no storageAreaNo,s.is_delete,s.create_date,s.create_by,s.update_date,s.update_by,s.pick_order,st.storage_name storageName,st.storage_no storageNo FROM storage_location s left join storage_area A on s.storage_area_id = A.id left join storage st on s.storage_id = st.id where s.is_delete = false and s.storage_id = ?");
        queryParam.add(request.getId());
        StringBuilder queryTotal = new StringBuilder("SELECT COUNT(0) from storage_location s left join storage_area A on s.storage_area_id = A.id left join storage st on s.storage_id = st.id where s.is_delete = false and s.storage_id = ?");
        if (StringUtils.hasText(request.getStorageAreaNo())) {// 条件查询
            querySql.append(" and A.area_no like CONCAT('%',?,'%') ");
            queryTotal.append(" and A.area_no like CONCAT('%',?,'%') ");
            queryParam.add(request.getStorageAreaNo());
        }
        if(StringUtils.hasText(request.getStorageLocationNo())){
            querySql.append(" and s.location_no like CONCAT('%',?,'%') ");
            queryTotal.append(" and s.location_no like CONCAT('%',?,'%') ");
            queryParam.add(request.getStorageLocationNo());
        }
        totalParam.addAll(queryParam);
        if (StringUtils.hasText(request.getPageNo().toString()) && StringUtils.hasText(request.getPageSize().toString())) {
            querySql.append(" limit ?,? ");// ORDER BY c.id
            queryParam.add((Integer.valueOf(request.getPageNo()) - 1) * Integer.valueOf(request.getPageSize()));
            queryParam.add(Integer.valueOf(request.getPageSize()));
        }
        List<StorageLocationResponese> list = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<StorageLocationResponese>() {
            @Override
            public StorageLocationResponese mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildStorageLocationResponseForList(rs);
            }
        }, queryParam.toArray());
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotal.toString(), totalParam.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    private StorageLocationResponese buildStorageLocationResponseForEdit(final ResultSet rs) throws SQLException {
        StorageLocationResponese storageLocation = new StorageLocationResponese();
        storageLocation.setId(rs.getString("id"));
        storageLocation.setLocationNo(rs.getString("location_no"));
        storageLocation.setLocationName(rs.getString("location_name"));
        storageLocation.setLocationBarcode(rs.getString("location_barcode"));
        storageLocation.setDescription(rs.getString("description"));
        storageLocation.setStorageId(rs.getInt("storage_id"));
        storageLocation.setPickOrder(rs.getInt("pick_order"));
        storageLocation.setCreateBy(rs.getInt("create_by"));
        storageLocation.setCreateDate(rs.getString("create_date"));
        storageLocation.setUpdateBy(rs.getInt("update_by"));
        storageLocation.setUpdateDate(rs.getString("update_date"));
        try {
            storageLocation.setStorageAreaId(rs.getInt("storage_area_id"));
        } catch (Exception e) {
        }
        try {
            storageLocation.setStorageName(rs.getString("storageName"));
        } catch (Exception e) {
        }
        try {
            storageLocation.setStorageNo(rs.getString("storageNo"));
        } catch (Exception e) {
        }
        return storageLocation;
    }

    private StorageLocationResponese buildStorageLocationResponseForList(final ResultSet rs) throws SQLException {
        StorageLocationResponese storageLocation = new StorageLocationResponese();
        storageLocation.setId(rs.getString("id"));
//        if (StringUtils.hasText(rs.getString("storageAreaNo"))) {
//            storageLocation.setLocationNo(rs.getString("storageAreaNo") + "-" + rs.getString("location_no"));
//            
//        } else {
//            storageLocation.setLocationNo(rs.getString("location_no"));
//        }
        storageLocation.setLocationNo(rs.getString("location_no"));
        storageLocation.setLocationName(rs.getString("location_name"));
        storageLocation.setLocationBarcode(rs.getString("location_barcode"));
        storageLocation.setDescription(rs.getString("description"));
        storageLocation.setStorageId(rs.getInt("storage_id"));
        storageLocation.setStorageAreaId(rs.getInt("storage_area_id"));
        storageLocation.setStorageAreaName(rs.getString("storageAreaName"));
        storageLocation.setStorageAreaNo(rs.getString("storageAreaNo"));
        storageLocation.setCreateBy(rs.getInt("create_by"));
        storageLocation.setCreateDate(rs.getString("create_date"));
        storageLocation.setUpdateBy(rs.getInt("update_by"));
        storageLocation.setUpdateDate(rs.getString("update_date"));
        storageLocation.setPickOrder(rs.getInt("pick_order"));
        try {
            storageLocation.setStorageName(rs.getString("storageName"));
        } catch (Exception e) {
        }
        try {
            storageLocation.setStorageNo(rs.getString("storageNo"));
        } catch (Exception e) {
        }
        return storageLocation;
    }

    public StorageLocationResponese getByLocationNo(String locationNo) {
        StorageLocationResponese storageLocation = jdbcAccessContext.findUniqueResult("STORAGE.SQL_GET_STORAGE_LOCATION_BY_LOCATIONNO", new RowMapper<StorageLocationResponese>() {
            @Override
            public StorageLocationResponese mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildStorageLocationResponseForList(rs);
            }
        }, new Object[] { locationNo });
        return storageLocation;
    }

    public List<StorageLocationResponse> getLocationListByStorageId(String storageId) {
        List<StorageLocationResponse> response = jdbcAccessContext.find("STORAGE.SQL_GET_STORAGE_LOCATION_ID_BY_STORAGE_ID", new RowMapper<StorageLocationResponse>() {
            @Override
            public StorageLocationResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildStorageLocationResponseOnlyId(rs);
            }
        }, new Object[] { storageId });
        return response;
    }

    private StorageLocationResponse buildStorageLocationResponseOnlyId(final ResultSet rs) throws SQLException {
        StorageLocationResponse locationResponse = new StorageLocationResponse();
        locationResponse.setStorageLocationId(rs.getInt("storageLocationId"));
        return locationResponse;
    }
}
