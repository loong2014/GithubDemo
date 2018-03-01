package com.sunny.libcore.log;

import android.util.Log;

/**
 * Created by zhangxin17 on 2018/2/28.
 * 日志管理类
 */
public class LogUtils {

    private static final String TAG = "SunnyLog";

    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        return tag;
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

    public static void logD(String msg) {
        logD(TAG, msg);
    }

    public static void logD(String tag, String msg) {
        StackTraceElement caller = getCallerStackTraceElement();
        tag = tag + "  " + generateTag(caller);
        Log.d(tag, msg + "\n");
    }

    public static void logI(String msg) {
        logI(TAG, msg);
    }

    public static void logI(String tag, String msg) {
        StackTraceElement caller = getCallerStackTraceElement();
        tag = tag + "  " + generateTag(caller);
        Log.i(tag, msg + "\n");
    }
}
