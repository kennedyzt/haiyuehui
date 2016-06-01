package com.siping.wms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.service.inventory.service.InventoryRestService;
import com.siping.service.storage.service.StorageLocationRestService;
import com.siping.smartone.invoicing.inventory.inventory.request.AddInventoryRequest;
import com.siping.smartone.material.response.GetMaterialBatchResponse;
import com.siping.smartone.wms.request.AddInventoryAllocateRequest;
import com.siping.smartone.wms.request.GetInventoryAllocateListRequest;
import com.siping.smartone.wms.response.GetInventoryAllocateResponse;

@Repository
public class InventoryAllocateRestRepository extends BillsRepository {

    @Autowired
    private JDBCAccessContext jdbcAccessContext;
    @Autowired
    private InventoryRestService inventoryRestService;
    @Autowired
    private StorageLocationRestService storageLocationRestService;

    public PagingResponse<GetInventoryAllocateResponse> getList(GetInventoryAllocateListRequest request) {
        PagingResponse<GetInventoryAllocateResponse> responseList = new PagingResponse<GetInventoryAllocateResponse>();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParams = new ArrayList<Object>();
        StringBuilder totalRecordsSql = new StringBuilder();
        long begin = (request.getPageNo() - 1) * request.getPageSize();
        List<GetInventoryAllocateResponse> response = jdbcAccessContext.findWithoutSqlManager(buildGetListSql(request, begin, queryParam, totalRecordsSql, totalParams),
            new RowMapper<GetInventoryAllocateResponse>() {
                @Override
                public GetInventoryAllocateResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                    return buildInventoryReceiptResponse(rs);
                }
            }, queryParam.toArray());
        int totalRecords = jdbcAccessContext.findIntegerWithoutSqlManager(totalRecordsSql.toString(), totalParams.toArray());
        responseList.setRecords(response);
        responseList.setTotalRecords(totalRecords);
        return responseList;
    }

    protected GetInventoryAllocateResponse buildInventoryReceiptResponse(ResultSet rs) throws SQLException {
        GetInventoryAllocateResponse i = new GetInventoryAllocateResponse();
        i.setAllocateNumber(rs.getString("allocate_number"));

        String billsDate = rs.getString("bills_date");
        if (StringUtils.hasText(billsDate)) {
            i.setBillsDate(billsDate.substring(0, 10));
        }
        i.setMaterialId(rs.getInt("material_id"));
        i.setMaterialName(rs.getString("material_name"));
        i.setMaterialNo(rs.getString("material_no"));
        i.setBarcode(rs.getString("barcode"));
        i.setBatchNumber(rs.getString("batch_number"));
        i.setUnitId(rs.getInt("unit_id"));
        i.setUnitName(rs.getString("unit_name"));
        String productionDate = rs.getString("production_date");
        if (StringUtils.hasText(productionDate)) {
            i.setProductionDate(productionDate.substring(0, 10));
        }
        String expirationDate = rs.getString("expiration_date");
        if (StringUtils.hasText(expirationDate)) {
            i.setExpirationDate(expirationDate.substring(0, 10));
        }
        i.setCounts(rs.getString("counts"));
        i.setFromLocationId(rs.getInt("from_location_id"));
        i.setFromLocationName(rs.getString("from_location_name"));
        i.setFromStroageNo(rs.getString("fromStroageNo"));
        i.setFromStroageName(rs.getString("fromStroageName"));
        i.setToLocationId(rs.getInt("to_location_id"));
        i.setToLocationName(rs.getString("to_location_name"));
        i.setToStroageNo(rs.getString("toStroageNo"));
        i.setToStroageName(rs.getString("toStroageName"));
        i.setCreateBy(rs.getInt("create_by"));
        try {
            i.setCreateName(rs.getString("createName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    // 查询sql语句拼接
    private String buildGetListSql(GetInventoryAllocateListRequest request, long begin, List<Object> queryParam, StringBuilder total, List<Object> totalParams) {
        Integer pageSize = request.getPageSize();
        total
            .append("SELECT COUNT(*) from (select i.allocate_number,i.bills_date,m.material_name,m.material_no from inventory_allocate i LEFT JOIN material m ON i.material_id = m.id LEFT JOIN material_unit u ON m.unit_id = u.id LEFT JOIN storage_location lf ON i.from_location_id = lf.id LEFT JOIN storage_location lt ON i.to_location_id = lt.id LEFT JOIN STORAGE sf ON i.from_storage_id = sf.id LEFT JOIN STORAGE st ON i.to_storage_id = st.id LEFT JOIN usr us ON i.create_by = us.id WHERE 1 = 1");
        StringBuilder sql = new StringBuilder(
            "SELECT i.allocate_number,i.bills_date,i.material_id,m.barcode,m.material_no,m.material_name,m.specifications_model,m.unit_id,u.unit_name,i.batch_number,i.production_date,i.expiration_date,i.from_location_id,lf.location_name from_location_name,sf.storage_name fromStroageName,sf.storage_no fromStroageNo,i.to_location_id,lt.location_name to_location_name,st.storage_name toStroageName,st.storage_no toStroageNo,i.counts,i.create_by,us.user_name createName FROM inventory_allocate i LEFT JOIN material m ON i.material_id = m.id LEFT JOIN material_unit u ON m.unit_id = u.id LEFT JOIN storage_location lf ON i.from_location_id = lf.id LEFT JOIN storage_location lt ON i.to_location_id = lt.id "
                + " LEFT JOIN STORAGE sf ON i.from_storage_id = sf.id LEFT JOIN STORAGE st ON i.to_storage_id = st.id LEFT JOIN usr us ON i.create_by = us.id WHERE 1 = 1");
        if (StringUtils.hasText(request.getKeywords())) {
            queryParam.add(request.getKeywords());
            queryParam.add(request.getKeywords());
            queryParam.add(request.getKeywords());
            sql.append(" and (i.allocate_number like CONCAT('%',?,'%')");
            total.append(" and (i.allocate_number like CONCAT('%',?,'%')");
            sql.append(" or m.material_name like CONCAT('%',?,'%')");
            total.append(" or m.material_name like CONCAT('%',?,'%')");
            sql.append(" or m.material_no like CONCAT('%',?,'%'))");
            total.append(" or m.material_no like CONCAT('%',?,'%'))");
        }
        if (StringUtils.hasText(request.getEndDate())) {
            queryParam.add(request.getEndDate());
            sql.append(" and Date(i.bills_date) <= ? ");
            total.append(" and Date(i.bills_date) < ? ");
        }
        if (StringUtils.hasText(request.getStartDate())) {
            queryParam.add(request.getStartDate());
            sql.append(" and Date(i.bills_date) >= ? ");
            total.append(" and Date(i.bills_date) >= ? ");
        }
        sql.append(" group by i.allocate_number");
        total.append(" group by i.allocate_number)as temp");
        totalParams.addAll(queryParam);
        queryParam.add(begin);
        queryParam.add(pageSize);
        sql.append(" order by i.create_date desc limit ?,? ");
        return sql.toString();
    }

    public GetInventoryAllocateResponse get(String allocateNumber) {
        GetInventoryAllocateResponse response = jdbcAccessContext.findUniqueResult("INVENTORYALLOCATE.SQL_GET_INVENTORYALLOCATE", new RowMapper<GetInventoryAllocateResponse>() {
            @Override
            public GetInventoryAllocateResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildInventoryReceiptResponse(rs);
            }
        }, new Object[] { allocateNumber });
        return response;
    }

    private void changeInvetory(AddInventoryAllocateRequest request) {
        AddInventoryRequest inventoryRequest = new AddInventoryRequest();
        inventoryRequest.setMaterialId(request.getMaterialId());
        inventoryRequest.setBillsNo(request.getAllocateNumber());
        inventoryRequest.setBillsDate(request.getBillsDate());
        inventoryRequest.setCounts(null != request.getCounts() ? Double.valueOf(request.getCounts()) : null);
        inventoryRequest.setTotal(null);
        inventoryRequest.setCreateBy(request.getCreateBy());
        inventoryRequest.setBatchNumber(request.getBatchNumber());
        inventoryRequest.setProductionDate(request.getProductionDate());
        inventoryRequest.setExpirationDate(request.getExpirationDate());
        inventoryRequest.setSellPrice(null);
        String purchasePrice = jdbcAccessContext.findStringWithoutSqlManager("SELECT M.price from inventory_log l LEFT JOIN material_batch m ON l.batch_uuid=m.uuid where "
            + " m.is_inbound=TRUE AND l.from_bills_no LIKE 'PD%'  AND M.batch_number=? AND M.material_id=?", new Object[] { request.getBatchNumber(), request.getMaterialId() });
        inventoryRequest.setPurchasePrice(StringUtils.hasText(purchasePrice) ? Double.valueOf(purchasePrice) : null);
        addOperationLog("调拨单", "新增调拨单，单据编号：[{0}],请求的ip为：[" + request.getIp() + "]，mac为：[" + request.getMac() + "]", request.getAllocateNumber(), request.getCreateBy() + "");
        if (null == request.getFromLocationId() && null != request.getToLocationId()) {
            // 1、从虚拟仓下架
            inventoryRequest.setStorageId(0); //
            inventoryRequest.setIsInbound(Boolean.FALSE);
            inventoryRequest.setStorageLocationId(null); // 虚拟仓没有库位
            inventoryRestService.add(inventoryRequest);
            // 2、上架到实体仓
            inventoryRequest.setStorageId(StringUtils.hasText(request.getToStorageId()) ? Integer.valueOf(request.getToStorageId()) : null);
            inventoryRequest.setIsInbound(Boolean.TRUE);
            inventoryRequest.setStorageLocationId(request.getToLocationId());
            inventoryRestService.add(inventoryRequest);
        } else if (null != request.getFromLocationId() && null == request.getToLocationId()) {
            // 3、从实体仓下架
            inventoryRequest.setStorageId(StringUtils.hasText(request.getFromStorageId()) ? Integer.valueOf(request.getFromStorageId()) : null);
            inventoryRequest.setIsInbound(Boolean.FALSE);
            inventoryRequest.setStorageLocationId(request.getFromLocationId());
            inventoryRestService.add(inventoryRequest);
            // 4、上架到虚拟仓
            inventoryRequest.setStorageId(0); //
            inventoryRequest.setIsInbound(Boolean.TRUE);
            inventoryRequest.setStorageLocationId(null); // 虚拟仓没有库位
            inventoryRestService.add(inventoryRequest);
        } else if (null != request.getFromLocationId() && null != request.getToLocationId()) {
            // 5、从实体仓下架
            inventoryRequest.setStorageId(StringUtils.hasText(request.getFromStorageId()) ? Integer.valueOf(request.getFromStorageId()) : null);
            inventoryRequest.setIsInbound(Boolean.FALSE);
            inventoryRequest.setStorageLocationId(request.getFromLocationId());
            inventoryRestService.add(inventoryRequest);
            // 6、上架到实体仓
            inventoryRequest.setStorageId(StringUtils.hasText(request.getToStorageId()) ? Integer.valueOf(request.getToStorageId()) : null);
            inventoryRequest.setIsInbound(Boolean.TRUE);
            inventoryRequest.setStorageLocationId(request.getToLocationId()); // 虚拟仓没有库位
            inventoryRestService.add(inventoryRequest);
        }
    }

    public synchronized Boolean add(AddInventoryAllocateRequest request) {// TODO
                                                                          // 添加调用影响库存的接口
        // changeInvetory(request);
        checkAllocateNumber(request);
        int add = -1;
        String allocateNumber = getAllocateNumber(request.getBillsDate());
        request.setAllocateNumber(allocateNumber);
        add = jdbcAccessContext.execute(
            "INVENTORYALLOCATE.SQL_ADD_INVENTORYALLOCATE",
            new Object[] { request.getAllocateNumber(), request.getBillsDate(), request.getMaterialId(), request.getMaterialNo(), request.getBatchNumber(), request.getProductionDate(),
                    request.getExpirationDate(), request.getFromLocationId(), request.getFromLocationNo(), request.getFromStorageId(), request.getToLocationId(), request.getToLocationNo(),
                    (null == request.getToLocationId() && !StringUtils.hasText(request.getToLocationNo()) && !StringUtils.hasText(request.getToStorageId())) ? 0 : request.getToStorageId(),
                    request.getCounts(), request.getCreateBy() });
        if (-1 == add) {
            return Boolean.FALSE;
        }
        changeInvetory(request); // 影响库存变化的操作
        addOperationLog("调拨单", "新增调拨单，单据编号：[{0}]", allocateNumber, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    public Boolean checkAllocateNumber(final AddInventoryAllocateRequest request) {
        List<GetMaterialBatchResponse> list = jdbcAccessContext.find("INVENTORYALLOCATE.SQL_GET_STORAGELOCATION_WITH_MATERIALBATCH_BY_MATERIAL", new RowMapper<GetMaterialBatchResponse>() {
            @Override
            public GetMaterialBatchResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialBatchResponseWithGetMaterialBatchByStorageLocation(rs);
            }
        }, new Object[] { request.getMaterialId(), request.getMaterialNo() });
        Boolean allocateExistFlag = false;
        if ("0".equals(request.getFromStorageId())) {// 上架是从虚拟仓库上架,上架一定是走这个判断,下架一定不走这个判断
            for (GetMaterialBatchResponse tempResponse : list) {
                if (tempResponse.getStorageId().equals("0") && tempResponse.getBatchNumber().equals(request.getBatchNumber()) && tempResponse.getProductionDate().equals(request.getProductionDate())) {// 没有库位信息，所以单独拿出来
                    allocateExistFlag = true;
                    if (Double.valueOf(request.getCounts()) > Double.valueOf(tempResponse.getCounts())) {
                        throw new BusinessProcessException("系统中记录的需要操作的商品数量不足!");
                    }
                }
            }
        } else {
            for (GetMaterialBatchResponse tempResponse : list) {// 下架一定走这个判定,
                if (StringUtils.hasText(tempResponse.getStorageLocationId())) {// 一定不是从虚拟仓库下架,过滤掉为空的
                    if (tempResponse.getStorageLocationId().equals(request.getFromLocationId().toString()) && tempResponse.getBatchNumber().equals(request.getBatchNumber())
                        && tempResponse.getProductionDate().equals(request.getProductionDate())) {// 有库位信息,并且必须制定库位
                        allocateExistFlag = true;
                        if (Double.valueOf(request.getCounts()) > Double.valueOf(tempResponse.getCounts())) {
                            throw new BusinessProcessException("系统中记录的需要操作的商品数量不足!");
                        }
                    }
                }
            }
        }
        if (!allocateExistFlag) {
            throw new BusinessProcessException("系统中没有记录到需要操作的数据,或者商品数量为零!");
        }
        return allocateExistFlag;
    }

    private GetMaterialBatchResponse buildMaterialBatchResponseWithGetMaterialBatchByStorageLocation(ResultSet rs) throws SQLException {// 根据库位得到该库位所有有货商品的批次信息构建对象
        GetMaterialBatchResponse response = new GetMaterialBatchResponse();
        response.setMaterialNo(rs.getString("materialNo"));
        response.setExpirationDate(rs.getString("expirationDate"));
        response.setBatchNumber(rs.getString("batchNumber"));
        response.setCounts(rs.getString("counts"));
        response.setProductionDate(rs.getString("productionDate"));
        response.setStorageLocationId(rs.getString("storageLocationId"));
        response.setStorageLocationNo(rs.getString("locationNo"));
        response.setStorageLocationName(rs.getString("locationName"));
        if (rs.getString("storageId") == null) {// 如果没有仓库信息，那么就是在虚拟仓库中
            response.setStorageId("0");
        } else {
            response.setStorageId(rs.getString("storageId"));
        }
        return response;
    }

    private synchronized String getAllocateNumber(String billsDate) { // 生成单据编号
        if (!StringUtils.hasText(billsDate)) {
            throw new BusinessProcessException("调拨单日期不能为空!");
        }
        String allocateNumber = null;
        Integer lastInsertId = jdbcAccessContext.findIntegerWithoutSqlManager(
            "SELECT max(CAST(substr(allocate_number,10) as SIGNED)) from inventory_allocate where 1=? and left(allocate_number,9) = ?", new Object[] { 1, "T" + billsDate });
        lastInsertId = lastInsertId + 1;
        if (lastInsertId < 10) {
            allocateNumber = "T" + billsDate + "000" + lastInsertId.toString();
        } else if (lastInsertId < 100) {
            allocateNumber = "T" + billsDate + "00" + lastInsertId.toString();
        } else if (lastInsertId < 1000) {
            allocateNumber = "T" + billsDate + "0" + lastInsertId.toString();
        } else if (lastInsertId < 10000) {
            allocateNumber = "T" + billsDate + lastInsertId.toString();
        } else if (lastInsertId >= 9999) {
            throw new BusinessProcessException("当天生成太多库存调拨单,出现了问题，请联系管理员");
        }
        return allocateNumber;
    }
}
