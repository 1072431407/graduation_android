package com.example.myapplication.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.activitys.ApplyActivity;
import com.example.myapplication.interfaces.myCallBack;
import com.example.myapplication.server.ModuleServer;
import com.example.myapplication.views.planfragment.ApplyView;
import com.example.myapplication.views.planfragment.SubjectView1;
import com.example.myapplication.views.planfragment.SubjectView2;
import com.example.myapplication.views.planfragment.SubjectView3;
import com.example.myapplication.views.planfragment.SubjectView4;
import com.xq.fasterdialog.dialog.LoadingDialog;

/*
    进度
 */
public class PlanFragment extends Fragment {
    private final int REQUEST_CODE = 2;
    private boolean isPrepared;
    private TextView state;
    private View view;
    private LinearLayout contentView;
    private LinearLayout linearLayout;
    private ModuleServer moduleServer;
    private SharedPreferences preferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.plan_fragment,container,false);
        isPrepared = true;
        init();
        initData();
        addListener();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isPrepared && isVisibleToUser) {
            initData();
        }
    }

    private void initData() {
        if (preferences.getBoolean("loginState", false)){
            moduleServer.getApplyState(preferences.getInt("userid", -1), new myCallBack() {
                @Override
                public void onSuccess(Object object) {
                    Bundle bundle = (Bundle)object;
                    String stateString = bundle.getString("state");
                    state.setText(stateString);
                    if ("未报名".equals(stateString)){
                        contentView = new ApplyView(getContext());
                        linearLayout.removeAllViews();
                        linearLayout.addView(contentView);
                        return;
                    }
                    if ("科目一".equals(stateString)){
                        contentView = new SubjectView1(getContext());
                        linearLayout.removeAllViews();
                        linearLayout.addView(contentView);
                        return;
                    }
                    if ("科目二".equals(stateString)){
                        contentView = new SubjectView2(getContext());
                        linearLayout.removeAllViews();
                        linearLayout.addView(contentView);
                        return;
                    }
                    if ("科目三".equals(stateString)){
                        contentView = new SubjectView3(getContext());
                        linearLayout.removeAllViews();
                        linearLayout.addView(contentView);
                        return;
                    }
                    if ("科目四".equals(stateString)){
                        contentView = new SubjectView4(getContext());
                        linearLayout.removeAllViews();
                        linearLayout.addView(contentView);
                        return;
                    }
                    onFailure("网络异常!");
                }

                @Override
                public void onFailure(Object object) {
                    Toast.makeText(getContext(),(String)object,Toast.LENGTH_SHORT).show();
                    state.setText("未报名");
                    contentView = new ApplyView(getContext());
                    linearLayout.removeAllViews();
                    linearLayout.addView(contentView);
                }
            });
        }else{
            Toast.makeText(getContext(),"您还没有登录!",Toast.LENGTH_SHORT).show();
            state.setText("未报名");
            contentView = new ApplyView(getContext());
            linearLayout.removeAllViews();
            linearLayout.addView(contentView);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == 0){
            state.setText("科目一");
        }
    }

    /**
     *:
     */
    private void addListener() {
        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preferences.getBoolean("loginState", false)) {
                    if ("未报名".equals(state.getText().toString())){
                        Intent intent = new Intent(getContext(), ApplyActivity.class);
                        startActivityForResult(intent,REQUEST_CODE);
                    }
                }else
                    Toast.makeText(getContext(),"请先登录!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        state = view.findViewById(R.id.state);
        linearLayout = view.findViewById(R.id.linearLayout);
        moduleServer = new ModuleServer();
        preferences = getActivity().getSharedPreferences("drivingSchool", Context.MODE_PRIVATE);
    }
}
