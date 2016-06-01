package com.siping.service.material.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.material.request.AddMaterialTypeRequest;
import com.siping.smartone.material.request.DeleteMaterialTypeRequest;
import com.siping.smartone.material.request.GetMaterialTypeListRequest;
import com.siping.smartone.material.response.GetMaterialTypeResponse;

@Repository
public class MaterialTypeRestRepository extends BillsRepository {

    public Boolean add(AddMaterialTypeRequest request) {
        Integer level = null;
        if (null != get(null, request.getTypeName())) {
            throw new BusinessProcessException("不能重复添加商品类型！");
        }
        if (request.getParentId() == null) {// 防止出现void
                                            // pointer错误，同时为空时直接给赋值到全部下面
            request.setParentId(0);
        }
        if (request.getParentId() != 0) {
            level = get(request.getParentId(), null).getLevel() + 1;
        } else {
            level = 1;
        }
        Object[] addParams = new Object[] { request.getTypeName(), level, request.getDescription(), request.getParentId(), false, true, request.getCreateBy() };
        int add = jdbcAccessContext.execute("MATERIAL.SQL_ADD_MATERIAL_TYPE", addParams);
        if (-1 == add) {
            return Boolean.FALSE;
        }
        try {
            excuteIsLeafnode(request.getParentId(), false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessProcessException("修改父节点为非叶子节点失败");
        }
        addOperationLog("商品分类", "新增商品分类:[" + request.getTypeName() + "]", null, request.getUpdateBy() + "");
        return Boolean.TRUE;
    }

    public Boolean edit(AddMaterialTypeRequest request) {
        List<GetMaterialTypeResponse> childResponses = getChild(request.getId().toString());
        for (GetMaterialTypeResponse childResponse : childResponses) {
            if (childResponse.getId().equals(request.getParentId())) {
                throw new BusinessProcessException("不能选取自己子分类作为自己的父级分类");
            }
        }
        // 修改节点的层级时，需要递归修改自己字节点的层次
        List<Object> paramList = new ArrayList<Object>();
        if (null == get(request.getId(), null)) {
            throw new BusinessProcessException("商品分类不存在不能编辑！");
        }
        int edit = jdbcAccessContext.executeWithoutSqlManager(buildEditSql(request, paramList), paramList.toArray());
        if (-1 == edit) {
            throw new BusinessProcessException("修改失败");
        }
        if (request.getParentId() != request.getOldParentId()) {
            try {
                excuteIsLeafnode(request.getOldParentId(), null);// 原来的父节点需要判断
                excuteIsLeafnode(request.getParentId(), false);// 新的父节点直接置为非叶子结点
                resetLevel(request.getParentId(), request.getId());
            } catch (Exception e) {
                throw new BusinessProcessException("修改原父节点和现父节点失败");
            }

        }
        addOperationLog("商品分类", "更新商品分类:[" + request.getTypeName() + "]", null, request.getUpdateBy() + "");
        return Boolean.TRUE;
    }

    private void resetLevel(Integer parentMaterialTypeId, Integer materialTypeId) {

        List<Object> queryParam = new ArrayList<Object>();
        // 获取父节点level,计算子节点level
        int level = 0;
        if (parentMaterialTypeId != 0) {
            level = get(parentMaterialTypeId, null).getLevel() + 1;
        }
        GetMaterialTypeResponse materialType = get(materialTypeId, null);
        materialType.setLevel(level);
        // 子节点是否有子节点
        if (!materialType.getIsLeafNode()) {
            // 有子节点，循环递归
            queryParam.add(materialType.getId());
            List<GetMaterialTypeResponse> list = getChild(materialType.getId().toString());
            for (GetMaterialTypeResponse type : list) {
                resetLevel(type.getParentTypeId(), type.getId());
            }
        }
    }

    private void excuteIsLeafnode(Integer typeId, Boolean condition) throws Exception {
        if (typeId == 0) {
            return;
        }
        if (condition != null) {
            jdbcAccessContext.executeWithoutSqlManager("UPDATE material_type SET is_leafnode = ? where id = ?", new Object[] { condition, typeId });
        } else {
            if (0 == jdbcAccessContext.findIntegerWithoutSqlManager("select count(*) from material_type where parent_type_id = ?", new Object[] { typeId }))
                excuteIsLeafnode(typeId, true);
            else {
                excuteIsLeafnode(typeId, false);
            }
        }
    }

    private static String buildEditSql(AddMaterialTypeRequest request, List<Object> list) {
        StringBuilder sql = new StringBuilder("UPDATE material_type SET ");
        if (StringUtils.hasText(request.getTypeName())) {
            sql.append("type_name=? ");
            list.add(request.getTypeName());
        }
        if (StringUtils.hasText(request.getDescription())) {
            if (list.size() > 0)
                sql.append(",description=? ");
            else
                sql.append("description=? ");
            list.add(request.getDescription());
        }
        if (null != request.getParentId()) {
            if (list.size() > 0)
                sql.append(",parent_type_id=? ");
            else
                sql.append("parent_type_id=? ");
            list.add(request.getParentId());
        }
        if (null != request.getUpdateBy() && request.getUpdateBy() != 0) {
            if (list.size() > 0)
                sql.append(",update_by=?,update_date=NOW() ");
            else
                sql.append("update_by=?,update_date=NOW() ");
            list.add(request.getUpdateBy());
        }
        sql.append("where id=? ");
        list.add(Integer.valueOf(request.getId()));
        return sql.toString();
    }

    public GetMaterialTypeResponse get(Integer id, String typeName) {
        List<Object> queryParam = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder(
            "SELECT t.id,t.type_name,t.description,t.parent_type_id,m.type_name parent_type_name,t.level,t.is_delete,t.is_leafnode,t.create_date,t.create_by from material_type t left join material_type m on t.parent_type_id = m.id where t.is_delete=FALSE ");
        if (id != null) {
            if (StringUtils.hasText(id.toString())) {
                sql.append("and t.id=? ");
                queryParam.add(Integer.valueOf(id));
            }
        }
        if (StringUtils.hasText(typeName)) {
            sql.append("and t.type_name=? ");
            queryParam.add(typeName);
        }
        List<GetMaterialTypeResponse> list = jdbcAccessContext.findWithoutSqlManager(sql.toString(), new RowMapper<GetMaterialTypeResponse>() {
            @Override
            public GetMaterialTypeResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialTypeResponseForDetail(rs);
            }
        }, queryParam.toArray());
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    private static GetMaterialTypeResponse buildMaterialTypeResponseForDetail(final ResultSet rs) throws SQLException {
        GetMaterialTypeResponse type = new GetMaterialTypeResponse();
        type.setTypeName(rs.getString("type_name"));
        type.setId(rs.getInt("id"));
        type.setLevel(rs.getInt("level"));
        type.setDescription(rs.getString("description"));
        type.setParentTypeId(rs.getInt("parent_type_id"));
        type.setIsLeafNode(rs.getBoolean("is_leafnode"));
        if (rs.getInt("parent_type_id") == 0) {
            type.setParentTypeName("(根类型)");
        } else {
            type.setParentTypeName(rs.getString("parent_type_name"));
        }
        type.setIsDelete(rs.getBoolean("is_delete"));
        String createDate = rs.getString("create_date");
        if (StringUtils.hasText(createDate)) {
            type.setCreateDate(createDate.substring(0, 10));
        }
        type.setCreateBy(rs.getInt("create_by"));
        return type;
    }

    private static GetMaterialTypeResponse buildMaterialTypeResponse(final ResultSet rs) throws SQLException {
        GetMaterialTypeResponse type = new GetMaterialTypeResponse();
        type.setTypeName(rs.getString("type_name"));
        type.setId(rs.getInt("id"));
        type.setLevel(rs.getInt("level"));
        type.setDescription(rs.getString("description"));
        type.setParentTypeId(rs.getInt("parent_type_id"));
        type.setIsLeafNode(rs.getBoolean("is_leafnode"));
        type.setIsDelete(rs.getBoolean("is_delete"));
        String createDate = rs.getString("create_date");
        if (StringUtils.hasText(createDate)) {
            type.setCreateDate(createDate.substring(0, 10));
        }
        type.setCreateBy(rs.getInt("create_by"));
        return type;
    }

    private static GetMaterialTypeResponse buildMaterialTypeResponseForList(final ResultSet rs) throws SQLException {
        GetMaterialTypeResponse type = new GetMaterialTypeResponse();
        type.setTypeName(rs.getString("type_name"));
        type.setId(rs.getInt("id"));
        type.setLevel(rs.getInt("level"));
        type.setDescription(rs.getString("description"));
        type.setParentTypeId(rs.getInt("parent_type_id"));
        type.setIsLeafNode(rs.getBoolean("is_leafnode"));
        // type.setIsDelete(rs.getBoolean("is_delete"));
        type.setParentTypeName(rs.getString("parent_type_name"));
        String createDate = rs.getString("create_date");
        if (StringUtils.hasText(createDate)) {
            type.setCreateDate(createDate.substring(0, 10));
        }
        type.setCreateBy(rs.getInt("create_by"));
        return type;
    }

    public List<GetMaterialTypeResponse> getList() {
        return jdbcAccessContext.find("MATERIAL.SQL_GET_ALL_MATERIAL_TYPE", new RowMapper<GetMaterialTypeResponse>() {
            @Override
            public GetMaterialTypeResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialTypeResponseForList(rs);
            }
        });
    }

