package com.siping.service.purchase.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.service.inventory.service.InventoryRestService;
import com.siping.service.material.service.MaterialRestService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.invoicing.inventory.inventory.request.AddInventoryRequest;
import com.siping.smartone.material.response.GetMaterialResponse;
import com.siping.smartone.purchase.request.PurchaseReturnsBatchDeleteRequest;
import com.siping.smartone.purchase.request.PurchaseReturnsItemRequest;
import com.siping.smartone.purchase.request.PurchaseReturnsOrderQueryParam;
import com.siping.smartone.purchase.request.PurchaseReturnsRequest;
import com.siping.smartone.purchase.response.PurchaseReturnsItemResponse;
import com.siping.smartone.purchase.response.PurchaseReturnsResponse;

@Repository
public class PurchaseReturnsRestRepository extends BillsRepository {
    @Autowired
    private InventoryRestService inventoryService;
    @Autowired
    private MaterialRestService materialRestService;

    public ResultMsg add(PurchaseReturnsRequest request) {
        // checkPostPeriodDateTime();
        String returnsNumber = generateBillsNumber("purchase_returns", "returns_number");
        if (!request.getReturnsNumber().equals(returnsNumber)) {
            request.setReturnsNumber(returnsNumber);
        }
        Object[] addParams = new Object[] { request.getReturnsNumber(), request.getFromBillsNo(), request.getBillsDate(), request.getPartnerId(), request.getOutboundStorageId(), request.getSummary(),
                request.getIsDraft(), request.getTotalPrice(), request.getGiftPrice(), request.getPayPrice(), request.getFavorablePrice(), request.getCreateBy(), request.getAuditor(),
                request.getAuditDate(), request.getOwner() };
        int add = 1;
        try {
            add = jdbcAccessContext.execute("PURCHASERETURNS.SQL_ADD_PURCHASERETURNS", addParams);
        } catch (Exception e) {
            throw new BusinessProcessException("新增采购退货单失败");
        }
        if (-1 == add) {
            throw new BusinessProcessException("新增采购退货单失败");
        }
        List<Object[]> addParamsList = new LinkedList<Object[]>();
        for (PurchaseReturnsItemRequest p : request.getPurchaseReturnsItemRequests()) {
            Object[] obj = new Object[] { request.getReturnsNumber(), p.getMaterialId(), p.getBatchNumber(), p.getProductionDate() == "" ? null : p.getProductionDate(), p.getCounts(),
                    p.getPurchasePrice(), p.getDiscount(), p.getAfterDiscount(), p.getTaxRate(), p.getTax(), p.getTotal(), p.getIsGift(), p.getRemark(), p.getCreateBy() };
            addParamsList.add(obj);
            // 若单据不为草稿，需做库存影响操作
            if (!request.getIsDraft()) {
                buildAddInventoryRequest(request, p);
            }
        }
        int[] adds = jdbcAccessContext.batchExecute("PURCHASERETURNS.SQL_ADD_PURCHASERETURNSITEM", addParamsList);
        if (Arrays.asList(adds).contains(-1)) {
            return new ResultMsg(true, "新增失败");
        }
        addOperationLog("采购退货单", "新增采购退货单，单据编号：[{0}],请求的ip为：[" + request.getIp() + "]，mac为：[" + request.getMac() + "]", returnsNumber, request.getCreateBy() + "");
        return new ResultMsg(true, "新增成功", request.getReturnsNumber());
    }

