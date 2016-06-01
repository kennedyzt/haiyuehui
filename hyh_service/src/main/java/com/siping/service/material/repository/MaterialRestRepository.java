package com.siping.service.material.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.stroma.framework.core.database.PreparedStatementCreatorImpl;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.CommonRepository;
import com.siping.smartone.inventory.request.AddInventoryWarningRequest;
import com.siping.smartone.inventory.response.GetInventoryCheckWithMaterialAndLocationInfo;
import com.siping.smartone.inventory.response.GetInventoryCheckWithMaterialAndLocationInfoRequest;
import com.siping.smartone.inventory.response.GetInventoryWarningResponse;
import com.siping.smartone.material.request.AddMaterialRequest;
import com.siping.smartone.material.request.DeleteMaterialRequest;
import com.siping.smartone.material.request.GetMaterialBatchRequest;
import com.siping.smartone.material.request.GetMaterialListByStorageRequest;
import com.siping.smartone.material.request.GetMaterialListRequest;
import com.siping.smartone.material.request.GetMaterialRequest;
import com.siping.smartone.material.request.GetOpeanWinMaterialRequest;
import com.siping.smartone.material.request.MaterialExportParamRequest;
import com.siping.smartone.material.response.GetMaterialBatchResponse;
import com.siping.smartone.material.response.GetMaterialResponse;
import com.siping.smartone.wms.response.StorageLocationResponse;

@Repository
public class MaterialRestRepository extends CommonRepository {
    // @Autowired
    // private PdaRestService pdaRestService;
    // @Autowired
    // private StorageAreaRestService storageAreaRestService;
    // @Autowired
    // private StorageLocationRestService storageLocationRestService;

