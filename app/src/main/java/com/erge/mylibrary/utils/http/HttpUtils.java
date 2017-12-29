package com.erge.mylibrary.utils.http;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.PostRequest;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

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
 * Created by liurui on 2017/12/20.
 */
public class HttpUtils {

    public static void get(String url, final HttpCallBack callBack) {
        OkGo.get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, okhttp3.Response response) {
                try {
                    callBack.onSuccess(new JSONObject(s));
                } catch (JSONException e) {
                    callBack.onSuccess(null);
                }
            }

            @Override
            public void onError(Call call, okhttp3.Response response, Exception e) {
                callBack.onError(e);
            }
        });
    }

    public static void post(String url, Object object, final HttpCallBack callBack) {
        PostRequest request = OkGo.post(url);
        if (object != null) {
            if (object instanceof JSONObject) {
                request.upJson((JSONObject) object);
            } else {
                request.upJson(new Gson().toJson(object));
            }
        }
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, okhttp3.Response response) {
                try {
                    callBack.onSuccess(new JSONObject(s));
                } catch (JSONException e) {
                    callBack.onSuccess(null);
                }
            }

            @Override
            public void onError(Call call, okhttp3.Response response, Exception e) {
                callBack.onError(e);
            }
        });
    }
}
