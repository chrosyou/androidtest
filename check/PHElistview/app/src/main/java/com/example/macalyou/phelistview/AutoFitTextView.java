package com.example.macalyou.phelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator.Y on 2015/11/19.
 */
public class AutoFitTextView extends TextView {

    private int lastHeight;

    // Attributes
    //private Paint testPaint;
    //private float minTextSize, maxTextSize;

    public AutoFitTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initialise();
        lastHeight = getHeight();
    }

    private void initialise()
    {

    };

    /**
     * Re size the font so the specified text fits in the text box * assuming
     * the text box is the specified width.
     */
    private void refitText(String text, int textWidth) {
        int height = getMeasuredHeight();
        int textLength = text.length();
        int controlLength = (int)(textLength * height / 1.8);
        //setHeight(controlLength);
        float trySize = (float) (height * 0.5);
        //this.setTextSize(trySize);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {

        refitText(this.getText().toString(), w);
    }
}
