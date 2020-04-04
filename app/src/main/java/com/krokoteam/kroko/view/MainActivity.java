package com.krokoteam.kroko.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.krokoteam.kroko.R;
import com.krokoteam.kroko.viewmodel.HomeScreenViewModel;

public class MainActivity extends AppCompatActivity implements HomeScreenViewModel.OpenProfileFragmentRouter, HomeScreenViewModel.OpenCreateLobbyFragmentRouter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.rootMainActivity, HomeScreenFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void openProfileFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootMainActivity, ProfileSettingsFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openCreateLobbyFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootMainActivity, CreateLobbyFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
