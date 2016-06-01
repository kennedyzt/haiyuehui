package com.siping.intranet.crm.menu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.menu.service.MenuNodeService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
public class MenuNodeAjaxController extends StromaController {
    @Autowired
    private MenuNodeService menuNodeService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/menunode/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg deleteMenuNode(@RequestParam("id") Integer id) {
        ResultMsg resultMsg = null;
        try {
            boolean isDel = menuNodeService.deleteMenuNode(id, sessionContext.get(SecuritySessionConstants.LOGGED_ID) + "");
            if(isDel){
                resultMsg = new ResultMsg(true, i18nUtil.getMessage(ErrorCode.DELETE_SUCCESS));
            }else{
                resultMsg = new ResultMsg(false, i18nUtil.getMessage(ErrorCode.DELETE_ERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(false, i18nUtil.getMessage(ErrorCode.DELETE_ERROR,new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }
}
