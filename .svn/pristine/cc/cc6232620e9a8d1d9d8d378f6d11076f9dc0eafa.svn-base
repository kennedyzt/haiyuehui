package com.siping.intranet.finance.postperiod.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.login.request.UpdateUserRequest;
import com.siping.smartone.postperiod.request.AddPostPeriodRequest;
import com.siping.smartone.postperiod.request.DeletePostPeriodRequest;
import com.siping.smartone.postperiod.request.GetPostPeriodListRequest;
import com.siping.smartone.postperiod.response.GetPostPeriodResponse;

@Repository
public class PostPeriodRepository {
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean add(AddPostPeriodRequest request) {
        validAddPostPeriodRequest(request);
        String requestContent = JSONBinder.binder(AddPostPeriodRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/postperiod/add").body(requestContent));
    }

    @Valid
    private void validAddPostPeriodRequest(AddPostPeriodRequest request) {
        if(!StringUtils.hasText(request.getPostPeriodNo())){
            throw new BusinessProcessException("过账期间编号不能为空");
        }
        if(!StringUtils.hasText(request.getPostPeriodName())){
            throw new BusinessProcessException("过账期间名称不能为空");
        }
        if(null==request.getPostPeriodFlag()){
            throw new BusinessProcessException("过账期间状态不能为空");
        }
        if(!StringUtils.hasText(request.getPostPeriodStartTime())||!StringUtils.hasText(request.getPostPeriodStartTime())){
            throw new BusinessProcessException("过账期间开始或者结束时间不能为空");
        }
        else{
            try {
                checkTime(request.getPostPeriodStartTime(), request.getPostPeriodEndTime());
            } catch (ParseException e) {
                e.printStackTrace();
                throw new BusinessProcessException("日期格式输入错误，请确保输入日期格式正确");
            }
        }
    }
    private void checkTime(String startTime,String endTime) throws ParseException{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if (((Date)df.parse(startTime)).getTime() > ((Date)df.parse(endTime)).getTime()) {
                throw new BusinessProcessException("结束日期不能在开始日期之前");
            }
//            if (((Date)df.parse(startTime)).getTime() == ((Date)df.parse(endTime)).getTime()) {
//                throw new BusinessProcessException("结束日期不能与开始日期相等");
//            }
    }

    public Boolean edit(AddPostPeriodRequest request) {
        if (null == request.getId()) {
            throw new BusinessProcessException("未选择需要编辑过账期间！");
        }
        validAddPostPeriodRequest(request); // 也需要校验必填 项
        String requestContent = JSONBinder.binder(AddPostPeriodRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/postperiod/edit").body(requestContent));
    }

    public GetPostPeriodResponse get(String id) {
        if (!StringUtils.hasText(id)) {
            throw new BusinessProcessException("缺失主键无法获得过账期间信息！");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(GetPostPeriodResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/postperiod/get/%s").arguments(id));
    }

    @SuppressWarnings("unchecked")
    public PagingResponse<GetPostPeriodResponse> getList(Integer pageNo, Integer pageSize) {
        GetPostPeriodListRequest request = new GetPostPeriodListRequest();
        request.setPageNo(pageNo == null ? "" : pageNo.toString());
        request.setPageSize(pageSize == null ? "" : pageSize.toString());
        String requestContent = JSONBinder.binder(GetPostPeriodListRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(GetPostPeriodResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/postperiod/getlist").body(requestContent));
    }

    public Boolean delete(List<String> ids, String updateBy) {
        if (null == ids) {
            throw new BusinessProcessException("缺失主键不能删除过账期间");
        }
        if(null == updateBy){
            throw new BusinessProcessException("当前登录出现问题，无法修改");
        }
        DeletePostPeriodRequest deletePostPeriodRequest = new DeletePostPeriodRequest();
        deletePostPeriodRequest.setIds(ids);
        deletePostPeriodRequest.setUpdateBy(updateBy);
        String requestContent = JSONBinder.binder(DeletePostPeriodRequest.class).toJSON(deletePostPeriodRequest);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/postperiod/delete").body(requestContent));
    }

    public GetPostPeriodResponse getMax() {
        try {
            return stromaWebServiceApi.get(EndPointBuilder.create(GetPostPeriodResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/postperiod/getMax"));
        } catch (Exception e) {
            return null;
        }
    }
}
