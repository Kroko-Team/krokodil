package com.krokoteam.kroko.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.krokoteam.kroko.databinding.HomeScreenBinding;
import com.krokoteam.kroko.utils.BitmapUtils;

import com.google.android.material.appbar.AppBarLayout;
import com.krokoteam.kroko.R;
import com.krokoteam.kroko.viewmodel.HomeScreenViewModel;

/**
 * Created by Syelkonya on 03.04.2020.
 */
public class HomeScreenFragment extends Fragment {

    private BitmapUtils mBitmapUtils = new BitmapUtils();
    private HomeScreenViewModel.OpenProfileFragmentRouter mOpenProfileFragmentRouter;
    private HomeScreenViewModel.OpenCreateLobbyFragmentRouter mOpenCreateLobbyFragmentRouter;
    private final OnFragmentIconClickListener mOnFragmentIconClickListener = new OnFragmentIconClickListener() {
        @Override
        public void onProfileClick(View v) {
            switch (v.getId()) {
                case R.id.user_profile_home_screen_image_view:
                    mOpenProfileFragmentRouter.openProfileFragment();
                    break;
                case R.id.horse_icon_home_screen_image_view:
                    mOpenCreateLobbyFragmentRouter.openCreateLobbyFragment();
                    break;
            }
        }
    };


    static Fragment newInstance() {
        return new HomeScreenFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HomeScreenBinding binding =  HomeScreenBinding.inflate(inflater, container, false);
        binding.setOnProfileClick(mOnFragmentIconClickListener);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) view
                .findViewById(R.id.collapsing_toolbar_layout)
                .getLayoutParams();
        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);

        ImageView mProfileImageHomeScreen = view.findViewById(R.id.profile_image_home_screen_image_view);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.igor);
        Bitmap cropImg = mBitmapUtils.cropImage(bitmap);

        mProfileImageHomeScreen.setImageDrawable(mBitmapUtils.createRoundedBitmapDrawableWithBorder(cropImg, getResources()));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mOpenProfileFragmentRouter = (HomeScreenViewModel.OpenProfileFragmentRouter) context;
        mOpenCreateLobbyFragmentRouter = (HomeScreenViewModel.OpenCreateLobbyFragmentRouter) context;
    }

}
