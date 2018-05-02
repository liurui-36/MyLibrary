package com.erge.mylibrary.utils;

import com.erge.mylibrary.MyLibrary;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Map;

public class LogUtils {

    public static void d(String msg) {
        if (MyLibrary.DEBUG)
            Logger.d(msg);
    }

    public static void d(String tag, String msg) {
        if (MyLibrary.DEBUG)
            Logger.t(tag).d(msg);
    }

    public static void e(String msg) {
        if (MyLibrary.DEBUG)
            Logger.e(msg);
    }

    public static void e(String msg, Throwable t) {
        if (MyLibrary.DEBUG)
            Logger.e(msg, t);
    }

    public static void e(String tag, String msg) {
        if (MyLibrary.DEBUG)
            Logger.t(tag).e(msg);
    }

    public static void e(String tag, String msg, Throwable t) {
        if (MyLibrary.DEBUG)
            Logger.t(tag).e(t, msg, null);
    }

    public static void v(String msg) {
        if (MyLibrary.DEBUG)
            Logger.v(msg);
    }

    public static void v(String tag, String msg) {
        if (MyLibrary.DEBUG)
            Logger.t(tag).v(msg);
    }

    public static void i(String msg) {
        if (MyLibrary.DEBUG)
            Logger.i(msg);
    }

    public static void i(String tag, String msg) {
        if (MyLibrary.DEBUG)
            Logger.t(tag).i(msg);
    }

    public static void wtf(String tag, String msg) {
        if (MyLibrary.DEBUG)
            Logger.t(tag).wtf(msg);
    }

    public static void json(String json) {
        if (MyLibrary.DEBUG)
            Logger.json(json);
    }

    public static void json(String tag, String json) {
        if (MyLibrary.DEBUG)
            Logger.t(tag).json(json);
    }

    public static void xml(String xml) {
        if (MyLibrary.DEBUG)
            Logger.xml(xml);
    }

    public static void xml(String tag, String xml) {
        if (MyLibrary.DEBUG)
            Logger.t(tag).xml(xml);
    }

    public static <T> void array(T[] array) {
        if (MyLibrary.DEBUG)
            Logger.d(array);
    }

    public static <T> void array(String tag, T[] array) {
        if (MyLibrary.DEBUG)
            Logger.t(tag).d(array);
    }

    public static <T, K> void map(Map<T, K> map) {
        if (MyLibrary.DEBUG)
            Logger.d(map);
    }

    public static <T, K> void map(String tag, Map<T, K> map) {
        if (MyLibrary.DEBUG)
            Logger.t(tag).d(map);
    }

    public static <T> void list(List<T> list) {
        if (MyLibrary.DEBUG)
            Logger.d(list);
    }

    public static <T> void list(String tag, List<T> list) {
        if (MyLibrary.DEBUG)
            Logger.t(tag).d(list);
    }
}
