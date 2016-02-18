package com.example.administrator.fragmenttest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016/2/17.
 */
public class RelativeLayoutContainer extends RelativeLayout {

    public RelativeLayoutContainer(Context context){
        super(context, null);
    }

    public RelativeLayoutContainer(Context context, AttributeSet attr){
        super(context, attr, 0);
    }

    public RelativeLayoutContainer(Context context, AttributeSet attr, int defStyleAttr){
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
