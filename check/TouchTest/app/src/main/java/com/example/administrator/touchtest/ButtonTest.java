package com.example.administrator.touchtest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by Administrator on 2016/1/21.
 */
public class ButtonTest extends Button {

    public ButtonTest(Context context){
        this(context, null);
    }

    public ButtonTest(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ButtonTest(Context context, AttributeSet attrs, int intDefStyle) {
        super(context, attrs, intDefStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("dispatchTouchEvent", "action=" + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("onTouchEvent", "action=" + event.getAction());
        super.onTouchEvent(event);
        return false;
        //return super.onTouchEvent(event);
    }
}