    // public Boolean delete(String id, String updateBy) {
    // if (null == get(id, null))
    // throw new BusinessProcessException("物料类型不存在，无法删除！");
    // AddMaterialTypeRequest request = new AddMaterialTypeRequest();
    // request.setId(id);
    // request.setIsDelete(Boolean.TRUE);
    // request.setUpdateBy(Integer.valueOf(updateBy));
    // return edit(request);
    // }

    public Boolean delete(DeleteMaterialTypeRequest request) {
        String updateBy = request.getUpdateBy();
        List<Integer> ids = request.getIds();
        List<Object[]> deleteParams = new LinkedList<Object[]>();
        Set<Integer> materialTypeParentIdSet = new HashSet<Integer>();
        StringBuilder typeNames = null;
        for (Integer id : ids) {
            String checkInfo = checkMaterialTypeUsedInfo(id);
            if (!"Y".equals(checkInfo)) {
                throw new BusinessProcessException(checkInfo);
            } else {
                Object[] obj = new Object[] { updateBy, id };
                deleteParams.add(obj);
                materialTypeParentIdSet.add(get(id, null).getParentTypeId());
            }
            if (null == typeNames) {
                typeNames = new StringBuilder(get(id, null).getTypeName());
            } else {
                typeNames.append("," + get(id, null).getTypeName());
            }
        }
        int[] delete = jdbcAccessContext.batchExecute("MATERIAL.SQL_DELETE_MATERIAL_TYPE", deleteParams);
        if (Arrays.asList(delete).contains(-1)) {
            throw new BusinessProcessException("删除失败");
        }
        // 检查父级是否需要变成子节点，获取父级节点
        List<Object[]> updateParentTypeIds = new ArrayList<Object[]>();
        for (Integer materialTypeId : materialTypeParentIdSet) {
            updateMaterilaTypeParentByParentId(materialTypeId, ids, updateParentTypeIds, request.getUpdateBy());
        }
        int[] update = jdbcAccessContext.batchExecute("MATERIAL.SQL_UPDATE_MATERIAL_TYPE_BY_DELETE_ITS_SON_NODE", updateParentTypeIds);
        if (Arrays.asList(update).contains(-1)) {
            throw new BusinessProcessException("删除失败");
        }
        addOperationLog("商品分类", "删除商品分类:[" + typeNames.toString() + "]", null, request.getUpdateBy() + "");
        return Boolean.TRUE;
    }

