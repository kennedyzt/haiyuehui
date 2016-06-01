package com.siping.smartone.purchase.response;

import java.io.Serializable;
import java.util.List;

import com.siping.smartone.purchase.PurchaseBase;

public class PurchaseReceiptResponse extends PurchaseBase implements Serializable {
    private static final long serialVersionUID = 1L;
    private String receiptNumber;
    //供应商id
    private Integer supplierId;
    //供应商编码
    private String supplierCode;
    //供应商名称
    private String supplierName;
    //供应商联系人名
    private String contactsFirstName;
    //供应商联系人姓
    private String contactsLastName;
    //供应商联系人移动电话
    private String mobile;
    //入库仓库
    private Integer inBoundStorage;
    //入库仓库名称
    private String inBoundStorageName;

    /**表尾信息
     * 
     */
    //总金额
    private Double totalAmount;
    //赠品金额
    private Double giftAmount;
    //优惠金额
    private Double discountAmount;
    //应付金额=总金额-优惠金额
    private Double payAmount;

    //表体对象列表
    private List<PurchaseReceiptItemResponse> items;


    public Integer getSupplierId() {
        return supplierId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getInBoundStorage() {
        return inBoundStorage;
    }

    public void setInBoundStorage(Integer inBoundStorage) {
        this.inBoundStorage = inBoundStorage;
    }

    public String getInBoundStorageName() {
        return inBoundStorageName;
    }

    public void setInBoundStorageName(String inBoundStorageName) {
        this.inBoundStorageName = inBoundStorageName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(Double giftAmount) {
        this.giftAmount = giftAmount;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public List<PurchaseReceiptItemResponse> getItems() {
        return items;
    }

    public void setItems(List<PurchaseReceiptItemResponse> items) {
        this.items = items;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getContactsFirstName() {
        return contactsFirstName;
    }

    public void setContactsFirstName(String contactsFirstName) {
        this.contactsFirstName = contactsFirstName;
    }

    public String getContactsLastName() {
        return contactsLastName;
    }

    public void setContactsLastName(String contactsLastName) {
        this.contactsLastName = contactsLastName;
    }
    

}
