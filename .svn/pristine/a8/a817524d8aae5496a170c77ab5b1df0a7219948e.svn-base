package com.siping.service.inventory.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
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

import com.siping.service.common.BillsRepository;
import com.siping.smartone.inventory.request.AddInventoryCheckRequestItemNew;
import com.siping.smartone.inventory.request.AddInventoryCheckRequestNew;
import com.siping.smartone.inventory.request.DeleteInventoryCheckRequest;
import com.siping.smartone.inventory.request.GetInventoryCheckListRequest;
import com.siping.smartone.inventory.request.GetMaterialBatchNumberListRequest;
import com.siping.smartone.inventory.response.GetInventoryCheckItemResponseNew;
import com.siping.smartone.inventory.response.GetMaterialBatchNumberResponse;
import com.siping.smartone.inventory.response.GetInventoryCheckResponse;
import com.siping.smartone.inventory.response.GetInventoryCheckResponseNew;
import com.siping.smartone.inventory.response.GetInventoryLogResponse;
import com.siping.smartone.material.response.GetMaterialResponse;

@Repository
public class InventoryCheckRestRepository extends BillsRepository {
    @Autowired
    private JDBCAccessContext jdbcAccessContext;

    // public Boolean add(AddInventoryCheckRequest request)
    // {//add方法未使用，addNew()使用
    // // String checkNumber = getMaxInventoryCheckId().getId();
    // //checkPostPeriodDateTime();
    // request.setCheckNumber(generateBillsNumber("inventory_check",
    // "check_number"));
    // if (null != get(request.getCheckNumber())) {
    // throw new BusinessProcessException("盘点编号值不能相同！");
    // }
    // filterVoidCheckItems(request);// 过滤表体中不合格的条目
    // Object[] addParams = new Object[] { request.getCheckNumber(),
    // request.getCheckStorage(), request.getSummary(), request.getIsDraft(),
    // request.getIsIoss(), request.getTotalPrice(), new Date(),
    // request.getCreateBy(), request.getAuditor(), request.getAuditDate(),
    // request.getOwner(), request.getBillsDate(), request.getTotalNumber() };
    // int add =
    // jdbcAccessContext.execute("INVENTORYCHECK.SQL_ADD_INVENTORYCHECK",
    // addParams);
    // if (-1 == add) {
    // throw new BusinessProcessException("添加库存盘点单失败,请从新执行该操作");
    // }
    // List<Object[]> addParamsList = new LinkedList<Object[]>();
    // for (GetInventoryCheckItemResponse c : request.getCheckItems()) {
    // Object[] obj = new Object[] { c.getCheckNumber(), c.getMaterialId(),
    // c.getBatchNumber(), c.getProductionDate(), c.getCounts(), c.getPrice(),
    // c.getTotal(), c.getIsGift(), c.getRemark(),
    // c.getAvailableAmount(), c.getActualAmount(), c.getCreateBy() };
    // addParamsList.add(obj);
    // }
    // int[] addFlag =
    // jdbcAccessContext.batchExecute("INVENTORYCHECK.SQL_ADD_INVENTORYCHECKITEMS",
    // addParamsList);
    // if (Arrays.asList(addFlag).contains(-1)) {
    // throw new BusinessProcessException("批量添加盘点条目时失败,请从新执行该操作");
    // }
    // return Boolean.TRUE;
    // }

