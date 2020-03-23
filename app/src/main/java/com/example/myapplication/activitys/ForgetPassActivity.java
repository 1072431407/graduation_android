package com.example.myapplication.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.myCallBack;
import com.example.myapplication.server.GrpcChannel;
import com.example.myapplication.server.UserServer;
import com.xq.fasterdialog.dialog.LoadingDialog;
import com.xq.fasterdialog.dialog.NormalDialog;

public class ForgetPassActivity extends Activity {
    private EditText userName;
    private EditText password;
    private EditText password1;
    private EditText authCode;
    private TextView getAuthCode;
    private ImageButton forgetPassword;
    private UserServer userServer;
    private LoadingDialog loadingDialog;
    private NormalDialog normalDialog;

    private String SystemAuthCode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);
        init();
        initData();
        addListener();
    }

    private void initData() {
        loadingDialog.setLoadingText("加载中...");
        normalDialog.setTitle("验证码");
        normalDialog.setPositiveText("确定");
        normalDialog.setWidthPercent(0.7f);
        normalDialog.setCanceledOnTouchOutside(true);
    }

    private String getSystemAuthCode(){
        int ren = (int)(Math.random()*(9999-1000)+1000);
        return ren+"";
    }
    private void addListener() {
        getAuthCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemAuthCode = getSystemAuthCode();
                normalDialog.setContent(SystemAuthCode);
                normalDialog.show();
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernameStr = userName.getText().toString();
                final String passwordStr = password.getText().toString();
                final String password1Str = password1.getText().toString();
                final String authCodeStr = authCode.getText().toString();

                if ("".equals(usernameStr)){
                    toast("请输入电话号!");
                    return;
                }
                if ("".equals(passwordStr)){
                    toast("请输入新密码!");
                    return;
                }
                if (passwordStr.length()>10||passwordStr.length()<6){
                    toast("密码为6-10位!");
                    return;
                }
                if (!passwordStr.equals(password1Str)){
                    toast("两次密码不一致!");
                    return;
                }
                if ("".equals(authCodeStr)){
                    toast("请输入验证码!");
                    return;
                }
                //比较验证码
                if (!authCodeStr.equals(SystemAuthCode)){
                    toast("验证码错误!");
                    return;
                }

                loadingDialog.show();
                userServer.forgetPassword(usernameStr, password1Str, new myCallBack() {
                    @Override
                    public void onSuccess(Object object) {
                        loadingDialog.dismiss();
                        Bundle bundle = (Bundle)object;
                        String state = bundle.getString("state");
                        if ("succeed".equals(state)){
                            toast("密码找回成功！");
                            finish();
                        }else{
                            toast(state+"!");
                        }
                    }

                    @Override
                    public void onFailure(Object object) {
                        loadingDialog.dismiss();
                        toast((String)object);
                    }
                });

            }
        });
    }

    private void toast(String value){
        Toast.makeText(ForgetPassActivity.this,value,Toast.LENGTH_SHORT).show();
    }
    private void init() {
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        password1 = findViewById(R.id.password1);
        authCode = findViewById(R.id.authCode);
        getAuthCode = findViewById(R.id.getAuthCode);
        forgetPassword = findViewById(R.id.forgetPassword);
        userServer = new UserServer(GrpcChannel.getChannel());
        loadingDialog = new LoadingDialog(this);
        normalDialog = new NormalDialog(this);
    }
}
