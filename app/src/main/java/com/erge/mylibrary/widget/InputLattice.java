package com.erge.mylibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.erge.mylibrary.R;

import java.util.ArrayList;
import java.util.List;

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
 * Created by liurui on 2017/12/13.
 */
public class InputLattice extends RelativeLayout {
    private LinearLayout llTv;
    private EditText etInput;
    private int latticeNumber; //格子数
    private int textSize; // 文字大小
    private int textColor; // 文字颜色
    private InputMethodManager imm;

    private List<TextView> tvList;

    public InputLattice(Context context) {
        this(context, null);
    }

    public InputLattice(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputLattice(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(attrs);
        init();
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.InputLattice);
        latticeNumber = ta.getInteger(R.styleable.InputLattice_latticeNumber, 4);
        textSize = ta.getInteger(R.styleable.InputLattice_textSize, 16);
        textColor = ta.getColor(R.styleable.InputLattice_textColor, Color.BLACK);
    }

    private void init() {
        imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        View v = LayoutInflater.from(getContext()).inflate(R.layout.layout_input_lattice, this, true);
        llTv = (LinearLayout) v.findViewById(R.id.ll_tv);
        addTextView();
        etInput = (EditText) findViewById(R.id.et_input);
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if (input != null) {
                    char[] cs = input.toCharArray();
                    if (cs.length <= latticeNumber) {
                        setText(cs);
                        if (cs.length == latticeNumber && imm != null) {
                            imm.hideSoftInputFromWindow(etInput.getWindowToken(), 0);
                        }
                    } else {
                        etInput.setText(input.substring(0, latticeNumber));
                        if (imm != null) {
                            imm.hideSoftInputFromWindow(etInput.getWindowToken(), 0);
                        }
                    }
                }
            }
        });
    }

    private void setText(char[] cs) {
        for (int i = 0; i < latticeNumber; i++) {
            if (i < cs.length) {
                tvList.get(i).setText("" + cs[i]);
            } else {
                tvList.get(i).setText("");
            }
        }
    }

    private void addTextView() {
        tvList = new ArrayList<>();
        for (int i = 0; i < latticeNumber; i++) {
            TextView tv = new TextView(getContext());
            ViewGroup.LayoutParams lp = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
            tv.setLayoutParams(lp);
            tv.setBackground(getResources().getDrawable(R.drawable.shape_kuang));
            tv.setTextColor(textColor);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
            tv.setGravity(Gravity.CENTER);
            llTv.addView(tv);
            tvList.add(tv);
        }
    }

    public String getText() {
        return etInput.getText().toString().trim();
    }

    public void setText(String s) {
        if (s != null) {
            if (s.length() > latticeNumber) {
                s = s.substring(0, latticeNumber);
            }
            etInput.setText(s);
            setText(s.toCharArray());
        }
    }

}