    // private void filterVoidCheckItems(AddInventoryCheckRequest request){
    // if(request.getCheckItems().size()!=0){
    // int i=0;
    // for(GetInventoryCheckItemResponse checkItem:request.getCheckItems()){
    // checkItem.setCheckNumber(request.getCheckNumber());
    // if(!StringUtils.hasText(checkItem.getMaterialId())){
    // request.getCheckItems().remove(i);
    // }
    // i++;
    // }
    // }
    //
    // }
    // private void filterVoidCheckItems(AddInventoryCheckRequest request) {
    // Double totalNumber = (double) 0;
    // Double totalPrice = (double) 0;
    // for (int i = 0; i < request.getCheckItems().size(); i++) {
    // request.getCheckItems().get(i).setCheckNumber(request.getCheckNumber());//
    // 同时给条目设置好盘点单号
    // if (request.getCheckItems().get(i).getMaterialId() == null ||
    // request.getCheckItems().get(i).getMaterialId().equals("")) {//
    // 没有选择盘点物料编号，直接过滤掉
    // request.getCheckItems().remove(i);// 去除盘点物料为空的条目
    // i--;
    // } else {
    // totalPrice = totalPrice + request.getCheckItems().get(i).getTotal();
    // totalNumber = totalNumber + request.getCheckItems().get(i).getCounts();
    // }
    // }
    // request.setTotalNumber(totalNumber);
    // request.setTotalPrice(totalPrice);
    // }

    public Boolean edit(AddInventoryCheckRequestNew request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int update = jdbcAccessContext.execute("INVENTORYCHECK.SQL_UPDATE_INVENTORYCHECK", new Object[] { sdf.format(new Date()), new Integer(1), request.getSummary(), request.getCheckNumber() });
        if (-1 == update) {
            throw new BusinessProcessException("编辑库存盘点单失败，请从新执行该操作");
        }
        int delete = jdbcAccessContext.execute("INVENTORYCHECK.SQL_DELETE_INVENTORYCHECKITEM", new Object[] { request.getCheckNumber() });
        if (-1 == delete) {
            throw new BusinessProcessException("编辑库存盘点单失败，请从新执行该操作");
        }
        List<Object[]> addParamsList = new LinkedList<Object[]>();
        for (AddInventoryCheckRequestItemNew item : request.getItems()) {
            Object[] obj = new Object[] { request.getCheckNumber(), item.getMaterialId(), item.getMaterialNo(), item.getMaterialName(), item.getBarcode(), item.getMaterialTypeName(),
                    item.getUnitName(), item.getLocationNo(), item.getBatchNumber(), item.getProductionDate().equals("null") ? "" : item.getProductionDate(),
                    item.getExpirationDate().equals("null") ? "" : item.getExpirationDate(), item.getInventoryNumber(), item.getActualNumber(), item.getDiffNumber() };
            addParamsList.add(obj);
        }
        int[] addFlag = jdbcAccessContext.batchExecute("INVENTORYCHECK.SQL_ADD_INVENTORYCHECKITEMS_NEW", addParamsList);
        if (Arrays.asList(addFlag).contains(-1)) {
            throw new BusinessProcessException("编辑盘点条目时失败,请从新执行该操作");
        }
        return Boolean.TRUE;
    }

    // private String buildEditSql(AddInventoryCheckRequest request,
    // List<Object> list) {
    // StringBuilder sql = new StringBuilder("UPDATE inventory_check SET ");
    // assemblePrams(request, list, sql);
    // sql.append(" WHERE check_number=? ");
    // list.add(request.getCheckNumber());
    // return sql.toString();
    // }

