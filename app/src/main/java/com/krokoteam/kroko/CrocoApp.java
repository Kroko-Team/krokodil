package com.krokoteam.kroko;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.app.Application;

public class CrocoApp extends Application {
//    private final String LOG_TAG = getClass().getSimpleName();
//
//    private RtcEngine mStreamEngine;
//    private IRtcEngineEventHandler mStreamActionsHandler;
//    private final String AGORA_APP_ID = getString(R.string.agora_app_id);
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        // setupStreamSettings();
//    }
//
//    public void setupStreamSettings() {
//        try {
//            mStreamEngine = RtcEngine.create(getApplicationContext(), AGORA_APP_ID, mStreamActionsHandler);
//            mStreamEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);
//
//        }
//        catch (Exception ex) {
//            mStreamEngine = null;
//            // Log.d(LOG_TAG, "Cannot setup stream settings. Ex: " + ex.getMessage());
//        }
//    }
//
//    private void setupVideoConfiguration(VideoSettings prefferedSettings) {
//        mStreamEngine.enableVideo();
//        mStreamEngine.disableAudio();
//
//        switch (prefferedSettings) {
//            case LOW:
//                mStreamEngine.setVideoEncoderConfiguration(new VideoEncoderConfiguration(
//                        VideoEncoderConfiguration.VD_120x120,
//                        VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_10,
//                        VideoEncoderConfiguration.COMPATIBLE_BITRATE,
//                        VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT
//                ));
//                break;
//            case HIGH:
//                mStreamEngine.setVideoEncoderConfiguration(new VideoEncoderConfiguration(
//                        VideoEncoderConfiguration.VD_640x480,
//                        VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_30,
//                        VideoEncoderConfiguration.COMPATIBLE_BITRATE,
//                        VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT
//                ));
//                break;
//        }
//    }
//
//    public enum VideoSettings {
//        LOW,
//        HIGH
//    }
}