    public Boolean add(AddMaterialRequest request) {
        if (null != get(null, request.getMaterialNo(), null)) {
            throw new BusinessProcessException("商品货号不能重复！");
        }
        if (StringUtils.hasText(request.getBarcode()) && null != get(null, null, request.getBarcode())) {
            throw new BusinessProcessException("国际编码不能重复！");
        }
        Object[] addParams = new Object[] { request.getMaterialNo().trim(), request.getMaterialName(), request.getForeignName(), request.getMaterialType(), request.getBrand(),
                request.getSpecificationsModel(), request.getSeason(), request.getIsPurchase(), request.getIsSell(), request.getIsInventory(), request.getUnitId(), request.getBarcode().trim(),
                request.getShops(), request.getIsBatch(), request.getExpirationDate(), request.getPartnerId(), request.getMinOrder(), request.getMaxInventory(), request.getMinInventory(),
                request.getIsEnable(), request.getDescription(), false, request.getTransactionId(), new Date(), request.getCreateBy(), request.getLegalUnit(), request.getLegalTranslationQuantity(),
                request.getEntryUnit(), request.getEntryTranslationQuantity(), request.getManufacturer(), request.getProvenance(), request.getEbec(), request.getEben(), request.getHscode(),
                request.getPostTaxNumber(), request.getCustom1(), request.getCustom2(), request.getCustom3(), request.getItemNo(), request.getWeight(), request.getPostTaxRate() };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int add = jdbcAccessContext.executeAndReturnPrimarykey(new PreparedStatementCreatorImpl("MATERIAL.SQL_ADD_MATERIAL", addParams, jdbcAccessContext), keyHolder);
        request.setId(keyHolder.getKey().toString());
        if (-1 == add) {
            return Boolean.FALSE;
        }
        List<Object[]> listParams = new ArrayList<Object[]>();
        Object[] addInventoryWarningParams = null;
        for (AddInventoryWarningRequest inventoryWarningRequest : request.getAddInventoryWarningRequests()) {
            addInventoryWarningParams = new Object[] { request.getId(), inventoryWarningRequest.getStorageId(), inventoryWarningRequest.getMaxInventory(), inventoryWarningRequest.getMinInventory(),
                    request.getCreateBy() };
            listParams.add(addInventoryWarningParams);
        }
        int[] addInventoryWarningFlag = jdbcAccessContext.batchExecute("MATERIAL.SQL_ADD_INVENTORY_WARNING", listParams);
        if (Arrays.asList(addInventoryWarningFlag).contains(-1)) {
            throw new BusinessProcessException("增加库存预警时失败,新增失败!");
        }
        addOperationLog("商品", "新增商品,商品编号：[" + request.getMaterialNo() + "],商品名称：[" + request.getMaterialName() + "]", null, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    private List<GetInventoryWarningResponse> getInventoryWarningListByMatertialId(String materialId) {
        List<GetInventoryWarningResponse> inventoryWarningResponses = jdbcAccessContext.find("MATERIAL.SQL_GET_INVENTORY_WARNING_BY_MATERIAL_ID", new RowMapper<GetInventoryWarningResponse>() {
            @Override
            public GetInventoryWarningResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildInventoryWarningResponse(rs);
            }
        }, new Object[] { materialId });
        return inventoryWarningResponses;
    }

    private GetInventoryWarningResponse buildInventoryWarningResponse(final ResultSet rs) throws SQLException {
        GetInventoryWarningResponse response = new GetInventoryWarningResponse();
        response.setId(rs.getString("id"));
        response.setMaterialId(rs.getString("material_id"));
        response.setStorageId(rs.getString("storage_id"));
        response.setMaxInventory(rs.getString("max_inventory"));
        response.setMinInventory(rs.getString("min_inventory"));
        return response;
    }

    private Integer checkMaterialUsedInfo(AddMaterialRequest request) {
        Integer materialFlag = jdbcAccessContext.findInteger("MATERIAL.SQL_GET_MATERIALFLAG_BY_ID", new Object[] { request.getId() });
        return materialFlag;
    }

    private Integer checkMaterialUsedInfo(String id) {
        Integer materialFlag = jdbcAccessContext.findInteger("MATERIAL.SQL_GET_MATERIALFLAG_BY_ID", new Object[] { id });
        return materialFlag;
    }

    private Integer checkMaterialUsedInfo(Integer id) {
        Integer materialFlag = jdbcAccessContext.findInteger("MATERIAL.SQL_GET_MATERIALFLAG_BY_ID", new Object[] { id });
        return materialFlag;
    }

    public Boolean edit(AddMaterialRequest request) {// 供应商，生产商
        GetMaterialResponse materialResponse = get(request.getId(), null, null);
        if (null == materialResponse) {
            throw new BusinessProcessException("商品不存在，无法编辑！");
        }
        // if (checkMaterialUsedInfo(request) == 1) {
        // throw new BusinessProcessException("商品已经被引用，无法编辑！");
        // }
        Object[] deleteParam = new Object[] { request.getId() };
        int delete = jdbcAccessContext.execute("MATERIAL.SQL_DELETE_INVENTORY_WARNING_BY_MATERIAL_ID", deleteParam);
        if (-1 == delete) {
            throw new BusinessProcessException("操作库存预警时失败");
        }
        List<Object> editParam = new ArrayList<Object>();
        int edit = jdbcAccessContext.executeWithoutSqlManager(buildEditSql(request, editParam), editParam.toArray());
        if (-1 == edit) {
            return Boolean.FALSE;
        }
        List<Object[]> listParams = new ArrayList<Object[]>();
        Object[] addInventoryWarningParams = null;
        for (AddInventoryWarningRequest inventoryWarningRequest : request.getAddInventoryWarningRequests()) {
            addInventoryWarningParams = new Object[] { request.getId(), inventoryWarningRequest.getStorageId(), inventoryWarningRequest.getMaxInventory(), inventoryWarningRequest.getMinInventory(),
                    request.getCreateBy() };
            listParams.add(addInventoryWarningParams);
        }
        int[] addInventoryWarningFlag = jdbcAccessContext.batchExecute("MATERIAL.SQL_ADD_INVENTORY_WARNING", listParams);
        if (Arrays.asList(addInventoryWarningFlag).contains(-1)) {
            throw new BusinessProcessException("修改库存预警时失败,修改失败!");
        }
        addOperationLog("商品", "更新商品:[" + request.getMaterialName() + "]", null, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    private String buildEditSql(AddMaterialRequest request, List<Object> list) {
        StringBuilder sql = new StringBuilder("UPDATE material SET ");
        assemblePramsForString(request, list, sql);
        assembleParamsForBoolean(request, list, sql);
        assembleParamsForInteger(request, list, sql);
        assemblePramsForDouble(request, list, sql);
        sql.append(" WHERE id=? ");
        list.add(request.getId());
        return sql.toString();
    }

    private void assemblePramsForDouble(AddMaterialRequest request, List<Object> list, StringBuilder sql) {
        if (request.getWeight() != null && request.getWeight() != 0.0) {
            if (list.size() > 0)
                sql.append(" ,weight=? ");
            else {
                sql.append(" weight=? ");
            }
            list.add(request.getWeight());
        }
        if (request.getPostTaxRate() != null && request.getPostTaxRate() != 0.0) {
            if (list.size() > 0)
                sql.append(" ,post_tax_rate=? ");
            else {
                sql.append(" post_tax_rate=? ");
            }
            list.add(request.getPostTaxRate());
        }
    }

    private void assemblePramsForString(AddMaterialRequest request, List<Object> list, StringBuilder sql) {
        if (StringUtils.hasText(request.getMaterialName())) {
            if (list.size() > 0)
                sql.append(" ,material_name=? ");
            else {
                sql.append(" material_name=? ");
            }
            list.add(request.getMaterialName());
        }
        if (StringUtils.hasText(request.getMaterialNo())) {
            if (list.size() > 0)
                sql.append(" ,material_no=? ");
            else
                sql.append(" material_no=? ");
            list.add(request.getMaterialNo().trim());
        }
        if (StringUtils.hasText(request.getForeignName())) {
            if (list.size() > 0)
                sql.append(" ,foreign_name=? ");
            else
                sql.append(" foreign_name=? ");
            list.add(request.getForeignName());
        }
        if (StringUtils.hasText(request.getBrand())) {
            if (list.size() > 0)
                sql.append(" ,brand=? ");
            else
                sql.append(" brand=? ");
            list.add(request.getBrand());
        }
        if (StringUtils.hasText(request.getSpecificationsModel())) {
            if (list.size() > 0)
                sql.append(" ,specifications_model=? ");
            else
                sql.append(" specifications_model=? ");
            list.add(request.getSpecificationsModel());
        }
        if (StringUtils.hasText(request.getSeason())) {
            if (list.size() > 0)
                sql.append(" ,season=? ");
            else
                sql.append(" season=? ");
            list.add(request.getSeason());
        }
        if (StringUtils.hasText(request.getBarcode())) {
            if (list.size() > 0)
                sql.append(" ,barcode=? ");
            else
                sql.append(" barcode=? ");
            list.add(request.getBarcode().trim());
        }
        if (StringUtils.hasText(request.getShops())) {
            if (list.size() > 0)
                sql.append(" ,shops=? ");
            else
                sql.append(" shops=? ");
            list.add(request.getShops());
        }
        if (StringUtils.hasText(request.getDescription())) {
            if (list.size() > 0)
                sql.append(" ,description=? ");
            else
                sql.append(" description=? ");
            list.add(request.getDescription());
        }
        if (StringUtils.hasText(request.getProvenance())) {
            if (list.size() > 0)
                sql.append(" ,provenance=? ");
            else
                sql.append(" provenance=? ");
            list.add(request.getProvenance());
        }
        if (StringUtils.hasText(request.getEbec())) {
            if (list.size() > 0)
                sql.append(" ,ebec=? ");
            else
                sql.append(" ebec=? ");
            list.add(request.getEbec());
        }
        if (StringUtils.hasText(request.getEben())) {
            if (list.size() > 0)
                sql.append(" ,eben=? ");
            else
                sql.append(" eben=? ");
            list.add(request.getEben());
        }
        if (StringUtils.hasText(request.getHscode())) {
            if (list.size() > 0)
                sql.append(" ,hscode=? ");
            else
                sql.append(" hscode=? ");
            list.add(request.getHscode());
        }
        if (StringUtils.hasText(request.getPostTaxNumber())) {
            if (list.size() > 0)
                sql.append(" ,post_tax_number=? ");
            else
                sql.append(" post_tax_number=? ");
            list.add(request.getPostTaxNumber());
        }
        if (StringUtils.hasText(request.getCustom1())) {
            if (list.size() > 0)
                sql.append(" ,custom1=? ");
            else
                sql.append(" custom1=? ");
            list.add(request.getCustom1());
        }
        if (StringUtils.hasText(request.getCustom2())) {
            if (list.size() > 0)
                sql.append(" ,custom2=? ");
            else
                sql.append(" custom2=? ");
            list.add(request.getCustom2());
        }
        if (StringUtils.hasText(request.getCustom3())) {
            if (list.size() > 0)
                sql.append(" ,custom3=? ");
            else
                sql.append(" custom3=? ");
            list.add(request.getCustom3());
        }
        if (StringUtils.hasText(request.getItemNo())) {
            if (list.size() > 0)
                sql.append(" ,item_no=? ");
            else
                sql.append(" item_no=? ");
            list.add(request.getItemNo());
        }
        // if (StringUtils.hasText(request.getManufacturer())) {
        if (true) {
            if (list.size() > 0)
                sql.append(" ,manufacturer=? ");
            else
                sql.append(" manufacturer=? ");
            list.add(request.getManufacturer());
        }
    }

    private void assembleParamsForBoolean(AddMaterialRequest request, List<Object> list, StringBuilder sql) {
        if (null != request.getIsEnable()) {
            if (list.size() > 0)
                sql.append(" ,is_enable=? ");
            else
                sql.append(" is_enable=? ");
            list.add(request.getIsEnable());
        }
        if (null != request.getIsBatch()) {
            if (list.size() > 0)
                sql.append(" ,is_batch=? ");
            else
                sql.append(" is_batch=? ");
            list.add(request.getIsBatch());
        }
        if (null != request.getIsDelete()) {
            if (list.size() > 0)
                sql.append(" ,is_delete=? ");
            else
                sql.append(" is_delete=? ");
            list.add(request.getIsDelete());
        }
        if (null != request.getIsInventory()) {
            if (list.size() > 0)
                sql.append(" ,is_inventory=? ");
            else
                sql.append(" is_inventory=? ");
            list.add(request.getIsInventory());
        }
        if (null != request.getIsSell()) {
            if (list.size() > 0)
                sql.append(" ,is_sell=? ");
            else
                sql.append("is_sell=?");
            list.add(request.getIsSell());
        }
        if (null != request.getIsPurchase()) {
            if (list.size() > 0)
                sql.append(" ,is_purchase=? ");
            else
                sql.append("is_purchase=?");
            list.add(request.getIsPurchase());
        }
    }

    private void assembleParamsForInteger(AddMaterialRequest request, List<Object> list, StringBuilder sql) {
        if (null != request.getUpdateBy() && 0 != request.getUpdateBy()) {
            if (list.size() > 0)
                sql.append(" ,update_date=? ,update_by=? ");
            else
                sql.append(" update_date=? ,update_by=? ");
            list.add(new Date());
            list.add(request.getUpdateBy());
        }
        if (null != request.getMaterialType() && 0 != request.getMaterialType()) {
            if (list.size() > 0)
                sql.append(" ,material_type=? ");
            else
                sql.append(" material_type=? ");
            list.add(request.getMaterialType());
        }
        if (null != request.getUnitId() && 0 != request.getUnitId()) {
            if (list.size() > 0)
                sql.append(" ,unit_id=? ");
            else
                sql.append(" unit_id=? ");
            list.add(request.getUnitId());
        }
        if(null != request.getExpirationDate()&& 0 !=request.getExpirationDate()){
            if (list.size() > 0)
                sql.append(" ,expiration_date=? ");
            else
                sql.append(" expiration_date=? ");
            list.add(request.getExpirationDate());
        }
        if (null != request.getMinOrder()) {
            if (list.size() > 0)
                sql.append(" ,min_order=? ");
            else
                sql.append(" min_order=? ");
            list.add(request.getMinOrder());
        }
        if (null != request.getLegalUnit() && 0 != request.getLegalUnit()) {
            if (list.size() > 0)
                sql.append(" ,legal_unit=? ");
            else
                sql.append(" legal_unit=? ");
            list.add(request.getLegalUnit());
        }
        if (null != request.getEntryUnit() && 0 != request.getEntryUnit()) {
            if (list.size() > 0)
                sql.append(" ,entry_unit=? ");
            else
                sql.append(" entry_unit=? ");
            list.add(request.getEntryUnit());
        }
        if (null != request.getPartnerId() && 0 != request.getPartnerId()) {
            if (list.size() > 0)
                sql.append(" ,partner_id=? ");
            else
                sql.append(" partner_id=? ");
            list.add(request.getPartnerId());
        }
    }

    public GetMaterialResponse get(String id, String materialNo, String barcode) {
        List<Object> queryParam = new ArrayList<Object>();
        List<GetMaterialResponse> list = jdbcAccessContext.findWithoutSqlManager(buildGetSql(id, materialNo, barcode, queryParam), new RowMapper<GetMaterialResponse>() {
            @Override
            public GetMaterialResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialResponseForDetail(rs);
            }
        }, queryParam.toArray());
        List<GetInventoryWarningResponse> inventoryWarings = new ArrayList<GetInventoryWarningResponse>();
        inventoryWarings = getInventoryWarningListByMatertialId(id);
        if (!CollectionUtils.isEmpty(list)) {
            list.get(0).setInventoryWarnings(inventoryWarings);
            if (checkMaterialUsedInfo(id) == 1) {
                list.get(0).setIsUsed(true);
            } else {
                list.get(0).setIsUsed(false);
            }
            return list.get(0);
        }
        return null;
    }

    private GetMaterialResponse buildMaterialResponseForDetail(ResultSet rs) throws SQLException {
        GetMaterialResponse m = new GetMaterialResponse();
        m.setId(rs.getInt("id"));
        m.setMaterialNo(rs.getString("material_no"));
        m.setMaterialName(rs.getString("material_name"));
        m.setForeignName(rs.getString("foreign_name"));
        m.setMaterialType(rs.getInt("material_type"));
        if (m.getMaterialType() == 0) {
            m.setMaterialTypeName("全部");
        } else {
            m.setMaterialTypeName(rs.getString("materialTypeName"));
        }
        m.setBrand(rs.getString("brand"));
        m.setSpecificationsModel(rs.getString("specifications_model"));
        m.setSeason(rs.getString("season"));
        // m.setUsageId(rs.getInt("usage_id"));
        m.setIsPurchase(rs.getBoolean("is_purchase"));
        m.setIsSell(rs.getBoolean("is_sell"));
        m.setIsInventory(rs.getBoolean("is_inventory"));
        m.setUnitId(rs.getInt("unit_id"));
        m.setUnitName(rs.getString("unitName"));
        m.setBarcode(rs.getString("barcode"));
        m.setShops(rs.getInt("shops"));
        if (rs.getInt("shops") == 0) {
            m.setShopName("自营");
        } else {
            m.setShopName(rs.getString("shopName"));
        }
        m.setIsBatch(rs.getBoolean("is_batch"));
        m.setExpirationDate(rs.getInt("expiration_date"));
        m.setPartnerId(rs.getInt("partner_id"));
        m.setPartnerName(rs.getString("partnerName"));
        m.setMinOrder(rs.getInt("min_order"));
        m.setMaxInventory(rs.getInt("max_inventory"));
        m.setMinInventory(rs.getInt("min_inventory"));
        m.setIsEnable(rs.getBoolean("is_enable"));
        m.setDescription(rs.getString("description"));
        m.setIsDelete(rs.getBoolean("is_delete"));
        m.setTransactionId(rs.getString("transaction_id"));
        m.setCreateBy(rs.getInt("create_by"));
        m.setCreateDate(rs.getString("create_date"));
        m.setCreateBy(rs.getInt("update_by"));
        m.setUpdateDate(rs.getString("update_date"));
        m.setLegalUnit(rs.getInt("legal_unit"));
        m.setLegalName(rs.getString("legalName"));
        m.setEntryUnit(rs.getInt("entry_unit"));
        m.setEntryName(rs.getString("entryName"));
        m.setLegalTranslationQuantity(rs.getFloat("legal_translation_quantity"));
        m.setEntryTranslationQuantity(rs.getFloat("entry_translation_quantity"));
        m.setManufacturer(rs.getString("manufacturer"));
        m.setProvenance(rs.getString("provenance"));
        m.setEbec(rs.getString("ebec"));
        m.setEben(rs.getString("eben"));
        m.setHscode(rs.getString("hscode"));
        m.setPostTaxNumber(rs.getString("post_tax_number"));
        m.setCustom1(rs.getString("custom1"));
        m.setCustom2(rs.getString("custom2"));
        m.setCustom3(rs.getString("custom3"));
        try {
            m.setItemNo(rs.getString("item_no"));
        } catch (Exception e) {
        }
        try {
            m.setWeight(rs.getDouble("weight"));
        } catch (Exception e) {
        }
        try {
            m.setPostTaxRate(rs.getDouble("post_tax_rate"));
        } catch (Exception e) {
            // 这个try cath 是防止这个组装被其他SQL调用，抛出异常，使系统错误
        }
        return m;
    }

    private static GetMaterialResponse buildMaterialResponseForList(final ResultSet rs) throws SQLException {
        GetMaterialResponse m = new GetMaterialResponse();
        m.setId(rs.getInt("id"));
        m.setMaterialNo(rs.getString("material_no"));
        m.setMaterialName(rs.getString("material_name"));
        m.setForeignName(rs.getString("foreign_name"));
        m.setMaterialType(rs.getInt("material_type"));
        if (m.getMaterialType() == 0) {
            m.setMaterialTypeName("全部");
        } else {
            m.setMaterialTypeName(rs.getString("materialTypeName"));
        }
        m.setBrand(rs.getString("brand"));
        m.setSpecificationsModel(rs.getString("specifications_model"));
        m.setSeason(rs.getString("season"));
        // m.setUsageId(rs.getInt("usage_id"));
        m.setIsPurchase(rs.getBoolean("is_purchase"));
        m.setIsSell(rs.getBoolean("is_sell"));
        m.setIsInventory(rs.getBoolean("is_inventory"));
        m.setUnitId(rs.getInt("unit_id"));
        m.setUnitName(rs.getString("unitName"));
        m.setBarcode(rs.getString("barcode"));
        m.setShops(rs.getInt("shops"));
        m.setIsBatch(rs.getBoolean("is_batch"));
        m.setExpirationDate(rs.getInt("expiration_date"));
        m.setPartnerId(rs.getInt("partner_id"));
        m.setPartnerName(rs.getString("partnerName"));
        m.setMinOrder(rs.getInt("min_order"));
        m.setMaxInventory(rs.getInt("max_inventory"));
        m.setMinInventory(rs.getInt("min_inventory"));
        m.setIsEnable(rs.getBoolean("is_enable"));
        m.setDescription(rs.getString("description"));
        m.setIsDelete(rs.getBoolean("is_delete"));
        m.setTransactionId(rs.getString("transaction_id"));
        m.setCreateBy(rs.getInt("create_by"));
        m.setCreateDate(rs.getString("create_date"));
        m.setCreateBy(rs.getInt("update_by"));
        m.setUpdateDate(rs.getString("update_date"));
        try {
            m.setStockQuantity(rs.getFloat("stockQuantity"));
        } catch (Exception e) {
        }
        return m;
    }

    private static GetMaterialResponse buildMaterialResponse(final ResultSet rs) throws SQLException {
        GetMaterialResponse m = new GetMaterialResponse();
        m.setId(rs.getInt("id"));
        m.setMaterialNo(rs.getString("material_no"));
        m.setMaterialName(rs.getString("material_name"));
        m.setForeignName(rs.getString("foreign_name"));
        m.setMaterialType(rs.getInt("material_type"));
        m.setBrand(rs.getString("brand"));
        m.setSpecificationsModel(rs.getString("specifications_model"));
        m.setSeason(rs.getString("season"));
        // m.setUsageId(rs.getInt("usage_id"));
        m.setIsPurchase(rs.getBoolean("is_purchase"));
        m.setIsSell(rs.getBoolean("is_sell"));
        m.setIsInventory(rs.getBoolean("is_inventory"));
        m.setUnitId(rs.getInt("unit_id"));
        m.setUnitName(rs.getString("unitName"));
        m.setBarcode(rs.getString("barcode"));
        m.setShops(rs.getInt("shops"));
        m.setIsBatch(rs.getBoolean("is_batch"));
        m.setExpirationDate(rs.getInt("expiration_date"));
        m.setPartnerId(rs.getInt("partner_id"));
        m.setMinOrder(rs.getInt("min_order"));
        m.setMaxInventory(rs.getInt("max_inventory"));
        m.setMinInventory(rs.getInt("min_inventory"));
        m.setIsEnable(rs.getBoolean("is_enable"));
        m.setDescription(rs.getString("description"));
        m.setIsDelete(rs.getBoolean("is_delete"));
        m.setTransactionId(rs.getString("transaction_id"));
        m.setCreateBy(rs.getInt("create_by"));
        m.setCreateDate(rs.getString("create_date"));
        m.setCreateBy(rs.getInt("update_by"));
        m.setUpdateDate(rs.getString("update_date"));
        return m;
    }

    private String buildGetSql(String id, String materialNo, String barcode, List<Object> queryParam) {
        StringBuilder sql = new StringBuilder(
            "SELECT m.id,m.material_no,m.material_name,m.foreign_name,m.material_type,t.type_name materialTypeName,m.brand,m.specifications_model,m.season,m.is_purchase,m.is_sell,m.is_inventory,m.unit_id,u.unit_name unitName,m.barcode,m.shops,s.shop_name shopName,m.is_batch,m.expiration_date,m.partner_id,b.partner_name partnerName,m.min_order,m.max_inventory,m.min_inventory,m.is_enable,m.description,m.is_delete,m.transaction_id,m.create_date,m.create_by,m.update_date,m.update_by,m.legal_unit,i.unit_name legalName,m.legal_translation_quantity,m.entry_unit,o.unit_name entryName,m.entry_translation_quantity,m.manufacturer,m.provenance,m.ebec,m.eben,m.hscode,m.post_tax_number,m.custom1,m.custom2,m.custom3,m.item_no,m.weight,m.post_tax_rate from material m left join material_unit u on m.unit_id = u.id left join material_unit i on m.legal_unit = i.id left join material_unit o on m.entry_unit = o.id left join business_partner b on m.partner_id = b.id left join shop s on m.shops = s.id left join material_type t on m.material_type = t.id WHERE m.is_delete=false ");
        if (StringUtils.hasText(id)) {
            sql.append(" and m.id=? ");
            queryParam.add(Integer.valueOf(id));
        }
        if (StringUtils.hasText(materialNo)) {
            sql.append(" and m.material_no=? ");
            queryParam.add(materialNo);
        }
        if (StringUtils.hasText(barcode)) {
            sql.append(" and m.barcode=? ");
            queryParam.add(barcode);
        }
        sql.append("order by m.id");
        return sql.toString();
    }

    public PagingResponse<GetMaterialResponse> getList(GetMaterialListRequest request) { // 分页查询
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        PagingResponse<GetMaterialResponse> response = new PagingResponse<GetMaterialResponse>();
        String keyWords = request.getKey();
        StringBuilder querySql = new StringBuilder(
            "SELECT m.id,m.material_no,m.material_name,m.foreign_name,m.material_type,m.brand,m.specifications_model,m.season,m.is_purchase,m.is_sell,m.is_inventory,m.unit_id,u.unit_name unitName,m.barcode,m.shops,m.is_batch,m.expiration_date,m.partner_id,p.partner_name partnerName,m.min_order,m.max_inventory,m.min_inventory,m.is_enable,m.description,m.is_delete,m.transaction_id,m.create_date,m.create_by,m.update_date,m.update_by,t.type_name materialTypeName from material m left join material_unit u on m.unit_id = u.id left join business_partner p on m.partner_id = p.id left join material_type t on m.material_type = t.id WHERE m.is_delete = 0 and m.is_enable= ? ");
        queryParam.add(request.getIsEnable());
        StringBuilder queryTotal = new StringBuilder("SELECT COUNT(0) from material m WHERE m.is_delete = 0 and m.is_enable= ? ");
        if (StringUtils.hasText(keyWords)) {// 条件查询
            querySql.append(" and (m.material_name like CONCAT('%',?,'%') or m.material_no like CONCAT('%',?,'%') or m.barcode like CONCAT('%',?,'%') or m.specifications_model like CONCAT('%',?,'%')) ");
            queryTotal.append(" and (m.material_name like CONCAT('%',?,'%') or m.material_no like CONCAT('%',?,'%') or m.barcode like CONCAT('%',?,'%') or m.specifications_model like CONCAT('%',?,'%')) ");
            queryParam.add(keyWords);
            queryParam.add(keyWords);
            queryParam.add(keyWords);
            queryParam.add(keyWords);
        }

        if (StringUtils.hasText(request.getMaterialTypeId())) {
            querySql.append(" AND FIND_IN_SET(m.material_type,queryAllChildMaterialType( ? )) ");
            queryTotal.append(" AND FIND_IN_SET(m.material_type,queryAllChildMaterialType( ? )) ");
            queryParam.add(request.getMaterialTypeId());
        }
        
        if (StringUtils.hasText(request.getSupplierId())) {
            querySql.append(" and m.partner_id = ?");
            queryTotal.append(" and m.partner_id = ?");
            queryParam.add(request.getSupplierId());
        }
        if (request.getLoggedSupId() != null) {
            querySql.append(" and m.partner_id = ?");
            queryTotal.append(" and m.partner_id = ?");
            queryParam.add(request.getLoggedSupId());
        }
        querySql.append(" ORDER BY m.id desc");
        totalParam.addAll(queryParam);
        if (StringUtils.hasText(request.getPageNo()) && StringUtils.hasText(request.getPageSize())) {
            querySql.append(" limit ?,?");
            queryParam.add((Integer.valueOf(request.getPageNo()) - 1) * Integer.valueOf(request.getPageSize()));
            queryParam.add(Integer.valueOf(request.getPageSize()));
        }
        // 需要添加商品的排序方式应该是在这里添加
        List<GetMaterialResponse> list = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<GetMaterialResponse>() {
            @Override
            public GetMaterialResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialResponseForList(rs);
            }
        }, queryParam.toArray());
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotal.toString(), totalParam.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    public PagingResponse<GetMaterialResponse> getListForSupplier(GetMaterialListRequest request) { // 分页查询
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        PagingResponse<GetMaterialResponse> response = new PagingResponse<GetMaterialResponse>();
        String keyWords = request.getKey();
        StringBuilder querySql = new StringBuilder(
            "SELECT SUM(i.inventory_amount) stockQuantity,m.id,m.material_no,m.material_name,m.foreign_name,m.material_type,m.brand,m.specifications_model,m.season,"
                + "m.is_purchase,m.is_sell,m.is_inventory,m.unit_id,u.unit_name unitName,m.barcode,m.shops,m.is_batch,m.expiration_date,m.partner_id,p.partner_name partnerName,m.min_order,m.max_inventory,"
                + "m.min_inventory,m.is_enable,m.description,m.is_delete,m.transaction_id,m.create_date,m.create_by,m.update_date,m.update_by,t.type_name materialTypeName from material m "
                + "left join material_unit u on m.unit_id = u.id left join business_partner p on m.partner_id = p.id left join material_type t on m.material_type = t.id LEFT JOIN inventory i "
                + "ON m.id = i.material_id WHERE m.is_enable = 1 and m.is_delete = 0 and m.partner_id = ? ");
        queryParam.add(request.getLoggedSupId());
        StringBuilder queryTotal = new StringBuilder("SELECT COUNT(0) from material m WHERE m.is_enable= 1 and m.is_delete = 0 and m.partner_id = ? ");
        if (StringUtils.hasText(keyWords)) {// 条件查询
            querySql.append(" and m.foreign_name like CONCAT('%',?,'%') or m.material_no like CONCAT('%',?,'%') or m.barcode like CONCAT('%',?,'%') or m.specifications_model like CONCAT('%',?,'%')");
            queryTotal
                .append(" and m.foreign_name like CONCAT('%',?,'%') or m.material_no like CONCAT('%',?,'%') or m.barcode like CONCAT('%',?,'%') or m.specifications_model like CONCAT('%',?,'%')");
            queryParam.add(keyWords);
            queryParam.add(keyWords);
            queryParam.add(keyWords);
            queryParam.add(keyWords);
        }
        querySql.append("GROUP BY i.material_id ORDER BY m.id DESC");
        totalParam.addAll(queryParam);
        if (StringUtils.hasText(request.getPageNo()) && StringUtils.hasText(request.getPageSize())) {
            querySql.append(" limit ?,?");
            queryParam.add((Integer.valueOf(request.getPageNo()) - 1) * Integer.valueOf(request.getPageSize()));
            queryParam.add(Integer.valueOf(request.getPageSize()));
        }
        // 需要添加商品的排序方式应该是在这里添加
        List<GetMaterialResponse> list = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<GetMaterialResponse>() {
            @Override
            public GetMaterialResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialResponseForList(rs);
            }
        }, queryParam.toArray());
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotal.toString(), totalParam.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    public Boolean delete(DeleteMaterialRequest request) {
        if (null == request.getIds()) {
            throw new BusinessProcessException("物料不存在，无法删除！");
        }
        String updateBy = request.getUpdateBy();
        List<String> ids = request.getIds();
        List<Object[]> deleteParams = new LinkedList<Object[]>();
        StringBuilder materialNames = null;
        for (int i = 0; i < ids.size(); i++) {
            String id = ids.get(i);
            if (checkMaterialUsedInfo(id) == 1) {
                throw new BusinessProcessException("您所删除的商品'" + get(id, null, null).getMaterialName() + "'已经被引用，无法删除！");
            }
            if (null == materialNames)
                materialNames = new StringBuilder(get(id, null, null).getMaterialName());
            else
                materialNames.append("," + get(id, null, null).getMaterialName());
            Object[] obj = new Object[] {id };
            deleteParams.add(obj);
        }
        int[] delete = jdbcAccessContext.batchExecute("MATERIAL.SQL_DELETE_MATERIAL", deleteParams);
        if (Arrays.asList(delete).contains(-1)) {
            throw new BusinessProcessException("批量删除某条信息时失败,已回滚");
        }
        addOperationLog("商品", "删除商品:[" + materialNames.toString() + "]", null, request.getUpdateBy() + "");
        return Boolean.TRUE;
    }

