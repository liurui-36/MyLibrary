package com.erge.mylibrary.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
 * Created by liurui on 2017/11/15.
 */
public class UIUtils {

    private static final String TAG = "UIUtils";

    private static Toast mToast;

    public static void toastInfo(String message) {
        if (message != null) {
            if (mToast == null) {
                mToast = Toast.makeText(MyLibrary.getInstance().getApp(), message, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(message);
                mToast.setDuration(Toast.LENGTH_SHORT);
            }
            mToast.show();
            Log.i(TAG, "toast --> " + message);
        }
    }

    public static void toastInfo(int strRes) {
        String msg = MyLibrary.getInstance().getApp().getResources().getString(strRes);
        toastInfo(msg);
    }

    private static Snackbar snackbar;

    /**
     * 利用 Snackbar 显示消息
     *
     * @param activity
     * @param message
     */
    public static void SnackbarInfo(Activity activity, String message, String actionText, View.OnClickListener listener) {
        if (snackbar == null) {
            snackbar = Snackbar.make(activity.getWindow().getDecorView(), message, Snackbar.LENGTH_LONG).setAction(actionText, listener);
        } else {
            snackbar.setText(message);
            snackbar.setAction(actionText, listener);
        }
        snackbar.show();
        Log.i(TAG, "Snackbar --> " + message);
    }

    public static ProgressDialog showWaitDialog(Context context, String msg) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(msg);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Log.i(TAG, "ProgressDialog --> " + msg);
        return dialog;
    }

    /**
     * 弹出AlertDialog
     *
     * @param title          标题
     * @param msg            内容
     * @param okBtnTxt       确认按钮文字
     * @param okListener     确认按钮监听
     * @param cancelTxt      取消按钮文字
     * @param cancelListener 取消按钮监听
     */
    public static AlertDialog createDefaultAlertDialog(Context context, String title, String msg, String okBtnTxt, DialogInterface.OnClickListener okListener, String cancelTxt, DialogInterface.OnClickListener cancelListener, boolean canceledOnTouchOutside) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(title)) builder.setTitle(title);
        if (!TextUtils.isEmpty(msg)) builder.setMessage(msg);
        if (!TextUtils.isEmpty(okBtnTxt)) builder.setPositiveButton(okBtnTxt, okListener);
        if (!TextUtils.isEmpty(cancelTxt)) builder.setNegativeButton(cancelTxt, cancelListener);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        dialog.show();
        Log.i(TAG, "AlertDialog --> " + msg);
        return dialog;
    }

    /**
     * 创建一个自定义布局的AlertDialog
     *
     * @param context
     * @param v
     * @return
     */
    public static AlertDialog createAlertDialog(Context context, View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(v);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
    }
}
