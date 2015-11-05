package com.macalyou.murisly.startactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2015/11/5.
 */
public class SecondActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        //获得previous 按钮
        Button previous = (Button)findViewById(R.id.previous);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取启动当前Activity的上一个Intent
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);

                //启动intent对应的Activity
                startActivity(intent);
            }
        });
    }
}
