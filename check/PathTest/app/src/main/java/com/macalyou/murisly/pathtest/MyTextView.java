package com.macalyou.murisly.pathtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/11/10.
 */
public class MyTextView extends View {
    final String str = "path";
    Path[] paths = new Path[3];
    Paint paint;

    public MyTextView(Context context, AttributeSet set)
    {
        super(context, set);

        paths[0] = new Path();
        paths[0].moveTo(0, 0);
        for (int i = 1; i < 7; i++)
        {
            //生成7个点，随机生成他们的Y坐标，连成一条线
            paths[0].lineTo(i * 30, (float) Math.random() * 30);
        }

        paths[1] = new Path();
        RectF rectF = new RectF(0 , 0 , 200 , 120);
        paths[1].addOval(rectF, Path.Direction.CCW);
        paths[2] = new Path();
        paths[2].addArc(rectF , 60, 180);
        //初始化画笔
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(1);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);
        canvas.translate(40, 40);
        //设置从右边开始绘制（右对齐）
        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setTextSize(20);
        //绘制路径
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(paths[0], paint);

    }
}
