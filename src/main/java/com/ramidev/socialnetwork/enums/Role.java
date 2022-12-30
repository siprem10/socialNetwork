package com.ramidev.socialnetwork.enums;

public enum Role {
    ADMIN_ROLE, USER_ROLE;
    public static Role getDefaultRole(){
        return USER_ROLE;
    }
}
