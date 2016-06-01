package com.siping.smartone.wms.response;

import java.io.Serializable;
import java.util.List;

import com.siping.smartone.wms.OrderPickBase;

public class OrderPickResponse extends OrderPickBase implements Serializable {

    private static final long serialVersionUID = 1L;
    private String storageName;
    private List<OrderPickItemResponse> items;
    private String loginUserName;
    private List<StorageLocationOrderPickItemResponse> storageLocationOrderPickItems; // 按库位
    private String fromBillsNumbers; //来自单据的拼接

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public List<OrderPickItemResponse> getItems() {
        return items;
    }

    public void setItems(List<OrderPickItemResponse> items) {
        this.items = items;
    }

    public List<StorageLocationOrderPickItemResponse> getStorageLocationOrderPickItems() {
        return storageLocationOrderPickItems;
    }

    public void setStorageLocationOrderPickItems(List<StorageLocationOrderPickItemResponse> storageLocationOrderPickItems) {
        this.storageLocationOrderPickItems = storageLocationOrderPickItems;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getFromBillsNumbers() {
        return fromBillsNumbers;
    }

    public void setFromBillsNumbers(String fromBillsNumbers) {
        this.fromBillsNumbers = fromBillsNumbers;
    }

}
