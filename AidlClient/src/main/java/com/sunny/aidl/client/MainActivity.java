package com.sunny.aidl.client;

import com.google.gson.Gson;
import com.sunny.aidl.server.aidl.IHistoryController;
import com.sunny.aidl.server.history.HistoryModel;
import com.sunny.libcore.activity.BaseActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
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

        findViewById(R.id.jumpOtherAct).setOnClickListener(this);
        findViewById(R.id.jumpOtherAct).setOnFocusChangeListener(this);


        tipTv = findViewById(R.id.historyTip);

        historyListView = findViewById(R.id.historyListView);
        mHistoryAdapter = new HistoryAdapter();
        mHistoryModels = new ArrayList<>();
        historyListView.setAdapter(mHistoryAdapter);
        historyListView.setOnItemClickListener(mOnItemClickListener);
    }


    private void bindHistoryServer() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.sunny.aidl.server", "com.sunny.aidl.server.history.HistoryAidlService"));
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

    private void doJumpOtherAppActivityIntent() {
        logI("doJumpOtherAppActivityIntent");


        JumpInfo jumpInfo = new JumpInfo("details", "move",
                "child", "10086", "电影");
        Gson gson = new Gson();
        // {"jumpType":"details","modelType":"move","subType":"child","videoId":"10086","videoName":"电影"}
        String jumpJson = gson.toJson(jumpInfo);
        logI("jumpJson : " + jumpJson);


        Intent intent = new Intent();
        intent.setAction("com.suuny.outjump");
        intent.putExtra("sunny_jump", jumpJson);

        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class JumpInfo implements Parcelable {

        String jumpType;  // details
        String modelType;  // move
        String subType;  // child
        String videoId;  // vid
        String videoName;  // name

        public JumpInfo(String jumpType, String modelType, String subType, String videoId, String videoName) {
            this.jumpType = jumpType;
            this.modelType = modelType;
            this.subType = subType;
            this.videoId = videoId;
            this.videoName = videoName;
        }

        public String getJumpType() {
            return jumpType;
        }

        public void setJumpType(String jumpType) {
            this.jumpType = jumpType;
        }

        public String getModelType() {
            return modelType;
        }

        public void setModelType(String modelType) {
            this.modelType = modelType;
        }

        public String getSubType() {
            return subType;
        }

        public void setSubType(String subType) {
            this.subType = subType;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getVideoName() {
            return videoName;
        }

        public void setVideoName(String videoName) {
            this.videoName = videoName;
        }

        protected JumpInfo(Parcel in) {
            jumpType = in.readString();
            modelType = in.readString();
            subType = in.readString();
            videoId = in.readString();
            videoName = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(jumpType);
            dest.writeString(modelType);
            dest.writeString(subType);
            dest.writeString(videoId);
            dest.writeString(videoName);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<JumpInfo> CREATOR = new Creator<JumpInfo>() {
            @Override
            public JumpInfo createFromParcel(Parcel in) {
                return new JumpInfo(in);
            }

            @Override
            public JumpInfo[] newArray(int size) {
                return new JumpInfo[size];
            }
        };
    }

    private void doJumpOtherAppActivityScheme() {
        logI("doJumpOtherAppActivityScheme");

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sunny://details/move/child?vid=10086&name=电影"));

        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        long time = System.currentTimeMillis();
        if (time - lastUpdateTime < 200) {
            return;
        }
        lastUpdateTime = time;

        switch (v.getId()) {

            case R.id.historyUpdate: {
                doGetHistory();
                break;
            }

            case R.id.bindServer: {

                bindHistoryServer();
                break;
            }
            case R.id.jumpOtherAct: {
                doJumpOtherAppActivityIntent();
//                doJumpOtherAppActivityScheme();
                break;
            }

            default:
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            v.setBackgroundColor(Color.GRAY);
        } else {
            v.setBackgroundColor(Color.WHITE);
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

                holder.nameView.setText("client :" + model.getVideoName());
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
