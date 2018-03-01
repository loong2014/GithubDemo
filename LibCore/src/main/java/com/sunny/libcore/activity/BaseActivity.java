package com.sunny.libcore.activity;

import com.sunny.libcore.log.LogUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

/**
 * Created by zhangxin17 on 2018/1/25.
 * activity的基类
 */
public class BaseActivity extends Activity {

    private static final boolean DEBUG = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logD("onCreate");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        logD("onNewIntent");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logD("onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        logD("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logD("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logD("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logD("onStop");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        logD("onBackPressed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logD("onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        logD("onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        logD("onRestoreInstanceState");
    }

    protected void logI(String msg) {
        LogUtils.logI(msg);
    }

    protected void logD(String msg) {
        if (DEBUG) {
            LogUtils.logD(msg);
        }
    }
}
