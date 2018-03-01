package com.sunny.aidl.server;

import com.sunny.libcore.log.LogUtils;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangxin17 on 2018/2/28.
 */
public class AidlServerApplication extends Application {

    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.logD("onCreate");
        mAppContext = this;

    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
