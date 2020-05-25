package com.krokoteam.kroko.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.krokoteam.kroko.R;
import com.krokoteam.kroko.data.model.Lobby;
import com.krokoteam.kroko.view.activities.GameActivity;

public class MainActivity extends AppCompatActivity
        implements OpenProfileFragmentRouter, OpenCreateLobbyFragmentRouter, OpenHomeScreenFragmentRouter,
        OpenGameActivityRouter {


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


    @Override
    public void openHomeScreenFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.rootMainActivity, HomeScreenFragment.newInstance())
                .commit();
    }

    @Override
    public void openGameActivity(Lobby lobby) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("channel_name", "dfhsldkjfh");
        intent.putExtra("user_hash", "asdfhlkajsd");
        intent.putExtra("player_id", 0);
        startActivity(intent);
    }
}
