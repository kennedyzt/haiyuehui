package com.siping.service.businesspartner.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.smartone.businesspartner.response.ShopResponse;
import com.siping.smartone.common.request.CommonBillsRequest;

@Repository
public class ShopRestRepository {
    @Autowired
    private JDBCAccessContext jdbcAccessContext;

    public PagingResponse<ShopResponse> getList(CommonBillsRequest request) {
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        PagingResponse<ShopResponse> response = new PagingResponse<ShopResponse>();
        String keyWords = request.getKeywords();
        StringBuilder querySql = new StringBuilder(
            "select id,shop_no,shop_name,is_delete,create_date,update_date,contact,telephone,address from shop where is_delete = false ");
        StringBuilder queryTotal = new StringBuilder("SELECT COUNT(0) from shop WHERE is_delete= false ");
        if (StringUtils.hasText(keyWords)) {// 条件查询
            querySql.append("and shop_no like CONCAT('%',?,'%') or shop_name like CONCAT('%',?,'%') ORDER BY id");
            queryTotal.append("and shop_no like CONCAT('%',?,'%') or shop_name like CONCAT('%',?,'%') ORDER BY id");
            queryParam.add(keyWords);
            queryParam.add(keyWords);;
        }
        totalParam.addAll(queryParam);
        if (StringUtils.hasText(request.getPageNo().toString()) && StringUtils.hasText(request.getPageSize().toString())) {
            querySql.append(" limit ?,? ");
            queryParam.add((Integer.valueOf(request.getPageNo()) - 1) * Integer.valueOf(request.getPageSize()));
            queryParam.add(Integer.valueOf(request.getPageSize()));
        }
        List<ShopResponse> list = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<ShopResponse>() {
            @Override
            public ShopResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildShopForList(rs);
            }
        }, queryParam.toArray());
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotal.toString(), totalParam.toArray());
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }
    private ShopResponse buildShopForList(ResultSet rs) throws SQLException{
        ShopResponse response = new ShopResponse();
        response.setId(rs.getString("id"));
        response.setShopNo(rs.getString("shop_no"));
        response.setShopName(rs.getString("shop_name"));
        response.setAddress(rs.getString("address"));
        response.setIsDelete(rs.getBoolean("is_delete"));
        response.setContact(rs.getString("contact"));
        response.setUpdateDate(rs.getString("update_date"));;
        response.setCreateDate(rs.getString("create_date"));
        response.setTelephone(rs.getString("telephone"));
        return response;
    }
}
