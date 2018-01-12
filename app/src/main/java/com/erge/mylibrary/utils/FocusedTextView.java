package com.erge.mylibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

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
 * Created by liurui on 2017/12/27.
 */
@SuppressLint("AppCompatCustomView")
public class FocusedTextView extends TextView {
    public FocusedTextView(Context context) {
        super(context);
    }

    public FocusedTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FocusedTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
