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
 * Created by Syelkonya on 04.04.2020.
 */
public class ProfileSettingsFragment extends Fragment {

    static Fragment newInstance() {
        return new ProfileSettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_settings, container, false);
    }
}