    private void buildAddInventoryRequest(PurchaseReturnsRequest returns, PurchaseReturnsItemRequest item) {
        AddInventoryRequest request = new AddInventoryRequest();
        request.setMaterialId(item.getMaterialId());
        request.setStorageId(0); // 仓库id为0，商品入到虚拟仓
        request.setStorageLocationId(null); // 虚拟仓没有库位
        request.setIsInbound(Boolean.FALSE);
        request.setBillsNo(returns.getReturnsNumber());
        request.setBillsDate(returns.getBillsDate());
        request.setCounts(item.getCounts());
        request.setTotal(item.getTotal());
        request.setPurchasePrice(item.getPurchasePrice() * item.getDiscount() / 100);
        request.setSellPrice(null);
        request.setCreateBy(returns.getCreateBy());
        request.setBatchNumber(item.getBatchNumber());
        request.setProductionDate(item.getProductionDate());
        GetMaterialResponse materialResponse = materialRestService.get(item.getMaterialId().toString());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            request.setExpirationDate(df.format(new Date(df.parse(item.getProductionDate()).getTime() + materialResponse.getExpirationDate() * 24 * 60 * 60 * 1000)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        inventoryService.add(request);
    }

    public Boolean delete(PurchaseReturnsBatchDeleteRequest request) {
        List<String> receiptNumbers = request.getReturnsNumbers();
        List<Object[]> deleteParams = new LinkedList<Object[]>();
        for (String str : receiptNumbers) {
            deleteParams.add(new Object[] { str });
        }
        int[] delete = jdbcAccessContext.batchExecute("PURCHASERETURNS.SQL_DELETE_PURCHASERETURNS_AND_PURCHASERETURNSITEM_BYRETURNSNUMBER", deleteParams);

        if (Arrays.asList(delete).contains(-1)) {
            return Boolean.FALSE;
        }
        addOperationLog("采购退货单", "新增采购退货单，单据编号：[{0}]", request.getReturnsNumbers().toString().replace("[", "").replace("]", ""), request.getUserId());
        return Boolean.TRUE;
    }

    public Boolean edit(PurchaseReturnsRequest request) {
        PurchaseReturnsResponse returns = get(request.getReturnsNumber());
        if (null == returns) {
            throw new BusinessProcessException("单据不存在，无法修改");
        }
        if (!returns.getIsDraft()) {
            throw new BusinessProcessException("该单据已经是正式单据，无法修改");
        }
        Object[] editParams = new Object[] { request.getPurchaseFlowId(), request.getOutboundStorageId(), request.getSummary(), request.getIsDraft(), request.getTotalPrice(), request.getGiftPrice(),
                request.getPayPrice(), request.getFavorablePrice(), request.getCreateBy(), request.getAuditor(), request.getAuditDate(), request.getOwner(), request.getReturnsNumber() };
        int edit = jdbcAccessContext.execute("PURCHASERETURNS.SQL_UPDATE_PURCHASERETURNS", editParams);
        if (-1 == edit) {
            return Boolean.FALSE;
        }
        int deleteOrderItem = jdbcAccessContext.execute("PURCHASERETURNS.SQL_DELETE_PURCHASERETURNSITEM", new Object[] { request.getReturnsNumber() });
        if (-1 == deleteOrderItem) {
            return Boolean.FALSE;
        }
        List<PurchaseReturnsItemRequest> items = request.getPurchaseReturnsItemRequests();
        List<Object[]> addParamsList = new ArrayList<Object[]>();
        for (PurchaseReturnsItemRequest item : items) {
            if (item.getProductionDate().equals("")) {
                item.setProductionDate(null);// 此处在库存日志可能会报出空指针的错误,经过实验发现如果给了空，是给了一个当前日期的初始值
            }
            if (!request.getIsDraft()) {
                buildAddInventoryRequest(request, item);
            }
            Object[] addTableParams = new Object[] { request.getReturnsNumber(), item.getMaterialId(), item.getBatchNumber(), item.getProductionDate(), item.getCounts(), item.getPurchasePrice(),
                    item.getDiscount(), item.getAfterDiscount(), item.getTaxRate(), item.getTax(), item.getTotal(), item.getIsGift(), item.getRemark(), item.getCreateBy() };
            addParamsList.add(addTableParams);
        }
        int[] adds = jdbcAccessContext.batchExecute("PURCHASERETURNS.SQL_ADD_PURCHASERETURNSITEM", addParamsList);
        if (Arrays.asList(adds).contains(-1)) {
            return Boolean.FALSE;
        }
        // 添加库存日志
        addOperationLog("采购退货单", "更新采购退货单，单据编号：[{0}]", request.getReturnsNumber(), request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    public PagingResponse<PurchaseReturnsResponse> getList(PurchaseReturnsOrderQueryParam request) {
        PagingResponse<PurchaseReturnsResponse> pagingResponse = new PagingResponse<PurchaseReturnsResponse>();
        long begin = (request.getPageNo() - 1) * request.getPageSize();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParams = new ArrayList<Object>();
        StringBuilder totalRecordsSql = new StringBuilder();
        List<PurchaseReturnsResponse> response = jdbcAccessContext.findWithoutSqlManager(buildGetListSql(request, begin, queryParam, totalRecordsSql, totalParams),
            new RowMapper<PurchaseReturnsResponse>() {
                @Override
                public PurchaseReturnsResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                    return buildPurchaseReturnsOrderResponse(rs);
                }
            }, queryParam.toArray());
        int totalRecords = jdbcAccessContext.findIntegerWithoutSqlManager(totalRecordsSql.toString(), totalParams.toArray());
        pagingResponse.setRecords(response);
        pagingResponse.setTotalRecords(totalRecords);
        return pagingResponse;
    }

    protected PurchaseReturnsResponse buildPurchaseReturnsOrderResponse(ResultSet rs) throws SQLException {
        PurchaseReturnsResponse p = new PurchaseReturnsResponse();
        p.setReturnsNumber(rs.getString("returnsNumber"));
        p.setBillsDate(DateUtils.formatDate(rs.getDate("billsDate"), "yyyy-MM-dd"));
        p.setOutboundStorageId(rs.getInt("outboundStorageId"));
        p.setOutboundStorageName(rs.getString("outboundStorageName"));
        p.setPayPrice(rs.getDouble("payPrice"));
        p.setPartnerName(rs.getString("partnerName"));
        p.setOwner(rs.getString("owner"));
        p.setSummary(rs.getString("summary"));
        String contactsName = rs.getString("contactsFirstName") + rs.getString("contactsLastName");
        if (contactsName.equals("nullnull")) {
            contactsName = "";
        }
        p.setContactsName(contactsName);
        return p;
    }

    private String buildGetListSql(PurchaseReturnsOrderQueryParam request, long begin, List<Object> queryParam, StringBuilder total, List<Object> totalParams) {
        Integer pageSize = request.getPageSize();
        String keyWords = request.getKeywords();
        String startDate = request.getStartDate();
        String endDate = request.getEndDate();
        Boolean isDraft = request.getIsDraft();
        total.append("SELECT count(*) from purchase_returns p left join business_partner b on p.partner_id=b.id where 1=1");
        StringBuilder sql = new StringBuilder(
            "SELECT p.returns_number returnsNumber, p.partner_id partnerId, p.outbound_storage_id outBoundStorageId,s.storage_name outBoundStorageName, p.summary, p.is_draft isDrage, p.total_price totalPrice, p.gift_price giftPrice, p.pay_price payPrice, p.favorable_price favorablePrice, p.bills_date billsDate, p.create_date createDate, p.create_by createBy, p.auditor, p.audit_date auditDate, p.owner,b.partner_name partnerName,b.contacts_first_name contactsFirstName,b.contacts_last_name contactsLastName from purchase_returns p left join business_partner b on p.partner_id=b.id left join storage s on p.outbound_storage_id = s.id where 1=1");
        if (StringUtils.hasText(keyWords)) {
            sql.append(" and (p.returns_number like CONCAT('%',?,'%') or b.partner_name like CONCAT('%',?,'%') or b.contacts_last_name like CONCAT('%',?,'%') or b.contacts_first_name like CONCAT('%',?,'%') or p.auditor like CONCAT('%',?,'%') or p.owner like CONCAT('%',?,'%'))");
            total
                .append(" and (p.returns_number like CONCAT('%',?,'%') or b.partner_name like CONCAT('%',?,'%') or b.contacts_last_name like CONCAT('%',?,'%') or b.contacts_first_name like CONCAT('%',?,'%') or p.auditor like CONCAT('%',?,'%') or p.owner like CONCAT('%',?,'%'))");
            queryParam.add(keyWords);
            queryParam.add(keyWords);
            queryParam.add(keyWords);
            queryParam.add(keyWords);
            queryParam.add(keyWords);
            queryParam.add(keyWords);
        }
        if (StringUtils.hasText(startDate)) {
            sql.append(" and Date(p.bills_date) >= ? ");
            total.append(" and Date(p.bills_date) >= ? ");
            queryParam.add(startDate);
        }
        if (StringUtils.hasText(endDate)) {
            sql.append(" and Date(p.bills_date) <= ? ");
            total.append(" and Date(p.bills_date) <= ? ");
            queryParam.add(endDate);
        }
        if (null != isDraft) {
            sql.append(" and p.is_draft = ? ");
            total.append(" and p.is_draft = ? ");
            queryParam.add(isDraft);
        }
        totalParams.addAll(queryParam);
        queryParam.add(begin);
        queryParam.add(pageSize);
        sql.append(" order by p.create_date desc limit ?,? ");
        return sql.toString();
    }

    public PurchaseReturnsResponse get(String returnsNumber) {
        PurchaseReturnsResponse response = jdbcAccessContext.findUniqueResult("PURCHASERETURNS.SQL_GET_PURCHASERETURNS_DETAILS", new RowMapper<PurchaseReturnsResponse>() {
            @Override
            public PurchaseReturnsResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPurchaseReturnsDetialsResponse(rs);
            }
        }, new Object[] { returnsNumber });
        List<PurchaseReturnsItemResponse> list = jdbcAccessContext.find("PURCHASERETURNS.SQL_GET_PURCHASERETURNSITEM_BYID", new RowMapper<PurchaseReturnsItemResponse>() {
            @Override
            public PurchaseReturnsItemResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                PurchaseReturnsItemResponse p = new PurchaseReturnsItemResponse();
                return buildPurchaseReturnsItemResponse(rs, p);
            }
        }, new Object[] { response.getReturnsNumber() });
        response.setPurchaseReturnsItems(list);
        return response;
    }

    protected PurchaseReturnsItemResponse buildPurchaseReturnsItemResponse(ResultSet rs, PurchaseReturnsItemResponse p) throws SQLException {
        p.setReturnsNumber(rs.getString("returnsNumber"));
        p.setMaterialId(rs.getInt("materialId"));
        p.setMaterialNo(rs.getString("materialNo"));
        p.setBatchNumber(rs.getString("batchNumber"));
        p.setProductionDate(DateUtils.formatDate(rs.getDate("productionDate"), "yyyy-MM-dd"));
        p.setCounts(rs.getDouble("counts"));
        p.setPurchasePrice(rs.getDouble("purchasePrice"));
        p.setDiscount(rs.getDouble("discount"));
        p.setAfterDiscount(rs.getDouble("afterDiscount"));
        p.setTaxRate(rs.getDouble("taxRate"));
        p.setTax(rs.getDouble("tax"));
        p.setTotal(rs.getDouble("total"));
        p.setIsGift(rs.getBoolean("isGift"));
        p.setRemark(rs.getString("remark"));
        p.setCreateDate(DateUtils.formatDate(rs.getDate("createDate"), "yyyy-MM-dd"));
        p.setCreateBy(rs.getInt("createBy"));
        p.setMaterialName(rs.getString("materialName"));
        p.setSpecificationsModel(rs.getString("specificationsModel"));
        p.setBrand(rs.getString("brand"));
        p.setBarcode(rs.getString("barcode"));
        p.setUnitName(rs.getString("unitName"));
        p.setSeason(rs.getString("season"));
        p.setIsBatch(rs.getBoolean("isBatch"));
        return p;
    }

    protected PurchaseReturnsResponse buildPurchaseReturnsDetialsResponse(ResultSet rs) throws SQLException {
        PurchaseReturnsResponse p = new PurchaseReturnsResponse();
        p.setReturnsNumber(rs.getString("returnsNumber"));
        p.setIsDraft(rs.getBoolean("isDraft"));
        p.setBillsDate(DateUtils.formatDate(rs.getDate("billsDate"), "yyyy-MM-dd"));
        p.setOutboundStorageId(rs.getInt("outboundStorageId"));
        p.setPartnerId(rs.getInt("partnerId"));
        p.setPartnerName(rs.getString("partnerName"));
        p.setPartnerCode(rs.getString("partnerCode"));
        p.setOwner(rs.getString("owner"));
        p.setOwnerName(rs.getString("ownerName"));
        p.setSummary(rs.getString("summary"));
        String contactsName = rs.getString("contactsFirstName") + rs.getString("contactsLastName");
        p.setContactsName(contactsName);
        p.setMobilephone(rs.getString("mobilephone"));
        p.setTotalPrice(rs.getDouble("totalPrice"));
        p.setGiftPrice(rs.getDouble("giftPrice"));
        p.setFavorablePrice(rs.getDouble("favorablePrice"));
        p.setPayPrice(rs.getDouble("payPrice"));
        p.setStorageName(rs.getString("storageName"));
        p.setAuditor(rs.getString("auditor"));
        return p;
    }
}
