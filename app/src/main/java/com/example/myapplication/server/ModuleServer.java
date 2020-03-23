package com.example.myapplication.server;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.drivinggrpc.driving.rpc.proto.module.ApplyStateRequest;
import com.drivinggrpc.driving.rpc.proto.module.ApplyStateResponse;
import com.drivinggrpc.driving.rpc.proto.module.ModuleRpcServerGrpc;
import com.drivinggrpc.driving.rpc.proto.module.ApplyRequest;
import com.drivinggrpc.driving.rpc.proto.module.ApplyResponse;
import com.drivinggrpc.driving.rpc.proto.module.StatisticsDataRequest;
import com.drivinggrpc.driving.rpc.proto.module.StatisticsDataResponse;
import com.example.myapplication.interfaces.myCallBack;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.concurrent.Executors;

public class ModuleServer {
    private ModuleRpcServerGrpc.ModuleRpcServerFutureStub futureStub;
    public ModuleServer(){
        futureStub = ModuleRpcServerGrpc.newFutureStub(GrpcChannel.getChannel());
    }
    public void getStatisticsData(final StatisticsDataRequest request,final myCallBack callBack){
        if (request.getUserId() == -1)
            throw new NullPointerException("userId 不能为-1");
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
        ListenableFuture<StatisticsDataResponse> future = futureStub.getStatisticsData(request);
        Futures.addCallback(future, new FutureCallback<StatisticsDataResponse>() {
            @Override
            public void onSuccess(@NullableDecl StatisticsDataResponse result) {
                Message message = new Message();
                message.what=1;
                Bundle bundle = new Bundle();
                bundle.putString("numQuestions",result.getNumQuestions());
                bundle.putString("accuracy",result.getAccuracy());
                bundle.putString("averScore",result.getAverScore());
                bundle.putString("degree",result.getDegree());
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
    public void getApplyState(int userId,final myCallBack callBack){
        if (userId == -1)
            throw new NullPointerException("userId 不能为-1");
        final ApplyStateRequest request = ApplyStateRequest.newBuilder().setUserId(userId).build();
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
        ListenableFuture<ApplyStateResponse> future = futureStub.getApplyState(request);
        Futures.addCallback(future, new FutureCallback<ApplyStateResponse>() {
            @Override
            public void onSuccess(@NullableDecl ApplyStateResponse result) {
                Message message = new Message();
                message.what=1;
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


    public void apply(ApplyRequest request,final myCallBack callBack){
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

        ListenableFuture<ApplyResponse> future = futureStub.apply(request);
        Futures.addCallback(future, new FutureCallback<ApplyResponse>() {
            @Override
            public void onSuccess(@NullableDecl ApplyResponse result) {
                Message message = new Message();
                message.what=1;
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
}
