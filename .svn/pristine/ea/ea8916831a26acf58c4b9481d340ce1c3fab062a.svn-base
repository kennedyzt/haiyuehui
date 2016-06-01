package com.siping.service.storage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.CommonRepository;
import com.siping.smartone.storage.request.DeleteStorageRequest;
import com.siping.smartone.storage.request.GetStorageListRequest;
import com.siping.smartone.storage.request.StorageRequest;
import com.siping.smartone.storage.response.StorageResponse;

@Repository
public class StorageRestRepository extends CommonRepository {
    @Autowired
    private JDBCAccessContext jdbcAccessContext;

    public Boolean addStorage(StorageRequest request) {
        checkAdd(request);
        int add = -1;
        add = jdbcAccessContext.execute("STORAGE.SQL_INSERT_STORAGE", new Object[] { request.getStorageNo(), request.getStorageName(), request.getIsEnableLocation(), request.getDescription(), false,
                request.getCreateBy() });
        if (-1 == add) {
            return Boolean.FALSE;
        }
        addOperationLog("仓库", "新增仓库:[" + request.getStorageName() + "]", null, request.getUpdateBy() + "");
        return Boolean.TRUE;
    }

    private void checkAdd(StorageRequest request) {
        List<StorageResponse> storageResponses = null;
        storageResponses = jdbcAccessContext.find("STORAGE.SQL_GET_STORAGE_BY_STORAGENAME", new RowMapper<StorageResponse>() {
            @Override
            public StorageResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return StorageRestRepositoryHelper.buildStorageResponse(rs);
            }
        }, new Object[] { request.getStorageName() });
        if (!CollectionUtils.isEmpty(storageResponses)) {
            throw new BusinessProcessException("仓库名称不能与其他仓库名称重复");
        }
        storageResponses = jdbcAccessContext.find("STORAGE.SQL_GET_STORAGE_BY_STORAGENO", new RowMapper<StorageResponse>() {
            @Override
            public StorageResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return StorageRestRepositoryHelper.buildStorageResponse(rs);
            }
        }, new Object[] { request.getStorageNo() });
        if (!CollectionUtils.isEmpty(storageResponses)) {
            throw new BusinessProcessException("仓库编码不能与其他仓库编码重复");
        }
    }

    private Integer checkStorageUsedInfo(StorageRequest request) {
        // Integer storageFlag =
        // jdbcAccessContext.findInteger("STORAGE.SQL_GET_STORAGEFLAG_BY_ID",
        // new Object[] { request.getId() });
        // return storageFlag;
        return 0;
    }

    private Integer checkStorageUsedInfo(String id) {
        Integer storageFlag = jdbcAccessContext.findInteger("STORAGE.SQL_GET_STORAGEFLAG_BY_ID", new Object[] { id });
        return storageFlag;
    }

    private Integer checkStorageUsedInfo(Integer id) {
        Integer storageFlag = jdbcAccessContext.findInteger("STORAGE.SQL_GET_STORAGEFLAG_BY_ID", new Object[] { id });
        return storageFlag;
    }

    private void checkEdit(StorageRequest request) {
        List<StorageResponse> storageResponses = null;
        storageResponses = jdbcAccessContext.find("STORAGE.SQL_GET_STORAGE_BY_STORAGENAME", new RowMapper<StorageResponse>() {
            @Override
            public StorageResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return StorageRestRepositoryHelper.buildStorageResponse(rs);
            }
        }, new Object[] { request.getStorageName() });
        if (!CollectionUtils.isEmpty(storageResponses)) {
            throw new BusinessProcessException("仓库名称不能与其他仓库名称重复");
        }
    }

    public Boolean edit(StorageRequest request) {
        StorageResponse storageResponse = get(request.getId().toString());
        if (null == storageResponse) {
            throw new BusinessProcessException("仓库不存在，无法编辑");
        }
        if (!(request.getStorageName().equals(storageResponse.getStorageName()))) {// 未修改仓库名，不用判断是否有重复
            checkEdit(request);
        }
        if (checkStorageUsedInfo(request) == 1) {
            throw new BusinessProcessException("仓库已经被使用，无法编辑");
        }
        List<Object> updateParam = new ArrayList<Object>();
        int edit = jdbcAccessContext.executeWithoutSqlManager(StorageRestRepositoryHelper.loadDynamicUpdateStorageSql(request, updateParam), updateParam.toArray());
        if (-1 == edit) {
            return Boolean.FALSE;
        }
        addOperationLog("仓库", "更新仓库:[" + storageResponse.getStorageName() + "]", null, request.getUpdateBy() + "");
        return Boolean.TRUE;
    }

    public StorageResponse get(String id) {
        StorageResponse storage = jdbcAccessContext.findUniqueResult("STORAGE.SQL_GET_STORAGE_BY_ID", new RowMapper<StorageResponse>() {
            @Override
            public StorageResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildStorageResponse(rs);
            }
        }, new Object[] { id });
        return storage;
    }

    private static StorageResponse buildStorageResponse(final ResultSet rs) throws SQLException {
        StorageResponse response = new StorageResponse();
        response.setId(rs.getInt("id"));
        response.setStorageNo(rs.getString("storage_no"));
        response.setStorageName(rs.getString("storage_name"));
        response.setDescription(rs.getString("description"));
        response.setIsDelete(rs.getBoolean("is_delete"));
        response.setCreateDate(DateUtils.date(rs.getDate("create_date"), rs.getTime("create_date")));
        response.setCreateBy(rs.getInt("create_by"));
        response.setUpdateDate(DateUtils.date(rs.getDate("update_date"), rs.getTime("update_date")));
        response.setUpdateBy(rs.getInt("update_by"));
        try {
            response.setIsEnableLocation(rs.getBoolean("is_enable_location"));
        } catch (Exception e) {
        }
        return response;

    }

    public Boolean delete(DeleteStorageRequest request) {
        String updateBy = request.getUpdateBy();
        List<String> ids = request.getIds();
        List<Object[]> deleteParams = new LinkedList<Object[]>();
        StringBuilder storageNames = null;
        for (String id : ids) {
            if (checkStorageUsedInfo(id) == 1) {
                throw new BusinessProcessException("您所删除的仓库'" + get(id).getStorageName() + "'已经被使用，无法删除！");
            }
            if (null == storageNames)
                storageNames = new StringBuilder(get(id).getStorageName());
            else
                storageNames.append("," + get(id).getStorageName());
            Object[] obj = new Object[] { updateBy, id };
            deleteParams.add(obj);
        }
        int[] delete = jdbcAccessContext.batchExecute("STORAGE.SQL_DELETE_STORAGE", deleteParams);
        if (Arrays.asList(delete).contains(-1)) {
            throw new BusinessProcessException("批量删除某条信息时失败,已回滚");
        }
        addOperationLog("仓库", "删除仓库:[" + storageNames.toString() + "]", null, request.getUpdateBy() + "");
        return Boolean.TRUE;
    }

    public List<StorageResponse> getStorageList(StorageRequest request) {
        List<Object> queryParam = new ArrayList<Object>();
        List<StorageResponse> list = jdbcAccessContext.findWithoutSqlManager(StorageRestRepositoryHelper.loadDynamicGetStorageSql(request.getStorageName(), request.getStorageNo(), queryParam),
            new RowMapper<StorageResponse>() {
                @Override
                public StorageResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                    return StorageRestRepositoryHelper.buildStorageResponse(rs);
                }
            }, queryParam.toArray());
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        throw new BusinessProcessException("无可用仓库信息！");
    }

    public List<StorageResponse> getList() {
        List<StorageResponse> StorageResponses = jdbcAccessContext.find("STORAGE.SQL_GET_ALL_STORAGE", new RowMapper<StorageResponse>() {
            @Override
            public StorageResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return StorageRestRepositoryHelper.buildStorageResponse(rs);
            }
        }, new Object[] {});
        if (CollectionUtils.isEmpty(StorageResponses)) {
            throw new BusinessProcessException("没有关于仓库的数据");
        }
        return StorageResponses;
    }

    public PagingResponse<StorageResponse> getList(GetStorageListRequest request) {
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        PagingResponse<StorageResponse> response = new PagingResponse<StorageResponse>();
        StringBuilder querySql = new StringBuilder(
            "SELECT id,storage_no,storage_name,description,is_delete,create_date,create_by,update_date,update_by,is_enable_location FROM storage where is_delete = false");
        StringBuilder queryTotal = new StringBuilder("SELECT COUNT(0) from storage c where is_delete = false");
        if (StringUtils.hasText(request.getKey())) {// 条件查询
            querySql.append(" and storage_no like CONCAT('%',?,'%') or storage_name like CONCAT('%',?,'%') or description like CONCAT('%',?,'%')");
            queryTotal.append(" and storage_no like CONCAT('%',?,'%') or storage_name like CONCAT('%',?,'%') or description like CONCAT('%',?,'%')");
            queryParam.add(request.getKey());
            queryParam.add(request.getKey());
            queryParam.add(request.getKey());
        }
        totalParam.addAll(queryParam);
        if (StringUtils.hasText(request.getPageNo()) && StringUtils.hasText(request.getPageSize())) {
            querySql.append(" limit ?,? ");// ORDER BY c.id
            queryParam.add((Integer.valueOf(request.getPageNo()) - 1) * Integer.valueOf(request.getPageSize()));
            queryParam.add(Integer.valueOf(request.getPageSize()));
        }
        List<StorageResponse> list = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<StorageResponse>() {
            @Override
            public StorageResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return StorageRestRepositoryHelper.buildStorageResponse(rs);
            }
        }, queryParam.toArray());
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotal.toString(), totalParam.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    public List<StorageResponse> getList(String key) {
        List<StorageResponse> response = jdbcAccessContext.find("STORAGE.SQL_GET_STORAGE_BYKEY", new RowMapper<StorageResponse>() {
            @Override
            public StorageResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return StorageRestRepositoryHelper.buildStorageResponse(rs);
            }
        }, new Object[] { key, key });
        if (CollectionUtils.isEmpty(response)) {
            throw new BusinessProcessException("没有关于仓库的数据");
        }
        return response;
    }

    public List<StorageResponse> getList(Boolean isEnableLocation) {
        List<StorageResponse> response = jdbcAccessContext.find("STORAGE.SQL_GET_STORAGE_BY_ISLOCATION", new RowMapper<StorageResponse>() {
            @Override
            public StorageResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return StorageRestRepositoryHelper.buildStorageResponse(rs);
            }
        }, new Object[] { isEnableLocation });
        if (CollectionUtils.isEmpty(response)) {
            throw new BusinessProcessException("没有关于仓库的数据");
        }
        return response;
    }
}
