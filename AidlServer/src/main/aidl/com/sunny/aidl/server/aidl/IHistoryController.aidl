// IHistoryController.aidl
package com.sunny.aidl.server.aidl;

// Declare any non-default types here with import statements

import com.sunny.aidl.server.history.HistoryModel;

interface IHistoryController {

    List<HistoryModel> getHistoryList();

    boolean deleteOneHistory(in String videoId);
}
