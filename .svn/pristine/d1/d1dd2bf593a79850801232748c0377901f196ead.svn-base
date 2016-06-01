package com.siping.intranet.crm.businesspartner.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.businesspartner.request.BankAccountRequest;
import com.siping.smartone.businesspartner.request.DeleteBankAccountRequest;
import com.siping.smartone.businesspartner.response.BankAccountResponse;
import com.siping.smartone.login.request.UpdateUserRequest;

@Repository
public class BankAccountRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(BankAccountRequest request) {
        validBankAccountRequest(request);
        String requestContent = JSONBinder.binder(BankAccountRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/bankaccount/add").body(requestContent));
    }

    @Valid
    private void validBankAccountRequest(BankAccountRequest request) {
        if (!StringUtils.hasText(request.getAccountNo()))
            throw new BusinessProcessException("银行账号不能为空！");
        if (!StringUtils.hasText(request.getBankName()))
            throw new BusinessProcessException("银行名不能为空！");
    }

    public Boolean edit(BankAccountRequest request) {
        if (null == request || null == request.getId()) {
            throw new BusinessProcessException("未选择需要编辑的账户！");
        }
        validBankAccountRequest(request); // 也需要校验必填项
        String requestContent = JSONBinder.binder(UpdateUserRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/bankaccount/edit").body(requestContent));
    }

    public BankAccountResponse get(Integer id) {
        if (null == id) {
            throw new BusinessProcessException("条件不足不能获得银行卡信息！");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(BankAccountResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/bankaccount/get/%s").arguments(id));
    }

    public Boolean delete(List<Integer> ids, Integer updateBy) {
        if (null == ids) {
            throw new BusinessProcessException("缺失主键不能删除银行账户");
        }
        if (null == updateBy) {
            throw new BusinessProcessException("当前登录出现问题，无法修改");
        }
        DeleteBankAccountRequest request = new DeleteBankAccountRequest();
        request.setIds(ids);
        request.setUpdateBy(updateBy);
        String requestContent = JSONBinder.binder(DeleteBankAccountRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/bankaccount/delete").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<BankAccountResponse> getList(Integer id) {
        if (null == id) {
            throw new BusinessProcessException("条件不足不能获得银行卡信息！");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(List.class).elementTypes(BankAccountResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey)
            .action("/bankaccount/getlist/%s").arguments(id));
    }

}
