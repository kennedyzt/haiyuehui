package com.siping.service.businesspartner.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;

import com.siping.smartone.businesspartner.request.BankAccountRequest;
import com.siping.smartone.businesspartner.request.DeleteBankAccountRequest;
import com.siping.smartone.businesspartner.response.BankAccountResponse;

@Repository
public class BankAccountRestRepository {
    @Autowired
    private JDBCAccessContext jdbcAccessContext;

    public Boolean add(BankAccountRequest request) {
        Object[] addParams = new Object[] { request.getPartnerId(), request.getBankName(), request.getBankAddress(), request.getAccountName(), request.getAccountNo(), request.getSwiftCode(),
                request.getRemark(), request.getCreateBy() };
        int add = jdbcAccessContext.execute("BANKACCOUNT.SQL_INSERT_BANKACCOUNT", addParams);
        if (-1 == add) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean edit(BankAccountRequest request) {
        int edit = jdbcAccessContext.execute("BANKACCOUNT.SQL_UPDATE_BANKACCOUNT", new Object[] { request.getPartnerId(), request.getBankName(), request.getBankAddress(), request.getAccountName(),
                request.getAccountNo(), request.getSwiftCode(), request.getRemark(), request.getUpdateBy(), request.getId() });
        if (-1 == edit) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public BankAccountResponse get(Integer id) {
        BankAccountResponse response = null;
        try {
            response = jdbcAccessContext.findUniqueResult("BANKACCOUNT.SQL_SELECT_BANKACCOUNT_BYID", new RowMapper<BankAccountResponse>() {
                @Override
                public BankAccountResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                    return buildBankAccountResponse(rs);
                }
            }, new Object[] { id });
        } catch (Exception e) {
            throw new BusinessProcessException("数据库异常");
        }
        return response;
    }

    private static BankAccountResponse buildBankAccountResponse(final ResultSet rs) throws SQLException {
        BankAccountResponse b = new BankAccountResponse();
        b.setId(rs.getInt("id"));
        b.setPartnerId(rs.getInt("partnerId"));
        b.setBankName(rs.getString("bankName"));
        b.setBankAddress(rs.getString("bankAddress"));
        b.setAccountNo(rs.getString("accountNo"));
        b.setAccountName(rs.getString("accountName"));
        b.setSwiftCode(rs.getString("swiftCode"));
        b.setRemark(rs.getString("remark"));
        return b;
    }

    public Boolean delete(DeleteBankAccountRequest request) {
        List<Object[]> deleteParams = new LinkedList<Object[]>();
        for (Integer id : request.getIds()) {
            Object[] obj = new Object[] { id };
            deleteParams.add(obj);
        }
        int[] delete = jdbcAccessContext.batchExecute("BANKACCOUNT.SQL_DELETE_BANKACCOUNT", deleteParams);
        if (Arrays.asList(delete).contains(-1)) {
            throw new BusinessProcessException("批量删除某条信息时失败,已回滚");
        }
        return Boolean.TRUE;
    }

    public List<BankAccountResponse> getList(Integer id) {
        List<BankAccountResponse> response;
        response = jdbcAccessContext.find("BANKACCOUNT.SQL_SELECT_BANKACCOUNT", new RowMapper<BankAccountResponse>() {
            @Override
            public BankAccountResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildBankAccountResponse(rs);
            }
        }, new Object[] { id });
        return response;
    }
}
