package com.siping.service.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.material.service.MaterialRestService;
import com.siping.service.postperiod.repository.PostPeriodRestRepository;
import com.siping.service.storage.service.StorageRestService;
import com.siping.smartone.inventory.request.GetMaterialBatchNumberListRequest;
import com.siping.smartone.inventory.response.GetMaterialBatchNumberResponse;
import com.siping.smartone.inventory.response.GetInventoryWarningResponse;
import com.siping.smartone.postperiod.response.GetPostPeriodResponse;

@Repository
public class BillsRepository extends CommonRepository { // 需要生成单据编号的Repository才需要继承此类
    @Autowired
    private PostPeriodRestRepository postPeriodRestRepository;
    @Autowired
    private MaterialRestService materialRestService;
    @Autowired
    private StorageRestService storageRestService;

    private final String PA = "PA"; // 采购申请单
    private final String PO = "PO"; // 采购订单
    private final String PD = "PD"; // 采购收货单
    private final String PR = "PR"; // 采购退货单
    private final String SO = "SO"; // 销售订单
    private final String DN = "DN"; // 销售发货单
    private final String RE = "RE"; // 销售退货单
    private final String MI = "MI"; // 库存收货单
    private final String MO = "MO"; // 库存发货单
    private final String MC = "MC"; // 库存盘点单
    private final String PS = "PS"; // 预发货单
    private final String OP = "OP"; // 拣货单

    private final String START_DATE = "yyyy-MM-dd 00:00:00";
    private final String END_DATE = "yyyy-MM-dd 23:59:59";
    private final String DATE = "yyyy-MM-dd";

    protected synchronized String generateBillsNumber(String tableName, String primaryKeyName) { // 生成单据编号
        if (!StringUtils.hasText(tableName) && !StringUtils.hasText(primaryKeyName)) {
            throw new BusinessProcessException("表名和主键名不能为空!");
        }
        Integer lastInsertId = jdbcAccessContext.findIntegerWithoutSqlManager("SELECT max(CAST(substr( " + primaryKeyName + " ,3) as SIGNED)) from " + tableName + " where 1=?", new Object[] { 1 });
        return generatePrimaryKey(tableName, lastInsertId);
    }

    private String generatePrimaryKey(String tableName, Integer lastInsertId) {
        String number = "1";
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyMMdd");
        String time = format.format(date);
        if ("purchase_apply_order".equalsIgnoreCase(tableName)) {
            return PA.concat(lastInsertId == null || lastInsertId == 0 ? number : (lastInsertId + 1) + "");
        }
        if ("purchase_order".equalsIgnoreCase(tableName)) {
            return PO.concat(lastInsertId == null || lastInsertId == 0 ? number : (lastInsertId + 1) + "");
        }
        if ("purchase_receipt".equalsIgnoreCase(tableName)) {
            return PD.concat(lastInsertId == null || lastInsertId == 0 ? number : (lastInsertId + 1) + "");
        }
        if ("purchase_returns".equalsIgnoreCase(tableName)) {
            return PR.concat(lastInsertId == null || lastInsertId == 0 ? number : (lastInsertId + 1) + "");
        }
        if ("sell_order".equalsIgnoreCase(tableName)) {
            return SO.concat(lastInsertId == null || lastInsertId == 0 ? number : (lastInsertId + 1) + "");
        }
        if ("sell_shipments".equalsIgnoreCase(tableName)) {
            return DN.concat(lastInsertId == null || lastInsertId == 0 ? number : (lastInsertId + 1) + "");
        }
        if ("sell_returns".equalsIgnoreCase(tableName)) {
            return RE.concat(lastInsertId == null || lastInsertId == 0 ? number : (lastInsertId + 1) + "");
        }
        if ("inventory_receipt".equalsIgnoreCase(tableName)) {
            return MI.concat(lastInsertId == null || lastInsertId == 0 ? number : (lastInsertId + 1) + "");
        }
        if ("inventory_shipments".equalsIgnoreCase(tableName)) {
            return MO.concat(lastInsertId == null || lastInsertId == 0 ? number : (lastInsertId + 1) + "");
        }
        if ("inventory_check".equalsIgnoreCase(tableName)) {
            return MC.concat(lastInsertId == null || lastInsertId == 0 ? number : (lastInsertId + 1) + "");
        }
        if ("ready_shipments".equalsIgnoreCase(tableName)) {
            String lastInsertIdStr = lastInsertId == 0 ? "0000000000" : lastInsertId.toString();
            lastInsertId = Integer.parseInt(lastInsertIdStr.substring(6));
            String str = String.format("%04d", lastInsertId + 1);
            return PS.concat(time + str + "");
        }
        if ("order_pick".equalsIgnoreCase(tableName)) {
            String lastInsertIdStr = lastInsertId == 0 ? "0000000000" : lastInsertId.toString();
            lastInsertId = Integer.parseInt(lastInsertIdStr.substring(6));
            String str = String.format("%04d", lastInsertId + 1);
            return OP.concat(time + str + "");
        }
        return null;
    }

