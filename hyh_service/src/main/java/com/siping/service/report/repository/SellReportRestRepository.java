package com.siping.service.report.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.smartone.report.request.CustomerSaleRequest;
import com.siping.smartone.report.request.GetSalesRrequest;
import com.siping.smartone.report.response.CustomerSaleResponse;
import com.siping.smartone.report.response.CustomerSaleSumResponse;
import com.siping.smartone.report.response.GetSalesReportWithExtraResponse;
import com.siping.smartone.report.response.GetSalesResponse;
import com.siping.smartone.report.response.Series;

@Repository
public class SellReportRestRepository extends BillsRepository {

    public GetSalesResponse getSellOrderCount(GetSalesRrequest request) {
        GetSalesResponse response = new GetSalesResponse();
        StringBuilder querySql = new StringBuilder();
        List<Object> queryParms = new ArrayList<Object>();
        if (null == request || !StringUtils.hasText(request.getQueryType()) || "1".equals(request.getQueryType())) {
            loadSellOrderCountSqlByYear(querySql, queryParms, request);

        } else if (null != request && StringUtils.hasText(request.getQueryType()) && "2".equals(request.getQueryType())) {
            loadSellOrderCountSqlByMonth(querySql, queryParms, request);
        } else if (null != request && StringUtils.hasText(request.getQueryType()) && "3".equals(request.getQueryType())) {
            loadSellOrderCountSqlByDay(querySql, queryParms, request);
        }
        List<DataBox> dataBoxs = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<DataBox>() {
            @Override
            public DataBox mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildDataBox(rs);
            }
        }, queryParms.toArray());
        if(request.getQueryType().equals("1")){
            assembleDataForYearByOrderCount(response, dataBoxs, request);
        }else if(request.getQueryType().equals("2")){
            assembleDataForMonthByOrderCount(response, dataBoxs, request);
        }else if(request.getQueryType().equals("3")){
            assembleDataForDayByOrderCount(response,dataBoxs, request);
        }
        response.setType(request.getQueryType());
        return response;
    }

    private void assembleDataForDayByOrderCount(GetSalesResponse response, List<DataBox> dataBoxs, GetSalesRrequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); 
        Calendar calendar = new GregorianCalendar(); 
        Date calcuDate = null;
        try {
            calcuDate = sdf.parse(request.getKeys());
        } catch (ParseException e) {
            throw new BusinessProcessException("查询日期格式错误!");
        } 
        calendar.setTime(calcuDate); //放入你的日期 
        int monthDays =  calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int monthDaysSubOne = monthDays-1;
        String[] categories = new String[monthDays];
        double[] series = new double[monthDays];
        for(int i=monthDaysSubOne;i >=0;i--){
            categories[monthDaysSubOne-i] = String.valueOf(monthDays-i);//这里没必要用减法了，这里不是年份用固定量来减的
            if(Integer.valueOf(categories[monthDaysSubOne-i])<10){
            	categories[monthDaysSubOne-i] = 0 + categories[monthDaysSubOne-i];
            }
        }
      if (CollectionUtils.isNotEmpty(dataBoxs)) {//装配查询数据
          for (Integer i = monthDaysSubOne; i >= 0; i--) {
              boolean flag = true;
              for(DataBox db:dataBoxs){
                  if(categories[monthDaysSubOne-i].equals(db.getCategorie())){
                      series[monthDaysSubOne-i] = db.getSerie();
                      flag = false;
                      break;
                  }
              }
              if(flag){
                  series[monthDaysSubOne-i]=0.0;
              }
          }
      }
      response.setCategories(categories);
      response.setSeries(series);
    }

    private void assembleDataForMonthByOrderCount(GetSalesResponse response, List<DataBox> dataBoxs, GetSalesRrequest request) {
        String[] categories = new String[12];
        for(int count = 0;count<12;count++){
            categories[count] = String.valueOf(count+1) + "月";
        }
        double[] series = new double[12];
        String supportCategories = null;
        if (CollectionUtils.isNotEmpty(dataBoxs)) {//装配查询数据
            for (Integer i=0 ; i < 12; i++) {
                boolean flag = true;
                if(i<10){//以下四句代码是防止出现0x和x不匹配的情况，没有将数据换为整型类数据，还是用的字符串的方式进行比较的
                    supportCategories = "0" + i;
                }else{
                    supportCategories = String.valueOf(i);
                }
                for(DataBox db:dataBoxs){
                    if(supportCategories.equals(db.getCategorie())){
                        series[i] = db.getSerie();
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    series[i]=0.0;
                }
            }
        }
        response.setCategories(categories);
        response.setSeries(series);
    }

    private void assembleDataForYearByOrderCount(GetSalesResponse response, List<DataBox> dataBoxs, GetSalesRrequest request) {
        String[] categories = new String[11];
        double[] series = new double[11];
        Integer year = null;//设置年份
        if(StringUtils.hasText(request.getKeys())){
            year = Integer.valueOf(request.getKeys());
        }else{
            Calendar cal = Calendar.getInstance();
            year = cal.get(Calendar.YEAR);//获取年份
        }
        for(int i=10;i >=0;i--){
            categories[10-i] = String.valueOf(year-i);//设置10年前的年份
        }
      if (CollectionUtils.isNotEmpty(dataBoxs)) {//装配查询数据
          for (Integer i = 10; i >= 0; i--) {
              boolean flag = true;
              for(DataBox db:dataBoxs){
                  if(categories[10-i].equals(db.getCategorie())){
                      series[10-i] = db.getSerie();
                      flag = false;
                      break;
                  }
              }
              if(flag){
                  series[10-i]=0.0;
              }
          }
      }
      
      response.setCategories(categories);
      response.setSeries(series);
    }

    private void assembleData(GetSalesResponse response, List<DataBox> dataBoxs) { //之前的组装数据如果为空就不包含，图形化可能需要包含
        if (CollectionUtils.isNotEmpty(dataBoxs)) {
            String[] categories = new String[dataBoxs.size()];
            double[] series = new double[dataBoxs.size()];
            for (int i = 0; i < dataBoxs.size(); i++) {
                DataBox db = dataBoxs.get(i);
                categories[i] = db.getCategorie();
                series[i] = db.getSerie();
            }
            response.setCategories(categories);
            response.setSeries(series);
        }
    }

    // 按年统计销售单量
    private void loadSellOrderCountSqlByYear(StringBuilder querySql, List<Object> queryParms, GetSalesRrequest request) {
        querySql.append("SELECT date_format(s.bills_date,'%Y') categorie,count(0) serie from sell_shipments s WHERE s.bills_date IS NOT NULL "
            + "AND date_format(s.bills_date,'%Y') =? GROUP BY date_format(s.bills_date,'%Y')");
        queryParms.add(StringUtils.hasText(request.getKeys()) ? request.getKeys() : DateTime.now().getYear());
    }

    // 按月统计销售单量
    private void loadSellOrderCountSqlByMonth(StringBuilder querySql, List<Object> queryParms, GetSalesRrequest request) {
        querySql.append("SELECT date_format(s.bills_date, '%m') categorie, count(0) serie FROM sell_shipments s WHERE s.bills_date IS NOT NULL "
            + "AND date_format(s.bills_date, '%Y') =? GROUP BY date_format(s.bills_date, '%m')");
        queryParms.add(StringUtils.hasText(request.getKeys()) ? request.getKeys() : DateTime.now().getYear());
    }

    // 按日统计销售单量
    private void loadSellOrderCountSqlByDay(StringBuilder querySql, List<Object> queryParms, GetSalesRrequest request) {
        querySql.append("SELECT DISTINCT date_format(s.bills_date, '%d') categorie,count(0) serie FROM sell_shipments s WHERE s.bills_date IS NOT NULL "
            + " AND date_format(s.bills_date, '%Y-%m') =? GROUP BY date_format(s.bills_date, '%d')");
        queryParms.add(StringUtils.hasText(request.getKeys()) ? request.getKeys() : DateTime.now().getMonthOfYear());
    }

    private DataBox buildDataBox(ResultSet rs) throws SQLException {
        DataBox db = new DataBox();
        db.setCategorie(rs.getString("categorie"));
        db.setSerie(rs.getDouble("serie"));
        return db;
    }

    public GetSalesResponse getSaleroom(GetSalesRrequest request) {
        int reportType = 0;
        GetSalesResponse response = new GetSalesResponse();
        StringBuilder querySql = new StringBuilder();
        List<Object> queryParms = new ArrayList<Object>();
        if (null == request || !StringUtils.hasText(request.getQueryType()) || "1".equals(request.getQueryType())) {
            loadSaleroomSqlByYear(querySql, queryParms, request); // 按年统计销售额
            reportType =1;
//        } else if (null != request && StringUtils.hasText(request.getQueryType()) && "2".equals(request.getQueryType())) {
//            loadSaleroomSqlByQuarter(querySql, queryParms, request); // 按季统计销售额
//            reportType =2;
//        } else if (null != request && StringUtils.hasText(request.getQueryType()) && "3".equals(request.getQueryType())) {
//            loadSaleroomSqlByMonth(querySql, queryParms, request); // 按月统计销售额
//            reportType =3;
        } else if (null != request && StringUtils.hasText(request.getQueryType()) && "4".equals(request.getQueryType())) {
            loadSaleroomSqlByDay(querySql, queryParms, request); // 按日统计销售额
            reportType =4;
        }
        List<DataBox> dataBoxs = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<DataBox>() {
            @Override
            public DataBox mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildDataBox(rs);
            }
        }, queryParms.toArray());
        if(reportType==1){
            assembleDataForYear(response, dataBoxs, request);
        }else if(reportType==4){
            assembleDataForDay(response, dataBoxs,request);
        }else{
            assembleData(response, dataBoxs);
        }
        response.setType(request.getQueryType());
        return response;
    }

    private void assembleDataForYear(GetSalesResponse response, List<DataBox> dataBoxs, GetSalesRrequest request) {
        String[] categories = new String[11];
        double[] series = new double[11];
        Integer year = null;//设置年份
        if(StringUtils.hasText(request.getKeys())){
            year = Integer.valueOf(request.getKeys());
        }else{
            Calendar cal = Calendar.getInstance();
            year = cal.get(Calendar.YEAR);//获取年份
        }
        for(int i=10;i >=0;i--){
            categories[10-i] = String.valueOf(year-i);//设置10年前的年份
        }
      if (CollectionUtils.isNotEmpty(dataBoxs)) {//装配查询数据
          for (Integer i = 10; i >= 0; i--) {
              boolean flag = true;
              for(DataBox db:dataBoxs){
                  if(categories[10-i].equals(db.getCategorie())){
                      series[10-i] = db.getSerie();
                      flag = false;
                      break;
                  }
              }
              if(flag){
                  series[10-i]=0.0;
              }
          }
      }
      
      response.setCategories(categories);
      response.setSeries(series);
    }
    
    private void assembleDataForDay(GetSalesResponse response, List<DataBox> dataBoxs, GetSalesRrequest request){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM"); 
        Calendar calendar = new GregorianCalendar(); 
        Date calcuDate = null;
        try {
            calcuDate = sdf.parse(request.getKeys());
        } catch (ParseException e) {
            throw new BusinessProcessException("查询日期格式错误!");
        } 
        calendar.setTime(calcuDate); //放入你的日期 
        int monthDays =  calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int monthDaysSubOne = monthDays-1;
        String[] categories = new String[monthDays];
        double[] series = new double[monthDays];
        for(int i=monthDaysSubOne;i >=0;i--){
            categories[monthDaysSubOne-i] = String.valueOf(monthDays-i);//这里没必要用减法了，这里不是年份用固定量来减的
            if(Integer.valueOf(categories[monthDaysSubOne-i])<10){
            	categories[monthDaysSubOne-i] = 0 + categories[monthDaysSubOne-i];//用于修改0x和x不统一的情况
            }
        }
      if (CollectionUtils.isNotEmpty(dataBoxs)) {//装配查询数据
          for (Integer i = monthDaysSubOne; i >= 0; i--) {
              boolean flag = true;
              for(DataBox db:dataBoxs){
                  if(categories[monthDaysSubOne-i].equals(db.getCategorie())){
                      series[monthDaysSubOne-i] = db.getSerie();
                      flag = false;
                      break;
                  }
              }
              if(flag){
                  series[monthDaysSubOne-i]=0.0;
              }
          }
      }
      response.setCategories(categories);
      response.setSeries(series);
    }

    private Double getTB(Double thisSaleData,String thisYear,Integer thisDataCategories,String queryType){//获取同比数据
        if(queryType.equals("2")){//季度同比
            String lastSaleData = null;
            try {
                lastSaleData = jdbcAccessContext.findString("SELL.SQL_GET_TBHB_DATA_WITH_YEAR_QUARTER_CATEGORIES", new Object[] { Integer.valueOf(thisYear)-1, thisDataCategories });
            } catch (Exception e) {//捕获查询结果为空的异常,捕获后的处理是赋值为0.0
                e.printStackTrace();
            }
            if(StringUtils.hasText(lastSaleData)&&(Double.valueOf(lastSaleData)!=0)){
                Double dataTB = (thisSaleData-Double.valueOf(lastSaleData))/Double.valueOf(lastSaleData);
                return dataTB;
            }
            else{//没有同比的数据的处理方法,需要添加就是在这里添加
                return 0.0;
            }
        }
        else if(queryType.equals("3")){//月度同比
            String lastSaleData = null;
            try {
                lastSaleData = jdbcAccessContext.findString("SELL.SQL_GET_TBHB_DATA_WITH_YEAR_MONTH_CATEGORIES", new Object[] { Integer.valueOf(thisYear)-1, thisDataCategories });
            } catch (Exception e) {//捕获查询结果为空的异常,捕获后的处理是赋值为0.0
                e.printStackTrace();
            }
            if(StringUtils.hasText(lastSaleData)&&(Double.valueOf(lastSaleData)!=0)){
                Double dataTB = (thisSaleData-Double.valueOf(lastSaleData))/Double.valueOf(lastSaleData);
                return dataTB;
            }
            else{//没有同比的数据的处理方法,需要添加就是在这里添加
                return 0.0;
            }
        }
        else {
            throw new BusinessProcessException("数据非法!");
        }
    }
    

    private Double getHB(Double thisSaleData, String thisYear, Integer thisDataCategories, String queryType) {
        if(queryType.equals("2")){//季度环比 
            String lastSaleData = null;
            if(thisDataCategories==1){//第一季度需要单独处理
                try {
                    lastSaleData = jdbcAccessContext.findString("SELL.SQL_GET_TBHB_DATA_WITH_YEAR_QUARTER_CATEGORIES", new Object[] { Integer.valueOf(thisYear)-1, 4 });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    lastSaleData = jdbcAccessContext.findString("SELL.SQL_GET_TBHB_DATA_WITH_YEAR_QUARTER_CATEGORIES", new Object[] { Integer.valueOf(thisYear), thisDataCategories-1 });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(StringUtils.hasText(lastSaleData)&&(Double.valueOf(lastSaleData)!=0)){
                Double dataHB = (thisSaleData-Double.valueOf(lastSaleData))/Double.valueOf(lastSaleData);
                return dataHB;
            }else{
                return 0.0;
            }
        }else if(queryType.equals("3")){//月度环比
            String lastSaleData = null;
            if(thisDataCategories==1){//第一季度需要单独处理
                try {
                    lastSaleData = jdbcAccessContext.findString("SELL.SQL_GET_TBHB_DATA_WITH_YEAR_MONTH_CATEGORIES", new Object[] { Integer.valueOf(thisYear)-1, 12 });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    lastSaleData = jdbcAccessContext.findString("SELL.SQL_GET_TBHB_DATA_WITH_YEAR_MONTH_CATEGORIES", new Object[] { Integer.valueOf(thisYear), thisDataCategories-1 });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(StringUtils.hasText(lastSaleData)&&(Double.valueOf(lastSaleData)!=0)){
                Double dataHB = (thisSaleData-Double.valueOf(lastSaleData))/Double.valueOf(lastSaleData);
                return dataHB;
            }else{
                return 0.0;
            }
        }else{
            throw new BusinessProcessException("数据非法!");
        }
    }

    private void assembleDataForQuartor(GetSalesReportWithExtraResponse response, List<DataBox> dataBoxs, GetSalesRrequest request) {
        String[] categories = new String[4];
        for(int count = 0;count<4;count++){
            categories[count] = String.valueOf(count+1)+ "季度"; 
        }
        Series[] series = new Series[3];
        for(int i=0;i<3;i++){
            if(i==0){
                Series tempSeries = new Series();
                tempSeries.setName("销售额");
                Double[] tempDoubleData = new Double[4];
                for(int j=0;j<4;j++){
                    Boolean flag = true;
                    for(DataBox tempDataBox:dataBoxs){
                        if((j+1)==Integer.valueOf(tempDataBox.getCategorie())){
                            tempDoubleData[j] = tempDataBox.getSerie();
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        tempDoubleData[j] = 0.0;
                    }
                }
                tempSeries.setData(tempDoubleData);
                series[i] = tempSeries;
            }else if(i==1){
                Series tempSeries = new Series();
                tempSeries.setName("同比");
                Double[] tempDoubleData = new Double[4];
                for(int j=0;j<4;j++){
                    Boolean flag = true;
                    for(DataBox tempDataBox:dataBoxs){
                        if((j+1)==Integer.valueOf(tempDataBox.getCategorie())){
                            tempDoubleData[j] = getTB(tempDataBox.getSerie(),request.getKeys(),(j+1),request.getQueryType());
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        tempDoubleData[j] = 0.0;
                    }
                }
                tempSeries.setData(tempDoubleData);
                series[i] = tempSeries;
            }else if(i==2){
                Series tempSeries = new Series();
                tempSeries.setName("环比");
                Double[] tempDoubleData = new Double[4];
                for(int j=0;j<4;j++){
                    Boolean flag = true;
                    for(DataBox tempDataBox:dataBoxs){
                        if((j+1)==Integer.valueOf(tempDataBox.getCategorie())){
                            tempDoubleData[j] = getHB(tempDataBox.getSerie(),request.getKeys(),(j+1),request.getQueryType());
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        tempDoubleData[j] = 0.0;
                    }
                }
                tempSeries.setData(tempDoubleData);
                series[i] = tempSeries;
            }
        }
        response.setCategories(categories);
        response.setSeries(series);
        response.setType(request.getQueryType());
    }
    // 按年统计销售额
    private void loadSaleroomSqlByYear(StringBuilder querySql, List<Object> queryParms, GetSalesRrequest request) {
        querySql.append("SELECT  date_format(s.bills_date,'%Y') categorie,sum(s.total_price) serie from sell_shipments s WHERE s.bills_date IS NOT NULL "
            + " AND date_format(s.bills_date,'%Y') <=? GROUP BY date_format(s.bills_date,'%Y')");
        queryParms.add(StringUtils.hasText(request.getKeys()) ? request.getKeys() : DateTime.now().getYear());
    }

    // 按季度统计销售额
    private void loadSaleroomSqlByQuarter(StringBuilder querySql, List<Object> queryParms, GetSalesRrequest request) {
        querySql.append("SELECT  quarter(s.bills_date) categorie,sum(s.total_price) serie from sell_shipments s WHERE s.bills_date IS NOT NULL "
            + " AND date_format(s.bills_date,'%Y') =? GROUP BY quarter(s.bills_date)");
        queryParms.add(StringUtils.hasText(request.getKeys()) ? request.getKeys() : DateTime.now().getYear());
    }

    // 按月统计销售额
    private void loadSaleroomSqlByMonth(StringBuilder querySql, List<Object> queryParms, GetSalesRrequest request) {
        querySql.append("SELECT date_format(s.bills_date,'%m') categorie,sum(s.total_price) serie from sell_shipments s WHERE s.bills_date IS NOT NULL "
            + " and s.total_price is NOT null AND date_format(s.bills_date,'%Y') =? GROUP BY date_format(s.bills_date,'%m')");
        queryParms.add(StringUtils.hasText(request.getKeys()) ? request.getKeys() : DateTime.now().getYear());
    }

    // 按日统计销售额
    private void loadSaleroomSqlByDay(StringBuilder querySql, List<Object> queryParms, GetSalesRrequest request) {
        querySql.append("SELECT date_format(s.bills_date,'%d') categorie,sum(s.total_price) serie from sell_shipments s WHERE s.bills_date IS NOT NULL "
            + "AND s.total_price is NOT NULL AND date_format(s.bills_date,'%Y-%m') =? GROUP BY date_format(s.bills_date,'%d')");
        queryParms.add(StringUtils.hasText(request.getKeys()) ? request.getKeys() : DateTime.now().getMonthOfYear());
    }

    class DataBox { // 用于取数据
        private String categorie;
        private double serie;

        public String getCategorie() {
            return categorie;
        }

        public void setCategorie(String categorie) {
            this.categorie = categorie;
        }

        public double getSerie() {
            return serie;
        }

        public void setSerie(double serie) {
            this.serie = serie;
        }
    }

    public PagingResponse<CustomerSaleResponse> getCustomerSaleData(CustomerSaleRequest request) {
        PagingResponse<CustomerSaleResponse> response = new PagingResponse<CustomerSaleResponse>();
        StringBuilder listSql = new StringBuilder();
        StringBuilder totalSql = new StringBuilder();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParam = new ArrayList<Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        buildCustomerSaleSql(request, listSql, totalSql, queryParam, totalParam);
        CustomerSaleSumResponse total = jdbcAccessContext.findUniqueResultWithoutSqlManager(totalSql.toString(), new RowMapper<CustomerSaleSumResponse>() {
            @Override
            public CustomerSaleSumResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildCustomerSaleSumList(paramResultSet);
            }
        }, totalParam.toArray());
        List<CustomerSaleResponse> list = jdbcAccessContext.findWithoutSqlManager(listSql.toString(), new RowMapper<CustomerSaleResponse>() {
            @Override
            public CustomerSaleResponse mapRow(ResultSet paramResultSet, int paramInt) throws SQLException {
                return buildCustomerSaleList(paramResultSet);
            }
        }, queryParam.toArray());
        response.setRecords(list);
        response.setTotalRecords(total.getPageCount());
        map.put("totalMoney", total.getTotalMoney());
        response.setStats(map);
        return response;
    }

    private CustomerSaleResponse buildCustomerSaleList(ResultSet rs) throws SQLException {
        CustomerSaleResponse response = new CustomerSaleResponse();
        response.setCustomerNo(rs.getString("partner_code"));
        response.setCustomerName(rs.getString("partner_name"));
        response.setMoney(rs.getDouble("totalNoTax"));
        return response;
    }

    private CustomerSaleSumResponse buildCustomerSaleSumList(ResultSet rs) throws SQLException {
        CustomerSaleSumResponse response = new CustomerSaleSumResponse();
        response.setPageCount(rs.getInt("pageCount"));
        response.setTotalMoney(rs.getDouble("totalNoTax"));
        return response;
    }

    private void buildCustomerSaleSql(CustomerSaleRequest request, StringBuilder listSql, StringBuilder totalSql, List<Object> queryParam, List<Object> totalParam) {
        listSql.append("SELECT b.id customerId,b.partner_code,b.partner_name,SUM(i.after_discount) totalNoTax FROM sell_shipments s LEFT JOIN "
            + "sell_shipments_item i ON s.shipments_number = i.shipments_number LEFT JOIN business_partner b ON s.partner_id = b.id WHERE 1=1 ");
        totalSql.append("SELECT COUNT(1) pageCount,SUM(t.totalNoTax) totalNoTax FROM (SELECT b.id customerId,SUM(i.after_discount) totalNoTax FROM "
            + "sell_shipments s LEFT JOIN sell_shipments_item i ON s.shipments_number = i.shipments_number LEFT JOIN business_partner b ON s.partner_id = b.id WHERE 1=1 ");
        if (StringUtils.hasText(request.getDateFrom())) {
            listSql.append("AND DATE_FORMAT(s.bills_date,'%Y-%m-%d') >= ? ");
            totalSql.append("AND DATE_FORMAT(s.bills_date,'%Y-%m-%d') >= ? ");
            queryParam.add(request.getDateFrom());
        }
        if (StringUtils.hasText(request.getDateTo())) {
            listSql.append("AND DATE_FORMAT(s.bills_date,'%Y-%m-%d') <= ? ");
            totalSql.append("AND DATE_FORMAT(s.bills_date,'%Y-%m-%d') <= ? ");
            queryParam.add(request.getDateTo());
        }
        if (StringUtils.hasText(request.getKeyword())) {
            listSql.append("AND (b.partner_code LIKE CONCAT('%',?,'%') OR b.partner_name LIKE CONCAT('%',?,'%')) ");
            totalSql.append("AND (b.partner_code LIKE CONCAT('%',?,'%') OR b.partner_name LIKE CONCAT('%',?,'%')) ");
            queryParam.add(request.getKeyword());
            queryParam.add(request.getKeyword());
        }
        listSql.append("GROUP BY s.partner_id ORDER BY b.id ");
        totalSql.append("GROUP BY s.partner_id) t");
        totalParam.addAll(queryParam);
        if (request.getIsPaging() == true && request.getPageNo() != null && request.getPageSize() != null) {
            listSql.append(" limit ?,?");
            queryParam.add((request.getPageNo() - 1) * request.getPageSize());
            queryParam.add(request.getPageSize());
        }
    }

    public GetSalesReportWithExtraResponse getSaleroomWithExtra(GetSalesRrequest request) {
        int reportType = 0;
        GetSalesReportWithExtraResponse response = new GetSalesReportWithExtraResponse();
        StringBuilder querySql = new StringBuilder();
        List<Object> queryParms = new ArrayList<Object>();
        if (request.getQueryType().equals("2")) {
            loadSaleroomSqlByQuarter(querySql, queryParms, request); // 按季度统计销售额
            reportType =2;
        }else if(request.getQueryType().equals("3")){
            loadSaleroomSqlByMonth(querySql, queryParms, request); // 按月份统计销售额
            reportType =3;
        }
        List<DataBox> dataBoxs = jdbcAccessContext.findWithoutSqlManager(querySql.toString(), new RowMapper<DataBox>() {
            @Override
            public DataBox mapRow(ResultSet rs, int paramInt) throws SQLException {
                return buildDataBox(rs);
            }
        }, queryParms.toArray());
        if(reportType==2){
            assembleDataForQuartor(response, dataBoxs, request);
        }else if(reportType==3){
            assembleDataForMonth(response,dataBoxs,request);
        }
        response.setType(request.getQueryType());
        return response;
    }

    private void assembleDataForMonth(GetSalesReportWithExtraResponse response, List<DataBox> dataBoxs, GetSalesRrequest request) {
        String[] categories = new String[12];
        for(int count = 0;count<12;count++){
            categories[count] = String.valueOf(count+1)+ "月"; 
        }
        Series[] series = new Series[3];//分别存放销售额，同比和环比数据
        for(int i=0;i<3;i++){
            if(i==0){
                Series tempSeries = new Series();
                tempSeries.setName("销售额");
                Double[] tempDoubleData = new Double[12];
                for(int j=0;j<12;j++){
                    Boolean flag = true;
                    for(DataBox tempDataBox:dataBoxs){
                        if((j+1)==Integer.valueOf(tempDataBox.getCategorie())){//用这种方式就可以规避0x和x不想等的情况
                            tempDoubleData[j] = tempDataBox.getSerie();
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        tempDoubleData[j] = 0.0;
                    }
                }
                tempSeries.setData(tempDoubleData);
                series[i] = tempSeries;
            }else if(i==1){
                Series tempSeries = new Series();
                tempSeries.setName("同比");
                Double[] tempDoubleData = new Double[12];
                for(int j=0;j<12;j++){
                    Boolean flag = true;
                    for(DataBox tempDataBox:dataBoxs){
                        if((j+1)==Integer.valueOf(tempDataBox.getCategorie())){
                            tempDoubleData[j] = getTB(tempDataBox.getSerie(),request.getKeys(),(j+1),request.getQueryType());//参数分别为数据，年份，当前月，报表类型(月报和季报)
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        tempDoubleData[j] = 0.0;
                    }
                }
                tempSeries.setData(tempDoubleData);
                series[i] = tempSeries;
            }else if(i==2){
                Series tempSeries = new Series();
                tempSeries.setName("环比");
                Double[] tempDoubleData = new Double[12];
                for(int j=0;j<12;j++){
                    Boolean flag = true;
                    for(DataBox tempDataBox:dataBoxs){
                        if((j+1)==Integer.valueOf(tempDataBox.getCategorie())){
                            tempDoubleData[j] = getHB(tempDataBox.getSerie(),request.getKeys(),(j+1),request.getQueryType());
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        tempDoubleData[j] = 0.0;
                    }
                }
                tempSeries.setData(tempDoubleData);
                series[i] = tempSeries;
            }
        }
        response.setCategories(categories);
        response.setSeries(series);
        response.setType(request.getQueryType());
    }
}
