package leeco.hellogithub.home;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import leeco.hellogithub.base.IDataFetchCallback;
import leeco.hellogithub.base.TaskCallBack;
import leeco.hellogithub.home.model.HomePageItemInfo;
import leeco.hellogithub.home.model.HomePageResponse;

/**
 * Created by zhangxin17 on 2018/1/25.
 * home页面数据提供
 */
public class HomePagePresenter implements HomePagePresenterInterface {

    private final HomePageViewInterface mViewInterface;

    HomePagePresenter(HomePageViewInterface viewInterface) {
        this.mViewInterface = viewInterface;
    }

    private final IDataFetchCallback mFetchCallback = new IDataFetchCallback() {
        @Override
        public void onStartDateFetch(String msg) {
            mViewInterface.onStartDataRequest();
        }

        @Override
        public void onDateFetchFail(int code, String msg, String errorCode) {
            mViewInterface.onDataRequestFail(code, msg, errorCode);
        }

        @Override
        public void onDateFetchSuccess(int arg, Object o) {

            if (o != null && o instanceof HomePageResponse) {
                HomePageResponse response = (HomePageResponse) o;

                mViewInterface.onDataRequestSuccess(response.getList());
            }
        }
    };

    @Override
    public void doRequestDataByResume() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                dealHomePageRequest(1);
            }
        }).start();
    }

    private void dealHomePageRequest(final int pageId) {

        TaskCallBack callBack = new TaskCallBack() {
            @Override
            public void callback(int code, String msg, String errorCode, Object object) {

                if (code == CODE_OK && object != null) {
                    mFetchCallback.onDateFetchSuccess(pageId, object);
                } else {
                    mFetchCallback.onDateFetchFail(code, msg, errorCode);
                }
            }
        };

        mFetchCallback.onStartDateFetch("HomePageDataRequest");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mFetchCallback.onDateFetchSuccess(pageId, buildTestData());

//        Context context = ContextProviderUtil.getApplicationContext();
//        new HomePageDataRequest(context, callBack).doRequest(
//                new HomePageParameter(pageId).combineParams());
    }

    private HomePageResponse mHomePageResponseTest;

    private HomePageResponse buildTestData() {
        if (mHomePageResponseTest == null) {
            mHomePageResponseTest = new HomePageResponse();

            List<HomePageItemInfo> list = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                list.add(buildItemInfo(i));
            }

            mHomePageResponseTest.setList(list);
        }
        return mHomePageResponseTest;
    }

    private HomePageItemInfo buildItemInfo(int id) {
        HomePageItemInfo itemInfo = new HomePageItemInfo();

        itemInfo.setItemId(id);
        itemInfo.setShowName("Item-" + id);
        itemInfo.setIconUrl("https://www.baidu.com/img/bd_logo1.png");

        return itemInfo;
    }

    private void logI(String msg) {
        Log.d("Sunny-HomePagePresenter", msg);
    }
}
