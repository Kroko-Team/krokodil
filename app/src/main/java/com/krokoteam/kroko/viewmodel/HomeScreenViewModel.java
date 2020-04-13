package com.krokoteam.kroko.viewmodel;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.krokoteam.kroko.data.FirestoreCallback;
import com.krokoteam.kroko.data.LobbyDatabase;
import com.krokoteam.kroko.data.model.Lobby;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Syelkonya on 04.04.2020.
 */
public class HomeScreenViewModel extends ViewModel {




    private ObservableArrayList<Lobby> mLobbyList;
    private LobbyDatabase mLobbyDatabase = new LobbyDatabase();

    public ObservableArrayList<Lobby> getLobbyList() {
        return mLobbyList;
    }

    public void setLobbyList(ObservableArrayList<Lobby> lobbyList) {
        mLobbyList = lobbyList;
    }

    public void getLobbiesFromLobbyDatabase() {
        mLobbyDatabase.readData(new FirestoreCallback() {
            @Override
            public void onCallBack(List<Lobby> list) {
                mLobbyList = (ObservableArrayList<Lobby>) list;
            }
        });
    }
}
