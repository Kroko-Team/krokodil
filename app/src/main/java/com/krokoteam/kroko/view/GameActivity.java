package com.krokoteam.kroko.view;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
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

public class GameActivity extends StreamingBaseActivity implements IStreamEventsHandler {

    private static final String LOG_TAG = GameActivity.class.getSimpleName();
    private static final int PERMISSION_REQ_ID = 1;
    private static final String[] REQUIRED_PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setupPlayerData();
        if (checkPermissions()) {
            setupAgoraStreamingService();
            Log.d(LOG_TAG, "setup complete");
        } else {
            Log.d(LOG_TAG, "YOU HAVE NO PERMISSIONS");
        }
    }

    // TODO: Взять данные из БД (ID канала, роль игрока и т.д.)
    private void setupPlayerData() {
        mUserID = 0;
        mChannelName = "testChannelName3";
    }

    private boolean checkPermissions() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, PERMISSION_REQ_ID);
                return false;
            }
        }
        return true;
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

    private void setupAgoraStreamingService() {
        registerStreamEventsHandler(this);
        setupStreamUI(findViewById(R.id.stream_layout_self), findViewById(R.id.stream_layout));
        setupVideoStream(StreamType.INCOMING, mUserID);
        findViewById(R.id.become_viewer).setOnClickListener(v -> {
            changePlayerStreamRole(PlayerRole.AUDIENCE);
            joinChannel(mChannelName, mUserID);
        });
        findViewById(R.id.become_broadcaster).setOnClickListener(v -> {
            changePlayerStreamRole(PlayerRole.BROADCASTER);
            joinChannel(mChannelName, mUserID);
        });
    }
    @Override
    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
        Log.d(LOG_TAG, "Join channel success. User: " + uid);
    }

    @Override
    public void onUserJoined(int uid, int elapsed) {
        Log.d(LOG_TAG, "Joined player: " + uid);
    }

    @Override
    public void onRemoteVideoStats(IRtcEngineEventHandler.RemoteVideoStats stats) {
        Log.d(LOG_TAG, "Remote video stats. User: " + stats.uid);
        setupVideoStream(StreamType.OUTCOMING, stats.uid);
    }
}
