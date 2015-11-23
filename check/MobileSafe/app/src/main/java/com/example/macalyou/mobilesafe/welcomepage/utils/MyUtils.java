package com.example.macalyou.mobilesafe.welcomepage.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by macalyou on 2015/11/24.
 */
public class MyUtils {
    /**
     * 获取版本号，返回版本号
     *
     */
    public static String getVersion(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            // 获取当前包的程序名
            PackageInfo packageinfo = manager.getPackageInfo(context.getPackageName(), 0);
            return packageinfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
