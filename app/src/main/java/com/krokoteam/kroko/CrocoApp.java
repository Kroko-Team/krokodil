package com.krokoteam.kroko;
import android.util.Log;
import io.agora.rtc.Constants;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoEncoderConfiguration;

import android.app.Application;

import com.krokoteam.kroko.view.IStreamEventsHandler;

public class CrocoApp extends Application {
    private final String LOG_TAG = getClass().getSimpleName();

    private RtcEngine mStreamEngine;
    private RtcEventsHandler mStreamActionsHandler = new RtcEventsHandler();

    @Override
    public void onCreate() {
        super.onCreate();
        setupStreamSettings();
    }

    public void setupStreamSettings() {
        try {
            mStreamEngine = RtcEngine.create(getApplicationContext(), getString(R.string.agora_app_id), mStreamActionsHandler);
            mStreamEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);
        }
        catch (Exception ex) {
            mStreamEngine = null;
            Log.d(LOG_TAG, "Cannot setup stream settings. Ex: " + ex.getMessage());
        }
    }

    public void registerEventHandler(IStreamEventsHandler handler) {
        mStreamActionsHandler.registerRtcEventsHandler(handler);
    }

    public void unregisterEventHandler(IStreamEventsHandler handler) {
        mStreamActionsHandler.unregisterRtcEventsHandler(handler);
    }

    public RtcEngine streamEngine() {
        return mStreamEngine;
    }
}
