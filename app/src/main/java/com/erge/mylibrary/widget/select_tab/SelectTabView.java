package com.erge.mylibrary.widget.select_tab;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.erge.mylibrary.R;
import com.erge.mylibrary.utils.DeviceUtils;


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
 * Created by liurui on 2018/1/9.
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class SelectTabView extends RelativeLayout {
    public SelectTabView(Context context) {
        this(context, null);
    }

    public SelectTabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initView();
    }

    private SelectedListener listener;

    private String leftText;
    private String rightText;
    private float textSize;
    private int selectedTextColor;
    private int unSelectedTextColor;
    private int leftSelectedBg;
    private int rightSelectedBg;
    private boolean rightSelected;

    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.SelectTabView);
        leftText = ta.getString(R.styleable.SelectTabView_leftString);
        rightText = ta.getString(R.styleable.SelectTabView_rightString);
        textSize = ta.getDimension(R.styleable.SelectTabView_stringSize, 30f);
        selectedTextColor = ta.getResourceId(R.styleable.SelectTabView_selectedTextColor, Color.BLACK);
        unSelectedTextColor = ta.getResourceId(R.styleable.SelectTabView_unSelectedTextColor, Color.WHITE);
        leftSelectedBg = ta.getResourceId(R.styleable.SelectTabView_leftSelectedBg, -1);
        rightSelectedBg = ta.getResourceId(R.styleable.SelectTabView_rightSelectedBg, -1);
        rightSelected = ta.getBoolean(R.styleable.SelectTabView_rightSelected, false);
        ta.recycle();
    }

    private LinearLayout bg;
    private TextView tv1;
    private TextView tv2;

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_select_tab, this, true);
        bg = (LinearLayout) view.findViewById(R.id.ll_parent);
        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv2 = (TextView) view.findViewById(R.id.tv2);
        tv1.setTextSize(DeviceUtils.px2dp(textSize));
        tv2.setTextSize(DeviceUtils.px2dp(textSize));
        tv1.setText(leftText);
        tv2.setText(rightText);
        tv1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLeft();
            }
        });
        tv2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                selectRight();
            }
        });
        if (rightSelected)
            selectRight();
        else
            selectLeft();

    }

    private void selectRight() {
        bg.setBackground(getContext().getResources().getDrawable(rightSelectedBg));
        tv1.setTextColor(getContext().getResources().getColor(unSelectedTextColor));
        tv2.setTextColor(getContext().getResources().getColor(selectedTextColor));
        if (listener != null) listener.selectRight();
    }

    private void selectLeft() {
        bg.setBackground(getContext().getResources().getDrawable(leftSelectedBg));
        tv2.setTextColor(getContext().getResources().getColor(unSelectedTextColor));
        tv1.setTextColor(getContext().getResources().getColor(selectedTextColor));
        if (listener != null) listener.selectLeft();
    }

    public void setSelectedListener(SelectedListener listener) {
        this.listener = listener;
    }
}
