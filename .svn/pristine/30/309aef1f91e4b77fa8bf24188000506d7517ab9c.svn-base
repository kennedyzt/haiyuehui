package com.siping.intranet.crm.material.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.material.service.MaterialUnitService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.material.request.AddMaterialUnitRequest;
import com.siping.smartone.material.response.GetMaterialUnitResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class MaterialUnitAjaxController extends StromaController {
    @Autowired
    private MaterialUnitService materialUnitService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;

    @RequestMapping(value = "/materialunit/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg delete(@RequestParam("id") List<String> ids) {
        ResultMsg resultMsg = null;
        try {
            Integer updateBy = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            if (materialUnitService.delete(ids, updateBy.toString()));
            {
                resultMsg = new ResultMsg(Boolean.TRUE, "删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/materialunit/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(AddMaterialUnitRequest request) {
        ResultMsg resultMsg = null;
        try {
            Integer updateBy = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            materialUnitService.add(request);
            resultMsg = new ResultMsg(Boolean.TRUE, "添加成功");
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/materialunit/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg edit(AddMaterialUnitRequest request) {
        ResultMsg resultMsg = null;
        try {
            Integer updateBy = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            materialUnitService.edit(request);
            resultMsg = new ResultMsg(Boolean.TRUE, "修改成功");
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }
    
    @RequestMapping(value = "/materialunit/getlistwithcondition", method = RequestMethod.GET)
    @ResponseBody
    public List<GetMaterialUnitResponse> getlistwithcondition(@RequestParam("keyword") String keyword) {
        List<GetMaterialUnitResponse> responses = new ArrayList<GetMaterialUnitResponse>();
        try {
            responses = materialUnitService.getListWithCondition(keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responses;
    }
}
