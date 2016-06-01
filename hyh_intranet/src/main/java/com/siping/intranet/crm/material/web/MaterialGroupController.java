package com.siping.intranet.crm.material.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.material.service.MaterialGroupService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.material.request.AddMaterialGroupRequest;
import com.siping.smartone.material.response.GetMaterialGroupResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
public class MaterialGroupController extends StromaController {
    @Autowired
    private MaterialGroupService materialUsageService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/materialgroup/add", method = RequestMethod.POST)
    public String add(AddMaterialGroupRequest request, Map<String, Object> model) {
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setCreateBy(userId);
            model.put("isAdd", materialUsageService.add(request));
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return "login/login";
    }

    @RequestMapping(value = "/materialgroup/edit", method = RequestMethod.POST)
    public String edit(AddMaterialGroupRequest request, Map<String, Object> model) {
        Boolean isEdit = false;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setUpdateBy(userId);
            isEdit = materialUsageService.edit(request);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        model.put("isEdit", isEdit);
        return "login/login";
    }

    // 该方法也适用于修改密码
    @RequestMapping(value = "/materialgroup/get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "groupName", required = false) String groupName, Map<String, Object> model) {
        GetMaterialGroupResponse response = null; // 查询单个物料可以单独用id查询，单独用物料编号查询，单独用国际编码查询均可，当然可以三个条件都提供来查询
        try {
            response = materialUsageService.get(id, groupName);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        model.put("material", response);
        return "login/login";
    }

    @RequestMapping(value = "/materialgroup/getlist", method = RequestMethod.GET)
    public String getList(Map<String, Object> model) {
        try {
            List<GetMaterialGroupResponse> response = materialUsageService.getList();
            model.put("materialList", response);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return "login/login";
    }
}
