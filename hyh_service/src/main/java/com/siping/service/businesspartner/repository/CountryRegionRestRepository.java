package com.siping.service.businesspartner.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;

import com.siping.smartone.businesspartner.request.CountryRegionRequest;
import com.siping.smartone.businesspartner.response.CountryRegionResponse;

@Repository
public class CountryRegionRestRepository {
    @Autowired
    private JDBCAccessContext jdbcAccessContext;

    public Boolean add(CountryRegionRequest request) {
        int counts = jdbcAccessContext.findInteger("COUNTRYREGION.SQL_GET_BY_COUNTRYREGION_NAME", new Object[] { request.getCountryRegionName() });
        if (0 < counts) {
            throw new BusinessProcessException("国家/地区名重复");
        }
        int add = jdbcAccessContext.execute("COUNTRYREGION.SQL_INSERT_COUNTRYREGION", new Object[] { request.getCountryRegionName(), request.getLoginId() });
        if (-1 == add) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public List<CountryRegionResponse> getList() {
        List<CountryRegionResponse> response = jdbcAccessContext.find("COUNTRYREGION.SQL_GET_ALL_COUNTRYREGION", new RowMapper<CountryRegionResponse>() {
            @Override
            public CountryRegionResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildCountryRegionResponse(rs);
            }
        }, new Object[] {});
        return response;
    }

    protected CountryRegionResponse buildCountryRegionResponse(ResultSet rs) throws SQLException {
        CountryRegionResponse c = new CountryRegionResponse();
        c.setId(rs.getInt("id"));
        c.setCountryRegionName(rs.getString("country_region_name"));
        return c;
    }

    public CountryRegionResponse get(Integer id) {
        CountryRegionResponse response = jdbcAccessContext.findUniqueResult("COUNTRYREGION.SQL_GET_COUNTRYREGION_BYID", new RowMapper<CountryRegionResponse>() {
            @Override
            public CountryRegionResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildCountryRegionResponse(rs);
            }
        }, new Object[] { id });
        return response;
    }

    public Boolean delete(Integer id, Integer loginId) {
        int counts = jdbcAccessContext.findInteger("COUNTRYREGION.SQL_CHECK_COUNTRYREGION_ISUSED", new Object[] { id });
        if (0 < counts) {
            throw new BusinessProcessException("该国家/地区被引用不能删除");
        }
        int delete = jdbcAccessContext.execute("COUNTRYREGION.SQL_DELETE_COUNTRYREGION_BYID", new Object[] { id });
        if (-1 == delete) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
