package com.krokoteam.kroko.data.model;

/**
 * Created by Syelkonya on 23.05.2020.
 */
public class Operation {
    public Lobby mLobby;
    public int mType;

    Operation(Lobby lobby, int type) {
        mLobby = lobby;
        mType = type;
    }
}
