package com.krokoteam.kroko.view;

import io.agora.rtc.IRtcEngineEventHandler;

public interface IStreamEventsHandler {
    void onJoinChannelSuccess(String channel, final int uid, int elapsed);
    void onUserJoined(int uid, int elapsed);
    void onRemoteVideoStats(IRtcEngineEventHandler.RemoteVideoStats stats);
}
