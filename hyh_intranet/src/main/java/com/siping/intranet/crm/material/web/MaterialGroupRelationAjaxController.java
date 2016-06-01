package com.siping.intranet.crm.material.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.material.service.MaterialGroupRelationService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;

@Controller
public class MaterialGroupRelationAjaxController extends StromaController {
    @Autowired
    private MaterialGroupRelationService materialGroupRelationService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;

    @RequestMapping(value = "/materialgrouprelation/delete", method = RequestMethod.GET)
    @ResponseBody
    public boolean delete(@RequestParam(value = "id", required = true) String id, Map<String, Object> model) {
        Boolean isDelete = false;
        try {
            isDelete = materialGroupRelationService.delete(id);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return isDelete;
    }
}
