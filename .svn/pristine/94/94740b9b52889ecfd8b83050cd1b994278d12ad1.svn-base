package com.siping.intranet.invoicing.sell.returns.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
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
import com.siping.intranet.common.CommonBillsForm;
import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.intranet.invoicing.sell.returns.service.SellReturnsService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.invoicing.sell.returns.response.GetSellReturnsListResponse;
import com.siping.smartone.invoicing.sell.returns.response.GetSellReturnsResponse;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
@LoginRequired
public class SellReturnsController extends StromaController {
    @Autowired
    private SellReturnsService sellReturnsService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/sellreturns/get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "returnsnumber", required = false) String returnsnumber, Map<String, Object> model) {
        try {
            GetSellReturnsResponse response = sellReturnsService.get(returnsnumber);
            model.put("sellReturns", response);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.DETAIL_RETURN_SO;
    }

    @RequestMapping(value = "/sellreturns/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, CommonBillsForm form,
                          Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.SELL_RETURNS_SEARCH_CONDITION)))
                form = jsonConverter.fromString(CommonBillsForm.class, sessionContext.get(SessionConstants.SELL_RETURNS_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.SELL_RETURNS_SEARCH_CONDITION, jsonConverter.toString(form));
            form.setIsDraft(false);
            PagingResponse<GetSellReturnsListResponse> response = sellReturnsService.getList(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, form);
            if (CollectionUtils.isNotEmpty(response.getRecords())) {
                PageModel<GetSellReturnsListResponse> pageModel = new PageModel<GetSellReturnsListResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, response.getTotalRecords(),
                    response.getRecords());
                model.put("pageModel", pageModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_RETURN_SO;
    }

    @RequestMapping(value = "/sellreturns/add", method = RequestMethod.GET)
    public String add(Map<String, Object> model) {
        List<StorageResponse> storages = storageService.getList();
        model.put("storages", storages);
        return PagePath.ADD_RETURN_SO;
    }

    @RequestMapping(value = "/sellreturns/edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value = "returnsnumber", required = true) String returnsnumber, Map<String, Object> model) {
        try {
            GetSellReturnsResponse response = sellReturnsService.get(returnsnumber);
            if (!response.getIsDraft()) {
                throw new BusinessProcessException("已经保存,无法编辑");
            }
            List<StorageResponse> storages = storageService.getList();
            model.put("storages", storages);
            model.put("return", response);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.EDIT_RETURN_SO;
    }

    @RequestMapping(value = "/sellreturns/getdraftlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getDraftList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, CommonBillsForm form,
                               Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.SELL_RETURNS_SEARCH_CONDITION)))
                form = jsonConverter.fromString(CommonBillsForm.class, sessionContext.get(SessionConstants.SELL_RETURNS_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.SELL_RETURNS_SEARCH_CONDITION, jsonConverter.toString(form));
            form.setIsDraft(true);
            PagingResponse<GetSellReturnsListResponse> response = sellReturnsService.getList(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, form);
            PageModel<GetSellReturnsListResponse> pageModel = new PageModel<GetSellReturnsListResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, response.getTotalRecords(),
                response.getRecords());
            model.put("pageModel", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_DRAFT_RETURN_SO;
    }

    @RequestMapping(value = "/sellreturns/print", method = RequestMethod.GET)
    public String getPrint(@RequestParam(value = "returnsnumber", required = false) String returnsnumber, Map<String, Object> model) {
        try {
            GetSellReturnsResponse response = sellReturnsService.get(returnsnumber);
            model.put("sellReturns", response);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.PRINT_RETURN_SO;
    }
}
