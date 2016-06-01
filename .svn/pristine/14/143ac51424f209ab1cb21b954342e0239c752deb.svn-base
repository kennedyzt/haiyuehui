package com.siping.intranet.crm.group.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SecureSessionContext;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.SessionConstants;
import com.siping.intranet.crm.group.serivce.GroupService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.group.request.GetGroupListForm;
import com.siping.smartone.group.request.GroupRequest;
import com.siping.smartone.group.request.Permission;
import com.siping.smartone.group.response.GroupResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
public class GroupController extends StromaController {
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private GroupService groupService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private SecureSessionContext secureSessionContxet;

    @RequestMapping(value = "/group/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getGroupList(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo,
                              GetGroupListForm request, Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.GROUP_SEARCH_CONDITION)))
                request = jsonConverter.fromString(GetGroupListForm.class, sessionContext.get(SessionConstants.GROUP_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.GROUP_SEARCH_CONDITION, jsonConverter.toString(request));
            PagingResponse<GroupResponse> pagingResponse = new PagingResponse<GroupResponse>();
            pagingResponse = groupService.getAllGroups(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize,  request);
            PageModel<GroupResponse> pageModel = new PageModel<GroupResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, pagingResponse.getTotalRecords(),
            pagingResponse.getRecords());
            model.put("pageModel", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_GROUP;
    }

    @RequestMapping(value = "/group/add", method = RequestMethod.GET)
    public String addGroupView() {
        return PagePath.ADD_GROUP;
    }

    @RequestMapping(value = "/group/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg addGroup(@RequestBody GroupRequest addGroup) {
        ResultMsg resultMsg = null;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            addGroup.setCreateBy(userId);
            if (groupService.addGroup(addGroup)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_ERROR));
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/group/edit", method = RequestMethod.GET)
    public String viewEditGroup(@RequestParam("id") String id, Map<String, Object> model) {
        try {
            GroupResponse gorup = groupService.get(id, null);
            model.put("group", gorup);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
            return "";
        }
        return PagePath.EDIT_GROUP;
    }

    @RequestMapping(value = "/group/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg editGroup(@RequestBody GroupRequest request, Map<String, Object> model) {
        ResultMsg resultMsg = null;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setUpdateBy(userId);
            if (groupService.editGroup(request)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR));
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/group/get", method = RequestMethod.GET)
    public String viewGroup(@RequestParam("id") String id, Map<String, Object> model) {
        try {
            GroupResponse gorup = groupService.get(id, null);
            model.put("group", gorup);
            List<Permission> permissions = groupService.getGroupPermissions(gorup.getId(), "");
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
            return "";
        }
        return PagePath.VIEW_GROUP;
    }

    @RequestMapping(value = "/group/get", method = RequestMethod.POST)
    @ResponseBody
    public GroupResponse getGroup(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "groupName", required = false) String groupName) {
        GroupResponse group = null;
        try {
            group = groupService.get(id, groupName);
            List<Permission> permissions = groupService.getGroupPermissions(group.getId(), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return group;
    }

    @RequestMapping(value = "/group/getpermissions", method = RequestMethod.POST)
    @ResponseBody
    public List<Permission> getGroupPermissions(@RequestParam(value = "id") Integer id) {
        List<Permission> permissions = null;
        try {
            permissions = groupService.getGroupPermissions(id, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return permissions;
    }

    @RequestMapping(value = "/group/editpermissions", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg addGroupPermissions(@RequestBody GroupRequest groupRequest) {
        ResultMsg resultMsg= new ResultMsg();
        try {
            if (groupService.editGroupPermissions(groupRequest)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR));
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/group/viewPermissions", method = RequestMethod.GET)
    public String viewPermissions(@RequestParam("id") Integer id, @RequestParam("useraccount") String userAccount, Map<String, Object> model) {
        try {
            List<Permission> permissions = groupService.getGroupPermissions(id, userAccount);
            model.put("permissions", permissions);
            model.put("groupId", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PagePath.VIEW_GROUP_PERMISSIONS;
    }

    @RequestMapping(value = "/group/editPermissions", method = RequestMethod.GET)
    public String editPermissions(@RequestParam("id") Integer id, @RequestParam("useraccount") String userAccount, Map<String, Object> model) {
        try {
            List<Permission> permissions = groupService.getGroupPermissions(id, userAccount);
            model.put("permissions", permissions);
            model.put("groupId", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PagePath.EDIT_GROUP_PERMISSIONS;
    }
}
