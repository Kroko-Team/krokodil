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

import com.google.firebase.firestore.FirebaseFirestore;
import com.krokoteam.kroko.R;
import com.krokoteam.kroko.data.model.Lobby;
import com.krokoteam.kroko.data.model.Player;
import com.krokoteam.kroko.databinding.CreateLobbyBinding;

import java.util.ArrayList;

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
                Lobby lobby = new Lobby(
                        mEditTextCreateLobbyName.getText().toString(),
                        "hostUserId",
                        "hostUserName",
                        "https://sun9-58.userapi.com/c850536/v850536449/f7a49/thoil9Jlw34.jpg",
                        new ArrayList<Player>(),
                        getAlphaNumericString(),
                        "");

                db.collection("lobby").document(getAlphaNumericString()).set(lobby);
            }
        });

    }

    private String getAlphaNumericString() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {

            int index = (int)(AlphaNumericString.length() * Math.random());

            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
