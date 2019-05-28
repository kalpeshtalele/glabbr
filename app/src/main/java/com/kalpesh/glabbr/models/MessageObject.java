package com.kalpesh.glabbr.models;

import java.util.ArrayList;

public class MessageObject {
    private int messageId;
    private long messageTime;
    private String messageText;
    private ArrayList<MessageStatus> messageStatus;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public ArrayList<MessageStatus> getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(ArrayList<MessageStatus> messageStatus) {
        this.messageStatus = messageStatus;
    }

    public MessageObject(int messageId, long messageTime, String messageText, ArrayList<MessageStatus> messageStatus) {
        this.messageId = messageId;
        this.messageTime = messageTime;
        this.messageText = messageText;
        this.messageStatus = messageStatus;
    }
}
