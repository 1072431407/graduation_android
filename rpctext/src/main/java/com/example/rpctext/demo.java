package com.example.rpctext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class demo extends AppCompatActivity {
    private TextView textView;
    private Button button;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                textView.setText(msg.getData().getString("key"));
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);


//        button = findViewById(R.id.button);
//        textView = findViewById(R.id.textView);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("192.168.3.36",8899)
//                        .usePlaintext().build();
//
//                TextServerGrpc.TextServerFutureStub stub = TextServerGrpc.newFutureStub(managedChannel);
//                ListenableFuture<Response> future = stub.server(Request.newBuilder().setRequest("张三a").build());
//                Futures.addCallback(future, new FutureCallback<Response>() {
//                    @Override
//                    public void onSuccess(@NullableDecl Response result) {
//                        updateView(result.getResponse());
//                    }
//                    @Override
//                    public void onFailure(Throwable t) {
//                        updateView("失败");
//                    }
//                }, Executors.newCachedThreadPool());
//

//                try {
//                    Response response= future.get();
//                    System.out.println(response.getResponse());
//                    updateView(response.getResponse());
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                        TextServerGrpc.TextServerBlockingStub stub = TextServerGrpc.newBlockingStub(managedChannel);
//                        String str = "张三";
//                        System.out.println(str);
//                        System.out.println(Arrays.toString(str.getBytes()));
//                        Request request = Request.newBuilder()
//                                .setRequest(str)
//                                .build();
//                        Response response = stub.server(request);
//                        System.out.println(response.getResponse());

//                        System.out.println(Arrays.toString(response.getResponseBytes().toByteArray()));
//                        System.out.println(new String(response.getResponseBytes().toByteArray()));
//                        updateView(new String(response.getResponseBytes().toByteArray()));
//                        updateView(response.getResponse());
//                        response: "\351\216\265\346\222\263\347\226\204"
//                        response: "\351\216\265\346\222\263\347\226\204"
//                        byte[] bytes = {-26, -119, -109, -27, -82, -98};
//                        String textString = new String(bytes);
//                        System.out.println(textString);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                });
//            }
////        });
    }

    private void updateView(final String response) {
        Message message = new Message();
        message.what = 0;
        Bundle bundle = new Bundle();
        bundle.putString("key",response);
        message.setData(bundle);
        handler.sendMessage(message);
    }
}
