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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.drivinggrpc.driving.rpc.proto.module.StatisticsDataRequest;
import com.example.myapplication.R;
import com.example.myapplication.adapters.Minelist1Adapter;
import com.example.myapplication.interfaces.myCallBack;
import com.example.myapplication.interfaces.viewCallBack;
import com.example.myapplication.po.MineList1Bean;
import com.example.myapplication.server.GrpcChannel;
import com.example.myapplication.server.ModuleServer;
import com.example.myapplication.server.UserServer;
import com.example.myapplication.tools.ApplicationTools;
import com.example.myapplication.views.CircleImageTransformer;
import com.example.myapplication.views.MaxHeightListView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.xq.fasterdialog.bean.InputBean;
import com.xq.fasterdialog.dialog.EditDialog;
import com.xq.fasterdialog.dialog.LoadingDialog;
import com.xq.fasterdialog.dialog.base.BaseDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
个人资料
 */
public class MineListActivity_1 extends Activity {

    private static final String HEAD_ICON_NAME = "headImage.jpg";
    private final        int ALBUM             = 20;
    private final        int CUPREQUEST        = 50;
    private Uri mImageUri = null;


    private final int icons[] = {
            R.drawable.mine_list_1_0,
            R.drawable.mine_list_1_1,
            R.drawable.mine_list_1_2,
            R.drawable.mine_list_1_3,
            R.drawable.mine_list_1_4,
            R.drawable.mine_list_1_5,
            R.drawable.mine_list_1_6,
            R.drawable.mine_list_1_7,
    };
    private final String titles[] = {"姓名","电话","性别","年龄","职业","QQ","微信","邮箱"};

    //1表示科目一  -1表示科目四
    private static int isSwitch = 1;

