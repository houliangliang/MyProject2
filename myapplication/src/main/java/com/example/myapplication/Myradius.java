package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Myradius extends AppCompatActivity  {

    private MyView myview;
    private MyView mFg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myradius);
        initView();

//        initView();
        MyView myView = new MyView(this);
    }

    private void initView() {
        mFg = (MyView) findViewById(R.id.myview);
//        mFg.setOnTouchListener(this);

    }

    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                //      钟表
//                if (x > 130 && y > 180 && x < 250  && y < 250) {
//                    Toast.makeText(MainActivity.this,"点击了",Toast.LENGTH_SHORT).show();
//                }

                //圆方

                //圆心坐标
                int lastX = mFg.getWidth() / 2;
                int lastY = mFg.getHeight() / 2;

                Log.i("zzz", "x  y " + x + "  " + y + " last x y " + lastX + " " + lastY);

                //圆半径 通过左右坐标计算获得getLeft
                int r = (mFg.getRight() - mFg.getLeft()) / 2 - 5;

                //点击位置x坐标与圆心的x坐标的距离
                int distanceX = Math.abs((int) x - lastX);
                //点击位置y坐标与圆心的y坐标的距离
                int distanceY = Math.abs((int) y - lastY);
                //点击位置与圆心的直线距离
                int distanceZ = (int) Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));

                //如果点击位置与圆心的距离小于圆的半径，证明点击位置在圆内
                if (distanceZ < r) {

                    float x1 = x - lastX;
                    float y1 = (float) Math.floor((double) (y - lastY));

                    if (r - Math.abs(x1) > Math.abs(y1)) {

                        if (x > lastX - r / 2 && x < lastX + r / 2 && y > lastY - r / 2 && y < lastY + r / 2) {
                            Toast.makeText(Myradius.this, "在方内", Toast.LENGTH_SHORT).show();
                            return true;
                        }


                        Toast.makeText(Myradius.this, "在菱形内", Toast.LENGTH_SHORT).show();
                        return true;
                    }


                    Toast.makeText(Myradius.this, "在圆内", Toast.LENGTH_SHORT).show();

                }


                break;
        }
        return true;

    }

//    private void initView() {
//        myview = (MyView) findViewById(R.id.myview);
//        myview.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int x =(int) event.getX();
//                int y =(int) event.getY();
//                v.setDrawingCacheEnabled(true);
//                int pixel = Bitmap.createBitmap(v.getDrawingCache()).getPixel(x,y);
//                //判断该像素在颜色中值
//                int redValue = Color.red(pixel);
//                int blueValue = Color.blue(pixel);
//                int greenValue = Color.green(pixel);
//                if (redValue==255){
//                    Toast.makeText(Myradius.this,"您点击了红色",Toast.LENGTH_SHORT).show();
//                }else if (blueValue==255){
//                    Toast.makeText(Myradius.this,"您点击了蓝色",Toast.LENGTH_SHORT).show();
//                }else if (greenValue==255){
//                    Toast.makeText(Myradius.this,"您点击了绿色",Toast.LENGTH_SHORT).show();
//                }
//                return false;
//            }
//        });
//    }


}
