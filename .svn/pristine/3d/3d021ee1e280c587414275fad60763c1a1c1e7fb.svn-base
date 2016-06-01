package com.siping.service.businesspartner.repository;

import java.util.List;

import org.stroma.framework.core.util.StringUtils;

public class BusinessPartnerRestRepositoryHelper {

    public static String loadDynamicGetBusinessPartnerSql(Integer id, String partnerName, List<Object> queryParam) {
        StringBuilder sql = new StringBuilder(
            "SELECT id id,partner_code partnerCode,partner_type partnerType,partner_name partnerName,foreign_name foreignName,partner_group partnerGroup,phone phone,mobilephone mobilephone,fax fax,email email,website website,contacts_last_name contactsLastName,contacts_first_name contactsFirstName,contacts_gender contactsGender,contacts_phone contactsPhone,contacts_mobilephone contactsMobilephone,contacts_desiption contactsDesiption,address address,create_date createDate,create_by createBy,update_date updateDate,update_by updateBy from business_partner where is_delete=false and is_enable=true");
        if (null != id) {
            sql.append(" and id=? ");
            queryParam.add(id);
        }
        if (StringUtils.hasText(partnerName)) {
            sql.append(" and partner_name like CONCAT('%',?,'%')");
            queryParam.add(partnerName);
        }
        return sql.toString();
    }

}
