package com.siping.intranet.crm.inventory.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.inventory.service.InventoryCheckItemService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.inventory.response.GetInventoryCheckItemResponse;

@Controller
@LoginRequired
public class InventoryCheckItemController extends StromaController {
    @Autowired
    private InventoryCheckItemService inventoryCheckItemService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/inventorycheckitem/add", method = RequestMethod.GET)
    public String addStorageView() {
        return PagePath.WELCOME;
    }

    @RequestMapping(value = "/inventorycheckitem/edit", method = RequestMethod.GET)
    public String editStorageView(@RequestParam("checkNumber") String checkNumber, Map<String, Object> model) {
        try {
            GetInventoryCheckItemResponse response =  inventoryCheckItemService.get(checkNumber);
            model.put("s", response);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        
        return PagePath.WELCOME;
    }
    
    
    // 该方法也适用于修改密码
    @RequestMapping(value = "/inventorycheckitem/get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "checkNumber", required = false) String checkNumber,Map<String, Object> model) {
        GetInventoryCheckItemResponse response = null; // 查询单个物料可以单独用id查询，单独用物料编号查询，单独用国际编码查询均可，当然可以三个条件都提供来查询
        try {
            response = inventoryCheckItemService.get(checkNumber);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        model.put("inventoryCheckItem", response);
        return "login/login";
    }

    @RequestMapping(value = "/inventorycheckitem/getlist", method = RequestMethod.GET)
    public String getList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, Map<String, Object> model) {
        try {
            PagingResponse<GetInventoryCheckItemResponse> response = inventoryCheckItemService.getList(pageNo, pageSize);
            model.put("inventoryCheckItemList", response);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return "login/login";
    }
}
