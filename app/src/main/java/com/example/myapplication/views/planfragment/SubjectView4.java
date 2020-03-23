package com.example.myapplication.views.planfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.drivinggrpc.driving.rpc.proto.module.StatisticsDataRequest;
import com.example.myapplication.R;
import com.example.myapplication.interfaces.myCallBack;
import com.example.myapplication.server.ModuleServer;

public class SubjectView4 extends LinearLayout {
    private TextView numQuestions;
    private TextView accuracy;//正确率:0%
    private TextView averScore;
    private TextView degree;//累计考试次数:0次
    private ModuleServer moduleServer;
    private Context context;
    public SubjectView4(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.subject_message_4,this);
        this.context = context;
        init();
        initData();
    }
    private void init() {
        numQuestions = findViewById(R.id.numQuestions);
        accuracy = findViewById(R.id.accuracy);
        averScore = findViewById(R.id.averScore);
        degree = findViewById(R.id.degree);
        moduleServer = new ModuleServer();
    }
    private void initData() {
        SharedPreferences preferences = context.getSharedPreferences("drivingSchool",Context.MODE_PRIVATE);
        int userId = preferences.getInt("userid",-1);
        StatisticsDataRequest request = StatisticsDataRequest.newBuilder()
                .setUserId(userId)
                .setRequestType(StatisticsDataRequest.REQUEST_TYPE.SUBJECT_4)
                .build();
        moduleServer.getStatisticsData(request, new myCallBack() {
            @Override
            public void onSuccess(Object object) {
                Bundle bundle = (Bundle)object;
                numQuestions.setText(bundle.getString("numQuestions"));
                accuracy.setText(bundle.getString("accuracy"));
                averScore.setText(bundle.getString("averScore"));
                degree.setText(bundle.getString("degree"));
            }

            @Override
            public void onFailure(Object object) {
                Toast.makeText(context,(String)object,Toast.LENGTH_SHORT).show();
                numQuestions.setText("0");
                accuracy.setText("正确率:0%");
                averScore.setText("0");
                degree.setText("累计考试次数:0次");
            }
        });
    }
    public SubjectView4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SubjectView4(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