    public GetInventoryCheckResponseNew get(String checkNumber) {
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> itemQueryParam = new ArrayList<Object>();
        List<GetInventoryCheckResponseNew> list = jdbcAccessContext.findWithoutSqlManager(buildGetCheckSql(checkNumber, queryParam), new RowMapper<GetInventoryCheckResponseNew>() {
            @Override
            public GetInventoryCheckResponseNew mapRow(ResultSet rs, int arg1) throws SQLException {
                GetInventoryCheckResponseNew m = new GetInventoryCheckResponseNew();
                m.setCheckNumber(rs.getString("check_number"));
                m.setStorageId(rs.getString("storage_id"));
                m.setStorageName(rs.getString("storageName"));// 通过连接查询的列，没有下划线
                m.setStorageAreaId(rs.getString("storage_area_id"));
                m.setStorageAreaName(rs.getString("storageAreaName"));
                m.setOperatorId(rs.getString("operator_id"));
                m.setOperatorName(rs.getString("operatorName"));
                m.setBillsDate(rs.getString("bills_date"));
                m.setSummary(rs.getString("summary"));
                m.setOwner(rs.getString("owner"));
                m.setOwnerName(rs.getString("ownerName"));
                m.setStatus(rs.getBoolean("status"));
                m.setBillsEditDate(rs.getString("bills_edit_date"));
                m.setIsDelete(rs.getBoolean("is_delete"));
                return m;
            }
        }, queryParam.toArray());
        List<GetInventoryCheckItemResponseNew> checkItems = jdbcAccessContext.findWithoutSqlManager(buildGetCheckItemsSql(checkNumber, itemQueryParam),
            new RowMapper<GetInventoryCheckItemResponseNew>() {
                @Override
                public GetInventoryCheckItemResponseNew mapRow(ResultSet rs, int arg1) throws SQLException {
                    GetInventoryCheckItemResponseNew checkItem = new GetInventoryCheckItemResponseNew();
                    checkItem.setId(rs.getString("id"));
                    checkItem.setMaterialId(rs.getString("material_id"));
                    checkItem.setMaterialNo(rs.getString("material_no"));
                    checkItem.setMaterialName(rs.getString("material_name"));
                    checkItem.setMaterialTypeName(rs.getString("material_type_name"));
                    checkItem.setBarcode(rs.getString("barcode"));
                    checkItem.setUnitName(rs.getString("unit_name"));
                    checkItem.setBatchNumber(rs.getString("batch_number"));
                    checkItem.setProductionDate(rs.getString("production_date"));
                    checkItem.setExpirationDate(rs.getString("expiration_date"));
                    checkItem.setInventoryNumber(rs.getString("inventory_number"));
                    checkItem.setActualNumber(rs.getString("actual_number"));
                    checkItem.setDiffNumber(rs.getString("diff_number"));
                    checkItem.setLocationNo(rs.getString("location_no"));
                    return checkItem;
                }
            }, itemQueryParam.toArray());
        if (!CollectionUtils.isEmpty(list)) {
            list.get(0).setItems(checkItems);
            return list.get(0);
        }
        return null;
    }

    private static GetInventoryCheckResponseNew buildInventoryCheckResponseForList(final ResultSet rs) throws SQLException {
        GetInventoryCheckResponseNew m = new GetInventoryCheckResponseNew();
        m.setCheckNumber(rs.getString("check_number"));
        m.setStorageId(rs.getString("storage_id"));
        m.setStorageAreaId(rs.getString("storage_area_id"));
        m.setOperatorId(rs.getString("operator_id"));
        m.setOperatorName(rs.getString("operatorName"));
        m.setBillsDate(rs.getString("bills_date"));
        m.setSummary(rs.getString("summary"));
        m.setOwner(rs.getString("owner"));
        m.setOwnerName(rs.getString("ownerName"));
        m.setStatus(rs.getBoolean("status"));
        if (rs.getBoolean("status") == true) {
            m.setStatusString("已盘点");
        } else {
            m.setStatusString("未盘点");
        }
        m.setBillsEditDate(rs.getString("bills_edit_date"));
        m.setIsDelete(rs.getBoolean("is_delete"));
        return m;
    }

    private static GetInventoryCheckResponseNew buildInventoryCheckResponseAddStorageName(final ResultSet rs) throws SQLException {
        GetInventoryCheckResponseNew m = new GetInventoryCheckResponseNew();
        m.setCheckNumber(rs.getString("check_number"));
        m.setStorageId(rs.getString("storage_id"));
        m.setStorageAreaId(rs.getString("storage_area_id"));
        return m;
    }

    private String buildGetCheckSql(String checkNumber, List<Object> queryParam) {
        StringBuilder sql = new StringBuilder(
            "SELECT st.storage_name storageName,sta.area_name storageAreaName,c.check_number,c.storage_id,c.storage_area_id,c.operator_id,u2.nickname operatorName,c.bills_date,c.summary,c.owner,u1.nickname ownerName,c.status,c.bills_edit_date,c.is_delete from inventory_check c left join usr u1 on c.owner = u1.id left join usr u2 on c.operator_id = u2.id left join storage st on c.storage_id = st.id left join storage_area sta on c.storage_area_id = sta.id");
        if (StringUtils.hasText(checkNumber)) {
            sql.append(" where c.check_number=? ");
            queryParam.add(checkNumber);
        }
        return sql.toString();
    }

