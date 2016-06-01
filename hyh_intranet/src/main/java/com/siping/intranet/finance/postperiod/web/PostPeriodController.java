package com.siping.intranet.finance.postperiod.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.finance.postperiod.service.PostPeriodService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.postperiod.response.GetPostPeriodResponse;

@Controller
@LoginRequired
public class PostPeriodController extends StromaController {
    @Autowired
    private PostPeriodService postPeriodService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/postperiod/add", method = RequestMethod.GET)
    public String addPostPeriodView(Map<String, Object> model) {
        if (null != getLastPostPeriod()) {
            try {
                model.put("startTime", getNextAddPostPeriodTime(getLastPostPeriod()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            model.put("startTime", "0");
        }
        return PagePath.ADD_POST_PERIOD;
    }

    private String getNextAddPostPeriodTime(String inputTime) throws Exception {// 给入一个时间，得到当前时间的下一秒‘
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String outTime = null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(inputTime));
            cal.add(Calendar.DAY_OF_MONTH, 1);
            outTime = sdf.format(cal.getTime());
        } catch (ParseException erroFmtExp) {
            throw new Exception("给入日期格式错误,日期格式必须是 yyyy-MM-dd");
        }
        if (null == outTime) {
            throw new Exception("出现未知错误");
        }
        return outTime;
    }

    private String getLastPostPeriod() {
        try {
            if (null != postPeriodService.getMax()) {
                return postPeriodService.getMax().getPostPeriodEndTime();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ("出现未知错误");
        }
    }

    @RequestMapping(value = "/postperiod/edit", method = RequestMethod.GET)
    public String editPostPeriodView(@RequestParam("id") String id, Map<String, Object> model) {
        try {
            GetPostPeriodResponse postPeriodresponse = new GetPostPeriodResponse();
            postPeriodresponse = postPeriodService.get(id);
            model.put("p", postPeriodresponse);
            if (postPeriodService.getMax().getId().equals(id)) {
                model.put("endTime", "last");
            } else {
                model.put("endTime", "notLast");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.EDIT_POST_PERIOD;
    }

    @RequestMapping(value = "/postperiod/get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "id", required = false) String id, Map<String, Object> model) {
        GetPostPeriodResponse response = null;
        try {
            response = postPeriodService.get(id);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        model.put("postPeriod", response);
        return PagePath.ALL_POST_PERIOD;
    }

    @RequestMapping(value = "/postperiod/getlist", method = RequestMethod.GET)
    public String getList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, Map<String, Object> model) {
        try {
            PagingResponse<GetPostPeriodResponse> response = new PagingResponse<GetPostPeriodResponse>();
            response = postPeriodService.getList(pageNo, pageSize);
            if (pageNo == null) {
                pageNo = 1;
            }
            if (pageSize == null) {
                pageSize = 10;
            }
            PageModel<GetPostPeriodResponse> pageModel = new PageModel<GetPostPeriodResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("pageModel", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_POST_PERIOD;
    }
}
