package com.krokoteam.kroko.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.krokoteam.kroko.R;
import com.krokoteam.kroko.databinding.ProfileSettingsBinding;


/**
 * Created by Syelkonya on 04.04.2020.
 */
public class ProfileSettingsFragment extends Fragment {

    static Fragment newInstance() {
        return new ProfileSettingsFragment();
    }

    private ProfileSettingsBinding mProfileSettingsBinding;
    private OpenHomeScreenFragmentRouter mOpenHomeScreenFragmentRouter;
    private OpenCreateLobbyFragmentRouter mOpenCreateLobbyFragmentRouter;

    private final OnFragmentIconClickListener mOnFragmentIconClickListener = new OnFragmentIconClickListener() {
        @Override
        public void onFragmentIconClick(View v) {
            switch (v.getId()) {
                case R.id.home_icon_profile_settings_image_view:
                    mOpenHomeScreenFragmentRouter.openHomeScreenFragment();
                    break;
                case R.id.horse_icon_profile_settings_image_view:
                    mOpenCreateLobbyFragmentRouter.openCreateLobbyFragment();
                    break;
            }
        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mOpenHomeScreenFragmentRouter = (OpenHomeScreenFragmentRouter) context;
        mOpenCreateLobbyFragmentRouter = (OpenCreateLobbyFragmentRouter) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mProfileSettingsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_settings, container, false);
        mProfileSettingsBinding.setOnProfileClick(mOnFragmentIconClickListener);

        return mProfileSettingsBinding.getRoot();
    }

}
