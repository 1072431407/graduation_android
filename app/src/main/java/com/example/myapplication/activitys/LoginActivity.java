package com.example.myapplication.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.myCallBack;
import com.example.myapplication.server.GrpcChannel;
import com.example.myapplication.server.UserServer;
import com.example.myapplication.tools.ApplicationTools;
import com.example.myapplication.views.CircleImageTransformer;
import com.example.myapplication.views.LoginPoUpWindow;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.xq.fasterdialog.dialog.LoadingDialog;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends Activity {
    private ImageView headImage;
    private EditText userName;
    private EditText password;
    private ImageView spread;
    private CheckBox savePassword;
    private CheckBox autoLogin;
    private ImageButton login;
    private TextView forgetPassword;
    private TextView toRegister;

    private LoginPoUpWindow popupWindow;
    private int isSpread;

    private UserServer userServer;
    private SharedPreferences preferences;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor editor;
    private SharedPreferences.Editor loginEditor;

    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        initData();
        addListener();
    }

    private void initData() {
        loadingDialog.setLoadingText("登录中...");

        if ("".equals(preferences.getString("headimage",""))){
            Picasso.with(this)
                    .load(R.drawable.head_image)
                    .transform(new CircleImageTransformer())
                    .into(headImage);
        }else{
            if (ApplicationTools.getHeadImage() == null){
                Picasso.with(this)
                        .load(preferences.getString("headimage", ""))
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .networkPolicy(NetworkPolicy.NO_CACHE)
                        .transform(new CircleImageTransformer())
                        .error(R.drawable.head_image)
                        .into(headImage);
            } else {
                headImage.setImageDrawable(ApplicationTools.getHeadImage());
            }
        }
        userName.setText(preferences.getString("username",""));
        password.setText(preferences.getString("password",""));
        savePassword.setChecked(preferences.getBoolean("savepassword",false));
        autoLogin.setChecked(preferences.getBoolean("autologin",false));
    }

    private void addListener() {
        spread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheme();
//                int screenWidth  = getWindowManager().getDefaultDisplay().getWidth();        // 屏幕宽（像素，如：480px）
//                int screenHeight = getWindowManager().getDefaultDisplay().getHeight();        // 屏幕高（像素，如：800p）
//                spread.getLocationInWindow(location); //获取在当前窗口内的绝对坐标，含toolBar
//        popupWindow.showAsDropDown(spread,0,0,Gravity.RIGHT);
                int[] location = new  int[2] ;
                spread.getLocationOnScreen(location); //获取在整个屏幕内的绝对坐标，含00statusBar
                popupWindow.showAtLocation(spread,Gravity.TOP|Gravity.START,location[0]-460,location[1]+34);
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTheme();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin();
            }
        });
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgetPassActivity.class);
                startActivity(intent);
            }
        });
    }
