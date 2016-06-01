package com.siping.intranet.system.web;

import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.SessionConstants;
import com.siping.intranet.system.service.OperationLogService;
import com.siping.smartone.common.OperationLog;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;

@Controller
@LoginRequired
public class OperationLogController extends StromaController {
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/operationLog/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getList(OperationLog log, @RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize,
                          Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.OPERATION_LOG_SEARCH_CONDITION)))
                log = jsonConverter.fromString(OperationLog.class, sessionContext.get(SessionConstants.OPERATION_LOG_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.OPERATION_LOG_SEARCH_CONDITION, jsonConverter.toString(log));
            PagingResponse<OperationLog> response = operationLogService.getList(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, log);
            if (CollectionUtils.isNotEmpty(response.getRecords())) {
                PageModel<OperationLog> pageModel = new PageModel<OperationLog>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, response.getTotalRecords(), response.getRecords());
                model.put("pageModel", pageModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_OPERATION_LOG;
    }

    @RequestMapping(value = "/operationLog/todeletepage", method = { RequestMethod.GET })
    public String toDeletePage(Map<String, Object> model) {
        return PagePath.DELETE_OPERATION_LOG;
    }

    @RequestMapping(value = "/operationLog/delete", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public ResultMsg delete(OperationLog log, Map<String, Object> model) {
        Boolean delete = Boolean.FALSE;
        try {
            delete = operationLogService.delete(log);
        } catch (Exception e) {
            return new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return new ResultMsg(delete, i18nUtil.getMessage(delete ? ErrorCode.DELETE_SUCCESS : ErrorCode.DELETE_ERROR_NO_PARAM));
    }
}
