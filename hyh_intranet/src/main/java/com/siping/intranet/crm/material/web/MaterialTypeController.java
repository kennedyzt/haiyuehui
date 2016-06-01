package com.siping.intranet.crm.material.web;

import java.util.List;
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
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.SessionConstants;
import com.siping.intranet.crm.material.service.MaterialTypeService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.material.request.GetMaterialTypeListRequest;
import com.siping.smartone.material.response.GetMaterialTypeResponse;

@Controller
@LoginRequired
public class MaterialTypeController extends StromaController {// 物料分类
    @Autowired
    private MaterialTypeService materialTypeService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;

    @RequestMapping(value = "/materialtype/add", method = RequestMethod.GET)
    public String add() {
        return PagePath.ADD_MATERIAL_TYPE_MANAGEMENT;
    }

    @RequestMapping(value = "/materialtype/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Integer id, Map<String, Object> model) {
        try {
            GetMaterialTypeResponse response =  materialTypeService.get(id, null);
            model.put("materialType", response);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.EDIT_MATERIAL_TYPE_MANAGEMENT;
    }

    @RequestMapping(value = "/materialtype/manage", method = RequestMethod.GET)
    public String manage() {
        return PagePath.MANAGE_MATERIAL_TYPE_MANAGEMENT;
    }

    @RequestMapping(value = "/materialtype/show", method = { RequestMethod.POST, RequestMethod.GET })
    public String show(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo, CommonBillsRequest request,
                       Map<String, Object> model) {
        List<GetMaterialTypeResponse> materialTypes = null;
        if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.MATERIAL_TYPE_SEARCH_CONDITION)))
            request = jsonConverter.fromString(CommonBillsRequest.class, sessionContext.get(SessionConstants.MATERIAL_TYPE_SEARCH_CONDITION));
        if (null == pageNo)
            sessionContext.set(SessionConstants.MATERIAL_TYPE_SEARCH_CONDITION, jsonConverter.toString(request));
        PagingResponse<GetMaterialTypeResponse> response = new PagingResponse<GetMaterialTypeResponse>();
        response = materialTypeService.getAllMaterialType(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, request);
        PageModel<GetMaterialTypeResponse> pageModel = new PageModel<GetMaterialTypeResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, response.getTotalRecords(),
            response.getRecords());
        model.put("pageModel", pageModel);
        materialTypes = materialTypeService.getList();
        model.put("materialTypes", materialTypes);
        return PagePath.SHOW_MATERIAL_TYPE_MANAGEMENT;
    }

    @RequestMapping(value = "/materialtype/get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "typeName", required = false) String typeName, Map<String, Object> model) {
        GetMaterialTypeResponse response = null;
        try {
            response = materialTypeService.get(id, typeName);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        model.put("material", response);
        return PagePath.ADD_MATERIAL_TYPE_MANAGEMENT;
    }

    @RequestMapping(value = "/materialtype/getalllist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, GetMaterialTypeListRequest request,
                          Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.MATERIAL_TYPE_SEARCH_CONDITION)))
                request = jsonConverter.fromString(GetMaterialTypeListRequest.class, sessionContext.get(SessionConstants.MATERIAL_TYPE_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.MATERIAL_TYPE_SEARCH_CONDITION, jsonConverter.toString(request));
            PagingResponse<GetMaterialTypeResponse> response = new PagingResponse<GetMaterialTypeResponse>();
            if (pageNo == null) {
                pageNo = 1;
            }
            if (pageSize == null) {
                pageSize = 10;
            }
            response = materialTypeService.getAllMaterialType(pageNo, pageSize, request);
            PageModel<GetMaterialTypeResponse> pageModel = new PageModel<GetMaterialTypeResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("pageModel", pageModel);
            model.put("searchCondition", request);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_MATERIAL_TYPE_MANAGEMENT;
    }
    
//    @RequestMapping(value = "/materialtype/getlist", method = RequestMethod.GET)
//    public String getList(Map<String, Object> model) {
//        try {
//            List<GetMaterialTypeResponse> response = materialTypeService.getList();
//            model.put("materialTypeList", response);
//        } catch (Exception e) {
//            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
//        }
//        return PagePath.ALL_MATERIAL_TYPE_MANAGEMENT;
//    }
}
