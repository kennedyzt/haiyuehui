package com.siping.smartone.report.response;

import java.io.Serializable;

public class GetSalesReportWithExtraResponse implements Serializable {
    private static final long serialVersionUID = 4741319342635242019L;
    private String type; // type=1，2，3；1表示按年，2表示按月，3表示按日
    private String[] categories; // 类别，比如按年，上面的list存2013，2014。。。
    private Series[] series; //

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public Series[] getSeries() {
        return series;
    }

    public void setSeries(Series[] series) {
        this.series = series;
    }
}
