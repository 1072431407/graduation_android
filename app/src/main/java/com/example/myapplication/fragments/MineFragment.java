package com.example.myapplication.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.adapters.MineListAdapter;
import com.example.myapplication.activitys.LoginActivity;
import com.example.myapplication.po.MineListBean;
import com.example.myapplication.tools.ApplicationTools;
import com.example.myapplication.views.CircleImageTransformer;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.xq.fasterdialog.dialog.NormalDialog;
import com.xq.fasterdialog.dialog.base.BaseDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MineFragment extends Fragment {
    private final int icons[] = { R.drawable.mine_list_0, R.drawable.mine_list_1, R.drawable.mine_list_2, R.drawable.mine_list_3, R.drawable.mine_list_4 };
    private final String titles[] = {"我的消息","个人资料","系统设置","用户反馈","关于我们"};
    private View view;

    private ImageView headImage;
    private TextView nickName;
    private TextView signature;
    private TextView totalDay;
    private TextView totalHour;
    private TextView totalMinute;
    private Button outLogin;
    private ListView listView;
    private List<MineListBean> listBeans;
    private MineListAdapter listAdapter;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private NormalDialog normalDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mine_fragment,container,false);
        init();
        initData();
        addListener();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 0){
            editor.putBoolean("loginState",true).apply();
            upDataView();
        }
        if (requestCode == 1 && resultCode == 1){
            if(ApplicationTools.getHeadImage()!=null)
                headImage.setImageDrawable(ApplicationTools.getHeadImage());
            nickName.setText(preferences.getString("nickname", ""));
            signature.setText(preferences.getString("signature", ""));
        }
    }

    private void upDataView() {
        if (preferences.getBoolean("loginState", false)) {
            if (ApplicationTools.getHeadImage() == null){
                Picasso.with(getActivity())
                        .load(preferences.getString("headimage", ""))
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .networkPolicy(NetworkPolicy.NO_CACHE)
                        .transform(new CircleImageTransformer())
                        .error(R.drawable.head_image)
                        .into(headImage);
            }else{
                headImage.setImageDrawable(ApplicationTools.getHeadImage());
            }
            nickName.setText(preferences.getString("nickname", ""));
            signature.setText(preferences.getString("signature", ""));
            totalDay.setText(subData(preferences.getString("data","2020-3-3")));
            totalHour.setText(preferences.getInt("minute",0)/60+"");
            totalMinute.setText(preferences.getInt("minute",0)%60+"");
            showLoginOut(true);
        }else{
            nickName.setText("");
            signature.setText("");
            totalDay.setText("0");
            totalHour.setText("0");
            totalMinute.setText("0");
            Picasso.with(getContext())
                    .load(R.drawable.head_image)
                    .transform(new CircleImageTransformer())
                    .into(headImage);
            showLoginOut(false);
        }
    }
    private String subData(String data){// 2018-9-2
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate;
        Date endDate = new Date();
        try{
            beginDate = format.parse(data);
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
//System.out.println("相隔的天数="+day);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return day+"";
    }

    private void addListener() {
        normalDialog.setPositiveListener(new BaseDialog.OnDialogClickListener(){
            @Override
            public void onClick(BaseDialog dialog) {
                editor.putBoolean("loginState",false)
                        .putBoolean("autoLogin",false)
                        .commit();//同步
                upDataView();
                normalDialog.dismiss();
            }
        });//设置确认监听
        normalDialog.setNegativeListener(new BaseDialog.OnDialogClickListener() {
            @Override
            public void onClick(BaseDialog dialog) {
                normalDialog.dismiss();
            }
        }); //设置取消监听
        headImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!preferences.getBoolean("loginState",false)){
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    startActivityForResult(intent,0);
                }
            }
        });
        outLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normalDialog.show();
            }
        });
    }

    private void initData() {
        normalDialog.setCustomView(NormalDialog.LAYOUT_METERAIL);
        normalDialog.setTitle("提示"); //设置标题
        normalDialog.setWidthPercent(0.8f);
        normalDialog.setContent("您确定退出登录吗？");//设置内容
        normalDialog.setPositiveText("确定");//设置确认文字
        normalDialog .setNegativeText("取消");//设置取消文字
        normalDialog.setCanceledOnTouchOutside(false);
//        showLoginOut(false);
        listBeans = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            MineListBean bean = new MineListBean();
            bean.setIcon(icons[i]);
            bean.setTitle(titles[i]);
            listBeans.add(bean);
        }
        listAdapter = new MineListAdapter(this, listBeans);
        listView.setAdapter(listAdapter);
        upDataView();
    }
    private void showLoginOut(boolean isShow){
        if (isShow) {
            outLogin.setVisibility(View.VISIBLE);
        } else {
            outLogin.setVisibility(View.INVISIBLE);
        }
    }

    private void init() {
        headImage = view.findViewById(R.id.headImage);
        nickName = view.findViewById(R.id.nickName);
        signature = view.findViewById(R.id.signature);
        totalDay = view.findViewById(R.id.totalDay);
        totalHour = view.findViewById(R.id.totalHour);
        totalMinute = view.findViewById(R.id.totalMinute);
        outLogin = view.findViewById(R.id.outLogin);
        listView = view.findViewById(R.id.listView);

        preferences = getContext().getSharedPreferences("drivingSchool", Context.MODE_PRIVATE);
        editor = preferences.edit();

        normalDialog = new NormalDialog(getContext());

    }
}
