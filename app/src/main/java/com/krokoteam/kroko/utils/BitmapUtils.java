package com.krokoteam.kroko.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.krokoteam.kroko.R;


/**
 * Created by Syelkonya on 04.04.2020.
 */
public class BitmapUtils{



    private Bitmap cropImage(Bitmap bitmap){
        int width  = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = Math.min(height, width);
        int newHeight = (height > width)? height - ( height - width) : height;
        int cropW = (width - height) / 2;
        cropW = Math.max(cropW, 0);
        int cropH = (height - width) / 2;
        cropH = Math.max(cropH, 0);
        return Bitmap.createBitmap(bitmap, cropW, cropH, newWidth, newHeight);
    }


    public RoundedBitmapDrawable createRoundedBitmapDrawableWithBorder(Bitmap bitmap, Resources resources){

       bitmap = cropImage(bitmap);

        final int BORDER_WIDTH_HALF = 24;
        int bitmapDiameter = bitmap.getWidth();
        int bitmapRadius = bitmapDiameter/2;
        int newBitmapSquareWidth = bitmapDiameter + BORDER_WIDTH_HALF;
        Bitmap roundedBitmap = Bitmap.createBitmap(newBitmapSquareWidth,newBitmapSquareWidth,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(roundedBitmap);
        canvas.drawBitmap(bitmap, BORDER_WIDTH_HALF, BORDER_WIDTH_HALF, null);


        Paint borderPaintGreen = new Paint();
        borderPaintGreen.setStyle(Paint.Style.STROKE);
        borderPaintGreen.setStrokeWidth(BORDER_WIDTH_HALF*2);
        borderPaintGreen.setColor(resources.getColor(R.color.colorPurple));
        canvas.drawCircle(canvas.getWidth()/2.0f, canvas.getWidth()/2.0f, newBitmapSquareWidth/2.0f, borderPaintGreen);

        Paint borderPaintWhite = new Paint();
        borderPaintWhite.setStyle(Paint.Style.STROKE);
        borderPaintWhite.setStrokeWidth(BORDER_WIDTH_HALF);
        borderPaintWhite.setColor(resources.getColor(R.color.colorGreen));
        canvas.drawCircle(canvas.getWidth()/2.0f, canvas.getWidth()/2.0f, newBitmapSquareWidth/2.0f, borderPaintWhite);


        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(resources,roundedBitmap);
        roundedBitmapDrawable.setCornerRadius(bitmapRadius);
        roundedBitmapDrawable.setAntiAlias(true);

        return roundedBitmapDrawable;
    }

}
