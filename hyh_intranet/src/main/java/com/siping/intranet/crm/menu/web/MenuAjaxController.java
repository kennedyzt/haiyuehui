package com.siping.intranet.crm.menu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;

import com.siping.intranet.crm.menu.service.MenuService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;

@Controller
public class MenuAjaxController extends StromaController {
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/menutree/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg deleteMenuTree(@RequestParam("id") Integer id) {
        try {
            menuService.deleteMenuTree(id);
        } catch (Exception e) {
            return new ResultMsg(false, ErrorCode.DELETE_ERROR);// TODO
                                                                // 必须加入国际化信息
        }
        return new ResultMsg(true, ErrorCode.DELETE_SUCCESS);
    }
}
