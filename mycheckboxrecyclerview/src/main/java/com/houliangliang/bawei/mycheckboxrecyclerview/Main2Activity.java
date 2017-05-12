package com.houliangliang.bawei.mycheckboxrecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        lv = (RecyclerView) findViewById(R.id.lv);
        Intent intent = getIntent();
        String list = intent.getStringExtra("list");
        String[] split = list.split("-");
        Main2Adapter main2Adapter = new Main2Adapter(this, split);
        lv.setAdapter(main2Adapter);
        lv.setLayoutManager(new LinearLayoutManager(this));
    }
}
