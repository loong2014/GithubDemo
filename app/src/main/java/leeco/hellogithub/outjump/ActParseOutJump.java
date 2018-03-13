package leeco.hellogithub.outjump;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;

public class ActParseOutJump extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logI("doIntentJumpParse by onCreate");
        doIntentJumpParse(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        logI("doIntentJumpParse by onNewIntent");
        doIntentJumpParse(intent);
    }

    private void doIntentJumpParse(Intent intent) {

//        doIntentJumpParseScheme(intent);

        doIntentJumpParseIntent(intent);

        finish();
    }

    private void doIntentJumpParseIntent(Intent intent) {

        // {"jumpType":"details","modelType":"move","subType":"child","videoId":"10086","videoName":"电影"}
        String jumpJson = intent.getStringExtra("sunny_jump");

        Gson gson = new Gson();
        JumpInfo jumpInfo = gson.fromJson(jumpJson, JumpInfo.class);
        jumpInfo.getJumpType(); // details
        jumpInfo.getModelType(); // move
        jumpInfo.getSubType(); // child
        jumpInfo.getVideoId(); // 10086
        jumpInfo.getVideoName(); // 电影

    }


    private static class JumpInfo implements Parcelable {

        private String jumpType;  // details
        private String modelType;  // move
        private String subType;  // child
        private String videoId;  // vid
        private String videoName;  // name

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

    private void doIntentJumpParseScheme(Intent intent) {

        intent.getScheme();  // sunny
        intent.getDataString();  // sunny://details/move/child?vid=10086&name=电影

        Uri uri = intent.getData();  // sunny://details/move/child?vid=10086&name=电影
        if (uri != null) {
            uri.getPathSegments();  // [move, child]

            uri.getQuery();  // vid=10086&name=电影
            uri.getQueryParameterNames(); // [vid, name]
            uri.getQueryParameter("vid");  // 10086
            uri.getQueryParameter("name");  // 电影
        }

    }

    private void logI(String msg) {
        Log.i("Sunny-OutJump", msg);
    }
}
