package com.krokoteam.kroko.data.repository;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.krokoteam.kroko.data.model.LobbyListLiveData;
import com.krokoteam.kroko.viewmodel.LobbyListViewModel;

/**
 * Created by Syelkonya on 23.05.2020.
 */
public class FirestoreLobbyListRepository implements LobbyListViewModel.LobbyListRepository,
        LobbyListLiveData.OnLastVisibleLobbyCallback, LobbyListLiveData.OnLastLobbyReachedCallback{

    private static final int LIMIT = 20;
    private static final String ROOM_ID = "mRoomId";
    private final String LOBBY_COLLECTION = "lobby";
    private FirebaseFirestore mFirebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference mLobbiesReference = mFirebaseFirestore.collection(LOBBY_COLLECTION);
    private Query mQuery = mLobbiesReference.limit(LIMIT);
    private DocumentSnapshot mLastVisibleLobby;
    private boolean mIsLastLobbyReached;

    private static FirestoreLobbyListRepository mInstance;

    public static FirestoreLobbyListRepository getInstance() {
        if (mInstance == null) {
            mInstance = new FirestoreLobbyListRepository();
        }
        return mInstance;
    }

    @Override
    public void setLastVisibleLobby(DocumentSnapshot lastVisibleLobby) {
        mLastVisibleLobby = lastVisibleLobby;
    }

    @Override
    public void setLastLobbyReached(boolean isLastLobbyReached) {
        mIsLastLobbyReached = isLastLobbyReached;
    }

    @Override
    public LobbyListLiveData getLobbyListLiveData() {
        if (mIsLastLobbyReached)
            return null;
        if (mLastVisibleLobby != null) {
            mQuery = mQuery.startAfter(mLastVisibleLobby);
        }
        return new LobbyListLiveData(mQuery, this, this);
    }
}
