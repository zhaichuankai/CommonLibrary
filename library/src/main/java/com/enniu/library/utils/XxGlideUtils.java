package com.enniu.library.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.enniu.library.widget.XxCornerTransform;

public class XxGlideUtils {
    public static void loadImg(Context context, Object url, ImageView imageView, int radius, int placeDrawable) {
        RequestBuilder<Drawable> builder = Glide.with(context)
                .load(url);
        if (radius != 0) {
            builder.apply(RequestOptions.bitmapTransform(new RoundedCorners(radius)));
        }
        builder.placeholder(ContextCompat.getDrawable(context, placeDrawable))
                .error(ContextCompat.getDrawable(context, placeDrawable))
                .into(imageView);
    }

    public static void loadImg(Context context, Object url, ImageView imageView, int radius, int placeDrawable, Drawable errorDrawable) {
        RequestBuilder<Drawable> builder = Glide.with(context)
                .load(url);
        if (radius != 0) {
            builder.apply(RequestOptions.bitmapTransform(new RoundedCorners(radius)));
        }
        builder.placeholder(ContextCompat.getDrawable(context, placeDrawable))
                .error(errorDrawable)
                .into(imageView);
    }

    public static void loadImg(Context context, Object url, ImageView imageView, XxCornerTransform transform, int placeDrawable) {
        Glide.with(context)
                .load(url).transform(transform)
                .placeholder(ContextCompat.getDrawable(context, placeDrawable))
                .error(ContextCompat.getDrawable(context, placeDrawable))
                .into(imageView);
    }
}