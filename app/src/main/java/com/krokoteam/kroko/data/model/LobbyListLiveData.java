package com.krokoteam.kroko.data.model;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.krokoteam.kroko.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static final String PLAYERS_TAG = "mPlayers",
                                GAME_HOST_NAME_TAG = "mHostUserName",
                                GAME_HOST_USER_ID_TAG = "mUserId",
                                IS_BROADCASTER_TAG = "mIsBroadcaster",
                                IS_WINNER_TAG = "mIsWinner",
                                PLAYER_SCORE_TAG = "mScore",
                                PLAYER_ID_TAG = "mUserId",
                                PLAYER_NAME_TAG = "mUserId",
                                GAME_NAME_TAG = "mGameName",
                                IMAGE_URL_TAG = "mImageUrl",
                                ROOM_ID_TAG = "mRoomId",
                                SECRET_WORD_TAG = "mSecretWord";

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
                    // Lobby addedLobby = documentChange.getDocument().toObject(Lobby.class);
                    QueryDocumentSnapshot lobbyInfo = documentChange.getDocument();
                    List<HashMap<String, String>> playersData =
                            (List<HashMap<String, String>>) lobbyInfo.get(PLAYERS_TAG);
                    ArrayList<Player> players = new ArrayList<>();
                    for (HashMap<String, String> player : playersData) {
                        Player pojoPlayer = new Player(
                                Boolean.parseBoolean(player.get(IS_BROADCASTER_TAG)),
                                Boolean.parseBoolean(player.get(IS_WINNER_TAG)),
                                Integer.parseInt(player.get(PLAYER_SCORE_TAG)),
                                player.get(PLAYER_ID_TAG),
                                player.get(PLAYER_NAME_TAG)
                        );
                        players.add(pojoPlayer);
                    }
                    Lobby lobby = new Lobby(
                            lobbyInfo.getString(GAME_HOST_NAME_TAG),
                            lobbyInfo.getString(GAME_HOST_USER_ID_TAG),
                            lobbyInfo.getString(GAME_NAME_TAG),
                            lobbyInfo.getString(IMAGE_URL_TAG),
                            players,
                            lobbyInfo.getString(ROOM_ID_TAG),
                            lobbyInfo.getString(SECRET_WORD_TAG)
                    );
                    Operation addOperation = new Operation(lobby, R.string.added);
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
