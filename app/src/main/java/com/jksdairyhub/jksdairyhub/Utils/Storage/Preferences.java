package com.jksdairyhub.jksdairyhub.Utils.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.jksdairyhub.jksdairyhub.App.Models.Customer;
import com.jksdairyhub.jksdairyhub.App.Models.User;
import com.jksdairyhub.jksdairyhub.Utils.AppController;

public class Preferences {
    private static Context context;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor pref_editor;
    private static Gson gson;

    private static void init() {
        context = AppController.getAppContext();
        preferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        pref_editor = preferences.edit();
        gson = new Gson();
    }

    public static String getIP() {
        init();
        return preferences.getString(Constants.IP, "");
    }

    public static void setIP(String ip) {
        init();
        pref_editor.putString(Constants.IP, ip);
        pref_editor.apply();
    }

    public static User getUserLoggedIn() {
        init();
        return gson.fromJson(preferences.getString(Constants.EMP_LOGGED_IN, gson.toJson(new User())), User.class);
    }

    public static void setUserLoggedIn(User emp) {
        init();
        pref_editor.putString(Constants.EMP_LOGGED_IN, gson.toJson(emp));
        pref_editor.apply();
    }

    public static boolean isNotifiedFingerEnabled(Context context) {
        init();
        return preferences.getBoolean(Constants.IS_NOTIFIED_FINGER_ENABLED, false);
    }

    public static void setNotifiedFingerEnabled() {
        init();
        pref_editor.putBoolean(Constants.IS_NOTIFIED_FINGER_ENABLED, true);
        pref_editor.apply();
    }

    public static Customer getCustomers() {
        init();
        return gson.fromJson(preferences.getString(Constants.CUSTOMERS, gson.toJson(new Customer())), Customer.class);
    }

    public static void setCustomers(Customer customer) {
        init();
        pref_editor.putString(Constants.CUSTOMERS, gson.toJson(customer));
        pref_editor.apply();
    }
}