    private void updateMaterilaTypeParentByParentId(Integer materialTypeId, List<Integer> ids, List<Object[]> updateParentTypeIds, String updateBy) {
        // List<GetMaterialTypeResponse> materialTypeResponses=
        // getChild(materialTypeId);
        // if(materialTypeResponses.size()==1){
        // if(ids.contains(materialTypeResponses.get(0).getId())){
        // Object[] obj = new Object[] {materialTypeId};
        // updateParentTypeIds.add(obj);
        // }
        // }
        // else if(materialTypeResponses.size()==0){
        // throw new BusinessProcessException("删除出现错误");
        // }
        // else if(materialTypeResponses.size()>1){
        // HashSet<String> childHashSet = new HashSet<String>();
        // for(GetMaterialTypeResponse
        // materialTypeResponse:materialTypeResponses){
        // childHashSet.add(materialTypeResponse.getId().toString());
        // }
        // HashSet<String> deleteHashSet = new HashSet<String>();
        // for(String id:ids){
        // deleteHashSet.add(id);
        // }
        // if(deleteHashSet.containsAll(childHashSet)){
        // Object[] obj = new Object[] {materialTypeId};
        // updateParentTypeIds.add(obj);
        // }
        // }
        if ("Y".equals(checkMaterialTypeUsedInfo(materialTypeId))) {
            Object[] obj = new Object[] { materialTypeId };
            updateParentTypeIds.add(obj);
        }
//        addOperationLog("商品分类", "更新商品父分类:[" + get(materialTypeId, null).getTypeName() + "]", null, updateBy);
    }

