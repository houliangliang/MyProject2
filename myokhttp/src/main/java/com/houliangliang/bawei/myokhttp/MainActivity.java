package com.houliangliang.bawei.myokhttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import Utils.OkHttpManagerHLL;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button get;
    private Button getAsyn;
    private Button post;
    private Button postAysn;
    private OkHttpClient mBuild;
    private RecyclerView recyclerView;
     Handler hh=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

//        List<DataBean.ResultBean.DateBean> mDate= (List<DataBean.ResultBean.DateBean>) msg.obj;
//        recyclerView.setAdapter(new recyclerviewadapter(MainActivity.this,mDate));
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
//        recyclerView.setLayoutManager(linearLayoutManager);
    }
};
    private OkHttpClient mclient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initokHttp();

    }

    private void initokHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        mclient = builder.build();
    }

    private void initView() {
        get = (Button) findViewById(R.id.get);
        getAsyn = (Button) findViewById(R.id.getAsyn);
        post = (Button) findViewById(R.id.post);
        postAysn = (Button) findViewById(R.id.postAysn);

        get.setOnClickListener(this);
        getAsyn.setOnClickListener(this);
        post.setOnClickListener(this);
        postAysn.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get:
//                try {
//                    okhttpget();
//                } catch (IOException e) {
//
//
//                }

                OkHttpManagerHLL.getAsync(URLUtils.url3, new OkHttpManagerHLL.DataCallBack() {
                    @Override
                    public void requestFailure(Request request, IOException e) {

                    }

                    @Override
                    public void requestSuccess(String requset) {
                        Toast.makeText(MainActivity.this, requset, Toast.LENGTH_SHORT).show();

                    }
                });
                break;
            case R.id.getAsyn:
                getAsyn();
                break;
            case R.id.post:

                break;
            case R.id.postAysn:
   post();
                break;
        }
    }

    public void okhttpget() throws IOException {

        new Thread() {
            @Override
            public void run() {
                super.run();
                Request.Builder builder = new Request.Builder()
                        .url(URLUtils.url3);
                builder.method("GET", null);
                Request build = builder.build();
                Call call = mclient.newCall(build);
                Response execute = null;
                try {
                    execute = call.execute();
                    Log.d("6666666666666", execute.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();


    }

    public void getAsyn() {
        final Request.Builder get = new Request.Builder().method("GET", null).url(URLUtils.url);
        Request build = get.build();
        Call call = mclient.newCall(build);
        call.enqueue(new Callback() {

            private List<DataBean.ResultBean.DateBean> mDate;

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String s = response.body().string();
                Gson gson = new Gson();
                DataBean dataBean = gson.fromJson(s, DataBean.class);
                mDate = dataBean.getResult().getDate();
                Log.d("9999999999999999999", s);
                Message message = new Message();
                message.obj=mDate;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(new recyclerviewadapter(MainActivity.this,mDate));
                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
                        recyclerView.setLayoutManager(linearLayoutManager);
                    }
                });
            }
        });
    }


    public void post(){
        FormBody build1 = new FormBody.Builder()
//                .add("page", "1")
//                .add("code", "news")
//                .add("pageSize", "20")
//                .add("parentid", "0")
//                .add("type", "1")
                .build();

        Request.Builder post = new Request.Builder().method("POST", build1).url(URLUtils.url3);
        Request build = post.build();
        Call call = mclient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                String string2 = response.body().toString();
                Log.d("88888888888",string);
                Log.d("88888888888",string2);
            }
        });
    }
}
