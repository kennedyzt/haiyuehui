package com.siping.smartone.postperiod.request;

import java.io.Serializable;

public class AddPostPeriodRequest implements Serializable {

    private static final long serialVersionUID = -4293434099130672539L;
    private String id;
    private String postPeriodNo;
    private String postPeriodName;
    private Boolean postPeriodFlag;
    private String postPeriodStartTime;
    private String postPeriodEndTime;
    private String description;

    public String getPostPeriodNo() {
        return postPeriodNo;
    }

    public void setPostPeriodNo(String postPeriodNo) {
        this.postPeriodNo = postPeriodNo;
    }

    public String getPostPeriodName() {
        return postPeriodName;
    }

    public void setPostPeriodName(String postPeriodName) {
        this.postPeriodName = postPeriodName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostPeriodStartTime() {
        return postPeriodStartTime;
    }

    public void setPostPeriodStartTime(String postPeriodStartTime) {
        this.postPeriodStartTime = postPeriodStartTime;
    }

    public String getPostPeriodEndTime() {
        return postPeriodEndTime;
    }

    public void setPostPeriodEndTime(String postPeriodEndTime) {
        this.postPeriodEndTime = postPeriodEndTime;
    }

    public Boolean getPostPeriodFlag() {
        return postPeriodFlag;
    }

    public void setPostPeriodFlag(Boolean postPeriodFlag) {
        this.postPeriodFlag = postPeriodFlag;
    }
}
