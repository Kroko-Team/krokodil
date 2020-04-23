package com.krokoteam.kroko.view;

import android.app.Application;
import android.content.Context;

import com.krokoteam.kroko.data.repository.network.ApiRepo;


public class ApplicationModified extends Application {

    private ApiRepo mApiRepo;
    private AuthRepo mAuthRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        mApiRepo = new ApiRepo();
        mAuthRepo = new AuthRepo(mApiRepo);
    }

    public AuthRepo getAuthRepo() {
        return mAuthRepo;
    }

    public ApiRepo getApis() {
        return mApiRepo;
    }

    public static ApplicationModified from(Context context) {
        return (ApplicationModified) context.getApplicationContext();
    }
}
