package com.krokoteam.kroko.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.krokoteam.kroko.data.FirestoreCallback;
import com.krokoteam.kroko.data.LobbyDatabase;
import com.krokoteam.kroko.data.model.Lobby;
import com.krokoteam.kroko.databinding.HomeScreenBinding;
import com.krokoteam.kroko.utils.BindingAdapters;
import com.krokoteam.kroko.utils.BitmapUtils;

import com.krokoteam.kroko.R;
import com.krokoteam.kroko.viewmodel.HomeScreenViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Syelkonya on 03.04.2020.
 */
public class HomeScreenFragment extends Fragment {

    private BitmapUtils mBitmapUtils = new BitmapUtils();
    private OpenProfileFragmentRouter mOpenProfileFragmentRouter;
    private OpenCreateLobbyFragmentRouter mOpenCreateLobbyFragmentRouter;
    private HomeScreenBinding mHomeScreenBinding;
    private HomeScreenViewModel mHomeScreenViewModel;
    private String TAG = getClass().getName();
    private LobbyDatabase mLobbyDatabase = LobbyDatabase.getInstance();
    private LobbyAdapter mLobbyAdapter;

    private final OnFragmentIconClickListener mOnFragmentIconClickListener = new OnFragmentIconClickListener() {
        @Override
        public void onFragmentIconClick(View v) {
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mOpenProfileFragmentRouter = (OpenProfileFragmentRouter) context;
        mOpenCreateLobbyFragmentRouter = (OpenCreateLobbyFragmentRouter) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        mLobbyDatabase.readData(new FirestoreCallback() {
//            @Override
//            public void onCallBack(List<Lobby> list) {
//                try {
//                    Thread.sleep(10_000);
//                } catch (InterruptedException e) {
//                    Log.e(TAG, "problem sleeping");
//                }
//                mLobbyAdapter = new LobbyAdapter(list, getContext());
//            }
//        });


//
//        List<Lobby> list = new ArrayList<>();
//        Lobby lobby123 = new Lobby();
//        lobby123.setImageUrl("https://firebasestorage.googleapis.com/v0/b/kroko-proj.appspot.com/o/images%2Fmitsubishi-lancer-evolution-9-2028.jpg?alt=media&token=4687782a-6fff-4141-913e-9150816efc1b");
//        lobby123.setName("gameLikkers");
//        lobby123.setParticipantsName("Ваня, Коля, Вася");
//        lobby123.setParticipantsQuantity(3);
//        lobby123.setGameLink("123");
//        list.add(lobby123);
//
//        mLobbyAdapter = new LobbyAdapter(list, getContext());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mHomeScreenBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false);


        RecyclerView recyclerViewForLobby = mHomeScreenBinding.recyclerViewForLobby;
        recyclerViewForLobby.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        recyclerViewForLobby.setAdapter(mLobbyAdapter);

        List<Lobby> list = new ArrayList<>();

        Lobby lobby123 = new Lobby();
        lobby123.setImageUrl("https://firebasestorage.googleapis.com/v0/b/kroko-proj.appspot.com/o/images%2Fmitsubishi-lancer-evolution-9-2028.jpg?alt=media&token=4687782a-6fff-4141-913e-9150816efc1b");
        lobby123.setName("gameLikkers");
        lobby123.setParticipantsName("Ваня, Коля, Вася");
        lobby123.setParticipantsQuantity(3);
        lobby123.setGameLink("123");
        list.add(lobby123);


        mLobbyAdapter = new LobbyAdapter(list, getContext());

        recyclerViewForLobby.setAdapter(mLobbyAdapter);

        return mHomeScreenBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView mProfileImageHomeScreen = view.findViewById(R.id.profile_image_home_screen_image_view);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.igor);
        mProfileImageHomeScreen.setImageDrawable(mBitmapUtils.createRoundedBitmapDrawableWithBorder(bitmap, getResources()));


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
