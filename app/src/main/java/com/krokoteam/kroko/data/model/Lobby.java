package com.krokoteam.kroko.data.model;


import com.google.firebase.database.PropertyName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Syelkonya on 09.04.2020.
 */
@SuppressWarnings("WeakerAcess")
public class Lobby  {
    private static final String USER_NAME_TAG = "user_name";
    private String mRoomId, mSecretWord, mImageUrl, mHostUserName, mHostUserId, mGameName;
    private int mGameStatus;

    private ArrayList<Player> mPlayers;

    public Lobby(String mGameName, String mHostUserId, String mHostUserName, String mImageUrl,
                 ArrayList<Player> mPlayers, String mRoomId, String mSecretWord, int mGameStatus) {
        this.mGameName = mGameName;
        this.mHostUserId = mHostUserId;
        this.mHostUserName = mHostUserName;
        this.mImageUrl = mImageUrl;
        this.mPlayers = mPlayers;
        this.mRoomId = mRoomId;
        this.mSecretWord = mSecretWord;
        this.mGameStatus = mGameStatus;
    }

    public Lobby() {}

    public String getPlayerNames() {
        StringBuilder playerNames = new StringBuilder();
        for (Player player: mPlayers){
            playerNames.append(player.getUserName());
        }
        return playerNames.toString();
    }

    public String getPlayersQuantity(){
        if (mPlayers != null) {
            return String.valueOf(mPlayers.size());
        } else {
            return "null";
        }
    }

    public Player getPlayerByHash(String id) {
        for (Player player : mPlayers) {
            if (player.getUserHash().equals(id))
                return player;
        }
        return null;
    }

    @PropertyName("mGameName")
    public String getGameName() {
        return mGameName;
    }

    @PropertyName("mImageUrl")
    public String getImageUrl() {
        return mImageUrl;
    }

    @PropertyName("mSecretWord")
    public String getSecretWord() {
        return mSecretWord;
    }

    @PropertyName("mRoomId")
    public String getRoomId() {
        return mRoomId;
    }

    @PropertyName("mHostUserId")
    public String getHostUserId() {
        return mHostUserId;
    }

    @PropertyName("mHostUserName")
    public String getHostUserName() {
        return mHostUserName;
    }

    @PropertyName("mGameStatus")
    public int getGameStatus() {
        return mGameStatus;
    }

    @PropertyName("mPlayers")
    public ArrayList<Player> getPlayers() {
        return mPlayers;
    }


    public GameStatus getCurrentGameStatement() {
        switch (mGameStatus) {
            case 0:
                return GameStatus.PREPARE;
            case 1:
                return GameStatus.GAME;
            default:
                return GameStatus.END;
        }
    }

    public enum GameStatus {
        PREPARE,
        GAME,
        END
    }
}
