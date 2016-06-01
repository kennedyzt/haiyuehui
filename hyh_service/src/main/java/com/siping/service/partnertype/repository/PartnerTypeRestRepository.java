package com.siping.service.partnertype.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;

import com.siping.smartone.partnertype.request.PartnerTypeRequest;
import com.siping.smartone.partnertype.response.PartnerTypeResponse;

@Repository
public class PartnerTypeRestRepository {
    @Autowired
    private JDBCAccessContext jdbcAccessContext;

    private Integer getPartnerTypeByTypeName(String typeName) {
        int select = jdbcAccessContext.findInteger("PARTNERTYPE.SQL_SELECT_BYTYPENAME", new Object[] { typeName });
        return select;
    }

    public Boolean addPartnerType(PartnerTypeRequest request) {
        if (getPartnerTypeByTypeName(request.getTypeName()) > 0) {
            throw new BusinessProcessException("类型名不能重复");
        }
        Object[] addParams = new Object[] { request.getTypeName(), request.getDescription(), request.getCreateBy() };
        int add = jdbcAccessContext.execute("PARTNERTYPE.SQL_INSERT_PARTNERTYPE", addParams);
        if (-1 == add) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean editPartnerType(PartnerTypeRequest request) {
        if (getPartnerTypeByTypeName(request.getTypeName()) > 0) {
            throw new BusinessProcessException("类型名不能重复");
        }
        Object[] editParams = new Object[] { request.getTypeName(), request.getDescription(), request.getUpdateBy(), request.getId() };
        int edit = jdbcAccessContext.execute("PARTNERTYPE.SQL_UPDATE_PARTNERTYPE", editParams);
        if (-1 == edit) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean deletePartnerTypeById(Integer id, Integer loginId) {
        Object[] deleteParams = new Object[] { loginId, id };
        int delete = jdbcAccessContext.execute("PARTNERTYPE.SQL_DELETE_PARTNERTYPE", deleteParams);
        if (-1 == delete) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public List<PartnerTypeResponse> getAllPartnerType() {
        List<PartnerTypeResponse> response = jdbcAccessContext.find("PARTNERTYPE.SQL_SELECT_PARTNERTYPE", new RowMapper<PartnerTypeResponse>() {
            @Override
            public PartnerTypeResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                PartnerTypeResponse p = new PartnerTypeResponse();
                p.setId(rs.getInt("id"));
                p.setTypeName(rs.getString("typeName"));
                p.setDescription(rs.getString("description"));
                p.setCreateDate(rs.getDate("createDate"));
                p.setCreateBy(rs.getInt("createBy"));
                p.setUpdateDate(rs.getDate("updateDate"));
                p.setUpdateBy(rs.getInt("updateBy"));
                return p;
            }
        });
        return response;
    }

}
