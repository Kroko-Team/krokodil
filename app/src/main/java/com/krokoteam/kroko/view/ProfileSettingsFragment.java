package com.krokoteam.kroko.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.krokoteam.kroko.R;
import com.krokoteam.kroko.data.FirestoreCallback;
import com.krokoteam.kroko.data.LobbyDatabase;
import com.krokoteam.kroko.data.model.Lobby;
import com.krokoteam.kroko.viewmodel.ProfileSettingsViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Syelkonya on 04.04.2020.
 */
public class ProfileSettingsFragment extends Fragment {

    private LobbyDatabase mLobbyDatabase = new LobbyDatabase();
    private FirebaseFirestore mFirebaseFirestore = FirebaseFirestore.getInstance();
    private ProfileSettingsViewModel mProfileSettingsViewModel = new ProfileSettingsViewModel();
    private ArrayList<Lobby> mLobbyList = new ArrayList<>();
    private String TAG = getClass().getName();
    private TextView tv;


    static Fragment newInstance() {
        return new ProfileSettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv = view.findViewById(R.id.text_view_profile_fragment);





    }



}
