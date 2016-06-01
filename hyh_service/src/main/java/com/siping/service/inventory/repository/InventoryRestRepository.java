package com.siping.service.inventory.repository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.smartone.inventory.request.MaterialCountsRequest;
import com.siping.smartone.inventory.response.InventoryAmountResponse;
import com.siping.smartone.inventory.response.InventoryWarningMsg;
import com.siping.smartone.inventory.response.MaxInventoryResponse;
import com.siping.smartone.invoicing.inventory.inventory.request.AddInventoryRequest;
import com.siping.smartone.invoicing.inventory.inventory.request.GetInventoryIsEnoughRequest;
import com.siping.smartone.invoicing.inventory.inventory.response.GetInventoryLogResponse;
import com.siping.smartone.invoicing.inventory.inventory.response.GetInventoryResponse;

@Repository
public class InventoryRestRepository extends BillsRepository { // 需要生成单据编号的Repository才需要继承BillsRepository类

    public Boolean add(final AddInventoryRequest request) { // 调用存储过程
        Integer executeProcedure = jdbcAccessContext.executeProcedureWithoutSqlManager("call add_inventory(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new CallableStatementCallback<Integer>() {
            public Integer doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                cs.setInt(1, request.getMaterialId());
                cs.setInt(2, request.getStorageId());
                if (null == request.getStorageLocationId()) {
                    cs.setString(3, null);
                } else {
                    cs.setString(3, request.getStorageLocationId() + "");
                }
                cs.setBoolean(4, request.getIsInbound());
                cs.setString(5, request.getBillsNo());
                cs.setString(6, request.getBillsDate());
                cs.setString(7, !StringUtils.hasText(request.getBatchNumber()) ? "" : request.getBatchNumber());
                if (StringUtils.hasText(request.getProductionDate())) {
                    cs.setString(8, request.getProductionDate().substring(0, 10));
                } else {
                    cs.setString(8, null);
                }
                if (StringUtils.hasText(request.getExpirationDate())) {
                    cs.setString(9, request.getExpirationDate().substring(0, 10));
                } else {
                    cs.setString(9, null);
                }
                cs.setDouble(10, null == request.getCounts() ? 0 : Double.valueOf(request.getCounts()));
                cs.setDouble(11, null == request.getPurchasePrice() ? 0.0 : request.getPurchasePrice());
                cs.setDouble(12, null == request.getSellPrice() ? 0.0 : request.getSellPrice());
                cs.setDouble(13, null == request.getTotal() ? 0 : request.getTotal());
                cs.setString(14, null == request.getCreateBy() ? null : String.valueOf(request.getCreateBy()));
                cs.registerOutParameter(15, Types.INTEGER); // 表示返回值为Integer
                cs.execute(); // 执行存储过程
                return Integer.valueOf(cs.getObject(15).toString());
            }
        });
        if (null == executeProcedure || (null != executeProcedure && -1 == executeProcedure))
            return Boolean.FALSE;
        else if (null != executeProcedure && 0 == executeProcedure)
            throw new BusinessProcessException("出库数大于库存量，无法出库！");
        return Boolean.TRUE;
    }

    public Boolean edit(AddInventoryRequest request) {
        return add(request);
    }

    public GetInventoryResponse get(String id) {// 获取单个库存
        GetInventoryResponse inventory = getInventory(id);
        if (null == inventory) {
            throw new BusinessProcessException("库存不存在！");
        }
        List<GetInventoryLogResponse> logList = jdbcAccessContext.find("INVENTORY.SQL_GET_INVENTORY_LOG_ITEMS", new RowMapper<GetInventoryLogResponse>() {
            @Override
            public GetInventoryLogResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildInventoryLog(paramResultSet);
            }
        }, new Object[] { id });
        if (null == logList && CollectionUtils.isEmpty(logList)) {
            throw new BusinessProcessException("没有找到库存日志！");
        }
        inventory.setLogList(logList);
        return inventory;
    }

    private GetInventoryResponse getInventory(String id) {
        return jdbcAccessContext.findUniqueResult("INVENTORY.SQL_GET_INVENTORY", new RowMapper<GetInventoryResponse>() {
            @Override
            public GetInventoryResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildInventoryResponse(paramResultSet);
            }
        }, new Object[] { id });
    }

    private GetInventoryResponse buildInventoryResponse(ResultSet rs) throws SQLException {
        GetInventoryResponse response = new GetInventoryResponse();
        response.setId(rs.getInt("id"));
        response.setMaterialId(rs.getInt("material_id"));
        response.setMaterialName(rs.getString("material_name"));
        response.setStorageId(rs.getInt("storage_id"));
        response.setStorageName(rs.getString("storage_name"));
        response.setInboundAmount(rs.getInt("inbound_amount"));
        response.setAvailableAmount(rs.getInt("available_amount"));
        response.setCreateDate(rs.getString("create_date"));
        response.setCreateBy(rs.getInt("create_by"));
        response.setUpdateDate(rs.getString("update_date"));
        response.setUpdateBy(rs.getInt("update_by"));
        return response;
    }

    private GetInventoryLogResponse buildInventoryLog(ResultSet rs) throws SQLException {
        GetInventoryLogResponse r = new GetInventoryLogResponse();
        r.setId(rs.getInt("id"));
        r.setInventoryId(rs.getInt("inventory_id"));
        // r.setFlowId(rs.getString("flow_id"));
        r.setIsInbound(rs.getBoolean("is_inbound"));
        r.setFromBillsId(rs.getString("from_bills_id"));
        r.setBatchNumber(rs.getString("batch_number"));
        r.setProductionDate(rs.getString("production_date"));
        r.setCounts(rs.getInt("counts"));
        r.setPurchasePrice(rs.getDouble("purchase_price"));
        r.setSellPrice(rs.getDouble("sell_price"));
        r.setTotal(rs.getDouble("total"));
        r.setCreateDate(rs.getString("create_date"));
        r.setCreateBy(rs.getInt("create_by"));
        return r;
    }

    public List<GetInventoryLogResponse> getList(String materialId, String storageId) {
        List<GetInventoryLogResponse> response = jdbcAccessContext.find("INVENTORY.SQL_GET_INVENTORY_LOGS", new RowMapper<GetInventoryLogResponse>() {
            @Override
            public GetInventoryLogResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildInventoryLog(paramResultSet);
            }
        }, new Object[] { materialId, storageId });
        return response;
    }

    public Boolean isEnough(GetInventoryIsEnoughRequest request) {
        List<Object> queryParams = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder(
            " SELECT count(1) from haiyuehui.inventory i LEFT JOIN inventory_log l ON i.id=l.inventory_id where i.material_id=? AND i.storage_id=? AND (i.available_amount>? or i.available_amount=?)  ");
        queryParams.add(request.getMaterialId());
        queryParams.add(request.getStorageId());
        queryParams.add(request.getCounts());
        queryParams.add(request.getCounts());
        if (StringUtils.hasText(request.getProductionDate())) {
            sql.append(" AND l.production_date=? ");
            queryParams.add(request.getProductionDate());
        }
        if (StringUtils.hasText(request.getBatchNumber())) {
            sql.append(" AND l.batch_number=? ");
            queryParams.add(request.getBatchNumber());
        }
        Integer exist = jdbcAccessContext.findIntegerWithoutSqlManager(sql.toString(), queryParams.toArray());
        if (null != exist && exist > 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public InventoryWarningMsg isMoreThan(List<MaterialCountsRequest> request) {
        InventoryWarningMsg inventoryWarningMsg = new InventoryWarningMsg();
        inventoryWarningMsg.setIsMoreThan(false);
        for (MaterialCountsRequest m : request) {
            MaxInventoryResponse maxInventoryResponse = jdbcAccessContext.findUniqueResult("INVENTORY.SQL_GET_INVENTORY_WARNING", new RowMapper<MaxInventoryResponse>() {
                @Override
                public MaxInventoryResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                    return buildMaxInventoryResponse(rs);
                }
            }, new Object[] { m.getMaterialId(), m.getStorageId() });
            InventoryAmountResponse inventoryAmountResponse = jdbcAccessContext.findUniqueResult("INVENTORY.SQL_GET_INVENTORY_AMOUNT_BY_MATERIALID", new RowMapper<InventoryAmountResponse>() {
                @Override
                public InventoryAmountResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                    return buildInventoryAmountResponse(rs);
                }
            }, new Object[] { m.getMaterialId(), m.getStorageId() });
            if (inventoryAmountResponse.getInventoryAmount() + m.getCounts() - maxInventoryResponse.getMaxInventory() > 0) {
                inventoryWarningMsg.setMaterialName(inventoryAmountResponse.getMaterialName());
                inventoryWarningMsg.setMaterialId(inventoryAmountResponse.getMaterialId());
                inventoryWarningMsg.setIsMoreThan(true);
                return inventoryWarningMsg;
            }
        }
        return inventoryWarningMsg;
    }

    protected MaxInventoryResponse buildMaxInventoryResponse(ResultSet rs) throws SQLException {
        MaxInventoryResponse m = new MaxInventoryResponse();
        m.setMaxInventory(rs.getDouble("max_inventory"));
        return m;
    }

    protected InventoryAmountResponse buildInventoryAmountResponse(ResultSet rs) throws SQLException {
        InventoryAmountResponse i = new InventoryAmountResponse();
        i.setInventoryAmount(rs.getDouble("inventory_amount"));
        i.setMaterialId(rs.getInt("material_id"));
        i.setMaterialName(rs.getString("material_name"));
        return i;
    }
}
