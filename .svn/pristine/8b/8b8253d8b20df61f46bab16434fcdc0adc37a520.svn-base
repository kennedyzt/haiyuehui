package com.siping.intranet.crm.material.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.material.service.MaterialTypeService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.material.request.AddMaterialTypeRequest;
import com.siping.smartone.material.request.GetMaterialTypeListRequest;
import com.siping.smartone.material.response.GetMaterialTypeResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class MaterialTypeAjaxController extends StromaController {

    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private MaterialTypeService materialTypeService;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/materialtype/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(AddMaterialTypeRequest request) {
        ResultMsg resultMsg = null;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setCreateBy(userId);
            materialTypeService.add(request);
            resultMsg = new ResultMsg(Boolean.TRUE, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg =  new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/materialtype/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg edit(AddMaterialTypeRequest request) {
        ResultMsg resultMsg = null;
        try {
            if(request.getId().equals(request.getParentId())){
                throw new BusinessProcessException("不能选取自己作为自己的父级分类");
            }
            
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setUpdateBy(userId);
            materialTypeService.edit(request);
            resultMsg = new ResultMsg(Boolean.TRUE, "修改成功");
        } catch (Exception e) {
            resultMsg =  new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }
    
    @RequestMapping(value = "/materialtype/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg delete(@RequestParam("id") List<Integer> ids) {
        ResultMsg resultMsg = null;
        try {
            Integer updateBy = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            if (materialTypeService.delete(ids, updateBy)){
                resultMsg = new ResultMsg(Boolean.TRUE, "删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }
    
    @RequestMapping(value = "/materialtype/getlist", method = RequestMethod.GET)
    @ResponseBody
    public List<GetMaterialTypeResponse> getList(Map<String, Object> model) {
        List<GetMaterialTypeResponse> response = null;
        try {
            response = materialTypeService.getList();
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return response;
    }
    
    @RequestMapping(value = "/materialtype/geteditlist", method = RequestMethod.GET)
    @ResponseBody
    public List<GetMaterialTypeResponse> getList(@RequestParam("id") String id,Map<String, Object> model) {
        List<GetMaterialTypeResponse> response = null;
        try {
            response = materialTypeService.getList(id);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return response;
    }

    @RequestMapping(value = "/materialtype/getlistbyparentid", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageModel<GetMaterialTypeResponse> getListByParentId(@RequestBody GetMaterialTypeListRequest request, Map<String, Object> model) {
        PagingResponse<GetMaterialTypeResponse> response = new PagingResponse<GetMaterialTypeResponse>();
        PageModel<GetMaterialTypeResponse> pageModel = null;
        try {
            response = materialTypeService.getListByParentId(request);
            pageModel = new PageModel<GetMaterialTypeResponse>(Integer.valueOf(request.getPageNo()), Integer.valueOf(request.getPageSize()), response.getTotalRecords(), response.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return pageModel;
    }
}
