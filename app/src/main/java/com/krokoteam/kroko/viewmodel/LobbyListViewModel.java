package com.krokoteam.kroko.viewmodel;

import androidx.lifecycle.ViewModel;

import com.krokoteam.kroko.data.FirestoreLobbyListRepositoryCallback;
import com.krokoteam.kroko.data.model.Lobby;
import com.krokoteam.kroko.data.model.LobbyListLiveData;

/**
 * Created by Syelkonya on 23.05.2020.
 */
public class LobbyListViewModel extends ViewModel {
    private LobbyListRepository mLobbyListRepository = new FirestoreLobbyListRepositoryCallback();

    public LobbyListLiveData getProductListLiveData() {
        return mLobbyListRepository.getLobbyListLiveData();
    }

    public interface LobbyListRepository {
        LobbyListLiveData getLobbyListLiveData();
    }

}
