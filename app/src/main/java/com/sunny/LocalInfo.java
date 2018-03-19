package com.sunny;

public class LocalInfo {
    public String localName;

    private int fileId;

    public LocalInfo(String name, int id) {
        this.localName = name;
        this.fileId = id;
    }

    public String getLocalName() {
        return this.localName;
    }

    public int getFileId() {
        return this.fileId;
    }

    private String getLocalStr() {
        return this.localName + " " + this.fileId;
    }

}
