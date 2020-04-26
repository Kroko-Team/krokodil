package com.krokoteam.kroko.data.model;

import androidx.databinding.Bindable;

/**
 * Created by Syelkonya on 09.04.2020.
 */
public class Lobby  {

    private String mImageUrl;
    private String mName;
    private String mParticipantsName;
    private int mParticipantsQuantity;
    private String mGameLink;

    public Lobby(String imageUrl, String name, String participantsName, int participantsQuantity, String gameLink) {
        mImageUrl = imageUrl;
        mName = name;
        mParticipantsName = participantsName;
        mParticipantsQuantity = participantsQuantity;
        mGameLink = gameLink;
    }


    public Lobby() {}

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getParticipantsName() {
        return mParticipantsName;
    }

    public void setParticipantsName(String participantsName) {
        mParticipantsName = participantsName;
    }

    public int getParticipantsQuantity() {
        return mParticipantsQuantity;
    }

    public String getParticipantsQuantityString() {
        return String.valueOf(mParticipantsQuantity);
    }

    public void setParticipantsQuantity(int participantsQuantity) {
        mParticipantsQuantity = participantsQuantity;
    }

    public String getGameLink() {
        return mGameLink;
    }

    public void setGameLink(String gameLink) {
        mGameLink = gameLink;
    }
}
