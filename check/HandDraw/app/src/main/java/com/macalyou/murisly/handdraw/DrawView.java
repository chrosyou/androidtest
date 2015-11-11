package com.macalyou.murisly.handdraw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2015/11/11.
 */
public class DrawView extends View {
    float preX;
    float preY;
    private Path path;
    public Paint paint = null;
    final int VIEW_WIDTH = 320;
    final int VIEW_HEIGHT = 480;

    //定义内存中的图片，将图片作为缓冲区
    Bitmap cacheBitmap = null;
    //定义cacheBitmap 上的canvas对象
    Canvas cacheCanvas = null;
    public DrawView (Context context, AttributeSet set)
    {
        super(context, set);

        //创建一个与该view相同大小的缓冲区
        cacheBitmap = Bitmap.createBitmap(VIEW_WIDTH, VIEW_HEIGHT, Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas();
        path = new Path();

        //设置cacheCanvas将会绘制到内存中的cacheBitmap上
        cacheCanvas.setBitmap(cacheBitmap);

        //设置画笔的颜色
        paint = new Paint(Paint.DITHER_FLAG);
        paint.setColor(Color.RED);

        //设置画笔风格
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        //反锯齿
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //获取拖动事件的位置
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                preX = x;
                preY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                path.quadTo(preX, preY, x, y);
                preX = x;
                preY = y;
                break;

            case MotionEvent.ACTION_UP:
                cacheCanvas.drawPath(path, paint);
                path.reset();
                break;
        }

        invalidate();
        //返回true表明处理方法已经处理改事件
        return true;
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        Paint bmpPaint = new Paint();
        //将cacheBitmap 绘制到View的组件上
        canvas.drawBitmap(cacheBitmap, 0, 0, bmpPaint);
        //沿path绘制
        canvas.drawPath(path, paint);

    }


}
