package com.krokoteam.kroko;

import io.agora.rtc.IRtcEngineEventHandler;

public class StreamEventsHandler extends IRtcEngineEventHandler {


    @Override
    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
        super.onJoinChannelSuccess(channel, uid, elapsed);

    }

    @Override
    public void onRemoteVideoStats(RemoteVideoStats stats) {
        super.onRemoteVideoStats(stats);

    }
}