/*
登陆方法
 */
    private void toLogin() {
        loadingDialog.show();
        final String userString = userName.getText().toString();
        final String passwordString = password.getText().toString();
        if ("".equals(userString)) {
            Toast.makeText(LoginActivity.this,"请输入账号",Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(passwordString)) {
            Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        /*
        发送rpc请求，执行回调方法

         */
        userServer.login(userString, passwordString, new myCallBack() {
            @Override
            public void onSuccess(Object object) {
                Bundle bundle = (Bundle)object;
                Toast.makeText(LoginActivity.this,bundle.getString("state"),Toast.LENGTH_SHORT).show();
                if ("用户不存在".equals(bundle.getString("state"))) return;
                if ("密码错误".equals(bundle.getString("state"))) return;
                if ("你没有权限登录该账号".equals(bundle.getString("state"))) return;
                /*
                登陆成功
                保存用户的登录信息
                 */
                if (autoLogin.isChecked()){
                    editor.putString("username",userString)
                            .putString("password",passwordString)
                            .putBoolean("autologin",true)
                            .putBoolean("savepassword",true)
                            .apply();
                } else {
                    if (savePassword.isChecked()){
                        editor.putString("username",userString)
                                .putString("password",passwordString)
                                .putBoolean("autologin",false)
                                .putBoolean("savepassword",true)
                                .apply();
                    }else{
                        editor.putString("username",userString)
                                .putString("password","")
                                .putBoolean("autologin",false)
                                .putBoolean("savepassword",false)
                                .apply();
                    }
                }
                /*
                获取用户信息。存到xml文件里
                */
                userServer.getUserMessageByUserName(userString, new myCallBack() {
                    @Override
                    public void onSuccess(Object object) {
                        Bundle bundle = (Bundle) object;
                        editor.putInt("userid",bundle.getInt("userid"))
                                .putString("headimage",bundle.getString("headimage"))
                                .putString("nickname",bundle.getString("nickname"))
                                .putString("signature",bundle.getString("signature"))
                                .putString("date",bundle.getString("date"))
                                .putInt("minute",bundle.getInt("minute"))
                                .putString("name",bundle.getString("name"))
                                .putString("phone",bundle.getString("phone"))
                                .putString("sex",bundle.getString("sex"))
                                .putString("age",bundle.getString("age"))
                                .putString("job",bundle.getString("job"))
                                .putString("qqCode",bundle.getString("qqCode"))
                                .putString("weChat",bundle.getString("weChat"))
                                .putString("e_mail",bundle.getString("e_mail"))
                                .commit();//同步

                        int sum = loginPreferences.getInt("sum",0);
                        int newSum = sum;
                        List<ThisBean> listBeans = new ArrayList<>();
                        for(int i=0; i<sum;i++){
                            ThisBean bean = new ThisBean();
                            bean.username = loginPreferences.getString("username"+i,"");
                            bean.password = loginPreferences.getString("password"+i,"");
                            bean.headimage = loginPreferences.getString("headimage"+i,"");
                            bean.isBoolean = loginPreferences.getBoolean("isBoolean"+i,false);
                            if (bean.username.equals(userString)){
                                newSum--;
                                continue;
                            }else
                                listBeans.add(bean);
                        }
                        newSum++;
                        ThisBean bean = new ThisBean();
                        if (savePassword.isChecked())
                            bean.password = passwordString;
                        else
                            bean.password = "";
                        bean.headimage = bundle.getString("headimage");
                        bean.isBoolean = savePassword.isChecked();
                        bean.username = userString;
                        listBeans.add(0,bean);
                        if (listBeans.size()>10)
                            listBeans.remove(10);
                        loginEditor.clear();
                        loginEditor.putInt("sum",newSum).apply();
                        for (int i = 0;i < newSum; i++){
                            ThisBean b = listBeans.get(i);
                            loginEditor.putBoolean("isBoolean"+i,b.isBoolean)
                                    .putString("username"+i,b.username)
                                    .putString("password"+i,b.password)
                                    .putString("headimage"+i,b.headimage)
                                    .apply();
                        }
                        loadingDialog.dismiss();
                        close();
                    }

                    @Override
                    public void onFailure(Object object) {
                        Toast.makeText(LoginActivity.this,(String)object,Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Object object) {
                Toast.makeText(LoginActivity.this,(String)object,Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });

    }

    private void close(){
        Intent intent = getIntent();
        setResult(0,intent);
        finish();
    }
    private void setTheme(){
        isSpread *= -1;
        if (isSpread == 1){
            spread.setImageResource(R.drawable.arr_up);
        } else {
            spread.setImageResource(R.drawable.arr_down);
        }
    }

    private void init() {
        isSpread = -1;
        headImage = findViewById(R.id.headImage);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        spread = findViewById(R.id.spread);
        savePassword = findViewById(R.id.savePassword);
        autoLogin = findViewById(R.id.autoLogin);
        login = findViewById(R.id.login);
        forgetPassword = findViewById(R.id.forgetPassword);
        toRegister = findViewById(R.id.toRegister);

        spread.setImageResource(R.drawable.arr_down);
        preferences = getSharedPreferences("drivingSchool", Context.MODE_PRIVATE);
        loginPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);

        popupWindow = new LoginPoUpWindow(LoginActivity.this);

        editor = preferences.edit();
        loginEditor = loginPreferences.edit();
        userServer = new UserServer(GrpcChannel.getChannel());

        loadingDialog = new LoadingDialog(this);

    }
    public void setUserName(String value){
        if(value != null)
            userName.setText(value);
    }

    public void setPassword(String value) {
        if(value != null)
            password.setText(value);
    }
    public void setSavePassword(boolean value) {
        savePassword.setChecked(value);
    }
    public void closePopupWindow() {
        if(popupWindow != null && popupWindow.isShowing()){
            popupWindow.dismiss();
            popupWindow = null;
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.alpha = 1f;
            getWindow().setAttributes(layoutParams);
            popupWindow = new LoginPoUpWindow(LoginActivity.this);
        }
    }

    public void setHeadImage(String headimage) {
        Picasso.with(this)
                .load(headimage)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .transform(new CircleImageTransformer())
                .error(R.drawable.head_image)
                .into(headImage);
    }


    class ThisBean {
        String username = "";
        String password = "";
        String headimage = "";
        boolean isBoolean = false;
    }

}
