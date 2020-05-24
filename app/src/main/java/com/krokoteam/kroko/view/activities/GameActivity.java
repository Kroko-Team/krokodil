package com.krokoteam.kroko.view.activities;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;

import io.agora.rtc.IRtcEngineEventHandler;

import android.os.Bundle;

import com.krokoteam.kroko.R;
import com.krokoteam.kroko.data.model.Lobby;
import com.krokoteam.kroko.data.model.Operation;
import com.krokoteam.kroko.data.repository.FirestoreLobbyListRepository;
import com.krokoteam.kroko.view.IStreamEventsHandler;

public class GameActivity extends StreamingBaseActivity implements IStreamEventsHandler {

    private static final String LOG_TAG = GameActivity.class.getSimpleName();
    private static final int PERMISSION_REQ_ID = 1;
    private static final String CHANNEL_NAME = "channel_name",
                                USER_HASH = "user_hash",
                                PLAYER_ID = "player_id";
    private static final String[] REQUIRED_PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private String mUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setupGameData(savedInstanceState);
        setupLobbyUpdateDataHandler();
        if (checkPermissions()) {
            setupAgoraStreamingService();
            Log.d(LOG_TAG, "setup complete");
        } else {
            Log.d(LOG_TAG, "YOU HAVE NO PERMISSIONS");
        }
    }

    private void setupGameData(Bundle bundle) {
        Bundle args = getIntent().getExtras();
        mPlayerID = args.getInt(PLAYER_ID);
        mUserID = args.getString(USER_HASH);
        mChannelName = args.getString(CHANNEL_NAME);
    }

    private void setupLobbyUpdateDataHandler() {
        FirestoreLobbyListRepository.getInstance().getLobbyListLiveData().observe(this, new Observer<Operation>() {
            @Override
            public void onChanged(Operation operation) {
                Log.d(LOG_TAG, "Updated data from server: " + operation.mLobby.getGameName());
                updateGameData(operation.mLobby);
            }
        });
    }

    private void updateGameData(Lobby lobby) {
        if (lobby.getCurrentGameStatement() == Lobby.GameStatus.END) {
            gameCompletion();
        }

        if (lobby.getPlayerByUserId(mUserID).isBroadcaster()) {
            changePlayerStreamRole(PlayerRole.BROADCASTER);
        } else {
            changePlayerStreamRole(PlayerRole.AUDIENCE);
        }
    }

    // TODO: завершение игры
    private void gameCompletion() {

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
        setupVideoStream(StreamType.INCOMING, mPlayerID);
        findViewById(R.id.become_viewer).setOnClickListener(v -> {
            changePlayerStreamRole(PlayerRole.AUDIENCE);
            joinChannel(mChannelName, mPlayerID);
        });
        findViewById(R.id.become_broadcaster).setOnClickListener(v -> {
            changePlayerStreamRole(PlayerRole.BROADCASTER);
            joinChannel(mChannelName, mPlayerID);
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
