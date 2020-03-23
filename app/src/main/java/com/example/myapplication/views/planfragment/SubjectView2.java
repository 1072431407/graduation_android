package com.example.myapplication.views.planfragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class SubjectView2 extends LinearLayout {
    public SubjectView2(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.subject_message_2,this);
    }

    public SubjectView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SubjectView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