    private String checkMaterialTypeUsedInfo(Integer typeId) {
        // 检查商品类别是否有子类别
        int count1 = jdbcAccessContext.findIntegerWithoutSqlManager("select count(*) from material_type where parent_type_id = ? and is_delete = 0", new Object[] { typeId });
        if(count1 > 0){
            return "商品分类‘" + get(typeId, null).getTypeName() + "’已经存在子类别，无法删除";
        }
        int count2 = jdbcAccessContext.findIntegerWithoutSqlManager("select count(*) from material where material_type = ? and is_delete = 0", new Object[] { typeId });
        if(count2 > 0){
            return "商品分类‘" + get(typeId, null).getTypeName() + "’已被商品主数据引用，无法删除";
        }
        return "Y";
    }

    public List<GetMaterialTypeResponse> getChild(String parentId) {
        return jdbcAccessContext.find("MATERIAL.SQL_GET_CHILD_MATERIAL_TYPE", new RowMapper<GetMaterialTypeResponse>() {
            @Override
            public GetMaterialTypeResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialTypeResponse(rs);
            }
        }, new Object[] { parentId });
    }

    public String getAllTypeChilds(String parentId) { // 查询所有的商品分类子节点,以字符串的形式返回
        String allId = jdbcAccessContext.findStringWithoutSqlManager("SELECT queryAllChildMaterialType(?)", new Object[] { parentId });
        if (StringUtils.hasText(allId)) {
            return allId.substring(2, allId.length());
        }
        return null;
    }

    public PagingResponse<GetMaterialTypeResponse> getAllMaterialType(GetMaterialTypeListRequest request) {
        // TODO Auto-generated method stub
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        PagingResponse<GetMaterialTypeResponse> response = new PagingResponse<GetMaterialTypeResponse>();
        String searchKey = request.getKey();
        StringBuilder queryTotal = new StringBuilder("SELECT count(*) from material_type b where b.is_delete=false ");
        StringBuilder querySql = new StringBuilder(
            "SELECT b.id ,b.type_name, b.description,b.parent_type_id,m.type_name parent_type_name,b.level,b.is_leafnode,b.create_date,b.create_by,b.update_date,b.update_by from material_type b left join material_type m on b.parent_type_id = m.id where b.is_delete=false ");
        if (StringUtils.hasText(searchKey)) {
            queryTotal.append(" and (b.type_name like CONCAT('%',?,'%') or b.description like CONCAT('%',?,'%'))");
            querySql.append(" and (b.type_name like CONCAT('%',?,'%') or b.description like CONCAT('%',?,'%'))");
            queryParam.add(searchKey);
            queryParam.add(searchKey);
        }
        totalParam.addAll(queryParam);
        if (StringUtils.hasText(request.getPageNo()) && StringUtils.hasText(request.getPageSize())) {
            querySql.append(" limit ?,? ");// ORDER BY c.id
            queryParam.add((Integer.valueOf(request.getPageNo()) - 1) * Integer.valueOf(request.getPageSize()));
            queryParam.add(Integer.valueOf(request.getPageSize()));
        }
        List<GetMaterialTypeResponse> list = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<GetMaterialTypeResponse>() {
            @Override
            public GetMaterialTypeResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialTypeResponseForList(rs);
            }
        }, queryParam.toArray());
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotal.toString(), totalParam.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    protected GetMaterialTypeResponse buildBusinessPartnerResponse(ResultSet rs) throws SQLException {
        GetMaterialTypeResponse res = new GetMaterialTypeResponse();
        res.setId(rs.getInt("id"));
        res.setTypeName(rs.getString("typeName"));
        res.setParentTypeId(rs.getInt("parentTypeId"));
        res.setIsLeafNode(rs.getBoolean("is_leafnode"));
        res.setDescription(rs.getString("description"));
        res.setUpdateBy(rs.getInt("updateBy"));
        res.setUpdateDate(rs.getString("updateDate"));
        res.setCreateBy(rs.getInt("createBy"));
        res.setUpdateDate(rs.getString("createDate"));
        return res;
    }

    private String buildGetListSql(CommonBillsRequest request, long begin, List<Object> queryParam, StringBuilder total, List<Object> totalParams) {
        Integer pageSize = request.getPageSize();
        String keyWords = request.getKeywords();
        total.append("SELECT count(*) from material_type b where is_delete=false ");
        StringBuilder sql = new StringBuilder(
            "SELECT id id,type_name typeName, description,parent_type_id parentTypeId, is_leafnode isLeafNode, level, create_date createDate,create_by createBy,update_date updateDate,update_by updateBy from material_type b where b.is_delete=false ");
        if (StringUtils.hasText(keyWords)) {
            sql.append(" and (b.type_name like CONCAT('%',?,'%') or b.description like CONCAT('%',?,'%'))");
            total.append(" and (b.type_name like CONCAT('%',?,'%') or b.description like CONCAT('%',?,'%'))");
            queryParam.add(keyWords);
            queryParam.add(keyWords);
        }
        queryParam.add(begin);
        queryParam.add(pageSize);
        sql.append(" limit ?,? ");
        return sql.toString();
    }

    public PagingResponse<GetMaterialTypeResponse> getAllMaterialType(CommonBillsRequest request) {
        return null;
    }

    public List<GetMaterialTypeResponse> getList(String id) {
        List<GetMaterialTypeResponse> allMaterialTypeResponses = getList();
        List<Integer> allMaterialTypeIdString = new ArrayList<Integer>();
        for (GetMaterialTypeResponse response : allMaterialTypeResponses) {
            allMaterialTypeIdString.add(response.getId());
        }
        String childMaterialTypeIdString = getAllTypeChilds(id);
        String[] childMaterialTypeIds = childMaterialTypeIdString.split(",");
        List<String> childMaterialTypeIdsList = new ArrayList<String>();
        for (int i = 0; i < childMaterialTypeIds.length; i++) {
            childMaterialTypeIdsList.add(childMaterialTypeIds[i]);
        }
        allMaterialTypeIdString.removeAll(childMaterialTypeIdsList);
        allMaterialTypeResponses = new ArrayList<GetMaterialTypeResponse>();
        for (Integer materialTypeId : allMaterialTypeIdString) {
            allMaterialTypeResponses.add(get(materialTypeId, null));
        }
        return allMaterialTypeResponses;
    }

    public PagingResponse<GetMaterialTypeResponse> getListByParentId(GetMaterialTypeListRequest request) {
        PagingResponse<GetMaterialTypeResponse> response = new PagingResponse<GetMaterialTypeResponse>();
        String keyWord = request.getKey();
        String pageNo = request.getPageNo();
        String pageSize = request.getPageSize();
        String parentId = request.getParentId();
        parentId = null;
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder(
            "SELECT g.id,g.type_name,g.description,g.parent_type_id,g.`level`,g.is_delete,g.is_leafnode,g.create_date,g.create_by,m.type_name parent_type_name "
            + "FROM material_type g LEFT JOIN material_type m ON g.parent_type_id = m.id where g.is_delete = 0 ");
        StringBuilder totalSql = new StringBuilder(
            "SELECT count(*) FROM material_type g LEFT JOIN material_type m ON g.parent_type_id = m.id where g.is_delete = 0 ");
        if (StringUtils.hasText(parentId)) {
            sql.append(" and g.parent_type_id = ? ");
            totalSql.append(" and g.parent_type_id = ? ");
            queryParam.add(Integer.parseInt(parentId));
            totalParam.add(Integer.parseInt(parentId));
        }
        if (StringUtils.hasText(keyWord)) {
            sql.append(" and g.type_name like CONCAT('%',?,'%') ");
            totalSql.append(" and g.type_name like CONCAT('%',?,'%') ");
            queryParam.add(keyWord);
            totalParam.add(keyWord);
        }
        if (StringUtils.hasText(pageNo) && StringUtils.hasText(pageSize)) {
            sql.append(" ORDER BY g.create_date DESC limit ?,? ");
            queryParam.add((Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize));
            queryParam.add(Integer.parseInt(pageSize));
        }
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(totalSql.toString(), totalParam.toArray());
        List<GetMaterialTypeResponse> list = jdbcAccessContext.findWithoutSqlManager(sql.toString(), new RowMapper<GetMaterialTypeResponse>() {
            @Override
            public GetMaterialTypeResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialTypeResponseForDetail(rs);
            }
        }, queryParam.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }
}
