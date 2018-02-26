package com.meteosurf.meteosurf.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {

    public static final String SHARED_PREF = "configMeteoSurf";
    public static final String SHARED_PREF_LOGIN = "isLogin";
    public static final String SHARED_PREF_LOGIN_NAME = "logName";
    public static final String SHARED_PREF_LOGIN_MAIL = "logMail";
    public static final String SHARED_PREF_LOGIN_TOKEN = "logToken";
    public static final String SHARED_PREF_LOGIN_API = "apiToken";
    public static final String SHARED_PREF_SPOT_ID = "spotId";
    public static final String SHARED_PREF_SPOT_NAME = "spotName";
    public static final String SHARED_PREF_SPOT = "isSpot";

    private static final String MAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String NAME_PATTERN = "^[A-Za-z0-9][A-Za-z0-9_]{3,15}$";

    public static boolean validMail(String mail) {

        Pattern pattern = Pattern.compile(MAIL_PATTERN);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();

    }

    public static boolean validName(String name) {

        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();

    }

}
