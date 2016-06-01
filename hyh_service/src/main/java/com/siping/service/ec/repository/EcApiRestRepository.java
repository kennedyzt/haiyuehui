package com.siping.service.ec.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;

import com.siping.service.common.BillsRepository;
import com.siping.smartone.ec.request.AddVendorRequest;
import com.siping.smartone.ec.request.PromisedProduct;
import com.siping.smartone.ec.response.ProductCustomsResponse;

@Repository
public class EcApiRestRepository extends BillsRepository {
    @Autowired
    private JDBCAccessContext jdbcAccessContext;

    public Boolean addShop(AddVendorRequest request) {
        String status = request.getStatus();
        if("add".equalsIgnoreCase(status)){
            int add = jdbcAccessContext.execute("EC.SQL_ADD_SHOP", new Object[] { request.getShopNo(), request.getShopName(), request.getContact(), request.getTelephone(), request.getAddress() });
            if (-1 == add) {
                return Boolean.FALSE;
            }
        }else if("edit".equalsIgnoreCase(status)){
            int edit = jdbcAccessContext.execute("EC.SQL_EDIT_SHOP", new Object[] { request.getShopName(), request.getContact(), request.getTelephone(), request.getAddress() ,request.getShopNo()});
            if (-1 == edit) {
                return Boolean.FALSE;
            }
            if (0 == edit) {
                throw new BusinessProcessException("系统中没有编号为"+request.getShopNo()+"的商家！");
            }
        }else if("delete".equalsIgnoreCase(status)){
            int delete = jdbcAccessContext.execute("EC.SQL_DELETE_SHOP", new Object[] { request.getShopNo() });
            if (-1 == delete) {
                return Boolean.FALSE;
            }
            if (0 == delete) {
                throw new BusinessProcessException("系统中没有编号为"+request.getShopNo()+"的商家！");
            }
        }else{
            throw new BusinessProcessException("无法辨别商家操作为新增、修改、还是删除！");
        }
        return Boolean.TRUE;
    }

    public ProductCustomsResponse checkMaterialNo(String materialno) {
        ProductCustomsResponse response = jdbcAccessContext.findUniqueResult("EC.SQL_CHECK_MATERIAL_NO_EXIST", new RowMapper<ProductCustomsResponse>() {
            @Override
            public ProductCustomsResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildProductCustomsResponse(rs);
            }
        }, new Object[] { materialno });
        return response;
    }

    private ProductCustomsResponse buildProductCustomsResponse(final ResultSet rs) throws SQLException {
        ProductCustomsResponse response = new ProductCustomsResponse();
        response.setBarcode(rs.getString("barcode"));
        response.setDeclareUnit(rs.getString("declareUnit"));
        response.setEbec(rs.getString("ebec"));
        response.setEben(rs.getString("eben"));
        response.setEntryQty(rs.getFloat("entryQty"));
        response.setEntryUnit(rs.getString("entryUnit"));
        response.setHscode(rs.getString("hscode"));
        response.setItemNo(rs.getString("itemNo"));
        response.setLegalQty(rs.getFloat("legalQty"));
        response.setLegalUnit(rs.getString("legalUnit"));
        response.setPostTaxNo(rs.getString("postTaxNo"));
        response.setProductName(rs.getString("productName"));
        response.setProductNo(rs.getString("productNo"));
        response.setSpecification(rs.getString("specification"));
        return response;
    }

    public String getOnhandBalance(String productno) {
        String count = jdbcAccessContext.findString("EC.SQL_GET_ONHAND_BALANCE", new Object[] { productno });
        return count;
    }

    public Boolean subtractQuantity(PromisedProduct request) {
        int result = jdbcAccessContext.execute("EC.SQL_PROMISED_QUANTITY_SUB", new Object[] { request.getQuantity(), request.getProductNo() });
        if (result > 0) {
            addOperationLog("减少订单已承诺数量", "商品货号："+request.getProductNo()+"，数量："+request.getQuantity(), null, "0");
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean addQuantity(PromisedProduct request) {
        int result = jdbcAccessContext.execute("EC.SQL_PROMISED_QUANTITY_ADD", new Object[] { request.getQuantity(), request.getProductNo() });
        if (result > 0) {
            addOperationLog("增加订单已承诺数量", "商品货号："+request.getProductNo()+"，数量："+request.getQuantity(), null, "0");
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
