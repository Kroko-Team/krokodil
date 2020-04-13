package com.krokoteam.kroko.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by Syelkonya on 13.04.2020.
 */
public class BindingAdapters {

    private static Bitmap mBitmap;
    private  static BitmapUtils mBitmapUtils = new BitmapUtils();

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Picasso.get().load(imageUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                mBitmapUtils.cropImage(bitmap);
                mBitmapUtils.createRoundedBitmapDrawableWithBorder(bitmap, Resources.getSystem());
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {}
        });


    }
}
