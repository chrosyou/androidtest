package com.example.administrator.fragmenttest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2016/2/17.
 */
public class FrameLayoutContainer extends FrameLayout {

    public FrameLayoutContainer(Context context){
        super(context, null);
    }

    public FrameLayoutContainer(Context context, AttributeSet attr){
        super(context, attr, 0);
    }

    public FrameLayoutContainer(Context context, AttributeSet attr, int defStyleAttr){
        super(context, attr, defStyleAttr);
    }

    public float getXFraction() {
        return getX() / getWidth(); // TODO: guard divide-by-zero
    }

    public void setXFraction(float xFraction) {
        // TODO: cache width
        final int width = getWidth();
        setX((width > 0) ? (xFraction * width) : -9999);
    }
}
