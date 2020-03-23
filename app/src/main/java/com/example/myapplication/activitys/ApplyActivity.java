package com.example.myapplication.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.drivinggrpc.driving.rpc.proto.module.ApplyRequest;
import com.example.myapplication.R;
import com.example.myapplication.interfaces.myCallBack;
import com.example.myapplication.interfaces.viewCallBack;
import com.example.myapplication.server.ModuleServer;
import com.example.myapplication.views.CallBackRadioButton;
import com.google.protobuf.ByteString;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.xq.fasterdialog.bean.InputBean;
import com.xq.fasterdialog.dialog.EditDialog;
import com.xq.fasterdialog.dialog.LoadingDialog;
import com.xq.fasterdialog.dialog.NormalDialog;
import com.xq.fasterdialog.dialog.base.BaseDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 报名
 */
public class ApplyActivity extends Activity {
    private static final String HEAD_ICON_NAME = "picture.jpg";
    private final        int ALBUM             = 20;
    private final        int CUPREQUEST        = 50;
    private Uri mImageUri = null;
    private String oldType;
    private String type;
    private CallBackRadioButton A1,A2,A3,B1,B2,C1,C2,C3,D,E,F,typeGroup0,typeGroup1,typeGroup2;
    private List<CallBackRadioButton> radioGroup;

    private ImageView picture;
    private Button changePicture;
    private TextView name;
    private TextView sex;
    private TextView age;
    private TextView address;
    private TextView phone;
    private TextView code;
    private Button save;

    private NormalDialog normalDialog;
    private LoadingDialog loadingDialog;

