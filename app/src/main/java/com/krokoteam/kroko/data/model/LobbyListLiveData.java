package com.krokoteam.kroko.data.model;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.krokoteam.kroko.R;

/**
 * Created by Syelkonya on 23.05.2020.
 */
@SuppressWarnings("ConstantConditions")
public class LobbyListLiveData extends LiveData<Operation> implements EventListener<QuerySnapshot> {
    private static final int LIMIT = 20;
    private Query query;
    private ListenerRegistration listenerRegistration;
    private OnLastVisibleLobbyCallback mOnLastVisibleLobbyCallback;
    private OnLastLobbyReachedCallback mOnLastLobbyReachedCallback;

    public LobbyListLiveData(Query query, OnLastVisibleLobbyCallback onLastVisibleLobbyCallback, OnLastLobbyReachedCallback onLastLobbyReachedCallback) {
        this.query = query;
        this.mOnLastVisibleLobbyCallback = onLastVisibleLobbyCallback;
        this.mOnLastLobbyReachedCallback = onLastLobbyReachedCallback;
    }

    @Override
    protected void onActive() {
        listenerRegistration = query.addSnapshotListener(this);
    }

    @Override
    protected void onInactive() {
        listenerRegistration.remove();
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot querySnapshot, @Nullable FirebaseFirestoreException e) {
        if (e != null) return;


            for (DocumentChange documentChange : querySnapshot.getDocumentChanges()) {
                switch (documentChange.getType()) {
                    case ADDED:
                        Lobby addedLobby = documentChange.getDocument().toObject(Lobby.class);
                        Operation addOperation = new Operation(addedLobby, R.string.added);
                        setValue(addOperation);
                        break;

                    case MODIFIED:
                        Lobby modifiedLobby = documentChange.getDocument().toObject(Lobby.class);
                        Operation modifyOperation = new Operation(modifiedLobby, R.string.modified);
                        setValue(modifyOperation);
                        break;

                    case REMOVED:
                        Lobby removedLobby = documentChange.getDocument().toObject(Lobby.class);
                        Operation removeOperation = new Operation(removedLobby, R.string.removed);
                        setValue(removeOperation);
                }
            }


        int querySnapshotSize = querySnapshot.size();
        if (querySnapshotSize < LIMIT) {
            mOnLastLobbyReachedCallback.setLastLobbyReached(true);
        } else {
            DocumentSnapshot lastVisibleLobby = querySnapshot.getDocuments().get(querySnapshotSize - 1);
            mOnLastVisibleLobbyCallback.setLastVisibleLobby(lastVisibleLobby);
        }
    }

    public interface OnLastVisibleLobbyCallback {
        void setLastVisibleLobby(DocumentSnapshot lastVisibleLobby);
    }

    public interface OnLastLobbyReachedCallback {
        void setLastLobbyReached(boolean isLastLobbyReached);
    }
}
