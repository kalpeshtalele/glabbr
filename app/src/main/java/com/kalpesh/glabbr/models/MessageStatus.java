package com.kalpesh.glabbr.models;

public class MessageStatus {
    private int userId;
    private int status;
    private long time;

    public MessageStatus(int userId, int status, long time) {
        this.userId = userId;
        this.status = status;
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
