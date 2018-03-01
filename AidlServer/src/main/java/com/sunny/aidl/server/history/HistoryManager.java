package com.sunny.aidl.server.history;

import com.sunny.libcore.log.LogUtils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxin17 on 2018/2/28.
 */
public class HistoryManager {

    private static final class SingletonHolder {
        private static final HistoryManager INSTANCE = new HistoryManager();
    }

    public static HistoryManager getInstance() {
        LogUtils.logD("getInstance  :" + SingletonHolder.INSTANCE);
        return SingletonHolder.INSTANCE;
    }

    private HistoryManager() {
        LogUtils.logD("init HistoryManager");
    }

    private List<HistoryModel> mLocalHistoryList = null;


    public List<HistoryModel> getHistoryList() {
        if (mLocalHistoryList == null) {
            mLocalHistoryList = getLocalHistoryList();
        }
        LogUtils.logD("getHistoryList  :" + mLocalHistoryList.size());
        return mLocalHistoryList;
    }

    public boolean deleteOneHistoryByVideoId(String videoId) {
        if (TextUtils.isEmpty(videoId)) {
            return false;
        }

        if (mLocalHistoryList == null) {
            return false;
        }

        int size = mLocalHistoryList.size();
        int needDelIndex = -1;
        for (int i = 0; i < size; i++) {
            if (videoId.equals(mLocalHistoryList.get(i).getVideoId())) {
                needDelIndex = i;
                break;
            }
        }

        if (needDelIndex != -1) {
            mLocalHistoryList.remove(needDelIndex);
            return true;
        }

        return false;
    }

    private List<HistoryModel> getLocalHistoryList() {
        List<HistoryModel> list = new ArrayList<>();
        list.add(buildHistoryModel("videoIdAaa", "videoNameAaa", 100000));
        list.add(buildHistoryModel("videoIdBbb", "videoNameBbb", 200000));
        list.add(buildHistoryModel("videoIdCcc", "videoNameCcc", 300000));
        list.add(buildHistoryModel("videoIdDdd", "videoNameDdd", 400000));
        list.add(buildHistoryModel("videoIdEee", "videoNameEee", 500000));
        list.add(buildHistoryModel("videoIdFff", "videoNameFff", 600000));
        LogUtils.logD("getLocalHistoryList  :" + list.size());
        return list;
    }

    private HistoryModel buildHistoryModel(String videoId, String videoName, long duration) {
        HistoryModel model = new HistoryModel();
        model.setVideoId(videoId);
        model.setVideoName(videoName);
        model.setDuration(duration);
        model.setPlayPosition(0);
        return model;
    }

}
