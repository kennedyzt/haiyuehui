package com.siping.wms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.smartone.inventory.request.InventoryReceiptBatchDeleteRequest;
import com.siping.smartone.inventory.request.InventoryReceiptItemRequest;
import com.siping.smartone.inventory.request.InventoryReceiptRequest;
import com.siping.smartone.inventory.request.PdaReceiptOrderParam;
import com.siping.smartone.inventory.response.GetInventoryCheckItemResponseNew;
import com.siping.smartone.inventory.response.GetInventoryCheckResponseNew;
import com.siping.smartone.inventory.response.InventoryReceiptItemResponse;
import com.siping.smartone.inventory.response.InventoryReceiptResponse;
import com.siping.smartone.material.response.GetMaterialBatchResponse;

@Repository
public class PdaRestRepository extends BillsRepository {

    public PagingResponse<GetInventoryCheckResponseNew> getInventoryCheckList(PdaReceiptOrderParam request) {
        PagingResponse<GetInventoryCheckResponseNew> responseList = new PagingResponse<GetInventoryCheckResponseNew>();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParams = new ArrayList<Object>();
        StringBuilder totalRecordsSql = new StringBuilder();
        long begin = (request.getPageNo() - 1) * request.getPageSize();
        List<GetInventoryCheckResponseNew> response = jdbcAccessContext.findWithoutSqlManager(buildInventoryCheckGetListSql(request, begin, queryParam, totalRecordsSql, totalParams),
            new RowMapper<GetInventoryCheckResponseNew>() {
                @Override
                public GetInventoryCheckResponseNew mapRow(ResultSet rs, int arg1) throws SQLException {
                    return buildInventoryCheckResponse(rs);
                }
            }, queryParam.toArray());
        int totalRecords = jdbcAccessContext.findIntegerWithoutSqlManager(totalRecordsSql.toString(), totalParams.toArray());
        responseList.setRecords(response);
        responseList.setTotalRecords(totalRecords);
        return responseList;
    }

    private String buildInventoryCheckGetListSql(PdaReceiptOrderParam request, long begin, List<Object> queryParam, StringBuilder total, List<Object> totalParams) {
        Integer pageSize = request.getPageSize();
        Integer userid = request.getUserid();
        Integer status = request.getStatus();
        total
            .append("SELECT COUNT(DISTINCT ic.check_number) from inventory_check ic LEFT JOIN storage s on ic.storage_id = s.id LEFT JOIN storage_area sa on ic.storage_area_id = sa.id where ic.pc_pda_flag = 1 and ic.is_delete = 0");
        StringBuilder sql = new StringBuilder(
            "select ic.check_number checkNumber, ic.storage_id storageId, s.storage_name storageName, ic.storage_area_id storageAreaId, sa.area_name storageAreaName, ic.bills_date billsDate, ic.status from inventory_check ic LEFT JOIN storage s on ic.storage_id = s.id LEFT JOIN storage_area sa on ic.storage_area_id = sa.id where ic.pc_pda_flag = 1 and ic.is_delete = 0");

        sql.append(" and ic.operator_id = ? ");
        total.append(" and ic.operator_id = ? ");
        queryParam.add(userid);

        sql.append(" and ic.status = ? ");
        total.append(" and ic.status = ? ");
        queryParam.add(status);

        sql.append(" group by ic.check_number");
        totalParams.addAll(queryParam);
        queryParam.add(begin);
        queryParam.add(pageSize);
        sql.append(" order by ic.check_number desc");
        sql.append(" limit ?,? ");
        return sql.toString();
    }

    protected GetInventoryCheckResponseNew buildInventoryCheckResponse(ResultSet rs) throws SQLException {
        GetInventoryCheckResponseNew i = new GetInventoryCheckResponseNew();
        i.setCheckNumber(rs.getString("checkNumber"));
        i.setBillsDate(DateUtils.formatDate(rs.getDate("billsDate"), "yyyy-MM-dd"));
        i.setStorageId(rs.getString("storageId"));
        i.setStorageName(rs.getString("storageName"));
        i.setStorageAreaId(rs.getString("storageAreaId"));
        i.setStorageAreaName(rs.getString("storageAreaName"));
        i.setStatus(rs.getBoolean("status"));
        return i;
    }

