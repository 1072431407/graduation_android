package com.example.myapplication.adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] fragmentBeans;//fragment管理员
    private FragmentManager fm;//fragment数组

    public MainPagerAdapter(FragmentManager fm, Fragment[] fragmentList) {
        super(fm);
        this.fragmentBeans = fragmentList;
        this.fm = fm;
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentBeans[position];
    }

    @Override
    public int getCount() {
        return fragmentBeans.length;
    }
}
