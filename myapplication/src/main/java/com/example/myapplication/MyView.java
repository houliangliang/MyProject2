package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * date: 2017/5/7
 * author:侯亮亮候亮亮
 */

public class MyView  extends View{

    private int mWidth;
    private int mHeight;
    private int mRadius;
    Context mContext;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = canvas.getWidth();
        mHeight = canvas.getHeight();
        mRadius = mWidth /2;
        Paint circlePaint = new Paint();
//去锯齿
        circlePaint.setAntiAlias(true);
//设置画笔颜色
        circlePaint.setColor(Color.GREEN);
//设置画笔style为描边
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setStrokeWidth(5);
        canvas.drawCircle(mWidth /2, mHeight /2, mRadius,circlePaint);
        Paint rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setColor(Color.BLUE);
        rectPaint.setStyle(Paint.Style.FILL);
        rectPaint.setStrokeWidth(5);
        Path path = new Path();
        path.moveTo(mWidth /2, mHeight /2- mRadius);
        path.lineTo(mWidth, mHeight /2);
        path.lineTo(mWidth /2, mHeight /2+ mRadius);
        path.lineTo(0, mHeight /2);
        path.close();
        canvas.drawPath(path,rectPaint);
        Paint rect2Paint = new Paint();
        rect2Paint.setAntiAlias(true);
        rect2Paint.setColor(Color.RED);
        rect2Paint.setStyle(Paint.Style.FILL);
        rect2Paint.setStrokeWidth(5);
        Path path2 = new Path();
        path2.moveTo(((mWidth /2+ mWidth)/2),((mHeight /2- mRadius)+(mHeight /2))/2);
        path2.lineTo((mWidth +(mWidth /2))/2,(mHeight /2+(mHeight /2+ mRadius))/2);
        path2.lineTo((mWidth /2)/2,(mHeight + mRadius)/2);
        path2.lineTo((mWidth /2)/2,(mHeight - mRadius)/2);
        canvas.drawPath(path2,rect2Paint);
        path2.close();

    }



}
