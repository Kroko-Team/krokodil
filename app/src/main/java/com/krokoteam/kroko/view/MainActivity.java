package com.krokoteam.kroko.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.krokoteam.kroko.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mainactivity_layout, AuthFragment.newInstance())
                    .commit();
        }
    }
}
