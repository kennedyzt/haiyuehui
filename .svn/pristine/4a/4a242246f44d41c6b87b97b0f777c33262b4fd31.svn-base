package com.siping.intranet.crm.usr.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.group.response.GroupResponse;
import com.siping.smartone.login.request.AddLocalRequest;
import com.siping.smartone.login.request.AddUserRequest;
import com.siping.smartone.login.request.GetUserListForm;
import com.siping.smartone.login.request.UpdateUserRequest;
import com.siping.smartone.login.request.UserLoginRequest;
import com.siping.smartone.login.request.UserQueryParam;
import com.siping.smartone.login.response.UserInfoResponse;
import com.siping.smartone.login.response.UserLoginResponse;

@Repository
public class UsrRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Autowired
    private JSONConverter jsonConverter;

    @Value("${site.api.appkeys}")
    private String appKey;

    public UserLoginResponse login(UserLoginRequest request) {
        if (!StringUtils.hasText(request.getUsername())) {
            throw new BusinessProcessException("用户名为空不能登录！");
        }
        if (!StringUtils.hasText(request.getPassword())) {
            throw new BusinessProcessException("密码为空不能登录！");
        }
        String requestContent = JSONBinder.binder(UserLoginRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(UserLoginResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/usr/login").body(requestContent));
    }

    public Boolean add(AddUserRequest request) {
        validAddUserRequest(request);
        String requestContent = JSONBinder.binder(AddUserRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/usr/add").body(requestContent));
    }

    @Valid
    private void validAddUserRequest(AddUserRequest request) {
        if("admin".equalsIgnoreCase(request.getUserName().trim())){
            throw new BusinessProcessException("不可添加管理员账号！");
        }
        if (!StringUtils.hasText(request.getUserName()))
            throw new BusinessProcessException("请输入用户名！");
        if (!StringUtils.hasText(request.getPwd()))
            throw new BusinessProcessException("请输入登录密码！");
        if (request.getPwd().trim().length() < 6)
            throw new BusinessProcessException("请输入大于等于6位的密码！");
        if("BusiPartner".equals(request.getUserType().toString())){
            if(request.getObjectId() == null){
                throw new BusinessProcessException("请选择供应商！");
            }
        }
        if(!StringUtils.hasText(request.getUserAccount())){
            throw new BusinessProcessException("请选择用户账套！");
        }
    }

    @Valid
    private void validEditUserRequest(UpdateUserRequest request) {
        if (!StringUtils.hasText(request.getNickName()))
            throw new BusinessProcessException("请输入昵称！");
        if("BusiPartner".equals(request.getUserType().toString())){
            if(request.getObjectId() == null){
                throw new BusinessProcessException("请选择供应商！");
            }
        }
        if (!StringUtils.hasText(request.getPassword()))
            throw new BusinessProcessException("请输入登录密码！");
        if (request.getPassword().trim().length() < 6)
            throw new BusinessProcessException("请输入大于等于6位的密码！");
        if(!StringUtils.hasText(request.getUserAccount())){
            throw new BusinessProcessException("请选择用户账套！");
        }
    }

    public Boolean edit(UpdateUserRequest request) {
        if (null == request || null == request.getId()) {
            throw new BusinessProcessException("未选择需要编辑的用户！");
        }
        validEditUserRequest(request);
        String requestContent = JSONBinder.binder(UpdateUserRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/usr/edit").body(requestContent));
    }

    public Boolean resetPwd(Integer id, String oldPwd, String pwd, Integer updateBy) {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setId(id);
        request.setPassword(pwd);
        request.setUpdateBy(updateBy);
        request.setOldPwd(oldPwd);
        String requestContent = JSONBinder.binder(UpdateUserRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/usr/resetpwd").body(requestContent));
    }

    public UserLoginResponse get(Integer id, String username) {
        if (null == id && !StringUtils.hasText(username)) {
            throw new BusinessProcessException("条件不足不能获得用户！");
        }
        UpdateUserRequest request = new UpdateUserRequest();
        request.setId(id);
        request.setUserName(username);
        String requestContent = JSONBinder.binder(UpdateUserRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(UserLoginResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/usr/get").body(requestContent));
    }

    @SuppressWarnings("unchecked")
    public List<UserLoginResponse> getList() {
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(UserLoginResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/usr/getlist"));
    }

    public Boolean delete(Integer id, Integer updateBy) {
        if (null == id) {
            throw new BusinessProcessException("用户id为空不能删除！");
        }
        return stromaWebServiceApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/usr/delete/%s/%s").arguments(id, updateBy));
    }

    public Boolean languageSwitch(String userId, String language) {
        if (!StringUtils.hasText(userId) || !StringUtils.hasText(language)) {
            throw new BusinessProcessException("切换国际化信息失败！");
        }
        AddLocalRequest request = new AddLocalRequest();
        request.setUserId(userId);
        request.setLanguage(language);
        String requestContent = JSONBinder.binder(AddLocalRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/usr/languageswitch").body(requestContent));
    }

    public PagingResponse<UserInfoResponse> getAllUsers(Integer pageNo, Integer pageSize, GetUserListForm request) {
        if (null == pageNo && null == pageSize) {
            throw new BusinessProcessException("条件不足不能查询");
        }
        UserQueryParam userQueryParam = new UserQueryParam();
        userQueryParam.setPageNo(pageNo);
        userQueryParam.setPageSize(pageSize);
        userQueryParam.setKeywords(request.getKeywords());
        if(request.getIsDeleted() == null){
            userQueryParam.setIsEnable(null);
        }else{
            userQueryParam.setIsEnable(request.getIsDeleted() == 1 ? false : true);
        }
        String requestContent = jsonConverter.toString(userQueryParam);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(UserInfoResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/usr/getlist").body(requestContent));
    }

    public List<GroupResponse> getGroupsOfUser(Integer id) {
        if (null == id) {
            throw new BusinessProcessException("条件不足不能获得用户角色列表！");
        }
        UpdateUserRequest request = new UpdateUserRequest();
        request.setId(id);
        String requestContent = JSONBinder.binder(UpdateUserRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(List.class).elementTypes(GroupResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/usr/getgroups").body(requestContent));
    }
}
