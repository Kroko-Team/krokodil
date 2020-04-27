package com.krokoteam.kroko.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.krokoteam.kroko.R;

/**
 * Created by Syelkonya on 05.04.2020.
 */
public class CreateLobbyFragment extends Fragment {

    public static CreateLobbyFragment newInstance() {
        return  new CreateLobbyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_lobby, container, false);
    }
}
