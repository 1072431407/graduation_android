package com.example.myapplication.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.myapplication.R;
import com.example.myapplication.activitys.LoginActivity;
import com.example.myapplication.adapters.LoginPopListAdapter;

public class LoginPoUpWindow extends PopupWindow {
    private LoginActivity activity;
    private View view;
    private ListView listView;
    private LoginPopListAdapter listAdapter;
    public LoginPoUpWindow(LoginActivity activity){
        super(activity);
        this.activity = activity;
        view = LayoutInflater.from(activity).inflate(R.layout.login_pop, null);
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(0x00ff0000));//必备设置2
        init();
    }

    private void init() {
        listView = view.findViewById(R.id.listView);
        listAdapter = new LoginPopListAdapter(activity);
        listView.setAdapter(listAdapter);
    }

}
