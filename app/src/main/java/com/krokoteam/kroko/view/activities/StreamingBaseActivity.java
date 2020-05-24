package com.krokoteam.kroko.view.activities;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.krokoteam.kroko.view.IStreamEventsHandler;
import com.krokoteam.kroko.view.activities.BaseActivity;

import io.agora.rtc.Constants;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;

public abstract class StreamingBaseActivity extends BaseActivity {

    private FrameLayout mLocalContainer, mRemoteContainer;
    protected String mChannelName;
    protected int mPlayerID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupVideoConfig();
    }

    protected void setupStreamUI(FrameLayout localContainer, FrameLayout remoteContainer) {
        mLocalContainer = localContainer;
        mRemoteContainer = remoteContainer;
    }

    protected void setupVideoConfig() {
        getStreamEngine().enableVideo();
        getStreamEngine().disableAudio();

        getStreamEngine().setVideoEncoderConfiguration(new VideoEncoderConfiguration(
                VideoEncoderConfiguration.VD_120x120,
                VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_10,
                VideoEncoderConfiguration.COMPATIBLE_BITRATE,
                VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT
        ));
    }

    protected void setupVideoStream(StreamType type, int userID) {
        SurfaceView surface = RtcEngine.CreateRendererView(getBaseContext());
        surface.setZOrderMediaOverlay(true);
        if (type == StreamType.INCOMING) {
            mLocalContainer.addView(surface);
            getStreamEngine().setupLocalVideo(new VideoCanvas(
                    surface,
                    VideoCanvas.RENDER_MODE_HIDDEN,
                    userID
            ));
        } else {
            mRemoteContainer.addView(surface);
            getStreamEngine().setupRemoteVideo(new VideoCanvas(
                    surface,
                    VideoCanvas.RENDER_MODE_HIDDEN,
                    userID
            ));
        }
    }

    protected void joinChannel(String channelName, int userID) {
        getStreamEngine().joinChannel(null, channelName, null, userID);
    }

    protected void leaveChannel() {
        getStreamEngine().leaveChannel();
    }

    protected void changePlayerStreamRole(PlayerRole role) {
        if (role == PlayerRole.BROADCASTER) {
            getStreamEngine().setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
        } else {
            getStreamEngine().setClientRole(Constants.CLIENT_ROLE_AUDIENCE);
        }
    }

    protected RtcEngine getStreamEngine() {
        return getCrocoApplication().streamEngine();
    }

    protected void registerStreamEventsHandler(IStreamEventsHandler handler) {
        getCrocoApplication().registerEventHandler(handler);
    }

    protected void unregisterStreamEventsHandler(IStreamEventsHandler handler) {
        getCrocoApplication().unregisterEventHandler(handler);
    }

    public enum PlayerRole {
        BROADCASTER,
        AUDIENCE
    }

    public enum StreamType {
        INCOMING,
        OUTCOMING
    }
}
