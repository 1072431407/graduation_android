package com.example.myapplication.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.example.myapplication.interfaces.viewCallBack;

@SuppressLint("AppCompatCustomView")
public class CallBackRadioButton extends RadioButton {
    private viewCallBack callBack;
    public CallBackRadioButton(Context context) {
        super(context);
    }

    public CallBackRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CallBackRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCallBack(viewCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void toggle() {
        super.toggle();
        callBack.callBack("");
    }
}
