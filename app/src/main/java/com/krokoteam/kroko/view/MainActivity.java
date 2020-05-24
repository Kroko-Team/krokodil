package com.krokoteam.kroko.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

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
                    .commit();
        }

//        HomeScreenFragment.newInstance().getView().setOnKeyListener( new View.OnKeyListener()
//        {
//            @Override
//            public boolean onKey( View v, int keyCode, KeyEvent event )
//            {
//                if( keyCode == KeyEvent.KEYCODE_BACK )
//                {
//                    finish();
//                    return true;
//                }
//                return false;
//            }
//        } );
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
}
