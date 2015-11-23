package com.example.macalyou.mobilesafe.welcomepage;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.macalyou.mobilesafe.R;
import com.example.macalyou.mobilesafe.welcomepage.utils.MyUtils;


/**
 * Created by macalyou on 2015/11/24.
 */
public class SplashActivity extends Activity {

    /** 应用版本号 */
    private TextView mVersionTV;

    /** 本地版本号 */
    private String mVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** 去掉标题栏 */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        mVersion = MyUtils.getVersion(getApplicationContext());
        initView();
    }

    /** 初始化控件 */
    public void initView() {
        mVersionTV = (TextView) findViewById(R.id.tv_splash_version);
        mVersionTV.setText("版本号： " + mVersion);
    }


}
