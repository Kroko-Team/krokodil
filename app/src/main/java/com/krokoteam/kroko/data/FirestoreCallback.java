package com.krokoteam.kroko.data;

import com.krokoteam.kroko.data.model.Lobby;

import java.util.List;

/**
 * Created by Syelkonya on 12.04.2020.
 */
public interface FirestoreCallback {
    void onCallBack(List<Lobby> list);
}
