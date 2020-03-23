package com.example.myapplication.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.po.TabBean;

import java.util.List;
import java.util.zip.Inflater;

public class MainTabAdapter extends BaseAdapter {
    private List<TabBean> listBeans;
    private MainActivity context;
    public MainTabAdapter(List<TabBean> listBeans, MainActivity context){
        this.context = context;
        this.listBeans = listBeans;
    }

    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public TabBean getItem(int position) {
        return listBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHandler handler;
        if (convertView == null){
//            convertView = View.inflate(context,R.layout.main_tab_item,parent);
            convertView = LayoutInflater.from(context).inflate(R.layout.main_tab_item, parent, false);
            handler = new ViewHandler();
            handler.icon = convertView.findViewById(R.id.icon);
            handler.title = convertView.findViewById(R.id.title);
            convertView.setTag(handler);
        } else {
            handler = (ViewHandler) convertView.getTag();
        }
        handler.icon.setImageResource(listBeans.get(position).getImage());
        handler.title.setText(listBeans.get(position).getTitle());
        if (listBeans.get(position).getTextColor())
            handler.title.setTextColor(Color.argb(255,0x12,0x96,0xdb));//up
        else
            handler.title.setTextColor(Color.argb(255,191,191,191));//down
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.ChangePager(position);
            }
        });
        return convertView;
    }
    class ViewHandler{
        ImageView icon;
        TextView title;
    }
}
