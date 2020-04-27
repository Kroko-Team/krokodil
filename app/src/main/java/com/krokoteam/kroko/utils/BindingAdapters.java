package com.krokoteam.kroko.utils;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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

}
