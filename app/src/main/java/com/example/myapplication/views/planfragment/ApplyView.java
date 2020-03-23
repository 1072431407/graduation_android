package com.example.myapplication.views.planfragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class ApplyView extends LinearLayout {
    public ApplyView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.driving_message,this);
    }

    public ApplyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ApplyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
