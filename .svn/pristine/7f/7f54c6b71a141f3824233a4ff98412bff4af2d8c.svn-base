package com.siping.service.report.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.smartone.report.request.MaterialSaleReportRequest;
import com.siping.smartone.report.request.MaterialWarningRequest;
import com.siping.smartone.report.request.SaleAnalysisReportRequest;
import com.siping.smartone.report.response.MaterialSaleReportResponse;
import com.siping.smartone.report.response.MaterialWarningResponse;
import com.siping.smartone.report.response.SaleAnalysisReportResponse;

@Repository
public class MaterialSaleReportRestRepository {
    @Autowired
    protected JDBCAccessContext jdbcAccessContext;

    public PagingResponse<MaterialSaleReportResponse> getList(MaterialSaleReportRequest request) {
        PagingResponse<MaterialSaleReportResponse> response = new PagingResponse<MaterialSaleReportResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        List<Object> queryList = new ArrayList<Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        loadDynamicSqlForMaterialSale(listSql, totalSql, queryList, request);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(totalSql.toString(), queryList.toArray());
        if (request.getIsPaging()) {
            queryList.add((request.getPageNo() - 1) * request.getPageSize());// 由于共用queryList,这两个参数需要在上个语句执行之后添加
            queryList.add(request.getPageSize());
        }
        List<MaterialSaleReportResponse> auditList = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<MaterialSaleReportResponse>() {
            @Override
            public MaterialSaleReportResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildMaterialSaleReportResponse(rs);
            }
        }, queryList.toArray());
        Double totalCounts = 0.0;
        Double totalPrice = 0.0;
        for (MaterialSaleReportResponse item : auditList) {
            totalCounts = totalCounts + Double.valueOf(item.getSaleNumber());
            totalPrice = totalPrice + Double.valueOf(item.getSaleTotal());
        }
        map.put("totalCounts", totalCounts);
        map.put("totalPrice", totalPrice);
        response.setStats(map);
        response.setRecords(auditList);
        response.setTotalRecords(total);
        return response;
    }

    protected MaterialSaleReportResponse buildMaterialSaleReportResponse(ResultSet rs) throws SQLException {
        MaterialSaleReportResponse response = new MaterialSaleReportResponse();
        response.setMaterialId(rs.getString("materialId"));
        response.setMaterialNo(rs.getString("materialNo"));
        response.setMaterialName(rs.getString("materialName"));
        if (rs.getString("materialTypeId").equals("0")) {
            response.setMaterialTypeName("全部");
        } else {
            response.setMaterialTypeName(rs.getString("materialTypeName"));
        }
        response.setBarcode(rs.getString("barcode"));
        response.setUnitName(rs.getString("unitName"));
        response.setSaleNumber(savePointTwo(rs.getString("saleNumber")));
        response.setSaleTotal(rs.getString("saleTotal"));
        return response;
    }

    private void loadDynamicSqlForMaterialSale(StringBuilder listSql, StringBuilder totalSql, List<Object> queryList, MaterialSaleReportRequest request) {
        listSql.append(
            "SELECT ssi.material_id materialId,SUM(ssi.counts) saleNumber,SUM(CONVERT (CONCAT(IF (ssi.is_gift, '0*', '+'),ssi.after_discount),DECIMAL)) saleTotal,mat.material_no materialNo,mat.barcode barcode,mat.material_name materialName,matu.unit_name unitName,matt.type_name materialTypeName,mat.material_type materialTypeId FROM sell_shipments_item ssi LEFT JOIN material mat ON ssi.material_id = mat.id LEFT JOIN material_unit matu on mat.unit_id = matu.id left join material_type matt on mat.material_type = matt.id where 1=1");
        totalSql.append(
            " SELECT count(*) from (SELECT ssi.material_id FROM sell_shipments_item ssi LEFT JOIN material mat ON ssi.material_id = mat.id LEFT JOIN material_unit matu on mat.unit_id = matu.id left join material_type matt on mat.material_type = matt.id where 1=1");
        if (StringUtils.hasText(request.getStartDate())) {
            listSql.append(" and Date(ssi.create_date) >= ? ");
            totalSql.append(" and Date(ssi.create_date) >= ? ");
            queryList.add(request.getStartDate());
        }
        if (StringUtils.hasText(request.getEndDate())) {
            listSql.append(" and Date(ssi.create_date) <= ? ");
            totalSql.append(" and Date(ssi.create_date) <= ? ");
            queryList.add(request.getEndDate());
        }
        if (StringUtils.hasText(request.getKeyword())) {
            listSql.append(" AND (mat.material_no LIKE CONCAT('%',?,'%') OR mat.material_name LIKE CONCAT('%',?,'%') OR mat.barcode LIKE CONCAT('%',?,'%'))");
            totalSql.append(" AND (mat.material_no LIKE CONCAT('%',?,'%') OR mat.material_name LIKE CONCAT('%',?,'%') OR mat.barcode LIKE CONCAT('%',?,'%'))");
            queryList.add(request.getKeyword());
            queryList.add(request.getKeyword());
            queryList.add(request.getKeyword());
        }
        if (StringUtils.hasText(request.getMaterialType())) {
            listSql.append(" AND FIND_IN_SET(mat.material_type,queryAllChildMaterialType( ? )) ");
            totalSql.append(" AND FIND_IN_SET(mat.material_type,queryAllChildMaterialType( ? )) ");
            queryList.add(request.getMaterialType());
        }
        totalSql.append(" GROUP BY ssi.material_id) as temp");
        listSql.append(" GROUP BY ssi.material_id");
        listSql.append(" ORDER BY ssi.material_id");
        if (request.getIsPaging() == true && null != request.getPageNo() && null != request.getPageSize()) {
            listSql.append(" limit ?,? ");
        }
    }

    private void loadDynamicSqlForSaleAnalysis(StringBuilder listSql, StringBuilder totalSql, List<Object> queryList, SaleAnalysisReportRequest request) {
        listSql.append(
            "SELECT ssi.material_id materialId,SUM(ssi.counts) saleNumber,SUM(CONVERT (CONCAT(IF (ssi.is_gift, '0*', '+'),ssi.after_discount),DECIMAL)) saleTotal,CONVERT(ssi.sell_price,DECIMAL) salePrice,mat.material_no materialNo,mat.barcode barcode,mat.material_name materialName,matu.unit_name unitName,sho.shop_name shopName,bpa.partner_name partnerName,fra.franchisee_name franchiseeName,ssi.shipments_number shipmentsNumber,ssh.bills_date billsDate");
        listSql.append(
            " FROM sell_shipments_item ssi LEFT JOIN sell_shipments ssh on ssi.shipments_number = ssh.shipments_number LEFT JOIN material mat ON ssi.material_id = mat.id LEFT JOIN material_unit matu ON mat.unit_id = matu.id Left JOIN shop sho on mat.shops = sho.id left join business_partner bpa on ssh.partner_id = bpa.id left join franchisee fra on ssh.franchisee_id = fra.id WHERE 1 = 1");
        totalSql.append(
            " SELECT count(*) from (SELECT ssi.material_id materialId, CONVERT (CONCAT(IF (ssi.is_gift, '0*', '+'),ssi.after_discount),DECIMAL) salePrice,sho.shop_name shopName,bpa.partner_name partnerName,fra.franchisee_name franchiseeName,ssi.shipments_number shipmentsNumber,ssh.bills_date billsDate FROM sell_shipments_item ssi LEFT JOIN sell_shipments ssh on ssi.shipments_number = ssh.shipments_number LEFT JOIN material mat ON ssi.material_id = mat.id LEFT JOIN material_unit matu ON mat.unit_id = matu.id Left JOIN shop sho on mat.shops = sho.id left join business_partner bpa on ssh.partner_id = bpa.id left join franchisee fra on ssh.franchisee_id = fra.id WHERE 1 = 1");
        if (StringUtils.hasText(request.getStartDate())) {
            listSql.append(" and Date(ssh.bills_date) >= ? ");
            totalSql.append(" and Date(ssh.bills_date) >= ? ");
            queryList.add(request.getStartDate());
        }
        if (StringUtils.hasText(request.getEndDate())) {
            listSql.append(" and Date(ssh.bills_date) <= ? ");
            totalSql.append(" and Date(ssh.bills_date) <= ? ");
            queryList.add(request.getEndDate());
        }
        if (StringUtils.hasText(request.getMaterialNo())) {
            listSql.append(" and mat.material_no = ? ");
            totalSql.append(" and mat.material_no = ? ");
            queryList.add(request.getMaterialNo());
        }
        if (StringUtils.hasText(request.getPartnerNo())) {
            listSql.append(" and bpa.partner_code= ? ");
            totalSql.append(" and bpa.partner_code = ? ");
            queryList.add(request.getPartnerNo());
        }
        if (StringUtils.hasText(request.getFranchiseeNo())) {
            listSql.append(" and fra.franchisee_no = ? ");
            totalSql.append(" and fra.franchisee_no = ? ");
            queryList.add(request.getFranchiseeNo());
        }
        if (StringUtils.hasText(request.getShopNo())) {
            listSql.append(" and sho.shop_no = ? ");
            totalSql.append(" and sho.shop_no = ? ");
            queryList.add(request.getShopNo());
        }
        totalSql.append(" GROUP BY materialId,salePrice,shopName,partnerName,franchiseeName,shipmentsNumber,billsDate) as temp");
        listSql.append(" GROUP BY materialId,salePrice,shopName,partnerName,franchiseeName,shipmentsNumber,billsDate");// 通过这些字段来聚合
        listSql.append(" ORDER BY billsDate,CAST(SUBSTR(shipmentsNumber,3)AS SIGNED) asc");
        if (request.getIsPaging() == true && null != request.getPageNo() && null != request.getPageSize()) {
            listSql.append(" limit ?,? ");
        }
    }

    public PagingResponse<SaleAnalysisReportResponse> getAnalysisList(SaleAnalysisReportRequest request) {
        PagingResponse<SaleAnalysisReportResponse> response = new PagingResponse<SaleAnalysisReportResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        List<Object> queryList = new ArrayList<Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        loadDynamicSqlForSaleAnalysis(listSql, totalSql, queryList, request);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(totalSql.toString(), queryList.toArray());
        if (request.getIsPaging()) {
            queryList.add((request.getPageNo() - 1) * request.getPageSize());
            queryList.add(request.getPageSize());
        }
        List<SaleAnalysisReportResponse> auditList = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<SaleAnalysisReportResponse>() {
            @Override
            public SaleAnalysisReportResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildSaleAnalysisReportResponse(rs);
            }
        }, queryList.toArray());
        Double totalCounts = 0.0;
        Double totalPrice = 0.0;
        for (SaleAnalysisReportResponse item : auditList) {
            totalCounts = totalCounts + Double.valueOf(item.getSaleNumber());
            totalPrice = totalPrice + Double.valueOf(item.getSaleTotal());
        }
        map.put("totalCounts", totalCounts);
        map.put("totalPrice", totalPrice);
        response.setStats(map);
        response.setRecords(auditList);
        response.setTotalRecords(total);
        return response;
    }

    private SaleAnalysisReportResponse buildSaleAnalysisReportResponse(final ResultSet rs) throws SQLException {
        SaleAnalysisReportResponse response = new SaleAnalysisReportResponse();
        response.setShipmentsNumber(rs.getString("shipmentsNumber"));
        response.setBillDate(rs.getString("billsDate"));
        response.setPartnerName(rs.getString("partnerName"));
        response.setFranchiseeName(rs.getString("franchiseeName"));
        response.setShopName(rs.getString("shopName"));
        response.setMaterialNo(rs.getString("materialNo"));
        response.setBarcode(rs.getString("barcode"));
        response.setMaterialName(rs.getString("materialName"));
        response.setUnitName(rs.getString("unitName"));
        response.setSaleNumber(savePointTwo(rs.getString("saleNumber")));
        response.setSalePrice(rs.getString("salePrice"));
        response.setSaleTotal(rs.getString("saleTotal"));
        return response;
    }
    
    private String savePointTwo(String primeData){
        DecimalFormat df=new DecimalFormat("#.00");
        return df.format(Double.valueOf(primeData));
    }

    public PagingResponse<MaterialWarningResponse> getMaterialWaringList(MaterialWarningRequest request) {
        PagingResponse<MaterialWarningResponse> response = new PagingResponse<MaterialWarningResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        List<Object> queryList = new ArrayList<Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        loadDynamicSqlForMaterialWarning(listSql, totalSql, queryList, request);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(totalSql.toString(), queryList.toArray());
        if (request.getIsPaging()) {
            queryList.add((request.getPageNo() - 1) * request.getPageSize());// 由于共用queryList,这两个参数需要在上个语句执行之后添加
            queryList.add(request.getPageSize());
        }
        List<MaterialWarningResponse> auditList = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<MaterialWarningResponse>() {
            @Override
            public MaterialWarningResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildMaterialWarningResponse(rs);
            }
        }, queryList.toArray());
        Double totalCounts = 0.0;//库存数量
        Double totalDiff = 0.0;
        List<MaterialWarningResponse> finalList = new ArrayList<MaterialWarningResponse>();
        if(request.getWarningType()==1){//上限预警
            for(MaterialWarningResponse item:auditList){
                MaterialWarningResponse tempResponse = new MaterialWarningResponse();
                copyMaterialWaringInfo(item,tempResponse);
                tempResponse.setWarningNumber(item.getMaxInventory());
                totalCounts = totalCounts + tempResponse.getInventoryNumber();
                totalDiff = totalDiff + tempResponse.getDiffNumber();
                finalList.add(tempResponse);
            }
        }
        else if(request.getWarningType()==0){
            for(MaterialWarningResponse item:auditList){
                MaterialWarningResponse tempResponse = new MaterialWarningResponse();
                copyMaterialWaringInfo(item,tempResponse);
                tempResponse.setWarningNumber(item.getMinInventory());
                totalCounts = totalCounts + tempResponse.getInventoryNumber();
                totalDiff = totalDiff + tempResponse.getDiffNumber();
                finalList.add(tempResponse);
            }
        }
        else {
            throw new BusinessProcessException("预警情况只有两种,必须选择一种!");
        }
        map.put("totalCounts", totalCounts);
        map.put("totalDiff", totalDiff);
        response.setStats(map);
        response.setRecords(finalList);
        response.setTotalRecords(total);
        return response;
    }

    private void copyMaterialWaringInfo(MaterialWarningResponse fromData,MaterialWarningResponse toData){
        toData.setMaterialName(fromData.getMaterialName());
        toData.setMaterialNo(fromData.getMaterialNo());
        toData.setMaterialTypeName(fromData.getMaterialTypeName());
        toData.setBarcode(fromData.getBarcode());
        toData.setUnitName(fromData.getUnitName());
        toData.setStorageName(fromData.getStorageName());
        toData.setStorageNo(fromData.getStorageNo());
        toData.setInventoryNumber(fromData.getInventoryNumber());    
        toData.setDiffNumber(Math.abs(fromData.getDiffNumber()));
    }
    
    private MaterialWarningResponse buildMaterialWarningResponse(ResultSet rs) throws SQLException {
        MaterialWarningResponse response = new MaterialWarningResponse();
        response.setMaterialNo(rs.getString("materialNo"));
        response.setMaterialName(rs.getString("materialName"));
        response.setMaterialTypeName(rs.getString("materialTypeName"));
        response.setBarcode(rs.getString("barcode"));
        response.setUnitName(rs.getString("unitName"));
        response.setStorageName(rs.getString("storageName"));
        response.setStorageNo(rs.getString("storageNo"));
        response.setInventoryNumber(rs.getDouble("inventoryNumber"));
        //response.setWarningNumber(rs.getDouble("warningNumber"));
        response.setMaxInventory(rs.getDouble("maxInventory"));
        response.setMinInventory(rs.getDouble("minInventory"));
        response.setDiffNumber(rs.getDouble("diffNumber"));
        return response;
    }
    
    private void loadDynamicSqlForMaterialWarning(StringBuilder listSql, StringBuilder totalSql, List<Object> queryList, MaterialWarningRequest request) {
        if(request.getWarningType()==1){//上限预警
            listSql.append("select * from(SELECT inv.material_id,inv.storage_id,SUM(inv.inventory_amount) inventoryNumber,iwa.max_inventory maxInventory,iwa.min_inventory minInventory,mat.material_name materialName,mat.material_no materialNo,mat.barcode barcode,matu.unit_name unitName,matt.type_name materialTypeName,sto.storage_name storageName,sto.storage_no storageNo,iwa.max_inventory - SUM(inv.inventory_amount) diffNumber FROM inventory inv LEFT JOIN material mat ON inv.material_id = mat.id LEFT JOIN inventory_warning iwa ON inv.material_id = iwa.material_id and inv.storage_id = iwa.storage_id left join material_unit matu on mat.unit_id = matu.id left join material_type matt on mat.material_type = matt.id left join storage sto on inv.storage_id = sto.id where 1 = 1");//数量为负，启动预警
            totalSql.append(" SELECT count(*) from (SELECT inv.material_id,inv.storage_id,iwa.max_inventory - SUM(inv.inventory_amount) diffNumber FROM inventory inv LEFT JOIN material mat ON inv.material_id = mat.id LEFT JOIN inventory_warning iwa ON inv.material_id = iwa.material_id and inv.storage_id = iwa.storage_id left join material_unit matu on mat.unit_id = matu.id left join material_type matt on mat.material_type = matt.id left join storage sto on inv.storage_id = sto.id where 1 = 1");
        }
        else if(request.getWarningType()==0){//上限预警
            listSql.append("select * from(SELECT inv.material_id,inv.storage_id,SUM(inv.inventory_amount) inventoryNumber,iwa.max_inventory maxInventory,iwa.min_inventory minInventory,mat.material_name materialName,mat.material_no materialNo,mat.barcode barcode,matu.unit_name unitName,matt.type_name materialTypeName,sto.storage_name storageName,sto.storage_no storageNo,SUM(inv.inventory_amount) - iwa.min_inventory diffNumber FROM inventory inv LEFT JOIN material mat ON inv.material_id = mat.id LEFT JOIN inventory_warning iwa ON inv.material_id = iwa.material_id and inv.storage_id = iwa.storage_id left join material_unit matu on mat.unit_id = matu.id left join material_type matt on mat.material_type = matt.id left join storage sto on inv.storage_id = sto.id where 1 = 1");//数量为负，启动预警
            totalSql.append(" SELECT count(*) from (SELECT inv.material_id,inv.storage_id, SUM(inv.inventory_amount) - iwa.min_inventory diffNumber FROM inventory inv LEFT JOIN material mat ON inv.material_id = mat.id LEFT JOIN inventory_warning iwa ON inv.material_id = iwa.material_id and inv.storage_id = iwa.storage_id left join material_unit matu on mat.unit_id = matu.id left join material_type matt on mat.material_type = matt.id left join storage sto on inv.storage_id = sto.id where 1 = 1");
        }
        else{
            throw new BusinessProcessException("预警情况只有两种,必须选择一种!");
        }
//        listSql.append("SELECT inv.material_id,inv.storage_id,SUM(inv.inventory_amount) inventoryNumber,iwa.max_inventory,iwa.min_inventory,mat.material_name materialName,mat.material_no materialNo,mat.barcode barcode,matu.unit_name unitName,matt.type_name materialTypeName,sto.storage_name storageName,sto.storage_no storageNo FROM inventory inv LEFT JOIN material mat ON inv.material_id = mat.id LEFT JOIN inventory_warning iwa ON inv.material_id = iwa.material_id and inv.storage_id = iwa.storage_id left join material_unit matu on mat.unit_id = matu.id left join material_type matt on mat.material_type = matt.id left join storage sto on inv.storage_id = sto.id where 1 = 1");
//        totalSql.append(" SELECT count(*) from (SELECT inv.material_id,inv.storage_id  FROM inventory inv LEFT JOIN material mat ON inv.material_id = mat.id LEFT JOIN inventory_warning iwa ON inv.material_id = iwa.material_id and inv.storage_id = iwa.storage_id left join material_unit matu on mat.unit_id = matu.id left join material_type matt on mat.material_type = matt.id left join storage sto on inv.storage_id = sto.id where 1 = 1");
        if (StringUtils.hasText(request.getKeyword())) {
            listSql.append(" and (sto.storage_name like CONCAT('%',?,'%') or sto.storage_no like CONCAT('%',?,'%')) ");
            totalSql.append(" and (sto.storage_name like CONCAT('%',?,'%') or sto.storage_no like CONCAT('%',?,'%')) ");
            queryList.add(request.getKeyword());
            queryList.add(request.getKeyword());
        }
        totalSql.append(" GROUP BY inv.material_id,inv.storage_id) as temp where temp.diffNumber < 0");
        listSql.append(" GROUP BY inv.material_id,inv.storage_id order by diffNumber asc) as temp where diffNumber < 0");// 通过这些字段来聚合
        if (request.getIsPaging() == true && null != request.getPageNo() && null != request.getPageSize()) {
            listSql.append(" limit ?,? ");
        }
    }
}
