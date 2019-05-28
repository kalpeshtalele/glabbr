package com.kalpesh.glabbr.service;

import com.kalpesh.glabbr.MessageNotifier;
import com.kalpesh.glabbr.models.MessageStatus;

public class GlabberService extends Thread {
    private MessageNotifier messageNotifier;
    private int status = 1;
    boolean canUpdateData = true;
    public GlabberService(MessageNotifier messageNotifier) {
        this.messageNotifier = messageNotifier;
    }

    @Override
    public void run() {
        while(canUpdateData){
            if(messageNotifier != null) {
                status = (status == 1)? 2 : 1;
                messageNotifier.updateMessageStatus(11, new MessageStatus(0, status, System.currentTimeMillis()));
            }
            try {
                /*
                * Update user status every 15 Seconds, for user id = 0.
                * Name = Kalpesh Talele status updates like send to delivered.
                */
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopTask() {
        Thread.currentThread().interrupt();
        this.interrupt();
        canUpdateData = false;
    }
}
