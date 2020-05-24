package com.krokoteam.kroko.data.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Syelkonya on 09.04.2020.
 */
@SuppressWarnings("WeakerAcess")
public class Lobby  {
    private static final String USER_NAME_TAG = "user_name";

    private String mGameName, mHostUserId, mHostUserName, mImageUrl, mRoomId, mSecretWord;
    private int mGameStatus;
    private ArrayList<Player>  mPlayers;

    public Lobby(String gameName, String hostUserId, String hostUserName, String imageUrl,
                 ArrayList<Player> playerArray, String roomId, String secretWord, int gameStatus) {
        mGameName = gameName;
        mHostUserId = hostUserId;
        mHostUserName = hostUserName;
        mImageUrl = imageUrl;
        mPlayers = playerArray;
        mRoomId = roomId;
        mSecretWord = secretWord;
        mGameStatus = gameStatus;
    }

    public Lobby() {}

    public String getGameName() {
        return mGameName;
    }

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

    public Player getPlayerByUserId(String id) {
        for (Player player : mPlayers) {
            if (player.getUserId() == id)
                return player;
        }
        return null;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getRoomId() {
        return mRoomId;
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
