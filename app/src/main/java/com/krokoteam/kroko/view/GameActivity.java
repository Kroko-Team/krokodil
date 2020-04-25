package com.krokoteam.kroko.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;
import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.krokoteam.kroko.R;

public class GameActivity extends AppCompatActivity {

    private static final String LOG_TAG = GameActivity.class.getSimpleName();
    private static final int PERMISSION_REQ_ID = 1;
    private static final String[] REQUIRED_PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button establishConnection = findViewById(R.id.connect_button);
        establishConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissions();
            }
        });

        // mRtcEngine.enableVideo();
    }

    private void checkPermissions() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, PERMISSION_REQ_ID);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQ_ID) {
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    Log.d(LOG_TAG, "Failed get permissions for " + grantResult);
                    break;
                }
            }
        }
    }

    //    private void initializeAgoraEngine() {
//        try {
//            mRtcEngine = RtcEngine.create(getBaseContext(), getString(R.string.agora_app_id), mRtcEventHandler);
//        } catch (Exception e) {
//            Log.e(LOG_TAG, Log.getStackTraceString(e));
//            throw new RuntimeException("NEED TO check rtc sdk init fatal error" + Log.getStackTraceString(e));
//        }
//    }
//
//    private void joinChannel() {
//        // if you do not specify the uid, Agora will assign one.
//        mRtcEngine.joinChannel(null, "demoChannel1", "Extra Optional Data", 0);
//    }
//
//    private void leaveChannel() {
//        mRtcEngine.leaveChannel();
//    }
}
