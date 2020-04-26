package com.krokoteam.kroko.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.krokoteam.kroko.data.model.Lobby;
import com.krokoteam.kroko.view.ProfileSettingsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Syelkonya on 09.04.2020.
 */
public class LobbyDatabase {

    private static final LobbyDatabase INSTANCE = new LobbyDatabase();

    private LobbyDatabase(){}

    public static LobbyDatabase getInstance(){
        return INSTANCE;
    }

    private static final String TAG = LobbyDatabase.class.getSimpleName();
    private FirebaseFirestore mFirebaseFirestore = FirebaseFirestore.getInstance();
    private ArrayList<Lobby> mLobbyList = new ArrayList<>();


    public void readData(FirestoreCallback firestoreCallback) {
        mFirebaseFirestore.collection("lobby")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Lobby lobby = document.toObject(Lobby.class);
                                mLobbyList.add(lobby);
                            }
                            firestoreCallback.onCallBack(mLobbyList);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}

