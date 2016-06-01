package com.siping.service.common;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.log.LoggerFactory;
import org.stroma.framework.core.util.StringUtils;

@Repository
public class CommonRepository {

    @Autowired
    protected JDBCAccessContext jdbcAccessContext;

    private final Logger logger = LoggerFactory.getLogger(CommonRepository.class);

    // 增加操作日志
    public Boolean addOperationLog(String operationType, String operationLog, String billsNo, String createBy) {
        if (!StringUtils.hasText(operationType) || !StringUtils.hasText(operationLog)) {
            return false;
        }
        int execute = 0;
        StringBuilder builder = new StringBuilder("");
        try {
            if (StringUtils.hasText(billsNo)) {
                MessageFormat messageFormat = new MessageFormat(operationLog);
                builder.append(messageFormat.format(new Object[] { billsNo }));
            } else {
                builder.append(operationLog);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String insertSql = "INSERT INTO operation_log(uuid,operation_type,operation_log,create_date,create_by)VALUES(UUID_SHORT(),?,?,?,?)";
            execute = jdbcAccessContext.executeWithoutSqlManager(insertSql, new Object[] { operationType, builder.toString(), sdf.format(new Date()), createBy });
        } catch (Exception e) {
            this.logger.error("Add Operation Log error, operationType={}, operationLog={}, createBy={} " + e.getMessage(), new Object[] { operationType, operationLog, createBy });
        }
        if (execute == -1) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    // 查询批次不能重复,返回值为true表示可用,返回值为false表示不可用
    public synchronized Boolean checkBatchNumber(String batchNumber, String materialId) {
        Integer Flag = jdbcAccessContext.findInteger("MATERIAL.SQL_GET_BATCH_NUMBER_FLAG", new Object[] { batchNumber, materialId });
        if (0 == Flag) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
