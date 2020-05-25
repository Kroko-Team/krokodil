package com.krokoteam.kroko.viewmodel;

import androidx.lifecycle.ViewModel;

import com.krokoteam.kroko.data.repository.FirestoreLobbyListRepository;
import com.krokoteam.kroko.data.model.LobbyListLiveData;

/**
 * Created by Syelkonya on 23.05.2020.
 */
public class LobbyListViewModel extends ViewModel {
    private LobbyListRepository mLobbyListRepository = FirestoreLobbyListRepository.getInstance();

    public LobbyListLiveData getProductListLiveData() {
        return mLobbyListRepository.getLimitedLobbyListLiveData();
    }

    public interface LobbyListRepository {
        LobbyListLiveData getLimitedLobbyListLiveData();
    }

}
