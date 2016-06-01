package com.siping.service.postperiod.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;





import com.siping.smartone.postperiod.request.AddPostPeriodRequest;
import com.siping.smartone.postperiod.request.DeletePostPeriodRequest;
import com.siping.smartone.postperiod.request.GetPostPeriodListRequest;
import com.siping.smartone.postperiod.response.GetPostPeriodResponse;


@Repository
public class PostPeriodRestRepository {
    @Autowired
    private JDBCAccessContext jdbcAccessContext;

    public Boolean add(AddPostPeriodRequest request) {
        int add = -1;
        if(checkByAdd(request)){
            Object[] addParams = new Object[] { request.getPostPeriodNo(), request.getPostPeriodName(),request.getPostPeriodFlag(),request.getPostPeriodStartTime(),request.getPostPeriodEndTime(),request.getDescription()};
            add = jdbcAccessContext.execute("POSTPERIOD.SQL_ADD_POSTPERIOD", addParams);
        }
        if (-1 == add) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private Boolean checkByAdd(AddPostPeriodRequest request){
        try {
            if(!getNextAddPostPeriodTime(getMax().getPostPeriodEndTime()).equals(request.getPostPeriodStartTime())){
                throw new BusinessProcessException("日期不连续，无法添加");
            }
            else {
                return true;
            }
        } 
        catch (BusinessProcessException e) {
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new BusinessProcessException("日期格式错误");
        }
        //TODO 验证合法性
    }
    private String getNextAddPostPeriodTime(String inputTime) throws Exception{//给入一个时间，得到当前时间的下一秒‘
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String outTime = null;
        try{
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(inputTime));
            cal.add(Calendar.DAY_OF_MONTH, 1);
            outTime =  sdf.format(cal.getTime());
        }catch(ParseException erroFmtExp){
            throw new Exception("给入日期格式错误,日期格式必须是 yyyy-MM-dd");
        }
        if(null==outTime){
            throw new Exception("出现未知错误");
        }
        return outTime;
    }
    private Boolean checkByEdit(AddPostPeriodRequest request){
        return true;
        //TODO 验证合法性
    }
    public Boolean edit(AddPostPeriodRequest request) {
        int edit = -1;
        GetPostPeriodResponse postPeriodResponse = get(request.getId());
        if (null == postPeriodResponse){
            throw new BusinessProcessException("过账期间不存在，无法编辑！");
        }
        if(checkByEdit(request)){
            List<Object> editParam = new ArrayList<Object>();
            edit = jdbcAccessContext.executeWithoutSqlManager(buildEditSql(request, editParam), editParam.toArray());
        }
        if (-1 == edit) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public GetPostPeriodResponse get(String id) {
        List<GetPostPeriodResponse> response = jdbcAccessContext.find("POSTPERIOD.SQL_SELECT_POSTPERIOD_BYID", new RowMapper<GetPostPeriodResponse>() {
            @Override
            public GetPostPeriodResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPostPeriodResponse(rs);
            }
        }, new Object[] {id});
        if (!CollectionUtils.isEmpty(response)) {
            return response.get(0);
        }
        return null;
    }
    private String buildEditSql(AddPostPeriodRequest request, List<Object> list) {
        StringBuilder sql = new StringBuilder("UPDATE post_period SET ");
        assemblePramsForString(request, list, sql);
        assembleParamsForBoolean(request, list, sql);
        assembleParamsForInteger(request, list, sql);
        sql.append(" WHERE id=? ");
        list.add(request.getId());
        return sql.toString();
    }

    private void assemblePramsForString(AddPostPeriodRequest request, List<Object> list, StringBuilder sql) {
        if (null != request.getPostPeriodEndTime()) {
            if (list.size() > 0)
                sql.append(" ,post_period_end_time=? ");
            else
                sql.append(" post_period_end_time=? ");
            list.add(request.getPostPeriodEndTime());
         }
    }

    private void assembleParamsForBoolean(AddPostPeriodRequest request, List<Object> list, StringBuilder sql) {
        if (null != request.getPostPeriodFlag()) {
          if (list.size() > 0)
              sql.append(" ,post_period_flag=? ");
          else
              sql.append(" post_period_flag=? ");
          list.add(request.getPostPeriodFlag());
       }
   }

    private void assembleParamsForInteger(AddPostPeriodRequest request, List<Object> list, StringBuilder sql) {
       //TODO 该对象不包含Int数据
    }



