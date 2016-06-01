package com.siping.service.purchase.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.purchase.request.DeletePurchaseApplyOrderRequest;
import com.siping.smartone.purchase.request.PurchaseApplyOrderItemRequest;
import com.siping.smartone.purchase.request.PurchaseApplyOrderRequest;
import com.siping.smartone.purchase.response.PurchaseApplyOrderItemResponse;
import com.siping.smartone.purchase.response.PurchaseApplyOrderResponse;

@Repository
public class PurchaseApplyOrderRestRepository extends BillsRepository {
    @Autowired
    private JDBCAccessContext jdbcAccessContext;

    public ResultMsg add(PurchaseApplyOrderRequest order) {
        // checkPostPeriodDateTime();
        String orderNumber = generateBillsNumber("purchase_apply_order", "order_number");
        if (!order.getPurchaseOrderNumber().equals(orderNumber)) {
            order.setPurchaseOrderNumber(orderNumber);
        }
        Object[] addTitleParams = new Object[] { order.getPurchaseOrderNumber(), order.getApplierId(), order.getSummary(), order.getIsDraft(), order.getBillDate() == "" ? null : order.getBillDate(),
                null, null, order.getCreateBy() };
        // 插入表头数据
        int add = 1;
        try {
            add = jdbcAccessContext.execute("PURCHASEAPPLYORDER.SQL_ADD_PURCHASEAPPLYORDER", addTitleParams);
        } catch (Exception e) {
            throw new BusinessProcessException("新增采购申请单时出现了问题");
        }
        if (-1 != add) {
            if (order.getItems() == null || order.getItems().isEmpty()) {
                throw new BusinessProcessException("缺少表体信息");
            }
            // 插入表体数据
            int[] adds = addOrderItems(order, orderNumber);
            if (Arrays.asList(adds).contains(-1)) {
                throw new BusinessProcessException("新增订单项失败!");
            }
            addOperationLog("采购申请单", "新增采购申请单，单据编号：[{0}]", orderNumber, order.getCreateBy() + "");
            return new ResultMsg(true, "新增成功", order.getPurchaseOrderNumber());
        }
        return new ResultMsg(false, "新增失败");
    }

