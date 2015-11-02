package com.macalyou.murisly.uitest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    int[] images = new int[]{
         R.drawable.alarm,
         R.drawable.dribbble,
         R.drawable.facebook,
    };
    int currentImg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取布局容器
        LinearLayout main = (LinearLayout) findViewById(R.id.fragment);
        //创建ImageView组件
        final ImageView image = new ImageView(this);
        //将ImageView添加到布局当中
        main.addView(image);
        image.setImageResource(images[0]);
        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImg >= 2) {
                    currentImg = -1;
                }
                //改变ImageView里显示的图片
                image.setImageResource(images[++currentImg]);
            }
        });

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
