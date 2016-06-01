package com.siping.smartone.wms.request;

import java.io.Serializable;
import java.util.List;

import com.siping.smartone.inventory.request.ItemReceiptCountsRequest;

public class ConfirmReceiptParam implements Serializable {

    private static final long serialVersionUID = 1L;
    private String fromBillsNo;
    private String receiptNumber;
    private Integer loginId;
    private List<ItemReceiptCountsRequest> itemReceiptCounts;
    // 为了记录日志需要
    private String ip;
    private String mac;

    public String getFromBillsNo() {
        return fromBillsNo;
    }

    public void setFromBillsNo(String fromBillsNo) {
        this.fromBillsNo = fromBillsNo;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public List<ItemReceiptCountsRequest> getItemReceiptCounts() {
        return itemReceiptCounts;
    }

    public void setItemReceiptCounts(List<ItemReceiptCountsRequest> itemReceiptCounts) {
        this.itemReceiptCounts = itemReceiptCounts;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}