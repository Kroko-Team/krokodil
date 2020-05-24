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
import com.krokoteam.kroko.databinding.CreateLobbyBinding;

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
}
