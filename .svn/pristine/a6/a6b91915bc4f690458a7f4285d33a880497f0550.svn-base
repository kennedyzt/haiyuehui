package com.siping.intranet.crm.businesspartner.repository;

import org.stroma.framework.core.util.StringUtils;

import com.siping.smartone.businesspartner.response.ValidateMessage;

public class BusinessPartnerBatchAddValidateHelper {

    public static ValidateMessage basicValidate(int rowIndex, int cellIndex, String value) {
        switch (cellIndex) {
            case 0:
                return validatePartnerCode(rowIndex, cellIndex, value);
            case 1:
                return validatePartnerType(rowIndex, cellIndex, value);
            case 2:
                return validatePartnerName(rowIndex, cellIndex, value);
            case 3:
                return validateForeignName(rowIndex, cellIndex, value);
            case 4:
                return validatePartnerGroup(rowIndex, cellIndex, value);
            case 5:
                return validatePhone(rowIndex, cellIndex, value);
            case 6:
                return validateMobilephone(rowIndex, cellIndex, value);
            case 7:
                return validateFax(rowIndex, cellIndex, value);
            case 8:
                return validateEmail(rowIndex, cellIndex, value);
            case 9:
                return validateWebsite(rowIndex, cellIndex, value);
            case 10:
                return validateBankAccount(rowIndex, cellIndex, value);
            case 11:
                return validateContactsLastName(rowIndex, cellIndex, value);
            case 12:
                return validateContactsLastName(rowIndex, cellIndex, value);
            default:
                return new ValidateMessage();
        }
    }

    private static ValidateMessage validatePartnerCode(int rowIndex, int cellIndex, String value) {
        if (!StringUtils.hasText(value))
            return new ValidateMessage(rowIndex, cellIndex + 1, "编码不能为空", true);
        if (StringUtils.hasText(value) && value.trim().length() > 18)
            return new ValidateMessage(rowIndex, cellIndex + 1, "编号不能超过18个字符", true);
        return new ValidateMessage();
    }

    private static ValidateMessage validatePartnerType(int rowIndex, int cellIndex, String value) {
        if (StringUtils.hasText(value) && value.trim().length() > 20)
            return new ValidateMessage(rowIndex, cellIndex + 1, "业务伙伴类型不能超过20个字符", false);
        return new ValidateMessage();
    }

    private static ValidateMessage validatePartnerName(int rowIndex, int cellIndex, String value) {
        if (!StringUtils.hasText(value))
            return new ValidateMessage(rowIndex, cellIndex + 1, "公司全名不能为空", true);
        return new ValidateMessage();
    }

    private static ValidateMessage validateForeignName(int rowIndex, int cellIndex, String value) {
        if (StringUtils.hasText(value) && value.length() > 30) {
            return new ValidateMessage(rowIndex, cellIndex + 1, "分类不能超过30个字符", false);
        }
        return new ValidateMessage();
    }

    private static ValidateMessage validatePartnerGroup(int rowIndex, int cellIndex, String value) {
        return new ValidateMessage();
    }

    private static ValidateMessage validatePhone(int rowIndex, int cellIndex, String value) {
        if (StringUtils.hasText(value)) {
            if (value.length() > 13 || value.length() < 7) {
                return new ValidateMessage(rowIndex, cellIndex + 1, "座机号码必需大于7位且不能超过13位", false);
            }
        }
        return new ValidateMessage();
    }

    private static ValidateMessage validateFax(int rowIndex, int cellIndex, String value) {
        if (StringUtils.hasText(value))
            if (value.length() > 11) {
                return new ValidateMessage(rowIndex, cellIndex + 1, "传真机位数不能超过11", false);
            }
        return new ValidateMessage();
    }

    private static ValidateMessage validateMobilephone(int rowIndex, int cellIndex, String value) {
        if (StringUtils.hasText(value)) {
            if (value.length() != 11) {
                return new ValidateMessage(rowIndex, cellIndex + 1, "手机号码必需为11位", false);
            }
        }
        return new ValidateMessage();
    }

    private static ValidateMessage validateEmail(int rowIndex, int cellIndex, String value) {
        if (StringUtils.hasText(value)) {
            String regex = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            if (!value.matches(regex)) {
                return new ValidateMessage(rowIndex, cellIndex + 1, "邮箱格式不正确", false);
            }
        }
        return new ValidateMessage();
    }

    private static ValidateMessage validateWebsite(int rowIndex, int cellIndex, String value) {
        String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        if (!value.matches(regex)) {
            return new ValidateMessage(rowIndex, cellIndex + 1, "网址格式不正确", false);
        }
        return new ValidateMessage();
    }

    private static ValidateMessage validateBankAccount(int rowIndex, int cellIndex, String value) {
        if (StringUtils.hasText(value)) {
            if (value.length() != 16) {
                return new ValidateMessage(rowIndex, cellIndex + 1, "银行卡号位数不正确必需为16位", false);
            }
        }
        return new ValidateMessage();
    }

    private static ValidateMessage validateContactsLastName(int rowIndex, int cellIndex, String value) {
        return new ValidateMessage();
    }
}
