package com.erge.mylibrary.utils;

import android.preference.PreferenceManager;

import com.erge.mylibrary.MyLibrary;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +                                 <br/>
 * 　　　　　　　┏┛┻━━━┛┻┓ + +                                  <br/>
 * 　　　　　　　┃　　　　　　　┃                                <br/>
 * 　　　　　　　┃　　　━　　　┃ ++ + + +                        <br/>
 * 　　　　　　 ████━████ ┃+                                    <br/>
 * 　　　　　　　┃　　　　　　　┃ +                              <br/>
 * 　　　　　　　┃　　　┻　　　┃                                 <br/>
 * 　　　　　　　┃　　　　　　　┃ + +                            <br/>
 * 　　　　　　　┗━┓　　　┏━┛                                   <br/>
 * 　　　　　　　　　┃　　　┃                                    <br/>
 * 　　　　　　　　　┃　　　┃ + + + +                            <br/>
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting                   <br/>
 * <br/>
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug                   <br/>
 * 　　　　　　　　　┃　　　┃                             <br/>
 * 　　　　　　　　　┃　　　┃　　+                        <br/>
 * 　　　　　　　　　┃　 　　┗━━━┓ + +                    <br/>
 * 　　　　　　　　　┃ 　　　　　　　┣┓                    <br/>
 * 　　　　　　　　　┃ 　　　　　　　┏┛                    <br/>
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +                    <br/>
 * 　　　　　　　　　　┃┫┫　┃┫┫                           <br/>
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +                    <br/>
 * <p>
 * Created by liurui on 2017/12/9.
 */
public class SharedPreferencesUtils {

    public static void saveString(String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(MyLibrary.getApp())
                .edit().putString(key, value).commit();
    }

    public static void saveBoolean(String key, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(MyLibrary.getApp())
                .edit().putBoolean(key, value).commit();
    }

    public static void saveFloat(String key, float value) {
        PreferenceManager.getDefaultSharedPreferences(MyLibrary.getApp())
                .edit().putFloat(key, value).commit();
    }

    public static void saveInt(String key, int value) {
        PreferenceManager.getDefaultSharedPreferences(MyLibrary.getApp())
                .edit().putInt(key, value).commit();
    }

    public static void saveLong(String key, long value) {
        PreferenceManager.getDefaultSharedPreferences(MyLibrary.getApp())
                .edit().putLong(key, value).commit();
    }

    public static String getString(String key, String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(MyLibrary.getApp())
                .getString(key, defaultValue);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(MyLibrary.getApp())
                .getBoolean(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(MyLibrary.getApp())
                .getInt(key, defaultValue);
    }

    public static long getLong(String key, long defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(MyLibrary.getApp())
                .getLong(key, defaultValue);
    }

    public static float getFloat(String key, float defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(MyLibrary.getApp())
                .getFloat(key, defaultValue);
    }

}