    public PagingResponse<InventoryReceiptResponse> getList(PdaReceiptOrderParam request) {
        PagingResponse<InventoryReceiptResponse> responseList = new PagingResponse<InventoryReceiptResponse>();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParams = new ArrayList<Object>();
        StringBuilder totalRecordsSql = new StringBuilder();
        long begin = (request.getPageNo() - 1) * request.getPageSize();
        List<InventoryReceiptResponse> response = jdbcAccessContext.findWithoutSqlManager(buildGetListSql(request, begin, queryParam, totalRecordsSql, totalParams),
            new RowMapper<InventoryReceiptResponse>() {
                @Override
                public InventoryReceiptResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                    return buildInventoryReceiptResponse(rs);
                }
            }, queryParam.toArray());
        int totalRecords = jdbcAccessContext.findIntegerWithoutSqlManager(totalRecordsSql.toString(), totalParams.toArray());
        responseList.setRecords(response);
        responseList.setTotalRecords(totalRecords);
        return responseList;
    }

    protected InventoryReceiptResponse buildInventoryReceiptResponse(ResultSet rs) throws SQLException {
        InventoryReceiptResponse i = new InventoryReceiptResponse();
        i.setReceiptNumber(rs.getString("receiptNumber"));
        i.setBillsDate(DateUtils.formatDate(rs.getDate("billsDate"), "yyyy-MM-dd"));
        i.setInboundStorageId(rs.getInt("inboundStorageId"));
        i.setTotalPrice(rs.getDouble("totalPrice"));
        i.setOwner(rs.getInt("owner"));
        i.setOwnerName(rs.getString("ownerName"));
        i.setSummary(rs.getString("summary"));
        i.setConsignee(rs.getInt("consignee"));
        i.setStatus(rs.getInt("status"));
        return i;
    }

    // 查询sql语句拼接
    private String buildGetListSql(PdaReceiptOrderParam request, long begin, List<Object> queryParam, StringBuilder total, List<Object> totalParams) {
        Integer pageSize = request.getPageSize();
        Integer userid = request.getUserid();
        Integer status = request.getStatus();
        total
            .append("SELECT COUNT(DISTINCT i.receipt_number) from inventory_receipt i left join inventory_receipt_item inv on i.receipt_number=inv.receipt_number left join material m on inv.material_id=m.id where 1=1 ");
        StringBuilder sql = new StringBuilder(
            "SELECT i.receipt_number receiptNumber,i.consignee consignee,i.status status, i.inbound_storage_id inboundStorageId, i.summary, i.is_draft isDraft, i.total_price totalPrice, i.gift_price giftPrice, i.bills_date billsDate, i.create_date createDate, i.create_by createBy, i.auditor, i.audit_date auditDate, i.consignee owner,u.user_name ownerName,inv.material_id materialId, SUM(inv.counts) counts from inventory_receipt i left join inventory_receipt_item inv on i.receipt_number=inv.receipt_number left join material m on inv.material_id=m.id LEFT JOIN usr u on i.consignee = u.id where 1=1");

        sql.append(" and i.consignee = ? ");
        total.append(" and i.consignee = ? ");
        queryParam.add(userid);

        sql.append(" and i.status = ? ");
        total.append(" and i.status = ? ");
        queryParam.add(status);

        sql.append(" group by i.receipt_number");
        totalParams.addAll(queryParam);
        queryParam.add(begin);
        queryParam.add(pageSize);
        sql.append(" order by i.create_date desc");
        sql.append(" limit ?,? ");
        return sql.toString();
    }

    public InventoryReceiptResponse get(String receiptNumber) {
        InventoryReceiptResponse response = jdbcAccessContext.findUniqueResult("INVENTORYRECEIPT.SQL_GET_INVENTORYRECEIPT", new RowMapper<InventoryReceiptResponse>() {
            @Override
            public InventoryReceiptResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildInventoryReceiptResponse(rs);
            }
        }, new Object[] { receiptNumber });
        List<InventoryReceiptItemResponse> list = jdbcAccessContext.find("INVENTORYRECEIPT.SQL_GET_INVENTORYITEMRECEIPT", new RowMapper<InventoryReceiptItemResponse>() {
            @Override
            public InventoryReceiptItemResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildInventoryReceiptItemResponse(rs);
            }
        }, new Object[] { response.getReceiptNumber() });
        response.setInventoryReceiptItemResponses(list);
        return response;
    }

    private InventoryReceiptItemResponse buildInventoryReceiptItemResponse(ResultSet rs) throws SQLException {
        InventoryReceiptItemResponse i = new InventoryReceiptItemResponse();
        i.setId(rs.getInt("id"));
        i.setItemNo(rs.getString("itemNo"));
        i.setExpirationDate(DateUtils.formatDate(rs.getDate("expirationDate"), "yyyy-MM-dd"));
        i.setMaterialId(rs.getInt("materialId"));
        i.setRemark(rs.getString("remark"));
        i.setRowNumber(rs.getInt("rowNumber"));
        i.setSpecificationsModel(rs.getString("specificationsModel"));
        i.setBrand(rs.getString("brand"));
        i.setSeason(rs.getString("season"));
        i.setBarcode(rs.getString("barcode"));
        i.setBatchNumber(rs.getString("batchNumber"));
        i.setProductionDate(DateUtils.formatDate(rs.getDate("productionDate"), "yyyy-MM-dd"));
        i.setUnitName(rs.getString("unitName"));
        i.setReceiptCounts(rs.getDouble("receiptCounts"));
        i.setCounts(rs.getDouble("counts"));
        i.setTotal(rs.getDouble("total"));
        i.setIsGift(rs.getBoolean("isGift"));
        i.setMaterialName(rs.getString("materialName"));
        i.setPurchasePrice(rs.getDouble("purchasePrice"));
        i.setIsBatch(rs.getBoolean("isBatch"));
        i.setMaterialNo(rs.getString("materialNo"));
        i.setFromBillsNo(rs.getString("fromBillsNo"));
        try {
            i.setStatus(rs.getInt("status"));
        } catch (Exception e) {
        }
        return i;
    }

    // public Boolean add(InventoryReceiptRequest request) {
    // checkPostPeriodDateTime();
    // String receiptNumber = generateBillsNumber("purchase_order",
    // "order_number");
    // if (!request.getReceiptNumber().equals(receiptNumber)) {
    // request.setReceiptNumber(receiptNumber);
    // }
    // Object[] addTitleParams = new Object[] { request.getReceiptNumber(),
    // request.getInboundStorageId(), request.getSummary(),
    // request.getIsDraft(), request.getTotalPrice(), request.getGiftPrice(),
    // request.getBillsDate(), request.getAuditor(), request.getOwner(),
    // request.getCreateBy(),request.getConsignee(),request.getStatus()};
    // // 插入表头数据
    // int add = 1;
    // try {
    // add =
    // jdbcAccessContext.execute("INVENTORYRECEIPT.SQL_ADD_INVENTORYRECEIPT_BYPDA",
    // addTitleParams);
    // } catch (Exception e) {
    // e.printStackTrace();
    // throw new BusinessProcessException("新增采购订单失败");
    // }
    // if (-1 != add) {
    // if (request.getInventoryReceiptItemRequests() == null ||
    // request.getInventoryReceiptItemRequests().isEmpty()) {
    // throw new BusinessProcessException("缺少表体信息");
    // }
    // // 插入表体数据
    // int[] adds = addOrderItems(request);
    // if (Arrays.asList(adds).contains(-1)) {
    // throw new BusinessProcessException("新增库存收货单项失败!");
    // }
    // return Boolean.TRUE;
    // }
    // return Boolean.FALSE;
    // }

    // private int[] addOrderItems(InventoryReceiptRequest request) {
    // List<InventoryReceiptItemRequest> list =
    // request.getInventoryReceiptItemRequests();
    // List<Object[]> addParamsList = new ArrayList<Object[]>();
    // for (InventoryReceiptItemRequest item : list) {
    // Object[] addTableParams = new Object[] { item.getReceiptNumber(),
    // item.getMaterialId(), item.getBatchNumber(), item.getProductionDate() ==
    // "" ? null : item.getProductionDate(), item.getCounts(),
    // item.getPurchasePrice(),
    // item.getTotal(), item.getIsGift(), item.getRemark(), item.getCreateBy()
    // };
    // addParamsList.add(addTableParams);
    // if(request.getIsDraft() == false){
    // changeInvetory(request, item);
    // }
    // }
    // // 插入表体数据
    // int[] adds =
    // jdbcAccessContext.batchExecute("INVENTORYRECEIPT.SQL_ADD_INVENTORYRECEIPTITEM",
    // addParamsList);
    // return adds;
    // }

    private void changeInvetory(InventoryReceiptRequest request, InventoryReceiptItemRequest item) {
        // 涉及到库存的改变的操作在这里进行，如果没有可以删除掉这个方法
    }

    public Boolean delete(InventoryReceiptBatchDeleteRequest request) {
        List<String> receiptNumbers = request.getReceiptNumbers();
        List<Object[]> deleteParams = new LinkedList<Object[]>();
        for (String str : receiptNumbers) {
            deleteParams.add(new Object[] { str });
        }
        int[] delete = jdbcAccessContext.batchExecute("INVENTORYRECEIPT.SQL_DELETE_INVENTORYRECEIPT", deleteParams);
        if (Arrays.asList(delete).contains(-1)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean addItem(InventoryReceiptItemResponse request) {
        int flag = 0;
        Object[] params = new Object[] { request.getMaterialId(), request.getReceiptNumber(), request.getReceiptCounts(), request.getBatchNumber(), request.getProductionDate(),
                request.getExpirationDate(), request.getRowNumber(), request.getFromBillsNo(), request.getPurchasePrice(), request.getCounts() };
        //根据参数判断是否为该收货单中该商品的首次收货
        List<InventoryReceiptItemResponse> response = jdbcAccessContext.find("INVENTORYRECEIPT.SQL_INVENTORYRECIPTITEM_GET_BATCHNUMBER",
            new RowMapper<InventoryReceiptItemResponse>() {
                @Override
                public InventoryReceiptItemResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                    return buildInventoryReceiptItemBatchNumberResponse(rs);
                }
        }, new Object[] { request.getBarcode(), request.getReceiptNumber()});
        //如果只有一条数据且batchNumber为空，表示首次收货，对收货单明细中，该行商品更新相关信息
        if (response.size() == 1 && (null == response.get(0).getBatchNumber())) {
            request.setId(response.get(0).getId());
            edit(request);
        } else {
            //否则，对直接插入数据到收货单明细列表
            flag = jdbcAccessContext.execute("INVENTORYRECEIPT.SQL_ADD_INVENTORYRECIPTITEM_BYPDA", params);
        }

        if (-1 == flag) {
            return Boolean.FALSE;
        } else {
            addOperationLog("库存收货单", "新增库存收货单，单据编号：[{0}]", request.getReceiptNumber(), request.getCreateBy() + "");
            return Boolean.TRUE;
        }
    }

    public Boolean edit(InventoryReceiptItemResponse request) {
        Object[] params = new Object[] { request.getReceiptCounts(), request.getBatchNumber(), request.getProductionDate(), request.getExpirationDate(), request.getRowNumber(), request.getId(), };
        // 编辑表头表尾信息
        int flag = jdbcAccessContext.execute("INVENTORYRECEIPT.SQL_UPDATE_INVENTORYRECIPTITEM_BYPDA", params);

        if (-1 == flag) {
            return Boolean.FALSE;
        } else {
            addOperationLog("库存收货单", "修改库存收货单，单据编号：[{0}]", request.getReceiptNumber(), request.getCreateBy() + "");
            return Boolean.TRUE;
        }
    }

     public Boolean edit1(InventoryReceiptItemResponse request) {
         int actionFlag = 0;
         int flag = 0;
         List<InventoryReceiptItemResponse> response = jdbcAccessContext.find("INVENTORYRECEIPT.SQL_SELECT_INVENTORYRECIPTITEM_STATUS_BY_ID_BATCHNUMBER_PRODUCTIONDATE",
             new RowMapper<InventoryReceiptItemResponse>() {
                 @Override
                 public InventoryReceiptItemResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                     return buildInventoryReceiptItemBatchNumberResponse(rs);
                 }
         }, new Object[] { request.getId(), request.getBatchNumber(), request.getProductionDate() });
         if (response.size() == 0) {
             List<InventoryReceiptItemResponse> responseSupport = new ArrayList<InventoryReceiptItemResponse>();
             //判断所收货物条目是不是没有批次和生产日期,如果是的话,那就用修改不用新增
             responseSupport = jdbcAccessContext.find("INVENTORYRECEIPT.SQL_SELECT_INVENTORYRECIPTITEM_STATUS_BY_ID_ONLY",
                 new RowMapper<InventoryReceiptItemResponse>() {
                     @Override
                     public InventoryReceiptItemResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                         return buildInventoryReceiptItemStatusWithBatchNumberResponse(rs);
                     }
             }, new Object[] { request.getId() });
             if (responseSupport == null) {
                 throw new BusinessProcessException("出现了错误");
             }
             if (!StringUtils.hasText(responseSupport.get(0).getBatchNumber()) &&
                 !StringUtils.hasText(responseSupport.get(0).getProductionDate())) {
                 actionFlag = 1;
             } else {
                 actionFlag = 0;
             }
         }
         if (response.size() == 1) {
         // if(!StringUtils.hasText(response.get(0).getBatchNumber())){
         // actionFlag = 1;
         // }
         // else {
             if (response.get(0).getStatus() == 1) {
                 actionFlag = 0;
             } else {
                 actionFlag = 1;
             }
     // }
         }
         if (response.size() > 1) {
             throw new BusinessProcessException("收货数据出现错误");
         }
         if (actionFlag == 1) {// 首先查询状态
             Object[] params = new Object[] { request.getReceiptCounts(),
                 request.getBatchNumber(), request.getProductionDate(),
                 request.getExpirationDate(), request.getId(), };
             flag = jdbcAccessContext.execute("INVENTORYRECEIPT.SQL_UPDATE_INVENTORYRECIPTITEM_BYPDA",params);// 状态为0，直接修改
         } else {
             Object[] params = new Object[] { request.getMaterialId(),
                 request.getReceiptNumber(), request.getReceiptCounts(),
                 request.getBatchNumber(), request.getProductionDate(),
                 request.getExpirationDate(), request.getRowNumber(),
                 request.getFromBillsNo(), request.getPurchasePrice(), request.getCounts()
             };
             // 编辑表头表尾信息
             flag =
             jdbcAccessContext.execute("INVENTORYRECEIPT.SQL_ADD_INVENTORYRECIPTITEM_BYPDA",
             params);// 都是用新增
         }
         if (-1 == flag) {
             return Boolean.FALSE;
         } else {
             addOperationLog("库存收货单", "新增库存收货单，单据编号：[{0}]",
                 request.getReceiptNumber(), request.getCreateBy() + "");
             return Boolean.TRUE;
         }
     }

    private InventoryReceiptItemResponse buildInventoryReceiptItemBatchNumberResponse(ResultSet rs) throws SQLException {
        InventoryReceiptItemResponse response = new InventoryReceiptItemResponse();
        response.setId(rs.getInt("id"));
        response.setBatchNumber(rs.getString("batchNumber"));
        return response;
    }

    private InventoryReceiptItemResponse buildInventoryReceiptItemStatusWithBatchNumberResponse(ResultSet rs) throws SQLException {
        InventoryReceiptItemResponse response = new InventoryReceiptItemResponse();
        response.setStatus(rs.getInt("status"));
        response.setProductionDate(rs.getString("productionDate"));
        response.setBatchNumber(rs.getString("batchNumber"));
        return response;
    }

    public List<GetMaterialBatchResponse> getMaterialBatchByStorageLocation(String locationId) {
        List<GetMaterialBatchResponse> list = jdbcAccessContext.find("INVENTORYALLOCATE.SQL_GET_MATERIALBATCHS_BY_STORAGE_LOCATION", new RowMapper<GetMaterialBatchResponse>() {
            @Override
            public GetMaterialBatchResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialBatchResponseForGetMaterialBatchByStorageLocation(rs);
            }
        }, new Object[] { locationId, locationId });
        return list;
    }

    private GetMaterialBatchResponse buildMaterialBatchResponseForGetMaterialBatchByStorageLocation(ResultSet rs) throws SQLException {// 根据库位得到该库位所有有货商品的批次信息构建对象
        GetMaterialBatchResponse response = new GetMaterialBatchResponse();
        response.setBarcode(rs.getString("barcode"));
        response.setMaterialNo(rs.getString("materialNo"));
        response.setMaterialName(rs.getString("materialName"));
        response.setExpirationDate(rs.getString("expirationDate"));
        response.setBatchNumber(rs.getString("batchNumber"));
        response.setCounts(rs.getString("counts"));
        response.setProductionDate(rs.getString("productionDate"));
        response.setMaterialId(rs.getString("materialId"));
        response.setStorageLocationId(rs.getString("storageLocationId"));
        response.setStorageLocationNo(rs.getString("locationNo"));
        response.setStorageLocationName(rs.getString("locationName"));
        return response;
    }

    public List<GetMaterialBatchResponse> getStorageLocationWithMaterialBatchByMaterial(String materialId) {
        List<GetMaterialBatchResponse> list = jdbcAccessContext.find("INVENTORYALLOCATE.SQL_GET_STORAGELOCATION_WITH_MATERIALBATCH_BY_MATERIAL", new RowMapper<GetMaterialBatchResponse>() {
            @Override
            public GetMaterialBatchResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialBatchResponseForGetStorageLocationWithMaterialBatchByMaterial(rs);
            }
        }, new Object[] { materialId, materialId });
        return list;
    }

    private GetMaterialBatchResponse buildMaterialBatchResponseForGetStorageLocationWithMaterialBatchByMaterial(ResultSet rs) throws SQLException {// 根据商品得到该商品在所有有货商品的批次信息以及所在库位构建对象
        GetMaterialBatchResponse response = new GetMaterialBatchResponse();
        response.setBarcode(rs.getString("barcode"));
        response.setMaterialId(rs.getString("materialId"));
        response.setMaterialNo(rs.getString("materialNo"));
        response.setMaterialName(rs.getString("materialName"));
        response.setBatchNumber(rs.getString("batchNumber"));
        response.setCounts(rs.getString("counts"));
        response.setProductionDate(rs.getString("productionDate"));
        response.setExpirationDate(rs.getString("expirationDate"));
        response.setStorageLocationNo(rs.getString("locationNo"));
        response.setStorageLocationName(rs.getString("locationName"));
        if (rs.getString("storageId") == null) {// 如果没有仓库信息，那么就是在虚拟仓库中
            response.setStorageId("0");
        } else {
            response.setStorageId(rs.getString("storageId"));
        }
        try {
            response.setPrice(rs.getString("price"));// 这个数据是对耗材有用的，批次表中过滤掉了出库的价格对该批次耗材的影响
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public Boolean commitInventoryCheck(GetInventoryCheckResponseNew request) {
        String checkNumber = request.getCheckNumber();
        GetInventoryCheckResponseNew newIC = getInventoryCheck(checkNumber);
        newIC.setItems(request.getItems());
        List<GetInventoryCheckItemResponseNew> list = request.getItems();
        List<Object[]> addParamsList = new ArrayList<Object[]>();

        // 更改表状态
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int update = jdbcAccessContext.execute("INVENTORYCHECK.SQL_UPDATE_INVENTORYCHECK", new Object[] { sdf.format(new Date()), new Integer(1), request.getSummary(), request.getCheckNumber() });

        for (GetInventoryCheckItemResponseNew item : list) {
            List<GetMaterialBatchResponse> materialList = getMaterialBatchByStorageLocation(item.getLocationNo());
            Object[] addTableParams = new Object[] { checkNumber, item.getMaterialId(), item.getMaterialNo(), item.getMaterialName(), item.getBarcode(), "", "", item.getLocationNo(),
                    item.getBatchNumber(), "", "", 0, item.getActualNumber(), item.getActualNumber() };
            for (GetMaterialBatchResponse ms : materialList) {
                if (ms.getBatchNumber().equals(item.getBatchNumber()) && ms.getMaterialId().equals(item.getMaterialId())) {
                    addTableParams[5] = ms.getUnitName();
                    addTableParams[9] = ms.getProductionDate();
                    addTableParams[10] = ms.getExpirationDate();
                    addTableParams[11] = ms.getCounts();
                    addTableParams[13] = Integer.parseInt(item.getActualNumber()) - Integer.parseInt(ms.getCounts());
                    break;
                }
            }
            addParamsList.add(addTableParams);
        }
        // 插入表体数据
        int[] adds = jdbcAccessContext.batchExecute("INVENTORYCHECK.SQL_ADD_INVENTORYCHECKITEMS_NEW", addParamsList);
        return Boolean.TRUE;
    }

    public GetInventoryCheckResponseNew getInventoryCheck(String checkNumber) {
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
                    checkItem.setLocationName(rs.getString("location_name"));
                    return checkItem;
                }
            }, itemQueryParam.toArray());
        if (!CollectionUtils.isEmpty(list)) {
            list.get(0).setItems(checkItems);
            return list.get(0);
        }
        return null;
    }

    public String getRowNumber(String batchNumber, int materialId) {
        Integer rowNumber = jdbcAccessContext
            .findIntegerWithoutSqlManager("SELECT id from inventory_receipt_item where batch_number = ? AND material_id= ? ", new Object[] { batchNumber, materialId });
        return rowNumber.toString();
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
            "SELECT i.id,i.check_number,i.material_id,i.diff_number,i.actual_number,i.inventory_number,i.location_no,sl.location_name,i.unit_name,i.material_type_name,i.barcode,i.material_name,i.material_no,i.batch_number,i.production_date,i.expiration_date FROM inventory_check_item i LEFT JOIN storage_location sl on i.location_no = sl.location_no");
        if (StringUtils.hasText(checkNumber)) {
            sql.append(" where i.check_number=? ");
            queryParam.add(checkNumber);
        }
        return sql.toString();
    }
}
