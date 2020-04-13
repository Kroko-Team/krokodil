package com.krokoteam.kroko.viewmodel;

import com.krokoteam.kroko.data.model.Lobby;

/**
 * Created by Syelkonya on 07.04.2020.
 */
public class LobbyListItemViewModel {

    private String mLobbyImageUrl;
    private String mLobbyName;
    private String mLobbyParticipantsName;
    private int mLobbyParticipantsQuantity;
    private String mGameLink;

    public LobbyListItemViewModel(Lobby lobby){
        mLobbyImageUrl = lobby.getImageUrl();
        mLobbyName = lobby.getName();
        mLobbyParticipantsName = lobby.getName();
        mLobbyParticipantsQuantity = lobby.getParticipantsQuantity();
        mGameLink = lobby.getGameLink();
    }


    public void setLobbyImageUrl(String lobbyImageUrl) {
        mLobbyImageUrl = lobbyImageUrl;
    }

    public void setLobbyName(String lobbyName) {
        mLobbyName = lobbyName;
    }

    public void setLobbyParticipantsName(String lobbyParticipantsName) {
        mLobbyParticipantsName = lobbyParticipantsName;
    }

    public void setLobbyParticipantsQuantity(int lobbyParticipantsQuantity) {
        mLobbyParticipantsQuantity = lobbyParticipantsQuantity;
    }

    public void setGameLink(String gameLink) {
        mGameLink = gameLink;
    }


    public String getLobbyImageUrl() {
        return mLobbyImageUrl;
    }

    public String getLobbyName() {
        return mLobbyName;
    }

    public String getLobbyParticipantsName() {
        return mLobbyParticipantsName;
    }

    public int getLobbyParticipantsQuantity() {
        return mLobbyParticipantsQuantity;
    }

    public String getGameLink() {
        return mGameLink;
    }
}
