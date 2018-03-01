package com.sunny.aidl.server;

import com.sunny.aidl.server.aidl.IHistoryController;
import com.sunny.aidl.server.history.HistoryAidlService;
import com.sunny.aidl.server.history.HistoryModel;
import com.sunny.libcore.activity.BaseActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private TextView tipTv;
    private long lastUpdateTime = 0;

    private ListView historyListView;
    private HistoryAdapter mHistoryAdapter;
    private List<HistoryModel> mHistoryModels;
    private LayoutInflater mLayoutInflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayoutInflater = LayoutInflater.from(this);
        initView();

        bindHistoryServer();
    }

    private void initView() {
        findViewById(R.id.historyUpdate).setOnClickListener(this);
        findViewById(R.id.historyUpdate).setOnFocusChangeListener(this);

        findViewById(R.id.bindServer).setOnClickListener(this);
        findViewById(R.id.bindServer).setOnFocusChangeListener(this);


        tipTv = findViewById(R.id.historyTip);

        historyListView = findViewById(R.id.historyListView);
        mHistoryAdapter = new HistoryAdapter();
        mHistoryModels = new ArrayList<>();
        historyListView.setAdapter(mHistoryAdapter);
        historyListView.setOnItemClickListener(mOnItemClickListener);
    }


    private void bindHistoryServer() {
        Intent intent = new Intent(this, HistoryAidlService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private boolean isConnected = false;
    private IHistoryController mHistoryController;
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mHistoryController = IHistoryController.Stub.asInterface(service);
            isConnected = true;
            tipTv.setText("服务绑定成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnected = false;
            mHistoryController = null;
            tipTv.setText("服务失去连接");
        }
    };

    private List<HistoryModel> getHistoryModelsByAidl() {
        if (isConnected) {
            try {
                return mHistoryController.getHistoryList();
            } catch (RemoteException e) {
                logI("getHistoryList by aidl error");
                e.printStackTrace();
            }
        } else {
            logI("getHistoryModelsByAidl  not connected");
        }
        return null;
    }

    private void doGetHistory() {
        logI("doGetHistory");
        mHistoryModels = getHistoryModelsByAidl();
        if (mHistoryModels == null) {
            logI("doGetHistory mHistoryModels is null");
            mHistoryModels = new ArrayList<>();
        }
        mHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.historyUpdate: {
                long time = System.currentTimeMillis();
                if (time - lastUpdateTime < 200) {
                    return;
                }
                lastUpdateTime = time;
                doGetHistory();
                break;
            }

            case R.id.bindServer: {
                long time = System.currentTimeMillis();
                if (time - lastUpdateTime < 200) {
                    return;
                }
                lastUpdateTime = time;
                bindHistoryServer();
                break;
            }

            default:
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {

            case R.id.historyUpdate:
            case R.id.bindServer:
                if (hasFocus) {
                    v.setBackgroundColor(Color.GRAY);
                } else {
                    v.setBackgroundColor(Color.WHITE);
                }
                break;

            default:
                break;
        }
    }

    private final AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            logI("onItemClick  delete one history");
            HistoryModel model = mHistoryAdapter.getItem(position);
            try {
                boolean state = mHistoryController.deleteOneHistory(model.getVideoId());
                logI("deleteOneHistory success :" + state);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    private class HistoryAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mHistoryModels.size();
        }

        @Override
        public HistoryModel getItem(int position) {
            if (position >= 0 && position < getCount()) {
                return mHistoryModels.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HistoryHolder holder;
            if (convertView == null) {
                holder = new HistoryHolder();
                convertView = mLayoutInflater.inflate(R.layout.item_history, null);
                holder.nameView = convertView.findViewById(R.id.itemName);
                holder.durationView = convertView.findViewById(R.id.itemDuration);
                holder.playPosView = convertView.findViewById(R.id.itemPlayPos);
                convertView.setTag(holder);
            } else {
                holder = (HistoryHolder) convertView.getTag();
            }

            HistoryModel model = getItem(position);
            if (model != null) {

                holder.nameView.setText("server :" + model.getVideoName());
                holder.durationView.setText("" + model.getDuration());
                holder.playPosView.setText("" + model.getPlayPosition());

            } else {
                holder.nameView.setText("XXX");
                holder.durationView.setText("0000");
                holder.playPosView.setText("0000");
            }
            return convertView;
        }

        class HistoryHolder {
            TextView nameView;
            TextView durationView;
            TextView playPosView;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }
}
