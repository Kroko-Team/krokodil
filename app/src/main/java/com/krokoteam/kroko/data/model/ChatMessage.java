package com.krokoteam.kroko.data.model;

import com.google.firebase.database.PropertyName;

public class ChatMessage {

    private String mSenderID;
    private String mMessage;

    public ChatMessage(String mSenderID, String mMessage){
        this.mSenderID = mSenderID;
        this.mMessage = mMessage;
    }

    @PropertyName("mSenderID")
    public String getmSenderID() {
        return this.mSenderID;
    }

    public void setmSenderID(String mSenderID) {
        this.mSenderID = mSenderID;
    }

    @PropertyName("mMessage")
    public String getmMessage() {
        return this.mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}
