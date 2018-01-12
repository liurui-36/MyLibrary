package com.erge.mylibrary.widget;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.erge.mylibrary.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
 * Created by liurui on 2017/12/28.
 */
public class ScrollTextView extends LinearLayout {

    private ViewFlipper viewFlipper;
    private int mCurrPos = -1;
    private Handler handler;
    private Timer timer;
    private View view;
    private long time = 5000L;
    private List<String> text;
    private int[] tvId;

    public ScrollTextView(Context context) {
        super(context);
        init();
    }

    public ScrollTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_scroll_text, this, true);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.view_flipper);
    }

    public void start() {
        try {
            if (handler == null) {
                handler = new Handler();
            }
            if (timer == null) {
                timer = new Timer();
            }
            setView();
            if (text.size() > tvId.length) {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                moveNext();
                            }
                        });
                    }
                }, time, time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            handler = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void moveNext() {
        setView();
        viewFlipper.setInAnimation(getContext(), R.anim.in_bottomtop);
        viewFlipper.setOutAnimation(getContext(), R.anim.out_topbottom);
        viewFlipper.showNext();
    }

    public void setContent(int layoutView, List<String> text, int[] tvId) {
        this.view = View.inflate(getContext(), layoutView, null);
        int i = text.size() % tvId.length;
        if (i != 0) {
            for (int j = 0; j < tvId.length - i; j++) {
                text.add("");
            }
        }
        this.text = text;
        this.tvId = tvId;
    }

    private void setView() {
        int next = mCurrPos + 1;
        if (next >= text.size() / tvId.length) {
            next = 0;
        }
        Log.i("ScrollTextView", "next = " + next);
        for (int i = 0; i < tvId.length; i++) {
            TextView notice_tv = (TextView) view.findViewById(tvId[i]);
            String s;
            if (next == 0) {
                s = text.get(i);
            } else {
                s = text.get(next * tvId.length + i);
            }
            if (TextUtils.isEmpty(s)) {
                notice_tv.setVisibility(INVISIBLE);
            } else {
                notice_tv.setText(s);
            }
        }
//        if (viewFlipper.getChildCount() > 1) {
//            viewFlipper.removeViewAt(0);
//        }
        viewFlipper.removeAllViews();
        viewFlipper.addView(view, viewFlipper.getChildCount());
        mCurrPos = next;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
