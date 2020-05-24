package com.krokoteam.kroko;

import com.krokoteam.kroko.view.IStreamEventsHandler;

import java.util.ArrayList;

import io.agora.rtc.IRtcEngineEventHandler;

public class RtcEventsHandler extends IRtcEngineEventHandler {
    private ArrayList<IStreamEventsHandler> mHandlers = new ArrayList<>();

    @Override
    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
        for (IStreamEventsHandler handler : mHandlers) {
            handler.onJoinChannelSuccess(channel, uid, elapsed);
        }
    }

    @Override
    public void onRemoteVideoStats(RemoteVideoStats stats) {
        for (IStreamEventsHandler handler : mHandlers) {
            handler.onRemoteVideoStats(stats);
        }
    }

    public void registerRtcEventsHandler(IStreamEventsHandler handler) {
        mHandlers.add(handler);
    }

    public void unregisterRtcEventsHandler(IStreamEventsHandler handler) {
        mHandlers.remove(handler);
    }
}
