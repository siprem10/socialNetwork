package com.ramidev.socialnetwork.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {

    public static String pwdEncoder(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static boolean pwdCompare(String password, String pwdHash) {
        return new BCryptPasswordEncoder().matches(password, pwdHash);
    }

}
