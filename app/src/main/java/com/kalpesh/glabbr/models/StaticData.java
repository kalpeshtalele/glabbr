package com.kalpesh.glabbr.models;

import java.util.ArrayList;

public class StaticData {

    private static StaticData instance;
    private ArrayList<User> users;
    private ArrayList<MessageObject> messageObjects;

    private StaticData(){
        setUserListData();
        setMessageObjectData();
    };

    public static StaticData getInstance() {
        if(instance == null) {
            instance = new StaticData();
        }
        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<MessageObject> getMessageObjects() {
        return messageObjects;
    }

    private void setUserListData() {
        users = new ArrayList<>();
        users.add(new User(0, "Kalpesh Talele"));
        users.add(new User(1, "Mangesh"));
        users.add(new User(2, "Test User"));
        users.add(new User(3, "KAT"));
        users.add(new User(4, "Glabbr user"));
        users.add(new User(5, "Mr. Talele"));
        users.add(new User(7, "Vijay Bharambe"));
        users.add(new User(8, "Anil Chaudhari"));
    }

    private void setMessageObjectData() {
        messageObjects = new ArrayList<>();
        messageObjects.add(new MessageObject(11, System.currentTimeMillis(), "This is dummy message of id 11", getMessageStatus()));
        messageObjects.add(new MessageObject(22, System.currentTimeMillis(), "Id of this message is: 22", getMessageStatus()));
    }


    private ArrayList<MessageStatus> getMessageStatus() {
        ArrayList<MessageStatus> messageStatuses = new ArrayList<>();
        //Message Delivered
        messageStatuses.add(new MessageStatus(0, 1, System.currentTimeMillis()));
        messageStatuses.add(new MessageStatus(2, 1, System.currentTimeMillis() - 3600));
        messageStatuses.add(new MessageStatus(4, 1, System.currentTimeMillis() - (3600 * 2)));
        messageStatuses.add(new MessageStatus(8, 1, System.currentTimeMillis() - (3600 * 3)));

        //MessageRead
        messageStatuses.add(new MessageStatus(1, 2, System.currentTimeMillis() - (3600 * 4)));
        messageStatuses.add(new MessageStatus(3, 2, System.currentTimeMillis() - (3600 * 5)));
        messageStatuses.add(new MessageStatus(5, 2, System.currentTimeMillis() -  - (3600 * 6)));
        return messageStatuses;
    }
}
