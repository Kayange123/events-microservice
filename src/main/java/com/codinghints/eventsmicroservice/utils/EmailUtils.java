package com.codinghints.eventsmicroservice.utils;

public class EmailUtils {
    public static String getEmailMessage(String name, String host, String token) {
        return "Hello "+name+ ", \n\nYour Account has been created. Please click on the link below to verify your account. \n\n"+
                getVerificationLink(host, token) + "\n\n The support Team";
    }

    public static String getPasswordResetMessage(String name, String host, String token) {
        return "Hello "+name+ ", \n\nYour Account has been created. Please click on the link below to verify your account. \n\n"+
                getPasswordResetLink(host, token) + "\n\n The support Team";
    }

    private static String getVerificationLink(String host, String token) {
        return host+ "/verify/account?token="+token;
    }
    private static String getPasswordResetLink(String host, String token) {
        return host+ "/verify/password?token="+token;
    }
}
