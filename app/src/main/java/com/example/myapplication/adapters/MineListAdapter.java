package com.example.myapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.activitys.MineListActivity_0;
import com.example.myapplication.activitys.MineListActivity_1;
import com.example.myapplication.activitys.MineListActivity_2;
import com.example.myapplication.activitys.MineListActivity_3;
import com.example.myapplication.activitys.MineListActivity_4;
import com.example.myapplication.po.MineListBean;

import java.util.List;

public class MineListAdapter extends BaseAdapter {
    private List<MineListBean> listBeans;
    private Fragment context;
    private SharedPreferences preferences;
    public MineListAdapter(Fragment context, List<MineListBean> listBeans){
        this.context = context;
        this.listBeans = listBeans;
        preferences = context.getContext().getSharedPreferences("drivingSchool", Context.MODE_PRIVATE);
    }

    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public MineListBean getItem(int position) {
        return listBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHandler handler;
        if(convertView == null) {
            convertView = LayoutInflater.from(context.getContext())
                    .inflate(R.layout.mine_list_item, parent, false);
            handler = new ViewHandler();
            handler.icon = convertView.findViewById(R.id.icon);
            handler.title = convertView.findViewById(R.id.title);
            handler.imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(handler);
        } else {
            handler = (ViewHandler) convertView.getTag();
        }
        handler.icon.setImageResource(listBeans.get(position).getIcon());
        handler.title.setText(listBeans.get(position).getTitle());
        handler.imageView.setImageResource(R.drawable.right_arrows);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!preferences.getBoolean("loginState",false)){
                    Toast.makeText(context.getContext(),"请先登录！",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(context.getContext(), MineListActivity_0.class);
                        context.startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(context.getContext(), MineListActivity_1.class);
                        context.startActivityForResult(intent,1);
                        break;
                    case 2:
                        intent = new Intent(context.getContext(), MineListActivity_2.class);
                        context.startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(context.getContext(), MineListActivity_3.class);
                        context.startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(context.getContext(), MineListActivity_4.class);
                        context.startActivity(intent);
                        break;
                }
            }
        });
        return convertView;
    }
    class ViewHandler{
        ImageView icon;
        TextView title;
        ImageView imageView;
    }
}
