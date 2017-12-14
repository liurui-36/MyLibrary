package com.erge.mylibrary.widget.tabViewPager;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.erge.mylibrary.R;
import com.erge.mylibrary.adapter.FragmentAdapter;

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
 * Created by liurui on 2017/6/15.
 */
public class CommonTabPager extends RelativeLayout {

    private ViewPager viewPager;
    private LinearLayout tabLayout;
    private List<PagerEntity> pagerList;
    private FragmentManager fm;
    private List<TabEntity> tabItems = new ArrayList<>();

    public CommonTabPager(Context context) {
        super(context);
        init();
    }

    public CommonTabPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CommonTabPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CommonTabPager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.common_tab_pager_layout, this, true);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        tabLayout = (LinearLayout) view.findViewById(R.id.ll_tab);
    }

    public void setPagerAndTab(FragmentManager fm, List<PagerEntity> pagerList) {
            this.fm = fm;
            this.pagerList = pagerList;
            if (viewPager == null) init();
            setPager();
            setTab();
            setSelectedItem(0);
    }

    /**
     * 设置选中的页项
     *
     * @param position
     */
    public void setSelectedItem(int position) {
        viewPager.setCurrentItem(position);
        setSelectedTab(position);
    }

    /**
     * 设置选中的Tab
     *
     * @param position
     */
    private void setSelectedTab(int position) {
        for (int i = 0; i < tabItems.size(); i++) {
            tabItems.get(i).setSelectedPosition(position);
        }
    }

    /**
     * 设置导航栏
     */
    private void setTab() {
        for (int i = 0; i < pagerList.size(); i++) {
            PagerEntity pager = pagerList.get(i);
            TabEntity item = new TabEntity(getContext(), pager.getSelectedIcon(), pager.getUnSelectedIcon(), i);
            item.setTextString(pager.getText());
            item.setTabClickListener(tabClickListener);
            tabItems.add(item);
            tabLayout.addView(item.getView());
            // 如果不是最后一个，则添加一个竖直的分割线
            if (i < pagerList.size() - 1) {
                View line = new View(getContext());
                line.setBackgroundResource(R.color.common_pressed);
                ViewGroup.LayoutParams parms = new ViewGroup.LayoutParams(2, LayoutParams.MATCH_PARENT);
                line.setLayoutParams(parms);
                tabLayout.addView(line);
            }
        }
        setTabWidth();
    }

    private void setTabWidth() {
        ViewGroup.LayoutParams params = tabItems.get(0).getView().getLayoutParams();

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;

        params.width = w_screen / tabItems.size() - (tabItems.size() - 1);
        for (TabEntity tab : tabItems) {
            tab.getView().setLayoutParams(params);
        }
    }

    /**
     * 将fragment添加到ViewPager上
     */
    private void setPager() {
        List<Fragment> pagers = new ArrayList<>();
        for(PagerEntity entity : pagerList) {
            pagers.add(entity.getFragment());
        }
        viewPager.setAdapter(new FragmentAdapter(fm, pagers));
        viewPager.addOnPageChangeListener(mPageChangeListener);
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            setSelectedTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    /**
     * 设置文字颜色
     *
     * @param selectedTextColor
     * @param unSelectedTextColor
     */
    public void setTexts(int selectedTextColor, int unSelectedTextColor) {
        for (TabEntity item : tabItems) {
            item.setTextColor(selectedTextColor, unSelectedTextColor);
        }
    }


    /**
     * Tab点击监听
     */
    private TabClickListener tabClickListener = new TabClickListener() {
        @Override
        public void onTabClick(int position) {
            viewPager.setCurrentItem(position);
        }
    };

}
