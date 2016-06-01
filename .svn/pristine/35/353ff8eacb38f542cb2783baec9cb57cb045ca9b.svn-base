package com.siping.system.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.smartone.common.OperationLog;

@Repository
public class OperationLogRestRepository extends BillsRepository {

    public PagingResponse<OperationLog> getList(OperationLog log) {
        PagingResponse<OperationLog> response = new PagingResponse<OperationLog>();
        List<Object> params = new ArrayList<Object>();
        StringBuilder queryListSql = new StringBuilder();
        StringBuilder queryTotalSql = new StringBuilder();
        loadDynamicQuerySql(log, params, queryListSql, queryTotalSql);
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotalSql.toString(), params.toArray());
        if (null != log.getPageNo() && null != log.getPageSize()) {
            queryListSql.append(" limit ?,? ");
            params.add((log.getPageNo() - 1) * log.getPageSize());
            params.add(log.getPageSize());
        }
        List<OperationLog> list = jdbcAccessContext.findWithoutSqlManager(queryListSql.toString(), new RowMapper<OperationLog>() {
            @Override
            public OperationLog mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildOperationLog(rs);
            }
        }, params.toArray());
        if (CollectionUtils.isNotEmpty(list)) {
            response.setRecords(list);
            response.setTotalRecords(total);
        }
        return response;
    }

    private OperationLog buildOperationLog(ResultSet rs) throws SQLException {
        OperationLog l = new OperationLog();
        l.setCreateBy(rs.getInt("create_by") + "");
        String createDate = rs.getString("create_date");
        if (StringUtils.hasText(createDate)) {
            l.setCreateDate(createDate.substring(0, 19));
        }
        l.setCreateUserName(rs.getString("user_name"));
        l.setNickName(rs.getString("nickname"));
        l.setOperationLog(rs.getString("operation_log"));
        l.setOperationType(rs.getString("operation_type"));
        l.setUuid(rs.getString("uuid"));
        return l;
    }

    private void loadDynamicQuerySql(OperationLog log, List<Object> params, StringBuilder queryListSql, StringBuilder queryTotalSql) {
        queryListSql.append("SELECT l.uuid,l.operation_type,l.operation_log,l.create_by,u.user_name,u.nickname,l.create_date from operation_log l LEFT JOIN usr u ON l.create_by=u.id ");
        queryTotalSql.append("SELECT count(l.uuid) from operation_log l LEFT JOIN usr u ON l.create_by=u.id ");
        int i = 0;
        if (StringUtils.hasText(log.getCreateBy())) {
            queryListSql.append(" where  l.create_by=? ");
            queryTotalSql.append(" where  l.create_by=? ");
            params.add(log.getCreateBy());
            i++;
        }
        if (StringUtils.hasText(log.getStartDate())) {
            if (i > 0) {
                queryListSql.append(" AND l.create_date >= '").append(parseDate(log.getStartDate(), Boolean.FALSE)).append("'");
                queryTotalSql.append(" AND l.create_date >= '").append(parseDate(log.getStartDate(), Boolean.FALSE)).append("'");
            } else {
                queryListSql.append(" WHERE l.create_date >= '").append(parseDate(log.getStartDate(), Boolean.FALSE)).append("'");
                queryTotalSql.append(" WHERE l.create_date >= '").append(parseDate(log.getStartDate(), Boolean.FALSE)).append("'");
            }
            i++;
        }
        if (StringUtils.hasText(log.getEndDate())) {
            if (i > 0) {
                queryListSql.append(" AND l.create_date <= '").append(parseDate(log.getEndDate(), Boolean.TRUE)).append("'");
                queryTotalSql.append(" AND l.create_date <= '").append(parseDate(log.getEndDate(), Boolean.TRUE)).append("'");
            } else {
                queryListSql.append(" WHERE l.create_date <= '").append(parseDate(log.getEndDate(), Boolean.TRUE)).append("'");
                queryTotalSql.append(" WHERE l.create_date <= '").append(parseDate(log.getEndDate(), Boolean.TRUE)).append("'");
            }
        }
        queryListSql.append(" ORDER BY L.create_date DESC ");
    }

    public Boolean delete(OperationLog log) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder deleteSQL = new StringBuilder("DELETE FROM operation_log where 1=1 ");
        if (StringUtils.hasText(log.getCreateBy())) {
            deleteSQL.append(" AND create_by = ? ");
            params.add(log.getCreateBy());
        }
        if (StringUtils.hasText(log.getStartDate())) {
            deleteSQL.append(" AND create_date >= '").append(parseDate(log.getStartDate(), Boolean.FALSE)).append("'");
        }
        if (StringUtils.hasText(log.getEndDate())) {
            deleteSQL.append(" AND create_date <= '").append(parseDate(log.getEndDate(), Boolean.TRUE)).append("'");
        }
        int delete = jdbcAccessContext.executeWithoutSqlManager(deleteSQL.toString(), params.toArray());
        if (-1 != delete)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }
}
