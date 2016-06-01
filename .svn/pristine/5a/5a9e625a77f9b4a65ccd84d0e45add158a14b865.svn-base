package com.siping.service.inventory.repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.JDBCAccessContext;

import com.siping.smartone.inventory.request.InventoryReceiptItemBatchDeleteRequest;
import com.siping.smartone.inventory.request.InventoryReceiptItemRequest;

@Repository
public class InventoryReceiptItemRestRepository {
    @Autowired
    private JDBCAccessContext jdbcAccessContext;

    public Boolean add(InventoryReceiptItemRequest request) {
        Object[] addParams = new Object[] { request.getReceiptNumber(), request.getMaterialId(), request.getBatchNumber(), request.getProductionDate(), request.getCounts(),
                request.getPurchasePrice(), request.getTotal(), request.getIsGift(), request.getRemark(), request.getCreateBy() };
        int add = jdbcAccessContext.execute("INVENTORYRECEIPTITEM.SQL_ADD_INVENTORYRECEIPTITEM", addParams);
        if (-1 == add) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean delete(InventoryReceiptItemBatchDeleteRequest request) {
        List<Integer> ids = request.getIds();
        List<Object[]> deleteParams = new LinkedList<Object[]>();
        for (Integer i : ids) {
            deleteParams.add(new Object[] { i });
        }
        int[] delete = jdbcAccessContext.batchExecute("INVENTORYRECEIPTITEM.SQL_DELETE_INVENTORYRECEIPTITEM", deleteParams);

        if (Arrays.asList(delete).contains(-1)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean edit(InventoryReceiptItemRequest request) {
        Object[] editParams = new Object[] { request.getReceiptNumber(), request.getMaterialId(), request.getBatchNumber(), request.getProductionDate(), request.getCounts(),
                request.getPurchasePrice(), request.getTotal(), request.getIsGift(), request.getRemark(), request.getCreateBy(), request.getId() };
        int edit = jdbcAccessContext.execute("INVENTORYRECEIPTITEMITEM.SQL_UPDATE_INVENTORYRECEIPTITEM", editParams);
        if (-1 == edit) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