    public String parseDate(String date, Boolean isEnd) {
        Date forMateDate = strTODate(date);
        SimpleDateFormat sp = new SimpleDateFormat(START_DATE);
        SimpleDateFormat lastSp = new SimpleDateFormat(END_DATE);
        if (isEnd)
            return lastSp.format(forMateDate);
        else
            return sp.format(forMateDate);
    }

    private Date strTODate(String date) {
        SimpleDateFormat now = new SimpleDateFormat(DATE);
        Date parse;
        try {
            parse = now.parse(date);
        } catch (Exception e) {
            parse = null;
        }
        return parse;
    }

    public void checkPostPeriodDateTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE);
        String nowTime = df.format(new Date());
        GetPostPeriodResponse response = postPeriodRestRepository.getByDateTime(nowTime);
        if (null == response) {
            throw new BusinessProcessException("当前日期不再过账期间中，无法添加单据");
        } else if (!response.getPostPeriodFlag()) {
            throw new BusinessProcessException("当前日期所在过账期间为锁定状态,无法添加单据");
        }
    }

    // 以下两个方法是在库存日志中查询批次号信息
    public List<GetMaterialBatchNumberResponse> getMaterialBatchNumberList(GetMaterialBatchNumberListRequest request) {
        List<Object> queryParam = new ArrayList<Object>();
        queryParam.add(request.getMaterialId());
        queryParam.add(request.getStorageId());
        List<GetMaterialBatchNumberResponse> filterResponses = new ArrayList<GetMaterialBatchNumberResponse>();
        List<GetMaterialBatchNumberResponse> responses = new ArrayList<GetMaterialBatchNumberResponse>();
        // StringBuilder querySql = new StringBuilder("SELECT
        // c.check_number,c.check_storage,c.summary,c.is_draft,c.is_loss,c.total_price,c.gift_price,c.create_date,c.create_by,c.auditor,c.aodit_date,c.owner
        // from inventory_check c");
        responses = jdbcAccessContext.find("INVENTORYCHECK.SQL_FIND_BATCH_NUMBER_WITH_MATERIALID_AND_STORAGEID", new RowMapper<GetMaterialBatchNumberResponse>() {
            @Override
            public GetMaterialBatchNumberResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildGetInventoryCheckMaterialBatchNumberResponse(rs);
            }
        }, queryParam.toArray());
        for (GetMaterialBatchNumberResponse response : responses) {// 检验仓库为零的，就不盘点了
            if (!response.getSmNumber().equals("0")) {
                filterResponses.add(response);
            }
        }
        return filterResponses;
    }

    private GetMaterialBatchNumberResponse buildGetInventoryCheckMaterialBatchNumberResponse(final ResultSet rs) throws SQLException {
        GetMaterialBatchNumberResponse response = new GetMaterialBatchNumberResponse();
        response.setMaterialId(rs.getString("materialId"));
        response.setMaterialNo(rs.getString("materialNo"));
        response.setMaterialName(rs.getString("materialName"));
        response.setBarcode(rs.getString("barcode"));
        response.setSpecificationsModel(rs.getString("SpecificationsModel"));
        response.setUnitName(rs.getString("unitName"));
        response.setInventoryId(rs.getString("inventoryId"));
        response.setBatchNumber(rs.getString("batchNumber"));
        response.setSmNumber(rs.getString("smNumber"));
        response.setProductionDate(rs.getString("productionDate"));
        response.setPrice(rs.getDouble("price"));
        response.setCounts(rs.getDouble("smNumber"));
        response.setExpirationDate(rs.getString("expirationDate"));
        return response;
    }

    public void checkInvnetoryNumber(String materialId, String storageId, String number) {
        String inventoryNumber = null;
        Object[] queryParam = new Object[] { materialId, storageId };
        try {
            inventoryNumber = jdbcAccessContext.findString("INVENTORY.SQL_FIND_INVENTORY_NUMBER_WITH_MATERIALID_AND_STORAGEID", queryParam);
        } catch (Exception e) {
            throw new BusinessProcessException("商品'" + materialRestService.get(materialId).getMaterialName() + "'在此仓库中没有库存量！不能出库");
        }
        if (Double.valueOf(inventoryNumber) < Double.valueOf(number)) {
            throw new BusinessProcessException("商品'" + materialRestService.get(materialId).getMaterialName() + "'出库量大于库存量!不能出库");
        }
    }

    public void checkInventoryMaxMinNumber(String materialId, String storageId, String number, Boolean isIn)// 0代表出库，1代表入库
    {
        Object[] queryParam = new Object[] { materialId, storageId };
        List<GetInventoryWarningResponse> inventoryWarnings = null;
        inventoryWarnings = jdbcAccessContext.find("INVENTORYCHECK.SQL_FIND_BATCH_NUMBER_WITH_MATERIALID_AND_STORAGEID", new RowMapper<GetInventoryWarningResponse>() {
            @Override
            public GetInventoryWarningResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                GetInventoryWarningResponse inventoryWarning = new GetInventoryWarningResponse();
                inventoryWarning.setMinInventory(rs.getString("min_inventory"));
                inventoryWarning.setMaxInventory(rs.getString("max_inventory"));
                return inventoryWarning;
            }
        }, queryParam);
        if (inventoryWarnings.size() > 1) {
            throw new BusinessProcessException("商品'" + materialRestService.get(materialId).getMaterialName() + "'不应该存在两个库存警戒，请联系管理员修改");
        }
        String inventoryNumber = null;
        if (inventoryWarnings.size() == 1) {
            try {
                inventoryNumber = jdbcAccessContext.findString("INVENTORY.SQL_FIND_INVENTORY_NUMBER_WITH_MATERIALID_AND_STORAGEID", queryParam);
                if (isIn) {// 入库
                    if (Double.valueOf(inventoryNumber) + Double.valueOf(number) >= Double.valueOf(inventoryWarnings.get(0).getMaxInventory())) {
                        throw new BusinessProcessException("商品'" + materialRestService.get(materialId).getMaterialName() + "'库存量在仓库'" + storageRestService.get(storageId).getStorageName()
                            + "'超过库存警戒，是否继续入库?");
                    }
                } else {
                    if (Double.valueOf(inventoryNumber) - Double.valueOf(number) <= Double.valueOf(inventoryWarnings.get(0).getMinInventory())) {
                        throw new BusinessProcessException("商品'" + materialRestService.get(materialId).getMaterialName() + "'库存量在仓库'" + storageRestService.get(storageId).getStorageName()
                            + "'小于库存警戒，是否继续出库?");
                    }
                }
            } catch (Exception e) {
                // 正常情况下，捕获到异常代表没有库存在库存表中生成。
                if (isIn) {// 入库
                    if (Double.valueOf(number) >= Double.valueOf(inventoryWarnings.get(0).getMaxInventory())) {
                        throw new BusinessProcessException("商品'" + materialRestService.get(materialId).getMaterialName() + "'达到库存警戒，是否继续入库?");
                    }
                } else {// 出库

                }
            }
        }
    }
}
