package leeco.hellogithub.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

import leeco.hellogithub.R;
import leeco.hellogithub.base.BaseActivity;
import leeco.hellogithub.base.IRecyclerViewItemListener;
import leeco.hellogithub.home.model.HomePageItemInfo;
import leeco.hellogithub.home.view.HomePageGridLayoutManager;
import leeco.hellogithub.home.view.HomePageRecyclerAdapter;
import leeco.hellogithub.utils.LargeViewUtil;

/**
 * Created by zhangxin17 on 2018/1/25.
 * 主页面
 */
public class HomeActivity extends BaseActivity implements HomePageViewInterface {

    private static final int MSG_SHOW_LOADING = 1;
    private static final int MSG_SUCCESS = 2;
    private static final int MSG_NOTIFY = 3;

    private RecyclerView mRecyclerView;
    private HomePageGridLayoutManager mLayoutManager;
    private HomePageRecyclerAdapter mRecyclerAdapter;

    private View mLoadingLayout;
    private Button tab1;
    private Button tab2;
    private Button tab3;
    private Button tab4;

    private HomePagePresenter mHomePagePresenter;


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg == null || isDestroyed()) {
                return;
            }

            switch (msg.what) {
                case MSG_SHOW_LOADING:
                    logI("MSG_SHOW_LOADING");
                    if (mLoadingLayout.getVisibility() != View.VISIBLE) {
                        mLoadingLayout.setVisibility(View.VISIBLE);
                    }

                    if (mRecyclerView.getVisibility() == View.VISIBLE) {
                        mRecyclerView.setVisibility(View.GONE);
                    }
                    break;

                case MSG_SUCCESS:
                    logI("MSG_SUCCESS");
                    if (mLoadingLayout.getVisibility() == View.VISIBLE) {
                        mLoadingLayout.setVisibility(View.GONE);
                    }

                    if (mRecyclerView.getVisibility() != View.VISIBLE) {
                        mRecyclerView.setVisibility(View.VISIBLE);
                    }
                    break;

                case MSG_NOTIFY:
                    logI("MSG_NOTIFY");
                    mRecyclerAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mHomePagePresenter = new HomePagePresenter(this);

        initLoadingView();

        initView();
    }

    private void initView() {
        tab1 = findViewById(R.id.home_tab_1);
        tab2 = findViewById(R.id.home_tab_2);
        tab3 = findViewById(R.id.home_tab_3);
        tab4 = findViewById(R.id.home_tab_4);

        initRecyclerView();
    }

    private void initRecyclerView() {

        mRecyclerView = findViewById(R.id.home_recyclerview);

        mRecyclerAdapter = new HomePageRecyclerAdapter(this);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        mLayoutManager = new HomePageGridLayoutManager(this, 3);
        mLayoutManager.setLayoutManagerCallback(new HomePageGridLayoutManager.ILayoutManagerCallback() {
            @Override
            public View getTabView() {
                return tab1;
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerAdapter.setItemListener(mRecyclerViewItemListener);
    }

    private void initLoadingView() {
        mLoadingLayout = findViewById(R.id.home_loading_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHomePagePresenter.doRequestDataByResume();
    }

    @Override
    public void onStartDataRequest() {
        logI("onStartDataRequest");
        mHandler.sendEmptyMessage(MSG_SHOW_LOADING);
    }

    @Override
    public void onDataRequestFail(int code, String msg, String errorCode) {

    }

    @Override
    public void onDataRequestSuccess(List<HomePageItemInfo> list) {
        logI("onDataRequestSuccess  list :" + list);

        mHandler.sendEmptyMessage(MSG_SUCCESS);
        mRecyclerAdapter.setData(list);
        mHandler.sendEmptyMessage(MSG_NOTIFY);
    }

    private final IRecyclerViewItemListener mRecyclerViewItemListener = new IRecyclerViewItemListener() {
        @Override
        public void onItemClick(View v) {

        }

        @Override
        public void onItemFocusChanged(View v, boolean hasFocus) {

            if (hasFocus) {
                LargeViewUtil.largeView(v, 1.2F);
            } else {
                LargeViewUtil.revertView(v);
            }
        }
    };

}
