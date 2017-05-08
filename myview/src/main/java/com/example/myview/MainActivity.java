package com.example.myview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MView mview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        new MView(this);
    }

    private void initView() {
        mview = (MView) findViewById(R.id.mview);
//        mview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                            Toast.makeText(MainActivity.this, "name不能为空", Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }
}
