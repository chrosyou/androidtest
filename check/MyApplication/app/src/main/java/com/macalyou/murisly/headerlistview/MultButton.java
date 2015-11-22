package com.macalyou.murisly.headerlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Administrator.Y on 2015/11/22.
 */
public class MultButton extends Button implements View.OnClickListener {

    private int selectState;

    public MultButton(Context context) {
        super(context);
        initMultButton();
    }

    public MultButton(Context context, AttributeSet attrs){
        super(context, attrs);
    }


    private void initMultButton(){
        selectState = Group.STATE_SELECTED;
    }

    @Override
    public void onClick(View v){
        Log.i("multbutton", "onclick");
        if (Group.STATE_SELECTED == selectState){
            setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.common_select_null, 0);
            selectState = Group.STATE_NOTSELECTED;
        } else {
            setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.common_select_all, 0);
            selectState = Group.STATE_SELECTED;
        }
    }


    public int getSelectState() {
        return selectState;
    }

    public void setSelectState(int state) {
        this.selectState = state;
    }
}