    /**
     * 物料导出
     * @param request
     * @return
     */
    public List<GetMaterialResponse> export(MaterialExportParamRequest request) {
        // 定义查询语句
        String sql = new String("select m.id id, " + "m.material_no materialNo, " + "m.material_name materialName, " + "m.foreign_name foreignName, " + "m.material_type materialType, "
            + "m.brand brand, " + "m.specifications_model specificationsModel, " + "m.season season, " + "m.is_purchase isPurchase, " + "m.is_sell isSell, " + "m.is_inventory isInventory, "
            + "m.unit_id unitId," + "m.barcode barcode," + "m.shops shops," + "s.name shopName, " + "m.expiration_date expirationDate," + "m.partner_id partnerId," + "bp.partner_name partnerName, "
            + "m.min_order minOrder," + "m.min_inventory minInventory," + "m.max_inventory maxInventory," + "m.description description," + "m.create_date createDate," + "m.create_by createBy,"
            + "m.update_date updateDate," + "m.update_by updateBy " + "from material m left join shopes s on s.id = m.shops " + "left join business_partner bp on bp.id = m.partner_id  "
            + "where m.is_delete = 0 and m.is_enable = 1");
        List<GetMaterialResponse> response = jdbcAccessContext.findWithoutSqlManager(sql, new RowMapper<GetMaterialResponse>() {

            @Override
            public GetMaterialResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialResponse(rs);
            }

            private GetMaterialResponse buildMaterialResponse(ResultSet rs) throws SQLException {
                GetMaterialResponse res = new GetMaterialResponse();
                res.setId(rs.getInt("id"));
                res.setMaterialNo(rs.getString("materialNo"));
                res.setMaterialName(rs.getString("materialName"));
                res.setForeignName(rs.getString("foreignName"));
                res.setMaterialType(rs.getInt("materialType"));
                res.setBrand(rs.getString("brand"));
                res.setSpecificationsModel(rs.getString("specificationsModel"));
                res.setSeason(rs.getString("season"));
                res.setUnitId(rs.getInt("unitId"));
                res.setBarcode(rs.getString("barcode"));
                res.setShops(rs.getInt("shops"));
                res.setShopName(rs.getString("shopName"));
                res.setExpirationDate(rs.getInt("expirationDate"));
                res.setPartnerId(rs.getInt("partnerId"));
                res.setPartnerName(rs.getString("partnerName"));
                res.setMinOrder(rs.getInt("minOrder"));
                res.setMinInventory(rs.getInt("minInventory"));
                res.setMaxInventory(rs.getInt("maxInventory"));
                res.setDescription(rs.getString("description"));
                res.setCreateDate(rs.getString("createDate"));
                res.setCreateBy(rs.getInt("createBy"));
                res.setUpdateDate(rs.getString("updateDate"));
                res.setUpdateBy(rs.getInt("updateBy"));
                return res;
            }
        }, new Object[] {});
        return response;
    }

    public List<GetMaterialResponse> getList(GetMaterialRequest request) {
        String keyword = request.getKeyword();
        StringBuilder querySql = new StringBuilder(
            "SELECT m.id,m.material_no materialNo,m.material_name materialName,m.foreign_name foreignName,m.specifications_model specificationsModel,m.brand,m.season,m.shops,m.barcode,m.unit_id unitId,m.is_batch isBatch, ma.unit_name unitName, m.min_order minOrder , m.expiration_date expirationDate from material m left join material_unit ma on m.unit_id=ma.id WHERE m.is_enable=true and (m.material_no like CONCAT('%',?,'%') or m.material_name like CONCAT('%',?,'%') or m.foreign_name like CONCAT('%',?,'%'))");
        int paramNum = 0;
        if (request.getIsPurchase() == true) {// 条件查询
            querySql.append(" and (m.is_purchase = true");
            paramNum++;
        }
        if (request.getIsSell() == true) {
            querySql.append(paramNum > 0 ? " or m.is_sell = true" : " and (m.is_sell = true");
        }
        if (request.getIsInventory() == true) {
            querySql.append(paramNum > 0 ? " or m.is_inventory = true" : " and (m.is_inventory = true");
        }
        querySql.append(") order by m.create_date");
        List<GetMaterialResponse> response = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<GetMaterialResponse>() {
            @Override
            public GetMaterialResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                GetMaterialResponse res = new GetMaterialResponse();
                res.setId(rs.getInt("id"));
                res.setMaterialNo(rs.getString("materialNo"));
                res.setMaterialName(rs.getString("materialName"));
                res.setForeignName(rs.getString("foreignName"));
                res.setBarcode(rs.getString("barcode"));
                res.setSpecificationsModel(rs.getString("specificationsModel"));
                res.setBrand(rs.getString("brand"));
                res.setSeason(rs.getString("season"));
                res.setShops(rs.getInt("shops"));
                res.setUnitId(rs.getInt("unitId"));
                res.setUnitName(rs.getString("unitName"));
                res.setMinOrder(rs.getInt("minOrder"));
                res.setIsBatch(rs.getBoolean("isBatch"));
                res.setExpirationDate(rs.getInt("expirationDate"));
                return res;
            }
        }, new Object[] { keyword, keyword, keyword });
        return response;
    }

    public List<GetMaterialResponse> getList(GetMaterialListByStorageRequest request) { // 分页查询
        List<Object> queryParam = new ArrayList<Object>();
        String keyWords = request.getKeyword();
        String storageId = request.getStorageId();
        StringBuilder querySql = new StringBuilder(
            "SELECT m.id,m.material_no,m.material_name,m.foreign_name,m.material_type,m.brand,m.specifications_model,m.season,m.is_purchase,m.is_sell,m.is_inventory,m.unit_id,m.barcode,m.shops,m.is_batch,m.expiration_date,m.partner_id,m.min_order,m.max_inventory,m.min_inventory,m.is_enable,m.description,m.is_delete,m.transaction_id,m.create_date,m.create_by,m.update_date,m.update_by from material m");
        if (StringUtils.hasText(storageId)) {
            querySql.append(" INNER JOIN inventory i ON m.id=i.material_id WHERE m.is_enable= true AND i.storage_id=? ");
            queryParam.add(storageId);
        } else
            querySql.append(" WHERE m.is_enable= true ");
        if (StringUtils.hasText(keyWords)) {// 条件查询
            querySql.append(" and ( m.material_name like CONCAT('%',?,'%') or m.material_no like CONCAT('%',?,'%') ) ");
            queryParam.add(keyWords);
            queryParam.add(keyWords);
        }
        querySql.append(" ORDER BY m.id ");
        return jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<GetMaterialResponse>() {
            @Override
            public GetMaterialResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialResponse(rs);
            }
        }, queryParam.toArray());
    }

    public PagingResponse<GetMaterialResponse> getOpenWinList(GetOpeanWinMaterialRequest request) {
        PagingResponse<GetMaterialResponse> pagingResponse = new PagingResponse<GetMaterialResponse>();
        List<Object> queryParams = new ArrayList<Object>();
        List<Object> queryTotalParams = new ArrayList<Object>();
        StringBuilder querySql = new StringBuilder();
        StringBuilder queryTotal = new StringBuilder();
        buildGetListSql(request, querySql, queryTotal, queryParams, queryTotalParams);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotal.toString(), queryTotalParams.toArray());
        List<GetMaterialResponse> response = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<GetMaterialResponse>() {
            @Override
            public GetMaterialResponse mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildOpenWinMaterialResponse(rs);
            }
        }, queryParams.toArray());
        pagingResponse.setRecords(response);
        pagingResponse.setTotalRecords(total);
        return pagingResponse;
    }

    private void buildGetListSql(GetOpeanWinMaterialRequest request, StringBuilder querySql, StringBuilder queryTatal, List<Object> list, List<Object> queryTotalParams) {
        querySql
            .append("SELECT m.id, material_no, m.material_name,m.foreign_name,m.brand,m.specifications_model,m.season,m.is_purchase,m.is_sell,m.is_inventory,m.unit_id,u.unit_name,m.barcode,m.shops,m.is_batch,m.expiration_date,m.partner_id,"
                + "m.min_order,m.min_inventory,m.max_inventory,m.is_enable,m.description,m.create_date,m.create_by FROM material m LEFT JOIN material_unit u ON m.unit_id = u.id left join material_type mt on m.material_type = mt.id");
        queryTatal.append(" SELECT count(1) FROM material m LEFT JOIN material_unit u ON m.unit_id = u.id left join material_type mt on m.material_type = mt.id");
//        if (StringUtils.hasText(request.getMaterialTypeId())) {
//            querySql.append(" LEFT JOIN material_type mt ON m.material_type = mt.id WHERE mt.id =? ");
//            queryTatal.append(" LEFT JOIN material_type mt ON m.material_type = mt.id WHERE mt.id =? ");
//            list.add(request.getMaterialTypeId());
//            queryTotalParams.add(request.getMaterialTypeId());
//        }
        // if (null != request.getMaterialGroupId()) {
        // querySql.append(" JOIN material_group_relation r ON m.id = r.material_id WHERE r.material_group_id =? ");
        // queryTatal.append(" JOIN material_group_relation r ON m.id = r.material_id WHERE r.material_group_id =? ");
        // list.add(request.getMaterialGroupId());
        // queryTotalParams.add(request.getMaterialGroupId());
        // }
        if (StringUtils.hasText(request.getKeyword())) {
            querySql.append(list.size() == 0 ? " where (m.material_name LIKE CONCAT('%' ,?, '%') or m.material_no LIKE CONCAT('%' ,?, '%'))"
                : " AND (m.material_name LIKE CONCAT('%' ,?, '%') or m.material_no LIKE CONCAT('%' ,?, '%')) ");
            queryTatal.append(list.size() == 0 ? " where (m.material_name LIKE CONCAT('%' ,?, '%') or m.material_no LIKE CONCAT('%' ,?, '%')) "
                : " AND (m.material_name LIKE CONCAT('%' ,?, '%') or m.material_no LIKE CONCAT('%' ,?, '%')) ");
            list.add(request.getKeyword());
            list.add(request.getKeyword());
            queryTotalParams.add(request.getKeyword());
            queryTotalParams.add(request.getKeyword());
        }
        if (StringUtils.hasText(request.getBarcode())) {
            querySql.append(list.size() == 0 ? " where m.barcode LIKE CONCAT('%' ,?, '%') " : " AND m.barcode LIKE CONCAT('%' ,?, '%')  ");
            queryTatal.append(list.size() == 0 ? " where m.barcode LIKE CONCAT('%' ,?, '%') " : " AND m.barcode LIKE CONCAT('%' ,?, '%')  ");
            list.add(request.getBarcode());
            queryTotalParams.add(request.getBarcode());
        }
        if (true == request.getIsPurchase()) {
            querySql.append(list.size() == 0 ? " where m.is_purchase = ? " : " and m.is_purchase = ? ");
            queryTatal.append(list.size() == 0 ? " where m.is_purchase = ? " : " and m.is_purchase = ? ");
            list.add(request.getIsPurchase());
            queryTotalParams.add(request.getIsPurchase());
        }
        if (true == request.getIsSell()) {
            querySql.append(list.size() == 0 ? " where m.is_sell = ? " : " and m.is_sell = ? ");
            queryTatal.append(list.size() == 0 ? " where m.is_sell = ? " : " and m.is_sell = ? ");
            list.add(request.getIsSell());
            queryTotalParams.add(request.getIsSell());
        }
        if (request.getIsInventory()) {
            querySql.append(list.size() == 0 ? " where m.is_inventory = ? " : " and m.is_inventory = ? ");
            queryTatal.append(list.size() == 0 ? " where m.is_inventory = ? " : " and m.is_inventory = ? ");
            list.add(request.getIsInventory());
            queryTotalParams.add(request.getIsInventory());
        }
        querySql.append(list.size() == 0 ? " where m.is_enable = true " : " and m.is_enable = true ");
        queryTatal.append(list.size() == 0 ? " where m.is_enable = true " : " and m.is_enable = true ");
        if (StringUtils.hasText(request.getMaterialTypeId())) {// 此处的materialType是前端传过来的商品类型的Id,特殊的一种验证
            String allChiledId = jdbcAccessContext.findStringWithoutSqlManager("SELECT queryAllChildMaterialType(?)", new Object[] { request.getMaterialTypeId() });
            if (StringUtils.hasText(allChiledId)) {
                allChiledId = allChiledId.substring(2, allChiledId.length());
            }
            querySql.append(list.size() == 0 ? " where mt.id in("+ allChiledId +") " : " and mt.id in("+ allChiledId +") ");
            queryTatal.append(list.size() == 0 ? " where mt.id in("+ allChiledId +") " : " and mt.id in("+ allChiledId +") ");
        }
        querySql.append(" ORDER BY m.create_date ").append(" LIMIT ?,? ");
        list.add((request.getPageNo() - 1) * request.getPageSize());
        list.add(request.getPageSize());
    }

    private static GetMaterialResponse buildOpenWinMaterialResponse(final ResultSet rs) throws SQLException {
        GetMaterialResponse m = new GetMaterialResponse();
        m.setId(rs.getInt("id"));
        m.setMaterialNo(rs.getString("material_no"));
        m.setMaterialName(rs.getString("material_name"));
        m.setForeignName(rs.getString("foreign_name"));
        m.setBrand(rs.getString("brand"));
        m.setSpecificationsModel(rs.getString("specifications_model"));
        m.setSeason(rs.getString("season"));
        m.setIsPurchase(rs.getBoolean("is_purchase"));
        m.setIsSell(rs.getBoolean("is_sell"));
        m.setIsInventory(rs.getBoolean("is_inventory"));
        m.setUnitId(rs.getInt("unit_id"));
        m.setUnitName(rs.getString("unit_name"));
        m.setBarcode(rs.getString("barcode"));
        m.setShopName(rs.getString("shops"));
        m.setIsBatch(rs.getBoolean("is_batch"));
        m.setExpirationDate(rs.getInt("expiration_date"));
        m.setMinOrder(rs.getInt("min_order"));
        m.setMinInventory(rs.getInt("min_inventory"));
        m.setMaxInventory(rs.getInt("max_inventory"));
        m.setIsEnable(rs.getBoolean("is_enable"));
        m.setDescription(rs.getString("description"));
        m.setCreateDate(rs.getString("create_date"));
        m.setCreateBy(rs.getInt("create_by"));
        return m;
    }

    public GetMaterialBatchResponse getMaterialBatch(GetMaterialBatchRequest request) {
        GetMaterialBatchResponse response = jdbcAccessContext.findUniqueResult("MATERIAL.SQL_SELECT_MATERIALBATCH_BY_MATERIALNO_AND_BATCH_NUMBER", new RowMapper<GetMaterialBatchResponse>() {
            @Override
            public GetMaterialBatchResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMaterialBatch(rs);
            }
        }, new Object[] { request.getMaterialId(), request.getMaterialBatch() });
        response.setMaterialId(request.getMaterialId());
        return response;
    }

    public GetMaterialBatchResponse buildMaterialBatch(final ResultSet rs) throws SQLException {
        GetMaterialBatchResponse response = new GetMaterialBatchResponse();
        response.setMaterialNo(rs.getString("material_no"));
        response.setMaterialName(rs.getString("material_name"));
        response.setBrand(rs.getString("brand"));
        response.setSeason(rs.getString("season"));
        response.setSpecificationsModel(rs.getString("specifications_model"));
        response.setBarcode(rs.getString("barcode"));
        response.setItemNo(rs.getString("item_no"));
        response.setUnitName(rs.getString("unitName"));
        response.setBatchNumber(rs.getString("batch_number"));
        response.setExpirationDate(rs.getString("expiration_date"));
        response.setProductionDate(rs.getString("production_date"));
        response.setCounts(rs.getString("counts"));
        return response;
    }

    private StorageLocationResponse buildStorageLocationResponseOnlyId(final ResultSet rs) throws SQLException {
        StorageLocationResponse locationResponse = new StorageLocationResponse();
        locationResponse.setStorageLocationId(rs.getInt("storageLocationId"));
        return locationResponse;
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
        response.setStorageLocationId(rs.getString("storageLocationId"));
        if (rs.getString("storageId") == null) {
            response.setStorageId("0");
        } else {
            response.setStorageId(rs.getString("storageId"));
        }
        try {
            response.setPrice(rs.getString("price"));// 2015-12-7这个数据是有错的，取成本是用通用的接口取的,这里的成本不要使用
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public List<GetInventoryCheckWithMaterialAndLocationInfo> assemInventoryCheckInfoWithStorageIdWithStorageArea(GetInventoryCheckWithMaterialAndLocationInfoRequest request) {
        List<String> locationCheckList = new ArrayList<String>();// 需要盘点的库位
        List<GetInventoryCheckWithMaterialAndLocationInfo> inventoryCheckInfos = new ArrayList<GetInventoryCheckWithMaterialAndLocationInfo>();
        if (StringUtils.hasText(request.getStorageAreaId())) {// 用户盘点选择了库区
            List<StorageLocationResponse> locationResponses = jdbcAccessContext.find("STORAGE.SQL_GET_STORAGE_LOCATION_ID_BY_STORAGE_AREA_ID", new RowMapper<StorageLocationResponse>() {// 由于装配不成功,使用jdbcAccess直接取的数据
                    @Override
                    public StorageLocationResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                        return buildStorageLocationResponseOnlyId(rs);// 只取Id
                    }
                }, new Object[] { request.getStorageAreaId() });
            // storageAreaRestService.getLocationListByAreaId(request.getStorageAreaId());
            for (StorageLocationResponse locationResponse : locationResponses) {
                locationCheckList.add(locationResponse.getStorageLocationId().toString());
            }
        } else {// 用户盘点未选择库区
            List<StorageLocationResponse> locationResponses = jdbcAccessContext.find("STORAGE.SQL_GET_STORAGE_LOCATION_ID_BY_STORAGE_ID", new RowMapper<StorageLocationResponse>() {
                @Override
                public StorageLocationResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                    return buildStorageLocationResponseOnlyId(rs);
                }
            }, new Object[] { request.getStorageId() });
            // storageLocationRestService.getLocationListByStorageId(request.getStorageId());
            for (StorageLocationResponse locationResponse : locationResponses) {
                locationCheckList.add(locationResponse.getStorageLocationId().toString());
            }
        }// 以上操作是选取盘点库位
        if (CollectionUtils.isEmpty(locationCheckList) && !request.getStorageId().equals("0")) {//虚拟仓库的逻辑单独列出
            throw new BusinessProcessException("所要盘点的仓库或者库区不含有任何库位");
        }
        for (String materialId : request.getIds()) {
            Boolean existFlag = new Boolean(false);
            List<GetMaterialBatchResponse> materialBatchResponses = jdbcAccessContext.find("INVENTORYALLOCATE.SQL_GET_STORAGELOCATION_WITH_MATERIALBATCH_BY_MATERIAL",//查询到所有商品在所有库位的信息
                new RowMapper<GetMaterialBatchResponse>() {
                    @Override
                    public GetMaterialBatchResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                        return buildMaterialBatchResponseForGetStorageLocationWithMaterialBatchByMaterial(rs);
                    }
                }, new Object[] { materialId, materialId });
            // pdaRestService.getStorageLocationWithMaterialBatchByMaterial(materialId);
            if (!CollectionUtils.isEmpty(materialBatchResponses)) {
                for (GetMaterialBatchResponse materialBatchResponse : materialBatchResponses) {
                    if (!request.getStorageId().equals("0") && locationCheckList.contains(materialBatchResponse.getStorageLocationId())) {
                        GetInventoryCheckWithMaterialAndLocationInfo inventoryCheckInfo = new GetInventoryCheckWithMaterialAndLocationInfo();
                        inventoryCheckInfo.setMaterialId(materialBatchResponse.getMaterialId());
                        assemInventoryCheckMaterialInfo(inventoryCheckInfo);
                        inventoryCheckInfo.setLocationNo(materialBatchResponse.getStorageLocationNo());
                        inventoryCheckInfo.setBatchNumber(materialBatchResponse.getBatchNumber());
                        inventoryCheckInfo.setProductionDate(materialBatchResponse.getProductionDate());
                        inventoryCheckInfo.setExpirationDate(materialBatchResponse.getExpirationDate());
                        inventoryCheckInfo.setInventoryNumber(StringUtils.hasText(materialBatchResponse.getCounts()) ? Double.valueOf(materialBatchResponse.getCounts()) : 0.0);
                        existFlag = true;
                        inventoryCheckInfos.add(inventoryCheckInfo);
                    } else if (request.getStorageId().equals("0") && materialBatchResponse.getStorageLocationId() == null) {//虚拟仓库逻辑单独列出
                        GetInventoryCheckWithMaterialAndLocationInfo inventoryCheckInfo = new GetInventoryCheckWithMaterialAndLocationInfo();
                        inventoryCheckInfo.setMaterialId(materialBatchResponse.getMaterialId());
                        assemInventoryCheckMaterialInfo(inventoryCheckInfo);
                        inventoryCheckInfo.setLocationNo("虚拟仓库不启用库位");
                        inventoryCheckInfo.setBatchNumber(materialBatchResponse.getBatchNumber());
                        inventoryCheckInfo.setProductionDate(materialBatchResponse.getProductionDate());
                        inventoryCheckInfo.setExpirationDate(materialBatchResponse.getExpirationDate());
                        inventoryCheckInfo.setInventoryNumber(StringUtils.hasText(materialBatchResponse.getCounts()) ? Double.valueOf(materialBatchResponse.getCounts()) : 0.0);
                        existFlag = true;
                        inventoryCheckInfos.add(inventoryCheckInfo);
                    }
                }
            }
            if (!existFlag) {
                GetInventoryCheckWithMaterialAndLocationInfo inventoryCheckInfo = new GetInventoryCheckWithMaterialAndLocationInfo();
                inventoryCheckInfo.setMaterialId(materialId);
                assemInventoryCheckMaterialInfo(inventoryCheckInfo);
                inventoryCheckInfo.setLocationNo("盘点区域不含此商品");
                inventoryCheckInfo.setBatchNumber("");
                inventoryCheckInfo.setProductionDate("");
                inventoryCheckInfo.setExpirationDate("");
                inventoryCheckInfo.setInventoryNumber(0.0);
                inventoryCheckInfos.add(inventoryCheckInfo);
            }
        }
        return inventoryCheckInfos;
    }

    private void assemInventoryCheckMaterialInfo(GetInventoryCheckWithMaterialAndLocationInfo inventoryCheckInfo) {
        GetMaterialResponse materialResponse = get(inventoryCheckInfo.getMaterialId(), null, null);
        inventoryCheckInfo.setMaterialName(materialResponse.getMaterialName());
        inventoryCheckInfo.setMaterialNo(materialResponse.getMaterialNo());
        inventoryCheckInfo.setBarcode(materialResponse.getBarcode());
        inventoryCheckInfo.setMaterialType(materialResponse.getMaterialTypeName());
        inventoryCheckInfo.setUnitName(materialResponse.getUnitName());
    }
}
