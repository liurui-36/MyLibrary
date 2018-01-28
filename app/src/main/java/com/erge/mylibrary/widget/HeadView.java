package com.erge.mylibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.erge.mylibrary.R;
import com.erge.mylibrary.utils.DeviceUtils;
import com.erge.mylibrary.utils.RxActivityTool;

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
 * Created by liurui on 2018/1/5.
 */
public class HeadView extends RelativeLayout {

    private ImageView ivLeft;
    private TextView tvLeft;
    private LinearLayout llLeft;

    private ImageView ivRight;
    private TextView tvRight;
    private LinearLayout llRight;

    private TextView tvTitle;
    private LinearLayout llBg;

    private View line;

    private int leftIconRes;
    private String leftText;
    private int leftTextColor;
    private float leftTextSize;

    private int rightIconRes;
    private String rightText;
    private int rightTextColor;
    private float rightTextSize;

    private String title;
    private int titleTextColor;
    private float titleTextSize;

    private int backgroundColor;
    private int backgroundImg;

    private boolean clickLeftFinish;
    private boolean showBottomLine;
    private int bottomLineColor;

    public HeadView(Context context) {
        this(context, null);
    }

    public HeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initAttrs(attrs);
        setView();
    }

    private void initView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.layout_head_view, this, true);
        ivLeft = (ImageView) v.findViewById(R.id.iv_left);
        ivRight = (ImageView) v.findViewById(R.id.iv_right);
        tvLeft = (TextView) v.findViewById(R.id.tv_left);
        tvRight = (TextView) v.findViewById(R.id.tv_right);
        tvTitle = (TextView) v.findViewById(R.id.tv_title);
        llBg = (LinearLayout) v.findViewById(R.id.ll_bg);
        llLeft = (LinearLayout) v.findViewById(R.id.ll_left);
        llRight = (LinearLayout) v.findViewById(R.id.ll_right);
        line = v.findViewById(R.id.view_line);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.HeadView);
        leftIconRes = ta.getResourceId(R.styleable.HeadView_leftIconRes, -1);
        rightIconRes = ta.getResourceId(R.styleable.HeadView_rightIconRes, -1);
        leftText = ta.getString(R.styleable.HeadView_leftText);
        rightText = ta.getString(R.styleable.HeadView_rightText);
        title = ta.getString(R.styleable.HeadView_title);
        backgroundColor = ta.getInteger(R.styleable.HeadView_backgroundColor, Color.WHITE);
        backgroundImg = ta.getInteger(R.styleable.HeadView_backgroundImg, -1);
        leftTextColor = ta.getColor(R.styleable.HeadView_leftTextColor, Color.BLACK);
        rightTextColor = ta.getColor(R.styleable.HeadView_rightTextColor, Color.BLACK);
        titleTextColor = ta.getColor(R.styleable.HeadView_titleTextColor, Color.BLACK);
        leftTextSize = ta.getDimension(R.styleable.HeadView_leftTextSize, 30f);
        rightTextSize = ta.getDimension(R.styleable.HeadView_rightTextSize, 30f);
        titleTextSize = ta.getDimension(R.styleable.HeadView_titleTextSize, 30f);
        clickLeftFinish = ta.getBoolean(R.styleable.HeadView_clickLeftFinish, false);
        showBottomLine = ta.getBoolean(R.styleable.HeadView_showBottomLine, false);
        bottomLineColor = ta.getColor(R.styleable.HeadView_bottomLineColor, Color.GRAY);
        ta.recycle();
    }

    private void setView() {
        if (leftIconRes != -1) ivLeft.setImageResource(leftIconRes);
        else ivLeft.setVisibility(GONE);
        if (rightIconRes != -1) ivRight.setImageResource(rightIconRes);
        else ivRight.setVisibility(GONE);
        tvLeft.setText(leftText);
        tvRight.setText(rightText);
        tvTitle.setText(title);
        llBg.setBackgroundColor(backgroundColor);
        if (backgroundImg != -1)
            llBg.setBackground(getContext().getResources().getDrawable(backgroundImg));
        tvLeft.setTextColor(leftTextColor);
        tvRight.setTextColor(rightTextColor);
        tvTitle.setTextColor(titleTextColor);
        tvLeft.setTextSize(DeviceUtils.px2dp(leftTextSize));
        tvRight.setTextSize(DeviceUtils.px2dp(rightTextSize));
        tvTitle.setTextSize(DeviceUtils.px2dp(titleTextSize));
        if (clickLeftFinish) {
            llLeft.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (RxActivityTool.currentActivity() != null)
                        RxActivityTool.currentActivity().finish();
                }
            });
        }
        line.setVisibility(showBottomLine ? VISIBLE : GONE);
        line.setBackgroundColor(bottomLineColor);
    }

    public void setLeftClick(OnClickListener leftClick) {
        llLeft.setOnClickListener(leftClick);
    }

    public void setRightClick(OnClickListener rightClick) {
        llRight.setOnClickListener(rightClick);
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
        tvLeft.setText(leftText);
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
        tvRight.setText(rightText);
    }

    public void setTitle(String title) {
        this.title = title;
        tvTitle.setText(title);
    }

    public void setBottomLineColor(int color) {
        this.backgroundColor = color;
        line.setBackgroundColor(color);
    }
}
