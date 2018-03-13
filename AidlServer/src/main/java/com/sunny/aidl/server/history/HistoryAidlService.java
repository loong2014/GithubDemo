package com.sunny.aidl.server.history;

import com.sunny.aidl.server.aidl.IHistoryController;
import com.sunny.libcore.service.BaseService;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by zhangxin17 on 2018/3/1.
 * aidl对应的服务
 */
public class HistoryAidlService extends BaseService {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        logI("onBind  mIBinder :" + mIBinder); // 对象是

//        return historyServiceBind;
        return mIBinder;
    }

    private final IBinder mIBinder = new IHistoryController.Stub() {
        @Override
        public List<HistoryModel> getHistoryList() throws RemoteException {
            logI("getHistoryList");
            return HistoryManager.getInstance().getHistoryList();
        }

        @Override
        public boolean deleteOneHistory(String videoId) throws RemoteException {
            logI("deleteOneHistory  : " + videoId);
            return HistoryManager.getInstance().deleteOneHistoryByVideoId(videoId);
        }
    };

    private final IBinder historyServiceBind = new HistoryServiceBind();

    public class HistoryServiceBind extends Binder {
        public HistoryAidlService getService() {
            return HistoryAidlService.this;
        }
    }

}
