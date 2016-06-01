package com.siping.smartone.login;

import org.codehaus.jackson.annotate.JsonCreator;

public enum UserType {
    Employee, BusiPartner;
    @JsonCreator
    public static UserType create(String typeStr) {
        UserType[] values = UserType.values();
        for (UserType type : values) {
            if (type.toString().equalsIgnoreCase(typeStr)) {
                return type;
            }
        }
        return Employee;
    }
}
