package com.example.myapplication;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.GridView;

import com.example.myapplication.adapters.MainPagerAdapter;
import com.example.myapplication.adapters.MainTabAdapter;
import com.example.myapplication.fragments.HomeFragment;
import com.example.myapplication.fragments.MineFragment;
import com.example.myapplication.fragments.PlanFragment;
import com.example.myapplication.fragments.StudyFragment;
import com.example.myapplication.po.TabBean;
import com.example.myapplication.views.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private final Fragment[] fragments = { new HomeFragment(), new StudyFragment(), new PlanFragment(), new MineFragment()};
    private final int[] iconDown = { R.drawable.home_down, R.drawable.study_down, R.drawable.plan_down, R.drawable.mine_down };
    private final int[] iconUp = { R.drawable.home_up, R.drawable.study_up, R.drawable.plan_up, R.drawable.mine_up };
    private final String[] title = {"主页", "学习", "进度", "个人中心"};

    private GridView gridView;
    private CustomViewPager viewPager;
    private List<TabBean> tabBeans;
    private MainTabAdapter gridViewAdapter;
    private MainPagerAdapter viewPagerAdapter;

    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addListener();
    }

    private void addListener() {
        viewPager.setAdapter(viewPagerAdapter);
        gridView.setAdapter(gridViewAdapter);
    }

    public void ChangePager(int item){
        if (item >= tabBeans.size()) throw new IndexOutOfBoundsException("item >= tabBeans.size()");
        for (int i=0;i<tabBeans.size();i++){
            tabBeans.get(i).setImage(iconUp[i]);
            tabBeans.get(i).setTextColor(false);
        }
        tabBeans.get(item).setTextColor(true);
        tabBeans.get(item).setImage(iconDown[item]);
        viewPager.setCurrentItem(item);
        gridViewAdapter.notifyDataSetChanged();
    }
    private void init() {
        preferences = getSharedPreferences("drivingSchool", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("loginState", false).commit();
                /*
        自动登录
        ||preferences.getBoolean("loginState",false)
         */
        if (preferences.getBoolean("autologin", false)) {
            editor.putBoolean("loginState", true).commit();
        }

        gridView = findViewById(R.id.gridView);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setScanScroll(false);
        viewPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(),fragments);
        tabBeans = new ArrayList<>();
        for (int i = 0;i < iconUp.length;i++){
            TabBean bean = new TabBean();
            bean.setImage(iconUp[i]);
            bean.setTitle(title[i]);
            bean.setTextColor(false);
            tabBeans.add(bean);
        }
        tabBeans.get(0).setTextColor(true);
        tabBeans.get(0).setImage(iconDown[0]);
        gridViewAdapter = new MainTabAdapter(tabBeans,this);

    }

}
