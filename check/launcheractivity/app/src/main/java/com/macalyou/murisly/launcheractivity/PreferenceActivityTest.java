package com.macalyou.murisly.launcheractivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Administrator on 2015/11/5.
 */
public class PreferenceActivityTest extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // 设置显示参数设置布局。
        addPreferencesFromResource(R.xml.preferences);
    }
}
