package com.jksdairyhub.jksdairyhub.Utils.Storage;

import android.util.Log;

import org.json.JSONObject;

public class Constants {
    public static final String PREF_NAME = "JK_DAIRY_HUB";
    public static final long ANIM_DELAY = 2500;
    public static final String DEFAULT_DATE_FORMAT = "dd MMM yyyy, EEEE '@' hh:mm a";
    public static final String sJSON_PARSE_ERROR = "JSONException";
    public static final String sJSON_SUCCESS = "JSON Success";
    public static final String sNO_DATA_FOUND = "No data found !";
    public static final String sSERVER_ERROR = "Server error";
    public static final String sNETWORK_ERROR = "Please enable internet";

    public static final int MY_SOCKET_TIMEOUT_MS = 5000;
    public static final int JSON_PARSE_ERROR = 300;
    public static final int NO_DATA_FOUND = 400;
    public static final int SERVER_ERROR = 500;
    public static final int NETWORK_ERROR = 500;

    public static final int REQ_VERIFY_LOGIN = 100;


    public static final String GET_VERIFY_LOGIN = "VerifyLogin";



    public static final String IP = "IP";
    public static final String EMP_LOGGED_IN = "EMP_LOGGED_IN";
    public static final String IS_NOTIFIED_FINGER_ENABLED = "IS_NOTIFIED_FINGER_ENABLED";
    public static final String CUSTOMERS = "CUSTOMERS";


    public static String getBaseUrlOf(String path) {
        return getBasePath() + path;
    }

    private static String getBasePath() {
        return "http://" + Preferences.getIP() + "/remediService/ReportService.ashx/";
    }

    public static void logger(String serverPath, JSONObject params) {
        Log.e("path", serverPath);
        Log.e("inputs", String.valueOf(params));
    }

}
