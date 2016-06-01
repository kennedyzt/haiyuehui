package com.siping.intranet.crm.material.web;

import java.util.ArrayList;
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
import com.siping.intranet.crm.businesspartner.service.BusinessPartnerService;
import com.siping.intranet.crm.businesspartner.service.PartnerGroupService;
import com.siping.intranet.crm.material.service.MaterialService;
import com.siping.intranet.crm.material.service.MaterialUnitService;
import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.smartone.businesspartner.request.GetBusinessPartnerListForm;
import com.siping.smartone.businesspartner.response.BusinessPartnerResponse;
import com.siping.smartone.businesspartner.response.PartnerGroupResponse;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.inventory.response.GetInventoryWarningResponse;
import com.siping.smartone.login.response.UserLoginResponse;
import com.siping.smartone.material.request.GetMaterialListRequest;
import com.siping.smartone.material.request.GetOpeanWinMaterialRequest;
import com.siping.smartone.material.response.GetMaterialResponse;
import com.siping.smartone.material.response.GetMaterialUnitResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
@LoginRequired
public class MaterialController extends StromaController {
    @Autowired
    private MaterialService materialService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private MaterialUnitService materialUnitService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private BusinessPartnerService businessPartnerService;
    @Autowired
    private PartnerGroupService partnerGroupService;

    @RequestMapping(value = "/material/add", method = RequestMethod.GET)
    public String addMaterialView(Map<String, Object> model) {
        try {
            List<StorageResponse> storageResponses = storageService.getList();
            List<GetMaterialUnitResponse> materialUnitResponses = materialUnitService.getList();
            model.put("storages", storageResponses);
            model.put("materialUnits", materialUnitResponses);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ADD_MATERIAL;
    }

    @RequestMapping(value = "/material/edit", method = RequestMethod.GET)
    public String editMaterialView(@RequestParam("id") String id, Map<String, Object> model) {
        try {
            List<StorageResponse> storageResponses = storageService.getList();
            List<GetMaterialUnitResponse> materialUnitResponses = materialUnitService.getList();
            GetMaterialResponse materialResponse = materialService.get(id, null, null, null);
            assemInventoryWarningForEdit(storageResponses, materialResponse);
            model.put("materialUnits", materialUnitResponses);
            model.put("material", materialResponse);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }

        return PagePath.EDIT_MATERIAL;
    }

    private void assemInventoryWarningForEdit(List<StorageResponse> storages, GetMaterialResponse materialResponse) {
        List<GetInventoryWarningResponse> inventoryWarnings = new ArrayList<GetInventoryWarningResponse>();
        for (StorageResponse storageResponse : storages) {
            GetInventoryWarningResponse inventoryWarning = new GetInventoryWarningResponse();// 这里对象一定要new
                                                                                             // 再添加；
            inventoryWarning.setStorageId(storageResponse.getId().toString());
            inventoryWarning.setMaterialId(materialResponse.getId().toString());
            inventoryWarning.setStorageName(storageResponse.getStorageName());
            inventoryWarning.setStorageNo(storageResponse.getStorageNo());
            inventoryWarnings.add(inventoryWarning);
        }
        for (int i = 0; i < inventoryWarnings.size(); i++) {
            for (int j = 0; j < materialResponse.getInventoryWarnings().size(); j++) {
                if (inventoryWarnings.get(i).getStorageId().equals(materialResponse.getInventoryWarnings().get(j).getStorageId())) {
                    inventoryWarnings.get(i).setMaxInventory(materialResponse.getInventoryWarnings().get(j).getMaxInventory());
                    inventoryWarnings.get(i).setMinInventory(materialResponse.getInventoryWarnings().get(j).getMinInventory());
                }
            }
        }
        materialResponse.setInventoryWarnings(inventoryWarnings);
    }

    @RequestMapping(value = "/material/get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "materialNo", required = false) String materialNo,
                      @RequestParam(value = "barcode", required = false) String barcode, @RequestParam(value = "description", required = false) String description, Map<String, Object> model) {
        GetMaterialResponse response = null; // 查询单个物料可以单独用id查询，单独用物料编号查询，单独用国际编码查询均可，当然可以三个条件都提供来查询
        try {
            List<StorageResponse> storageResponses = storageService.getList();
            response = materialService.get(id, materialNo, barcode, description);
            assemInventoryWarningForEdit(storageResponses, response);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        model.put("material", response);
        return PagePath.DETAIL_MATERIAL;
    }

    @RequestMapping(value = "/material/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize,
                          GetMaterialListRequest getMaterialListRequest, Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.MATERIAL_SEARCH_CONDITION)))
                getMaterialListRequest = jsonConverter.fromString(GetMaterialListRequest.class, sessionContext.get(SessionConstants.MATERIAL_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.MATERIAL_SEARCH_CONDITION, jsonConverter.toString(getMaterialListRequest));
            if (getMaterialListRequest.getIsEnable() == null) {
                getMaterialListRequest.setIsEnable(true);
            }
            PagingResponse<GetMaterialResponse> response = new PagingResponse<GetMaterialResponse>();
            UserLoginResponse user = jsonConverter.fromString(UserLoginResponse.class, sessionContext.get(SecuritySessionConstants.LOGGED_USER));
            if ("BusiPartner".equals(user.getUserType().toString())) {
                getMaterialListRequest.setLoggedSupId(user.getObjectId());
            }
            response = materialService.getList(pageNo, pageSize, getMaterialListRequest);
            if (pageNo == null) {
                pageNo = 1;
            }
            if (pageSize == null) {
                pageSize = 10;
            }
            PageModel<GetMaterialResponse> pageModel = new PageModel<GetMaterialResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("pageModel", pageModel);
            model.put("searchCondition", getMaterialListRequest);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_MATERIAL;
    }

