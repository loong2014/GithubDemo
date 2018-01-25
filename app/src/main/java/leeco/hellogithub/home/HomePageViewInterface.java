package leeco.hellogithub.home;

import java.util.List;

import leeco.hellogithub.home.model.HomePageItemInfo;

/**
 * Created by zhangxin17 on 2018/1/25.
 * home页面需要实现的接口
 */
public interface HomePageViewInterface {

    void onStartDataRequest();

    void onDataRequestFail(int code, String msg, String errorCode);

    void onDataRequestSuccess(List<HomePageItemInfo> list);
}