    private ModuleServer moduleServer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        init();
        initData();
        addListener();
    }

    private void initData() {
        normalDialog.setCustomView(NormalDialog.LAYOUT_METERAIL);
        normalDialog.setTitle("提示"); //设置标题
        normalDialog.setWidthPercent(0.8f);
        normalDialog.setContent("请确认信息无误后提交!!!");//设置内容
        normalDialog.setPositiveText("确定");//设置确认文字
        normalDialog .setNegativeText("取消");//设置取消文字
        normalDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setLoadingText("正在报名...");
    }

    private void addListener() {
        normalDialog.setPositiveListener(new BaseDialog.OnDialogClickListener(){
            @Override
            public void onClick(BaseDialog dialog) {
                normalDialog.dismiss();
                loadingDialog.show();
                submit();
            }
        });//设置确认监听
        normalDialog.setNegativeListener(new BaseDialog.OnDialogClickListener() {
            @Override
            public void onClick(BaseDialog dialog) {
                normalDialog.dismiss();
            }
        }); //设置取消监听
        changePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocalPhoto();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advSubmit();
            }
        });
        typeGroup0.setCallBack(new viewCallBack() {
            @Override
            public void callBack(String value) {
                setEditDialog("已有证件类型","C1");
            }
        });
        typeGroup2.setCallBack(new viewCallBack() {
            @Override
            public void callBack(String value) {
                setEditDialog("吊销证件类型","C1");
            }
        });
        typeGroup1.setCallBack(new viewCallBack() {
            @Override
            public void callBack(String value) {
                oldType = "无";
            }
        });
    }

    private void submit(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final SharedPreferences preferences = getSharedPreferences("drivingSchool", Context.MODE_PRIVATE);
        int userId = preferences.getInt("userid",0);
        final String dateString = format.format(date);
        String nameString = name.getText().toString();
        String sexString = sex.getText().toString();
        String ageString = age.getText().toString();
        String addressString = address.getText().toString();
        String phoneString = phone.getText().toString();
        String codeString = code.getText().toString();
        Drawable drawable = picture.getDrawable();
        ByteString pictureString = ByteString.copyFrom(DrawableToBytes(drawable));
        //oldtype
        //type
        ApplyRequest request = ApplyRequest.newBuilder()
                .setUserId(userId)
                .setPicture(pictureString)
                .setDate(dateString)
                .setName(nameString)
                .setSex(sexString)
                .setAge(ageString)
                .setAddress(addressString)
                .setPhone(phoneString)
                .setCode(codeString)
                .setOldType(oldType)
                .setType(type)
                .build();
        moduleServer.apply(request, new myCallBack() {
            @Override
            public void onSuccess(Object object) {
                loadingDialog.dismiss();
                Bundle bundle = (Bundle)object;
                String state = bundle.getString("state");
                toast(state);
                if ("报名成功".equals(state)){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("date",dateString).apply();
                    Intent intent = getIntent();
                    setResult(0,intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Object object) {
                loadingDialog.dismiss();
                toast((String)object);
            }
        });
    }
    /**
     * 欲提交状态 检查数据
     */
    private void advSubmit() {
        String nameString = name.getText().toString();
        if ("".equals(nameString)){
            toast("姓名不能为空!");
            return;
        }
        String sexString = sex.getText().toString();
        if ("".equals(sexString)){
            toast("性别不能为空!");
            return;
        }
        String ageString = age.getText().toString();
        if ("".equals(ageString)){
            toast("年龄不能为空!");
            return;
        }
        String addressString = address.getText().toString();
        if ("".equals(addressString)){
            toast("地址不能为空!");
            return;
        }
        String phoneString = phone.getText().toString();
        if ("".equals(phoneString)){
            toast("联系电话不能为空!");
            return;
        }
        String codeString = code.getText().toString();
        if ("".equals(codeString)){
            toast("证件号码不能为空!");
            return;
        }

        Drawable drawable = picture.getDrawable();
        Drawable.ConstantState drawableCs = this.getResources().getDrawable(R.drawable.head).getConstantState();
        if( drawable.getConstantState().equals(drawableCs) ){
            toast("请选择二寸照片!");
            return;
        }
        normalDialog.show();
    }
    private void setEditDialog(final String title, final String hint){
        final EditDialog editDialog = new EditDialog(this);
        InputBean inputBean = new InputBean();
        editDialog.setCustomView(R.layout.layout_editdialog_xq);
        editDialog.setStyle(R.style.Animate_Alpha);
        editDialog.setWidthPercent(0.8f);
        editDialog.setCanceledOnTouchOutside(false);
        editDialog.setPositiveText("确定");
        editDialog.setNegativeText("取消");
        inputBean.setHint(hint);
        editDialog.setTitle(title);
        editDialog.setInputBean(0,inputBean);
        editDialog.show();
        editDialog.setPositiveListener(new BaseDialog.OnDialogClickListener(){
            @Override
            public void onClick(BaseDialog dialog) {
                SparseArray<CharSequence> array = editDialog.getTextArray();
                oldType = (String)array.get(0);
                if ("".equals(oldType)){
                    toast("证件类型不能为空!");
                    setEditDialog(title,hint);
                }

            }
        });
    }
    private void toast(String content) {
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }

    private byte[] DrawableToBytes(Drawable drawable){
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        return outputStream.toByteArray();
    }
    private void init() {
        type = "";
        oldType = "";
        moduleServer = new ModuleServer();
        normalDialog = new NormalDialog(this);
        loadingDialog = new LoadingDialog(this);
        typeGroup0 = findViewById(R.id.typeGroup0);
        typeGroup1 = findViewById(R.id.typeGroup1);
        typeGroup2 = findViewById(R.id.typeGroup2);
        picture = findViewById(R.id.picture);
        changePicture = findViewById(R.id.changePicture);
        name = findViewById(R.id.name);
        sex = findViewById(R.id.sex);
        age = findViewById(R.id.age);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        code = findViewById(R.id.code);
        save = findViewById(R.id.save);
        A1 = findViewById(R.id.A1Radio);
        A2 = findViewById(R.id.A2Radio);
        A3 = findViewById(R.id.A3Radio);
        B1 = findViewById(R.id.B1Radio);
        B2 = findViewById(R.id.B2Radio);
        C1 = findViewById(R.id.C1Radio);
        C2 = findViewById(R.id.C2Radio);
        C3 = findViewById(R.id.C3Radio);
        D = findViewById(R.id.DRadio);
        E = findViewById(R.id.ERadio);
        F = findViewById(R.id.FRadio);
        radioGroup = new ArrayList<>();
        radioGroup.add(A1);radioGroup.add(A2);radioGroup.add(A3);
        radioGroup.add(B1); radioGroup.add(B2);
        radioGroup.add(C1);radioGroup.add(C2);radioGroup.add(C3);
        radioGroup.add(D);radioGroup.add(E);radioGroup.add(F);
        radioAddListener(radioGroup);

    }

    private void radioAddListener(List<CallBackRadioButton> list){
        for (int i=0;i<list.size();i++){
            final int index = i;
            list.get(i).setCallBack(new viewCallBack() {
                @Override
                public void callBack(String value) {
                    radioChangeTheme(index);
                }
            });
        }
    }
    private void radioChangeTheme(int index){
        for (int i=0;i<radioGroup.size();i++)
            radioGroup.get(i).setChecked(false);
        radioGroup.get(index).setChecked(true);
        type = radioGroup.get(index).getText().toString();
    }

    /**
     * 相册
     */
    private void setLocalPhoto() {
        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(albumIntent, ALBUM);
    }
    /**
     * 获取图片，根据两种不同的情况下的requestCode分别获取图片
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ALBUM:// 直接从相册获取
                try {
                    // 裁剪图片，相机返回的intent就是URI
                    startPhotoZoom(data.getData());
                } catch (NullPointerException e) {
                    e.printStackTrace();// 用户点击取消操作时的异常
                }
                break;
            case CUPREQUEST:// 取得裁剪后的图片
                if (data != null) {
                    Picasso.with(this)
                            .load(mImageUri)
                            .memoryPolicy(MemoryPolicy.NO_CACHE)
                            .networkPolicy(NetworkPolicy.NO_CACHE)
                            .into(picture);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 裁剪图片方法实现
     *
     * @param uri 要裁剪的图片的URI
     */
    public void startPhotoZoom(Uri uri) {
        mImageUri = Uri.fromFile(new File(getExternalCacheDir(), HEAD_ICON_NAME));
        Intent intent = new Intent("com.android.camera.action.CROP");
        // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "false");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 0.66f);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高，这里可以将宽高作为参数传递进来
        intent.putExtra("outputX", 105);
        intent.putExtra("outputY", 159);
        // 其实加上下面这两句就可以实现基本功能，
        //但是这样做我们会直接得到图片的数据，以bitmap的形式返回，在Intent中。而Intent传递数据大小有限制，1kb=1024字节，这样就对最后的图片的像素有限制。
        //intent.putExtra("return-data", true);
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);

        // 解决不能传图片，Intent传递数据大小有限制，1kb=1024字节
        // 方法：裁剪后的数据不以bitmap的形式返回，而是放到磁盘中，更方便上传和本地缓存
        // 设置裁剪后的数据不以bitmap的形式返回，剪切后图片的位置，图片是否压缩等
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);

        // 调用系统的图片剪切
        startActivityForResult(intent, CUPREQUEST);
    }
}
