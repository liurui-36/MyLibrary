package com.erge.mylibrary.widget.tabViewPager;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.erge.mylibrary.R;


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
 * Created by liurui on 2017/6/15.
 */
public class TabEntity {
    private View view;
    private boolean selected; // 是否被选中

    private ImageView icon; // 图标控件
    private int selectedIcon; // 选中显示的图标
    private int unSelectedIcon; // 未选中显示的图标

    private TextView tv; // 文字控件
    private String text; // 文字内容
    private int selectedTextColor; // 选中的文字颜色
    private int unSelectedTextColor; // 未选中的文字颜色
    private boolean setTextString = false; // 是否设置的文字
    private boolean setTextColor = false; // 是否设置了文字颜色

    private int position;
    private TabClickListener mTabClickListener;

    public TabEntity(Context context, int selectedIcon, int unSelectedIcon, int position) {
        this.view = View.inflate(context, R.layout.common_tab_item_layout, null);
        // 设置图标
        this.icon = (ImageView) view.findViewById(R.id.iv_tab_icon);
        this.tv = (TextView) view.findViewById(R.id.tv_tab_text);
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
        this.position = position;
        tv.setVisibility(View.GONE);
        // 默认未选中
        setSelectedPosition(0);
        view.setOnClickListener(mClickListener);
    }

    public View getView() {
        return view;
    }

    /**
     * 得到选中的状态
     *
     * @return
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * 设置是否选中
     */
    public void setSelectedPosition(int poi) {
        this.selected = poi == position;
        if (selected) {
            icon.setImageResource(selectedIcon);
            if (setTextString && setTextColor) {
                tv.setTextColor(selectedTextColor);
            }
        } else {
            icon.setImageResource(unSelectedIcon);
            if (setTextString && setTextColor) {
                tv.setTextColor(unSelectedTextColor);
            }
        }
    }

    /**
     * 设置Tab的文字
     *
     * @param text
     */
    public void setTextString(String text) {
        // 如果文本不为空，则设置，为空则隐藏控件
        if (TextUtils.isEmpty(text)) {
            tv.setVisibility(View.GONE);
        } else {
            this.text = text;
            tv.setVisibility(View.VISIBLE);
            tv.setText(text);
            setTextString = true;
        }
    }

    /**
     * 设置文字选中和未选中的颜色
     *
     * @param selectedTextColor
     * @param unSelectedTextColor
     */
    public void setTextColor(int selectedTextColor, int unSelectedTextColor) {
        this.selectedTextColor = selectedTextColor;
        this.unSelectedTextColor = unSelectedTextColor;
        setTextColor = true;
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mTabClickListener != null) {
                mTabClickListener.onTabClick(position);
            }
        }
    };

    public void setTabClickListener(TabClickListener tabClickListener) {
        this.mTabClickListener = tabClickListener;
    }
}
