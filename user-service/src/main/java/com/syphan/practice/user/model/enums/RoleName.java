package com.syphan.practice.user.model.enums;

import lombok.Getter;

@Getter
public enum RoleName {
    ADMIN("ROLE_SYS_ADMIN", "System Administrator");

    private String code;
    private String name;

    private RoleName(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static RoleName getRoleNameByCode(String code) {
        for (RoleName gender : RoleName.values()) {
            if (gender.code.equals(code)) {
                return gender;
            }
        }
        return null;
    }
}