    public Boolean delete(DeletePurchaseApplyOrderRequest request) {
        if (null != request && !CollectionUtils.isEmpty(request.getOrderNumbers())) {
            String orderNumbers = request.getOrderNumbers().toString().replace("[", "").replace("]", "");

            // 删除草稿表头表尾
            int deleteOrder = jdbcAccessContext.execute("PURCHASEAPPLYORDER.SQL_DELETE_PURCHASEAPPLYORDER", new Object[] { orderNumbers });

            if (-1 != deleteOrder) {
                int deleteOrderItem = jdbcAccessContext.execute("PURCHASEAPPLYORDER.SQL_DELETE_PURCHASEAPPLYORDERITEM", new Object[] { orderNumbers });
                if (-1 != deleteOrderItem) {
                    addOperationLog("采购申请单", "删除采购申请单，单据编号：[{0}]", orderNumbers, request.getUserId());
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    // 编辑单据
    public Boolean edit(PurchaseApplyOrderRequest request) {
        PurchaseApplyOrderResponse order = getPAOOrder(request.getPurchaseOrderNumber());
        if (null == order)
            throw new BusinessProcessException("采购申请单不存在!");
        if (!order.getIsDraft())
            throw new BusinessProcessException("不能编辑正式的采购申请单!");
        Object[] params = new Object[] { request.getApplierId(), request.getSummary(), request.getIsDraft(), request.getBillDate() == "" ? null : order.getBillDate(),
                request.getOrderExpireDate() == "" ? null : request.getOrderExpireDate(), request.getLastDate() == "" ? null : request.getLastDate(), request.getAuditor(), request.getAuditDate(),
                request.getPurchaseOrderNumber() };
        // 编辑表头表尾信息
        int editOrder = jdbcAccessContext.execute("PURCHASEAPPLYORDER.SQL_UPDATE_PURCHASEAPPLYORDER", params);

        if (-1 != editOrder) {// 删除以前的订单项
            int deleteOrderItem = jdbcAccessContext.execute("PURCHASEAPPLYORDER.SQL_DELETE_PURCHASEAPPLYORDERITEM", new Object[] { request.getPurchaseOrderNumber() });
            if (-1 != deleteOrderItem) {// 新增订单项
                int[] batchAddItems = addOrderItems(request, request.getPurchaseOrderNumber());
                while (Arrays.asList(batchAddItems).contains(-1)) {
                    throw new BusinessProcessException("编辑订单项失败!");
                }
                addOperationLog("采购申请单", "更新采购申请单，单据编号：[{0}]", request.getPurchaseOrderNumber(), request.getCreateBy() + "");
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    // 根据申请单编号查询详情
    public PurchaseApplyOrderResponse get(String purchaseOrder) {
        // 查询表头表尾信息
        PurchaseApplyOrderResponse response = getPAOOrder(purchaseOrder);
        if (null == response)
            throw new BusinessProcessException("采购申请单不存在!");

        // 查询表体信息
        List<PurchaseApplyOrderItemResponse> itemList = jdbcAccessContext.find("PURCHASEAPPLYORDER.SQL_SELECT_PURCHASEAPPLYORDERITEMS", new RowMapper<PurchaseApplyOrderItemResponse>() {
            @Override
            public PurchaseApplyOrderItemResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPurchaseAppleOrderItemResponse(rs);
            }
        }, new Object[] { purchaseOrder });
        if (CollectionUtils.isEmpty(itemList))
            throw new BusinessProcessException("采购申请单没有订单项!");
        response.setItems(itemList);
        return response;
    }

    // 查询所有采购申请单
    public PagingResponse<PurchaseApplyOrderResponse> getAllApplyPO(CommonBillsRequest request) {
        PagingResponse<PurchaseApplyOrderResponse> pagingResponse = new PagingResponse<PurchaseApplyOrderResponse>();
        List<Object> paramsList = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        buildListQuerySQL(request, paramsList, sql, countSql);
        // 采购申请单总条数
        int totalRecords = jdbcAccessContext.findIntegerWithoutSqlManager(countSql.toString(), paramsList.toArray());

        if (null != request.getPageNo() && null != request.getPageSize()) {
            sql.append(" limit ?,? ");
            paramsList.add((request.getPageNo() - 1) * request.getPageSize());
            paramsList.add(request.getPageSize());
        }
        // 采购申请单表头信息数据
        List<PurchaseApplyOrderResponse> response = jdbcAccessContext.findWithoutSqlManager(sql.toString(), new RowMapper<PurchaseApplyOrderResponse>() {
            @Override
            public PurchaseApplyOrderResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPurchaseAppleOrderResponse(rs);
            }
        }, paramsList.toArray());
        pagingResponse.setRecords(response);
        pagingResponse.setTotalRecords(totalRecords);
        return pagingResponse;
    }

    // 查询sql语句拼接
    private void buildListQuerySQL(CommonBillsRequest request, List<Object> paramsList, StringBuilder sql, StringBuilder countSql) {
        sql.append("SELECT DISTINCT pao.bill_date billDate, pao.order_number orderNumber, pao.expire_date expireDate, pao.last_date lastDate, pao.is_draft isDraft, "
            + "pao.applier_id applierId, item.counts, u.user_name applierName,u.nickname nickName,  pao.summary summary, u2.user_name ownerName FROM purchase_apply_order pao "
            + "left join usr u2 on u2.id = pao.create_by left join purchase_apply_order_item i on i.order_number = pao.order_number "
            + "left join material m on m.id = i.material_id left join usr u on u.id = pao.applier_id "
            + "LEFT JOIN (  SELECT order_number orderNumber, sum(counts) counts FROM purchase_apply_order_item  GROUP BY orderNumber) item ON item.orderNumber = pao.order_number WHERE 1=1 ");
        countSql.append("SELECT COUNT(1) FROM (SELECT distinct pao.* FROM purchase_apply_order pao left join usr u2 on u2.id = pao.create_by "
            + "left join purchase_apply_order_item i on i.order_number = pao.order_number left join material m on m.id = i.material_id left join usr u on u.id = pao.applier_id "
            + "LEFT JOIN (  SELECT order_number orderNumber, sum(counts) counts FROM purchase_apply_order_item  GROUP BY orderNumber) item ON item.orderNumber = pao.order_number WHERE 1=1 ");
        if (null != request.getIsDraft()) {
            sql.append("  AND pao.is_draft = ?");
            countSql.append("  AND pao.is_draft = ?");
            paramsList.add(request.getIsDraft());
        }
        // 处理查询时间
        if (StringUtils.hasText(request.getStartDate())) {
            sql.append(" and Date(pao.bill_date) >= ? ");
            countSql.append(" and Date(pao.bill_date) >= ? ");
            paramsList.add(request.getStartDate());
        }
        if (StringUtils.hasText(request.getEndDate())) {
            sql.append(" and Date(pao.bill_date) <= ? ");
            countSql.append(" and Date(pao.bill_date) <= ? ");
            paramsList.add(request.getEndDate());
        }
        if (StringUtils.hasText(request.getKeywords())) {
            // 处理关键字查询
            // 合作伙伴编码、名称
            // sql.append(" AND ( ").append(" bp.partner_code like
            // CONCAT('%',?,'%') ").append(" OR bp.partner_name like
            // CONCAT('%',?,'%') ");
            // countSql.append(" AND ( ").append(" bp.partner_code like
            // CONCAT('%',?,'%') ").append(" OR bp.partner_name like
            // CONCAT('%',?,'%') ");
            // 仓库编码、名称
            // sql.append(" OR s.storage_no like CONCAT('%',?,'%') ").append("
            // OR s.storage_name like CONCAT('%',?,'%') ");
            // countSql.append(" OR s.storage_no like CONCAT('%',?,'%')
            // ").append(" OR s.storage_name like CONCAT('%',?,'%') ");
            // 商品货号、名称
            sql.append(" AND ( ").append(" m.material_no like CONCAT('%',?,'%') ").append(" OR m.material_name like CONCAT('%',?,'%') ");
            countSql.append(" AND ( ").append(" m.material_no like CONCAT('%',?,'%') ").append(" OR m.material_name like CONCAT('%',?,'%') ");
            // 商品国际编码 所有人名称
            sql.append(" OR m.barcode like CONCAT('%',?,'%') ").append(" OR u2.user_name like CONCAT('%',?,'%') ");
            countSql.append(" OR m.barcode like CONCAT('%',?,'%') ").append(" OR u2.user_name like CONCAT('%',?,'%') ");
            sql.append(" OR pao.order_number like CONCAT('%',?,'%') ");
            countSql.append(" OR pao.order_number like CONCAT('%',?,'%') ");
            for (int i = 0; i < 5; i++) {
                paramsList.add(request.getKeywords());
            }
            sql.append(")");
            countSql.append(") ");
        }
        sql.append(" order by pao.create_date desc");
        countSql.append(") a");
    }

    // 配置表体返回结果
    private PurchaseApplyOrderItemResponse buildPurchaseAppleOrderItemResponse(ResultSet rs) throws SQLException {
        PurchaseApplyOrderItemResponse response = new PurchaseApplyOrderItemResponse();
        response.setOrderNumber(rs.getString("orderNumber"));
        response.setId(rs.getInt("id"));
        response.setMaterialNo(rs.getString("materialNo"));
        response.setMaterialId(rs.getInt("materialId"));
        response.setMaterialName(rs.getString("materialName"));
        response.setSpecificationsModel(rs.getString("specificationsModel"));
        response.setBrand(rs.getString("brand"));
        response.setSeason(rs.getString("season"));
        response.setBarcode(rs.getString("barcode"));
        response.setUnitId(rs.getInt("unitId"));
        response.setUnitName(rs.getString("unitName"));
        response.setCounts(rs.getDouble("counts"));
        response.setLastDate(rs.getString("lastDate"));
        response.setDescription(rs.getString("description"));
        response.setRemark(rs.getString("remark"));
        return response;
    }

    // 配置表头返回结果对象
    private PurchaseApplyOrderResponse buildPurchaseAppleOrderResponse(ResultSet rs) throws SQLException {
        PurchaseApplyOrderResponse response = new PurchaseApplyOrderResponse();
        response.setOrderNumber(rs.getString("orderNumber"));
        response.setBillDate(rs.getString("billDate"));
        response.setOrderExpireDate(rs.getString("expireDate"));
        response.setLastDate(rs.getString("lastDate"));
        response.setSummary(rs.getString("summary"));
        response.setApplierId(rs.getString("applierId"));
        response.setApplierName(rs.getString("applierName"));
        response.setOwnerName(rs.getString("ownerName"));
        response.setIsDraft(rs.getBoolean("isDraft"));
        try {
            rs.findColumn("counts");
            response.setCounts(rs.getDouble("counts"));
        } catch (SQLException e) {
            // do nothing
        }
        // response.setCreateBy(rs.getInt("createBy"));
        // response.setCreateByName(rs.getString("ownerName"));
        try {
            response.setNickName(rs.getString("nickName"));
        } catch (SQLException e) {
        }
        return response;
    }

    private PurchaseApplyOrderResponse getPAOOrder(String orderNumber) {
        return jdbcAccessContext.findUniqueResult("PURCHASEAPPLYORDER.SQL_SELECT_PURCHASEAPPLYORDER_BYID", new RowMapper<PurchaseApplyOrderResponse>() {
            @Override
            public PurchaseApplyOrderResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPurchaseAppleOrderResponse(rs);
            }
        }, new Object[] { orderNumber });
    }

    // 新增表体数据函数
    private int[] addOrderItems(PurchaseApplyOrderRequest request, String orderNumber) {
        List<PurchaseApplyOrderItemRequest> list = request.getItems();
        List<Object[]> addParamsList = new ArrayList<Object[]>();
        for (PurchaseApplyOrderItemRequest item : list) {
            Object[] addTableParams = new Object[] { orderNumber, item.getMaterialId(), null, item.getCounts(), item.getRemark(), request.getCreateBy() };
            addParamsList.add(addTableParams);
        }
        // 插入表体数据
        int[] adds = jdbcAccessContext.batchExecute("PURCHASEAPPLYORDER.SQL_ADD_PURCHASEAPPLYORDERITEM", addParamsList);
        return adds;
    }

}
