package com.sunny.aidl.server;

import com.sunny.libcore.log.LogUtils;
import com.sunny.libcore.service.BaseService;

import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

public class EduAidlService extends BaseService {

    EduAidlService() {
        LogUtils.logD("EduAidlService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IBinder mBinder = new IEduAidl.Stub() {
        @Override
        public int getSum(int a, int b) throws RemoteException {
            return a + b;
        }
    };
}
