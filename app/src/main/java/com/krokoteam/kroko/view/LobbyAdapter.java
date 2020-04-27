package com.krokoteam.kroko.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.krokoteam.kroko.R;
import com.krokoteam.kroko.data.model.Lobby;
import com.krokoteam.kroko.databinding.LobbyListBinding;

import java.util.BitSet;
import java.util.List;

/**
 * Created by Syelkonya on 11.04.2020.
 */
public class LobbyAdapter extends RecyclerView.Adapter<LobbyHolder> {


    private final List<Lobby> mLobbyList;
    private Context mContext;

    public LobbyAdapter(List<Lobby> lobbyList, Context context){
        mLobbyList = lobbyList;
        mContext = context;
    }

    @NonNull
    @Override
    public LobbyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LobbyListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.lobby_list_item, parent, false);

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
