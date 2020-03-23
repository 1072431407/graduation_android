package com.example.myapplication.server;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.drivinggrpc.driving.rpc.proto.user.UserRpcServerGrpc;
import com.drivinggrpc.driving.rpc.proto.user.getUserMessageRequest;
import com.drivinggrpc.driving.rpc.proto.user.getUserMessageResponse;
import com.drivinggrpc.driving.rpc.proto.user.loginRequest;
import com.drivinggrpc.driving.rpc.proto.user.loginResponse;
import com.drivinggrpc.driving.rpc.proto.user.registerRequest;
import com.drivinggrpc.driving.rpc.proto.user.registerResponse;
import com.drivinggrpc.driving.rpc.proto.user.setUserMessageRequest;
import com.drivinggrpc.driving.rpc.proto.user.setUserMessageResponse;
import com.drivinggrpc.driving.rpc.proto.user.upPasswordRequest;
import com.drivinggrpc.driving.rpc.proto.user.upPasswordResponse;
import com.example.myapplication.interfaces.myCallBack;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.ByteString;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.concurrent.Executors;

import io.grpc.ManagedChannel;

public class UserServer {
    private UserRpcServerGrpc.UserRpcServerFutureStub futureStub;
    public UserServer(ManagedChannel channel){
        futureStub = UserRpcServerGrpc.newFutureStub(channel);
    }
    /*
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
    public void setMessage(Bundle bundle,byte[] bytes,final myCallBack callBack){
        final setUserMessageRequest request = setUserMessageRequest.newBuilder()
                .setUserId(bundle.getInt("userid"))
                .setNickName(bundle.getString("nickname"))
                .setSignature(bundle.getString("signature"))
                .setMinute(0)
                .setDate("")
                .setName(bundle.getString("name"))
                .setPhone(bundle.getString("phone"))
                .setSex(bundle.getString("sex"))
                .setAge(bundle.getString("age"))
                .setJob(bundle.getString("job"))
                .setQqCode(bundle.getString("qq_code"))
                .setWeChat(bundle.getString("we_chat"))
                .setEMail(bundle.getString("e_mail"))
                .setHeadImage(ByteString.copyFrom(bytes))
                .setOldHeadImage(bundle.getString("old_head_image"))
                .setNewHeadImage(bundle.getString("new_head_image"))
                .build();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        callBack.onSuccess(msg.getData());
                        break;
                    case 2:
                        callBack.onFailure("网络异常！");
                        break;
                }
            }
        };
        ListenableFuture<setUserMessageResponse> future = futureStub.setUserMessage(request);
        Futures.addCallback(future, new FutureCallback<setUserMessageResponse>() {
            @Override
            public void onSuccess(@NullableDecl setUserMessageResponse result) {
                Message message = new Message();
                message.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("state",result.getState());
                message.setData(bundle);
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Throwable t) {
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        },Executors.newCachedThreadPool());
    }
    public void login(String user, String password, final myCallBack callBack){
        if ("".equals(user)||user == null||"".equals(password)||password == null)
            throw new NullPointerException("user and password no null！");
        final loginRequest request = loginRequest
                .newBuilder()
                .setUsername(user)
                .setPassword(password)
                .setPower(1)
                .build();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        callBack.onSuccess(msg.getData());
                        break;
                    case 2:
                        callBack.onFailure("网络异常！");
                        break;
                }
            }
        };
        ListenableFuture<loginResponse> future = futureStub.login(request);
        Futures.addCallback(future, new FutureCallback<loginResponse>() {
            @Override
            public void onSuccess(@NullableDecl loginResponse result) {
                Message message = new Message();
                message.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("state",result.getState());
                message.setData(bundle);
                handler.sendMessage(message);
            }
            @Override
            public void onFailure(Throwable t) {
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        }, Executors.newCachedThreadPool());
    }
    public void getUserMessageByUserName(String username,final myCallBack callBack){
        if ("".equals(username)||username == null)
            throw new NullPointerException("user and password no null！");
        final getUserMessageRequest request = getUserMessageRequest
                .newBuilder()
                .setUserName(username)
                .build();
        @SuppressLint("HandlerLeak")
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        callBack.onSuccess(msg.getData());
                        break;
                    case 2:
                        callBack.onFailure("用户信息获取失败");
                        break;

                }
            }
        };
        ListenableFuture<getUserMessageResponse> future = futureStub.getUserMessageByUserName(request);
        Futures.addCallback(future, new FutureCallback<getUserMessageResponse>() {
            @Override
            public void onSuccess(@NullableDecl getUserMessageResponse result) {
                Message message = new Message();
                message.what=1;
                Bundle bundle = new Bundle();
                try{
                    bundle.putInt("userid",result.getUserId());
                    bundle.putInt("minute",result.getMinute());
                    bundle.putString("headimage",result.getHeadImage());
                    bundle.putString("nickname",result.getNickName());
                    bundle.putString("signature",result.getSignature());
                    bundle.putString("date",result.getDate());

                    bundle.putString("name",result.getName());
                    bundle.putString("phone",result.getPhone());
                    bundle.putString("sex",result.getSex());
                    bundle.putString("age",result.getAge());
                    bundle.putString("job",result.getJob());
                    bundle.putString("qqCode",result.getQqCode());
                    bundle.putString("weChat",result.getWeChat());
                    bundle.putString("e_mail",result.getEMail());
                }catch (Exception e){
                    onFailure(null);
                }

                message.setData(bundle);
                handler.sendMessage(message);

            }

            @Override
            public void onFailure(Throwable t) {
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        },Executors.newCachedThreadPool());
    }

    public void register(String username,String password, final myCallBack callBack){
        if ("".equals(username)||username == null)
            throw new NullPointerException("username no null！");
        if ("".equals(password)||password == null)
            throw new NullPointerException("password no null！");
        final registerRequest request = registerRequest.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .setPower(1)
                .build();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        callBack.onSuccess(msg.getData());
                        break;
                    case 2:
                        callBack.onFailure("网络异常！");
                        break;
                }
            }
        };
        ListenableFuture<registerResponse> future = futureStub.register(request);
        Futures.addCallback(future, new FutureCallback<registerResponse>() {
            @Override
            public void onSuccess(@NullableDecl registerResponse result) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                message.what = 1;
                bundle.putString("state",result.getState());
                message.setData(bundle);
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Throwable t) {
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        },Executors.newCachedThreadPool());
    }

    public void forgetPassword(String username,String password,final myCallBack callBack){
        if ("".equals(username)||username == null)
            throw new NullPointerException("username no null！");
        if ("".equals(password)||password == null)
            throw new NullPointerException("password no null！");
        final upPasswordRequest request = upPasswordRequest.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        callBack.onSuccess(msg.getData());
                        break;
                    case 2:
                        callBack.onFailure("网络异常！");
                        break;
                }
            }
        };

        ListenableFuture<upPasswordResponse> future = futureStub.upPassword(request);
        Futures.addCallback(future, new FutureCallback<upPasswordResponse>() {
            @Override
            public void onSuccess(@NullableDecl upPasswordResponse result) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                message.what = 1;
                bundle.putString("state",result.getState());
                message.setData(bundle);
                handler.sendMessage(message);
            }
            @Override
            public void onFailure(Throwable t) {
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        },Executors.newCachedThreadPool());
    }
}
