package com.example.myapplication.views.planfragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class SubjectView3 extends LinearLayout {
    public SubjectView3(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.subject_message_3,this);
    }

    public SubjectView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SubjectView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
