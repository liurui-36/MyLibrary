package com.erge.mylibrary.utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

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
 * Created by liurui on 2017/6/5.
 */
public class CrashUtils implements Thread.UncaughtExceptionHandler {

    private static boolean saveErrorLog = false;
    private static Context ctx;

    private static final String TAG = "CrashHandler";
    // CrashUtils实例
    private static CrashUtils instance;
    // 程序的Context对象
    private Application application;
    // 系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /**
     * 保证只有一个CrashUtils实例
     */
    private CrashUtils(Context context) {
        application = (Application) context.getApplicationContext();
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 获取CrashUtils实例 ,单例模式
     */
    public static void init(Context context) {
        CrashUtils inst = instance;
        ctx = context;
        if (inst == null) {
            synchronized (CrashUtils.class) {
                inst = instance;
                if (inst == null) {
                    inst = new CrashUtils(context.getApplicationContext());
                    instance = inst;
                }
            }
        }
    }

    /**
     * 获取CrashUtils实例 ,单例模式
     */
    public static CrashUtils getInstance() {
        if (instance == null) {
            init(ctx);
        }
        return instance;
    }

    public static void openSaveLog() {
        Log.i(TAG, "openSaveLog");
        saveErrorLog = true;
    }

    public static void closeSaveLog() {
        Log.i(TAG, "closeSaveLog");
        saveErrorLog = false;
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (saveErrorLog) {
            saveCrashInfoFile(ex);
        }
        Log.i(TAG, "e.message : " + ex.getMessage());
        mDefaultHandler.uncaughtException(thread, ex);
    }


    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private void saveCrashInfoFile(Throwable ex) {
        try {
            String time = TimeUtils.getNowDate19();
            String error = time + "\n" + getError(ex);
            Log.e(TAG, error);

            File file = new File(FileUtils.getAppPath() + "/log/", "crash-" + time);
            FileUtils.writeTxtToFile(file, error, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getError(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        return writer.toString();
    }
}
