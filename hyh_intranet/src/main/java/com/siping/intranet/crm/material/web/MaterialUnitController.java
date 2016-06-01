package com.siping.intranet.crm.material.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SecureSessionContext;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.SessionConstants;
import com.siping.intranet.crm.material.service.MaterialUnitService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.material.request.AddMaterialUnitRequest;
import com.siping.smartone.material.request.GetMaterialUnitListRequest;
import com.siping.smartone.material.response.GetMaterialUnitResponse;

@Controller
@LoginRequired
public class MaterialUnitController extends StromaController {// 计量单位
    @Autowired
    private MaterialUnitService materialUnitService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SecureSessionContext secureSessionContxet;
    @Autowired
    private JSONConverter jsonConverter;

    @RequestMapping(value = "/materialunit/add", method = RequestMethod.GET)
    public String add(AddMaterialUnitRequest request) {
        return PagePath.ADD_MATERIAL_UNIT;
    }

    @RequestMapping(value = "/materialunit/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("unitId") String unitId, Map<String, Object> model) {
        try {
            GetMaterialUnitResponse response = materialUnitService.get(unitId, null);
            model.put("materialUnit", response);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.EDIT_MATERIAL_UNIT;
    }

    @RequestMapping(value = "/materialunit/get", method = RequestMethod.GET)
    public String get(@RequestParam("unitId") String unitId, Map<String, Object> model) {
        GetMaterialUnitResponse response = materialUnitService.get(unitId, null);
        model.put("materialUnit", response);
        return PagePath.DETAIL_MATERIAL_UNIT;
    }

    @RequestMapping(value = "/materialunit/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getList(GetMaterialUnitListRequest request,@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize,Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.MATERIAL_UNIT_SEARCH_CONDITION)))
                request = jsonConverter.fromString(GetMaterialUnitListRequest.class, sessionContext.get(SessionConstants.MATERIAL_UNIT_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.MATERIAL_UNIT_SEARCH_CONDITION, jsonConverter.toString(request));
            PagingResponse<GetMaterialUnitResponse> response = new PagingResponse<GetMaterialUnitResponse>();
            response = materialUnitService.getList(pageNo, pageSize, request);
            if (pageNo == null) {
                pageNo = 1;
            }
            if (pageSize == null) {
                pageSize = 10;
            }
            PageModel<GetMaterialUnitResponse> pageModel = new PageModel<GetMaterialUnitResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());model.put("pageModel", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_MATERIAL_UNIT;
    }
    
    @RequestMapping(value = "/materialunit/openwin", method = { RequestMethod.POST, RequestMethod.GET })
    public String getWinList(GetMaterialUnitListRequest request,@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize,Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.MATERIAL_UNIT_SEARCH_CONDITION)))
                request = jsonConverter.fromString(GetMaterialUnitListRequest.class, sessionContext.get(SessionConstants.MATERIAL_UNIT_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.MATERIAL_UNIT_SEARCH_CONDITION, jsonConverter.toString(request));
            PagingResponse<GetMaterialUnitResponse> response = new PagingResponse<GetMaterialUnitResponse>();
            response = materialUnitService.getList(pageNo, pageSize, request);
            if (pageNo == null) {
                pageNo = 1;
            }
            if (pageSize == null) {
                pageSize = 10;
            }
            PageModel<GetMaterialUnitResponse> pageModel = new PageModel<GetMaterialUnitResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("pageModel", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_WIN_MATERIAL_UNIT;
    }
}
