package com.krokoteam.kroko.viewmodel;

import androidx.lifecycle.ViewModel;

import com.krokoteam.kroko.databinding.HomeScreenBinding;

/**
 * Created by Syelkonya on 04.04.2020.
 */
public class HomeScreenViewModel extends ViewModel {
    /**
     * Created by Syelkonya on 04.04.2020.
     */




    public interface OpenProfileFragmentRouter {
        void openProfileFragment();
    }

    public interface OpenCreateLobbyFragmentRouter {
        void openCreateLobbyFragment();
    }
}