    private Button save;
    private ImageView headImage;
    private TextView nickName;
    private TextView signature;
    private TextView totalDay;
    private TextView totalHour;
    private TextView totalMinute;
    private ImageButton switchButton;
    private TextView title;
    private TextView numQuestions;
    private TextView accuracy;//正确率:0%
    private TextView averScore;
    private TextView degree;//累计考试次数:0次
    private ModuleServer moduleServer;
    private MaxHeightListView listView;
    private Minelist1Adapter listAdapter;
    private List<MineList1Bean> listBeans;
    private SharedPreferences preferences;
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_list_1);
        init();
        initData();
        addListener();
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
                            .transform(new CircleImageTransformer())
                            .into(headImage);
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
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高，这里可以将宽高作为参数传递进来
        intent.putExtra("outputX", 162);
        intent.putExtra("outputY", 162);

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
    private void changeSwitch(int value){
        if (value == 1){//科目一
            title.setText("科目一");
            getStatisticsData(StatisticsDataRequest.REQUEST_TYPE.SUBJECT_1);
        }else{//科目四
            title.setText("科目四");
            getStatisticsData(StatisticsDataRequest.REQUEST_TYPE.SUBJECT_4);
        }
    }
    private void addListener() {
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSwitch(isSwitch);
                isSwitch *= -1;
            }
        });
        headImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocalPhoto();
            }
        });
        nickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                setEditDialog("昵称", nickName.getText().toString(), new viewCallBack() {
                    @Override
                    public void callBack(String value) {
                        nickName.setText(value);
                    }
                });
            }
        });
        signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                setEditDialog("昵称", signature.getText().toString(), new viewCallBack() {
                    @Override
                    public void callBack(String value) {
                        signature.setText(value);
                    }
                });
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.show();
                String nickname = nickName.getText().toString();
                String signAture = signature.getText().toString();
                List<MineList1Bean> listBeans = listAdapter.getListBeans();
                setSave(preferences.getInt("userid",-1),nickname,signAture, DrawableToBytes(headImage.getDrawable()),listBeans);

            }
        });
    }


    /**
    int32 user_id = 1;
    bytes head_image = 2;
    string nick_name = 3;
    string signature = 4;//签名
    int32 minute = 5;
    string date = 6;//报名日期
    string name = 7;
    string phone = 8;
    string sex = 9;
    string age = 10;
    string job = 11;
    string qq_code = 12;
    string we_chat = 13;
    string e_mail = 14;
     */
    private void setSave(final int userId, final String nickname, final String signature, byte[] bytes , final List<MineList1Bean> listBeans){
        Bundle bundle = new Bundle();
        bundle.putInt("userid",userId);
        bundle.putString("nickname",nickname);
        bundle.putString("signature",signature);
        bundle.putString("name",listBeans.get(0).getContent());
        bundle.putString("phone",listBeans.get(1).getContent());
        bundle.putString("sex",listBeans.get(2).getContent());
        bundle.putString("age",listBeans.get(3).getContent());
        bundle.putString("job",listBeans.get(4).getContent());
        bundle.putString("qq_code",listBeans.get(5).getContent());
        bundle.putString("we_chat",listBeans.get(6).getContent());
        bundle.putString("e_mail",listBeans.get(7).getContent());
        bundle.putString("old_head_image",preferences.getString("headimage",""));
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        final String newFileName = "head-"+userId+"-"+format.format(date);
        bundle.putString("new_head_image",newFileName);

        UserServer userServer = new UserServer(GrpcChannel.getChannel());
        userServer.setMessage(bundle,bytes,new myCallBack(){
            @Override
            public void onSuccess(Object object) {
                loadingDialog.dismiss();
                Bundle bundle = (Bundle)object;
                if ("保存成功".equals(bundle.getString("state"))){
                    ApplicationTools.setHeadImage(headImage.getDrawable());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("headimage","http://"+GrpcChannel.SERVER_IP+":8080/androidImage/"+newFileName+".jpg")
                            .putString("nickname",nickname)
                            .putString("signature",signature)
                            .putString("name",listBeans.get(0).getContent())
                            .putString("phone",listBeans.get(1).getContent())
                            .putString("sex",listBeans.get(2).getContent())
                            .putString("age",listBeans.get(3).getContent())
                            .putString("job",listBeans.get(4).getContent())
                            .putString("qqCode",listBeans.get(5).getContent())
                            .putString("weChat",listBeans.get(6).getContent())
                            .putString("e_mail",listBeans.get(7).getContent())
                            .commit();
                    Intent intent = getIntent();
                    setResult(1,intent);
                    finish();
                }
                Toast.makeText(MineListActivity_1.this,bundle.getString("state"),Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Object object) {
                Toast.makeText(MineListActivity_1.this,(String)object,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setEditDialog(String title, String hint, final viewCallBack callBack){
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
                String message = (String)array.get(0);
                callBack.callBack(message);
            }
        });
    }

    private void initData() {
        changeSwitch(1);

        loadingDialog .setLoadingText("正在保存...");
        String content[] = {
                preferences.getString("name",""),
                preferences.getString("phone",""),
                preferences.getString("sex",""),
                preferences.getString("age",""),
                preferences.getString("job",""),
                preferences.getString("qqCode",""),
                preferences.getString("weChat",""),
                preferences.getString("e_mail",""),
        };
        listBeans = new ArrayList<>();
        for (int i =0;i<content.length;i++){
            MineList1Bean bean = new MineList1Bean();
            bean.setIcon(icons[i]);
            bean.setTitle(titles[i]);
            bean.setContent(content[i]);
            listBeans.add(bean);
        }
        listAdapter = new Minelist1Adapter(this,listBeans);
        listView.setAdapter(listAdapter);
        listView.setHeight();
        if (ApplicationTools.getHeadImage() == null){
            Picasso.with(this)
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
    }
    private void getStatisticsData(StatisticsDataRequest.REQUEST_TYPE request_type){
        int userId = preferences.getInt("userid",-1);
        StatisticsDataRequest request = StatisticsDataRequest.newBuilder()
                .setUserId(userId)
                .setRequestType(request_type)
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
                Toast.makeText(MineListActivity_1.this,(String)object,Toast.LENGTH_SHORT).show();
                numQuestions.setText("0");
                accuracy.setText("正确率:0%");
                averScore.setText("0");
                degree.setText("累计考试次数:0次");
            }
        });
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
    private void init() {
        headImage = findViewById(R.id.headImage);
        nickName = findViewById(R.id.nickName);
        signature = findViewById(R.id.signature);
        totalDay = findViewById(R.id.totalDay);
        totalHour = findViewById(R.id.totalHour);
        totalMinute = findViewById(R.id.totalMinute);
        listView = findViewById(R.id.listView);
        save = findViewById(R.id.save);
        switchButton = findViewById(R.id.switchButton);
        title = findViewById(R.id.title);
        numQuestions = findViewById(R.id.numQuestions);
        accuracy = findViewById(R.id.accuracy);
        averScore = findViewById(R.id.averScore);
        degree = findViewById(R.id.degree);
        moduleServer = new ModuleServer();
        loadingDialog = new LoadingDialog(this);
        preferences = getSharedPreferences("drivingSchool", Context.MODE_PRIVATE);
    }
}
