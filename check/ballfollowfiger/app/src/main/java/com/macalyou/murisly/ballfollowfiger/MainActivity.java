package com.macalyou.murisly.ballfollowfiger;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获得布局中的LinearLayout容器
        LinearLayout root = (LinearLayout)findViewById(R.id.root);

        //创建DrawView组件
        final DrawView draw = new DrawView(this);
        //设置自定义
        draw.setMinimumWidth(300);
        draw.setMinimumHeight(500);

        //为draw绑定touch事件
        draw.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //修改draw组件的currentX和currentY两个属性
                draw.currentX = event.getX();
                draw.currentY = event.getY();
                //通知draw组件重绘
                draw.invalidate();
                //返回true表示处理方法已经处理该事件
                return true;
            }
        });
        root.addView(draw);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
