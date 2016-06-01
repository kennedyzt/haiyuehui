package com.siping.intranet.crm.inventory.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.SessionConstants;
import com.siping.intranet.common.service.CommonBillsNumberService;
import com.siping.intranet.crm.inventory.service.InventoryCheckService;
import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.inventory.request.GetInventoryCheckListRequest;
import com.siping.smartone.inventory.response.GetInventoryCheckItemResponse;
import com.siping.smartone.inventory.response.GetInventoryCheckResponse;
import com.siping.smartone.inventory.response.GetInventoryCheckResponseNew;
import com.siping.smartone.login.response.UserLoginResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
@LoginRequired
public class InventoryCheckController extends StromaController {
    @Autowired
    private InventoryCheckService inventorycheckService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private CommonBillsNumberService commonBillsNumberService;

    @RequestMapping(value = "/inventorycheck/add", method = RequestMethod.GET)
    public String addInventoryCheckView(Map<String, Object> model) {
        model.put("checkNumber", commonBillsNumberService.getGenerateBillsNumber("inventory_check", "check_number"));
        model.put("loginUser", jsonConverter.fromString(UserLoginResponse.class, sessionContext.get(SecuritySessionConstants.LOGGED_USER)));
        return PagePath.ADD_INVENTORY_CHECK;
    }

    @RequestMapping(value = "/pdainventorycheck/add", method = RequestMethod.GET)
    public String addPDAInventoryCheckView(Map<String, Object> model) {
        model.put("checkNumber", commonBillsNumberService.getGenerateBillsNumber("inventory_check", "check_number"));
        List<StorageResponse> tempResponses = storageService.getList();
        //List<StorageResponse> finalResponses = new ArrayList<StorageResponse>();
        for(StorageResponse response:tempResponses){
            if(response.getId()==0){
                tempResponses.remove(response);
                break;
            }
        }
        model.put("storages",tempResponses);
        model.put("loginUser", jsonConverter.fromString(UserLoginResponse.class, sessionContext.get(SecuritySessionConstants.LOGGED_USER)));
        return PagePath.ADD_PDA_INVENTORY_CHECK;
    }
    
    @RequestMapping(value = "/inventorycheck/edit", method = RequestMethod.GET)
    public String editStorageView(@RequestParam("checkNumber") String checkNumber, Map<String, Object> model) {
        try {
            GetInventoryCheckResponseNew response = inventorycheckService.get(checkNumber);
            model.put("i", response);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.EDIT_INVENTORY_CHECK;
    }

    @RequestMapping(value = "/inventorycheck/get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "checkNumber", required = true) String checkNumber, Map<String, Object> model) {
        GetInventoryCheckResponseNew response = null;
        try {
            response = inventorycheckService.get(checkNumber);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        model.put("i", response);
        return PagePath.DETAIL_INVENTORY_CHECK;
    }
    
    @RequestMapping(value = "/pdainventorycheck/get", method = RequestMethod.GET)
    public String pdaGet(@RequestParam(value = "checkNumber", required = true) String checkNumber, Map<String, Object> model) {
        GetInventoryCheckResponseNew response = null;
        try {
            response = inventorycheckService.get(checkNumber);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        model.put("i", response);
        return PagePath.DETAIL_INVENTORY_CHECK;
    }

    @RequestMapping(value = "/inventorycheck/getlist", method = { RequestMethod.GET, RequestMethod.POST })
    public String getList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, GetInventoryCheckListRequest request,
                          Map<String, Object> model) {
        request.setIsDraft(false);
        request.setPcPdaFlag("0");
        if(!StringUtils.hasText(request.getStatus())){//没有条件默认显示未盘点的,2是表示所有的
            request.setStatus("0");
        }
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.INVENTORY_CHECK_SEARCH_CONDITION)))
                request = jsonConverter.fromString(GetInventoryCheckListRequest.class, sessionContext.get(SessionConstants.INVENTORY_CHECK_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.INVENTORY_CHECK_SEARCH_CONDITION, jsonConverter.toString(request));
            PagingResponse<GetInventoryCheckResponseNew> response = inventorycheckService.getList(pageNo, pageSize, request);
            if (pageNo == null) {
                pageNo = 1; 
            }
            if (pageSize == null) {
                pageSize = 10;
            }
            PageModel<GetInventoryCheckResponseNew> pageModel = new PageModel<GetInventoryCheckResponseNew>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("inventorycheckList", pageModel);
            model.put("status", request.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_INVENTORY_CHECK;
    }

    @RequestMapping(value = "/pdainventorycheck/getlist", method = { RequestMethod.GET, RequestMethod.POST })
    public String getPdaList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, GetInventoryCheckListRequest request,
                          Map<String, Object> model) {
        request.setIsDraft(false);
        request.setPcPdaFlag("1");//Pc盘点和Pda盘点的状态
        if(!StringUtils.hasText(request.getStatus())){//没有条件默认显示未盘点的,2是表示所有的
            request.setStatus("0");
        }
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.INVENTORY_CHECK_SEARCH_CONDITION)))
                request = jsonConverter.fromString(GetInventoryCheckListRequest.class, sessionContext.get(SessionConstants.INVENTORY_CHECK_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.INVENTORY_CHECK_SEARCH_CONDITION, jsonConverter.toString(request));
            PagingResponse<GetInventoryCheckResponseNew> response = inventorycheckService.getList(pageNo, pageSize, request);
            if (pageNo == null) {
                pageNo = 1; 
            }
            if (pageSize == null) {
                pageSize = 10;
            }
            PageModel<GetInventoryCheckResponseNew> pageModel = new PageModel<GetInventoryCheckResponseNew>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("inventorycheckList", pageModel);
            model.put("status", request.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_PDA_INVENTORY_CHECK;
    }
    @RequestMapping(value = "/inventorycheck/print", method = RequestMethod.GET)
    public String getPrint(@RequestParam(value = "checkNumber", required = false) String checkNumber, Map<String, Object> model) {
        GetInventoryCheckResponse response = null; // 查询单个物料可以单独用id查询，单独用物料编号查询，单独用国际编码查询均可，当然可以三个条件都提供来查询
        try {
           // response = null//inventorycheckService.get(checkNumber);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        model.put("inventorycheck", response);
        return PagePath.PRINT_INVENTORY_CHECK;
    }

//    @RequestMapping(value = "/inventorycheck/getdraftlist", method = { RequestMethod.GET, RequestMethod.POST })//存为草稿不用了
//    public String getDraftList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize,
//                               GetInventoryCheckListRequest request, Map<String, Object> model) {
//        request.setIsDraft(true);
//        try {
//            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.INVENTORY_CHECK_SEARCH_CONDITION)))
//                request = jsonConverter.fromString(GetInventoryCheckListRequest.class, sessionContext.get(SessionConstants.INVENTORY_CHECK_SEARCH_CONDITION));
//            if (null == pageNo)
//                sessionContext.set(SessionConstants.INVENTORY_CHECK_SEARCH_CONDITION, jsonConverter.toString(request));
//            PagingResponse<GetInventoryCheckResponse> response = inventorycheckService.getList(pageNo, pageSize, request);
//            if (pageNo == null) {
//                pageNo = 1;
//            }
//            if (pageSize == null) {
//                pageSize = 10;
//            }
//            PageModel<GetInventoryCheckResponse> pageModel = new PageModel<GetInventoryCheckResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
//            model.put("inventorycheckList", pageModel);
//        } catch (Exception e) {
//            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
//        }
//        return PagePath.ALL_DRAFT_INVENTORY_CHECK;
//    }
}
