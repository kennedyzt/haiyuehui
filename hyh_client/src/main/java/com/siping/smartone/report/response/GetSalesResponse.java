package com.siping.smartone.report.response;

import java.io.Serializable;

public class GetSalesResponse implements Serializable {
    private static final long serialVersionUID = -8047867271795507799L;
    private String type; // type=1，2，3；1表示按年，2表示按月，3表示按日
    private String[] categories; // 类别，比如按年，上面的list存2013，2014。。。
    private double[] series; // 一系列数 ，比如2013对应的单量20，2014对应的单量30。。。

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

    public double[] getSeries() {
        return series;
    }

    public void setSeries(double[] series) {
        this.series = series;
    }
}
