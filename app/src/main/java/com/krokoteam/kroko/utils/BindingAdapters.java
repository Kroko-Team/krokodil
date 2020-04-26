package com.krokoteam.kroko.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.krokoteam.kroko.data.model.Lobby;
import com.krokoteam.kroko.view.LobbyAdapter;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by Syelkonya on 13.04.2020.
 */
public class BindingAdapters {


    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {


        Glide.with(imageView)
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(imageView);
    }


    @BindingAdapter({"bind:data"})
    public static void configureRecyclerView(RecyclerView recyclerView, List<Lobby> lobbyList) {
        LobbyAdapter lobbyAdapter = new LobbyAdapter(lobbyList, recyclerView.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(lobbyAdapter);
    }
}
