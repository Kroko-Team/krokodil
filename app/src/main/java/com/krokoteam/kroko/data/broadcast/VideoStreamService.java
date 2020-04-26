package com.krokoteam.kroko.data.broadcast;

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

import com.krokoteam.kroko.R;


public class VideoStreamService {
    private static VideoStreamService mInstance;

    public static VideoStreamService getInstance() {
        if (mInstance == null) {
            mInstance = new VideoStreamService();
        }
        return mInstance;
    }



    private VideoStreamService() {

    }

    private void setupHost() {

    }
}
