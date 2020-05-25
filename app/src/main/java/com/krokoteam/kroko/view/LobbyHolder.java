package com.krokoteam.kroko.view;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.krokoteam.kroko.data.model.Lobby;
import com.krokoteam.kroko.databinding.LobbyListBinding;
import com.krokoteam.kroko.view.activities.GameActivity;

/**
 * Created by Syelkonya on 12.04.2020.
 */
class LobbyHolder extends RecyclerView.ViewHolder {

    private LobbyListBinding mLobbyListBinding;
    private OpenGameActivityRouter mOpenGameActivityRouter;

    LobbyHolder(LobbyListBinding binding, Context context) {
        super(binding.getRoot());
        mLobbyListBinding = binding;
        mOpenGameActivityRouter = (OpenGameActivityRouter) context;
    }

    void bind(Lobby item){
        mLobbyListBinding.setLobby(item);
        mLobbyListBinding.executePendingBindings();

        mLobbyListBinding.setOnEnterLobbyClickListener(new OnEnterLobbyClickListener() {
            @Override
            public void onLobbyButtonClicked(Lobby lobby) {
                mOpenGameActivityRouter.openGameActivity(lobby);
            }
        });
    }
}
