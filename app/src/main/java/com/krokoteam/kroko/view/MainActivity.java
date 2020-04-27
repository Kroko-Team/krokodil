package com.krokoteam.kroko.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.krokoteam.kroko.R;

public class MainActivity extends AppCompatActivity
        implements OpenProfileFragmentRouter, OpenCreateLobbyFragmentRouter, OpenHomeScreenFragmentRouter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.rootMainActivity, HomeScreenFragment.newInstance())
                    .addToBackStack("HOME_SCREEN_FRAGMENT")
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


    @Override
    public void openHomeScreenFragment() {
        getSupportFragmentManager()
                .popBackStackImmediate("HOME_SCREEN_FRAGMENT", 0);
//                .beginTransaction()
//                .replace(R.id.rootMainActivity, HomeScreenFragment.newInstance())
//                .addToBackStack(null)
//                .commit();
    }
}
