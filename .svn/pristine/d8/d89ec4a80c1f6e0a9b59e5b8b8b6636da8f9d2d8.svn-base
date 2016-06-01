package com.siping.intranet.common.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.common.request.GetGenerateBillsNumber;

@Repository
public class CommonBillsNumberRepository {
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Value("${site.api.appkeys}")
    private String appKey;
    
    public String getGenerateBillsNumber(String tableName, String primaryKeyName){
        GetGenerateBillsNumber  getGenerateBillsNumber = new GetGenerateBillsNumber();
        getGenerateBillsNumber.setTableName(tableName);
        getGenerateBillsNumber.setPrimaryKeyName(primaryKeyName);
        String requestContent = JSONBinder.binder(GetGenerateBillsNumber.class).toJSON(getGenerateBillsNumber);
        return stromaWebServiceApi.post(EndPointBuilder.create(String.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/purchasereceipt/getGenerateBillsNumber").body(requestContent));
       }
}
