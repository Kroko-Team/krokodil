package com.krokoteam.kroko.view.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.krokoteam.kroko.CrocoApp;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected CrocoApp getCrocoApplication() {
        return (CrocoApp) getApplication();
    }
}
