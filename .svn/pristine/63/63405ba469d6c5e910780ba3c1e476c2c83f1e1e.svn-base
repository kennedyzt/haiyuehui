package com.siping.intranet.crm.inventory.web;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.inventory.service.InventoryCheckService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.inventory.request.AddInventoryCheckRequest;
import com.siping.smartone.inventory.request.AddInventoryCheckRequestNew;
import com.siping.smartone.inventory.response.GetMaterialBatchNumberResponse;
import com.siping.smartone.inventory.response.GetInventoryCheckResponse;
import com.siping.smartone.inventory.response.GetInventoryCheckResponseNew;
import com.siping.smartone.login.response.UserLoginResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class InventoryCheckAjaxController extends StromaController {
    @Autowired
    private InventoryCheckService inventoryCheckService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;

    @RequestMapping(value = "/inventorycheck/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg delete(@RequestParam("id") List<String> ids) {
        ResultMsg resultMsg = null;
        Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        try {
            if (inventoryCheckService.delete(ids, userId.toString()))
                ;
            {
                resultMsg = new ResultMsg(Boolean.TRUE, "删除成功");
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    // @RequestMapping(value = "/inventorycheck/add", method =
    // RequestMethod.POST)//这个是以前的盘点版本，现在没有用了，所有的关于盘点的操作加了一个New
    // @ResponseBody
    // public ResultMsg add(@RequestBody AddInventoryCheckRequest request) {
    // ResultMsg resultMsg = null;
    // try {
    // inventoryCheckService.add(request);
    // resultMsg = new ResultMsg(Boolean.TRUE, "添加成功");
    // } catch (Exception e) {
    // e.printStackTrace();
    // resultMsg = new ResultMsg(Boolean.FALSE,
    // i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" +
    // e.getMessage() }));
    // }
    // return resultMsg;
    // }

    @RequestMapping(value = "/inventorycheck/edit", method = RequestMethod.POST)//this is a test
    @ResponseBody
    public ResultMsg edit(@RequestBody AddInventoryCheckRequestNew request) {
        ResultMsg resultMsg = null;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            inventoryCheckService.edit(request);
            resultMsg = new ResultMsg(Boolean.TRUE, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    // 根据物料主键以及仓库主键得到该物料所有批次信息，包含仓库的剩余数量，批次单价等信息
    @RequestMapping(value = "/inventorycheck/getmaterialbatchnumberlist", method = RequestMethod.GET)
    @ResponseBody
    public List<GetMaterialBatchNumberResponse> getMaterialBatchNumberList(@RequestParam(value = "materialId", required = true) String materialId,
                                                                           @RequestParam(value = "storageId", required = true) String storageId, Map<String, Object> model) {
        List<GetMaterialBatchNumberResponse> responses = null;
        try {
            responses = inventoryCheckService.getMaterialBatchNumberList(materialId, storageId);
            // 排除批次数量为0的项
            Iterator<GetMaterialBatchNumberResponse> iter = responses.iterator();
            while (iter.hasNext()) {
                GetMaterialBatchNumberResponse i = iter.next();
                if (i.getCounts() <= 0)
                    iter.remove();
            }
            model.put("List", responses);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return responses;
    }

    @RequestMapping(value = "/inventorycheck/getinventorycheckbystorageidwithdrft", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg getInventorycheckByStorageIdWithDrft(@RequestParam(value = "storageId", required = true) String storageId) {
        List<GetInventoryCheckResponse> responses = null;
        try {
            responses = inventoryCheckService.getInventorycheckByStorageIdWithDrft(storageId);
            if (!responses.isEmpty()) {
                return new ResultMsg(false, responses.get(0).getCheckNumber());
            } else {
                return new ResultMsg(true, "可以添加盘点单");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMsg(true, "验证盘点单是否存在时出现问题");
        }
    }

    @RequestMapping(value = "/inventorycheck/addnew", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg addNew(@RequestBody AddInventoryCheckRequestNew request) {
        ResultMsg resultMsg = null;
        try {
            inventoryCheckService.addNew(request);
            resultMsg = new ResultMsg(Boolean.TRUE, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/pdainventorycheck/addnew", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg addPdaInventoryCheck(@RequestBody AddInventoryCheckRequestNew request) {
        ResultMsg resultMsg = null;
        try {
            request.setOwner(jsonConverter.fromString(UserLoginResponse.class, sessionContext.get(SecuritySessionConstants.LOGGED_USER)).getId().toString());
            inventoryCheckService.addPdaInventoryCheck(request);
            resultMsg = new ResultMsg(Boolean.TRUE, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/inventorycheck/responsebody/get", method = RequestMethod.GET)
    @ResponseBody
    public GetInventoryCheckResponseNew get(@RequestParam(value = "checkNumber", required = true) String checkNumber, Map<String, Object> model) {
        GetInventoryCheckResponseNew response = null;
        try {
            response = inventoryCheckService.get(checkNumber);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return response;
    }

}
