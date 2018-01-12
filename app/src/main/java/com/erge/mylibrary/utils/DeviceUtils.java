package com.erge.mylibrary.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;

import com.erge.mylibrary.MyLibrary;

import java.io.File;
import java.util.List;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * <p>
 * Created by liurui on 2017/5/18.
 */
public class DeviceUtils {
    private static final String TAG = "DeviceUtils";

    /**
     * 读取网络类型。<br>
     * 0为移动网络、1为WIFI、-1为无网络
     */
    public static int getNetworkType() {
        ConnectivityManager manager = (ConnectivityManager) MyLibrary.getApp()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = manager.getActiveNetworkInfo();
        if (netInfo != null) {
            if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE
                    || netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                Log.i(TAG, "网络类型:" + (netInfo.getType() == 0 ? "移动网络" : "WIFI"));
                return netInfo.getType();
            } else {
                Log.i(TAG, "无网络");
                return -1;
            }
        } else {
            Log.i(TAG, "无网络");
            return -1;
        }
    }

    /**
     * 获取IMEI
     */
    @SuppressLint("MissingPermission")
    public static String getImei() {
        TelephonyManager manager = (TelephonyManager) MyLibrary.getApp()
                .getSystemService(Context.TELEPHONY_SERVICE);
        Log.i(TAG, "获取IMEI:" + manager.getDeviceId());
        return manager.getDeviceId();
    }

    /**
     * 返回当前程序版本名称
     */
    public static String getAppVersionName() {
        String versionName = "";
        try {
            PackageManager pm = MyLibrary.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(MyLibrary.getApp().getPackageName(), 0);
            versionName = pi.versionName;
            Log.i(TAG, "获取当前程序版本名称:versionName=" + versionName);
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("获取当前程序版本名称", "Exception", e);
        }
        return versionName;
    }

    /**
     * 返回当前程序版本号
     */
    public static int getAppVersionCode() {
        int versionCode = -1;
        try {
            PackageManager pm = MyLibrary.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(MyLibrary.getApp().getPackageName(), 0);
            versionCode = pi.versionCode;
            Log.i(TAG, "返回当前程序版本号:versionCode=" + versionCode);
        } catch (Exception e) {
            Log.e("返回当前程序版本号", "Exception", e);
        }
        return versionCode;
    }

    /**
     * 安装APK
     *
     * @param apk
     */
    public static void installAPK(File apk) {
        Log.i(TAG, "安装APK:path=" + apk.getAbsolutePath());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(MyLibrary.getApp(), MyLibrary.getFileProvider(), apk);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apk), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        MyLibrary.getApp().startActivity(intent);
    }

    /**
     * 打开相机
     * 兼容7.0
     *
     * @param activity    Activity
     * @param file        File
     * @param requestCode result requestCode
     */
    public static void startActionCapture(Activity activity, File file, int requestCode) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriForFile(activity, file));
        activity.startActivityForResult(intent, requestCode);
    }

    public static Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context.getApplicationContext(), MyLibrary.getFileProvider(), file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    /**
     * 发送短信
     *
     * @param phoneNumber 目标手机号
     * @param message     消息内容
     */
    public static void sendSMS(String phoneNumber, String message) {
        Log.i(TAG, "sendSMS:" + phoneNumber + "----" + message);
        //获取短信管理器
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
        //拆分短信内容（手机短信长度限制）
        List<String> divideContents = smsManager.divideMessage(message);
        for (String text : divideContents) {
            smsManager.sendTextMessage(phoneNumber, null, text, null, null);
        }
    }

    /**
     * 打电话
     *
     * @param phoneNumber
     */
    public static void callPhone(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (ActivityCompat.checkSelfPermission(MyLibrary.getApp(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        MyLibrary.getApp().startActivity(intent);
    }

    public static int dp2px(int dpval) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpval, MyLibrary.getApp().getResources().getDisplayMetrics());
    }

    public static int px2dp(float pxValue) {
        final float scale = MyLibrary.getApp().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
