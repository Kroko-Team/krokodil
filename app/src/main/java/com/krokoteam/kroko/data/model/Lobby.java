package com.krokoteam.kroko.data.model;

/**
 * Created by Syelkonya on 09.04.2020.
 */
public class Lobby  {

    private String mImageUrl;
    private String mName;
    private String mParticipantsName;
    private int mParticipantsQuantity;
    private String mGameLink;

    public Lobby(String name,String imageUrl, String gameLink) {
        mImageUrl = imageUrl;
        mName = name;
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
