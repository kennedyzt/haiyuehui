package com.siping.smartone.businesspartner.request;

public enum BusinessPartnerBaseAttributeTile {

    PARTNER_CODE("编码", 0, true), PARTNER_TYPE("类型", 1, true), PARTNER_NAME("公司名称", 2, true), FOREIGN_NAME("英文名", 3, true), PARTNER_GROUP("组", 4, false), PHONE("电话", 5, true), MOBILEPHONE("移动电话", 6,
        false), FAX("传真", 7, false), EMAIL("邮箱", 8, false), WEBSITE("站点", 9, false), BANK_ACCOUNT("银行账户", 10, false), CONTACTS_LAST_NAME("联系人姓氏", 11, false), CONTACTS_FIRST_NAME("联系人名", 12, false), CONTACTS_GENDER(
        "性别", 13, false), CONTACTS_PHONE("联系人电话", 14, false), CONTACTS_MOBILEPHONE("联系人手机", 15, false), CONTACTS_DESCRIPTION("描述", 16, false), ADDRESS("地址", 17, false);

    private String name; // 名称

    private int index; // 顺序

    private boolean required; // 是否必填

    private BusinessPartnerBaseAttributeTile(String name, int index, boolean required) {
        this.name = name;
        this.index = index;
        this.required = required;
    }

    public String getName() {
        return this.name;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean isRequired() {
        return this.required;
    }
}
