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

    private String mGameName;
    private String mHostUserId;
    private String mHostUserName;
    private String mImageUrl;
    private ArrayList<Player>  mPlayers;
    private String mRoomId;
    private String mSecretWord;

    public Lobby(String gameName, String hostUserId, String hostUserName, String imageUrl,
                 ArrayList<Player> playerArray, String roomId, String secretWord) {
        mGameName = gameName;
        mHostUserId = hostUserId;
        mHostUserName = hostUserName;
        mImageUrl = imageUrl;
        mPlayers = playerArray;
        mRoomId = roomId;
        mSecretWord = secretWord;
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

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getRoomId() {
        return mRoomId;
    }
}
