package com.krokoteam.kroko.data.repository.network;

import android.content.Context;

import com.krokoteam.kroko.view.ApplicationModified;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiRepo {
    private final UserApi mUserApi;
    private final OkHttpClient mOkHttpClient;

    public ApiRepo() {
        mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(new HttpUrl.Builder().scheme("https")
                        .host("kroko-proj.firebaseio.com")
                        .build())
                .client(mOkHttpClient)
                .build();

        mUserApi = retrofit.create(UserApi.class);
    }

    public UserApi getUserApi() {
        return mUserApi;
    }

    public static ApiRepo from(Context context) {
        return ApplicationModified.from(context).getApis();
    }
}
