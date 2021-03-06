package com.krokoteam.kroko.data.model;

/**
 * Created by Syelkonya on 24.05.2020.
 */
@SuppressWarnings("WeakerAcess")
public class Player {
    private boolean mIsBroadcaster;
    private boolean mIsWinner;
    private int mScore;
    private String mUserId;
    private String mUserHash;
    private String mUserName;

    public Player() {}

    public Player(boolean isBroadcaster, boolean isWinner, int score, String userId, String userName, String userHash) {
        mIsBroadcaster = isBroadcaster;
        mIsWinner = isWinner;
        mScore = score;
        mUserId = userId;
        mUserName = userName;
        mUserHash = userHash;
    }

    public boolean isBroadcaster() {
        return mIsBroadcaster;
    }

    public boolean isWinner() {
        return mIsWinner;
    }

    public int getScore() {
        return mScore;
    }

    public String getUserHash() { return mUserHash; }

    public String getUserId() {
        return mUserId;
    }

    public String getUserName() {
        return mUserName;
    }
}
