package com.siping.intranet.system.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.common.OperationLog;

@Repository
public class OperationLogRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    @SuppressWarnings("unchecked")
    public PagingResponse<OperationLog> getList(Integer pageNo, Integer pageSize, OperationLog log) {
        if (null != log) {
            log.setPageNo(pageNo);
            log.setPageSize(pageSize);
        }
        String requestContent = JSONBinder.binder(OperationLog.class).toJSON(log);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(OperationLog.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/operationlog/getlist")
            .body(requestContent));
    }

    public Boolean delete(OperationLog log) {
        String requestContent = JSONBinder.binder(OperationLog.class).toJSON(log);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/operationlog/delete").body(requestContent));
    }

}
