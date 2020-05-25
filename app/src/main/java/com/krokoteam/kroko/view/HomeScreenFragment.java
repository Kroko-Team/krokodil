package com.krokoteam.kroko.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.krokoteam.kroko.data.model.Lobby;
import com.krokoteam.kroko.data.model.LobbyListLiveData;
import com.krokoteam.kroko.databinding.HomeScreenBinding;
import com.krokoteam.kroko.utils.BindingAdapters;
import com.krokoteam.kroko.utils.BitmapUtils;

import com.krokoteam.kroko.R;
import com.krokoteam.kroko.view.activities.GameActivity;
import com.krokoteam.kroko.viewmodel.HomeScreenViewModel;
import com.krokoteam.kroko.viewmodel.LobbyListViewModel;

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
    private LobbyAdapter mLobbyAdapter;
    private boolean mIsScrolling;
    private LobbyListViewModel mLobbyListViewModel;
    private List<Lobby> mLobbyList = new ArrayList<>();
    private RecyclerView mRecyclerViewForLobby;

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
        mLobbyListViewModel = new ViewModelProvider(this).get(LobbyListViewModel.class);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mHomeScreenBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false);
        mHomeScreenBinding.setOnProfileClick(mOnFragmentIconClickListener);

        getLobbies();

        return mHomeScreenBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerViewForLobby = mHomeScreenBinding.recyclerViewForLobby;
        mRecyclerViewForLobby.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        initRecyclerViewOnScrollListener();

        mLobbyAdapter = new LobbyAdapter(mLobbyList, getContext());
        mRecyclerViewForLobby.setAdapter(mLobbyAdapter);


//        mLobbyListViewModel.getProductListLiveData().observe(getViewLifecycleOwner(), Observer{});

        ImageView mProfileImageHomeScreen = view.findViewById(R.id.profile_image_home_screen_image_view);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.igor);
        mProfileImageHomeScreen.setImageDrawable(mBitmapUtils.createRoundedBitmapDrawableWithBorder(bitmap, getResources()));

        view.findViewById(R.id.find_table_home_screen_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("channel_name", "dfhsldkjfh");
                intent.putExtra("user_hash", "asdfhlkajsd");
                intent.putExtra("player_id", 0);
                startActivity(intent);
            }
        });
    }


    private void initRecyclerViewOnScrollListener() {
        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    mIsScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
                if (layoutManager != null) {
                    int firstVisibleProductPosition = layoutManager.findFirstVisibleItemPosition();
                    int visibleProductCount = layoutManager.getChildCount();
                    int totalProductCount = layoutManager.getItemCount();

                    if (mIsScrolling && (firstVisibleProductPosition + visibleProductCount == totalProductCount)) {
                        mIsScrolling = false;
                        getLobbies();
                    }
                }
            }
        };
        mRecyclerViewForLobby.addOnScrollListener(onScrollListener);
    }


    private void getLobbies() {
        LobbyListLiveData lobbyListLiveData = mLobbyListViewModel.getProductListLiveData();
        if (lobbyListLiveData != null) {
            lobbyListLiveData.observe(getViewLifecycleOwner(), operation -> {
                switch (operation.mType) {
                    case R.string.added:
                        Lobby addedLobby = operation.mLobby;
                        addLobby(addedLobby);
                        break;

                    case R.string.modified:
                        Lobby modifiedLobby = operation.mLobby;
                        modifyLobby(modifiedLobby);
                        break;

                    case R.string.removed:
                        Lobby removedLobby = operation.mLobby;
                        removeLobby(removedLobby);
                }
                mLobbyAdapter.notifyDataSetChanged();
            });
        }
    }

    private void addLobby(Lobby addedLobby) {
        mLobbyList.add(addedLobby);
    }

    private void modifyLobby(Lobby modifiedLobby) {
        for (int i = 0; i < mLobbyList.size(); i++) {
            Lobby currentLobby = mLobbyList.get(i);
            if (currentLobby.getRoomId().equals(modifiedLobby.getRoomId())) {
                mLobbyList.remove(currentLobby);
                mLobbyList.add(i, modifiedLobby);
            }
        }
    }

    private void removeLobby(Lobby removedLobby) {
        for (int i = 0; i < mLobbyList.size(); i++) {
            Lobby currentLobby = mLobbyList.get(i);
            if (currentLobby.getRoomId().equals(removedLobby.getRoomId())) {
                mLobbyList.remove(currentLobby);
            }
        }
    }


}
