package com.example.myapplication.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.myapplication.R;

public class MaxHeightListView extends ListView {

    private float mMaxHeight = 100;//默认100px
    public MaxHeightListView(Context context) {
        super(context);
    }

    public MaxHeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaxHeightListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setHeight() {
        ListAdapter listAdapter = this.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, this);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = this.getLayoutParams();

        params.height = totalHeight
                + (this.getDividerHeight() * (listAdapter.getCount() - 1));

        ((MarginLayoutParams) params).setMargins(10, 10, 10, 10); // 可删除

        this.setLayoutParams(params);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
