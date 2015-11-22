package com.macalyou.murisly.callbackbuton;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/11/5.
 */
public class myclass extends Button implements View.OnClickListener {
    public myclass (Context context, AttributeSet set)
    {
        super(context, set);
    }

    @Override
    public void onClick(View v) {
        Log.i("multbutton", "onclick");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        super.onKeyDown(keyCode, event);
        Log.v("-crazyit.org-", "the key down");

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
        TextView show = (TextView)findViewById(R.id.tvshow);
        show.setText("已经点击");
        //返回true表示事件不会扩散
        return true;
    }
}