    private static GetPostPeriodResponse buildPostPeriodResponse(final ResultSet rs) throws SQLException {
        GetPostPeriodResponse p = new GetPostPeriodResponse();
        p.setId(rs.getString("id"));
        p.setPostPeriodNo(rs.getString("post_period_no"));
        p.setPostPeriodName(rs.getString("post_period_name"));
        p.setPostPeriodFlag(rs.getBoolean("post_period_flag"));
        p.setPostPeriodStartTime(rs.getString("post_period_start_time"));
        p.setPostPeriosEndTime(rs.getString("post_period_end_time"));
        p.setDescription(rs.getString("description"));
        return p;
    }


    public PagingResponse<GetPostPeriodResponse> getList(GetPostPeriodListRequest request) { // 分页查询
        List<Object> queryParam = new ArrayList<Object>();
        PagingResponse<GetPostPeriodResponse> response = new PagingResponse<GetPostPeriodResponse>();
        StringBuilder querySql = new StringBuilder("SELECT p.id,p.post_period_no,p.post_period_name,p.post_period_flag,p.post_period_start_time,p.post_period_end_time,p.description from post_period p WHERE p.is_delete= false ORDER BY p.id");
        StringBuilder queryTotal = new StringBuilder("SELECT COUNT(0) from post_period p WHERE p.is_delete=false");
        if (StringUtils.hasText(request.getPageNo()) && StringUtils.hasText(request.getPageSize())) {
            querySql.append(" limit ?,? ");
            queryParam.add((Integer.valueOf(request.getPageNo()) - 1) * Integer.valueOf(request.getPageSize()));
            queryParam.add(Integer.valueOf(request.getPageSize()));
        }
        System.out.println(querySql.toString());
        List<GetPostPeriodResponse> list = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<GetPostPeriodResponse>() {
            @Override
            public GetPostPeriodResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPostPeriodResponse(rs);
            }
        }, queryParam.toArray());
        Integer total = jdbcAccessContext.findIntegerWithoutSqlManager(queryTotal.toString(), new Object[] {});
        response.setRecords(list);
        response.setTotalRecords(total);
        return response;
    }

    public Boolean delete(DeletePostPeriodRequest request) {
        if (null == request.getIds()){
            throw new BusinessProcessException("过账期间不存在，无法删除！");
        }
        String updateBy = request.getUpdateBy();
        List<String> ids = request.getIds();
        List<Object[]> deleteParams = new LinkedList<Object[]>();
        for (String id : ids) {
            Object[] obj = new Object[] {updateBy, id};
            deleteParams.add(obj);
        }
        int[] delete = jdbcAccessContext.batchExecute("POSTPERIOD.SQL_DELETE_POSTPERIOD", deleteParams);
        if (Arrays.asList(delete).contains(-1)) {
            throw new BusinessProcessException("批量删除某条信息时失败,已回滚");
        }
        return Boolean.TRUE;
    }

    public GetPostPeriodResponse getMax() {
        List<GetPostPeriodResponse> response = jdbcAccessContext.find("POSTPERIOD.SQL_SELECT_POSTPERIOD_BY_ID_MAX", new RowMapper<GetPostPeriodResponse>() {
            @Override
            public GetPostPeriodResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPostPeriodResponse(rs);
            }
        }, new Object[] {});
        if (!CollectionUtils.isEmpty(response)) {
            return response.get(0);
        }
        else{
            throw new BusinessProcessException("是第一条信息");
        }
    }
    
    public GetPostPeriodResponse getByDateTime(String dateTime){
        System.out.println("test");
        List<GetPostPeriodResponse> response = jdbcAccessContext.findWithoutSqlManager("select id,post_period_no,post_period_name,post_period_flag,post_period_start_time,post_period_end_time,description from post_period where is_delete=false and UNIX_TIMESTAMP(?)<(UNIX_TIMESTAMP(post_period_end_time)+86400) and UNIX_TIMESTAMP(?)>UNIX_TIMESTAMP(post_period_start_time)",new RowMapper<GetPostPeriodResponse>() {
            @Override
            public GetPostPeriodResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildPostPeriodResponse(rs);
            }
        }, new Object[] {dateTime,dateTime});
//        List<GetPostPeriodResponse> response = jdbcAccessContext.find("POSTPERIOD.SQL_SELECT_POSTPERIOD_BY_DATETIME", new RowMapper<GetPostPeriodResponse>() {
//            @Override
//            public GetPostPeriodResponse mapRow(ResultSet rs, int arg1) {
//                try {
//                    return buildPostPeriodResponse(rs);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                    throw new BusinessProcessException("获取过账期间时遇到了一些问题");
//                }
//            }
//        }, new Object[] {dateTime,dateTime});
        if (!CollectionUtils.isEmpty(response)) {
            if(response.size()!=1){
                throw new BusinessProcessException("过账期间日期重复，请联系管理员确保过账期间连续且不重复");
            }
            else{
                return response.get(0);
            }
            
        }
        else{
            return null;
        }
    }
}