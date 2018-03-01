package com.sunny.aidl.server.history;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhangxin17 on 2018/2/28.
 */
public class HistoryModel implements Parcelable {

    private String videoId;

    private String albumId;

    private String videoName;

    private String videoUrl;

    private boolean isVip;

    private long duration;

    private long playPosition;

    public HistoryModel() {

    }

    protected HistoryModel(Parcel in) {
        videoId = in.readString();
        albumId = in.readString();
        videoName = in.readString();
        videoUrl = in.readString();
        isVip = in.readByte() != 0;
        duration = in.readLong();
        playPosition = in.readLong();
    }

    public static final Creator<HistoryModel> CREATOR = new Creator<HistoryModel>() {
        @Override
        public HistoryModel createFromParcel(Parcel in) {
            return new HistoryModel(in);
        }

        @Override
        public HistoryModel[] newArray(int size) {
            return new HistoryModel[size];
        }
    };

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getPlayPosition() {
        return playPosition;
    }

    public void setPlayPosition(long playPosition) {
        this.playPosition = playPosition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(videoId);
        dest.writeString(albumId);
        dest.writeString(videoName);
        dest.writeString(videoUrl);
        dest.writeByte((byte) (isVip ? 1 : 0));
        dest.writeLong(duration);
        dest.writeLong(playPosition);
    }
}