    @RequestMapping(value = "/material/supplier/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getListForSupplier(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize,
                          GetMaterialListRequest getMaterialListRequest, Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.MATERIAL_SEARCH_CONDITION)))
                getMaterialListRequest = jsonConverter.fromString(GetMaterialListRequest.class, sessionContext.get(SessionConstants.MATERIAL_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.MATERIAL_SEARCH_CONDITION, jsonConverter.toString(getMaterialListRequest));
            if (getMaterialListRequest.getIsEnable() == null) {
                getMaterialListRequest.setIsEnable(true);
            }
            PagingResponse<GetMaterialResponse> response = new PagingResponse<GetMaterialResponse>();
            UserLoginResponse user = jsonConverter.fromString(UserLoginResponse.class, sessionContext.get(SecuritySessionConstants.LOGGED_USER));
            if ("BusiPartner".equals(user.getUserType().toString())) {
                getMaterialListRequest.setLoggedSupId(user.getObjectId());
            }
            response = materialService.getListForSupplier(pageNo, pageSize, getMaterialListRequest);
            if (pageNo == null) {
                pageNo = 1;
            }
            if (pageSize == null) {
                pageSize = 10;
            }
            PageModel<GetMaterialResponse> pageModel = new PageModel<GetMaterialResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("pageModel", pageModel);
            model.put("searchCondition", getMaterialListRequest);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_SUPPLIER_MATERIAL;
    }

    // 弹出框选择物料
    @RequestMapping(value = "/material/openwin", method = { RequestMethod.POST, RequestMethod.GET })
    public String getList(GetOpeanWinMaterialRequest request, @RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize,
                          @RequestParam(value = "functionName", required = true) String functionName, @RequestParam(value = "rowId", required = false) Integer rowId, Map<String, Object> model) {
        if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.MATERIAL_SEARCH_CONDITION_LIST)))
            request = jsonConverter.fromString(GetOpeanWinMaterialRequest.class, sessionContext.get(SessionConstants.MATERIAL_SEARCH_OPENWIN_LIST));
        if (null == pageNo)
            sessionContext.set(SessionConstants.MATERIAL_SEARCH_OPENWIN_LIST, jsonConverter.toString(request));
        try {
            PagingResponse<GetMaterialResponse> response = materialService.getOpenWinList(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, request);
            PageModel<GetMaterialResponse> pageModel = new PageModel<GetMaterialResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, response.getTotalRecords(),
                response.getRecords());
            model.put("pageModel", pageModel);
            model.put("rowId", rowId);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        // 返回前段需要数据
        returnNecessaryData(request, functionName, model, rowId);
        if (null == rowId) { // TODO 若rowId为空表明旧版商品选择弹出框
                             // ,以后删掉此判断,并修改rowId参数required = true
            return PagePath.OPEN_WIN_MATERIAL;
        }
        return PagePath.OPEN_MATERIAL_WIN;
    }

    private void returnNecessaryData(GetOpeanWinMaterialRequest request, String functionName, Map<String, Object> model, Integer rowId) {
        model.put("functionName", functionName); // 决定前段调用什么方法
        model.put("isPurchase", request.getIsPurchase());
        model.put("isSell", request.getIsSell());
        model.put("isInventory", request.getIsInventory());
        model.put("rowId", rowId);
    }

    @RequestMapping(value = "/material/getmaterialbatchnumberlist", method = RequestMethod.GET)
    public String getmaterialbatchnumberlist(@RequestParam(value = "rowId", required = true) Integer rowId, Map<String, Object> model) {
        model.put("rowId", rowId);
        return PagePath.BATCH_NUMBER;
    }

    @RequestMapping(value = "/businesspartner/openwinwithidconfict", method = { RequestMethod.POST, RequestMethod.GET })
    public String openWinGetPartner(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                    @RequestParam(value = "partnerType", required = true) Integer partnerType, GetBusinessPartnerListForm request, Map<String, Object> model) {
        List<PartnerGroupResponse> partnerGroups = null;
        if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION)))
            request = jsonConverter.fromString(GetBusinessPartnerListForm.class, sessionContext.get(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION));
        if (null == pageNo)
            sessionContext.set(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION, jsonConverter.toString(request));
        PagingResponse<BusinessPartnerResponse> pagingResponse = new PagingResponse<BusinessPartnerResponse>();
        pagingResponse = businessPartnerService.getAllBusinessPartner(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, partnerType, request);
        PageModel<BusinessPartnerResponse> pageModel = new PageModel<BusinessPartnerResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, pagingResponse.getTotalRecords(),
            pagingResponse.getRecords());
        model.put("pageModel", pageModel);
        partnerGroups = partnerGroupService.getList();
        model.put("partnerGroups", partnerGroups);
        return PagePath.OPEN_WIN_PARTNER_WITH_ID_CONFLICT;
    }

    @RequestMapping(value = "/readyshipments/pack", method = { RequestMethod.POST, RequestMethod.GET })
    public String openPack() {
        return PagePath.PACK;
    }

    @RequestMapping(value = "/test", method = { RequestMethod.POST, RequestMethod.GET })
    public String openWinGetPartner() {
        return PagePath.TEST;
    }
}
