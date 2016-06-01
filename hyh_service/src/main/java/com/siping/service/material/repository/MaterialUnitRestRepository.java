package com.siping.service.material.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.smartone.material.request.AddMaterialUnitRequest;
import com.siping.smartone.material.request.DeleteMaterialUnitRequest;
import com.siping.smartone.material.request.GetMaterialUnitListRequest;
import com.siping.smartone.material.response.GetMaterialUnitResponse;

@Repository
public class MaterialUnitRestRepository extends BillsRepository {

    public Boolean add(AddMaterialUnitRequest request) {
        if (null != get(null, request.getUnitName())) {
            throw new BusinessProcessException("你的物料单位‘" + request.getUnitName() + "’已经存在,不能重复添加");
        }
        Object[] addParams = new Object[] { request.getUnitName(), request.getDescription(), false, new Date(), request.getCreateBy(),request.getUnitNo()};
        int add = jdbcAccessContext.execute("MATERIAL.SQL_ADD_MATERIAL_UNIT", addParams);
        if (-1 == add) {
            return Boolean.FALSE;
        }
        addOperationLog("计量单位", "新增计量单位:[" + request.getUnitName() + "]", null, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    public Boolean edit(AddMaterialUnitRequest request) {
        List<Object> paramList = new ArrayList<Object>();
        if (null == get(request.getId(), null)) {
            throw new BusinessProcessException("商品计量单位不存在不能编辑");
        }
        if (checkMaterialUnitUsedInfo(request) == 1) {
            throw new BusinessProcessException("商品计量单位已经被引用，无法编辑");
        }
        int edit = jdbcAccessContext.executeWithoutSqlManager(buildEditSql(request, paramList), paramList.toArray());
        if (-1 == edit) {
            return Boolean.FALSE;
        }
        addOperationLog("计量单位", "更新计量单位:[" + request.getUnitName() + "]", null, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    private Integer checkMaterialUnitUsedInfo(AddMaterialUnitRequest request) {
        Integer materialUnitFlag = jdbcAccessContext.findInteger("MATERIAL.SQL_GET_MATERIALUNITFLAG_BY_ID", new Object[] { request.getId() });
        return materialUnitFlag;
    }

    private Integer checkMaterialUnitUsedInfo(String id) {
        Integer materialUnitFlag = jdbcAccessContext.findInteger("MATERIAL.SQL_GET_MATERIALUNITFLAG_BY_ID", new Object[] { id });
        return materialUnitFlag;
    }

    private Integer checkMaterialUnitUsedInfo(Integer id) {
        Integer materialUnitFlag = jdbcAccessContext.findInteger("MATERIAL.SQL_GET_MATERIALUNITFLAG_BY_ID", new Object[] { id });
        return materialUnitFlag;
    }

    private static String buildEditSql(AddMaterialUnitRequest request, List<Object> list) {
        StringBuilder sql = new StringBuilder("UPDATE material_unit SET ");
        if (StringUtils.hasText(request.getUnitName())) {
            sql.append("unit_name=? ");
            list.add(request.getUnitName());
        }
        if(StringUtils.hasText(request.getUnitNo())){
            if(list.size() > 0){
                sql.append(",unit_no=? ");
            }
            else{
                sql.append("unit_no=? ");
            }
            list.add(request.getUnitNo());
        }
        if (StringUtils.hasText(request.getDescription())) {
            if (list.size() > 0)
                sql.append(",description=? ");
            else
                sql.append("description=? ");
            list.add(request.getDescription());
        }
        if (null != request.getIsDelete() && request.getIsDelete()) {
            if (list.size() > 0)
                sql.append(",is_delete=? ");
            else
                sql.append("is_delete=? ");
            list.add(request.getIsDelete());
        }
        if (null != request.getUpdateBy() && request.getUpdateBy() != 0) {
            if (list.size() > 0)
                sql.append(",update_by=?,update_date=? ");
            else
                sql.append("update_by=?,update_date=? ");
            list.add(request.getUpdateBy());
            list.add(new Date());
        }
        sql.append("where id=? ");
        list.add(Integer.valueOf(request.getId()));
        return sql.toString();
    }

    public GetMaterialUnitResponse get(String id, String unitName) {
        List<Object> queryParam = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder("SELECT M.id,M.unit_no,M.unit_name,M.description,M.is_delete,M.create_date,M.create_by,M.update_date,M.update_By from material_unit M where M.is_delete=FALSE ");
        if (StringUtils.hasText(id)) {
            sql.append("and M.id=? ");
            queryParam.add(id);
        }
        if (StringUtils.hasText(unitName)) {
            sql.append("and M.unit_name=? ");
            queryParam.add(unitName);
        }
        List<GetMaterialUnitResponse> list = jdbcAccessContext.findWithoutSqlManager(sql.toString(), new RowMapper<GetMaterialUnitResponse>() {
            @Override
            public GetMaterialUnitResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialUnitResponse(rs);
            }
        }, queryParam.toArray());
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    private static GetMaterialUnitResponse buildMaterialUnitResponse(final ResultSet rs) throws SQLException {
        GetMaterialUnitResponse unit = new GetMaterialUnitResponse();
        unit.setUnitName(rs.getString("unit_name"));
        unit.setId(rs.getString("id"));
        unit.setDescription(rs.getString("description"));
        unit.setUnitNo(rs.getString("unit_no"));
        unit.setIsDelete(rs.getBoolean("is_delete"));
        unit.setCreateDate(rs.getString("create_date"));
        unit.setCreateBy(rs.getInt("create_by"));
        unit.setUpdateDate(rs.getString("update_date"));
        unit.setUpdateBy(rs.getInt("update_by"));
        return unit;
    }

    public List<GetMaterialUnitResponse> getList() {
        return jdbcAccessContext.find("MATERIAL.SQL_GET_ALL_MATERIAL_UNIT", new RowMapper<GetMaterialUnitResponse>() {
            @Override
            public GetMaterialUnitResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialUnitResponse(rs);
            }
        });
    }

    public PagingResponse<GetMaterialUnitResponse> getList(GetMaterialUnitListRequest request) {
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        PagingResponse<GetMaterialUnitResponse> response = new PagingResponse<GetMaterialUnitResponse>();
        String searchKey = request.getKey();
        StringBuilder querySql = new StringBuilder("SELECT M.id,M.unit_no,M.unit_name,M.description,M.is_delete,M.create_date,M.create_by,M.update_date,M.update_By from material_unit M where M.is_delete=FALSE");
        StringBuilder queryTotal = new StringBuilder("SELECT COUNT(0) from material_unit M where M.is_delete = FALSE");
        if (StringUtils.hasText(searchKey)) {// 条件查询
            querySql.append(" and M.unit_name like CONCAT('%',?,'%') or M.unit_no like CONCAT('%',?,'%')");
            queryTotal.append(" and M.unit_name like CONCAT('%',?,'%') or M.unit_no like CONCAT('%',?,'%')");
            queryParam.add(searchKey);
            queryParam.add(searchKey);
        }
        totalParam.addAll(queryParam);
        if (StringUtils.hasText(request.getPageNo()) && StringUtils.hasText(request.getPageSize())) {
            querySql.append(" limit ?,? ");// ORDER BY c.id
            queryParam.add((Integer.valueOf(request.getPageNo()) - 1) * Integer.valueOf(request.getPageSize()));
            queryParam.add(Integer.valueOf(request.getPageSize()));
        }
        List<GetMaterialUnitResponse> list = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<GetMaterialUnitResponse>() {
            @Override
            public GetMaterialUnitResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialUnitResponse(rs);
            }
        }, queryParam.toArray());
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotal.toString(), totalParam.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    public Boolean delete(DeleteMaterialUnitRequest request) {
        String updateBy = request.getUpdateBy();
        List<String> ids = request.getIds();
        List<Object[]> deleteParams = new LinkedList<Object[]>();
        StringBuilder unitNames = null;
        for (String id : ids) {
            if (checkMaterialUnitUsedInfo(id) == 1) {
                throw new BusinessProcessException("商品计量单位‘" + get(id, null).getUnitName() + "’已经商品引用，无法删除");
            }
            if (null == unitNames)
                unitNames = new StringBuilder(get(id, null).getUnitName());
            else
                unitNames.append("," + get(id, null).getUnitName());
            Object[] obj = new Object[] { updateBy, id };
            deleteParams.add(obj);
        }
        int[] delete = jdbcAccessContext.batchExecute("MATERIAL.SQL_DELETE_MATERIAL_UNIT", deleteParams);
        if (Arrays.asList(delete).contains(-1)) {
            throw new BusinessProcessException("删除失败");
        }
        addOperationLog("计量单位", "删除计量单位:[" + unitNames.toString() + "]", null, request.getUpdateBy());
        return Boolean.TRUE;
    }

    public List<GetMaterialUnitResponse> getListWithCondition(GetMaterialUnitListRequest request) {
        List<Object> queryParam = new ArrayList<Object>();
        List<GetMaterialUnitResponse> response = new ArrayList<GetMaterialUnitResponse>();
        String keyword = request.getKey();
        StringBuilder querySql = new StringBuilder("SELECT M.id,M.unit_no,M.unit_name,M.description,M.is_delete,M.create_date,M.create_by,M.update_date,M.update_By from material_unit M where M.is_delete=FALSE");
        if (StringUtils.hasText(keyword)) {// 条件查询
            querySql.append(" and M.unit_name like CONCAT('%',?,'%') or M.unit_no like CONCAT('%',?,'%')");
            queryParam.add(keyword);
            queryParam.add(keyword);
        }
        response= jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<GetMaterialUnitResponse>() {
            @Override
            public GetMaterialUnitResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialUnitResponse(rs);
            }
        }, queryParam.toArray());
        return response;
    }
}
