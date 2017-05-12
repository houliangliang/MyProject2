package com.houliangliang.bawei.myrecl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,OnItemClickListener  {

    private RecyclerView recyclerview;
    private SwipeRefreshLayout swipe_refresh_widget;
    private LinearLayoutManager mLinearLayoutManager;
    private recyclerview_adapter mRecyclerview_adapter;
    static  ArrayList<DataBean> dataBeen = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.list_normal) {
            Toast.makeText(MainActivity.this, "name不能为空", Toast.LENGTH_SHORT).show();
            initdata();
        }

        return super.onOptionsItemSelected(item);
    }

    private void initdata() {

        for (int i = 0; i < 20; i++) {
            DataBean dataBean = new DataBean();
            dataBean.setIcon(R.mipmap.ic_launcher);
            dataBean.setName("你好" + i);
            dataBeen.add(dataBean);

        }
//        swipe_refresh_widget.setRefreshing(false);
        swipe_refresh_widget.setEnabled(false);
        mRecyclerview_adapter = new recyclerview_adapter(MainActivity.this, dataBeen);
        mRecyclerview_adapter.setOnItemClickListener(this);
        recyclerview.setAdapter(mRecyclerview_adapter);
        mLinearLayoutManager = new LinearLayoutManager(this);

        recyclerview.setLayoutManager(mLinearLayoutManager);
    }

    private void initView() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        swipe_refresh_widget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        swipe_refresh_widget.setOnRefreshListener(MainActivity.this);


        recyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {

            private int mLastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItem + 1 == mRecyclerview_adapter.getItemCount()) {
                    swipe_refresh_widget.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....


//                    handler.sendEmptyMessageDelayed(0, 3000);
                    initdata();

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    public static String httpdata(String url) {
        try {

            URL uu = new URL(url);

            HttpURLConnection httpurl = (HttpURLConnection) uu.openConnection();

            httpurl.setRequestMethod("POST");

            httpurl.setConnectTimeout(5000);

            httpurl.setReadTimeout(5000);

            if (httpurl.getResponseCode() == 200) {

                InputStream inputStream = httpurl.getInputStream();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte by[] = new byte[1024];
                int len = 0;

                while ((len = inputStream.read(by)) != -1) {
                    byteArrayOutputStream.write(by, 0, len);
                }
                return byteArrayOutputStream.toString("utf-8");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onRefresh() {
        swipe_refresh_widget.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//                    handler.sendEmptyMessageDelayed(0, 3000);
        initdata();
    }

    @Override
    public void ItemChickListeren(int position) {
    Toast.makeText(this, "name不能为空"+position, Toast.LENGTH_SHORT).show();

    }
}
