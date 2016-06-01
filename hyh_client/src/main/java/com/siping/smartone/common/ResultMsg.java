package com.siping.smartone.common;

import java.io.Serializable;

public class ResultMsg implements Serializable {

    private static final long serialVersionUID = -9197926144517133339L;

    /**
     * 处理成功标记
     */
    private boolean success;
    /**
     * 返回的消息
     */
    private String msg;

    private String billNumber;

    public ResultMsg() {
    }

    public ResultMsg(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public ResultMsg(boolean success, String msg, String billNumber) {
        this.success = success;
        this.msg = msg;
        this.billNumber = billNumber;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }
}
