package com.siping.intranet.crm.usr.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SecureSessionContext;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.SessionConstants;
import com.siping.intranet.crm.businesspartner.service.BusinessPartnerService;
import com.siping.intranet.crm.businesspartner.service.PartnerGroupService;
import com.siping.intranet.crm.group.serivce.GroupService;
import com.siping.intranet.crm.usr.service.UsrService;
import com.siping.smartone.businesspartner.request.GetBusinessPartnerListForm;
import com.siping.smartone.businesspartner.response.BusinessPartnerResponse;
import com.siping.smartone.businesspartner.response.PartnerGroupResponse;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.group.response.GroupResponse;
import com.siping.smartone.login.request.AddUserRequest;
import com.siping.smartone.login.request.GetUserListForm;
import com.siping.smartone.login.request.UpdateUserRequest;
import com.siping.smartone.login.request.UserLoginRequest;
import com.siping.smartone.login.response.UserInfoResponse;
import com.siping.smartone.login.response.UserLoginResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
public class UsrController extends StromaController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UsrService usrService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private SecureSessionContext secureSessionContext;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    BusinessPartnerService businessPartnerService;
    @Autowired
    PartnerGroupService partnerGroupService;

    @RequestMapping(value = "/usr/login", method = RequestMethod.GET)
    public String login(Map<String, Object> cache, ResultMsg resultMsg) {
        String msg = resultMsg.getMsg() == null ? "ok" : resultMsg.getMsg();
        cache.put("logMsg", msg);
        return PagePath.LOGIN_LOGIN;
    }

    @RequestMapping(value = "/usr/login", method = RequestMethod.POST)
    public String login(UserLoginRequest userLoginRequest, Map<String, Object> model, HttpServletRequest request) {
        UserLoginResponse response = null;
        try {
            response = usrService.login(userLoginRequest);
        } catch (Exception e) {
            ResultMsg resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.LOGIN_ERROR, new Object[] { ":" + e.getMessage() }));
            return login(model, resultMsg);
        }
        request.getSession().setAttribute("loginUser", response);
        String responseJson = JSONBinder.binder(UserLoginResponse.class).toJSON(response);
        sessionContext.set(SecuritySessionConstants.LOGGED_USER, responseJson);
        secureSessionContext.set(SecuritySessionConstants.LOGGED_USER, responseJson);
        sessionContext.set(SecuritySessionConstants.LOGGED_ID, response.getId());
        secureSessionContext.set(SecuritySessionConstants.LOGGED_ID, response.getId());
        if (null != response.getObjectId() && 0 != response.getObjectId()) {
            sessionContext.set(SecuritySessionConstants.LOGGED_USER_TYPE, response.getObjectId());
            secureSessionContext.set(SecuritySessionConstants.LOGGED_USER_TYPE, response.getObjectId());
        }
        sessionContext.set(SecuritySessionConstants.LOGGED_USER_NAME, response.getNickname());
        secureSessionContext.set(SecuritySessionConstants.LOGGED_USER_NAME, response.getNickname());
        sessionContext.set(SecuritySessionConstants.LOGGED_IN, Boolean.TRUE);
        secureSessionContext.set(SecuritySessionConstants.LOGGED_IN, Boolean.TRUE);
        if (StringUtils.hasText(response.getLocal())) {// 如果用户有自己的国际化语言，则使用自己的国际化方式
            i18nUtil.setLocale(new Locale(response.getLocal()));
            i18nUtil.setIsLanguageSwitch(Boolean.TRUE);// 说明是通过浏览器按钮指定的国际化
        }
        model.put("loginResponse", response);
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/usr/getPermissions", method = RequestMethod.POST)
    @ResponseBody
    public UserLoginResponse getUserPermission() {
        UserLoginResponse response = new UserLoginResponse();
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            response.setId(userId);
            if (userId == 0) {
                return response;
            }
            response = jsonConverter.fromString(UserLoginResponse.class, sessionContext.get(SecuritySessionConstants.LOGGED_USER));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/usr/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getUserList(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo, GetUserListForm request,
                              Map<String, Object> model) {
        buildUserResponse(pageSize, pageNo, request, model);
        return PagePath.ALL_USER;
    }

    private void buildUserResponse(Integer pageSize, Integer pageNo, GetUserListForm request, Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION)))
                request = jsonConverter.fromString(GetUserListForm.class, sessionContext.get(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION, jsonConverter.toString(request));
            PagingResponse<UserInfoResponse> pagingResponse = new PagingResponse<UserInfoResponse>();
            pagingResponse = usrService.getAllUsers(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, request);
            PageModel<UserInfoResponse> pageModel = new PageModel<UserInfoResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, pagingResponse.getTotalRecords(),
                pagingResponse.getRecords());
            model.put("pageModel", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
    }

    // 弹出框选择用户buildPurchaseReturnResponse
    @RequestMapping(value = "/usr/openwin", method = { RequestMethod.POST, RequestMethod.GET })
    public String openWin(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo, GetUserListForm request,
                          Map<String, Object> model) {
        buildUserResponse(pageSize, pageNo, request, model);
        return PagePath.OPEN_WIN_USER;
    }
    
    @RequestMapping(value = "/usr/add", method = RequestMethod.GET)
    public ModelAndView addUserView() {
        List<GroupResponse> groups = groupService.getAllGroups();
        Map<String, Object> model = new LinkedHashMap<String, Object>();
        model.put("groups", groups);
        return new ModelAndView(PagePath.ADD_USER, model);
    }

    @RequestMapping(value = "/usr/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg addUser(@RequestBody AddUserRequest addUserRequest) {
        ResultMsg resultMsg = null;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            addUserRequest.setCreateBy(userId);
            if (usrService.add(addUserRequest)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR));
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/usr/edit", method = RequestMethod.GET)
    public ModelAndView viewEditUser(@RequestParam("id") Integer id) {
        Map<String, Object> model = new LinkedHashMap<String, Object>();
        List<GroupResponse> groups = groupService.getAllGroups();
        model.put("groups", groups);
        model.put("userId", id);
        return new ModelAndView(PagePath.EDIT_USER, model);
    }

    @RequestMapping(value = "/usr/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg editUser(@RequestBody UpdateUserRequest request, Map<String, Object> model) {
        ResultMsg resultMsg = null;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setUpdateBy(userId);
            if (usrService.edit(request)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR));
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    // 用于修改密码
    @RequestMapping(value = "/usr/resetpwd", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg resetPwd(@RequestParam(required = true) String oldPwd, @RequestParam(required = true) String pwd) {
        ResultMsg resultMsg = null;
        Boolean isReset = false;
        try {
            Integer userid = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            isReset = usrService.resetPwd(userid, oldPwd, pwd, userid);
            if (isReset) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    // 修改密码
    @RequestMapping(value = "/usr/resetpass", method = RequestMethod.GET)
    public String resetPass() {
        return PagePath.USER_RESET_PASS;
    }

    @RequestMapping(value = "/usr/get", method = RequestMethod.GET)
    public ModelAndView viewUser(@RequestParam("id") Integer id, Map<String, Object> model) {
        model.put("userId", id);
        return new ModelAndView(PagePath.VIEW_USER, model);
    }

    @RequestMapping(value = "/usr/get", method = RequestMethod.POST)
    @ResponseBody
    public UserLoginResponse getUserById(@RequestParam(required = false) Integer id, @RequestParam(required = false) String userName, Map<String, Object> model) {
        UserLoginResponse response = null;
        try {
            response = usrService.get(id, userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/usr/businesspartner/openwin", method = { RequestMethod.POST, RequestMethod.GET })
    public String openWinGetPartner(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                    @RequestParam(value = "partnerType", required = true) Integer partnerType, GetBusinessPartnerListForm request, Map<String, Object> model) {
        List<PartnerGroupResponse> partnerGroups = null;
        if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION)))
            request = jsonConverter.fromString(GetBusinessPartnerListForm.class, sessionContext.get(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION));
        if (null == pageNo)
            sessionContext.set(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION, jsonConverter.toString(request));
        PagingResponse<BusinessPartnerResponse> pagingResponse = new PagingResponse<BusinessPartnerResponse>();
        pagingResponse = businessPartnerService.getAllBusinessPartner(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, partnerType, request);
        PageModel<BusinessPartnerResponse> pageModel = new PageModel<BusinessPartnerResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, pagingResponse.getTotalRecords(),
            pagingResponse.getRecords());
        model.put("pageModel", pageModel);
        partnerGroups = partnerGroupService.getList();
        model.put("partnerGroups", partnerGroups);
        return PagePath.OPEN_WIN_PARTNER_FORNAME;
    }
}
