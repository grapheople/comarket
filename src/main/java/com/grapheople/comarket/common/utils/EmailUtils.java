package com.grapheople.comarket.common.utils;

public class EmailUtils {
    public static String getEmailDomain(String email) {
        return email.substring(email.indexOf('@') + 1);
    }
}
