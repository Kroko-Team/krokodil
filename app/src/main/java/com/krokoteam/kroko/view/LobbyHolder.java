package com.krokoteam.kroko.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.krokoteam.kroko.data.model.Lobby;
import com.krokoteam.kroko.databinding.LobbyListBinding;
import com.krokoteam.kroko.viewmodel.LobbyListItemViewModel;

/**
 * Created by Syelkonya on 12.04.2020.
 */
public class LobbyHolder extends RecyclerView.ViewHolder {

    private LobbyListBinding mLobbyListBinding;

    public LobbyHolder(LobbyListBinding binding) {
        super(binding.getRoot());
        mLobbyListBinding = binding;
    }

    public void bind(Lobby item){
        mLobbyListBinding.setLobby(new LobbyListItemViewModel(item));
        mLobbyListBinding.executePendingBindings();
    }
}