    private String buildGetCheckItemsSql(String checkNumber, List<Object> queryParam) {
        StringBuilder sql = new StringBuilder(
            // "SELECT
            // c.id,c.check_number,c.inventory_id,c.material_id,m.material_name
            // materialName,m.material_no materialNo,m.specifications_model
            // specificationsModel,m.barcode barcode,u.unit_name
            // unitName,c.batch_number,c.production_date,c.counts,c.price,c.total,c.is_gift,c.remark,c.available_amount,c.actual_amount,c.create_date,c.create_by
            // from inventory_check_item c left join material m on c.material_id
            // = m.id left join material_unit u on m.unit_id = u.id");
            "SELECT i.id,i.check_number,i.material_id,i.diff_number,i.location_no,i.actual_number,i.inventory_number,i.location_no,i.unit_name,i.material_type_name,i.barcode,i.material_name,i.material_no,i.batch_number,i.production_date,i.expiration_date FROM inventory_check_item i");
        if (StringUtils.hasText(checkNumber)) {
            sql.append(" where i.check_number=? ");
            queryParam.add(checkNumber);
        }
        return sql.toString();
    }

    public PagingResponse<GetInventoryCheckResponseNew> getList(GetInventoryCheckListRequest request) { // 分页查询
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        PagingResponse<GetInventoryCheckResponseNew> response = new PagingResponse<GetInventoryCheckResponseNew>();
        String searchKey = request.getSearchKey();
        StringBuilder querySql = new StringBuilder(
            "SELECT c.check_number,c.storage_id,c.storage_area_id,c.operator_id,u2.nickname operatorName,c.bills_date,c.summary,c.owner,u1.nickname ownerName,c.status,c.bills_edit_date,c.is_delete from inventory_check c left join usr u1 on c.owner = u1.id left join usr u2 on c.operator_id = u2.id left join inventory_check_item ici on c.check_number = ici.check_number left join material mat on ici.material_id = mat.id where c.is_delete = ? ");
        queryParam.add(false);// 默认查询isDelete=false 的值
        StringBuilder queryTotal = new StringBuilder(
            "select count(*) from (SELECT c.check_number from inventory_check c left join inventory_check_item ici on c.check_number = ici.check_number left join material mat on ici.material_id = mat.id where c.is_delete = ? ");
        if (StringUtils.hasText(searchKey)) {// 条件查询
            querySql.append(" and (c.check_number like CONCAT('%',?,'%')");
            queryTotal.append(" and (c.check_number like CONCAT('%',?,'%')");
            queryParam.add(searchKey);
        }
        if (StringUtils.hasText(searchKey)) {
            querySql.append(" or mat.material_name like CONCAT('%',?,'%') or mat.barcode like CONCAT('%',?,'%') or mat.material_no like CONCAT('%',?,'%'))");
            queryTotal.append(" or mat.material_name like CONCAT('%',?,'%') or mat.barcode like CONCAT('%',?,'%') or mat.material_no like CONCAT('%',?,'%'))");
            queryParam.add(searchKey);
            queryParam.add(searchKey);
            queryParam.add(searchKey);
        }
        if (StringUtils.hasText(request.getStartTime())) {// TODO
                                                          // 盘点日期为查询条件，还是编辑日期为查询条件
            querySql.append(" and ? <= Date(c.bills_date)");
            queryTotal.append(" and ? <= Date(c.bills_date)");
            queryParam.add(request.getStartTime());
        }
        if (StringUtils.hasText(request.getEndTime())) {
            querySql.append(" and ? >= Date(c.bills_date)");
            queryTotal.append(" and ? >= Date(c.bills_date)");
            queryParam.add(request.getEndTime());
        }
        if (StringUtils.hasText(request.getStatus())) {// 默认显示未盘点的
                if(!request.getStatus().equals("2")){
                    querySql.append(" and ? = c.status");
                    queryTotal.append(" and ? = c.status");
                    queryParam.add(request.getStatus());
                }
              
        }
        if (StringUtils.hasText(request.getStorageId())) {
            querySql.append(" and ? = c.storage_id");
            queryTotal.append(" and ? = c.storage_id");
            queryParam.add(request.getStorageId());
        }
        if(StringUtils.hasText(request.getPcPdaFlag())){
            querySql.append(" and ? = c.pc_pda_flag");
            queryTotal.append(" and ? = c.pc_pda_flag");
            queryParam.add(request.getPcPdaFlag());
        }
        queryTotal.append(" GROUP BY c.check_number)as temp");// 加上这句话是因为连接查询导致行的数量增加
        totalParam.addAll(queryParam);
        if (StringUtils.hasText(request.getPageNo()) && StringUtils.hasText(request.getPageSize())) {
            querySql.append(" group by c.check_number order by c.bills_date desc,CAST(SUBSTR(c.check_number,3)AS SIGNED) desc limit ?,? ");// ORDER// TODO
                                                                                                                  // BY
                                                                                                                  // c.bills_date
            queryParam.add((Integer.valueOf(request.getPageNo()) - 1) * Integer.valueOf(request.getPageSize()));
            queryParam.add(Integer.valueOf(request.getPageSize()));
        }
        List<GetInventoryCheckResponseNew> list = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<GetInventoryCheckResponseNew>() {
            @Override
            public GetInventoryCheckResponseNew mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildInventoryCheckResponseForList(rs);
            }
        }, queryParam.toArray());
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotal.toString(), totalParam.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    public Boolean delete(DeleteInventoryCheckRequest request) {
        if (null == request.getIds()) {
            throw new BusinessProcessException("库存 盘点不存在，无法删除！");
        }
        List<String> ids = request.getIds();
        List<Object[]> deleteParams = new LinkedList<Object[]>();
        for (String id : ids) {
            Object[] obj = new Object[] { id };
            deleteParams.add(obj);
        }
        if (request.getIds().size() > 1) {
            throw new BusinessProcessException("一次只能删除一条");
        }
        GetInventoryCheckResponseNew checkResponse = get(request.getIds().get(0));
        {
            if (checkResponse.getStatus() != false) {
                throw new BusinessProcessException("已经完成的盘点单不能删除");
            }
        }
        int[] delete = jdbcAccessContext.batchExecute("INVENTORYCHECK.SQL_DELETE_INVENTORYCHECK", deleteParams);
        if (Arrays.asList(delete).contains(-1)) {
            throw new BusinessProcessException("批量删除某条信息时失败,请从新执行该操作");
        }
        return Boolean.TRUE;
    }

    public List<GetMaterialBatchNumberResponse> getMaterialBatchNumberList(GetMaterialBatchNumberListRequest request) {
        List<Object> queryParam = new ArrayList<Object>();
        queryParam.add(request.getMaterialId());
        queryParam.add(request.getStorageId());
        List<GetMaterialBatchNumberResponse> responses = jdbcAccessContext.find("INVENTORYCHECK.SQL_FIND_BATCH_NUMBER_WITH_MATERIALID_AND_STORAGEID", new RowMapper<GetMaterialBatchNumberResponse>() {
            @Override
            public GetMaterialBatchNumberResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildGetMaterialBatchNumberResponse(rs);
            }
        }, queryParam.toArray());
        return responses;
    }

    private GetInventoryLogResponse buildGetInventoryLogResponse(ResultSet rs) throws SQLException, ParseException {
        GetInventoryLogResponse response = new GetInventoryLogResponse();
        response.setMaterialId(rs.getString("material_id"));
        response.setStorageId(rs.getString("storage_id"));
        response.setInventoryId(rs.getString("inventory_id"));
        response.setFlowId(rs.getString("flow_id"));
        response.setIsInbound(rs.getBoolean("is_inbound"));
        response.setFromBillsId(rs.getString("from_bills_no"));
        response.setBatchNumber(rs.getString("batch_number"));
        response.setProductionDate(productDateCut(rs.getString("production_date")));
        response.setCounts(rs.getInt("counts"));
        response.setPrice(rs.getDouble("price"));
        response.setTotal(rs.getDouble("total"));
        response.setCreateDate(rs.getString("create_date"));
        response.setCreateBy(rs.getInt("create_by"));
        return response;
    }

    private GetMaterialBatchNumberResponse buildGetMaterialBatchNumberResponse(final ResultSet rs) throws SQLException {
        GetMaterialBatchNumberResponse response = new GetMaterialBatchNumberResponse();
        response.setMaterialId(rs.getString("materialId"));
        response.setMaterialNo(rs.getString("materialNo"));
        response.setMaterialName(rs.getString("materialName"));
        response.setBarcode(rs.getString("barcode"));
        response.setSpecificationsModel(rs.getString("SpecificationsModel"));
        response.setUnitName(rs.getString("unitName"));
        response.setInventoryId(rs.getString("inventoryId"));
        response.setBatchNumber(rs.getString("batchNumber"));
        response.setProductionDate(DateUtils.formatDate(rs.getDate("productionDate"), "yyyy-MM-dd"));
        response.setCounts(rs.getDouble("counts"));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        response.setExpirationDate(df.format(new Date(rs.getDate("productionDate").getTime() - rs.getInt("expirationDate") * 24 * 60 * 60 * 1000)));
        return response;
    }

    private GetInventoryCheckResponse getMaxInventoryCheckId() {
        List<GetInventoryCheckResponse> response = jdbcAccessContext.find("INVENTORYCHECK.SQL_SELECT_INVENTORYCHECK_BY_ID_MAX", new RowMapper<GetInventoryCheckResponse>() {
            @Override
            public GetInventoryCheckResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                GetInventoryCheckResponse response = new GetInventoryCheckResponse();
                response.setId(rs.getString("id"));
                return response;
            }
        }, new Object[] {});
        if (!CollectionUtils.isEmpty(response)) {
            return response.get(0);
        } else {
            throw new BusinessProcessException("是第一条信息");
        }
    }

    private void bulidGetInventoryCheckMaterialBatchNumberResponse(GetMaterialBatchNumberListRequest request, List<GetInventoryLogResponse> logResponses,
                                                                   List<GetMaterialBatchNumberResponse> responses) {

        GetMaterialResponse material = request.getMaterialResponse();
        HashSet<String> filterSameBatchNumber = new HashSet<String>();
        List<String> repeatBatchNumber = new ArrayList<String>();
        List<String> noRepeatBatchNumber = new ArrayList<String>();
        for (GetInventoryLogResponse logResponse : logResponses) {
            String batchNumber = logResponse.getBatchNumber();
            if (filterSameBatchNumber.contains(batchNumber)) {
                repeatBatchNumber.add(batchNumber);
            } else {
                filterSameBatchNumber.add(batchNumber);
                noRepeatBatchNumber.add(batchNumber);
            }
        }
        for (String batchNumber : noRepeatBatchNumber) {// 将没有重复的写入，有重复的再做处理
            for (GetInventoryLogResponse singleInventoryLogResponse : logResponses) {
                if (batchNumber.equals(singleInventoryLogResponse.getBatchNumber())) {
                    GetMaterialBatchNumberResponse response = new GetMaterialBatchNumberResponse();
                    response.setMaterialNo(material.getMaterialNo());
                    response.setMaterialName(material.getMaterialName());
                    response.setBarcode(material.getBarcode());
                    if (singleInventoryLogResponse.getIsInbound() == true)
                        response.setSmNumber(singleInventoryLogResponse.getCounts().toString());
                    response.setBatchNumber(singleInventoryLogResponse.getBatchNumber());
                    response.setPrice(singleInventoryLogResponse.getPrice());
                    response.setProductionDate(singleInventoryLogResponse.getProductionDate());
                    response.setSpecificationsModel(material.getSpecificationsModel());
                    responses.add(response);
                }
            }
        }
        // 输出重复的元素
        for (String batchNumber : repeatBatchNumber) {
            HashSet<String> filterSameProductDate = new HashSet<String>();
            List<String> repeatProductDate = new ArrayList<String>();
            List<String> noRepeatProductDate = new ArrayList<String>();
            List<GetInventoryLogResponse> tempLogResponses = new ArrayList<GetInventoryLogResponse>();
            for (GetInventoryLogResponse singleInventoryLogResponse : logResponses) {
                if (batchNumber.equals(singleInventoryLogResponse.getBatchNumber())) {
                    tempLogResponses.add(singleInventoryLogResponse);
                }
            }
            for (GetInventoryLogResponse tempSingleLogResponse : tempLogResponses) {
                // String batchNumber = logResponse.getBatchNumber();
                // if (filterSameBatchNumber.contains(batchNumber)) {
                // repeatBatchNumber.add(batchNumber);
                // }
                // else {
                // filterSameBatchNumber.add(batchNumber);
                // noRepeatBatchNumber.add(batchNumber);
                // }
            }
        }

    }

    private String productDateCut(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(date).toString();
    }

    public Boolean addNew(AddInventoryCheckRequestNew request) {
        request.setCheckNumber(generateBillsNumber("inventory_check", "check_number"));
        // if (null != get(request.getCheckNumber())) {
        // throw new BusinessProcessException("盘点编号值不能相同！");
        // }
        Object[] addParams = new Object[] { request.getCheckNumber(), request.getStorageId(), request.getStorageAreaId(), request.getOperatorId(), request.getBillsDate(), request.getSummary(),
                request.getOwner(), 0, request.getBillsDate(), 0, 0 };
        int add = jdbcAccessContext.execute("INVENTORYCHECK.SQL_ADD_INVENTORYCHECK_NEW", addParams);
        if (-1 == add) {
            throw new BusinessProcessException("添加库存盘点单失败,请从新执行该操作");
        }
        List<Object[]> addParamsList = new LinkedList<Object[]>();
        for (AddInventoryCheckRequestItemNew item : request.getItems()) {
            Object[] obj = new Object[] { request.getCheckNumber(), item.getMaterialId(), item.getMaterialNo(), item.getMaterialName(), item.getBarcode(), item.getMaterialTypeName(),
                    item.getUnitName(), item.getLocationNo(), item.getBatchNumber(), item.getProductionDate().equals("null") ? "" : item.getProductionDate(),
                    item.getExpirationDate().equals("null") ? "" : item.getExpirationDate(), item.getInventoryNumber(), item.getActualNumber(), item.getDiffNumber() };
            addParamsList.add(obj);
        }
        int[] addFlag = jdbcAccessContext.batchExecute("INVENTORYCHECK.SQL_ADD_INVENTORYCHECKITEMS_NEW", addParamsList);
        if (Arrays.asList(addFlag).contains(-1)) {
            throw new BusinessProcessException("批量添加盘点条目时失败,请从新执行该操作");
        }
        return Boolean.TRUE;
    }

    public Boolean addPdaInventoryCheck(AddInventoryCheckRequestNew request) {
        request.setCheckNumber(generateBillsNumber("inventory_check", "check_number"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        request.setBillsDate(sdf.format(new Date()));
        Object[] addParams = new Object[] { request.getCheckNumber(), request.getStorageId(), request.getStorageAreaId(), request.getOperatorId(), request.getBillsDate(), request.getSummary(),
                request.getOwner(), 0, request.getBillsEditDate(), 0, 1 };
        int add = jdbcAccessContext.execute("INVENTORYCHECK.SQL_ADD_INVENTORYCHECK_NEW", addParams);
        if (-1 == add) {
            throw new BusinessProcessException("添加库存盘点单失败,请从新执行该操作");
        }
        return Boolean.TRUE;
    }
}
