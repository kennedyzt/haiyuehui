package com.siping.intranet.crm.material.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.SessionConstants;
import com.siping.intranet.crm.material.service.MaterialGroupRelationService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.material.request.AddMaterialGroupRelationRequest;
import com.siping.smartone.material.request.GetMaterialConditionRequest;
import com.siping.smartone.material.response.GetMaterialGroupResponse;
import com.siping.smartone.material.response.GetMaterialResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
public class MaterialGroupRelationController extends StromaController {
    @Autowired
    private MaterialGroupRelationService materialGroupRelationService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private JSONConverter jsonConverter;

    @RequestMapping(value = "/materialgrouprelation/add", method = RequestMethod.POST)
    public String add(AddMaterialGroupRelationRequest request, Map<String, Object> model) {
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setCreateBy(userId);
            model.put("isAdd", materialGroupRelationService.add(request));
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return "login/login";
    }

    @RequestMapping(value = "/materialgrouprelation/edit", method = RequestMethod.POST)
    public String edit(AddMaterialGroupRelationRequest request, Map<String, Object> model) {
        Boolean isEdit = false;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            isEdit = materialGroupRelationService.edit(request);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        model.put("isEdit", isEdit);
        return "login/login";
    }

    // 查询当前商品属于哪个组
    @RequestMapping(value = "/materialgrouprelation/get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "materialno", required = false) String materialno, Map<String, Object> model) {
        List<GetMaterialGroupResponse> response = null; // 通过商品id或者商品货号查询商品组
        try {
            response = materialGroupRelationService.get(id, materialno);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        model.put("material", response);
        return "login/login";
    }

    @RequestMapping(value = "/materialgrouprelation/getlist", method = RequestMethod.GET)
    public String getList(GetMaterialConditionRequest request, @RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize,
                          Map<String, Object> model) {
        if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.MATERIAL_SEARCH_CONDITION_LIST)))
            request = jsonConverter.fromString(GetMaterialConditionRequest.class, sessionContext.get(SessionConstants.MATERIAL_SEARCH_CONDITION_LIST));
        if (null == pageNo)
            sessionContext.set(SessionConstants.MATERIAL_SEARCH_CONDITION_LIST, jsonConverter.toString(request));
        try {
            PagingResponse<GetMaterialResponse> response = materialGroupRelationService.getList(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, request);
            model.put("materialList", response);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return "login/login";
    }
}
