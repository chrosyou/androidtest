package com.macalyou.murisly.ballfollowfiger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Administrator on 2015/11/2.
 */
public class DrawView extends View{
    public float currentX = 40;
    public float currentY = 50;

    /**
     * consruction
     * @param context
     */
    public DrawView(Context context)
    {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        //创建画笔
        Paint p = new Paint();
        //设置画笔颜色
        p.setColor(Color.GREEN);
        //绘制小圆球
        canvas.drawCircle(currentX, currentY, 20, p);
    }
};
