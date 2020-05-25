package com.krokoteam.kroko.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.krokoteam.kroko.R;
import com.krokoteam.kroko.data.model.Lobby;
import com.krokoteam.kroko.data.model.Player;
import com.krokoteam.kroko.data.repository.FirestoreLobbyListRepository;
import com.krokoteam.kroko.databinding.CreateLobbyBinding;
import com.krokoteam.kroko.utils.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Syelkonya on 05.04.2020.
 */
public class CreateLobbyFragment extends Fragment {

    static CreateLobbyFragment newInstance() {
        return  new CreateLobbyFragment();
    }

    private CreateLobbyBinding mCreateLobbyBinding;
    private OpenHomeScreenFragmentRouter mOpenHomeScreenFragmentRouter;
    private OpenProfileFragmentRouter mOpenProfileFragmentRouter;
    private Button mButtonCreateLobby;
    private EditText mEditTextCreateLobbyName;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private final OnFragmentIconClickListener mOnFragmentIconClickListener = new OnFragmentIconClickListener() {
        @Override
        public void onFragmentIconClick(View v) {
            switch (v.getId()) {
                case R.id.home_icon_create_lobby_image_view:
                    mOpenHomeScreenFragmentRouter.openHomeScreenFragment();
                    break;
                case R.id.user_profile_create_lobby_image_view:
                    mOpenProfileFragmentRouter.openProfileFragment();
                    break;
            }
        }
    };


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mOpenHomeScreenFragmentRouter = (OpenHomeScreenFragmentRouter) context;
        mOpenProfileFragmentRouter = (OpenProfileFragmentRouter) context;
     }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mCreateLobbyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_lobby, container, false);
        mCreateLobbyBinding.setOnProfileClick(mOnFragmentIconClickListener);

        return mCreateLobbyBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mButtonCreateLobby = view.findViewById(R.id.create_lobby_button);
        mEditTextCreateLobbyName = view.findViewById(R.id.lobby_name_edit_text);


        mButtonCreateLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> player = new HashMap<>();
                player.put("mIsBroadcaster", false);
                player.put("mIsWinner", false);
                player.put("mScore", 0);
                player.put("mUserId", 0);
                player.put("mUserName", "Тит");
                ArrayList<Map<String, Object>> players = new ArrayList<>();
                players.add(player);

                Map<String, Object> docData = new HashMap<>();
                docData.put("mGameName", mEditTextCreateLobbyName.getText().toString());
                docData.put("mHostUserId", "aaa");
                docData.put("mHostUserName", "tetus");
                docData.put("mImageUrl", "https://sun9-58.userapi.com/c850536/v850536449/f7a49/thoil9Jlw34.jpg");
                docData.put("mRoomId", Utilities.getInstance().getAlphaNumericString());
                docData.put("mSecretWord", "ля");
                docData.put("mGameStatus", 0);
                docData.put("mPlayers", players);

                FirestoreLobbyListRepository.getInstance().insertLobby(docData);
            }
        });

    }
}
