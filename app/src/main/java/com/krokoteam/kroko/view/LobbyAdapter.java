package com.krokoteam.kroko.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.krokoteam.kroko.data.model.Lobby;
import com.krokoteam.kroko.databinding.LobbyListBinding;

import java.util.BitSet;
import java.util.List;

/**
 * Created by Syelkonya on 11.04.2020.
 */
public class LobbyAdapter extends RecyclerView.Adapter<LobbyHolder> {


    private final List<Lobby> mLobbyList;

    public LobbyAdapter(List<Lobby> lobbyList){
        mLobbyList = lobbyList;
    }

    @NonNull
    @Override
    public LobbyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LobbyListBinding binding = LobbyListBinding.inflate(inflater, parent, false);
        return new LobbyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LobbyHolder holder, int position) {
        Lobby lobby = mLobbyList.get(position);
        holder.bind(lobby);
    }

    @Override
    public int getItemCount() {
        return mLobbyList.size();
    }
}
