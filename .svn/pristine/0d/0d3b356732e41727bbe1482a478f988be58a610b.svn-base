package com.siping.intranet.crm.inventory.web;

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

import com.siping.intranet.crm.inventory.service.InventoryCheckItemService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.inventory.request.AddInventoryCheckItemRequest;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class InventoryCheckItemAjaxController extends StromaController {
    @Autowired
    private InventoryCheckItemService inventoryCheckItemService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;

    @RequestMapping(value = "/inventorycheckitem/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg delete(@RequestParam("id") List<String> ids) {
        ResultMsg resultMsg = null;
        try {
            if(inventoryCheckItemService.delete(ids, "1"));{
                resultMsg=new ResultMsg(Boolean.TRUE, "删除成功");
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }
    
    @RequestMapping(value = "/inventorycheckitem/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(AddInventoryCheckItemRequest request) {
        ResultMsg resultMsg = null;
        try {
            inventoryCheckItemService.add(request);
            resultMsg=new ResultMsg(Boolean.TRUE, "添加成功");
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/inventorycheckitem/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg edit(AddInventoryCheckItemRequest request) {
        ResultMsg resultMsg = null;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            inventoryCheckItemService.edit(request);
            resultMsg=new ResultMsg(Boolean.TRUE, "修改成功");
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

}
