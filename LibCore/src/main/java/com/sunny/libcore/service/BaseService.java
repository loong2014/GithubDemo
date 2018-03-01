package com.sunny.libcore.service;

import com.sunny.libcore.log.LogUtils;

import android.app.Service;

public abstract class BaseService extends Service {

    protected void logI(String msg) {
        LogUtils.logI(msg);
    }

    protected void logD(String msg) {
        LogUtils.logD(msg);
    }
}
