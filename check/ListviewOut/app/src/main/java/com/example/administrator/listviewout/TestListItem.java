package com.example.administrator.listviewout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/1/6.
 */
public class TestListItem extends RelativeLayout {

    private TextView name__;

    public TestListItem(Context context) {
        this(context, null);
    }

    public TestListItem(Context context, AttributeSet attr) {
        this(context, attr, 0);
    }

    public TestListItem(Context context, AttributeSet attr, int defStyleAttr) {
        super(context, attr, defStyleAttr);
        inflate(context, R.layout.test_childitem, this);

        //AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.getInstance().dp2Pix(68));
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 68);
        setLayoutParams(params);

        name__ = (TextView) findViewById(R.id.name);
    }

    public void freshData(String item) {
        name__.setText(item);
    }
}
