package com.krokoteam.kroko.view;

import androidx.recyclerview.widget.RecyclerView;

import com.krokoteam.kroko.data.model.Lobby;
import com.krokoteam.kroko.databinding.LobbyListBinding;

/**
 * Created by Syelkonya on 12.04.2020.
 */
class LobbyHolder extends RecyclerView.ViewHolder {

    private LobbyListBinding mLobbyListBinding;

    LobbyHolder(LobbyListBinding binding) {
        super(binding.getRoot());
        mLobbyListBinding = binding;
    }

    void bind(Lobby item){
        mLobbyListBinding.setLobby(item);
        mLobbyListBinding.executePendingBindings();
    }
}
